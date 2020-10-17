package Interfaces;

import Matrix.Matrix;

/**
 * An interface for the Affine Transformation factory.
 */
public interface IATFactory {

    // Rotate around one of these axis.
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
    Matrix getTranslation(double x, double y, double z);

    /**
     * Create a 4D scaling matrix.
     * @param x Scaling in the x-direction.
     * @param y Scaling in the y-direction.
     * @param z Scaling in the z-direction.
     * @return The resulting scaling matrix.
     */
    Matrix getScaling(double x, double y, double z);

    /**
     * Create a 4D rotation matrix.
     * @param axis The rotation axis.
     * @param beta The rotation angle.
     * @return The resulting rotation matrix.
     */
    Matrix getRotation(RotationAxis axis, double beta);

}
