package Objects;

import Matrix.Point;
import RayTracing.Ray;
import Graphics.*;

import java.awt.*;

public abstract class Shape {

    protected Point location = new Point(0,0,0);
    protected Rgb color = new Rgb(1.0f, 1.0f, 1.0f);

    public abstract Double getCollidingT(Ray ray);

    public Rgb getColor() {
        return color;
    }
}
