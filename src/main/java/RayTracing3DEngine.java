import Graphics.Screen;
import Interfaces.IATFactory;
import Matrix.ATFactory;
import Objects.*;
import Objects.Shape;
import Properties.Material.Material;
import Properties.Texture.Texture;
import World.LightSource;
import Matrix.Point;
import Matrix.Vector;
import World.Cam;
import World.Scene;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import Graphics.Rgb;
import World.Scenes.EquilateralTriangle;
import World.Scenes.RefractionExhibition;
import World.Scenes.ShapesRow;
import World.Scenes.TexturesRow;

import static Utils.Constants.BLACK;
import static Utils.Constants.DARK_BROWN;

/**
 * A 3D engine using the ray tracing principle.
 */
public class RayTracing3DEngine {

    /**
     * @param args The input arguments.
     */
    public static void main (String[] args){

        // Create configuration handler.
        Path path = Paths.get(".\\src\\main\\java\\config.properties");
        ConfigurationHandler configHandler = new ConfigurationHandler( path.toString() );

        // Read configuration parameters.
        int width = configHandler.getIntProperty("width");
        int height = configHandler.getIntProperty("height");
        int refreshRate = configHandler.getIntProperty("refresh_rate");
        int maxRecursionLevel = configHandler.getIntProperty("max_recursion_level");
        double camDistance = configHandler.getDoubleProperty("cam_distance");
        double camVelocity = configHandler.getDoubleProperty("cam_velocity");

        // Specify screen dimensions.
        Dimension screenSize = new Dimension( width, height );

        // Calculate aspect ratio.
        double aspectRatio = (double) screenSize.width / (double) screenSize.height;

        // View angle.
        double viewAngle = Math.PI / 3;

        // Create screen.
        Screen screen =
                new Screen(screenSize.width, screenSize.height, aspectRatio, viewAngle, camDistance);

        // Create camera.
        Point eye = new Point(0, camDistance, 0);
        Point look = new Point(0, 0,0);     // Eye looks at the origin.
        Vector up = new Vector(0, 0, 1);
        Cam cam = new Cam( eye, look, up ).setVelocity( camVelocity );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Create a new scene.
        //Scene scene = new EquilateralTriangle( maxRecursionLevel, 4, new Point(0, 0, 0));
        Scene scene = new ShapesRow( maxRecursionLevel );
        //Scene scene = new TexturesRow( maxRecursionLevel );
        //Scene scene = new RefractionExhibition( maxRecursionLevel );

        // Measure time in milliseconds.
        long start = System.currentTimeMillis();
        long end, elapsedTime;

        // Render the screen.
        cam.render( screen, scene );
        screen.update();

        while(true) {
            // Update every few milliseconds.
            end = System.currentTimeMillis();
            elapsedTime = end - start;

            if (elapsedTime >= refreshRate) {
                // Reset timer.
                start = System.currentTimeMillis();

                // Poll the keyboard.
                screen.getKeyboard().poll();
                screen.processInput( cam );

                // Refresh the screen.
                cam.render(screen, scene);
                screen.update();
            }
        }
    }
}