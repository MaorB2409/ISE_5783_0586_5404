package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

//import static primitives.Util.isZero;
import static primitives.Util.*;


public class Tube extends RadialGeometry {
    final Ray axisRay;
    final double radius;
    /**
     * Constructor for Tube class with a ray and a radius
     *
     * @param axisRay ray value
     * @param radius  radius value
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * @param point
     * @return the normal vector of the point on this tube.
     */
    @Override
    public Vector getNormal(Point point) {
        // Finding the normal:
        // n = normalize(p - o)
        // t = v * (p - p0)
        // o = p0 + t * v

        Vector v= axisRay.getDir();
        Point p0 =axisRay.getP0();

        double t= v.dotProduct(point.subtract(p0));

        //if t=0, then t*v is the zero vector and o=p0.
        Point o=p0;

        if(!isZero(t))
        {
            o=p0.add(v.scale(t));
        }

        return point.subtract(o).normalize();
    }

    /**
     *
     * @param o
     * @return true if axisRay equals the tube's axisRay and the radius equals the tube's radius
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (/*o == null ||*/ !(o instanceof Tube tube)) return false;
        return this.axisRay.equals(tube.axisRay)&&this.radius==tube.radius;
    }

    /**
     *
     * @return toString
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * @return The radius of the circle.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @return The axisRay is being returned.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * method calculates a list of Points that a ray from the light source to the object intersects
     * @param ray ray
     * @return returns a list of Points between the geometry and the light source
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector dir = ray.getDir();
        Vector v = axisRay.getDir();
        double dirV = dir.dotProduct(v);

        if (ray.getP0().equals(axisRay.getP0())) { // In case the ray starts on the p0.
            if (isZero(dirV))//the vectors are orthogonal, no intersections
                return List.of(ray.getPoint(radius));

            if (dir.equals(v.scale(dir.dotProduct(v))))//if the vectors are aligned
                return null;


            return List.of(ray.getPoint(
                    Math.sqrt(radius * radius / dir.subtract(v.scale(dir.dotProduct(v))).lengthSquared())));


        }
        Vector deltaP = ray.getP0().subtract(axisRay.getP0());
        double dpV = deltaP.dotProduct(v);

        double a = 1 - dirV * dirV;
        double b = 2 * (dir.dotProduct(deltaP) - dirV * dpV);
        double c = deltaP.lengthSquared() - dpV * dpV - radius * radius;

        if (isZero(a)) {
            if (isZero(b)) { // If a constant equation.
                return null;
            }
            return List.of(ray.getPoint(-c / b)); // if it's linear, there's a solution.
        }

        double discriminant = alignZero(b * b - 4 * a * c);

        if (discriminant < 0) // No real solutions.
            return null;

        //////////////
        double t1 = alignZero(-(b + Math.sqrt(discriminant)) / (2 * a)); // Positive solution.
        double t2 = alignZero(-(b - Math.sqrt(discriminant)) / (2 * a)); // Negative solution.

        if (discriminant <= 0) // No real solutions.
            return null;

        if (t1 > 0 && t2 > 0) {
            List<Point> points = new LinkedList<>();
            points.add(ray.getPoint(t1));
            points.add(ray.getPoint(t2));
            return points;
        }
        else if (t1 > 0) {
            List<Point> points = new LinkedList<>();
            points.add(ray.getPoint(t1));
            return  points;
        }
        else if (t2 > 0) {
            List<Point> points = new LinkedList<>();
            points.add(ray.getPoint(t2));
            return points;
        }
        return null;
    }

}
