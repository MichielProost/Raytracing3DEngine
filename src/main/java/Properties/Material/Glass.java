package Properties.Material;

import Graphics.Rgb;
import static Utils.Constants.BLACK;

/**
 * Class that represents a glass material.
 */
public class Glass extends Material {

    /**
     * Create a glass material.
     */
    public Glass(){
        // Color.
        setColor( BLACK );

        // Weights.
        set_weights(0.15f, 0.05f, 0.80f);

        // Ambient component.
        set_ambient(0.35f, 0.35f, 0.35f);

        // Diffuse component.
        set_diffuse(0.1f, 0.1f, 0.1f);

        // Specular component.
        set_specular(0.6f, 0.6f, 0.6f, 128.0);

        // Index of refraction.
        set_refraction_index(1.52);
    }

    /**
     * Create a glass material with a specified color.
     * @param color The required color.
     */
    public Glass(Rgb color){
        // Color.
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.15f, 0.05f, 0.80f);

        // Ambient component.
        set_ambient(0.35f, 0.35f, 0.35f);

        // Diffuse component.
        set_diffuse(0.1f, 0.1f, 0.1f);

        // Specular component.
        set_specular(0.6f, 0.6f, 0.6f, 128.0);

        // Index of refraction.
        set_refraction_index(1.52);
    }
}
