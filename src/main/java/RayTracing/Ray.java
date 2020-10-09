package RayTracing;
import Matrix.*;

public class Ray {

    public Matrix start;
    public Matrix dir;

    /**
     * Create a ray object starting from a point
     * with a direction described by a vector.
     * @param point The starting point.
     * @param vector The ray's direction.
     */
    public Ray(Point point, Vector vector){
        start = point;
        dir = vector;
    }

}
