package World;

import Graphics.Rgb;
import Matrix.*;

/**
 * Implements a light source in our scene: light-emitting.
 */
public class LightSource {

    public Point location;  // The location of the light source.
    public Rgb color = new Rgb(1.0f, 1.0f, 1.0f);   // The (default) color of the light source.

    /**
     * Default constructor.
     * @param location The location of the light source.
     */
    public LightSource(Point location){
        this.location = location;
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
