package Properties.Texture;

import Graphics.Rgb;
import Matrix.Point;
import static Utils.Constants.*;

public class Checkerboard2D extends Texture {

    @Override
    public Rgb getTexelValue(Point location) {
        int jump = ((int) (location.getX() / CHECKER_SQUARE_X) + (int) (location.getY() / CHECKER_SQUARE_Y)) % 2;
        if (jump == 0){
            return CHESS_DARK;
        } else {
            return CHESS_LIGHT;
        }
    }

}
