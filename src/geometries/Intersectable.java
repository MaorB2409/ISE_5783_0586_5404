package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;


public interface Intersectable {
    /**
     *
     * @param ray
     * @return a list of intersection points
     */
    List<Point> findIntersections(Ray ray);

}
