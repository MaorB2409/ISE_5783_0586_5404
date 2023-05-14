package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;
import java.util.ArrayList;

import static java.lang.Math.sqrt;
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector vec;
        try {
            vec = this.center.subtract(ray.getP0());
        } catch (IllegalArgumentException ignore) {
            return List.of(new GeoPoint(this, ray.getPoint(this.radius)));
        }

        // Here we calculate the projection of the vector formed by the center of the
        // circle and the head of the ray. Then we calculate the distance between then center
        // and the projection and the distance between the projection to the intersections points.
        // The idea is that the projection is the middle of the two intersection points
        // so all we have to do is to add and subtract the distance to the intersection points
        double tm = alignZero(vec.dotProduct(ray.getDir()));
        double dSqr = vec.lengthSquared() - tm * tm;
        double radiusSqr=this.radius*this.radius;
        double thSqr = radiusSqr - dSqr;
        // If the ray is tangent to the sphere or doesn't intersect the sphere at all return null
        if (alignZero(thSqr) <= 0) return null;

        double th = sqrt(thSqr);
        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null;
        double t1 = alignZero(tm - th);
        // If only one is greater than 0 then the ray intersects the sphere only once
        return t1 <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t2))) : List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
    }
}
