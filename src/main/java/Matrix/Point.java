package Matrix;

/**
 * The matrix representation of a point.
 * [x-value, y-value, z-value, 0]^T
 */
public class Point extends Matrix{

    private double x;   // The value on the x-axis.
    private double y;   // The value on the y-axis.
    private double z;   // The value on the z-axis.

    /**
     * Default constructor.
     */
    public Point(){
        super(4,1);
        put(3, 0, 1);     // Element on the 4th row should be 1.
    }

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
        put(3, 0, 1);     // Element on the 4th row should be 1.
        put(0, 0, x).put(1, 0, y).put(2, 0, z);
    }

    /**
     * Get the value on the x-axis.
     * @return The value on the x-axis.
     */
    public double getX(){
        return x;
    }

    /**
     * Get the value on the y-axis.
     * @return The value on the y-axis.
     */
    public double getY(){
        return y;
    }

    /**
     * Get the value on the z-axis.
     * @return The value on the z-axis.
     */
    public double getZ(){
        return z;
    }

    /**
     * Set the value on the x-axis.
     * @param x The value on the x-axis.
     */
    public void setX(double x){
        this.x = x;
        put(0,0, x);
    }

    /**
     * Set the value on the y-axis.
     * @param y The value on the y-axis.
     */
    public void setY(double y){
        this.y = y;
        put(1,0, y);
    }

    /**
     * Set the value on the z-axis.
     * @param z The value on the z-axis.
     */
    public void setZ(double z){
        this.z = z;
        put(1,0, z);
    }

    /**
     * Get the magnitude of this point.
     * @return The magnitude of this point.
     */
    public double getMagnitude(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
    }

    /**
     * Normalize this point.
     * @return This point.
     */
    public Point normalize(){
        double magnitude = getMagnitude();
        setX(x / magnitude);
        setY(y / magnitude);
        setZ(z / magnitude);
        return this;
    }

    /**
     * Perform a dot product between this point and a vector B.
     * @param B The vector to do the dot product with.
     * @return The result of the dot product.
     */
    public double dot(Vector B){
        Point A = this;
        return
                ((A.getX() * B.getX()) + (A.getY() * B.getY()) + (A.getZ() * B.getZ()));
    }

    /**
     * D = A + B.
     * @param B The vector to be added.
     * @return The resulting matrix D.
     */
    public Point plus(Vector B){
        Point A = this;
        Point D = new Point();

        D.setX(A.getX() + B.getX());
        D.setY(A.getY() + B.getY());
        D.setZ(A.getZ() + B.getZ());

        return D;
    }

    /**
     * D = A - B.
     * @param B The point which we are going to subtract from this point.
     * @return A vector which captures the result.
     */
    public Vector minus(Point B){
        Point A = this;
        Vector D = new Vector();

        D.setX(A.getX() - B.getX());
        D.setY(A.getY() - B.getY());
        D.setZ(A.getZ() - B.getZ());

        return D;
    }

    /**
     * D = A - B.
     * @param B The vector which we are going to subtract with this point.
     * @return A point which captures the result.
     */
    public Point minus(Vector B){
        Point A = this;
        Point D = new Point();

        D.setX(A.getX() - B.getX());
        D.setY(A.getY() - B.getY());
        D.setZ(A.getZ() - B.getZ());

        return D;
    }
}
