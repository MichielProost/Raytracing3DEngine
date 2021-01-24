package Material.Texture;

import Graphics.Rgb;
import Matrix.Point;
import static Utils.Constants.*;

public class Stripes extends Texture {

    @Override
    public Rgb getTexelValue(Point location) {
        if (Math.sin(Math.PI * location.getX() / STRIPE_WIDTH) > 0){
            return YELLOW;
        } else {
            return RED;
        }
    }

}
