package World;

import Graphics.Screen;
import Matrix.*;
import Matrix.Point;
import Graphics.*;
import RayTracing.Ray;

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

    // A matrix for converting the camera coordinates (u, v, n) to world coordinates (x, y, z).
    Matrix modelView;

    // The control state of this camera.
    public ControlState controlState;

    /**
     * Create a new camera.
     * @param eye The location of the eye.
     * @param look The point at which the eye is looking.
     * @param up The camera's upward direction.
     */
    public Cam(Point eye, Point look, Vector up){
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

        // Set the camera's control state.
        this.controlState = ControlState.TRANSLATION;
    }

    /**
     * Switch this camera to the next control state.
     */
    public void nextControlState(){
        controlState = controlState.next();
    }

    /**
     * Build the model view matrix.
     */
    private void setModelViewMatrix(){
        Vector minus_eye = new Vector(-eye.getX(), -eye.getY(), -eye.getZ());
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
     * Render the screen to show the current scene.
     * @param screen The screen.
     * @param scene The scene.
     */
    public void render(Screen screen, Scene scene){

        // Ray starts at the eye.
        Ray ray = new Ray().setStart(eye);

        for (int r = 0; r < screen.getWidth(); r++){
            for (int c = 0; c < screen.getHeight(); c++)
            {
                // Compute the ray's direction.
                Vector dir = ray.computeDirection(screen, r, c);

                // Translate to world coordinates.
                dir = modelView.times(dir);

                // Built the rc-th ray.
                ray.setDir(dir);

                // Initialize the ray's recursive level.
                ray.recurseLevel = 0;

                // Ray trace the current scene.
                Rgb color = scene.rayTrace(ray);

                // Place the color in the rc-th pixel.
                screen.drawPoint(r, c, color);
            }
        }
    }
}
