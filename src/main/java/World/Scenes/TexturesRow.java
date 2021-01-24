package World.Scenes;

import Interfaces.IATFactory;
import Material.Material;
import Material.Texture.Texture;
import Matrix.ATFactory;
import Matrix.Point;
import Matrix.Vector;
import Objects.Plane;
import Objects.Shape;
import Objects.Sphere;
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
        Shape stripeSphere = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setTexture( Texture.Textures.stripes )
                .setATMatrix( factory.getTranslation(0, 1, 0) )
                .setATMatrix( factory.getScaling(1.5, 1.5, 1.5) );
        Shape ground = new Plane( new Vector(0, 0, -1), new Point(0, 0, -1 ))
                .setFinite()
                .setMaterial( Material.Materials.lambertian, DARK_BROWN );
        addShape( stripeSphere );
        addShape( ground );
    }

}
