package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import java.util.List;
import static primitives.Util.*;



/**
 * Plane class that implements geometry shapes
 */
public class Plane implements Geometry {
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
     * @param ray ray
     * @return returns a list of Points between the geometry and the light source
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0=ray.getP0();
        Vector v = ray.getDir();
        Vector n=normal;
        //return null when p0 is on the plane, no intersections
        if(q0.equals(P0)) {
            return null;
        }
        Vector P0_Q0=q0.subtract(P0);
        //numerator
        double nP0Q0=alignZero(n.dotProduct(P0_Q0));
        //return null when p0=q0, no intersections
        if(isZero(nP0Q0)){
            return null;
        }
        //denominator
        double nv=alignZero(n.dotProduct(v));
        //return null when ray is lying in the plane axis, infinite intersections
        if(isZero(nv)){
            return null;
        }
        double t=alignZero(nP0Q0/nv);
        //return null when t is not positive, opposite to the plane direction
        if(t<=0){
            return null;
        }
        Point point=ray.getPoint(t);
        return List.of(point);
    }
}