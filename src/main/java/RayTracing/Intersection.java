package RayTracing;

import Matrix.Point;
import Matrix.Vector;

/**
 * Intersection between ray and object.
 */
public class Intersection {
    // The ray.
    private Ray ray;

    // The object.
    private Object object;

    // The time at which the intersection occurs.
    private double time;

    // The location at which the intersection occurs.
    private Point location;

    // Normal vector at the intersection.
    private Vector normal;

    // Transformed ray corresponding to object.
    private Ray mutatedRay;

    /**
     * Get the time at which the intersection occurs.
     * @return the time at which the intersection occurs.
     */
    public double getTime(){
        return time;
    }

}
