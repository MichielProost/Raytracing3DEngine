package Objects;

import Matrix.*;
import RayTracing.Ray;

/**
 * A generic sphere with a certain radius.
 */
public class Sphere extends Shape {

    private double radius = 1.0;  // The radius of the sphere.
    private Point location = new Point(0,0,0);  // The location of the sphere.

    /**
     * Default constructor.
     */
    public Sphere(){
        super();
    }

    /**
     * Set the radius of the sphere.
     * @param radius The radius of the sphere.
     * @return This sphere.
     */
    public Sphere setRadius(double radius){
        this.radius = radius;
        return this;
    }

    /**
     * Set the location of the sphere.
     * @param location The location of the sphere.
     * @return This sphere.
     */
    public Sphere setLocation(Point location){
        this.location = location;
        return this;
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
        Vector D = ray.dir;
        // The starting point of the ray.
        Vector S = ray.start.minus(this.location);

        double A = Math.pow(D.getMagnitude(),2);
        double B = S.dot(D);
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
