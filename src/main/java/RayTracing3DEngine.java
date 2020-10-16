import Graphics.Screen;
import Matrix.*;
import Matrix.Point;
import Objects.*;
import Objects.Shape;
import RayTracing.Cam;
import java.awt.*;
import java.util.ArrayList;

/**
 * A 3D engine using the ray tracing principle.
 */
public class RayTracing3DEngine {

    /**
     * @param args The input arguments.
     */
    public static void main (String[] args) {

        // Specify screen dimensions.
        Dimension screenSize = new Dimension(600,400);

        // Calculate aspect ratio.
        double ratio = (double) screenSize.width / (double) screenSize.height;

        // View angle & camera distance.
        double viewAngle = Math.PI / 3;
        double camDistance = 10;

        // Create screen.
        Screen screen =
                new Screen(screenSize.width, screenSize.height, camDistance, viewAngle, ratio);

        // Initialize camera.
        Point eye = new Point(0, 0, camDistance);
        Point look = new Point(0, 0,0);     // Eye looks at the origin.
        Vector up = new Vector(0, 1, 0);
        Cam cam = new Cam().set(eye, look, up);

        // Define shapes.
        ArrayList<Shape> objects = new ArrayList<>();
        objects.add( new Sphere(1) );

        long start = System.currentTimeMillis();
        long end, elapsedTime;

        // Ray trace the current scene.
        cam.rayTrace(screen, objects);
        screen.forceUpdate();

        // The screen's refresh rate.
        int refreshRate = 200;

        while(true) {
            // Update every few ms.
            end = System.currentTimeMillis();
            elapsedTime = end - start;

            if (elapsedTime > refreshRate) {
                cam.slide(0.0, 0.05, -0.1);
                cam.roll(1.0);
                // Refresh the screen.
                cam.rayTrace(screen, objects);
                screen.forceUpdate();
            }
        }
    }
}