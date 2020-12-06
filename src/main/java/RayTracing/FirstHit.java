package RayTracing;

import Matrix.Matrix;
import Matrix.Point;
import Matrix.Vector;
import Objects.Shape;
import java.util.List;

/**
 * Holds information about the first hit between a ray and an object.
 */
public class FirstHit {

    // The closest object that the ray intersected with.
    public Shape hitObject;
    // Point at which the closest intersection occurs.
    public Point hitPoint;
    // Normal vector at hit spot.
    public Vector hitNormal;
    // Specific ray for hit object.
    public Ray hitRay;

    /**
     * Does this first hit exist?
     * @return true if this first hit exists. Returns false otherwise.
     */
    public Boolean exists(){
        return hitPoint != null || hitObject != null || hitRay != null || hitNormal != null;
    }

    /**
     * Get the first hit of the ray with an object in the scene.
     * @param ray The sent out ray.
     * @param objects The objects in the scene.
     */
    public FirstHit(Ray ray, List<Shape> objects){
        // Store original ray.
        Point origin = ray.start;
        Vector direction = ray.dir;

        Double closestTime = null;

        // All intersections of ray with objects in the scene.
        for (Shape object : objects){

            // Specific ray for this object.
            Matrix inverseAT = object.getInverseAT();
            Ray transformed = new Ray(
                    inverseAT.times( origin ),
                    inverseAT.times( direction )
            );

            // Check for collisions.
            Hit hit = object.getClosestHit( transformed );
            // There was a hit.
            if (hit != null){
                double t = hit.getTime();
                if (t >= 0 && (closestTime == null || t <= closestTime)){
                    closestTime = t;
                    // Place hit point at the right location with transformation of object.
                    hitPoint = object.getATMatrix().times( hit.location );
                    hitObject = object;
                    hitRay = transformed;
                    hitRay.recurseLevel = ray.recurseLevel;
                    hitNormal = hit.getNormal();
                }
            }
        }

        // There was an intersection.
        if (exists()) {
            Matrix inverseAT = hitObject.getInverseAT();
            hitNormal = inverseAT.times( hitNormal );
        }
    }

}
