package Objects;

import Matrix.Point;
import Matrix.Vector;
import RayTracing.Intersection;
import RayTracing.IntersectionMap;
import RayTracing.Ray;

import static Utils.Constants.CYLINDER_EPS;

/**
 * A generic cylinder.
 */
public class Cylinder extends Shape{

    @Override
    public Intersection getClosestIntersection(Ray ray) {
        // Create intersection map.
        IntersectionMap handler = new IntersectionMap();

        // The starting point of the ray.
        Point origin = ray.start;

        // The direction of the ray.
        Vector direction = ray.dir;

        // CYLINDER WALLS: X²+Y²=1 and |Z|<=1
        // ----------------------------------
        // Calculate coefficients.
        double A = Math.pow( direction.getX(), 2) + Math.pow(direction.getY(), 2 );
        double B = 2 * ( origin.getX() * direction.getX() + origin.getY() * direction.getY() );
        double C = Math.pow( origin.getX(), 2 ) + Math.pow( origin.getY(), 2) - 1;

        // Calculate discriminant.
        double D = Math.pow(B, 2) - (4 * A * C);

        // Times at which the intersections occur.
        double t1 = (-B + Math.sqrt(D)) / (2 * A);
        double t2 = (-B - Math.sqrt(D)) / (2 * A);

        if ( t1 >= CYLINDER_EPS && betweenCaps(ray, t1) ){
            Point location = ray.getPoint( t1 );
            handler.addIntersection(
                    new Intersection( t1, location, new Vector(location.getX(), location.getY(), 0))
            );
        }
        if ( t2 >= CYLINDER_EPS && betweenCaps(ray, t2) ){
            Point location = ray.getPoint( t2 );
            handler.addIntersection(
                    new Intersection( t2, location, new Vector(location.getX(), location.getY(), 0))
            );
        }

        // CYLINDER CAPS: |Z|=1, X²+Y²<1
        // -----------------------------
        // Times at which the intersection occur.
        double t3 = (1 - origin.getZ()) / direction.getZ();
        double t4 = (-1 - origin.getZ()) / direction.getZ();

        // Plane Y = 1.
        if ( t3 >= 0 && betweenWalls(ray, t3) ){
            Point location = ray.getPoint( t3 );
            handler.addIntersection(
                    new Intersection( t3, location, new Vector(0, 0, 1) )
            );
        }
        // Plane Y = -1.
        if ( t4 >= 0 && betweenWalls(ray, t4) ){
            Point location = ray.getPoint( t4 );
            handler.addIntersection(
                    new Intersection( t4, location, new Vector(0, 0, -1) )
            );
        }

        // Return the closest intersection.
        if (handler.isEmpty()){
            return null;
        } else {
            return handler.getClosestIntersection();
        }
    }

    /**
     * Check if the intersection is between the cylinder caps.
     * @param ray The ray.
     * @param t The time at which the intersection occurs.
     * @return If the intersection is between the cylinder caps.
     */
    private boolean betweenCaps(Ray ray, double t){
        double z = ray.start.getZ() + t * ray.dir.getZ();
        return Math.abs(z) <= 1;
    }

    private boolean betweenWalls(Ray ray, double t){
        double x = ray.start.getX() + t * ray.dir.getX();
        double y = ray.start.getY() + t * ray.dir.getY();
        return ( Math.pow(x, 2) + Math.pow(y,2) ) <= 1;
    }
}
