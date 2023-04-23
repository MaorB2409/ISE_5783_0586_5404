package primitives;

import java.util.Objects;

public class Point {
    protected Double3 xyz;

    /**
     * Constructor1
     *
     * @param x x coordinate of Point
     * @param y y coordinate of Point
     * @param z z coordinate of Point
     */
    public Point(double x, double y, double z) {
        // for performance reasons we don't use the following
        // this(new Double3(x,y,z));
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Deep constructor for Point (Constructor2)
     *
     * @param xyz values for our own xyz
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Overriding ToString() function
     *
     * @return Coordinates of Point
     */
    @Override
    public String toString() {
        return "Point: " + xyz;
    }

    /**
     * Override the equals function
     *
     * @param o Object o
     * @return true if equals and false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Point other)
            return this.xyz.equals(other.xyz);
        return false;
    }

    /**
     * @return hashCode of Object with xyz
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Function receives two points and calculates vector connecting both points
     *
     * @param p _point to subtract with this class _point
     * @return new vector with subtract result
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * @param vector Vector to add
     * @return new Point after adding Vector
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * @param p Point to calculate squared distance from
     * @return the squared distance from given Point
     */
    public double distanceSquared(Point p) {
        return ((xyz.d1 - p.xyz.d1) * (xyz.d1 - p.xyz.d1) + (xyz.d2 - p.xyz.d2) * (xyz.d2 - p.xyz.d2) + (xyz.d3 - p.xyz.d3) * (xyz.d3 - p.xyz.d3));
    }

    /**
     * @param p Point to ca
     * @return square root of squared distance (aka the actual distance from Point)
     */
    public double distance(Point p) {
        return (Math.sqrt(distanceSquared(p)));
    }
    public double getX(){
        return this.xyz.d1;
    }
    public double getY(){
        return this.xyz.d2;
    }
    public double getZ(){
        return this.xyz.d3;
    }
}

