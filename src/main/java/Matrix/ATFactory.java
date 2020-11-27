package Matrix;

import Interfaces.IATFactory;

public class ATFactory implements IATFactory {

    public Matrix getTranslation(double x, double y, double z){
        return new Identity(4)
                .put(0,3, x)
                .put(1,3, y)
                .put(2,3, z);
    }

    public Matrix getScaling(double x, double y, double z){
        return new Identity(4)
                .put(0,0, x)
                .put(1,1, y)
                .put(2,2, z);
    }

    public Matrix getRotation(RotationAxis axis, double beta){

        Matrix matrix = new Identity(4);

        double cos = Math.cos(beta);
        double sin = Math.sin(beta);

        switch(axis)
        {
            case X:
                matrix = matrix .put(1,1, cos)
                                .put(1,2,-sin)
                                .put(2,1, sin)
                                .put(2,2, cos);
                break;
            case Y:
                matrix = matrix .put(0,0, cos)
                                .put(2,0,-sin)
                                .put(0,2, sin)
                                .put(2,2, cos);
                break;
            case Z:
                matrix = matrix .put(0,0, cos)
                                .put(1,0, sin)
                                .put(0,1,-sin)
                                .put(1,1, cos);
                break;
            default:
                break;
        }

        return matrix;
    }

}
