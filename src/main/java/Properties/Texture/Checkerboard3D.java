package Properties.Texture;

import Graphics.Rgb;
import Matrix.Point;
import static Utils.Constants.*;

/**
 * Solid texture: Implements a 3D checkerboard pattern.
 */
public class Checkerboard3D extends Texture {

    @Override
    public Rgb getTexelValue(Point location) {
        int jump =
                ((int) (location.getX() / CHECKER_SQUARE_X) +
                        (int) (location.getY() / CHECKER_SQUARE_Y) +
                        (int) (location.getZ() / CHECKER_SQUARE_Z)) % 2;
        if (jump == 0){
            return PURPLE;
        } else {
            return BLUE;
        }
    }
}
