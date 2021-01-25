package Properties.Material;

import Graphics.Rgb;
import static Utils.Constants.BLACK;

/**
 * Class that implements a wood material.
 */
public class Wood extends Material {

    /**
     * Create a wood material.
     */
    public Wood(){
        // Color.
        setColor( BLACK );

        // Weights.
        set_weights(0.97f, 0.02f, 0.01f);

        // Ambient component.
        set_ambient(0.2f, 0.2f, 0.2f);

        // Diffuse component.
        set_diffuse(0.95f, 0.95f, 0.95f);

        // Specular component.
        set_specular(0.50f, 0.50f, 0.50f, 32.0);

        // Index of refraction.
        set_refraction_index(1.56);
    }

    /**
     * Create a wood material with a specified color.
     * @param color The required color.
     */
    public Wood(Rgb color){
        // Color.
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.97f, 0.02f, 0.01f);

        // Ambient component.
        set_ambient(0.0f, 0.0f, 0.0f);

        // Diffuse component.
        set_diffuse(0.2f, 0.2f, 0.2f);

        // Specular component.
        set_specular(0.50f, 0.50f, 0.50f, 32.0);

        // Index of refraction.
        set_refraction_index(1.56);
    }

}
