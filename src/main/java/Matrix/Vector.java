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
     * Default constructor.
     */
    public Vector(){
        super(4, 1);            // Create a 4-by-1 matrix.
    }

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
     * Get the magnitude of this vector.
     * @return The magnitude of this vector.
     */
    public double getMagnitude(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
    }

    /**
     * Normalizing this vector.
     * @return The normalized vector.
     */
    public Vector normalize(){
        double magnitude = getMagnitude();
        setX(x / magnitude);
        setY(y / magnitude);
        setZ(z / magnitude);
        return this;
    }

    /**
     * D = A + B.
     * @param B The vector to be added.
     * @return The resulting vector D.
     */
    public Vector plus(Vector B){
        Vector D = new Vector(  this.getX() + B.getX(),
                                this.getY() + B.getY(),
                                this.getZ() + B.getZ());
        return D;
    }

    /**
     * Perform a dot product between this vector and another vector.
     * @param B The vector to do the dot product with.
     * @return The result of the dot product.
     */
    public double dot(Vector B){
        Vector A = this;
        return
                ((A.getX() * B.getX()) + (A.getY() * B.getY()) + (A.getZ() * B.getZ()));
    }

    /**
     * Perform a dot product between this vector and another point.
     * @param B The point to do the dot product with.
     * @return The result of the dot product.
     */
    public double dot(Point B){
        Vector A = this;
        return
                ((A.getX() * B.getX()) + (A.getY() * B.getY()) + (A.getZ() * B.getZ()));
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
