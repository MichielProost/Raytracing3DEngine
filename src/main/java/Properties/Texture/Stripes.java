package Properties.Texture;

import Graphics.Rgb;
import Matrix.Point;
import static Utils.Constants.*;

/**
 * Solid pattern: Adds a stripe pattern onto the surface.
 */
public class Stripes extends Texture {

    @Override
    public Rgb getTexelValue(Point location) {
        if (Math.sin(Math.PI * location.getZ() / STRIPE_WIDTH) > 0){
            return YELLOW;
        } else {
            return RED;
        }
    }

}
