package RayTracing;

import Graphics.Screen;
import Matrix.*;
import Matrix.Point;
import Objects.Shape;
import Graphics.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cam {

    private Point eye, look;
    private Vector up;
    private Vector u, v, n;
    Matrix modelView;

    /**
     * Default constructor
     */
    public Cam(){
        eye = new Point();
        look = new Point();
        up = new Vector();
        u = new Vector(); v = new Vector(); n = new Vector();
        Matrix modelView = new Matrix(4,4);
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
        this.modelView = new Identity(4)
                .put(0,0,u.getX()).put(0,1,u.getY()).put(0,2,u.getZ()).put(0,3,minus_eye.dot(u))
                .put(1,0,v.getX()).put(1,1,v.getY()).put(1,2,v.getZ()).put(1,3,minus_eye.dot(v))
                .put(2,0,n.getX()).put(2,1,n.getY()).put(2,2,n.getZ()).put(2,3,minus_eye.dot(n));
    }

    public void rayTrace(Screen screen, List<Shape> objects){
        Ray ray = new Ray();
        ray.setStart(eye);
        int i = 0;
        for (int c=0; c < screen.getWidth(); c++){
            for (int r = 0; r < screen.getHeight(); r++)
            {
                // Compute the ray's direction.
                Vector dir = ray.computeDirection(screen, r, c);

                // Built the rc-th ray.
                ray.setDir(dir);

                Map<Double, Shape> intersections = new HashMap<>();

                // Find all the intersections of ray with objects in the scene.
                for (Shape object : objects){
                    Double t = object.getCollidingT(ray);
                    if (t != null && t >= 0){
                        intersections.put(t, object);
                    }
                }

                // Identify the intersection that lies closest to, and in front of, the eye.
                double closestTime = -1;
                Shape closestObject = null;
                for( Map.Entry<Double, Shape> entry : intersections.entrySet()){
                    if (closestTime == -1 || entry.getKey() < closestTime){
                        closestTime = entry.getKey();
                        closestObject = entry.getValue();
                    }
                }

                if(closestObject == null) continue;
                // Compute the hit point where the ray hits the object, and the normal vector at that point.
                // Find the color of the light returning to the eye along the ray from the point of intersection.
                Rgb color = closestObject.getColor();
                // Place the color in the rc-th pixel.
                screen.drawPoint(r, c, color.r(), color.g(), color.b());
            }
        }
    }
}
