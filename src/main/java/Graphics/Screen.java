package Graphics;
import javax.swing.*;

/**
 * Library for everything graphics related (e.g. drawing points, squares).
 */
public class Screen extends JFrame {

    private final Surface surface;

    private int width;
    private int height;

    private double x = 0;   // The value on the x-axis.
    private double y = 0;   // The value on the y-axis.
    private double z = 0;   // The value on the z-axis.

    /**
     * Create a new JFrame and add a surface to it.
     * @param width The width of the surface.
     * @param height The height of the surface.
     */
    public Screen(int width, int height){

        surface = new Surface(width, height);

        this.width = width;
        this.height = height;

        setTitle("Ray Tracing");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(surface);
        setVisible(true);

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
     * Set the screen's location in 3D space.
     * @param x The location on the x-axis.
     * @param y The location on the y-axis.
     * @param x The location on the z-axis.
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

}
