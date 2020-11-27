package Objects;

import Matrix.Point;
import Matrix.Vector;
import RayTracing.Ray;
import java.util.Arrays;

/**
 * A generic cylinder with a certain radius.
 */
public class Cylinder extends Shape {

    // The center of the cylinder.
    private Point center = new Point(0,0,0);

    // The radius of the cylinder.
    private double radius = 1.0;

    // The height of the cylinder in the z-direction.
    private double height = 1.0;

    // The normal vector of this cylinder.
    private Vector normal = new Vector();

    /**
     * Default constructor.
     */
    public Cylinder(){
        super();
    }

    public Cylinder setCenter(double x, double y, double z){
        this.center = new Point(x, y, z);
        return this;
    }

    public Cylinder setRadius(double radius){
        this.radius = radius;
        return this;
    }

    public Cylinder setHeight(double height){
        this.height = height;
        return this;
    }

    @Override
    public Vector getNormalVector(Point hit) {
        return normal;
    }

    @Override
    public Double getCollidingT(Ray ray) {

        // The direction of the ray.
        Vector D = ray.dir;
        // The starting point of the ray.
        Vector S = ray.start.minus(center);

        double A = Math.pow(D.getX(), 2) + Math.pow(D.getY(), 2);
        double B = 2 * ((D.getX() * S.getX()) + (D.getY() * S.getY()));
        double C = Math.pow(S.getX(), 2) + Math.pow(S.getY(), 2) - Math.pow(radius, 2);

        double discriminant = B * B - 4 * A * C;

        if (discriminant < 0){
            return null;
        }

        double t1 = Math.min((-B + Math.sqrt(discriminant)) / (2 * A), (-B - Math.sqrt(discriminant)) / (2 * A));
        double t2 = (height / 2.0 - S.getZ()) / D.getZ();
        double t3 = (-height / 2.0 - S.getZ()) / D.getZ();

        // Iterate through the values in sorted order so that we can find the closest intersection first.
        double[] tarr = {t1, t2, t3};
        Arrays.sort(tarr);

        // The lowest intersection.
        Double t = null;
        for (double x : tarr) {
            Point location = ray.start.plus(new Vector(D.getX() * x, D.getY() * x, D.getZ() * x));
            if (x == t1) {
                if (Math.abs(location.getZ() - center.getZ()) < height / 2.0) {
                    normal = new Vector(
                            location.getX() - center.getX(),
                            location.getY() - center.getY(),
                            0);
                    t = x;
                    break;
                }
            } else {
                if (Math.pow(location.getX() - center.getX(), 2)
                    + Math.pow(location.getY() - center.getY(), 2)
                    + Math.pow(radius, 2) <= 0) {
                    if (x == t2) {
                        normal = new Vector(0, 0, 1);
                    } else if (x == t3){
                        normal = new Vector(0, 0, -1);
                    }

                    t = x;
                    break;
                }
            }
        }

        if (t == null || t > 1 || t < 0) {
            return null;
        }

        return t;
    }
}
