package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Extended Surface Class that adds an additional image to the panel.
 */
public class Surface extends JPanel {

    private final BufferedImage image;

    /**
     * Initialize the surface.
     * @param width The width of the image (thus, the surface).
     * @param height The height of the image (thus, the surface).
     */
    Surface(int width, int height){
        super(true);    // Enable double buffering.
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setAccelerationPriority(0);
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
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
        image.setRGB(x, y, new Color(r, g, b).getRGB());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0,0, this.getWidth(), this.getHeight(), this);
    }
}
