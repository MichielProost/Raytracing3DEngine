package World.Scenes;

import Graphics.Rgb;
import Interfaces.IATFactory;
import Matrix.ATFactory;
import Matrix.Point;
import Matrix.Vector;
import Objects.Box;
import Objects.Plane;
import Objects.Shape;
import Properties.Material.Material;
import Properties.Texture.Texture;
import World.LightSource;
import World.Scene;
import static Utils.Constants.*;

public class PoolTable extends Scene {

    public PoolTable (int maxRecursionLevel){
        // Create a new scene.
        super( maxRecursionLevel );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define shapes.
        Shape ground = new Plane( new Vector(0, 0, 1), new Point(0, 0, -1 ))
                .setFinite()
                .setMaterial( Material.Materials.polished_wood )
                .setImageTexture( ".\\resources\\Pool\\WoodenFloor.jpg" );
        addShape( ground );
        addShape( table );

        // Define light sources.
        LightSource source_front = new LightSource(0, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        LightSource source_right = new LightSource(2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        LightSource source_left = new LightSource(-2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        addSource( source_front );
        addSource( source_left );
        addSource( source_right );

    }

}
