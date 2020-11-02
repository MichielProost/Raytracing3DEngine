package Objects;

import Light.Material;
import Matrix.*;
import RayTracing.Ray;

/**
 * A generic sphere with a certain radius.
 */
public class Sphere extends Shape {

    // The radius of the sphere.
    private double radius;

    /**
     * Default constructor.
     * @param radius The radius of the sphere.
     */
    public Sphere(double radius){
        super();
        this.radius = radius;
    }

    /**
     * Constructor to set the sphere's location as well.
     * @param radius The radius of the sphere.
     * @param location The location of the sphere.
     */
    public Sphere(double radius, Point location){
        super(location);
        this.radius = radius;
    }

    @Override
    public Vector getNormalVector(Point hit){
        return new Vector(  (hit.getX() - this.location.getX()) / this.radius,
                            (hit.getY() - this.location.getY()) / this.radius,
                            (hit.getZ() - this.location.getZ()) / this.radius);
    }

    @Override
    public Double getCollidingT(Ray ray) {
        // The direction of the ray.
        Vector c = ray.dir;
        // The starting point of the ray.
        Vector S = ray.start.minus(this.location);

        double A = Math.pow(c.getMagnitude(),2);
        double B = S.dot(c);
        double C = Math.pow(S.getMagnitude(), 2) - Math.pow(radius, 2);

        double discriminant = Math.pow(B, 2) - (A * C);

        if(discriminant < 0){
            return null;
        }

        double t1 = (-B + Math.sqrt(discriminant)) / (2 * A);
        double t2 = (-B - Math.sqrt(discriminant)) / (2 * A);

        if (t1 < 0 && t2 < 0)
            return null;
        else if (t1 < 0)
            return t2;
        else if (t2 < 0)
            return t1;
        else
            return Math.min(t1, t2);
    }
}
