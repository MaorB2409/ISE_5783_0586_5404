package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;
import static primitives.Util.*;


/**
 * Triangle class that extends polygon
 */
public class Triangle extends Polygon{

    /**
     * Triangle constructor using polygon constructor with 3 points
     *
     * @param p0 first point
     * @param p1 second point
     * @param p2 third point
     */
    public Triangle(Point p0, Point p1, Point p2) throws IllegalAccessException {
        super(p0, p1, p2);
    }

    @Override
    public String toString() {
        return "Triangle: "+this.plane+this.vertices;
    }


    /**
     * method calculates a list of Points that a ray from the light source to the object intersects
     * @param ray ray
     * @return returns a list of Points between the geometry and the light source
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        var intersections = this.plane.findGeoIntersections(ray);
        if (intersections == null)//checks if there is an intersection with the plane of the triangle
            return null;

        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        //we will check if the point is inside or outside the triangle
        Vector v1 = this.vertices.get(0).subtract(p0);
        Vector v2 = this.vertices.get(1).subtract(p0);
        Vector n1 = (v1.crossProduct(v2)).normalize();
        double sign1 = alignZero(dir.dotProduct(n1));
        if (sign1 == 0) return null;

        Vector v3 = this.vertices.get(2).subtract(p0);
        Vector n2 = (v2.crossProduct(v3)).normalize();
        double sign2 = alignZero(dir.dotProduct(n2));
        if (sign1 * sign2 <= 0) return null;

        Vector n3 = (v3.crossProduct(v1)).normalize();
        double sign3 = alignZero(dir.dotProduct(n3));
        if (sign1 * sign3 <= 0) return null;

        //if all signs are equal (+/-) the point is inside the triangle
        return List.of(new GeoPoint(this, intersections.get(0).point));
    }
}