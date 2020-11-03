package Light;

public class Material {

    // Weights.
    private float light_weight;
    private float reflection_weight;

    // Ambient reflection coefficients.
    private float rho_ar;
    private float rho_ag;
    private float rho_ab;

    // Diffuse reflection coefficients.
    private float rho_dr;
    private float rho_dg;
    private float rho_db;

    // Specular reflection coefficients.
    private float rho_sr;
    private float rho_sg;
    private float rho_sb;
    private double exponent;

    // The kind of material.
    public enum MaterialType{
        black_plastic,
        gold,
        polished_silver
    }

    /**
     * Return a material based on the given type.
     * @param type The type of required material.
     * @return The appropriate material.
     */
    public Material getMaterial(MaterialType type){
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
     * Set the reflection coefficients for a black plastic.
     * @return A black plastic material.
     */
    public Material BlackPlastic(){

        // Weights.
        light_weight = 0.98f;
        reflection_weight = 0.02f;

        // Ambient component.
        rho_ar = 0.0f;
        rho_ag = 0.0f;
        rho_ab = 0.0f;

        // Diffuse component.
        rho_dr = 0.01f;
        rho_dg = 0.01f;
        rho_db = 0.01f;

        // Specular component.
        rho_sr = 0.50f;
        rho_sg = 0.50f;
        rho_sb = 0.50f;
        exponent = 32;
        return this;
    }

    /**
     * Set the reflection coefficients for a gold material.
     * @return A gold material.
     */
    public Material Gold(){

        // Weights.
        light_weight = 0.9f;
        reflection_weight = 0.1f;

        // Ambient component.
        rho_ar = 0.24725f;
        rho_ag = 0.1955f;
        rho_ab = 0.0745f;

        // Diffuse component.
        rho_dr = 0.75164f;
        rho_dg = 0.60648f;
        rho_db = 0.22648f;

        // Specular component.
        rho_sr = 0.628281f;
        rho_sg = 0.555802f;
        rho_sb = 0.366065f;
        exponent = 51.2;
        return this;
    }

    /**
     * Set the reflection coefficients for a gold material.
     * @return A gold material.
     */
    public Material PolishedSilver(){

        // Weights.
        light_weight = 0.8f;
        reflection_weight = 0.2f;

        // Ambient component.
        rho_ar = 0.23125f;
        rho_ag = 0.23125f;
        rho_ab = 0.23125f;

        // Diffuse component.
        rho_dr = 0.2755f;
        rho_dg = 0.2755f;
        rho_db = 0.2755f;

        // Specular component.
        rho_sr = 0.773911f;
        rho_sg = 0.773911f;
        rho_sb = 0.773911f;
        exponent = 89.6;
        return this;
    }

    /**
     * Return the weights of this material.
     * @return The weights of this material.
     */
    public float[] get_weights() {
        return new float[]{light_weight, reflection_weight};
    }

    /**
     * Set the weights of this material.
     * @param light_weight The weight for the light component.
     * @param reflection_weight The weight for the reflection component.
     */
    public void set_weights(float light_weight, float reflection_weight){
        this.light_weight = light_weight;
        this.reflection_weight = reflection_weight;
    }

    /**
     * Return the ambient coefficients of this material.
     * @return The ambient coefficients of this material.
     */
    public float[] ambient_coefficients(){
        return new float[]{rho_ar, rho_ag, rho_ab};
    }

    /**
     * Return the diffuse coefficients of this material.
     * @return The diffuse coefficients of this material.
     */
    public float[] diffuse_coefficients(){
        return new float[]{rho_dr, rho_dg, rho_db};
    }

    /**
     * Return the specular coefficients of this material.
     * @return The specular coefficients of this material.
     */
    public float[] specular_coefficients(){
        return new float[]{rho_sr, rho_sg, rho_sb};
    }

    /**
     * Get the specular exponent of this material.
     * @return The specular exponent of this material.
     */
    public double getExponent(){
        return exponent;
    }

}
