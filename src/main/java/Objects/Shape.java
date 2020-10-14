package Objects;

import Matrix.Point;
import RayTracing.Ray;

import java.awt.*;

public abstract class Shape {

    protected Point location;
    protected Color color = Color.GREEN;

    public abstract boolean isColliding(Ray ray);

    public Color getColor() {
        return color;
    }
}
