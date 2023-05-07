package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;
import java.util.ArrayList;

import static primitives.Util.alignZero;

/**
 * Sphere class extending abstract class RadialGeometry,
 * represents a sphere in 3D Cartesian coordinate system
 * @author Sarah Daatyah Furmanski and Efrat Kartman
 */
public class Sphere extends RadialGeometry {
    final private Point center;

    /**
     * constructor
     * @param radius radius of geometry
     * @param point  center of sphere
     */
    public Sphere(Point point,double radius) {
        super(radius);
        this.center = point;
    }

    /**
     *
     * @return center point of sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     *
     * @param point point to calculate the normal for
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    /**
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        try {
            Point P0 = ray.getP0();
            Vector v = ray.getDir();

            if (P0.equals(center)) {
                return List.of(center.add(v.scale(radius)));
            }
            Vector u = center.subtract(P0);

            double tm = alignZero(v.dotProduct(u));
            double d = alignZero(Math.sqrt(Math.abs(u.dotProduct(u) - tm * tm)));

            // no intersections : the ray direction is above the sphere
            if (d >= radius) {
                return null;
            }

            double th = alignZero(Math.sqrt(radius * radius - d * d));

            double t1 = alignZero(tm - th);
            double t2 = alignZero(tm + th);

            if (t1 <= 0 && t2 <= 0) {
                return null;
            }

            if (t1 > 0 && t2 > 0) {
                Point P1 = ray.getPoint(t1);
                Point P2 = ray.getPoint(t2);
                return List.of(P1, P2);
            }
            if (t1 > 0) {
                Point P1 = ray.getPoint(t1);
                return List.of(P1);
            }
            if (t2 > 0) {
                Point P2 = ray.getPoint(t2);
                return List.of(P2);
            }
            return null;
        } catch(IllegalArgumentException e) { //ray is orthogonal to sphere center line
            return null;
        }
    }
}
