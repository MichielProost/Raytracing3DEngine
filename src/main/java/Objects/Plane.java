package Objects;

import Matrix.Point;
import Matrix.Vector;
import RayTracing.Intersection;
import RayTracing.Ray;
import static Utils.Constants.PLANE_EPS;

/**
 * A generic plane that is defined by a normal vector and a point.
 */
public class Plane extends Shape {

    // The normal vector of the plane.
    Vector normal;

    // A point in the plane.
    Point point;

    // Whether the plane is infinite or not.
    boolean infinite = true;

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

    /**
     * Make the plane finite.
     * @return This plane.
     */
    public Plane setFinite(){
        infinite = false;
        return this;
    }

    @Override
    public Intersection getClosestIntersection(Ray ray) {
        // The starting point of the ray.
        Point origin = ray.start;

        // The direction of the ray.
        Vector direction = ray.dir;

        // The vector between the origin of the ray and the point on the plane.
        Vector w = point.minus( origin );

        // Determine the time at which the intersection occurs.
        double product1 = w.dot( normal );
        double product2 = direction.dot( normal );
        double t = product1 / product2;

        // Create condition based on finite or infinite ray.
        boolean condition = (t >= PLANE_EPS);
        if (!infinite){
            condition = condition && (t < 1.0);
        }

        // Return intersection if it exists.
        if (condition){
            return new Intersection(t, ray.getPoint( t ), normal);
        } else {
            return null;
        }

    }

    @Override
    public double getPixelX(Point hitLocation) {
        return 0;
    }

    @Override
    public double getPixelY(Point hitLocation) {
        return 0;
    }
}
