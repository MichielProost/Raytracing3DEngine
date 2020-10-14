package RayTracing;
import Graphics.Screen;
import Matrix.*;

public class Ray {

    public Point start;    // The starting point of the ray.
    public Vector dir;     // The direction of the ray.

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

    public Vector computeDirection(Screen screen, int r, int c){
        Vector dir = new Vector();
        dir.setX(-screen.getX());
        dir.setY((double) screen.getWidth() * (((2 * (double) c) / (double) screen.getnColumns()) - 1));
        dir.setZ((double) screen.getHeight() * (((2 * (double) r) / (double) screen.getnRows()) - 1));
        return dir;
    }

    public Point getPoint(double t){
        Point point = new Point(start.getX() + dir.getX() * t,
                                start.getY() + dir.getY() * t,
                                start.getZ() + dir.getZ() * t);
        return point;
    }

}
