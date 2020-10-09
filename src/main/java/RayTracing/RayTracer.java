package RayTracing;
import Graphics.Screen;
import Matrix.*;

public class RayTracer {

    static Screen screen;
    static Matrix eye = new Point(0,0,0);   // The eye is at the origin.

    /**
     * Initialize the RayTracer with its parameters.
     * @param width The width of the screen.
     * @param height The height of the screen.
     * @param N Distance from the eye (origin) to the screen.
     */
    public RayTracer(int width, int height, double N)
    {
        screen = new Screen(width, height).setLocation(N, 0, 0);
    }

    /**
     * Refresh the frame buffer by recalculating the color of every pixel.
     */
    public void refresh()
    {
        // Define objects and light sources in the scene.
        // Set up the camera.
        for (int r=0; r < screen.getHeight(); r++){
            for (int c = 0; c < screen.getWidth(); c++)
            {
                // Built the rc-th ray.
                Matrix normal = screen.getCenter().minus(eye);
                Matrix centerToPix = screen.getPixelLeftCorner(c, r).minus(screen.getCenter());

                Matrix dir = normal.plus(centerToPix);

                screen.drawPoint(r,c);
                Ray ray = new Ray(eye, dir);

                // Find all the intersections of ray with objects in the scene.
                // Identify the intersection that lies closest to, and in front of, the eye.
                // Compute the hit point where the ray hits the object, and the normal vector at that point.
                // Find the color of the light returning to the eye along the ray from the point of intersection.
                // Place the color in the rc-th pixel.
            }
        }
    }
}