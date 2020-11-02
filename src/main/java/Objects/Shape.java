package Objects;

import Light.Material;
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
    protected Rgb color = new Rgb(0.0f, 0.0f, 0.0f);

    // Transformation matrix.
    private Matrix ATMatrix = new Identity(4);

    // Inverse transformation matrix.
    private Matrix InverseAT;

    // All shapes are by default gold.
    public Material material = new Material().Gold();

    /**
     * Default constructor.
     */
    public Shape(){
        this.InverseAT = this.ATMatrix;
    }

    /**
     * Constructor.
     * @param loc The location of the shape.
     */
    public Shape(Point loc){
        this.location = loc;
        this.InverseAT = this.ATMatrix;
    }

    /**
     * Get the normal vector at the hit spot.
     * @param hit The hit spot.
     * @return The normal vector at the hit spot.
     */
    public abstract Vector getNormalVector(Point hit);

    /**
     * Get the hit time between this object and a given ray.
     * @param ray The given ray.
     * @return The closest value of t. Returns null if no hit points are found.
     */
    public abstract Double getCollidingT(Ray ray);

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
     * Return the inverse AT matrix.
     * @return The inverse AT matrix.
     */
    public Matrix getInverseAT(){
        return InverseAT;
    }

    /**
     * Set the location of the shape.
     * @param location The location of the shape.
     */
    public void setLocation(Point location){
        this.location = location;
    }

    /**
     * Set the material type of the shape.
     * @param type The type of material.
     * @return This shape.
     */
    public Shape setMaterial(Material.MaterialType type){
        this.material = new Material().getMaterial(type);
        return this;
    }

    /**
     * Set the Affine Transformation matrix.
     * @param matrix The AT matrix.
     * @return This shape.
     */
    public Shape setATMatrix(Matrix matrix){
        this.ATMatrix = this.ATMatrix.times(matrix);
        this.InverseAT = this.ATMatrix.inverse();
        return this;
    }

    /**
     * Set the Affine Transformation matrix.
     * @param matrices The AT matrices.
     * @return This shape.
     */
    public Shape setATMatrices(List<Matrix> matrices){
        for (Matrix matrix : matrices){
            this.ATMatrix = this.ATMatrix.times(matrix);
        }
        this.InverseAT = this.ATMatrix.inverse();
        return this;
    }
}
