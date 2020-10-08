import Matrix.*;
import org.junit.Test;

public class TestMatrix {

    @Test
    public void testEquals()
    {
        Matrix firstMatrix  =   new Matrix(4,4)
                .put(1,1, 5);
        Matrix secondMatrix =   new Matrix(4,4)
                .put(2,2, 5);
        Matrix thirdMatrix  =   new Matrix(new double[][]{  {0, 0, 0, 0},
                                                            {0, 5, 0, 0},
                                                            {0, 0, 0, 0},
                                                            {0, 0, 0, 0}});

        assert(!firstMatrix.equals(secondMatrix));
        assert(firstMatrix.equals(thirdMatrix));

        Matrix vector = new Vector(1,2, 3);

        assert(!vector.equals(firstMatrix));
    }

    @Test
    public void testGetAndPut()
    {
        Matrix matrix = new Identity(2).put(0,0, 10);

        double expected = 10;

        assert(expected == matrix.get(0,0));
    }

    @Test
    public void testTranspose()
    {
        Vector vector = new Vector(5,10,15);

        Matrix expectedMatrix = new Matrix(1,4)
                .put(0,0, 5)
                .put(0,1,10)
                .put(0,2,15)
                .put(0,3, 1);

        assert(vector.transpose().equals(expectedMatrix));

    }

    @Test
    public void testAddition()
    {
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

        assert((firstMatrix.plus(secondMatrix)).equals(expectedMatrix));

    }

    @Test
    public void testMultiplication()
    {
        Matrix firstMatrix  =   new Matrix(new double[][]  {{6, 2, 0, 2},
                                                            {3, 7, 8, 3},
                                                            {4, 6, 8, 1},
                                                            {9, 4, 7, 3}});

        Matrix secondMatrix =   new Matrix(new double[][]  {{12,4,26, 5},
                                                            {7, 3, 5, 4},
                                                            {3, 4,45, 5},
                                                            {7, 5, 3, 3}});

        Matrix expectedMatrix = new Matrix(new double[][]{{100, 40,172, 44},
                                                          {130, 80,482, 92},
                                                          {121, 71,497, 87},
                                                          {178, 91,578,105}});

        assert((firstMatrix.times(secondMatrix)).equals(expectedMatrix));
    }
}
