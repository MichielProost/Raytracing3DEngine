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

    // The image texture of the shape.
    public ImageTexture imageTexture;

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
     * Get the x location of the pixel (normalized between 0 and 1) given a hit location.
     * @param hitlocation The location of the hit.
     * @return The x location of the pixel [0 1].
     */
    public abstract double getPixelX(Point hitlocation);

    /**
     * Get the y location of the pixel (normalized between 0 and 1) given a hit location.
     * @param hitLocation The location of the hit.
     * @return The y location of the pixel [0 1].
     */
    public abstract double getPixelY(Point hitLocation);

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
     * Get the image texture of this shape.
     * @return The image texture of this shape.
     */
    public ImageTexture getImageTexture(){
        return imageTexture;
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
     * Set the image texture of this shape.
     * @param path The path of the image.
     * @return This shape.
     */
    public Shape setImageTexture(String path){
        imageTexture = new ImageTexture( path );
        return this;
    }

    /**
     * Returns whether or not the shape has a texture.
     * @return Whether or not the shape has a texture.
     */
    public boolean hasTexture(){
        return texture != null;
    }

    /**
     * Returns whether or not the shape has an image texture.
     * @return Whether or not the shape has an image texture.
     */
    public boolean hasImageTexture(){
        return imageTexture != null;
    }

}
