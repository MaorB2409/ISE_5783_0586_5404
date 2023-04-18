package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;
import static primitives.Util.*;


public interface Intersectable {
    /**
     *
     * @param ray
     * @return
     */
    List<Point> findIntsersections(Ray ray);

}
