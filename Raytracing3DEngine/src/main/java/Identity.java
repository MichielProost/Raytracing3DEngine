/**
 *  An identity matrix of size N.
 *  A N-by-N square matrix with ones on the main diagonal
 *  and zeros elsewhere.
 */
public class Identity extends Matrix {

    private int N;  // Its size.

    /**
     * Creates an identity matrix of size N.
     * @param N The desired size.
     */
    public Identity(int N){
        super(N,N);
        this.N = N;
        for(int i=0; i<N; i++){
            put(i,i,1);
        }
    }

}
