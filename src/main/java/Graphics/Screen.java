package Graphics;

import Input.KeyboardInput;
import World.Cam;
import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Implements a screen (or buffer) on which we can draw points.
 * Provides the ability to handle user input.
 */
public class Screen extends JFrame {

    // Create a keyboard for handling user input.
    private KeyboardInput keyboard = new KeyboardInput();

    // The surface - buffer - to be placed on the frame.
    private final Surface surface;

    // The distance in front of the eye.
    private double N;

    // The window extends from -W to W in the u-direction.
    private double W;
    // The window extends from -H to H in the v-direction.
    private double H;

    // Width & height of the screen.
    private int width;
    private int height;

    // Determines if the screen is blurred or not.
    boolean isBlurred = false;

    /**
     * Create a new JFrame and add a surface - buffer - to it.
     * @param width The width of the surface (pixels).
     * @param height The height of the surface (pixels).
     * @param aspect_ratio The aspect ratio of the screen.
     * @param view_angle The view angle.
     * @param N The distance in front of the eye.
     */
    public Screen(int width, int height, double aspect_ratio, double view_angle, double N){

        // Set the width and height of the buffer.
        this.width = width;
        this.height = height;

        // Set the distance in front of the eye.
        this.N = N;

        // W and H are given by the expressions:
        double H = 2 * N * Math.tan( view_angle / 2 );
        this.H = H;
        this.W = H * aspect_ratio;

        // Create a new surface.
        surface = new Surface( width, height );

        // Settings.
        setTitle("Ray Tracing 3D Engine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        add( surface );
        setVisible(true);

        // Hookup keyboard polling.
        addKeyListener( keyboard );
        surface.addKeyListener( keyboard );

    }

    /**
     * Get the keyboard.
     * @return The keyboard.
     */
    public KeyboardInput getKeyboard() { return this.keyboard; }

    /**
     * Get the screen's width.
     * @return The width of the screen.
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Get the screen's height.
     * @return The height of the screen.
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * Get W.
     * @return W.
     */
    public double getW(){
        return W;
    }

    /**
     * Get H.
     * @return H.
     */
    public double getH(){
        return H;
    }

    /**
     * Get the distance in front of the eye.
     * @return The distance in front of the eye.
     */
    public double getN(){
        return N;
    }

    /**
     * Draw a white point at a specified location of the surface.
     * @param x Location on the x-axis.
     * @param y Location on the y-axis.
     */
    public void drawPoint(int x, int y){
        surface.drawPoint(x, y, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Draw a colored point at a specified location of the surface.
     * @param x Location on the x-axis.
     * @param y Location on the y-axis.
     * @param r Red color component.
     * @param g Green color component.
     * @param b Blue color component.
     */
    public void drawPoint(int x, int y, float r, float g, float b){
        surface.drawPoint(x, y, r, g, b);
    }

    /**
     * Draw a colored point at a specified location of the surface.
     * @param x Location on the x-axis.
     * @param y Location on the y-axis.
     * @param color The required color.
     */
    public void drawPoint(int x, int y, Rgb color){
        surface.drawPoint(x, y, color.r(), color.g(), color.b());
    }

    /**
     * Update of the screen.
     */
    public void update()
    {
        // Blur the screen if required.
        if ( isBlurred ){
            surface.blur();
        }
        // Repaint the surface.
        surface.repaint();
    }

    /**
     * Process the keyboard's input.
     * @param cam The camera in our scene.
     */
    public void processInput(Cam cam) {

        double vel = cam.getVelocity();

        // If pressing the down key.
        if (keyboard.keyDown( KeyEvent.VK_DOWN )) {
            if (cam.controlState == Cam.ControlState.TRANSLATION){
                // Slide the camera downwards.
                cam.slide(0.0, vel, 0.0);
            }
        }

        // If pressing the up key.
        if (keyboard.keyDown( KeyEvent.VK_UP )) {
            if (cam.controlState == Cam.ControlState.TRANSLATION){
                // Slide the camera upwards.
                cam.slide(0.0, -vel, 0.0);
            }
        }

        // If pressing the left key.
        if (keyboard.keyDown( KeyEvent.VK_LEFT )) {
            if (cam.controlState == Cam.ControlState.TRANSLATION){
                // Slide the camera to the left.
                cam.slide(-vel, 0.0, 0.0);
            }
        }

        // If pressing the right key.
        if (keyboard.keyDown( KeyEvent.VK_RIGHT )) {
            if (cam.controlState == Cam.ControlState.TRANSLATION) {
                // Slide the camera to the right.
                cam.slide(vel, 0.0, 0.0);
            }
        }

        // If pressing S.
        if (keyboard.keyDown( KeyEvent.VK_S )) {
            if (cam.controlState == Cam.ControlState.TRANSLATION) {
                // Zoom in.
                cam.slide(0.0,0.0, vel);
            }
        }

        // If pressing W.
        if (keyboard.keyDown( KeyEvent.VK_W )) {
            if (cam.controlState == Cam.ControlState.TRANSLATION) {
                // Zoom out.
                cam.slide(0.0,0.0, -vel);
            }
        }

        if (keyboard.keyDown( KeyEvent.VK_B )) {
            isBlurred = !isBlurred;
        }

        // If pressing space.
        if (keyboard.keyDownOnce( KeyEvent.VK_SPACE )) {
            // Change the camera's control state.
            cam.nextControlState();
            // Show the new control state to the user.
            System.out.println( cam.controlState );
        }

        // If pressing escape.
        if (keyboard.keyDownOnce( KeyEvent.VK_ESCAPE )){
            // Exit the program.
            System.exit(0);
        }
    }

}
