package Light;

public class Material {

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

    /**
     * Set the reflection coefficients for a gold material.
     * @return A gold material.
     */
    public Material Gold(){
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
