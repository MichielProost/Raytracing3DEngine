package Objects;

import Matrix.Point;
import Matrix.Vector;
import RayTracing.Intersection;
import RayTracing.IntersectionMap;
import RayTracing.Ray;
import static Utils.Constants.BOX_SIZE;
import static Utils.Constants.BOX_EPS;

/**
 * A generic box with a given BOX_SIZE.
 */
public class Box extends Shape {

    @Override
    public Intersection getClosestIntersection(Ray ray) {
        // Create intersection map.
        IntersectionMap handler = new IntersectionMap();

        // The plane.
        Plane plane;

        // The intersection.
        Intersection intersection;

        // The normal vector.
        Vector normal;

        // PLANE X = 1
        // -----------
        normal = new Vector(1, 0, 0);
        plane = new Plane( normal, new Point(BOX_SIZE, 0, 0));
        intersection = plane.getClosestIntersection( ray );
        if ((intersection != null) && (Math.abs(intersection.getLocation().getY()) <= BOX_SIZE && Math.abs(intersection.getLocation().getZ()) <= BOX_SIZE)){
            handler.addIntersection(
                    new Intersection(intersection.getTime(), intersection.getLocation(), normal)
            );
        }

        // PLANE X = -1
        // ------------
        normal = new Vector(-1, 0, 0);
        plane = new Plane( normal, new Point(-BOX_SIZE, 0, 0));
        intersection = plane.getClosestIntersection( ray );
        if ((intersection != null) && (Math.abs(intersection.getLocation().getY()) <= BOX_SIZE && Math.abs(intersection.getLocation().getZ()) <= BOX_SIZE)){
            handler.addIntersection(
                    new Intersection(intersection.getTime(), intersection.getLocation(), normal)
            );
        }

        // PLANE Y = 1
        // -----------
        normal = new Vector(0, 1, 0);
        plane = new Plane( normal, new Point(0, BOX_SIZE, 0));
        intersection = plane.getClosestIntersection( ray );
        if ((intersection != null) && (Math.abs(intersection.getLocation().getX()) <= BOX_SIZE && Math.abs(intersection.getLocation().getZ()) <= BOX_SIZE)){
            handler.addIntersection(
                    new Intersection(intersection.getTime(), intersection.getLocation(), normal)
            );
        }

        // PLANE Y = -1
        // ------------
        normal = new Vector(0, -1, 0);
        plane = new Plane( normal, new Point(0, -BOX_SIZE, 0));
        intersection = plane.getClosestIntersection( ray );
        if ((intersection != null) && (Math.abs(intersection.getLocation().getX()) <= BOX_SIZE && Math.abs(intersection.getLocation().getZ()) <= BOX_SIZE)){
            handler.addIntersection(
                    new Intersection(intersection.getTime(), intersection.getLocation(), normal)
            );
        }

        // PLANE Z = 1
        // -----------
        normal = new Vector(0, 0, 1);
        plane = new Plane( normal, new Point(0, 0, BOX_SIZE));
        intersection = plane.getClosestIntersection( ray );
        if ((intersection != null) && (Math.abs(intersection.getLocation().getX()) <= BOX_SIZE && Math.abs(intersection.getLocation().getY()) <= BOX_SIZE)){
            handler.addIntersection(
                    new Intersection(intersection.getTime(), intersection.getLocation(), normal)
            );
        }

        // PLANE Z = -1
        // ------------
        normal = new Vector(0, 0, -1);
        plane = new Plane( normal, new Point(0, 0, -BOX_SIZE));
        intersection = plane.getClosestIntersection( ray );
        if ((intersection != null) && (Math.abs(intersection.getLocation().getX()) <= BOX_SIZE && Math.abs(intersection.getLocation().getY()) <= BOX_SIZE)){
            handler.addIntersection(
                    new Intersection(intersection.getTime(), intersection.getLocation(), normal)
            );
        }

        // Return the closest intersection.
        if (handler.isEmpty()){
            return null;
        } else {
            return handler.getClosestIntersection();
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

    @Deprecated
    public Intersection getClosestIntersection(Ray ray, double parameter) {
        // Create intersection map.
        IntersectionMap handler = new IntersectionMap();

        // The starting point of the ray.
        Point origin = ray.start;

        // The direction of the ray.
        Vector direction = ray.dir;

        // The time at which the intersection occurs.
        double t;

        // The location at which the intersection occurs.
        Point location;

        // PLANE X = 1
        // -----------
        t = (BOX_SIZE - origin.getX()) / direction.getX();
        location = ray.getPoint( t );
        if (t >= BOX_EPS && Math.abs(location.getY()) <= BOX_SIZE && Math.abs(location.getZ()) <= BOX_SIZE){
            handler.addIntersection(
                    new Intersection(t, location, new Vector(1, 0, 0))
            );
        }

        // PLANE X = -1
        // ------------
        t = (-BOX_SIZE - origin.getX()) / direction.getX();
        location = ray.getPoint( t );
        if (t >= BOX_EPS && Math.abs(location.getY()) <= BOX_SIZE && Math.abs(location.getZ()) <= BOX_SIZE)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(-1, 0, 0))
            );

        // PLANE Y = 1
        // -----------
        t = (BOX_SIZE - origin.getY()) / direction.getY();
        location = ray.getPoint( t );
        if (t >= BOX_EPS && Math.abs(location.getX()) <= BOX_SIZE && Math.abs(location.getZ()) <= BOX_SIZE)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(0, 1, 0))
            );

        // PLANE Y = -1
        // ------------
        t = (-BOX_SIZE - origin.getY()) / direction.getY();
        location = ray.getPoint( t );
        if (t >= BOX_EPS && Math.abs(location.getX()) <= BOX_SIZE && Math.abs(location.getZ()) <= BOX_SIZE)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(0, -1, 0))
            );

        // PLANE Z = 1
        // -----------
        t = (BOX_SIZE - origin.getZ()) / direction.getZ();
        location = ray.getPoint( t );
        if (t >= BOX_EPS && Math.abs(location.getX()) <= BOX_SIZE && Math.abs(location.getY()) <= BOX_SIZE)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(0, 0, 1))
            );

        // PLANE Z = -1
        // ------------
        t = (-BOX_SIZE - origin.getZ()) / direction.getZ();
        location = ray.getPoint( t );
        if (t >= BOX_EPS && Math.abs(location.getX()) <= BOX_SIZE && Math.abs(location.getY()) <= BOX_SIZE)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(0, 0, -1))
            );

        // Return the closest intersection.
        if (handler.isEmpty()){
            return null;
        } else {
            return handler.getClosestIntersection();
        }
    }

}
