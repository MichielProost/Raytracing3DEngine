package RayTracing;

import Graphics.Screen;
import Matrix.*;

/**
 * Ray that starts at a point and goes in a certain direction.
 */
public class Ray {

    // The starting point of the ray.
    public Point start;

    // The direction of the ray.
    public Vector dir;

    // The recursion level starts at 0.
    public int recurseLevel = 0;

    /**
     * Default constructor.
     */
    public Ray(){}

    /**
     * Create a ray.
     * @param start The starting point.
     * @param dir The ray's direction.
     */
    public Ray(Point start, Vector dir){
        this.start = start;
        this.dir = dir;
    }

    /**
     * Set the starting point of the ray.
     * @param start The starting point.
     * @return This ray.
     */
    public Ray setStart(Point start){
        this.start = start;
        return this;
    }

    /**
     * Set the direction of the ray.
     * @param dir The ray's direction.
     */
    public void setDir(Vector dir){
        this.dir = dir;
    }

    /**
     * Compute the ray's direction.
     * @param screen The screen.
     * @param r The row of the rc-th pixel.
     * @param c The column of the rc-th pixel.
     * @return The ray's direction.
     */
    public Vector computeDirection(Screen screen, int r, int c){
        double W = screen.getW();
        double H = screen.getH();
        double W_half = W / 2;
        double H_half = H / 2;

        final double ux = -W_half + (2 * W_half * c) / screen.getWidth();
        final double uy = -H_half + (2 * H_half * r) / screen.getHeight();

        return new Vector( -ux, -uy, -screen.getN() );
    }

    /**
     * Get the intersection point of the ray given a hit time.
     * @param t The hit time.
     * @return The intersection point.
     */
    public Point getPoint(double t){
        return new Point(start.getX() + dir.getX() * t,
                            start.getY() + dir.getY() * t,
                                start.getZ() + dir.getZ() * t);
    }

}
