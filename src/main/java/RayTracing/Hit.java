package RayTracing;

import Matrix.Point;
import Matrix.Vector;

/**
 * Holds information about the intersection between a ray and an object.
 */
public class Hit {

    // The time at which the hit occurs.
    public Double time;
    // The location at which the hit occurs.
    public Point location;
    // The normal vector at the intersection.
    public Vector normal;

    /**
     * Create a new hit.
     * @param time The time at which the hit occurs.
     * @param location The location at which the hit occurs.
     * @param normal The normal vector at the intersection.
     */
    public Hit(double time, Point location, Vector normal){
        this.time = time;
        this.location = location;
        this.normal = normal;
    }

    /**
     * Return the time at which the hit occurs.
     * @return The time at which the hit occurs.
     */
    public double getTime(){
        return time;
    }

    /**
     * Return the location at which the hit occurs.
     * @return The location at which the hit occurs.
     */
    public Point getLocation(){
        return location;
    }

    /**
     * Return the normal vector at this intersection.
     * @return The normal vector at this intersection.
     */
    public Vector getNormal(){
        return normal;
    }

}
