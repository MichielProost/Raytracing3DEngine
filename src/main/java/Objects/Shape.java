package Objects;

import Graphics.Rgb;
import Material.Material;
import Material.Lambertian;
import Matrix.*;
import RayTracing.Hit;
import RayTracing.Ray;
import java.util.List;

/**
 * Represents an object or shape in 3D space.
 */
public abstract class Shape {

    // Transformation matrix.
    private Matrix ATMatrix = new Identity(4);

    // Inverse transformation matrix.
    private Matrix InverseAT = new Identity(4);

    // The material of the shape (default: white).
    public Material material = new Lambertian();

    /**
     * Default constructor.
     */
    public Shape(){}

    /**
     * Gather information about the hit between this object and a given ray.
     * @param ray The given ray.
     * @return Information about the hit. Returns null if no hit was found.
     */
    public abstract Hit getClosestHit(Ray ray);

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
     * Set the material of this shape.
     * @param material The required material.
     * @return This shape.
     */
    public Shape setMaterial(Material material){
        this.material = material;
        return this;
    }

    /**
     * Set the material of this shape.
     * @param type The type of material.
     * @return This shape.
     */
    public Shape setMaterial(Material.Materials type){
        this.material = new Material().getMaterial(type);
        return this;
    }

    /**
     * Set the material of this shape.
     * @param type The type of material.
     * @param color The required color of the material.
     * @return This shape.
     */
    public Shape setMaterial(Material.Materials type, Rgb color){
        this.material = new Material().getMaterial(type, color);
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
     * @param matrices The individual AT matrices.
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
