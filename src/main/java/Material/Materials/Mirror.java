package Material.Materials;

import Graphics.Rgb;
import Material.Material;

import static Utils.Constants.BLACK;

/**
 * Class that represents a mirror material.
 */
public class Mirror extends Material {

    /**
     * Create a mirror material.
     */
    public Mirror(){
        // Color.
        setColor( BLACK );

        // Weights.
        set_weights(0.3f, 0.7f, 0.0f);

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
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.3f, 0.7f, 0.0f);

        // Ambient component.
        set_ambient(0.35f, 0.35f, 0.35f);

        // Diffuse component.
        set_diffuse(0.1f, 0.1f, 0.1f);

        // Specular component.
        set_specular(0.6f, 0.6f, 0.6f, 128.0);

        // Index of refraction.
        set_refraction_index(1.693);
    }
}
