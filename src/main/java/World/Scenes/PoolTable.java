package World.Scenes;

import Graphics.Rgb;
import Interfaces.IATFactory;
import Matrix.ATFactory;
import Matrix.Point;
import Matrix.Vector;
import Objects.Box;
import Objects.Plane;
import Objects.Shape;
import Objects.Sphere;
import Properties.Material.Material;
import Properties.Texture.Texture;
import World.LightSource;
import World.Scene;
import static Utils.Constants.*;

public class PoolTable extends Scene {

    public PoolTable (int maxRecursionLevel){
        // Create a new scene.
        super( maxRecursionLevel );

        // Create Affine Transformation Factory.
        IATFactory factory = new ATFactory();

        // Define shapes.
        Shape ground = new Plane( new Vector(0, 0, 1), new Point(0, 0, -1 ))
                .setFinite()
                .setMaterial( Material.Materials.wood )
                .setImageTexture( ".\\resources\\Pool\\WoodenFloor.jpg" );
        Shape thirteen_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\thirteen_ball.jpg" )
                .setATMatrix( factory.getTranslation(0, 2.5, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape fourteen_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\fourteen_ball.jpg" )
                .setATMatrix( factory.getTranslation(0.9, 2.5, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape twelve_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\twelve_ball.jpg" )
                .setATMatrix( factory.getTranslation(-0.9, 2.5, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape eleven_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\eleven_ball.jpg" )
                .setATMatrix( factory.getTranslation(-1.8, 2.5, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape fifteen_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\fifteen_ball.jpg" )
                .setATMatrix( factory.getTranslation(1.8, 2.5, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape eight_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\eight_ball.jpg" )
                .setATMatrix( factory.getTranslation(-0.45, 3.4, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape nine_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\nine_ball.jpg" )
                .setATMatrix( factory.getTranslation(0.45, 3.4, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape ten_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\ten_ball.jpg" )
                .setATMatrix( factory.getTranslation(1.35, 3.4, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape seven_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\seven_ball.jpg" )
                .setATMatrix( factory.getTranslation(-1.35, 3.4, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape five_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\five_ball.jpg" )
                .setATMatrix( factory.getTranslation(0, 4.3, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape six_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\six_ball.jpg" )
                .setATMatrix( factory.getTranslation(0.9, 4.3, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape four_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\four_ball.jpg" )
                .setATMatrix( factory.getTranslation(-0.9, 4.3, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape two_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\two_ball.png" )
                .setATMatrix( factory.getTranslation(-0.45, 5.2, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape three_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\three_ball.jpg" )
                .setATMatrix( factory.getTranslation(0.45, 5.2, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        Shape one_ball = new Sphere()
                .setMaterial( Material.Materials.lambertian, BLACK )
                .setImageTexture( ".\\resources\\Pool\\one_ball.jpg" )
                .setATMatrix( factory.getTranslation(0, 6.1, -0.6) )
                .setATMatrix( factory.getScaling( 0.4, 0.4, 0.4) )
                .setATMatrix( factory.getRotation( IATFactory.RotationAxis.X, 30) );
        addShape( ground );
        addShape( thirteen_ball );
        addShape( fourteen_ball );
        addShape( twelve_ball );
        addShape( eleven_ball );
        addShape( fifteen_ball );
        addShape( eight_ball );
        addShape( nine_ball );
        addShape( ten_ball );
        addShape( seven_ball );
        addShape( four_ball );
        addShape( five_ball );
        addShape( six_ball );
        addShape( two_ball );
        addShape( three_ball );
        addShape( one_ball );

        // Define light sources.
        LightSource source_front = new LightSource(0, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        LightSource source_right = new LightSource(2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        LightSource source_left = new LightSource(-2, 8, 0).setColor(new Rgb(0.7f, 0.7f, 0.7f));
        addSource( source_front );
        addSource( source_left );
        addSource( source_right );

    }

}
