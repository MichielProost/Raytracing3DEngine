package Graphics;

/**
 * Defines a color by its RGB components.
 */
public class Rgb {

    private float r;    // Red component.
    private float g;    // Green component.
    private float b;    // Blue component.

    /**
     * Create a new color.
     * @param r Red component. Range [0.0 1.0f].
     * @param g Green component. Range [0.0 1.0f].
     * @param b Blue component. Range [0.0 1.0f].
     */
    public Rgb(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Create a new color.
     * @param r Red component. Range [0 255].
     * @param g Green component. Range [0 255].
     * @param b Blue component. Range [0 255].
     */
    public Rgb(int r, int g, int b)
    {
        this.r = (float) r / 255;
        this.g = (float) g / 255;
        this.b = (float) b / 255;
    }

    /**
     * Return the red component.
     * @return The red component of this color.
     */
    public float r()
    {
        return r;
    }

    /**
     * Return the green component.
     * @return The green component of this color.
     */
    public float g()
    {
        return g;
    }

    /**
     * Return the blue component.
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

        // Add colors.
        Rgb new_color = new Rgb(this.r + color.r, this.g + color.g, this.b + color.b);

        // Limit RGB components in case of overexposure.
        return checkBoundaries( new_color );

    }

    /**
     * Multiply this color with a value.
     * @param value The value to multiply this color with.
     * @return The resulting color.
     */
    public Rgb multiply(float value){

        // Multiply color with value.
        Rgb new_color = new Rgb( this.r * value, this.g * value, this.b * value);

        // Limit RGB components in case of overexposure.
        return checkBoundaries( new_color );

    }

    /**
     * RGB components may not exceed their boundaries.
     */
    public Rgb checkBoundaries(Rgb color){
        // Check boundary of red component.
        if (color.r > 1){
            color.r = 1;
        }
        // Check boundary of green component.
        if (color.g > 1){
            color.g = 1;
        }
        // Check boundary of blue component.
        if (color.b > 1){
            color.b = 1;
        }
        return color;
    }
}
