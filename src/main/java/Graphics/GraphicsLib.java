package Graphics;
import javax.swing.*;

/**
 * Library for everything graphics related (e.g. drawing points, squares).
 */
public class GraphicsLib extends JFrame {

    private final Surface surface;

    /**
     * Create a new JFrame and add a surface to it.
     * @param width The width of the surface.
     * @param height The height of the surface.
     */
    public GraphicsLib(int width, int height){

        surface = new Surface(width, height);

        setTitle("Ray Tracing");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(surface);
        setVisible(true);

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
