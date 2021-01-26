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
import static Utils.Constants.DARK_BROWN;

/**
 * Scene: Showcase refraction.
 */
public class RefractionExhibition extends Scene {

    /**
     * View a textured sphere trough a glass cylinder.
     * @param maxRecursionLevel The maximum recursion level.
     */
    public RefractionExhibition( int maxRecursionLevel ){
        // Create a new scene.
        super( maxRecursionLevel );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define shapes.
        Shape sphere = new Sphere()
                .setMaterial( Material.Materials.bronze )
                .setTexture( Texture.Textures.stripes )
                .setATMatrix( factory.getTranslation(0, 1, 0.25))
                .setATMatrix( factory.getScaling(0.75, 0.75, 0.75));
        Shape cylinder = new Cylinder()
                .setMaterial( Material.Materials.glass )
                .setATMatrix( factory.getTranslation( 0, 5, 0.25))
                .setATMatrix( factory.getScaling( 0.7, 0.7, 0.7));
        Shape checkerboard2D = new Plane( new Vector(0, 0, -1), new Point(0, 0, -1 ))
                .setFinite()
                .setMaterial( Material.Materials.lambertian, DARK_BROWN )
                .setTexture( Texture.Textures.checkerboard2D );
        addShape( sphere );
        addShape( cylinder );
        addShape( checkerboard2D );

        // Define light sources.
        LightSource source_front = new LightSource(0, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        LightSource source_right = new LightSource(2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        LightSource source_left = new LightSource(-2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        addSource( source_front );
        addSource( source_left );
        addSource( source_right );
    }

}
