package RayTracing;

import Graphics.Screen;
import Matrix.*;
import Matrix.Point;
import Objects.Shape;
import Graphics.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that implements a camera in our 3D world.
 */
public class Cam {

    // The eye is located at point eye and looking at point look.
    private Point eye, look;
    private Vector up;
    // The camera base coordinates.
    private Vector u, v, n;
    // A matrix for converting the camera coordinates to the x,y,z coordinates.
    Matrix modelView;

    /**
     * Default constructor
     */
    public Cam(){
        eye = new Point();
        look = new Point();
        up = new Vector();
        u = new Vector(); v = new Vector(); n = new Vector();
        this.modelView = new Matrix(4,4);
    }

    /**
     * Initialize the camera's parameters.
     * @param eye The location of the eye.
     * @param look The point at which the eye is looking.
     * @param up The camera's upward direction.
     * @return This camera.
     */
    public Cam set(Point eye, Point look, Vector up){
        this.eye = eye;
        this.look = look;
        this.up = up;

        // Set the u,v a n coordinates.
        n = eye.minus(look);
        u = up.cross(n);
        n.normalize(); u.normalize();
        v = n.cross(u);

        // Set model view matrix.
        setModelViewMatrix();

        return this;
    }

    /**
     * Build the model view matrix.
     */
    private void setModelViewMatrix(){
        Vector minus_eye = eye.minus(new Point(0,0,0));
        this.modelView = new Identity(4)
                .put(0,0,u.getX()).put(0,1,u.getY()).put(0,2,u.getZ()).put(0,3,minus_eye.dot(u))
                .put(1,0,v.getX()).put(1,1,v.getY()).put(1,2,v.getZ()).put(1,3,minus_eye.dot(v))
                .put(2,0,n.getX()).put(2,1,n.getY()).put(2,2,n.getZ()).put(2,3,minus_eye.dot(n));
    }

    /**
     * Ray trace and update the current scene.
     * @param screen The screen.
     * @param objects The objects in the scene.
     */
    public void rayTrace(Screen screen, List<Shape> objects){

        // The ray starts at the eye.
        Ray ray = new Ray().setStart(eye);

        for (int c=0; c < screen.getWidth(); c++){
            for (int r = 0; r < screen.getHeight(); r++)
            {
                // Compute the ray's direction.
                Vector dir = ray.computeDirection(screen, r, c);

                // Built the rc-th ray.
                ray.setDir(modelView.times(dir));

                // Create a map that contains all the intersections of this ray with this pixel.
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

                // Continue if there were no intersections.
                if(closestObject == null) continue;

                // Compute the hit point where the ray hits the object, and the normal vector at that point.
                Point hit = ray.getPoint( closestTime );

                // Find the color of the light returning to the eye along the ray from the point of intersection.
                Rgb color = closestObject.getColor();

                // Place the color in the rc-th pixel.
                screen.drawPoint(r, c, color.r(), color.g(), color.b());
            }
        }
    }
}
