package Objects;

import Matrix.*;
import RayTracing.Ray;


public class Cone extends Shape {

    private Vector V;   // Its axis in the direction of increasing radius.
    private double phi; // The half angle between the axis and the surface.

    public Cone(Vector V, double phi){
        this.V = V;
        this.phi = phi;
    }

    @Override
    public Double getCollidingT(Ray ray) {
        // The direction of the ray.
        Vector D = ray.dir;
        // The origin of the ray.
        Point O = ray.start;

        // The tip of the cone.
        Point T = this.location;

        Vector OT = O.minus(T);
        double cos_squared = Math.pow(Math.cos(phi),2);

        double A = Math.pow(D.dot(V), 2) - cos_squared;
        double B = 2 * (((D.dot(V)) * (OT.dot(V))) - (D.dot(OT) * cos_squared));
        double C = Math.pow(OT.dot(V), 2) - (OT.dot(OT) * cos_squared);

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
