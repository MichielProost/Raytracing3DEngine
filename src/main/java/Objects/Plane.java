package Objects;

import Matrix.Point;
import Matrix.Vector;
import RayTracing.Intersection;
import RayTracing.IntersectionMap;
import RayTracing.Ray;

/**
 * A generic plane that is defined by a normal vector and a point.
 */
public class Plane extends Shape {

    // The normal vector of the plane.
    Vector normal;

    // A point in the plane.
    Point point;

    /**
     * Create a new plane.
     * @param normal The normal vector of the plane.
     * @param point A point in the plane.
     */
    public Plane(Vector normal, Point point){
        super();
        this.normal = normal;
        this.point = point;
    }

    @Override
    public Intersection getClosestIntersection(Ray ray) {
        // The starting point of the ray.
        Point origin = ray.start;

        // The direction of the ray.
        Vector direction = ray.dir;

        // The vector between the origin of the ray and the point on the plane.
        Vector w = origin.minus( point );

        // Determine the time at which the intersection occurs.
        double product1 = w.dot( normal );
        double product2 = direction.dot( normal );
        double t = product1 / product2;
        if (t >= 0 ){
            return new Intersection(t, ray.getPoint( t ), normal);
        } else {
            return null;
        }
    }
}
