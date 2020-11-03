package RayTracing;

import Graphics.Rgb;
import Light.LightSource;
import Matrix.Vector;
import Objects.Shape;
import com.sun.javafx.scene.text.TextLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes our 3D world.
 */
public class Scene {

    public List<Shape> objects;         // Objects in the scene.
    public List<LightSource> sources;   // Light sources in the scene.
    public int maxRecursionLevel;       // Max recursion level.
    public Rgb background = new Rgb(0.0f, 0.0f, 0.0f);  // Default background is black.

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

        // The color of the hit object.
        Rgb color = info.hitObject.getColor();

        color = addAmbientComponent(color, info);

        // Loop over every light source (for shading purposes).
        for (LightSource L: sources){

            // Add diffuse component.
            color = addDiffuseComponent(color, L, info);

            // Add specular component.
            color = addSpecularComponent(color, L, info);

        }

        // Check recursion level.
        if (ray.recurseLevel == maxRecursionLevel){
            return color;
        }

        // Shininess of hit object.
        float shininess = info.hitObject.shininess;

        if (shininess > 0.1f){    // Add any reflected light.
            double dirDotm = info.hitRay.dir.dot(normal);
            // Get reflection direction.
            Vector reflection_dir = ray.dir.minus(
                    new Vector(2*dirDotm*normal.getX(), 2*dirDotm*normal.getY(), 2*dirDotm*normal.getZ()));
            // Build reflected ray.
            Ray reflected = new Ray().setStart(info.hitPoint);
            reflected.setDir(reflection_dir);
            // Go up a level.
            reflected.recurseLevel = ray.recurseLevel + 1;
            // Add reflected component.
            Rgb reflection_color = this.rayTrace(reflected);
            Rgb reflection_factor = new Rgb(shininess * reflection_color.r(),
                    shininess * reflection_color.g(),
                    shininess * reflection_color.b());
            color.add(reflection_factor);
        }

        return color;
    }

    /**
     * Compute and add the ambient component to the current color.
     * @param color The current color.
     * @param info Info about the first hit.
     * @return The new color that includes the ambient component.
     */
    public Rgb addAmbientComponent(Rgb color, HitInfo info){

        // Get the reflective coefficients.
        float[] coefficients = info.hitObject.material.ambient_coefficients();

        // Ambient component.
        Rgb ambient = new Rgb(coefficients[0], coefficients[1], coefficients[2]);
        return color.add(ambient);

    }

    /**
     * Compute and add the diffuse component to the current color.
     * @param color The current color.
     * @param L The light source.
     * @param info Info about the first hit.
     * @return The new color that includes a diffuse component.
     */
    public Rgb addDiffuseComponent(Rgb color, LightSource L, HitInfo info){

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
            Rgb diffuse = new Rgb((float) lambert * coefficients[0] * L.color.r(),
                    (float) lambert * coefficients[1] * L.color.g(),
                    (float) lambert * coefficients[2] * L.color.b());
            return color.add(diffuse);
        }
        return color;

    }

    /**
     * Compute and add the specular component to the current color.
     * @param color The current color.
     * @param L The light source.
     * @param info Info about the first hit.
     * @return The new color that includes a specular component.
     */
    public Rgb addSpecularComponent(Rgb color, LightSource L, HitInfo info){

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
            return color;
        double phong = Math.pow(part, exponent);
        Rgb specular = new Rgb( (float) phong * coefficients[0] * L.color.r(),
                (float) phong * coefficients[1] * L.color.g(),
                (float) phong * coefficients[2] * L.color.b());
        return color.add(specular);

    }

}
