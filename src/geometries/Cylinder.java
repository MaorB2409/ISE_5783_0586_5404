package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
//import static primitives.Util.isZero;
import static primitives.Util.*;
/**
 * cylinder class that extends the tube shape
 */
public class Cylinder extends Tube{
    final private double height;

    /**
     * Constructor for Cylinder
     *
     * @param axisRay central ray of Cylinder
     * @param radius  radius value
     * @param height  height value
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * getter function for height
     *
     * @return height of cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Cylinder) &&
                super.equals(obj)&&  this.height == ((Cylinder) obj).height;
    }



    @Override
    // Returning the normal of the cylinder at the point p.
    public Vector getNormal(Point p) {
        // Finding the normal:
        // n = normalize(p - o)
        // t = v * (p - p0)
        // o = p0 + t * v

        Vector v= axisRay.getDir();
        Point p0 =axisRay.getP0();

        //if p=p0, then (p-p0) is zero vector
        //returns the vector of the base as a normal
        if(p.equals(p0)){
            return v.scale(-1);
        }

        double t= v.dotProduct(p.subtract(p0));
        //check if the point on the bottom
        if(isZero(t)){
            return v.scale(-1);
        }
        //check if the point on the top
        if(isZero(t-height)){
            return v;
        }

        Point o=p0.add(v.scale(t));
        return p.subtract(o).normalize();
    }

    /**
     * method calculates a list of Points that a ray from the light source to the object intersects
     * @param ray ray
     * @return returns a list of Points between the geometry and the light source
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> res = new ArrayList<>();
        List<Point> lst = super.findIntersections(ray);
        if (lst != null)
            for (Point point : lst) {
                double distance = Util.alignZero(point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()));
                if (distance > 0 && distance <= height)
                    res.add(point);
            }
        if (res.size() == 0)
            return null;
        return res;
    }

}