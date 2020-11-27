package Graphics;

/**
 * Defines a color by its RGB components.
 */
public class Rgb {

    private float r;    // Red component.
    private float g;    // Green component.
    private float b;    // Blue component.

    /**
     * Red, green and blue components in the range [0.0f 1.0f].
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

        Rgb result = new Rgb(this.r + color.r, this.g + color.g, this.b + color.b);
        // Limit in case of overexposure.
        if (result.r > 1){
            result.r = 1;
        }
        if (result.g > 1){
            result.g = 1;
        }
        if (result.b > 1){
            result.b = 1;
        }
        return result;

    }

    /**
     * Multiplication of this color with a value.
     * @param value The value to multiply this color with.
     * @return The resulting color.
     */
    public Rgb multiply(float value){

        Rgb result = new Rgb( value * this.r, value * this.g, value * this.b);
        // Limit in case of overexposure.
        if (result.r > 1){
            result.r = 1;
        }
        if (result.g > 1){
            result.g = 1;
        }
        if (result.b > 1){
            result.b = 1;
        }
        return result;

    }
}
