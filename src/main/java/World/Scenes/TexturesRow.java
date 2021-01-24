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
 * Scene: Spheres are rendered next to each other and showcase the different textures.
 */
public class TexturesRow extends Scene {

    /**
     * Showcase the different textures in a scene.
     * @param maxRecursionLevel The maximum recursion level.
     */
    public TexturesRow( int maxRecursionLevel ){
        // Create a new scene.
        super( maxRecursionLevel );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define shapes.
        Shape stripes = new Cylinder()
                .setMaterial( Material.Materials.bronze )
                .setTexture( Texture.Textures.stripes )
                .setATMatrix( factory.getTranslation(-2, 3, 0.25) )
                .setATMatrix( factory.getScaling( 0.60, 0.60, 0.60 ))
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.Y, 50));
        Shape smoothColors = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setTexture( Texture.Textures.smoothColors )
                .setATMatrix( factory.getTranslation(0, 2, 0));
        Shape checkerboard3D = new Box()
                .setMaterial( Material.Materials.polished_silver )
                .setTexture( Texture.Textures.checkerboard3D )
                .setATMatrix( factory.getTranslation(2, 3, 0.25) )
                .setATMatrix( factory.getScaling( 0.60, 0.60, 0.60 ))
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.Y, -50));
        Shape checkerboard2D = new Plane( new Vector(0, 0, -1), new Point(0, 0, -1 ))
                .setFinite()
                .setMaterial( Material.Materials.lambertian, DARK_BROWN )
                .setTexture( Texture.Textures.checkerboard2D );
        addShape( stripes );
        addShape( smoothColors );
        addShape( checkerboard3D );
        addShape( checkerboard2D );
    }

}
