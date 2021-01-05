package RayTracing;

import Matrix.Point;
import Matrix.Vector;

/**
 * Intersection between ray and object.
 */
public class Intersection {
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
     * Create a new intersection.
     * @param time The time at which the intersection occurs.
     * @param location The location at which the intersection occurs.
     * @param normal The normal vector at the intersection.
     */
    public Intersection(double time, Point location, Vector normal){
        this.time = time;
        this.location = location;
        this.normal = normal;
    }

    /**
     * Get the time at which the intersection occurs.
     * @return the time at which the intersection occurs.
     */
    public double getTime(){
        return time;
    }

}
