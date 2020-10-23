package Objects;

import Matrix.*;
import RayTracing.Ray;
import Graphics.*;

import java.util.List;

/**
 * The class that represents an object or shape in 3D space.
 */
public abstract class Shape {

    // The location of the object.
    protected Point location = new Point(0,0,0);

    // The color of the object.
    protected Rgb color = new Rgb(1.0f, 1.0f, 1.0f);

    // Transformation matrix.
    private Matrix ATMatrix = new Identity(4);

    // Inverse transformation matrix.
    private Matrix InverseAT;

    /**
     * Get the hit time between this object and a given ray.
     * @param ray The given ray.
     * @return The closest value of t. Returns null if no hit points are found.
     */
    public abstract Double getCollidingT(Ray ray);

    public Shape setLocation(Point location){
        this.location = location;
        return this;
    }

    /**
     * Get the color of this object.
     * @return The color of this object.
     */
    public Rgb getColor() {
        return color;
    }

    /**
     * Get the location of this object.
     * @return The location of this object.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Return the Affine Transformation matrix.
     * @return The AT matrix.
     */
    public Matrix getATMatrix(){
        return ATMatrix;
    }

    /**
     * Set the Affine Transformation matrix.
     * @param matrices The AT matrices.
     * @return this shape.
     */
    public Shape setATMatrix(List<Matrix> matrices){
        for (Matrix matrix : matrices){
            this.ATMatrix = this.ATMatrix.times(matrix);
        }
        this.InverseAT = this.ATMatrix.inverse();
        return this;
    }

    /**
     * Return the inverse AT matrix.
     * @return The inverse AT matrix.
     */
    public Matrix getInverseAT(){
        return InverseAT;
    }
}