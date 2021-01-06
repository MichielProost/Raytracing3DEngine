package World;

import Graphics.Rgb;
import Matrix.*;

/**
 * Implements a light source in our scene: light-emitting.
 */
public class LightSource {

    // The location of the light source.
    private Point location;

    // The (default) color of the light source.
    public Rgb color = new Rgb(1.0f, 1.0f, 1.0f);

    /**
     * Constructor.
     * @param x The location on the x axis.
     * @param y The location on the y axis.
     * @param z The location on the z axis.
     */
    public LightSource(double x, double y, double z){
        this.location = new Point(x, y, z);
    }

    /**
     * Get the location of the light source.
     * @return The location of the light source.
     */
    public Point getLocation(){
        return location;
    }

    /**
     * Set the color of the light source.
     * @param color The color of the light source.
     * @return This light source.
     */
    public LightSource setColor(Rgb color){
        this.color = color;
        return this;
    }

}
