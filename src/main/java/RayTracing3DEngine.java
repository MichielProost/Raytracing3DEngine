import Graphics.Screen;
import Graphics.Rgb;
import Interfaces.IATFactory;
import Matrix.Matrix;
import World.LightSource;
import Material.Material;
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
import java.util.ArrayList;
import java.util.List;

import Material.Lambertian;

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
        Matrix trans1 = factory.getScaling(1, 1, 2);
        Matrix trans2 = factory.getScaling(3, 1, 1);

        // Define a scene.
        Scene scene = new Scene(maxRecursionLevel);

        // Define shapes.
        Shape sphere1 = new Sphere()
                .setRadius(0.90)
                .setLocation(0,3, 0)
                .setMaterial(Material.Materials.polished_silver)
                .setATMatrix(trans1);
        Shape sphere2 = new Sphere()
                .setRadius(0.30)
                .setLocation(0, 7, 0)
                .setMaterial(Material.Materials.gold)
                .setATMatrix(trans2);
        scene.addShape( sphere1 );
        scene.addShape( sphere2 );

        // Define light sources.
        LightSource source = new LightSource(0, 10, 0)
                .setColor(new Rgb(1.0f, 1.0f, 1.0f));
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