package com.github.MichielProost.Raytracing3DEngine;

import java.util.Arrays;

public class Matrix {

    int[][] arr;

    // Constructor.
    public Matrix()
    {
        // Initialize a 4D array.
        arr = new int[][]{  {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0} };
    }


    /**
     * Set a value at a particular location.
     * @param x Location on the x-axis.
     * @param y Location on the y-axis.
     * @param value The value to be set.
     * @return The matrix object.
     */
    public Matrix setValue(int x, int y, int value)
    {
        arr[x][y] = value;
        return this;
    }

    /**
     * Multiply this matrix with another matrix.
     * @param matrix The matrix we're going to multiply with.
     * @return The new matrix.
     */
    Matrix multiply(Matrix matrix)
    {
        // Create another matrix that stores the multiplication of the two matrices.
        Matrix mult = new Matrix();

        // Multiply two matrices.
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                for (int k=0; k<4; k++)
                {
                    int value = mult.arr[i][j] + arr[i][k] * matrix.arr[k][j];
                    mult.setValue(i, j, value);
                }
            }
        }

        return mult;

    }

    /**
     * Add this matrix with another matrix.
     * @param matrix The matrix we're going to add.
     * @return The new matrix.
     */
    Matrix add(Matrix matrix)
    {
        // Create antoher matrix that stores the addition of the two matrices.
        Matrix sum = new Matrix();

        for(int i=0; i<4; i++){
            for( int j=0; j<4; j++)
            {
                sum.setValue(i, j, arr[i][j] + matrix.arr[i][j]);
            }
        }

        return sum;
    }

    // Print matrix to screen.
    void print(){

        System.out.println(Arrays.deepToString(arr));

    }

}

