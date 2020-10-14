package Objects;

import Matrix.*;
import RayTracing.Ray;

public class Sphere extends Shape {

    private double radius;

    public Sphere(double radius){
        this.radius = radius;
    }

    @Override
    public boolean isColliding(Ray ray) {
        // The direction of the ray.
        Vector c = ray.dir;
        // The starting point of the ray.
        Point S = ray.start;
        return false;
    }
}
