package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
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
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }


}