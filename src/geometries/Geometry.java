package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * geometry interface as a base for all of the shapes
 */
public interface Geometry {
    /**
     * get normal function that receives a point
     * @param p point to calculate the normal for
     * @return orthogonal vector that is the normal to point p
     */
    public default Vector getNormal(Point p) { //////////////////////////
        return null;
    }
}