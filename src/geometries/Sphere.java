package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static java.lang.Math.sqrt;
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
     * method calculates a list of Points that a ray from the light source to the object intersects
     * @param ray ray
     * @return returns a list of Points between the geometry and the light source
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector pointToCenter;
        try {
            pointToCenter = center.subtract(ray.getP0());
        } catch (IllegalArgumentException ignore) {
            return List.of(ray.getPoint(radius));
        }

        double tm = pointToCenter.dotProduct(ray.getDir());
        double distanceFromCenterSquared = pointToCenter.dotProduct(pointToCenter) - tm * tm;
        double thSquared = radiusSquared - distanceFromCenterSquared;
        //check that ray crosses area of sphere, if not then return null
        if (alignZero(thSquared) <= 0) return null;

        double th = sqrt(thSquared);
        double secondDistance = tm + th;
        //when the distance is not positive return null
        if (alignZero(secondDistance) <= 0) return null;
        double firstDistance = tm - th;
        Point gp2 = ray.getPoint(secondDistance);
        return firstDistance <= 0 ? List.of(gp2) : List.of((ray.getPoint(firstDistance)), gp2);
    }
}