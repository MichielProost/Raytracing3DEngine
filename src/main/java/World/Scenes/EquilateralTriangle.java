package World.Scenes;

import Graphics.Rgb;
import Interfaces.IATFactory;
import Material.Material;
import Matrix.ATFactory;
import Matrix.Point;
import Objects.Shape;
import Objects.Sphere;
import World.Scene;

/**
 * Scene: Three spheres at the corners of an equilateral triangle.
 * The spheres centers have the same value on the z-axis.
 */
public class EquilateralTriangle extends Scene {

    /**
     * Create three spheres and place them at the corners of an equilateral triangle.
     * @param maxRecursionLevel The maximum recursion level.
     * @param size Length of the sides.
     * @param top The location of the top sphere.
     */
    public EquilateralTriangle(int maxRecursionLevel, double size, Point top ){
        // Create a new scene.
        super( maxRecursionLevel );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // The altitude (height) from any side.
        double h = Math.sqrt( 3 ) / 2 * size;

        // Define shapes.
        Shape sphere1 = new Sphere()
                .setMaterial( Material.Materials.gold )
                .setATMatrix( factory.getTranslation(top.getX() - size/2, top.getY() + h, top.getZ()));
        Shape sphere2 = new Sphere()
                .setMaterial( Material.Materials.polished_silver )
                .setATMatrix( factory.getTranslation(top.getX(), top.getY(), top.getZ()));
        Shape sphere3 = new Sphere()
                .setMaterial( Material.Materials.lambertian, new Rgb(0.0f, 0.0f, 1.0f) )
                .setATMatrix( factory.getTranslation(top.getX() + size/2, top.getY() + h, top.getZ()));
        addShape( sphere1 );
        addShape( sphere2 );
        addShape( sphere3 );
    }

}