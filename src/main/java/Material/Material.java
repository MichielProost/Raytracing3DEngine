package Material;

import Graphics.Rgb;

/**
 * Every shape is of a specific material with its properties implemented in this class.
 */
public class Material {

    // Constants.
    final float REQUIRED_SHININESS = 0.05f;
    final float REQUIRED_TRANSPARENCY = 1.0f;

    // The color of the material (default white).
    private Rgb color = new Rgb(1.0f, 1.0f, 1.0f);

    // Default weights.
    private float [] weights = new float[]{0.8f, 0.2f, 0.0f};

    // Ambient reflection coefficients.
    private Rgb rho_ambient;

    // Diffuse reflection coefficients.
    private Rgb rho_diffuse;

    // Specular reflection coefficients.
    private Rgb rho_specular;

    // The specular exponent.
    private double exponent;

    // Index of refraction.
    private double refraction_index;

    // Set of materials.
    public enum Materials{
        lambertian,
        black_plastic,
        gold,
        polished_silver,
        mirror
    }

    /**
     * Return the requested material.
     * @param type A material from the set of materials.
     * @return The requested material. Returns null if the material cannot be found.
     */
    public Material getMaterial(Materials type){
        switch (type){
            case lambertian:
                return new Lambertian();
            case black_plastic:
                return new BlackPlastic();
            case gold:
                return new Gold();
            case polished_silver:
                return new PolishedSilver();
            case mirror:
                return new Mirror();
        }
        return null;
    }

    /**
     * Return the requested material with a specified color.
     * @param type A material from the set of materials.
     * @param color The required color.
     * @return The requested material. Returns null if the material cannot be found.
     */
    public Material getMaterial(Materials type, Rgb color){
        switch (type){
            case lambertian:
                return new Lambertian( color );
            case black_plastic:
                return new BlackPlastic( color );
            case gold:
                return new Gold( color );
            case polished_silver:
                return new PolishedSilver( color );
            case mirror:
                return new Mirror( color );
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
     * @param r The red component of the required color.
     * @param g The green component of the required color.
     * @param b The blue component of the required color.
     */
    public void setColor(float r, float g, float b){
        this.color = new Rgb(r, g, b);
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
     * Set the ambient reflection coefficients.
     * @param r The red component.
     * @param g The green component.
     * @param b The blue component.
     */
    public void set_ambient(float r, float g, float b){
        rho_ambient = new Rgb(r, g, b);
    }

    /**
     * Set the diffuse reflection coefficients.
     * @param r The red component.
     * @param g The green component.
     * @param b The blue component.
     */
    public void set_diffuse(float r, float g, float b){
        rho_diffuse = new Rgb(r, g, b);
    }

    /**
     * Set the specular reflection coefficients and the exponent.
     * @param r The red component.
     * @param g The green component.
     * @param b The blue component.
     * @param exponent The specular exponent.
     */
    public void set_specular(float r, float g, float b, double exponent){
        rho_specular = new Rgb(r, g, b);
        this.exponent = exponent;
    }

    /**
     * Set the refraction index.
     * @param index The index of refraction.
     */
    public void set_refraction_index(double index){
        refraction_index = index;
    }

    /**
     * Is the material shiny enough?
     * @return True if the material is shiny enough. False otherwise.
     */
    public boolean isShinyEnough(){
        return weights[1] >= REQUIRED_SHININESS;
    }

    /**
     * Is the material transparent enough?
     * @return True if the material is transparent enough. False otherwise.
     */
    public boolean isTransparentEnough(){
        return weights[2] >= REQUIRED_TRANSPARENCY;
    }

}
