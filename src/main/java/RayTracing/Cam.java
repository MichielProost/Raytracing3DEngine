package RayTracing;

import Graphics.Screen;
import Matrix.*;

public class Cam {

    private Point eye, look;
    private Vector up;
    private Vector u, v, n;

    /**
     * Default constructor
     */
    public Cam(){
        eye = new Point();
        look = new Point();
        up = new Vector();
        u = new Vector(); v = new Vector(); n = new Vector();
    }

    public Cam set(Point eye, Point look, Vector up){
        this.eye = eye;
        this.look = look;
        this.up = up;

        n = eye.minus(look);
        u = up.cross(n);

        n.normalize(); u.normalize();

        v = n.cross(u);

        setModelViewMatrix();

        return this;
    }

    private void setModelViewMatrix(){
        Vector minus_eye = eye.minus(new Point(0,0,0));
        Matrix model = new Identity(4)
                .put(0,0,u.getX()).put(0,1,u.getY()).put(0,2,u.getZ()).put(0,3,minus_eye.dot(u))
                .put(1,0,v.getX()).put(1,1,v.getY()).put(1,2,v.getZ()).put(1,3,minus_eye.dot(v))
                .put(2,0,n.getX()).put(2,1,n.getY()).put(2,2,n.getZ()).put(2,3,minus_eye.dot(n));
    }

    public void rayTrace(Screen screen){
        Ray ray = new Ray();
        ray.setStart(eye);
        for (int r=0; r < screen.getnRows(); r++){
            for (int c = 0; c < screen.getnColumns(); c++)
            {
                // Compute the ray's direction.
                Vector dir = ray.computeDirection(screen, r, c);
                // Built the rc-th ray.
                ray.setDir(dir);
                System.out.println(dir);
                // Find all the intersections of ray with objects in the scene.
                // Identify the intersection that lies closest to, and in front of, the eye.
                // Compute the hit point where the ray hits the object, and the normal vector at that point.
                // Find the color of the light returning to the eye along the ray from the point of intersection.
                // Place the color in the rc-th pixel.
            }
        }
    }
}
