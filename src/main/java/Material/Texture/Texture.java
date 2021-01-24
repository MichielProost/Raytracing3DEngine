package Material.Texture;

import Graphics.Rgb;
import Material.Material;
import Matrix.Point;

public abstract class Texture {

    // Set of textures.
    public enum Textures{
        stripes,
        smoothColors,
        checkerboard2D
    }

    /**
     * Get the texel value corresponding the given object coordinates.
     * @param location The object coordinates.
     * @return The text texel value.
     */
    public abstract Rgb getTexelValue(Point location);

}
