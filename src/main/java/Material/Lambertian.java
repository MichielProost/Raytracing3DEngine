package Material;

import Graphics.Rgb;

/**
 * Class that represents a lambertian material.
 */
public class Lambertian extends Material {

    /**
     * Create a lambertian material.
     */
    public Lambertian(){
        // Color.
        setColor(0.0f, 0.0f, 0.0f);

        // Weights.
        set_weights(0.8f, 0.2f, 0.0f);

        // Ambient component.
        set_ambient(0.3f, 0.3f, 0.3f);

        // Diffuse component.
        set_diffuse(0.5f, 0.5f, 0.5f);

        // Specular component.
        set_specular(0.2f, 0.2f, 0.2f,2.0);

        // Index of refraction.
        set_refraction_index(1.0);
    }

    /**
     * Create a lambertian material with a specified color.
     * @param color The required color.
     */
    public Lambertian(Rgb color){
        // Color.
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.8f, 0.2f, 0.0f);

        // Ambient component.
        set_ambient(0.3f, 0.3f, 0.3f);

        // Diffuse component.
        set_diffuse(0.5f, 0.5f, 0.5f);

        // Specular component.
        set_specular(0.2f, 0.2f, 0.2f,2.0);

        // Index of refraction.
        set_refraction_index(1.0);
    }

}
