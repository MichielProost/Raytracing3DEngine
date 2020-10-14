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

    /**
     * Get the projection of this vector along the x-axis.
     * @return The projection of this vector along the x-axis.
     */
    public double getX(){
        return x;
    }

    /**
     * Get the projection of this vector along the y-axis.
     * @return The projection of this vector along the y-axis.
     */
    public double getY(){
        return y;
    }

    /**
     * Get the projection of this vector along the z-axis.
     * @return The projection of this vector along the z-axis.
     */
    public double getZ(){
        return z;
    }

    /**
     * Set the projection of this vector along the x-axis.
     * @param x The projection of this vector along the x-axis.
     */
    public void setX(double x){
        this.x = x;
        put(0,0, x);
    }

    /**
     * Set the projection of this vector along the y-axis.
     * @param y The projection of this vector along the y-axis.
     */
    public void setY(double y){
        this.y = y;
        put(1,0, y);
    }

    /**
     * Set the projection of this vector along the z-axis.
     * @param z The projection of this vector along the z-axis.
     */
    public void setZ(double z){
        this.z = z;
        put(2,0, z);
    }

    /**
     * Calculate the cross product of two vectors
     * D = A x B.
     * @param B The vector to be multiplied with.
     * @return The resulting vector D.
     */
    public Vector cross(Vector B){
        Vector A = this;
        double xValue = (A.getY() * B.getZ()) - (A.getZ() * B.getY());
        double yValue = (A.getZ() * B.getX()) - (A.getX() * B.getZ());
        double zValue = (A.getX() * B.getY()) - (A.getY() * B.getX());
        return new Vector(xValue, yValue, zValue);
    }
}
