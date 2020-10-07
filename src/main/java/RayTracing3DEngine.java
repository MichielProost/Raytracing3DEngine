import Matrix.*;

/**
 * A 3D engine using the ray tracing principle.
 */
public class RayTracing3DEngine {
    /**
     * @param args The input arguments.
     */
    public static void main (String[] args) {
        Matrix firstMatrix      = new Matrix(3,2)
                .put(0,0, 1).put(0,1, 1)
                .put(1,0, 2).put(1,1, 4)
                .put(2,0,-1).put(2,1, 5);

        Matrix secondMatrix     = new Matrix(3,2)
                .put(0,0, 6).put(0,1,-4)
                .put(1,0, 1).put(1,1, 3)
                .put(2,0, 1).put(2,1, 5);

        Matrix expectedMatrix   = new Matrix(3,2)
                .put(0,0, 7).put(0,1,-3)
                .put(1,0, 3).put(1,1, 7)
                .put(2,0, 0).put(2,1,10);

        System.out.println(expectedMatrix);
        System.out.println(firstMatrix.plus(secondMatrix));
;    }
}