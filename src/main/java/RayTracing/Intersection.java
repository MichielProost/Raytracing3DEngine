package RayTracing;

import Matrix.Point;
import Matrix.Vector;
import Objects.Shape;

/**
 * Intersection between ray and object.
 */
public class Intersection {
    // The ray.
    private Ray ray;

    // The object.
    private Shape object;

    // The time at which the intersection occurs.
    private double time;

    // The location at which the intersection occurs.
    private Point location;

    // Normal vector at the intersection.
    private Vector normal;

    // Transformed ray corresponding to object.
    private Ray transformedRay;

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
     * Get the ray.
     * @return The ray.
     */
    public Ray getRay(){
        return ray;
    }

    /**
     * Get the object.
     * @return The object.
     */
    public Shape getObject(){
        return object;
    }

    /**
     * Get the time at which the intersection occurs.
     * @return The time at which the intersection occurs.
     */
    public double getTime(){
        return time;
    }

    /**
     * Get the location at which the intersection occurs.
     * @return The location at which the intersection occurs.
     */
    public Point getLocation(){
        return location;
    }

    /**
     * Get the normal vector at this intersection.
     * @return The normal vector at this intersection.
     */
    public Vector getNormal(){
        return normal;
    }

    /**
     * Get the transformed ray.
     * @return The transformed ray.
     */
    public Ray getTransformedRay(){
        return transformedRay;
    }

    /**
     * Set the ray.
     * @param ray The ray.
     */
    public void setRay(Ray ray){
        this.ray = ray;
    }

    /**
     * Set the object.
     * @param object The object.
     */
    public void setObject(Shape object){
        this.object = object;
    }

    /**
     * Set the location of this intersection.
     * @param location The location of this intersection.
     */
    public void setLocation(Point location){
        this.location = location;
    }

    /**
     * Set the normal vector of this intersection.
     * @param normal The normal vector of this intersection.
     */
    public void setNormal(Vector normal){
        this.normal = normal;
    }

    /**
     * Set the transformed ray of this intersection.
     * @param transformedRay The transformed ray of this intersection.
     */
    public void setTransformedRay(Ray transformedRay){
        this.transformedRay = transformedRay;
    }
}
