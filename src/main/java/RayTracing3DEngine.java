import Matrix.*;

/**
 * A 3D engine using the ray tracing principle.
 */
public class RayTracing3DEngine {
    /**
     * @param args The input arguments.
     */
    public static void main (String[] args) {
        Matrix matrix1 = new Matrix(4,4).put(1,1,5);
        Matrix matrix2 = new Matrix(4, 4).put(3,1, 10);
        System.out.println(matrix1);
        System.out.println(matrix2);
        Matrix matrix3 = matrix1.times(matrix2);
        System.out.println(matrix3);
;    }
}