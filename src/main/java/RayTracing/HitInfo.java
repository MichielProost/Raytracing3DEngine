package RayTracing;

import Matrix.Matrix;
import Matrix.Point;
import Matrix.Vector;
import Objects.Shape;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds info about the first hit between a ray and an object.
 */
public class HitInfo {

    // Time for closest intersection.
    public double closestTime;
    // Object involved in closest intersection.
    public Shape hitObject;
    // Point at which closest intersection occurs.
    public Point hitPoint;
    // Normal vector at the hit spot.
    public Vector hitNormal;
    // Specific ray for closest object.
    public Ray hitRay;

    /**
     * Get the first hit of the ray with an object in the scene.
     * @param ray The sent out ray.
     * @param objects The objects in the scene.
     * @return Info about the first hit.
     */
    public HitInfo getFirstHit(Ray ray, List<Shape> objects){

        // All intersections of ray with objects in the scene.
        Map<Double, Shape> intersections = new HashMap<>();
        for (Shape object : objects){

            // Specific ray for this object.
            //Matrix inverseAT = object.getInverseAT();
            //ray.setStart(inverseAT.times(ray.start));
            //ray.setDir(inverseAT.times(ray.dir));

            // Check for collisions.
            Double t = object.getCollidingT(ray);
            if (t != null && t >= 0){
                intersections.put(t, object);
            }
        }

        // Identify the intersection that lies closest to, and in front of, the eye.
        closestTime = -1;
        for( Map.Entry<Double, Shape> entry : intersections.entrySet()){
            if (closestTime == -1 || entry.getKey() < closestTime){
                closestTime = entry.getKey();
                hitObject = entry.getValue();
            }
        }

        // There were no intersections.
        if (hitObject == null){
            return null;
        // Compute other hit information.
        } else {
            hitPoint = ray.getPoint(closestTime);
            hitNormal = hitObject.getInverseAT().times(hitObject.getNormalVector(hitPoint));
            Matrix inverseAT = hitObject.getInverseAT();
            hitRay = new Ray().setStart(inverseAT.times(ray.start));
            hitRay.setDir(inverseAT.times(ray.dir));
        }

        return this;
    }

}
