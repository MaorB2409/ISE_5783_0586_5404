package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static primitives.Util.*;



/**
 * Plane class that implements geometry shapes
 */
public class Plane extends Geometry {
    final Point q0;
    final Vector normal;

    /**
     * Constructor for plane with 3 points and calculates the normal vector of the plane
     *
     * @param p0 first point
     * @param p1 second point
     * @param p2 third point
     * @throws IllegalArgumentException when the points are on the same line
     */
    public Plane(Point p0, Point p1, Point p2) {
        q0 = p0;
        Vector v1 = p1.subtract(p2);
        Vector v2 = p0.subtract(p1);
        normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructor for plane with normal vector and a point on the plane
     *
     * @param q0 point on the plane
     * @param normal Vector on the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * returns the normal vector of the plane
     *
     * @return normal Vector
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p0) {
        return normal;
    }

    /**
     * method calculates a list of Points that a ray from the light source to the object intersects
     *
     * @param ray ray
     * @return returns a list of Points between the geometry and the light source
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        double denominator = this.normal.dotProduct(ray.getDir());
        if (isZero(denominator))
            return null; // ray parallel to the plane- the ray direction orthogonal to the normal

        Vector u;
        try {
            u = q0.subtract(ray.getP0());
        } catch (IllegalArgumentException ignore) {
            // the ray starts at the plane's reference point
            return null;
        }

        double t = alignZero(this.normal.dotProduct(u) / denominator);
        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}