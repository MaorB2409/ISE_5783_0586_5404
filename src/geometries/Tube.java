package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //The overall idea is to form a quadratic equation that it's
        //solutions are the scale factor for the getPoint method.
        //We form this quadratic equation by setting two restriction on an arbitrary point:
        // 1. It is on the ray (i.e. of the form p+t*v)
        // 2. It is on the tube (i.e. it's distance from the tube axis ray is r)
        //Give those two restrictions we extract the requested quadratic equation.
        Vector tubeDir = this.axisRay.getDir();
        Vector rayDir = ray.getDir();

        // if the ray is parallel  to the tube axis ray return null
        if (tubeDir.equals(rayDir) || tubeDir.equals(rayDir.scale(-1))) {
            return null;
        }

        double dotP1 = Util.alignZero(rayDir.dotProduct(tubeDir));
        //if rayDir and tubeDir are orthogonal return just the rayDir,
        //else return their dot product.
        Vector vec1 = dotP1 == 0 ? rayDir : rayDir.subtract(tubeDir.scale(dotP1));
        double radiusSquared = this.radius * this.radius;

        //First coefficient of the quadratic equation.
        double A = Util.alignZero(vec1.lengthSquared());

        if (ray.getP0().equals(this.axisRay.getP0())) {
            return List.of(new GeoPoint(this, ray.getPoint(Math.sqrt(radiusSquared / A))));
        }

        //The vector between the ray heads.
        Vector deltaP = ray.getP0().subtract(this.axisRay.getP0());

        //If the ray starts at the tube axis ray
        if (tubeDir.equals(deltaP.normalize()) || tubeDir.equals(deltaP.normalize().scale(-1))) {
            return List.of(new GeoPoint(this, (ray.getPoint(Math.sqrt(radiusSquared / A)))));
        }

        double dotP2 = Util.alignZero(deltaP.dotProduct(tubeDir));
        var vec2 = dotP2 == 0 ? deltaP : deltaP.subtract(tubeDir.scale(dotP2));

        //Second coefficient for the quadratic equation
        double B = Util.alignZero(2 * (vec1.dotProduct(vec2)));
        //Third coefficient for the quadratic equation
        double C = Util.alignZero(vec2.lengthSquared() - radiusSquared);

        //Discriminant for the quadratic equation
        double det = Util.alignZero(B * B - 4 * A * C);

        //If the discriminant is smaller or equal to 0,
        // the ray is outside the tube.
        if (det <= 0) return null;

        //Solving the quadratic equation.
        det = Math.sqrt(det);
        double t1 = Util.alignZero((-B + det) / (2 * A));
        double t2 = Util.alignZero((-B - det) / (2 * A));

        //The intersection points are behind the head of the ray
        if (t1 <= 0) return null;

        //Check if there are one or two intersection points.
        return t2 <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t1))) : List.of(new GeoPoint(this, ray.getPoint(t2)), new GeoPoint(this, ray.getPoint(t1)));
    }

}
