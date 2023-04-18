package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (/*o == null ||*/ !(o instanceof Tube tube)) return false;
        return this.axisRay.equals(tube.axisRay)&&this.radius==tube.radius;
    }

    //  @Override
    //  protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
    //     return null;
    //  }

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
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

}
