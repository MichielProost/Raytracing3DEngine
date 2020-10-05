import com.github.MichielProost.Raytracing3DEngine.Matrix;
import org.junit.Test;

public class TestMatrix {

    @Test
    void testEquals(){

        Matrix firstMatrix = new Matrix()
                .setValue(0, 0, 10);
        Matrix secondMatrix = new Matrix()
                .setValue(0, 0, 10);
        Matrix thirdMatrix = new Matrix()
                .setValue(0, 0, 20);

        assert (firstMatrix.equals(secondMatrix));
        assert (!firstMatrix.equals(thirdMatrix));

    }

}
