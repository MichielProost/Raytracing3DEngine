package Material;

import Graphics.Rgb;

import java.util.Random;

/**
 * Class that represents a black plastic material.
 */
public class BlackPlastic extends Material {

    /**
     * Create a black plastic material.
     */
    public BlackPlastic(){
        // Random number generator.
        Random r = new Random();

        // Color.
        setColor(0.0f, 0.0f, 0.0f);

        // Weights.
        set_weights(0.95f, 0.03f, 0.02f);

        // Ambient component.
        set_ambient(0.0f, 0.0f, 0.0f);

        // Diffuse component.
        set_diffuse(0.01f, 0.01f, 0.01f);

        // Specular component.
        set_specular(0.50f, 0.50f, 0.50f,32);

        // Index of refraction.
        double rangeMin = 1.3;
        double rangeMax = 1.7;
        set_refraction_index( rangeMin + (rangeMax - rangeMin) * r.nextDouble() );
    }

    /**
     * Create a black plastic material with a specified color.
     * @param color The required color.
     */
    public BlackPlastic(Rgb color){
        // Random number generator.
        Random r = new Random();

        // Color.
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.95f, 0.03f, 0.02f);

        // Ambient component.
        set_ambient(0.0f, 0.0f, 0.0f);

        // Diffuse component.
        set_diffuse(0.01f, 0.01f, 0.01f);

        // Specular component.
        set_specular(0.50f, 0.50f, 0.50f,32);

        // Index of refraction.
        double rangeMin = 1.3;
        double rangeMax = 1.7;
        set_refraction_index( rangeMin + (rangeMax - rangeMin) * r.nextDouble() );
    }

}
