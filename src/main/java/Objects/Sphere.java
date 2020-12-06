package Objects;

import Matrix.*;
import RayTracing.Hit;
import RayTracing.Ray;

/**
 * A generic sphere with a certain radius.
 */
public class Sphere extends Shape {

    @Override
    public Hit getClosestHit(Ray ray) {

        // The starting point of the ray.
        Vector origin = new Vector(ray.start.getX(), ray.start.getY(), ray.start.getZ());
        // The direction of the ray.
        Vector direction = ray.dir;

        double A = Math.pow( direction.getMagnitude() , 2 );
        double B = origin.dot(direction);
        double C = Math.pow( origin.getMagnitude(), 2 ) - 1;

        double D = Math.pow(B, 2) - (A * C);

        if(D < 0){
            return null;
        }

        double t1 = (-B + Math.sqrt(D)) / (2 * A);
        double t2 = (-B - Math.sqrt(D)) / (2 * A);

        if (t1 < 0 && t2 < 0)
            return null;
        else if (t1 < 0)
            return new Hit (
                    t2,
                    ray.getPoint(t2),
                    new Vector( ray.getPoint( t2 ).getX(), ray.getPoint( t2 ).getY() , ray.getPoint( t2 ).getZ()));
        else if (t2 < 0)
            return new Hit (
                    t1,
                    ray.getPoint(t1),
                    new Vector( ray.getPoint( t1 ).getX(), ray.getPoint( t1 ).getY() , ray.getPoint( t1 ).getZ()));
        else {
            double t = Math.min(t1, t2);
            return new Hit (
                    t,
                    ray.getPoint(t),
                    new Vector( ray.getPoint( t ).getX(), ray.getPoint( t ).getY() , ray.getPoint( t ).getZ()));
        }
    }
}
