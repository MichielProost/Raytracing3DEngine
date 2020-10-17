package Matrix;

/**
 * A rectangular array of numbers
 * also known as a matrix.
 */
public class Matrix{

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
     * Get the number of rows in this matrix.
     * @return The number of rows.
     */
    public int getR(){
        return R;
    }

    /**
     * Get the number of columns in this matrix.
     * @return The number of columns.
     */
    public int getC(){
        return C;
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
        assert(B.getR() == A.getR() && B.getC() == A.getC()) : "Wrong matrix dimensions in addition.";
        Matrix D = new Matrix(R, C);
        for (int i=0; i<getR(); i++){
            for (int j=0; j<getC(); j++)
            {
                D.put(i,j,A.get(i,j) + B.get(i,j));
            }
        }
        return D;
    }

    /**
     * D = A - B.
     * @param B The matrix to be subtracted from the original one.
     * @return The resulting matrix D.
     */
    public Matrix minus(Matrix B){
        Matrix A = this;
        assert(B.getR() == A.getR() && B.getC() == A.getR()) : "Wrong matrix dimensions in addition.";
        Matrix D = new Matrix(R, C);
        for (int i=0; i<getR(); i++){
            for (int j=0; j<getC(); j++)
            {
                D.put(i,j,A.get(i,j) - B.get(i,j));
            }
        }
        return D;
    }

    /**
     * D = A * B.
     * @param B The matrix to be multiplied with.
     * @return The resulting matrix D.
     */
    public <T extends Matrix> T times (T B){
        Matrix A = this;
        assert(A.C == B.getR()) : "Wrong matrix dimensions in multiplication.";
        Matrix D = new Matrix(A.R, B.getC());
        for (int i=0; i<D.getR(); i++){
            for (int j=0; j<D.getC(); j++){
                for (int k=0; k<A.C; k++)
                {
                    D.put(i,j,D.get(i,j) + (A.values[i][k] * B.get(k,j)));
                }
            }
        }

        D = toCorrectMatrixClass(D, B.getClass());
        return (T) D;
    }

    /**
     * Convert a general matrix to a specific (child) class.
     * @param M The matrix to be converted.
     * @param C The class to which the matrix should be converted.
     * @return The converted matrix.
     */
    public Matrix toCorrectMatrixClass(Matrix M, Class C){
        if (C == Vector.class){
            M = new Vector(M.get(0,0), M.get(1,0), M.get(2,0));
        }
        if (C == Point.class){
            M = new Point(M.get(0,0), M.get(1,0), M.get(2,0));
        }
        return M;
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

    @Override
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