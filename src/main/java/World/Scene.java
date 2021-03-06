package World;

import Graphics.Rgb;
import Matrix.Matrix;
import RayTracing.Intersection;
import RayTracing.IntersectionMap;
import RayTracing.Ray;
import Matrix.Point;
import Matrix.Vector;
import Objects.Shape;
import java.util.ArrayList;
import java.util.List;

import static Utils.Constants.*;

/**
 * Describes our 3D world.
 */
public class Scene {

    // Objects in the scene.
    public List<Shape> objects;

    // Light sources in the scene.
    public List<LightSource> sources;

    // Max recursion level.
    public int maxRecursionLevel;

    // Default background is air.
    public Rgb background = AIR;

    /**
     * Create a new scene.
     * @param maxRecursionLevel The maximum recursion level.
     */
    public Scene( int maxRecursionLevel ){
        // Create a list of objects and light sources.
        objects = new ArrayList<>();
        sources = new ArrayList<>();

        // Set the maximum recursion level.
        this.maxRecursionLevel = maxRecursionLevel;
    }

    /**
     * Add a shape to the list of objects.
     * @param shape The shape to be added.
     */
    public void addShape(Shape shape){
        objects.add( shape );
    }

    /**
     * Add a light to the list of sources.
     * @param source The light source to be added.
     */
    public void addSource(LightSource source){
        sources.add( source );
    }

    /**
     * Ray trace this scene.
     * @param ray The transmitted ray.
     * @return The appropriate color.
     */
    public Rgb rayTrace(Ray ray){

        // Get the closest intersection.
        Intersection intersection = getClosestIntersection( ray );

        // No intersections.
        if(intersection == null) {
            return background;
        }

        // Get the weights of the appropriate material.
        float[] weights = intersection.getObject().getMaterial().get_weights();

        // The color of the object.
        Rgb color = intersection.getObject().getMaterial().getColor();

        Rgb pixel_color = BLACK;
        if(intersection.getObject().hasImageTexture()){
            Point location = intersection.getObject().getInverseAT().times( intersection.getLocation() );
            double x = intersection.getObject().getPixelX(location);
            double y = intersection.getObject().getPixelY(location);
            pixel_color = intersection.getObject().getImageTexture().getRgb(x, y);
        }

        // Whether to apply the texture or not.
        boolean applyTexture = intersection.getObject().hasTexture();

        // Get ambient component.
        Rgb light = getAmbientComponent( intersection );
        if (applyTexture){
            Point location = intersection.getObject().getInverseAT().times( intersection.getLocation() );
            Rgb texelValue = intersection.getObject().getTexture().getTexelValue( location );
            light = light.multiply(texelValue);
        }
        if (intersection.getObject().hasImageTexture()){
            light = light.multiply(pixel_color);
        }

        // Loop over every light source (for shading purposes).
        for (LightSource L: sources){

            // Check for shadow.
            if ( isInShadow( getShadowRay( L, intersection )) || intersection.getTime() > 1){
                continue;
            }

            // Get and add diffuse component.
            light = light.add( getDiffuseComponent( L, intersection ) );
            if (applyTexture){
                Point location = intersection.getObject().getInverseAT().times( intersection.getLocation() );
                Rgb texelValue = intersection.getObject().getTexture().getTexelValue( location );
                light = light.multiply(texelValue);
            }
            if (intersection.getObject().hasImageTexture()){
                light = light.multiply(pixel_color);
            }

            // Get and add specular component.
            light = light.add( getSpecularComponent( L, intersection ) );

        }

        // Add light component to final color.
        color = color.add( light.multiply( weights[0]) );

        // Check recursion level.
        if (ray.recurseLevel == maxRecursionLevel){
            return color;
        }

        // Add reflected light if object is shiny enough.
        if (intersection.getObject().material.isShinyEnough()){

            Rgb reflected = getReflectedLight( intersection );
            color = color.add( reflected.multiply( weights[1]) );

        }

        // Add refracted light if the object is transparent enough.
        if (intersection.getObject().material.isTransparentEnough()){

            Rgb refracted = getTransmittedLight( intersection );
            color = color.add( refracted.multiply( weights[2] ) );

        }

        return color;
    }

    /**
     * Get the closest intersection between this scene and a given ray.
     * @param ray The given ray.
     * @return The closest intersection.
     */
    public Intersection getClosestIntersection(Ray ray){
        // Create intersection map.
        IntersectionMap intersectionMap = new IntersectionMap();

        // The starting point of the ray.
        Point origin = ray.start;

        // The direction of the ray.
        Vector direction = ray.dir;

        // Build intersection map.
        for (Shape object : objects){

            // Inverse transformation matrix.
            Matrix inverseAT = object.getInverseAT();

            // Transformed ray corresponding to object.
            Ray transformed = new Ray(
                    inverseAT.times( origin ),
                    inverseAT.times( direction )
            );
            // Both rays have the same recursive level.
            transformed.recurseLevel = ray.recurseLevel;

            // Get closest intersection between ray and object (if there is any).
            Intersection intersection = object.getClosestIntersection( transformed );

            // If intersection exists.
            if (intersection != null){
                intersection.setObject( object );
                intersection.setTransformedRay( transformed );
                intersectionMap.addIntersection( intersection );
            }
        }

        // Are there any intersections?
        if (intersectionMap.isEmpty()){
            return null;
        } else {
            // Get the closest intersection.
            Intersection intersection = intersectionMap.getClosestIntersection();

            // Get information about the intersection.
            Ray transformed = intersection.getTransformedRay();
            Matrix ATMatrix = intersection.getObject().getATMatrix();

            // Calculate location of intersection.
            intersection.setLocation( ATMatrix.times( transformed.getPoint( intersection.getTime() )));

            Ray hitRay = new Ray(origin, direction);
            hitRay.recurseLevel = ray.recurseLevel;
            intersection.setHitRay( hitRay );

            // Return the intersection.
            return intersection;
        }
    }

    /**
     * Returns true if the intersection is in shadow. Returns false otherwise.
     * @param ray The shadow ray.
     * @return True if in shadow. Returns false otherwise.
     */
    public boolean isInShadow(Ray ray){

        // Check if any object intersects with shadow ray.
        for (Shape object : objects){

            // Inverse transformation matrix.
            Matrix inverseAT = object.getInverseAT();

            // Transformed ray corresponding to object.
            Ray transformed = new Ray(
                    inverseAT.times( ray.start ),
                    inverseAT.times( ray.dir )
            );

            // Get closest intersection between ray and object (if there is any).
            Intersection intersection = object.getClosestIntersection( transformed );

            // Shadow if an intersection was found.
            if (intersection != null){
                return true;
            }
        }

        // No shadow of no intersections were found.
        return false;

    }

    /**
     * Create a shadow ray (from intersection to light source).
     * @param L The light source.
     * @param intersection The intersection.
     * @return The shadow ray.
     */
    public Ray getShadowRay(LightSource L, Intersection intersection){

        // Create shadow ray.
        Ray shadowRay = new Ray();

        // The direction from the intersection to the light source.
        Vector dir = L.getLocation().minus( intersection.getLocation() );
        dir.normalize();

        // Calculate the starting point of the shadow ray.
        Point start = intersection.getLocation().minus(
                new Vector(EPSILON * dir.getX(),
                        EPSILON * dir.getY(),
                        EPSILON * dir.getZ())
        );

        // Set the starting point of the shadow ray.
        shadowRay.setStart( start );

        // Set the direction of the shadow ray.
        shadowRay.setDir( dir );

        return shadowRay;

    }

    /**
     * Compute the ambient component.
     * @param intersection Info about the first hit.
     * @return The color that represents the ambient component.
     */
    public Rgb getAmbientComponent(Intersection intersection){

        // Get the reflective coefficients.
        return intersection.getObject().material.ambient_coefficients();

    }

    /**
     * Compute the diffuse component.
     * @param L The light source.
     * @param intersection Info about the first hit.
     * @return The color that represents the diffuse component.
     */
    public Rgb getDiffuseComponent(LightSource L, Intersection intersection){

        // Get the reflective coefficients.
        Rgb coefficients = intersection.getObject().getMaterial().diffuse_coefficients();

        // Get the normal vector at the intersection.
        Vector normal = intersection.getNormal();
        // Multiply with transformation matrix.
        normal = intersection.getObject().getATMatrix().times(normal);
        // Normalize this vector.
        normal.normalize();

        // Invert the normal if located on  other side of object.
        Ray hitRay = intersection.getHitRay();
        if( hitRay.dir.dot(normal) > 0){
            normal = new Vector( -normal.getX(), -normal.getY(), -normal.getZ() );
        }

        // Vector from hit point to source.
        Vector s = L.getLocation().minus( intersection.getLocation() );
        // Normalize this vector.
        s.normalize();

        // The lambert term.
        double lambert = s.dot( normal ) / Math.abs(normal.getMagnitude() * s.getMagnitude());

        // Hit point is turned towards the light.
        if ( lambert > 0.0 ){
            // Diffuse component.
            return new Rgb((float) lambert * coefficients.r() * L.color.r(),
                    (float) lambert * coefficients.g() * L.color.g(),
                    (float) lambert * coefficients.b() * L.color.b());
        }
        return background;

    }

    /**
     * Compute the specular component.
     * @param L The light source.
     * @param intersection Info about the first hit.
     * @return The color that represents the specular component.
     */
    public Rgb getSpecularComponent(LightSource L, Intersection intersection){

        // Get the reflective coefficients.
        Rgb coefficients = intersection.getObject().getMaterial().specular_coefficients();
        double exponent = intersection.getObject().getMaterial().getExponent();

        // Get the normal vector at the intersection.
        Vector normal = intersection.getNormal();
        // Multiply with transformation matrix.
        normal = intersection.getObject().getATMatrix().times(normal);
        // Normalize this vector.
        normal.normalize();

        // Invert the normal if located on  other side of object.
        Ray hitRay = intersection.getHitRay();
        if( hitRay.dir.dot(normal) > 0){
            normal = new Vector( -normal.getX(), -normal.getY(), -normal.getZ() );
        }

        // Negative of the ray's direction. Points to the viewer.
        Vector v = new Vector(
                -hitRay.dir.getX(),
                -hitRay.dir.getY(),
                -hitRay.dir.getZ());

        // Vector from hit point to source.
        Vector s = L.getLocation().minus( intersection.getLocation() );
        // Normalize this vector.
        s.normalize();

        // The halfway vector.
        Vector h = v.plus( s );
        // Normalize this vector.
        h.normalize();

        // Dot product between halfway vector and normal.
        double product = h.dot( normal );
        if(product <= 0)  // No specular distribution.
            return background;
        double phong = Math.pow( product, exponent );
        return new Rgb( (float) phong * coefficients.r() * L.color.r(),
                (float) phong * coefficients.g() * L.color.g(),
                (float) phong * coefficients.b() * L.color.b());

    }

    /**
     * Get the reflected light component.
     * @param intersection Info about the first hit.
     * @return The color that represents the reflected light component.
     */
    public Rgb getReflectedLight(Intersection intersection){

        // Get the normal vector at the hit point.
        Vector normal = intersection.getNormal();
        normal = intersection.getObject().getInverseAT().transpose().times( normal );
        normal.normalize();

        if (normal.dot( intersection.getHitRay().dir ) < 0)
            normal = new Vector(-normal.getX(), -normal.getY(), -normal.getZ());

        // Dot product between ray and normal.
        double factor = intersection.getHitRay().dir.dot(normal);

        // Get reflection direction.
        Vector dir = intersection.getHitRay().dir.minus(
                new Vector(2*factor*normal.getX(), 2*factor*normal.getY(), 2*factor*normal.getZ()));

        // Build reflected ray.
        Ray reflected = new Ray(
                intersection.getLocation(),
                dir
        );

        // Go up a level.
        reflected.recurseLevel = intersection.getHitRay().recurseLevel + 1;

        // Reflected component.
        return this.rayTrace(reflected);

    }

    /**
     * Get the refracted light component.
     * @param intersection Info about the first hit.
     * @return The color that represents the refracted light component.
     */
    public Rgb getTransmittedLight(Intersection intersection){

        // Get the normal vector at the hit point.
        Vector normal = intersection.getNormal();
        normal = intersection.getObject().getInverseAT().transpose().times( normal );
        normal.normalize();

        if (normal.dot( intersection.getHitRay().dir ) < 0)
            normal = new Vector(-normal.getX(), -normal.getY(), -normal.getZ());

        // Direction of hit ray.
        Vector dir_hit = intersection.getHitRay().dir;

        // Dot product between ray and normal.
        double product = normal.dot(dir_hit);

        // Index of refraction.
        double index = intersection.getObject().material.getRefraction_index();

        // cos(02)
        double cos = 1 - ((Math.pow(index, 2)) * (1 - Math.pow(product, 2)));

        // Refraction.
        if (cos > ANGLE_THRESHOLD) {

            // Square root of cos.
            cos = Math.sqrt( cos );

            // Calculate factor for determining transmitted direction.
            double factor = (index * product) - cos;

            // Calculate individual vectors.
            Vector vector1 = new Vector(index * dir_hit.getX(), index * dir_hit.getY(), index * dir_hit.getZ());
            Vector vector2 = new Vector(-factor * normal.getX(), -factor * normal.getY(), -factor * normal.getZ());

            // Get transmitted direction.
            Vector dir = vector1.plus( vector2 );

            // Build refracted ray.
            Ray refracted = new Ray(
                    intersection.getLocation(),
                    dir
            );

            // Go up a level.
            refracted.recurseLevel = intersection.getHitRay().recurseLevel + 1;

            // Refracted component.
            return this.rayTrace( refracted );
        }
        return BLACK;
    }
}