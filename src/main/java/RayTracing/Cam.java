package RayTracing;

import Graphics.Screen;
import Light.LightSource;
import Matrix.*;
import Matrix.Point;
import Graphics.*;

/**
 * Class that implements a camera in our 3D world.
 */
public class Cam {

    /**
     * The camera can be controlled in a variety of ways:
     * Translation, roll, pitch and rotation.
     * This allows us to use the same keys on the keyboard depending on the state.
     */
    public enum ControlState {

        TRANSLATION,
        ROLL;
        private static ControlState[] values = values();

        /**
         * Get the next control state.
         * @return The next control state.
         */
        public ControlState next(){
            return values[(this.ordinal()+1) % values.length];
        }
    }

    // The eye is located at point "eye" and looking at point "look".
    private Point eye, look;
    private Vector up;

    // The camera coordinates.
    private Vector u, v, n;

    // A matrix for converting the camera coordinates to world coordinates (x, y, z).
    Matrix modelView;

    // The control state of this camera.
    public ControlState controlState;

    /**
     * Default constructor
     */
    public Cam(){
        eye = new Point();
        look = new Point();
        up = new Vector();
        u = new Vector(); v = new Vector(); n = new Vector();
        this.modelView = new Matrix(4,4);
        controlState = ControlState.TRANSLATION;
    }

    /**
     * Set the control state of this camera to the next one.
     */
    public void nextControlState(){
        controlState = controlState.next();
    }

    /**
     * Initialize the camera's parameters.
     * @param eye The location of the eye.
     * @param look The point at which the eye is looking.
     * @param up The camera's upward direction.
     * @return This camera.
     */
    public Cam set(Point eye, Point look, Vector up){
        this.eye = eye;
        this.look = look;
        this.up = up;

        // Set the u,v a n coordinates.
        n = eye.minus(look);
        u = up.cross(n);
        n.normalize(); u.normalize();
        v = n.cross(u);

        // Set the model view matrix.
        setModelViewMatrix();

        return this;
    }

    /**
     * Build the model view matrix.
     */
    private void setModelViewMatrix(){
        Vector minus_eye = eye.minus(new Point(0,0,0));
        this.modelView = new Identity(4)
                .put(0,0,u.getX()).put(0,1,u.getY()).put(0,2,u.getZ()).put(0,3,minus_eye.dot(u))
                .put(1,0,v.getX()).put(1,1,v.getY()).put(1,2,v.getZ()).put(1,3,minus_eye.dot(v))
                .put(2,0,n.getX()).put(2,1,n.getY()).put(2,2,n.getZ()).put(2,3,minus_eye.dot(n));
    }

    /**
     * Slide the camera along one of its own axis.
     * @param delU Slide forward or back.
     * @param delV Slide up or down.
     * @param delN Slide left or right.
     */
    public void slide(double delU, double delV, double delN){
        this.eye.setX(this.eye.getX() + delU * u.getX() + delV * v.getX() + delN * n.getX());
        this.eye.setY(this.eye.getY() + delU * u.getY() + delV * v.getY() + delN * n.getY());
        this.eye.setZ(this.eye.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ());
        this.look.setZ(this.look.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ());
        this.look.setZ(this.look.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ());
        this.look.setZ(this.look.getZ() + delU * u.getZ() + delV * v.getZ() + delN * n.getZ());

        // Set the model view matrix.
        setModelViewMatrix();
    }

    /**
     * Roll the camera.
     * @param angle The angle to roll the camera with.
     */
    public void roll(double angle){
        double cos = Math.cos((Math.PI/180) * angle);
        double sin = Math.sin((Math.PI/180) * angle);
        Vector t = u;
        u.setX(cos*t.getX() - sin*v.getX());
        u.setY(cos*t.getY() - sin*v.getY());
        u.setZ(cos*t.getZ() - sin*v.getZ());
        v.setX(sin*t.getX() + cos*v.getX());
        v.setY(sin*t.getY() + cos*v.getY());
        v.setZ(sin*t.getZ() + cos*v.getZ());

        // Set the model view matrix.
        setModelViewMatrix();
    }

    /**
     * Ray trace the current scene.
     * @param scene The scene.
     * @param ray The sent out ray.
     * @return The appropriate color.
     */
    public Rgb rayTrace(Scene scene, Ray ray){

        // The background color.
        Rgb background = new Rgb(0.0f, 0.0f, 0.0f);

        // Get info about the first hit if there is one.
        HitInfo info = new HitInfo().getFirstHit(ray, scene.objects);

        // No intersections.
        if(info == null) {
            return background;
        }

        // Find the color of the light returning to the eye along the ray from the point of intersection.
        Rgb color = info.hitObject.getColor();

        // Get the reflective coefficients.
        float[] amb_coef = info.hitObject.material.ambient_coefficients();
        float[] dif_coef = info.hitObject.material.diffuse_coefficients();
        float[] spe_coef = info.hitObject.material.specular_coefficients();
        double exponent = info.hitObject.material.getExponent();

        // Ambient component.
        Rgb ambient = new Rgb(amb_coef[0], amb_coef[1], amb_coef[2]);
        color = color.add(ambient);

        // Get the normal vector at the hit point.
        Vector normal = info.hitNormal;
        normal.normalize();

        // Negative of the ray's direction. Points to the viewer.
        Vector v = new Vector(-ray.dir.getX(), -ray.dir.getY(), -ray.dir.getZ());

        // Loop over every light source (for shading purposes).
        for (LightSource L: scene.sources){

            // Vector from hit point to source.
            Vector s = L.location.minus(info.hitPoint);
            s.normalize();
            // The lambert term.
            double mDotS = s.dot(normal);
            // Hit point is turned toward the light.
            if (mDotS > 0.0){
                // Diffuse component.
                Rgb diffuse = new Rgb((float) mDotS * dif_coef[0] * L.color.r(),
                        (float) mDotS * dif_coef[1] * L.color.g(),
                        (float) mDotS * dif_coef[2] * L.color.b());
                color = color.add(diffuse);
            }
            // The halfway vector.
            Vector h = v.plus(s);
            h.normalize();
            // Part of phong term.
            double mDotH = h.dot(normal);
            if(mDotH <= 0)  // No specular distribution.
                continue;
            double phong = Math.pow(mDotH, exponent);
            Rgb specular = new Rgb( (float) phong * spe_coef[0] * L.color.r(),
                    (float) phong * spe_coef[1] * L.color.g(),
                    (float) phong * spe_coef[2] * L.color.b());
            color = color.add(specular);

        }

        return color;
    }

    /**
     * Render the screen to show the current scene.
     * @param screen The screen.
     * @param scene The scene.
     */
    public void render(Screen screen, Scene scene){

        // Ray starts at the eye.
        Ray ray = new Ray().setStart(eye);

        for (int r = 0; r < screen.getWidth(); r++){
            for (int c = 0; c < screen.getHeight(); c++)
            {
                // Compute the ray's direction.
                Vector dir = ray.computeDirection(screen, r, c);

                // Translate to world coordinates.
                dir = modelView.times(dir);

                // Built the rc-th ray.
                ray.setDir(dir);

                // Ray trace the current scene.
                Rgb color = rayTrace(scene, ray);

                // Place the color in the rc-th pixel.
                screen.drawPoint(r, c, color);
            }
        }
    }
}
