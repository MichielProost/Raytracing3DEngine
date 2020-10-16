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
        // Create camera.
        Cam cam = new Cam();

        // Aspect ratio.
        Dimension screenSize = new Dimension(600,400);
        double ratio = (double) screenSize.width / (double) screenSize.height;

        // View angle & camera distance.
        double viewAngle = Math.PI / 3;
        double camDistance = 10;

        // Create screen.
        Screen screen =
                new Screen(screenSize.width, screenSize.height, camDistance, viewAngle, ratio);

        // Initialize camera.
        cam.set(new Point(0,0,camDistance),new Point(10,0,0),new Vector(0,1,0));

        // Define shapes.
        ArrayList<Shape> objects = new ArrayList<>();
        objects.add( new Sphere(1) );

        // Refresh the screen buffer.
        cam.rayTrace(screen, objects);

        screen.forceUpdate();
        System.out.println("Done");

;    }
}