package Objects;

import Matrix.Point;
import Matrix.Vector;
import RayTracing.Hit;
import RayTracing.Ray;
import java.util.HashMap;
import java.util.Map;

/**
 * A generic box with a given size.
 */
public class Box extends Shape {

    // The size of the box.
    static final Double size = 1.0;

    @Override
    public Hit getClosestHit(Ray ray) {
        // The starting point of the ray.
        Point origin = ray.start;
        // The direction of the ray.
        Vector direction = ray.dir;

        // Hold all hits with object.
        Map<Double, Vector> hits = new HashMap<>();

        double t;
        Point location;

        // PLANE X = 1
        t = (size - origin.getX()) / direction.getX();
        location = ray.getPoint(t);
        if (t >= 0 && Math.abs(location.getY()) <= size && Math.abs(location.getZ()) <= size){
            hits.put( t, new Vector(0, 0, 1) );
        }

        // PLANE X = -1
        t = (-size - origin.getX()) / direction.getX();
        location = ray.getPoint(t);
        if (t >= 0 && Math.abs(location.getY()) <= size && Math.abs(location.getZ()) <= size)
            hits.put(t, new Vector(-1, 0, 0));

        // PLANE Y = 1
        t = (size - origin.getY()) / direction.getY();
        location = ray.getPoint(t);
        if (t >= 0 && Math.abs(location.getX()) <= size && Math.abs(location.getZ()) <= size)
            hits.put(t, new Vector(0, 1, 0));

        // PLANE Y = -1
        t = (-size - origin.getY()) / direction.getY();
        location = ray.getPoint(t);
        if (t >= 0 && Math.abs(location.getX()) <= size && Math.abs(location.getZ()) <= size)
            hits.put(t, new Vector(0, -1, 0));

        // PLANE Z = 1
        t = (size - origin.getZ()) / direction.getZ();
        location = ray.getPoint(t);
        if (t >= 0 && Math.abs(location.getX()) <= size && Math.abs(location.getY()) <= size)
            hits.put(t, new Vector(0, 0, 1));

        // PLANE Z = -1
        t = (-size - origin.getZ()) / direction.getZ();
        location = ray.getPoint(t);
        if (t >= 0 && Math.abs(location.getX()) <= size && Math.abs(location.getY()) <= size)
            hits.put(t, new Vector(0, 0, -1));

        // Find the closest hit.
        Double lowestT = null;
        for (Map.Entry<Double, Vector> hit : hits.entrySet()) {
            if (lowestT == null || hit.getKey() < lowestT)
            {
                lowestT = hit.getKey();
            }
        }

        // Return closest hit.
        if (lowestT != null){
            return new Hit(
                    lowestT,
                    ray.getPoint(lowestT),
                    hits.get(lowestT)
            );
        // Return null if no hits were found.
        } else {
            return null;
        }

    }
}
