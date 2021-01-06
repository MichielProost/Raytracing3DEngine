package Material;

import Graphics.Rgb;

/**
 * Class that represents a polished silver material.
 */
public class PolishedSilver extends Material {

    /**
     * Create a polished silver material.
     */
    public PolishedSilver(){
        // Color.
        setColor(0.0f, 0.0f, 0.0f);

        // Weights.
        set_weights(0.75f, 0.2f, 0.05f);

        // Ambient component.
        set_ambient(0.23125f, 0.23125f, 0.23125f);

        // Diffuse component.
        set_diffuse(0.2755f, 0.2755f, 0.2755f);

        // Specular component.
        set_specular(0.773911f, 0.773911f, 0.773911f, 89.6);

        // Index of refraction.
        set_refraction_index(0.13511);
    }

    /**
     * Create a polished silver material with a specified color.
     * @param color The required color.
     */
    public PolishedSilver(Rgb color){
        // Color.
        setColor(color.r(), color.g(), color.b());

        // Weights.
        set_weights(0.75f, 0.2f, 0.05f);

        // Ambient component.
        set_ambient(0.23125f, 0.23125f, 0.23125f);

        // Diffuse component.
        set_diffuse(0.2755f, 0.2755f, 0.2755f);

        // Specular component.
        set_specular(0.773911f, 0.773911f, 0.773911f, 89.6);

        // Index of refraction.
        set_refraction_index(0.13511);
    }

}
