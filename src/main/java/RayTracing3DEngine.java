import Graphics.Screen;
import Interfaces.IATFactory;
import Light.LightSource;
import Matrix.*;
import Matrix.Point;
import Objects.*;
import Objects.Shape;
import RayTracing.Cam;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
        ConfigurationHandler config_handler = new ConfigurationHandler(path.toString());

        // Read configuration parameters.
        int width = config_handler.getIntProperty("width");
        int height = config_handler.getIntProperty("height");
        int refresh_rate = config_handler.getIntProperty("refresh_rate");
        double cam_distance = config_handler.getDoubleProperty("cam_distance");

        // Specify screen dimensions.
        Dimension screen_size = new Dimension(width, height);

        // Calculate aspect ratio.
        double aspect_ratio = (double) screen_size.width / (double) screen_size.height;

        // View angle.
        double view_angle = Math.PI / 3;

        // Create screen.
        Screen screen =
                new Screen(screen_size.width, screen_size.height, aspect_ratio, view_angle, cam_distance);

        // Create an initialize camera.
        Point eye = new Point(0, cam_distance, 0);
        Point look = new Point(0, 0,0);     // Eye looks at the origin.
        Vector up = new Vector(0, 0, 1);
        Cam cam = new Cam().set(eye, look, up);

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define shapes.
        ArrayList<Shape> objects = new ArrayList<>();
        Shape sphere = new Sphere(2);
        objects.add( sphere );

        // Define light sources.
        LightSource source = new LightSource(new Point(10, 10, 10));
        ArrayList<LightSource> sources = new ArrayList<>();
        sources.add( source );

        // Measure time in milliseconds.
        long start = System.currentTimeMillis();
        long end, elapsedTime;

        // Ray trace the current scene.
        cam.rayTrace(screen, objects, sources);
        screen.forceUpdate();

        while(true) {
            // Update every few milliseconds.
            end = System.currentTimeMillis();
            elapsedTime = end - start;

            if (elapsedTime >= refresh_rate) {
                // Reset timer.
                start = System.currentTimeMillis();

                // Poll the keyboard.
                screen.keyboard.poll();
                screen.processInput(cam);

                // Refresh the screen.
                cam.rayTrace(screen, objects, sources);
                screen.forceUpdate();
            }
        }
    }
}