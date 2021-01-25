package Objects;

import Graphics.Rgb;
import Properties.Material.Material;
import Properties.Material.Lambertian;
import Properties.Texture.*;
import Matrix.*;
import RayTracing.Intersection;
import RayTracing.Ray;
import java.util.List;

/**
 * Represents an object or shape in 3D space.
 */
public abstract class Shape {

    // Affine transformation matrix.
    private Matrix ATMatrix = new Identity(4);

    // Inverse transformation matrix.
    private Matrix InverseAT = new Identity(4);

    // The material of the shape.
    public Material material = new Lambertian();

    // The texture of the shape.
    public Texture texture;

    /**
     * Create a new shape.
     */
    public Shape(){}

    /**
     * Return the closest intersection between this object and a given ray.
     * @param ray The given ray.
     * @return The closest intersection. Returns null if there are no intersections.
     */
    public abstract Intersection getClosestIntersection(Ray ray);

    /**
     * Return the affine transformation matrix.
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
     * Return the material of the shape.
     * @return The material of the shape.
     */
    public Material getMaterial(){
        return material;
    }

    /**
     * Get the texture of this shape.
     * @return The texture of this shape.
     */
    public Texture getTexture(){
        return texture;
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
     * @param color The color of the material.
     * @return This shape.
     */
    public Shape setMaterial(Material.Materials type, Rgb color){
        this.material = new Material().getMaterial(type, color);
        return this;
    }

    /**
     * Set the affine transformation matrix.
     * @param matrix The AT matrix.
     * @return This shape.
     */
    public Shape setATMatrix(Matrix matrix){
        this.ATMatrix = this.ATMatrix.times( matrix );
        this.InverseAT = this.ATMatrix.inverse();
        return this;
    }

    /**
     * Set the affine transformation matrix.
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
     * Set the texture of this shape.
     * @param type The texture type.
     * @return This shape.
     */
    public Shape setTexture(Texture.Textures type){
        switch( type ){
            case stripes:
                texture = new Stripes();
                break;
            case smoothColors:
                texture = new SmoothColors();
                break;
            case checkerboard2D:
                texture = new Checkerboard2D();
                break;
            case checkerboard3D:
                texture = new Checkerboard3D();
                break;
        }
        return this;
    }

    /**
     * Returns whether or not the shape has a texture.
     * @return Whether or not the shape has a texture.
     */
    public boolean hasTexture(){
        return texture != null;
    }

}
