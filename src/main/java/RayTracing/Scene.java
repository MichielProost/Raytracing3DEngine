package RayTracing;

import Light.LightSource;
import Objects.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 * Describes our 3D world.
 */
public class Scene {

    public List<Shape> objects;         // Objects in the scene.
    public List<LightSource> sources;   // Light sources in the scene.
    public int maxRecursionLevel;       // Max recursion level.

    /**
     * Default constructor.
     */
    public Scene(int maxRecursionLevel){
        objects = new ArrayList<>();
        sources = new ArrayList<>();
        this.maxRecursionLevel = maxRecursionLevel;
    }

    /**
     * Add a shape to the list of objects.
     * @param shape The shape to be added.
     */
    public void addShape(Shape shape){
        objects.add( shape );
    }

    /**
     * Add a source to the list of light sources.
     * @param source The source to be added.
     */
    public void addSource(LightSource source){
        sources.add( source );
    }

}
