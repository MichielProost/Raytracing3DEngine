package Objects;

import Matrix.*;
import RayTracing.Intersection;
import RayTracing.IntersectionMap;
import RayTracing.Ray;

/**
 * A generic sphere with a certain radius.
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
        double A = Math.pow( direction.getMagnitude() , 2 );
        double B = origin.dot( direction );
        double C = Math.pow( origin.getMagnitude(), 2 ) - 1;

        // Calculate discriminant.
        double D = Math.pow(B, 2) - (A * C);

        // Times at which the intersections occur.
        double t1 = (-B + Math.sqrt(D)) / (2 * A);
        double t2 = (-B - Math.sqrt(D)) / (2 * A);

        // There are no intersections if the discriminant is smaller than zero.
        if (D < 0){
            return null;
        }

        // Add valid intersections.
        if (t1 >= 0) {
            Point location = ray.getPoint( t1 );
            handler.addIntersection(
                    new Intersection(t1, location, new Vector(location.getX(), location.getY(), location.getZ()))
            );
        }
        if (t2 >= 0) {
            Point location = ray.getPoint( t2 );
            handler.addIntersection(
                    new Intersection(t2, location, new Vector(location.getX(), location.getY(), location.getZ()))
            );
        }

        return handler.getClosestIntersection();
    }

}
