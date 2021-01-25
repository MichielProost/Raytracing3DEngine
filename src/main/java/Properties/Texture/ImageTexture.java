package Properties.Texture;

import Graphics.Rgb;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTexture {

    // The image.
    BufferedImage img;
    // The height of the image.
    int height;
    // The width of the image.
    int width;

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
     * @param x Location of pixel on the x axis.
     * @param y Location of pixel on the y axis.
     * @return The color at the corresponding pixel.
     */
    public Rgb getRgb(int x, int y){
        int rgb = img.getRGB(x, y);
        float red = (float) (((rgb >> 16 ) & 0x000000FF)/255.0);
        float green = (float) (((rgb >> 8 ) & 0x000000FF)/255.0);
        float blue = (float) (((rgb) & 0x000000FF)/255.0);
        return new Rgb(red, green, blue);
    }
}
