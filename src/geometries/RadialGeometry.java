package geometries;
import static primitives.Util.*;



/**
 * abstract radial geometry class that implements geometry
 */
public abstract class RadialGeometry implements Geometry{
    final double radius;

    /**
     * constructor of radial geometry that receives the radius value
     * @param radius is radius of a radial geometry
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * getter for the radius
     * @return the radius of the radial geometry shape
     */
    public double getRadius() {
        return radius;
    }
}