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

        // Ray trace the current scene.
        cam.rayTrace(screen, objects);

        // Refresh the screen.
        screen.forceUpdate();
        System.out.println("Done");

;    }
}