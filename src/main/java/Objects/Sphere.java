package Objects;

import Matrix.*;
import RayTracing.Ray;

public class Sphere extends Shape {

    private double radius;

    public Sphere(double radius){
        this.radius = radius;
    }

    @Override
    public Double getCollidingT(Ray ray) {
        // The direction of the ray.
        Vector c = ray.dir;
        // The starting point of the ray.
        Point S = ray.start;

        double A = Math.pow(c.getMagnitude(),2);
        double B = S.dot(c);
        double C = Math.pow(S.getMagnitude(), 2) - 1;

        double discriminant = Math.pow(B, 2) - (A * C);

        if(discriminant < 0){
            return null;
        }

        double t1 = (-B + Math.sqrt(discriminant)) / A;
        double t2 = (-B - Math.sqrt(discriminant)) / A;

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
