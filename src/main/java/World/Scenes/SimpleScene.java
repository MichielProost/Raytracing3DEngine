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

public class SimpleScene extends Scene {

    public SimpleScene( int maxRecursionLevel ){
        // Create a new scene.
        super( maxRecursionLevel );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define shapes.
        Shape sphere = new Sphere()
                .setMaterial( Material.Materials.bronze );
        addShape( sphere );

        // Define light sources.
        LightSource source_left = new LightSource(2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        LightSource source_right = new LightSource(-2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        addSource( source_left );
        addSource( source_right );
    }

}
