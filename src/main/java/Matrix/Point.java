package Matrix;

/**
 * The matrix representation of a point.
 * [x-value, y-value, z-value, 0]^T
 */
public class Point extends Matrix {

    private double x;   // The value on the x-axis.
    private double y;   // The value on the y-axis.
    private double z;   // The value on the z-axis.

    /**
     * Create a matrix representation of a 3D point.
     * @param x The value on the x-axis.
     * @param y The value on the y-axis.
     * @param z The value on the z-axis.
     */
    public Point(double x, double y, double z){
        super(4,1);             // Create a 4-by-1 matrix.
        this.x = x;
        this.y = y;
        this.z = z;
        put(0, 0, x).put(1, 0, y).put(2, 0, z);
    }
}
