package Graphics;

import Input.KeyboardInput;
import RayTracing.Cam;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Library for everything graphics related (e.g. drawing points, creating a buffer and so on).
 */
public class Screen extends JFrame {

    // Create a keyboard for handling user input.
    public KeyboardInput keyboard = new KeyboardInput();

    // The surface to be placed on the frame.
    private final Surface surface;

    // The distance in front of the eye.
    private double N;

    // Window extends from -W to W in the u-direction.
    private double W;
    // Window extends from -H to H in the v-direction.
    private double H;

    // Width & height of the screen.
    private int width;
    private int height;

    private double x = 0;   // The value on the x-axis.
    private double y = 0;   // The value on the y-axis.
    private double z = 0;   // The value on the z-axis.

    /**
     * Create a new JFrame and add a surface to it.
     * @param width The width of the surface (pixels).
     * @param height The height of the surface (pixels).
     * @param N The distance in front of the eye.
     * @param alpha The view angle.
     * @param aspect The aspect ratio of the screen.
     */
    public Screen(int width, int height, double N, double alpha, double aspect){

        this.width = width;
        this.height = height;

        this.N = N;

        // H and W are given by the expressions:
        double W = 2 * N * Math.tan( alpha / 2 );
        double H = W / aspect;
        this.W = W;
        this.H = H;

        // Create a new surface.
        surface = new Surface(width, height);

        // Settings:
        setTitle("Ray Tracing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        add(surface);
        setVisible(true);

        // Hookup keyboard polling
        addKeyListener(keyboard);
        surface.addKeyListener(keyboard);

    }

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
     * Set the screen's location in 3D space.
     * @param x The location on the x-axis.
     * @param y The location on the y-axis.
     * @param x The location on the z-axis.
     * @return This screen.
     */
    public Screen setLocation(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
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
     * Force an update of the screen.
     */
    public void forceUpdate()
    {
        surface.repaint();
    }

    /**
     * Process the keyboard's input.
     * @param cam The camera in our scene.
     */
    public void processInput(Cam cam) {

        // If going up
        if (keyboard.keyDown( KeyEvent.VK_DOWN )) {
            if (cam.controlState == Cam.ControlState.TRANSLATION){
                cam.slide(0.0, 0.1, 0.0);
            }
        }

        // If going down.
        if (keyboard.keyDown( KeyEvent.VK_UP )) {
            if (cam.controlState == Cam.ControlState.TRANSLATION){
                cam.slide(0.0, -0.1, 0.0);
            }
        }

        // If going left.
        if (keyboard.keyDown( KeyEvent.VK_LEFT)) {
            if (cam.controlState == Cam.ControlState.TRANSLATION){
                cam.slide(-0.1, 0.0, 0.0);
            } else if (cam.controlState == Cam.ControlState.ROLL){
                cam.roll(1.0);
            }
        }

        // If going right.
        if (keyboard.keyDown( KeyEvent.VK_RIGHT)) {
            if (cam.controlState == Cam.ControlState.TRANSLATION) {
                cam.slide(0.1, 0.0, 0.0);
            } else if (cam.controlState == Cam.ControlState.ROLL) {
                cam.roll(-1.0);
            }
        }

        // If pressing S.
        if (keyboard.keyDown( KeyEvent.VK_S)) {
            if (cam.controlState == Cam.ControlState.TRANSLATION) {
                cam.slide(0.0,0.0,0.1);
            }
        }

        // If pressing W.
        if (keyboard.keyDown( KeyEvent.VK_W)) {
            if (cam.controlState == Cam.ControlState.TRANSLATION) {
                cam.slide(0.0,0.0,-0.1);
            }
        }

        // If pressing space.
        if (keyboard.keyDownOnce( KeyEvent.VK_SPACE )) {
            cam.nextControlState();
            System.out.println(cam.controlState);
        }
    }

}
