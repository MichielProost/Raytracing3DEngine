package Graphics;

/**
 * Defines a color by its RGB components.
 */
public class Rgb {

    private float r;    // Red component.
    private float g;    // Green component.
    private float b;    // Blue component.

    /**
     * Red, green and blue components in the range [0.0 1.0].
     * @param r Red component.
     * @param g Green component.
     * @param b Blue component.
     */
    public Rgb(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Return the red component of this color.
     * @return The red component of this color.
     */
    public float r()
    {
        return r;
    }

    /**
     * Return the green component of this color.
     * @return The green component of this color.
     */
    public float g()
    {
        return g;
    }

    /**
     * Return the blue component of this color.
     * @return The blue component of this color.
     */
    public float b()
    {
        return b;
    }

    /**
     * Addition of two colors.
     * @param color The color to be added.
     * @return The resulting color.
     */
    public Rgb add(Rgb color){
        Rgb color_result = new Rgb(this.r + color.r, this.g + color.g, this.b + color.b);
        // Limit in case of overexposure.
        if (color_result.r > 1){
            color_result.r = 1;
        }
        if (color_result.g > 1){
            color_result.g = 1;
        }
        if (color_result.b > 1){
            color_result.b = 1;
        }
        return color_result;
    }
}
