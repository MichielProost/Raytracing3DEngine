package Properties.Texture;

import Graphics.Rgb;
import Matrix.Point;

/**
 * Class that implements a solid state texture.
 */
public abstract class Texture {

    // Set of textures.
    public enum Textures{
        stripes,
        smoothColors,
        checkerboard2D,
        checkerboard3D
    }

    /**
     * Get the texel value corresponding the given object coordinates.
     * @param location The object coordinates.
     * @return The text texel value.
     */
    public abstract Rgb getTexelValue(Point location);

}
