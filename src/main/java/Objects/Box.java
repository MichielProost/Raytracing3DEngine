package Objects;

import Matrix.Point;
import Matrix.Vector;
import RayTracing.Ray;

/**
 * A generic box with a minimum and maximum point.
 */
public class Box extends Shape {

    private Point minimum;
    private Point maximum;

    /**
     * Default constructor.
     * @param minimum The minimum extend of the bounding box.
     * @param maximum The maximum extend of the bounding box.
     */
    public Box(Point minimum, Point maximum){
        super();
        this.minimum = minimum;
        this.maximum = maximum;
    }

    /**
     * Constructor to set the location of the box as well.
     * @param minimum The minimum extend of the bounding box.
     * @param maximum The maximum extend of the bounding box.
     * @param location The location of the box.
     */
    public Box(Point minimum, Point maximum, Point location){
        this.minimum = minimum;
        this.maximum = maximum;
        super.setLocation(location);
    }

    @Override
    public Double getCollidingT(Ray ray) {
        // The direction of the ray.
        Vector D = ray.dir;
        // The starting point of the ray.
        Vector S = ray.start.minus(this.location);

        Vector invDir = new Vector(1 / D.getX(), 1 / D.getY(), 1 / D.getZ());

        boolean signDirX = invDir.getX() < 0;
        boolean signDirY = invDir.getY() < 0;
        boolean signDirZ = invDir.getZ() < 0;

        Point bbox = signDirX ? maximum : minimum;
        double tmin = (bbox.getX() - S.getX()) * invDir.getX();
        bbox = signDirX? minimum : maximum;
        double tmax = (bbox.getX() - S.getX()) * invDir.getX();
        bbox = signDirY ? maximum : minimum;
        double tymin = (bbox.getY() - S.getY()) * invDir.getY();
        bbox = signDirY ? minimum : maximum;
        double tymax = (bbox.getY() - S.getY()) * invDir.getY();

        if ((tmin > tymax) || (tymin > tmax)) {
            return null;
        }
        if (tymin > tmin) {
            tmin = tymin;
        }
        if (tymax < tmax) {
            tmax = tymax;
        }

        bbox = signDirZ ? maximum : minimum;
        double tzmin = (bbox.getZ() - S.getZ()) * invDir.getZ();
        bbox = signDirZ ? minimum : maximum;
        double tzmax = (bbox.getZ() - S.getZ()) * invDir.getZ();

        if ((tmin > tzmax) || (tzmin > tmax)){
            return null;
        }
        if (tzmax < tmin) {
            tmin = tzmin;
        }
        if (tzmax < tmax) {
            tmax = tzmax;
        }
        if ((tmin < 1) && (tmax > 0)) {
            return tmin;
        }
        return null;
    }
}
