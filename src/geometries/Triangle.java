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
    public List<Point> findIntersections(Ray ray) {
        //returns polygon findIntersections because it is the same
        return super.findIntersections(ray);
    }
}