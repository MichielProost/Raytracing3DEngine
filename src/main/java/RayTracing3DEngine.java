import Graphics.Screen;
import Graphics.Rgb;
import Interfaces.IATFactory;
import World.LightSource;
import Objects.Material;
import Matrix.Point;
import Matrix.Vector;
import Matrix.ATFactory;
import Objects.*;
import Objects.Shape;
import World.Cam;
import World.Scene;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        ConfigurationHandler configHandler = new ConfigurationHandler(path.toString());

        // Read configuration parameters.
        int width = configHandler.getIntProperty("width");
        int height = configHandler.getIntProperty("height");
        int refreshRate = configHandler.getIntProperty("refresh_rate");
        int maxRecursionLevel = configHandler.getIntProperty("max_recursion_level");
        double camDistance = configHandler.getDoubleProperty("cam_distance");

        // Specify screen dimensions.
        Dimension screenSize = new Dimension(width, height);

        // Calculate aspect ratio.
        double aspectRatio = (double) screenSize.width / (double) screenSize.height;

        // View angle.
        double view_angle = Math.PI / 3;

        // Create screen.
        Screen screen =
                new Screen(screenSize.width, screenSize.height, aspectRatio, view_angle, camDistance);

        // Create an initialize camera.
        Point eye = new Point(0, camDistance, 0);
        Point look = new Point(0, 0,0);     // Eye looks at the origin.
        Vector up = new Vector(0, 0, 1);
        Cam cam = new Cam().set(eye, look, up);

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define a scene.
        Scene scene = new Scene(maxRecursionLevel);

        // Define shapes.
        Shape sphere1 = new Sphere()
                .setRadius(0.90)
                .setLocation(0, 0, 0)
                .setMaterial(Material.Materials.polished_silver);
        Shape sphere2 = new Sphere()
                .setRadius(0.30)
                .setLocation(0,4, 0);
        scene.addShape( sphere1 );
        scene.addShape( sphere2 );

        // Define light sources.
        LightSource source = new LightSource(0, 10, 0)
                .setColor(new Rgb(0.7f, 0.7f, 0.7f));
        scene.addSource( source );

        // Measure time in milliseconds.
        long start = System.currentTimeMillis();
        long end, elapsedTime;

        // Render the screen.
        cam.render(screen, scene);
        screen.forceUpdate();

        while(true) {
            // Update every few milliseconds.
            end = System.currentTimeMillis();
            elapsedTime = end - start;

            if (elapsedTime >= refreshRate) {
                // Reset timer.
                start = System.currentTimeMillis();

                // Poll the keyboard.
                screen.keyboard.poll();
                screen.processInput(cam);

                // Refresh the screen.
                cam.render(screen, scene);
                screen.forceUpdate();
            }
        }
    }
}