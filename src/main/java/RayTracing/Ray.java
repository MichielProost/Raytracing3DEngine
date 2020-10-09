package RayTracing;
import Matrix.*;

public class Ray {

    public Matrix start;    // The starting point of the vector.
    public Matrix dir;      // The direction of the vector.

    /**
     * Create a ray object starting from a point
     * with a direction described by a vector.
     * @param point The starting point.
     * @param vector The ray's direction.
     */
    public Ray(Matrix point, Matrix vector){
        start = point;
        dir = vector;
    }

}
