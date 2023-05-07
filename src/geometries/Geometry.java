package geometries;

import primitives.Point;
import primitives.Vector;
import static primitives.Util.*;

/**
 * geometry interface as a base for all the shapes
 */
public interface Geometry extends Intersectable {
    /**
     * get normal function that receives a point
     * @param p point to calculate the normal for
     * @return orthogonal vector that is the normal to point p
     */
    public default Vector getNormal(Point p) { //////////////////////////
        return null;
    }
}