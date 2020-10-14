package RayTracing;
import Matrix.*;

public class Ray {

    public Point start;    // The starting point of the ray.
    public Matrix dir;     // The direction of the ray.

    /**
     * Set the starting point of the ray.
     * @param start The starting point.
     */
    public void setStart(Point start){
        this.start = start;
    }

    /**
     * Set the direction of the ray.
     * @param dir The ray's direction.
     */
    public void setDir(Vector dir){
        this.dir = dir;
    }

}
