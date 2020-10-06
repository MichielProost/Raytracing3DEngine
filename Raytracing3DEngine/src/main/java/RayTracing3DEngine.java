/**
 * A 3D engine using the ray tracing principle.
 */
public class RayTracing3DEngine {
    /**
     * @param args The input arguments.
     */
    public static void main (String[] args) {
        ATFactory factory = new ATFactory();
        Matrix matrix = factory.getScaling(5,10,15);
        System.out.println(matrix);
    }
}