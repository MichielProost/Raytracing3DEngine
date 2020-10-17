package Objects;

import Matrix.Point;
import RayTracing.Ray;
import Graphics.*;

/**
 * The class that represents an object or shape in 3D space.
 */
public abstract class Shape {

    // The location of the object.
    protected Point location = new Point(0,0,0);

    // The color of the object.
    protected Rgb color = new Rgb(1.0f, 1.0f, 1.0f);

    /**
     * Get the hit time between this object and a given ray.
     * @param ray The given ray.
     * @return The closest value of t. Returns null if no hit points are found.
     */
    public abstract Double getCollidingT(Ray ray);

    /**
     * Get the color of this object.
     * @return The color of this object.
     */
    public Rgb getColor() {
        return color;
    }

    /**
     * Get the location of this object.
     * @return The location of this object.
     */
    public Point getLocation() {
        return location;
    }
}
