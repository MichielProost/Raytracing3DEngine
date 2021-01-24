package Material.Texture;

import Graphics.Rgb;
import Matrix.Point;

public class SmoothColors extends Texture {

    @Override
    public Rgb getTexelValue(Point location) {
        return new Rgb(
                (float) (1 - Math.abs(2 * fract(location.getX()) - 1)),
                (float) (1 - Math.abs(2 * fract(location.getY()) - 1)),
                (float) (1 - Math.abs(2 * fract(location.getZ()) - 1))
        );
    }

    private double fract(double coordinate){
        return (coordinate - Math.floor(coordinate));
    }

}
