package Properties.Material;

import Graphics.Rgb;
import static Utils.Constants.BLACK;

/**
 * Class that represents a gold material.
 */
public class Gold extends Material {

    /**
     * Create a gold material.
     */
    public Gold(){
        // Color.
        setColor( BLACK );

        // Weights.
        set_weights(0.8f, 0.15f, 0.05f);

        // Ambient component.
        set_ambient(0.24725f, 0.1955f, 0.0745f);

        // Diffuse component.
        set_diffuse(0.75164f, 0.60648f, 0.22648f);

        // Specular component.
        set_specular(0.628281f, 0.555802f, 0.366065f, 51.2);

        // Index of refraction.
        set_refraction_index(0.18104);
    }

    /**
     * Create a gold material with a specified color.
     * @param color The required color.
     */
    public Gold(Rgb color){
        // Color.
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.8f, 0.15f, 0.05f);

        // Ambient component.
        set_ambient(0.24725f, 0.1955f, 0.0745f);

        // Diffuse component.
        set_diffuse(0.75164f, 0.60648f, 0.22648f);

        // Specular component.
        set_specular(0.628281f, 0.555802f, 0.366065f, 51.2);

        // Index of refraction.
        set_refraction_index(0.18104);
    }

}
