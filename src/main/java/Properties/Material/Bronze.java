package Properties.Material;

import Graphics.Rgb;
import static Utils.Constants.BLACK;

/**
 * Class that represents a bronze material.
 */
public class Bronze extends Material{


    /**
     * Create a bronze material.
     */
    public Bronze(){
        // Color.
        setColor( BLACK );

        // Weights.
        set_weights(0.85f, 0.10f, 0.05f);

        // Ambient component.
        set_ambient(0.2125f, 0.1275f, 0.054f);

        // Diffuse component.
        set_diffuse(0.714f, 0.4284f, 0.18144f);

        // Specular component.
        set_specular(0.393548f, 0.271906f, 0.166271f, 25.6);

        // Index of refraction.
        set_refraction_index(0.18104);
    }

    /**
     * Create a bronze material with a specified color.
     * @param color The required color.
     */
    public Bronze(Rgb color){
        // Color.
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.85f, 0.10f, 0.05f);

        // Ambient component.
        set_ambient(0.2125f, 0.1275f, 0.054f);

        // Diffuse component.
        set_diffuse(0.714f, 0.4284f, 0.18144f);

        // Specular component.
        set_specular(0.393548f, 0.271906f, 0.166271f, 25.6);

        // Index of refraction.
        set_refraction_index(0.18104);
    }
}
