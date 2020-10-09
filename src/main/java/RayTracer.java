public class RayTracer {

    int nCols;  // Screen width (number of pixels).
    int nRows;  // Screen height (number of pixels).

    /**
     * Initialize the RayTracer with its parameters.
     * @param width
     * @param height
     */
    public RayTracer(int width, int height)
    {
        nCols = width;
        nRows = height;
    }

    /**
     * Refresh the frame buffer by recalculating the color of every pixel.
     */
    public void refresh()
    {
        // Define objects and light sources in the scene.
        // Set up the camera.
        for (int r=0; r < nRows; r++){
            for (int c = 0; c < nCols; c++)
            {
                // Built the rc-th ray.
                // Find all the intersections of ray with objects in the scene.
                // Identify the intersection that lies closest to, and in front of, the eye.
                // Compute the hit point where the ray hits the object, and the normal vector at that point.
                // Find the color of the light returning to the eye along the ray from the point of intersection.
                // Place the color in the rc-th pixel.
            }
        }
    }
}
