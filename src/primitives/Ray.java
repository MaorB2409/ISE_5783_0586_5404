package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Ray class represents a ray in 3D Cartesian coordinate system,
 * using a point and a vector
 */
public class Ray {
    private static final double DELTA = 0.1;
    final private Point p0;
    final private Vector dir;

    /**
     * constructor
     * normalize the vector received
     * @param p0 point
     * @param dir direction vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    /**
     * the head of the constructed ray will be moves by the direction vector multiplied by delta.
     * the direction ov the move with or against the normal vector determined by the dot product of the direction and normal.
     *
     * @param head      the point that the ray suppose to start with.
     * @param direction the direction of the ray.
     * @param normal    the direction to move the ray with.
     */
    public Ray(Point head, Vector direction, Vector normal) {
        double mult = direction.dotProduct(normal);
        this.p0 = head.add(normal.scale(mult >= 0 ? DELTA : -DELTA));
        this.dir = direction;
    }

    /**
     *
     * @param t
     * @return the point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     *
     * @return the point p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     *
     * @return vector dir
     */
    public Vector getDir() {
        return dir;
    }

    /**
     *
     * @param o
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        if (!p0.equals(ray.p0)) return false;
        return dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        int result = p0.hashCode();
        result = 31 * result + dir.hashCode();
        return result;
    }

    /**
     *
     * @return toString
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     *
     * @param points is a collection of points
     * @return the closest Point to start of Ray
     */

//    public Point findClosestPoint(List<Point> pointList) {
//        //In case of empty list return null
//        if (pointList == null || pointList.isEmpty()) {
//            return null;
//        }
//
//        Point p = null;
//        double d = Double.POSITIVE_INFINITY;
//        //Iterating through the list. Once we find smaller distance
//        //than we have we replace the values.
//        //This goes on until the end of the list.
//        for (Point pnt : pointList) {
//            if (d > this.getP0().distance(pnt)) {
//                d = this.getP0().distance(pnt);
//                p = pnt;
//            }
//        }
//
//        return p;
//    }

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     * Method for finding the closest GeoPoint to the head of the ray in a given list of points
     *
     * @param lst List of points
     * @return Closest GeoPoint to the head of the ray
     */
    public Intersectable.GeoPoint findClosestGeoPoint(List<Intersectable.GeoPoint> lst) {
        //In case of empty list return null
        if (lst == null) {
            return null;
        }

        Intersectable.GeoPoint p = null;
        double d = Double.POSITIVE_INFINITY;
        //Iterating through the list. Once we find smaller distance
        //than we have we replace the values.
        //This goes on until the end of the list.
        for (Intersectable.GeoPoint pnt : lst) {
            double distance = this.p0.distance(pnt.point);
            if (d > distance) {
                d = distance;
                p = pnt;
            }
        }

        return p;
    }
}
