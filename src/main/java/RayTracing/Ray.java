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
        double W = screen.getW();
        double H = screen.getH();
        double W_half = W / 2;
        double H_half = H / 2;

        final double ux = -W_half + (W * r) / screen.getWidth();
        final double uy = -H_half + (H * c) / screen.getHeight();

        Vector dir = new Vector(-ux, -uy, -screen.getN());
        return dir;
    }

    public Point getPoint(double t){
        Point point = new Point(start.getX() + dir.getX() * t,
                                start.getY() + dir.getY() * t,
                                start.getZ() + dir.getZ() * t);
        return point;
    }

}
