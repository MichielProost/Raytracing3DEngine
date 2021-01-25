package World.Scenes;

import Graphics.Rgb;
import Interfaces.IATFactory;
import Matrix.ATFactory;
import Matrix.Point;
import Matrix.Vector;
import Objects.*;
import Properties.Material.Material;
import Properties.Texture.Texture;
import World.LightSource;
import World.Scene;
import static Utils.Constants.BLACK;
import static Utils.Constants.DARK_BROWN;

/**
 * Scene: Shapes are rendered next to each other and showcase the different textures.
 */
public class ImageTexturesRow extends Scene {

    /**
     * Showcase the different image textures in a scene.
     * @param maxRecursionLevel The maximum recursion level.
     */
    public ImageTexturesRow( int maxRecursionLevel ){
        // Create a new scene.
        super( maxRecursionLevel );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        Shape world = new Sphere()
                .setMaterial(Material.Materials.lambertian, BLACK)
                .setImageTexture(".\\resources\\World.png")
                .setATMatrix(factory.getTranslation(-0.80, 2, 0.25))
                .setATMatrix(factory.getScaling(0.75, 0.75, 0.75));
        Shape moon = new Sphere()
                .setMaterial(Material.Materials.lambertian, BLACK)
                .setImageTexture(".\\resources\\Moon.jpg")
                .setATMatrix(factory.getTranslation(0.80, 2, 0.25))
                .setATMatrix(factory.getScaling(0.75, 0.75, 0.75));
        Shape box = new Box()
                .setMaterial(Material.Materials.wood)
                .setImageTexture(".\\resources\\Box.png")
                .setATMatrix( factory.getTranslation(3, 3,-0.25))
                .setATMatrix( factory.getScaling(0.75, 0.75, 0.75))
                .setATMatrix( factory.getRotation(IATFactory.RotationAxis.Z, 15) );
        Shape floor = new Plane( new Vector(0, 0, -1), new Point(0, 0, -1 ))
                .setFinite()
                .setMaterial( Material.Materials.lambertian, DARK_BROWN )
                .setTexture( Texture.Textures.checkerboard2D );
        addShape( world );
        addShape( moon );
        addShape( box );
        addShape( floor );

        LightSource source_left = new LightSource(2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        LightSource source_right = new LightSource(-2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        addSource( source_left );
        addSource( source_right );
    }

}
