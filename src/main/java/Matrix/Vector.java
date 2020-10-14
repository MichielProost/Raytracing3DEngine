package Matrix;

/**
 * The matrix representation of a vector.
 * [x-projection, y-projection, z-projection, 1]^T
 */
public class Vector extends Matrix {

    private double x;   // Projection of this vector along the x-axis.
    private double y;   // Projection of this vector along the y-axis.
    private double z;   // Projection of this vector along the z-axis.

    /**
     * Create a matrix representation of a 3D vector.
     * @param x Projection of this vector along the x-axis.
     * @param y Projection of this vector along the y-axis.
     * @param z Projection of this vector along the z-axis.
     */
    public Vector(double x, double y, double z) {
        super(4, 1);            // Create a 4-by-1 matrix.
        this.x = x;
        this.y = y;
        this.z = z;
        put(0, 0, x).put(1, 0, y).put(2, 0, z);
    }
}
