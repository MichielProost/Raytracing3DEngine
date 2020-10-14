import Graphics.Screen;
import Matrix.*;
import Matrix.Point;
import RayTracing.Cam;

import java.awt.*;

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
        double ratio =
                (double) Toolkit.getDefaultToolkit().getScreenSize().width
                        / (double) Toolkit.getDefaultToolkit().getScreenSize().height;

        // Create screen.
        Screen screen = new Screen(500, 500, 50, 60, ratio);

        // Initialize camera.
        cam.set(new Point(0,0,0),new Point(10,0,0),new Vector(0,1,0));

        // Refresh the screen buffer.
        cam.rayTrace(screen);
;    }
}