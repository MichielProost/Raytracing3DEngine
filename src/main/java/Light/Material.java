package Light;

public class Material {

    // Diffuse reflection coefficients.
    private float rho_dr;
    private float rho_dg;
    private float rho_db;

    /**
     * Set the reflection coefficients for a gold material.
     * @return A gold material.
     */
    public Material Gold(){
        rho_dr = 0.75164f;
        rho_dg = 0.60648f;
        rho_db = 0.22648f;
        return this;
    }

    /**
     * Return the diffuse coefficients of this material.
     * @return The diffuse coefficients of this material.
     */
    public float[] diffuse_coefficients(){
        float[] coefficients = {rho_dr, rho_dg, rho_db};
        return coefficients;
    }

}
