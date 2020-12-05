package Material;

import Graphics.Rgb;

/**
 * Class that represents a mirror material.
 */
public class Mirror extends Material {

    /**
     * Create a mirror material.
     */
    public Mirror(){
        // Color.
        setColor(0.0f, 0.0f, 0.0f);
        // Weights.
        set_weights(0.05f, 0.95f, 0.0f);
        // Ambient component.
        set_ambient(0.35f, 0.35f, 0.35f);
        // Diffuse component.
        set_diffuse(0.1f, 0.1f, 0.1f);
        // Specular component.
        set_specular(0.6f, 0.6f, 0.6f, 128.0);
        // Index of refraction.
        set_refraction_index(1.693);
    }

    /**
     * Create a mirror material with a specified color.
     * @param color The required color.
     */
    public Mirror(Rgb color){
        // Color.
        setColor(0.0f, 0.0f, 0.0f);
        // Weights.
        set_weights(0.1f, 0.8f, 0.1f);
        // Ambient component.
        set_ambient(0.7f, 0.7f, 0.7f);
        // Diffuse component.
        set_diffuse(0.3f, 0.3f, 0.3f);
        // Specular component.
        set_specular(0.2f, 0.2f, 0.2f, 50.0);
        // Index of refraction.
        set_refraction_index(1.5);
    }
}
