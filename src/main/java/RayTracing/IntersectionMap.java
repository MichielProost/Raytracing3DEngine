package RayTracing;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides information about a set of intersections.
 */
public class IntersectionMap {

    // A map containing intersections.
    private final Map<Double, Intersection> intersections;

    // The time at which the closest intersection occurs.
    private Double lowestTime;

    // The closest intersection.
    private Intersection closestIntersection;

    /**
     * Create a new intersection map.
     */
    public IntersectionMap(){
        intersections = new HashMap<>();
    }

    /**
     * Return the closest intersection.
     * @return The closest intersection. Returns null if there are no intersections in this map.
     */
    public Intersection getClosestIntersection(){
        return closestIntersection;
    }

    /**
     * Is the map empty?
     * @return whether the map is empty or not.
     */
    public boolean isEmpty(){
        return closestIntersection == null || intersections.isEmpty();
    }

    /**
     * Add an intersection to the map.
     * @param intersection The new intersection.
     */
    public void addIntersection(Intersection intersection){
        // Time at which the intersection occurs.
        double time = intersection.getTime();

        // Put intersection in map.
        intersections.put(time, intersection);

        double epsilon = 0.00001;
        // Check if this is the closest intersection.
        if ((time >= epsilon) && (lowestTime == null || time < lowestTime)){
            // Time of closest intersection.
            lowestTime = time;
            // The closest intersection.
            closestIntersection = intersection;
        }
    }

}
