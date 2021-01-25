package World.Scenes;

import Interfaces.IATFactory;
import Properties.Material.Material;
import Matrix.ATFactory;
import Matrix.Point;
import Matrix.Vector;
import Objects.Plane;
import Objects.Shape;
import Objects.Sphere;
import World.Scene;
import static Utils.Constants.*;

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
    public EquilateralTriangle( int maxRecursionLevel, double size, Point top ){
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
                .setMaterial( Material.Materials.mirror )
                .setATMatrix( factory.getTranslation(top.getX() + size/2, top.getY() + h, top.getZ()));
        Shape ground = new Plane( new Vector(0, 0, -1), new Point(0, 0, -1 ))
                .setFinite()
                .setMaterial( Material.Materials.lambertian, DARK_BROWN );
        Shape ceiling = new Plane( new Vector(0, 0, 1), new Point(0, 0, 3))
                .setFinite()
                .setMaterial( Material.Materials.lambertian, GREEN );
        Shape left_wall = new Plane( new Vector(-1, 0, 0), new Point(-5, 0, 0))
                .setFinite()
                .setMaterial( Material.Materials.lambertian, BLUE );
        Shape right_wall = new Plane( new Vector(1, 0, 0), new Point(5, 0, 0))
                .setFinite()
                .setMaterial( Material.Materials.lambertian, RED );
        addShape( sphere1 );
        addShape( sphere2 );
        addShape( sphere3 );
        addShape( ground );
        addShape( ceiling );
        addShape( left_wall );
        addShape( right_wall );
    }

}