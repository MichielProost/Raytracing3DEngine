package RayTracing;

import Matrix.Matrix;
import Matrix.Point;
import Matrix.Vector;
import Objects.Shape;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds information about the first hit between a ray and an object.
 */
public class HitInfo {

    // The closest object that the ray intersected with.
    public Shape hitObject;
    // Point at which the closest intersection occurs.
    public Point hitPoint;
    // Normal vector at hit spot.
    public Vector hitNormal;
    // Specific ray for hit object.
    public Ray hitRay;

    /**
     * Get the first hit of the ray with an object in the scene.
     * @param ray The sent out ray.
     * @param objects The objects in the scene.
     * @return Info about the first hit.
     */
    public HitInfo getFirstHit(Ray ray, List<Shape> objects){
        // Store original ray.
        Point origin = ray.start;
        Vector direction = ray.dir;

        // Initialize
        Double closestTime = null;

        // All intersections of ray with objects in the scene.
        for (Shape object : objects){

            // Specific ray for this object.
            Matrix inverseAT = object.getInverseAT();
            //Ray transformed = new Ray(
                    //inverseAT.times( origin ),
                    //inverseAT.times( direction )
            //);

            // Check for collisions.
            Double t = object.getCollidingT( ray );
            if ((t != null && t >= 0) && (closestTime == null || t <= closestTime) ){
                hitPoint = object.getATMatrix().times( ray.getPoint( t ));
                closestTime = t;
                hitObject = object;
                hitRay = ray;
            }
        }

        // There were no intersections.
        if (hitObject == null) {
            return null;
        } else {
            Matrix inverseAT = hitObject.getInverseAT();
            hitNormal = inverseAT.times(hitObject.getNormalVector(hitPoint));
            hitRay.recurseLevel = ray.recurseLevel;
        }
        return this;
    }

}
