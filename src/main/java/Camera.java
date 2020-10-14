import Matrix.*;

public class Camera {

    private Point eye, look;
    private Vector up;
    private Vector u, v, n;

    public Camera set(Point eye, Point look, Vector up){
        this.eye = eye;
        this.look = look;
        this.up = up;

        //n = eye.minus(look);
        u = up.times(n);
        v = n.times(u);

        return this;
    }

    public void slide(double delU, double delV, double delN){

    }
}
