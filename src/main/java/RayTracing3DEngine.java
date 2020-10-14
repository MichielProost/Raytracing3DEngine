import Graphics.Screen;
import Matrix.*;
import RayTracing.Cam;

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

        // Create screen.
        Screen screen = new Screen(500,500).setLocation(50, 0, 0);

        // Initialize camera.
        cam.set(new Point(0,0,0),new Point(-10,0,0),new Vector(0,1,0));

        // Refresh the screen buffer.
        cam.rayTrace(screen);
;    }
}