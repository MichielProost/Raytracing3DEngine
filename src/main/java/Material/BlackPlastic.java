package Material;

import Graphics.Rgb;

/**
 * Class that represents a black plastic material.
 */
public class BlackPlastic extends Material {

    /**
     * Create a black plastic material.
     */
    public BlackPlastic(){
        // Color.
        setColor(0.0f, 0.0f, 0.0f);
        // Weights.
        set_weights(0.98f, 0.02f, 0.0f);
        // Ambient component.
        set_ambient(0.0f, 0.0f, 0.0f);
        // Diffuse component.
        set_diffuse(0.01f, 0.01f, 0.01f);
        // Specular component.
        set_specular(0.50f, 0.50f, 0.50f,32);
        // Index of refraction.
        set_refraction_index(1.2);
    }

    /**
     * Create a black plastic material with a specified color.
     * @param color The required color.
     */
    public BlackPlastic(Rgb color){
        // Color.
        setColor(color.r(), color.g(), color.b());
        // Weights.
        set_weights(0.90f, 0.06f, 0.04f);
        // Ambient component.
        set_ambient(0.0f, 0.0f, 0.0f);
        // Diffuse component.
        set_diffuse(0.01f, 0.01f, 0.01f);
        // Specular component.
        set_specular(0.50f, 0.50f, 0.50f,32);
        // Index of refraction.
        set_refraction_index(1.2);
    }

}
