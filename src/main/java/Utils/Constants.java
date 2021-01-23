package Utils;

import Graphics.Rgb;

public final class Constants {

    private Constants() {
        // Restrict instantiation.
    }

    // Keyboard input.
    public static final int KEY_COUNT = 256;

    // Colors.
    public static final Rgb WHITE = new Rgb(1.0f, 1.0f, 1.0f);
    public static final Rgb BLACK = new Rgb(0.0f, 0.0f, 0.0f);
    public static final Rgb AIR = new Rgb(166, 231, 255);
    public static final Rgb RED = new Rgb(1.0f, 0.0f, 0.0f);
    public static final Rgb GREEN = new Rgb(0.0f, 1.0f, 0.0f);
    public static final Rgb BLUE = new Rgb(0.0f, 0.0f, 1.0f);
    public static final Rgb LIGHT_BROWN = new Rgb(202, 164, 114);
    public static final Rgb DARK_BROWN = new Rgb(93, 67, 44);

    // Material.
    public static final float REQUIRED_SHININESS = 0.05f;
    public static final float REQUIRED_TRANSPARENCY = 1.0f;

    // Shapes.
    public static final int BOX_SIZE = 1;
    public static final double CYLINDER_EPS = 0.00001;

    // Intersection.
    public static final double EPSILON = 0.000001;

}
