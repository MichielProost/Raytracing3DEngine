package World.Scenes;

import Interfaces.IATFactory;
import Material.Material;
import Material.Texture.Texture;
import Matrix.ATFactory;
import Matrix.Point;
import Matrix.Vector;
import Objects.*;
import World.Scene;
import static Utils.Constants.*;

/**
 * Scene: All shapes are rendered next to each other.
 * The shapes have the same value on the z-axis.
 */
public class ShapesRow extends Scene {

    /**
     * Create every generic shape and place them in a line next to each other.
     * @param maxRecursionLevel The maximum recursion level.
     */
    public ShapesRow( int maxRecursionLevel ){
        // Create a new scene.
        super( maxRecursionLevel );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define shapes.
        Shape cylinder = new Cylinder()
                .setMaterial( Material.Materials.mirror )
                .setATMatrix( factory.getTranslation(0, 6, 0.25) )
                .setATMatrix( factory.getScaling( 0.60, 0.60, 0.60 ))
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.Y, 50));
        Shape sphere = new Sphere()
                .setMaterial( Material.Materials.gold )
                .setATMatrix( factory.getTranslation(0, 2, 0) );
        Shape box = new Box()
                .setMaterial( Material.Materials.glass )
                .setATMatrix( factory.getTranslation(2, 3, 0.25) )
                .setATMatrix( factory.getScaling( 0.60, 0.60, 0.60 ))
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.Y, -50));
        Shape ground = new Plane( new Vector(0, 0, -1), new Point(0, 0, -1 ))
                .setFinite()
                .setTexture( Texture.Textures.checkerboard2D )
                .setMaterial( Material.Materials.lambertian, DARK_BROWN );
        addShape( cylinder );
        addShape( sphere );
        addShape( box );
        addShape( ground );
    }

}
