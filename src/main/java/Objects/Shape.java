package Objects;

import Matrix.*;
import RayTracing.Ray;
import Graphics.*;
import java.util.List;

/**
 * The class that represents an object or shape in 3D space.
 */
public abstract class Shape {

    // Transformation matrix.
    private Matrix ATMatrix = new Identity(4);

    // Inverse transformation matrix.
    private Matrix InverseAT;

    // All shapes are by default gold.
    public Material material = new Material().Gold();

    // Represents how shiny the object is.
    public float shininess = 0.0f;

    // Represents how transparent the object is.
    public float transparency = 0.0f;

    /**
     * Default constructor.
     */
    public Shape(){
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
     * Set the material type of the shape.
     * @param type The type of material.
     * @return This shape.
     */
    public Shape setMaterial(Material.MaterialType type){
        this.material = new Material().getMaterial(type);
        return this;
    }

    /**
     * Set the shininess of the shape.
     * @param shininess The required shininess.
     * @return This shape.
     */
    public Shape setShininess(float shininess){
        this.shininess = shininess;
        return this;
    }

    /**
     * Set the transparency of the shape.
     * @param transparency The required transparency.
     * @return This shape.
     */
    public Shape setTransparency(float transparency){
        this.transparency = transparency;
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

    /**
     * Is the object shiny enough?
     * @return True if the object is shiny enough. False otherwise.
     */
    public boolean isShinyEnough(){

        // Required shininess.
        float required = 0.1f;

        return shininess >= required;

    }

    /**
     * Is the object transparent enough?
     * @return True if the object is transparent enough. False otherwise.
     */
    public boolean isTransparentEnough(){

        // Required transparency.
        float required = 0.1f;

        return transparency >= required;

    }
}
