public class ATFactory {

    enum RotationAxis{
        X,
        Y,
        Z
    }
    /**
     * Create a 4D translation matrix.
     * @param x Translation amount in the x-direction.
     * @param y Translation amount in the y-direction.
     * @param z Translation amount in the z-direction.
     * @return The resulting translation matrix.
     */
    public Matrix getTranslation(double x, double y, double z){
        return new Identity(4)
                .put(0,3, x)
                .put(1,3, y)
                .put(2,3, z);
    }

    /**
     * Create a 4D scaling matrix.
     * @param x Scaling in the x-direction.
     * @param y Scaling in the y-direction.
     * @param z Scaling in the z-direction.
     * @return The resulting scaling matrix.
     */
    public Matrix getScaling(double x, double y, double z){
        return new Identity(4)
                .put(0,0, x)
                .put(1,1, y)
                .put(2,2, z);
    }

    /**
     * Create a 4D rotation matrix.
     * @param axis The rotation axis.
     * @param beta The rotation angle.
     * @return The resulting rotation matrix.
     */
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
