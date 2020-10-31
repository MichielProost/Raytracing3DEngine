package Light;

import Graphics.Rgb;
import Matrix.*;

/**
 * Implements a light source in our scene: light-emitting.
 */
public class LightSource {

    public Point location;  // The location of the light source.
    public Rgb color = new Rgb(1.0f, 1.0f, 1.0f);   // The color of the light source.

    /**
     * Default constructor.
     * @param location The location of the light source.
     */
    public LightSource(Point location){
        this.location = location;
    }

    /**
     * Constructor.
     * @param location The location of the light source.
     * @param color The color of the light source.
     */
    public LightSource(Point location, Rgb color){
        this.location = location;
        this.color = color;
    }

}
