package RayTracing;

import Graphics.Rgb;
import Light.LightSource;
import Matrix.Point;
import Matrix.Vector;
import Objects.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 * Describes our 3D world.
 */
public class Scene {

    public List<Shape> objects;         // Objects in the scene.
    public List<LightSource> sources;   // Light sources in the scene.
    public int maxRecursionLevel;       // Max recursion level.
    public Rgb background = new Rgb(1.0f, 1.0f, 1.0f);  // Default background is black.

    /**
     * Default constructor.
     */
    public Scene(int maxRecursionLevel){
        objects = new ArrayList<>();
        sources = new ArrayList<>();
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
        HitInfo info = new HitInfo().getFirstHit(ray, objects);

        // No intersections.
        if(info == null) {
            return background;
        }

        // Get the weights of the appropriate material.
        float[] weights = info.hitObject.material.get_weights();

        // The color of the hit object.
        Rgb color = info.hitObject.getColor();

        // Get ambient component.
        Rgb light = getAmbientComponent( info );

        // Loop over every light source (for shading purposes).
        for (LightSource L: sources){

             // Check for shadow.
            if ( isInShadow( getShadowRay( L, info ) ) ){
                continue;
            }

            // Get and add diffuse component.
            light = light.add( getDiffuseComponent( L, info ) );

            // Get and add specular component.
            light = light.add( getSpecularComponent( L, info ) );

        }

        // Add light component to final color.
        color = color.add( light.multiply( weights[0]) );

        // Check recursion level.
        if (ray.recurseLevel == maxRecursionLevel){
            return color;
        }

        // Add reflected light if object is shiny enough.
        if (info.hitObject.isShinyEnough()){

            Rgb reflected = getReflectedLight( info );
            color = color.add( reflected.multiply( weights[1]) );

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
            Double t = object.getCollidingT(ray);
            if (t != null && t >= 0){
                return true;
            }
        }
        return false;

    }

    /**
     * Calculates a shadow feeler ray.
     * @param L The light source.
     * @param info Info about the first hit.
     * @return The shadow feeler ray.
     */
    public Ray getShadowRay(LightSource L, HitInfo info){

        // Create shadow feeler ray.
        Ray feeler = new Ray();

        // The direction of the hit ray.
        Vector hitRayDir = info.hitRay.dir;

        // Calculate the starting point of the shadow feeler ray.
        double epsilon = 0.01;  // A small positive number.
        Point start = info.hitPoint.minus(
                new Vector(epsilon * hitRayDir.getX(),
                        epsilon * hitRayDir.getY(),
                        epsilon * hitRayDir.getZ())
        );

        // Set the starting point of the shadow feeler ray.
        feeler.setStart(start);
        // Set the direction of the shadow feeler ray.
        feeler.setDir(L.location.minus(info.hitPoint));

        return feeler;

    }

    /**
     * Compute the ambient component.
     * @param info Info about the first hit.
     * @return The color that represents the ambient component.
     */
    public Rgb getAmbientComponent(HitInfo info){

        // Get the reflective coefficients.
        float[] coefficients = info.hitObject.material.ambient_coefficients();

        // Ambient component.
        return new Rgb(coefficients[0], coefficients[1], coefficients[2]);

    }

    /**
     * Compute the diffuse component.
     * @param L The light source.
     * @param info Info about the first hit.
     * @return The color that represents the diffuse component.
     */
    public Rgb getDiffuseComponent(LightSource L, HitInfo info){

        // Get the reflective coefficients.
        float[] coefficients = info.hitObject.material.diffuse_coefficients();

        // Get the normal vector at the hit point.
        Vector normal = info.hitNormal;
        normal.normalize();

        // Vector from hit point to source.
        Vector s = L.location.minus(info.hitPoint);
        // Normalize this vector.
        s.normalize();

        // The lambert term.
        double lambert = s.dot(normal);
        // Hit point is turned towards the light.
        if (lambert > 0.0){
            // Diffuse component.
            return new Rgb((float) lambert * coefficients[0] * L.color.r(),
                    (float) lambert * coefficients[1] * L.color.g(),
                    (float) lambert * coefficients[2] * L.color.b());
        }
        return background;

    }

    /**
     * Compute the specular component.
     * @param L The light source.
     * @param info Info about the first hit.
     * @return The color that represents the specular component.
     */
    public Rgb getSpecularComponent(LightSource L, HitInfo info){

        // Get the reflective coefficients.
        float[] coefficients = info.hitObject.material.specular_coefficients();
        double exponent = info.hitObject.material.getExponent();

        // Get the normal vector at the hit point.
        Vector normal = info.hitNormal;
        normal.normalize();

        // Negative of the ray's direction. Points to the viewer.
        Vector v = new Vector(-info.hitRay.dir.getX(), -info.hitRay.dir.getY(), -info.hitRay.dir.getZ());

        // Vector from hit point to source.
        Vector s = L.location.minus(info.hitPoint);
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
        return new Rgb( (float) phong * coefficients[0] * L.color.r(),
                (float) phong * coefficients[1] * L.color.g(),
                (float) phong * coefficients[2] * L.color.b());

    }

    /**
     * Get the reflected light component.
     * @param info Info about the first hit.
     * @return The color that represents the reflected light component.
     */
    public Rgb getReflectedLight(HitInfo info){

        // Get the normal vector at the hit point.
        Vector normal = info.hitNormal;
        normal.normalize();

        // Dot product between ray and normal.
        double factor = info.hitRay.dir.dot(normal);

        // Get reflection direction.
        Vector dir = info.hitRay.dir.minus(
                new Vector(2*factor*normal.getX(), 2*factor*normal.getY(), 2*factor*normal.getZ()));

        // Build reflected ray.
        Ray reflected = new Ray().setStart(info.hitPoint);
        reflected.setDir(dir);

        // Go up a level.
        reflected.recurseLevel = info.hitRay.recurseLevel + 1;

        // Reflected component.
        Rgb color = this.rayTrace(reflected);
        return new Rgb(info.hitObject.shininess * color.r(),
                info.hitObject.shininess * color.g(),
                info.hitObject.shininess * color.b());

    }

}
