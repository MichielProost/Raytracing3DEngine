package Objects;

import Matrix.*;
import RayTracing.Intersection;
import RayTracing.IntersectionMap;
import RayTracing.Ray;
import static Utils.Constants.*;

/**
 * A generic sphere.
 */
public class Sphere extends Shape {

    @Override
    public Intersection getClosestIntersection(Ray ray) {
        // Create intersection map.
        IntersectionMap handler = new IntersectionMap();

        // The starting point of the ray.
        Point origin = ray.start;

        // The direction of the ray.
        Vector direction = ray.dir;

        // Calculate coefficients.
        double A = direction.dot( direction );
        double B = direction.dot( origin );
        double C = Math.pow(origin.getMagnitude(), 2) - 1;

        // Calculate discriminant.
        double D = Math.pow(B, 2) - (A * C);

        // There are no intersections if the discriminant is smaller than zero.
        if (D < 0){
            return null;
        }

        // Times at which the intersections occur.
        double t1 = (-B + Math.sqrt(D)) / A;
        double t2 = (-B - Math.sqrt(D)) / A;

        // Add valid intersections.
        if (t1 >= SPHERE_EPS) {
            Point location = ray.getPoint( t1 );
            handler.addIntersection(
                    new Intersection(t1, location, new Vector(location.getX(), location.getY(), location.getZ()))
            );
        }
        if (t2 >= SPHERE_EPS) {
            Point location = ray.getPoint( t2 );
            handler.addIntersection(
                    new Intersection(t2, location, new Vector(location.getX(), location.getY(), location.getZ()))
            );
        }

        return handler.getClosestIntersection();
    }

    @Override
    public double getPixelX(Point hitLocation) {
        double angle = Math.atan2(-hitLocation.getZ(), hitLocation.getX());
        return (angle + Math.PI) / (2 * Math.PI);
    }

    @Override
    public double getPixelY(Point hitLocation) {
        double angle = Math.acos(-hitLocation.getY());
        return angle / Math.PI;
    }

}
