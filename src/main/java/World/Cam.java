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
     * Translation.
     * This allows us to use the same keys on the keyboard depending on the state.
     */
    public enum ControlState {

        TRANSLATION;
        //TODO. Implement roll, pitch and rotation.
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

    // A matrix for converting camera coordinates (u, v, n) to world coordinates (x, y, z).
    private Matrix modelView;

    // The camera's control state.
    public ControlState controlState;

    // The camera's velocity (speed when it is moving).
    private double velocity = 0.1;

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

        // Set the u,v,n coordinates.
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
     * Go to the next control state.
     */
    public void nextControlState(){
        controlState = controlState.next();
    }

    /**
     * Get the camera's velocity.
     * @return The camera's velocity.
     */
    public double getVelocity(){
        return velocity;
    }

    /**
     * Set the camera's velocity.
     * @param velocity The speed at which the camera will move.
     * @return This camera.
     */
    public Cam setVelocity( double velocity ){
        this.velocity = velocity;
        return this;
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
     * Slide the camera along one of its axis.
     * @param delU Slide left or right.
     * @param delV Slide up or down.
     * @param delN Slide forward or back.
     */
    public void slide(double delU, double delV, double delN){
        // New x, y and z.
        double x, y, z;

        // Calculate and set the eye's new position.
        x = eye.getX() + delU * u.getX() + delV * v.getX() + delN * n.getX();
        y = eye.getY() + delU * u.getY() + delV * v.getY() + delN * n.getY();
        z = eye.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ();
        eye = new Point(x, y, z);

        // Calculate and set the new point at which the eye is looking.
        x = look.getX() + delU * u.getX() + delV * v.getX() + delN * n.getX();
        y = look.getY() + delU * u.getY() + delV * v.getY() + delN * n.getY();
        z = look.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ();
        look = new Point(x, y, z);

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
        Ray ray = new Ray().setStart( eye );

        for (int r = 0; r < screen.getHeight(); r++){
            for (int c = 0; c < screen.getWidth(); c++)
            {
                // Compute the ray's direction.
                Vector dir = ray.computeDirection( screen, r, c );

                // Translate to world coordinates.
                dir = modelView.times( dir );

                // Built the rc-th ray.
                ray.setDir(dir);

                // Ray trace the current scene.
                Rgb color = scene.rayTrace( ray );

                // Place the color in the rc-th pixel.
                screen.drawPoint( c, r, color );
            }
        }
    }
}
