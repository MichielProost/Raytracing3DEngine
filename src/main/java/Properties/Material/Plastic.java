package Properties.Material;

import Graphics.Rgb;
import static Utils.Constants.BLACK;

/**
 * Class that implements a plastic material.
 */
public class Plastic extends Material {

    /**
     * Create a plastic silver material.
     */
    public Plastic(){
        // Color.
        setColor( BLACK );

        // Weights.
        set_weights(0.88f, 0.1f, 0.02f);

        // Ambient component.
        set_ambient(0.1f, 0.1f, 0.1f);

        // Diffuse component.
        set_diffuse(0.6f, 0.6f, 0.6f);

        // Specular component.
        set_specular(0.4f, 0.4f, 0.4f, 32);

        // Index of refraction.
        set_refraction_index(1.4);
    }

    /**
     * Create a plastic silver material with a specified color.
     * @param color The required color.
     */
    public Plastic(Rgb color){
        // Color.
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.88f, 0.1f, 0.02f);

        // Ambient component.
        set_ambient(0.1f, 0.1f, 0.1f);

        // Diffuse component.
        set_diffuse(0.6f, 0.6f, 0.6f);

        // Specular component.
        set_specular(0.4f, 0.4f, 0.4f, 32);

        // Index of refraction.
        set_refraction_index(1.4);
    }

}
