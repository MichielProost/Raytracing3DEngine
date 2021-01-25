package Properties.Texture;

import Graphics.Rgb;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class that implements an image texture.
 */
public class ImageTexture {

    // The image.
    BufferedImage img;

    // The height of the image.
    int height;

    // The width of the image.
    int width;

    /**
     * Create a new image texture.
     * @param tex The path of the image.
     */
    public ImageTexture(String tex){
        try {
            img = ImageIO.read(new File(tex));
            height = img.getHeight();
            width = img.getWidth();
        } catch (IOException e) {
            System.out.println("Could not read image with directory: " + tex);
        }
    }

    /**
     * Get the RGB components from the corresponding pixel.
     * @param x Location of pixel on the x axis [0 1].
     * @param y Location of pixel on the y axis [0 1].
     * @return The color at the corresponding pixel.
     */
    public Rgb getRgb(double x, double y){
        x = Math.abs(x); y = Math.abs(y);
        int u = (int) (x * (width-1));
        int v = (int) (y * (height-1));
        int rgb = img.getRGB(u, v);
        float red = (float) (((rgb >> 16 ) & 0x000000FF)/255.0);
        float green = (float) (((rgb >> 8 ) & 0x000000FF)/255.0);
        float blue = (float) (((rgb) & 0x000000FF)/255.0);
        return new Rgb(red, green, blue);
    }

}
