package geometries;

import primitives.Point;

import java.util.Objects;

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


}