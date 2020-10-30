package Light;

import Matrix.*;

/**
 * Implements a light source in our scene: light-emitting.
 */
public class LightSource {

    public Point location;  // The location of the light source.

    /**
     * Default constructor.
     * @param location The location of the light source.
     */
    public LightSource(Point location){
        this.location = location;
    }

}
