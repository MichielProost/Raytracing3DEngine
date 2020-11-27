package Objects;

import Graphics.Rgb;

public class Material {

    // The color of the material (default white).
    private Rgb color = new Rgb(1.0f, 1.0f, 1.0f);

    // Default weights.
    private float [] weights = new float[]{0.7f, 0.3f, 0.0f};

    // Ambient reflection coefficients.
    private Rgb rho_ambient = new Rgb(0.0f, 0.0f, 0.0f);

    // Diffuse reflection coefficients.
    private Rgb rho_diffuse = new Rgb(0.0f, 0.0f, 0.0f);

    // Specular reflection coefficients.
    private Rgb rho_specular = new Rgb(0.0f, 0.0f, 0.0f);
    private double exponent = 1.0;

    // Index of refraction.
    private double refraction_index = 0.0;

    // Set of materials.
    public enum Materials{
        black_plastic,
        gold,
        polished_silver
    }

    /**
     * Return the requested material.
     * @param type A material from the set of materials.
     * @return The requested materials.
     */
    public Material getMaterial(Materials type){
        switch (type){
            case black_plastic:
                return new Material().BlackPlastic();
            case gold:
                return new Material().Gold();
            case polished_silver:
                return new Material().PolishedSilver();
        }
        return null;
    }

    /**
     * Get the color of this material.
     * @return The color of this material.
     */
    public Rgb getColor(){
        return color;
    }

    /**
     * Return the weights of this material.
     * @return The weights of this material.
     */
    public float[] get_weights() {
        return weights;
    }

    /**
     * Return the ambient coefficients of this material.
     * @return The ambient coefficients of this material.
     */
    public Rgb ambient_coefficients(){
        return rho_ambient;
    }

    /**
     * Return the diffuse coefficients of this material.
     * @return The diffuse coefficients of this material.
     */
    public Rgb diffuse_coefficients(){
        return rho_diffuse;
    }

    /**
     * Return the specular coefficients of this material.
     * @return The specular coefficients of this material.
     */
    public Rgb specular_coefficients(){
        return rho_specular;
    }

    /**
     * Get the specular exponent of this material.
     * @return The specular exponent of this material.
     */
    public double getExponent(){
        return exponent;
    }

    /**
     * Get the index of refraction of this material.
     * @return The refraction index of this material.
     */
    public double getRefraction_index(){
        return refraction_index;
    }

    /**
     * Set the color of this material.
     * @param color The color of this material.
     * @return This material.
     */
    public Material setColor(Rgb color){
        this.color = color;
        return this;
    }

    /**
     * Set the weights of this material.
     * @param light_weight The weight for the light component.
     * @param reflection_weight The weight for the reflection component.
     * @param refraction_weight The weight for the refraction component.
     */
    public void set_weights(float light_weight, float reflection_weight, float refraction_weight){
        this.weights[0] = light_weight;
        this.weights[1] = reflection_weight;
        this.weights[2] = refraction_weight;
    }

    /**
     * Set the properties for a black plastic.
     * @return A black plastic material.
     */
    public Material BlackPlastic(){

        // Color.
        color = new Rgb(0.0f, 0.0f, 0.0f);

        // Weights.
        set_weights(0.96f, 0.04f, 0.00f);

        // Ambient component.
        rho_ambient = new Rgb(0.0f, 0.0f, 0.0f);
        // Diffuse component.
        rho_diffuse = new Rgb(0.01f, 0.01f, 0.01f);
        // Specular component.
        rho_specular = new Rgb(0.50f, 0.50f, 0.50f);
        exponent = 32;

        // Index of refraction.
        refraction_index = 0.71428;
        return this;
    }

    /**
     * Set the properties for a gold material.
     * @return A gold material.
     */
    public Material Gold(){

        // Color.
        color = new Rgb(0.0f, 0.0f, 0.0f);

        // Weights.
        set_weights(0.6f, 0.4f, 0.0f);

        // Ambient component.
        rho_ambient = new Rgb(0.24725f, 0.1955f, 0.0745f);
        // Diffuse component.
        rho_diffuse = new Rgb(0.75164f, 0.60648f, 0.22648f);
        // Specular component.
        rho_specular = new Rgb(0.628281f, 0.555802f, 0.366065f);
        exponent = 51.2;

        // Index of refraction.
        refraction_index = 2.12766;
        return this;
    }

    /**
     * Set the reflection coefficients for a gold material.
     * @return A gold material.
     */
    public Material PolishedSilver(){

        // Color.
        color = new Rgb(0.0f, 0.0f, 0.0f);

        // Weights.
        set_weights(0.6f, 0.4f, 0.00f);

        // Ambient component.
        rho_ambient = new Rgb(0.23125f, 0.23125f, 0.23125f);
        // Diffuse component.
        rho_diffuse = new Rgb(0.2755f, 0.2755f, 0.2755f);
        // Specular component.
        rho_specular = new Rgb(0.773911f, 0.773911f, 0.773911f);
        exponent = 89.6;

        // Index of refraction.
        refraction_index = 0.74074;
        return this;
    }

    /**
     * Is the material shiny enough?
     * @return True if the material is shiny enough. False otherwise.
     */
    public boolean isShinyEnough(){
        return weights[1] >= 0.05f;
    }

    /**
     * Is the material transparent enough?
     * @return True if the material is transparent enough. False otherwise.
     */
    public boolean isTransparentEnough(){
        return weights[2] >= 0.05f;
    }

}
