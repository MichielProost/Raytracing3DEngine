package World;

import Graphics.Rgb;
import RayTracing.FirstHit;
import RayTracing.Hit;
import RayTracing.Ray;
import Matrix.Point;
import Matrix.Vector;
import Objects.Shape;
import java.util.ArrayList;
import java.util.List;

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

    // Default background is black.
    public Rgb background = new Rgb(0.0f, 0.0f, 0.0f);

    /**
     * Create a new scene.
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

        // Get info about the first hit if there is one.
        FirstHit firstHit = new FirstHit(ray, objects);

        // No intersections.
        if(!firstHit.exists()) {
            return background;
        }

        // Get the weights of the appropriate material.
        float[] weights = firstHit.hitObject.material.get_weights();

        // The color of the hit object.
        Rgb color = firstHit.hitObject.material.getColor();

        // Get ambient component.
        Rgb light = getAmbientComponent( firstHit );

        // Loop over every light source (for shading purposes).
        for (LightSource L: sources){

             // Check for shadow.
            if ( isInShadow( getShadowRay( L, firstHit ) ) ){
                continue;
            }

            // Get and add diffuse component.
            light = light.add( getDiffuseComponent( L, firstHit ) );

            // Get and add specular component.
            light = light.add( getSpecularComponent( L, firstHit ) );

        }

        // Add light component to final color.
        color = color.add( light.multiply( weights[0]) );

        // Check recursion level.
        if (ray.recurseLevel == maxRecursionLevel){
            return color;
        }

        // Add reflected light if object is shiny enough.
        if (firstHit.hitObject.material.isShinyEnough()){

            Rgb reflected = getReflectedLight( firstHit );
            color = color.add( reflected.multiply( weights[1]) );

        }

        // Add refracted light if the object is transparent enough.
        if (firstHit.hitObject.material.isTransparentEnough()){

            Rgb refracted = getTransmittedLight( firstHit );
            color = color.add( refracted.multiply( weights[2] ) );

        }

        return color;
    }

    /**
     * Returns true if in shadow. Returns false otherwise.
     * @param ray The sent out ray.
     * @return True if in shadow. Returns false otherwise.
     */
    public boolean isInShadow(Ray ray){

        // All intersections of ray with objects in the scene.
        for (Shape object : objects){
            // Check for collisions.
            Hit closestHit = object.getClosestHit( ray );
            if (closestHit != null && closestHit.time >= 0){
                return true;
            }
        }
        return false;

    }

    /**
     * Calculates a shadow feeler ray.
     * @param L The light source.
     * @param firstHit Info about the first hit.
     * @return The shadow feeler ray.
     */
    public Ray getShadowRay(LightSource L, FirstHit firstHit){

        // Create shadow feeler ray.
        Ray feeler = new Ray();

        // The direction of the hit ray.
        Vector hitRayDir = firstHit.hitRay.dir;

        // Calculate the starting point of the shadow feeler ray.
        double epsilon = 0.01;  // A small positive number.
        Point start = firstHit.hitPoint.minus(
                new Vector(epsilon * hitRayDir.getX(),
                        epsilon * hitRayDir.getY(),
                        epsilon * hitRayDir.getZ())
        );

        // Set the starting point of the shadow feeler ray.
        feeler.setStart(start);
        // Set the direction of the shadow feeler ray.
        feeler.setDir(L.location.minus(firstHit.hitPoint));

        return feeler;

    }

    /**
     * Compute the ambient component.
     * @param firstHit Info about the first hit.
     * @return The color that represents the ambient component.
     */
    public Rgb getAmbientComponent(FirstHit firstHit){

        // Get the reflective coefficients.
        return firstHit.hitObject.material.ambient_coefficients();

    }

    /**
     * Compute the diffuse component.
     * @param L The light source.
     * @param firstHit Info about the first hit.
     * @return The color that represents the diffuse component.
     */
    public Rgb getDiffuseComponent(LightSource L, FirstHit firstHit){

        // Get the reflective coefficients.
        Rgb coefficients = firstHit.hitObject.material.diffuse_coefficients();

        // Get the normal vector at the hit point.
        Vector normal = firstHit.hitNormal;
        normal.normalize();

        // Vector from hit point to source.
        Vector s = L.location.minus(firstHit.hitPoint);
        // Normalize this vector.
        s.normalize();

        // The lambert term.
        double lambert = s.dot(normal);
        // Hit point is turned towards the light.
        if (lambert > 0.0){
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
     * @param firstHit Info about the first hit.
     * @return The color that represents the specular component.
     */
    public Rgb getSpecularComponent(LightSource L, FirstHit firstHit){

        // Get the reflective coefficients.
        Rgb coefficients = firstHit.hitObject.material.specular_coefficients();
        double exponent = firstHit.hitObject.material.getExponent();

        // Get the normal vector at the hit point.
        Vector normal = firstHit.hitNormal;
        normal.normalize();

        // Negative of the ray's direction. Points to the viewer.
        Vector v = new Vector(-firstHit.hitRay.dir.getX(), -firstHit.hitRay.dir.getY(), -firstHit.hitRay.dir.getZ());

        // Vector from hit point to source.
        Vector s = L.location.minus(firstHit.hitPoint);
        // Normalize this vector.
        s.normalize();

        // The halfway vector.
        Vector h = v.plus(s);
        // Normalize this vector.
        h.normalize();

        // Part of phong term.
        double part = h.dot(normal);
        if(part <= 0)  // No specular distribution.
            return background;
        double phong = Math.pow(part, exponent);
        return new Rgb( (float) phong * coefficients.r() * L.color.r(),
                (float) phong * coefficients.g() * L.color.g(),
                (float) phong * coefficients.b() * L.color.b());

    }

    /**
     * Get the reflected light component.
     * @param firstHit Info about the first hit.
     * @return The color that represents the reflected light component.
     */
    public Rgb getReflectedLight(FirstHit firstHit){

        // Get the normal vector at the hit point.
        Vector normal = firstHit.hitNormal;
        normal.normalize();

        // Dot product between ray and normal.
        double factor = firstHit.hitRay.dir.dot(normal);

        // Get reflection direction.
        Vector dir = firstHit.hitRay.dir.minus(
                new Vector(2*factor*normal.getX(), 2*factor*normal.getY(), 2*factor*normal.getZ()));

        // Build reflected ray.
        Ray reflected = new Ray(
                firstHit.hitPoint,
                dir
        );

        // Go up a level.
        reflected.recurseLevel = firstHit.hitRay.recurseLevel + 1;

        // Reflected component.
        return this.rayTrace(reflected);

    }

    /**
     * Get the refracted light component.
     * @param firstHit Info about the first hit.
     * @return The color that represents the refracted light component.
     */
    public Rgb getTransmittedLight(FirstHit firstHit){

        // Get the normal vector at the hit point.
        Vector normal = firstHit.hitNormal;
        normal.normalize();
        Vector minus_normal = new Vector(-normal.getX(), -normal.getY(), -normal.getZ());

        // Direction of hit ray.
        Vector dir_hit = firstHit.hitRay.dir;
        dir_hit.normalize();

        // Dot product between ray and normal.
        double product = minus_normal.dot(dir_hit);

        // Index of refraction.
        double index = firstHit.hitObject.material.getRefraction_index();
        // double index = 1;

        // cos(02)
        double cos = Math.sqrt(1 - (Math.pow(index, 2)) * (1 - Math.pow(product, 2)));

        // Calculate factor for determining transmitted direction.
        double factor = (index * product) - cos;

        // Get transmitted direction.
        Vector vector1 = new Vector(index * dir_hit.getX(), index * dir_hit.getY(), index * dir_hit.getZ());
        Vector vector2 = new Vector(factor * normal.getX(), factor * normal.getY(), factor * normal.getZ());
        Vector dir = vector1.plus(vector2);

        // Build refracted ray.
        Ray refracted = new Ray(
                firstHit.hitPoint,
                dir
        );

        // Go up a level.
        refracted.recurseLevel = firstHit.hitRay.recurseLevel + 1;

        // Refracted component.
        return this.rayTrace(refracted);

    }

}