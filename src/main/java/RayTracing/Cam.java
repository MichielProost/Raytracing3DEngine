package RayTracing;

import Graphics.Screen;
import Light.LightSource;
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

    /**
     * The camera can be controlled in a variety of ways:
     * Translation, roll, pitch and rotation.
     * This allows us to use the same keys on the keyboard depending on the state.
     */
    public enum ControlState {

        TRANSLATION,
        ROLL;
        private static ControlState[] values = values();

        /**
         * Get the next control state.
         * @return The next control state.
         */
        public ControlState next(){
            return values[(this.ordinal()+1) % values.length];
        }
    }

    // The eye is located at point "eye" and looking at point "look".
    private Point eye, look;
    private Vector up;

    // The camera coordinates.
    private Vector u, v, n;

    // A matrix for converting the camera coordinates to world coordinates (x, y, z).
    Matrix modelView;

    // The control state of this camera.
    public ControlState controlState;

    /**
     * Default constructor
     */
    public Cam(){
        eye = new Point();
        look = new Point();
        up = new Vector();
        u = new Vector(); v = new Vector(); n = new Vector();
        this.modelView = new Matrix(4,4);
        controlState = ControlState.TRANSLATION;
    }

    /**
     * Set the control state of this camera to the next one.
     */
    public void nextControlState(){
        controlState = controlState.next();
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

        // Set the model view matrix.
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
     * Slide the camera along one of its own axis.
     * @param delU Slide forward or back.
     * @param delV Slide up or down.
     * @param delN Slide left or right.
     */
    public void slide(double delU, double delV, double delN){
        this.eye.setX(this.eye.getX() + delU * u.getX() + delV * v.getX() + delN * n.getX());
        this.eye.setY(this.eye.getY() + delU * u.getY() + delV * v.getY() + delN * n.getY());
        this.eye.setZ(this.eye.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ());
        this.look.setZ(this.look.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ());
        this.look.setZ(this.look.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ());
        this.look.setZ(this.look.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ());

        // Set the model view matrix.
        setModelViewMatrix();
    }

    /**
     * Roll the camera.
     * @param angle The angle to roll the camera with.
     */
    public void roll(double angle){
        double cos = Math.cos((Math.PI/180) * angle);
        double sin = Math.sin((Math.PI/180) * angle);
        Vector t = u;
        u.setX(cos*t.getX() - sin*v.getX());
        u.setY(cos*t.getY() - sin*v.getY());
        u.setZ(cos*t.getZ() - sin*v.getZ());
        v.setX(sin*t.getX() + cos*v.getX());
        v.setY(sin*t.getY() + cos*v.getY());
        v.setZ(sin*t.getZ() + cos*v.getZ());

        // Set the model view matrix.
        setModelViewMatrix();
    }

    /**
     * Ray trace and update the current scene.
     * @param screen The screen.
     * @param objects The shapes in the scene.
     * @param sources The light sources in the scene.
     */
    public void rayTrace(Screen screen, List<Shape> objects, List<LightSource> sources){

        // Ray starts at the eye.
        Ray ray = new Ray().setStart(eye);

        for (int r = 0; r < screen.getWidth(); r++){
            for (int c = 0; c < screen.getHeight(); c++)
            {
                // Compute the ray's direction.
                Vector dir = ray.computeDirection(screen, r, c);
                dir = modelView.times(dir);

                // Built the rc-th ray.
                ray.setDir(dir);

                // Create a map that contains all the intersections of this ray with this pixel.
                Map<Double, Shape> intersections = new HashMap<>();

                // Find all the intersections of ray with objects in the scene.
                for (Shape object : objects){

                    // Specific ray for this object.
                    //Matrix inverseAT = object.getInverseAT();
                    //ray.setStart(inverseAT.times(eye));
                    //ray.setDir(inverseAT.times(dir));

                    // Check for collisions.
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
                if(closestObject == null) {
                    screen.drawPoint(r, c,0.0f, 0.0f, 0.0f);
                    continue;
                }

                // Find the color of the light returning to the eye along the ray from the point of intersection.
                Rgb color = closestObject.getColor();

                // Compute the hit point where the ray hits the object.
                Point hit = ray.getPoint( closestTime );
                // Compute the normal vector at that point.
                Vector normal = closestObject.getInverseAT().times(closestObject.getNormalVector(hit));
                // Normalize this vector.
                normal.normalize();

                Rgb color2 = new Rgb(0.0f, 0.0f, 0.0f);
                // Loop over every light source (shading purposes).
                for (LightSource L: sources){
                    Vector s = L.location.minus(hit);   // Vector from hit point to source.
                    s.normalize();
                    double mDotS = s.dot(normal);       // The lambert term.
                    if (mDotS > 0.0){                   // Hit point is turned toward the light.
                        float[] diff_coef = closestObject.material.diffuse_coefficients();
                        Rgb diffuse = new Rgb(  (float) mDotS * diff_coef[0] * L.color.r(),
                                                (float) mDotS * diff_coef[1] * L.color.g(),
                                                (float) mDotS * diff_coef[2] * L.color.b());
                        color2 = color.add(diffuse);
                    }
                }

                // Place the color in the rc-th pixel.
                screen.drawPoint(r, c, color2);
            }
        }
    }
}
