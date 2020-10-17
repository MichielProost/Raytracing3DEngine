import org.junit.Test;
import Matrix.*;
import Interfaces.IATFactory;

public class TestATFactory {

    @Test
    public void testTranslation()
    {
        IATFactory factory = new ATFactory();
        Matrix translationMatrix = factory.getTranslation(5, 10, 0);

        Matrix expectedMatrix = new Matrix(new double[][]{  {1, 0, 0, 5},
                                                            {0, 1, 0,10},
                                                            {0, 0, 1, 0},
                                                            {0, 0, 0, 1}});

        assert(translationMatrix.equals(expectedMatrix));
    }

    @Test
    public void testScaling()
    {
        IATFactory factory = new ATFactory();
        Matrix scalingMatrix = factory.getScaling(5, 7, 2);

        Matrix expectedMatrix = new Matrix(new double[][]{  {5, 0, 0, 0},
                                                            {0, 7, 0, 0},
                                                            {0, 0, 2, 0},
                                                            {0, 0, 0, 1}});

        assert(scalingMatrix.equals(expectedMatrix));
    }

    @Test
    public void testRotation()
    {
        IATFactory factory = new ATFactory();

        Matrix rotationMatrix;
        Matrix expectedMatrix;

        double cos;
        double sin;

        for(int beta = 0; beta<360; beta++)
        {
            cos = Math.cos(beta);
            sin = Math.sin(beta);

            rotationMatrix = factory.getRotation(IATFactory.RotationAxis.X, beta);

            expectedMatrix = new Matrix(new double[][]{ {1,   0,   0, 0},
                                                        {0, cos,-sin, 0},
                                                        {0, sin, cos, 0},
                                                        {0,   0,   0, 1}});

            assert(rotationMatrix.equals(expectedMatrix));

            rotationMatrix = factory.getRotation(IATFactory.RotationAxis.Y, beta);

            expectedMatrix = new Matrix(new double[][]{ { cos, 0, sin, 0},
                                                        {   0, 1,   0, 0},
                                                        {-sin, 0, cos, 0},
                                                        {   0, 0,   0, 1}});

            assert(rotationMatrix.equals(expectedMatrix));

            rotationMatrix = factory.getRotation(IATFactory.RotationAxis.Z, beta);

            expectedMatrix = new Matrix(new double[][]{ {cos,-sin, 0, 0},
                                                        {sin, cos, 0, 0},
                                                        {  0,   0, 1, 0},
                                                        {  0,   0, 0, 1}});

            assert(rotationMatrix.equals(expectedMatrix));
        }
    }

}
