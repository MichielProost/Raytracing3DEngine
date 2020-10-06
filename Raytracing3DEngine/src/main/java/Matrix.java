import java.util.Arrays;
import java.util.Objects;

/**
 * A rectangular array of numbers
 * also known as a matrix.
 */
public class Matrix {

    private int R;              // Number of rows.
    private int C;              // Number of columns.
    private double[][] values;  // R-by-C array

    /**
     * Create a R-by-C matrix of 0's.
     * @param R The desired number of rows.
     * @param C The desired numbers of columns.
     */
    public Matrix(int R, int C){
        this.R = R;
        this.C = C;
        values = new double[R][C];
    }

    /**
     * Create a matrix based on a 2D array.
     * @param values A 2D array.
     */
    public Matrix(double[][] values){
        R = values.length;
        C = values[0].length;
        this.values = new double[R][C];
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++)
            {
                this.values[i][j] = values[i][j];
            }
        }
    }

    /**
     * Get the value of a specific element in the matrix.
     * @param r The desired row.
     * @param c The desired column.
     * @return The value of the desired element.
     */
    public double get(int r, int c){
        return values[r][c];
    }

    /**
     * Put a value at a specific element in the matrix.
     * @param r The desired row.
     * @param c The desired column.
     * @param value The value to be put into the matrix.
     * @return This matrix.
     */
    public Matrix put(int r, int c, double value){
        values[r][c] = value;
        return this;
    }

    /**
     * Create and return the transpose of this matrix.
     * @return The transpose of this matrix.
     */
    public Matrix transpose(){
        Matrix T = new Matrix(C,R);
        for (int i=0; i<R; i++){
            for(int j=0; j<C; j++)
            {
                T.values[j][i] = this.values[i][j];
            }
        }
        return T;
    }

    /**
     * D = A + B.
     * @param B The matrix to be added.
     * @return The resulting matrix D.
     */
    public Matrix plus(Matrix B){
        Matrix A = this;
        assert(B.R == A.R && B.C == A.C) : "Wrong matrix dimensions in addition.";
        Matrix D = new Matrix(R, C);
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++)
            {
                D.values[i][j] = A.values[i][j] + B.values[i][j];
            }
        }
        return D;
    }

    /**
     * D = A * B.
     * @param B The matrix to be multiplied with.
     * @return The resulting matrix D.
     */
    public Matrix times(Matrix B){
        Matrix A = this;
        assert(A.C == B.R) : "Wrong matrix dimensions in multiplication.";
        Matrix D = new Matrix(A.R, B.C);
        for (int i=0; i<D.R; i++){
            for (int j=0; j<D.C; j++){
                for (int k=0; k<A.C; k++)
                {
                    D.values[i][j] += (A.values[i][k] * B.values[k][j]);
                }
            }
        }
        return D;
    }

    @Override
    public boolean equals(Object o) {
        Matrix A = this;
        if( ! (o instanceof Matrix) )
            return false;

        Matrix B = (Matrix) o;
        if(B.R != A.R || B.C != A.C)
            return false;
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++)
            {
                if (B.values[i][j] != A.values[i][j])
                    return false;
            }
        }
        return true;
    }

    public String toString() {
        String str = "";
        for(int i=0; i<R; i++){
            str += "| ";
            for(int j=0; j<C; j++)
            {
                str += values[i][j] + " | ";
            }
            str += "\n";
        }
        return str;
    }
}