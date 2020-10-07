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
}
