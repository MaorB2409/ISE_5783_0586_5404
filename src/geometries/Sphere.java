package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static primitives.Util.*;


/**
 * Sphere class that extends RadialGeometry and includes a center point and radius
 */
public class Sphere extends RadialGeometry{
    final private Point center;
    final private double radius;
    final private double radiusSquared;

    /**
     * Constructor for sphere that receives center and radius
     *
     * @param center center point
     * @param radius radius value
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
        this.radius = radius;
        this.radiusSquared = radius * radius;
    }

    /**
     * function that returns center of sphere
     *
     * @return center of sphere
     */

    public Point getCenter() {
        return center;
    }

    /**
     * function that returns radius
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p0) {
        return p0.subtract(center).normalize();
    }

    /**
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}