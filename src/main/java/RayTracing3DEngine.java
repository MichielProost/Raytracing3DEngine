import Graphics.Screen;
import Interfaces.IATFactory;
import Light.LightSource;
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
        Point eye = new Point(0, camDistance, 0);
        Point look = new Point(0, 0,0);     // Eye looks at the origin.
        Vector up = new Vector(0, 0, 1);
        Cam cam = new Cam().set(eye, look, up);

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define shapes.
        ArrayList<Shape> objects = new ArrayList<>();
        Shape box = new Box(new Point(0,0,0), new Point(1,1,1));
        objects.add( box );

        // Define light source(s).
        LightSource source = new LightSource(new Point(10, 10, 10));
        ArrayList<LightSource> sources = new ArrayList<>();
        sources.add(source);

        long start = System.currentTimeMillis();
        long end, elapsedTime;

        // Ray trace the current scene.
        cam.rayTrace(screen, objects, sources);
        screen.forceUpdate();

        // The screen's refresh rate.
        int refreshRate = 10;

        while(true) {
            // Update every few ms.
            end = System.currentTimeMillis();
            elapsedTime = end - start;

            if (elapsedTime > refreshRate) {
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