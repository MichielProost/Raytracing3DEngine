package Objects;

import Matrix.Point;
import Matrix.Vector;
import RayTracing.Intersection;
import RayTracing.IntersectionMap;
import RayTracing.Ray;

/**
 * A generic box with a given size.
 */
public class Box extends Shape {

    // The size of the box.
    static final double size = 1.0;

    @Override
    public Intersection getClosestIntersection(Ray ray) {
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
        t = (size - origin.getX()) / direction.getX();
        location = ray.getPoint( t );
        if (t >= 0 && Math.abs(location.getY()) <= size && Math.abs(location.getZ()) <= size){
            handler.addIntersection(
                    new Intersection(t, location, new Vector(1, 0, 0))
            );
        }

        // PLANE X = -1
        // ------------
        t = (-size - origin.getX()) / direction.getX();
        location = ray.getPoint( t );
        if (t >= 0 && Math.abs(location.getY()) <= size && Math.abs(location.getZ()) <= size)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(-1, 0, 0))
            );

        // PLANE Y = 1
        // -----------
        t = (size - origin.getY()) / direction.getY();
        location = ray.getPoint( t );
        if (t >= 0 && Math.abs(location.getX()) <= size && Math.abs(location.getZ()) <= size)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(0, 1, 0))
            );

        // PLANE Y = -1
        // ------------
        t = (-size - origin.getY()) / direction.getY();
        location = ray.getPoint( t );
        if (t >= 0 && Math.abs(location.getX()) <= size && Math.abs(location.getZ()) <= size)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(0, -1, 0))
            );

        // PLANE Z = 1
        // -----------
        t = (size - origin.getZ()) / direction.getZ();
        location = ray.getPoint( t );
        if (t >= 0 && Math.abs(location.getX()) <= size && Math.abs(location.getY()) <= size)
            handler.addIntersection(
                    new Intersection(t, location, new Vector(0, 0, 1))
            );

        // PLANE Z = -1
        // ------------
        t = (-size - origin.getZ()) / direction.getZ();
        location = ray.getPoint( t );
        if (t >= 0 && Math.abs(location.getX()) <= size && Math.abs(location.getY()) <= size)
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
