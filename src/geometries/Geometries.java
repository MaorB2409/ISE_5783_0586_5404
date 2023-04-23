package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private final List<Intersectable> geometries= new LinkedList<>();

    /**
     *
     */
    public Geometries() {
        //geometries = new LinkedList<>();
    }


    /**
     * constructor for the class
     *
     * @param geometries list of geometries to add
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add geometries to the list
     *
     * @param geometries list of geometries to add
     */
    public void add(Intersectable... geometries) {
        if (geometries.length > 0) this.geometries.addAll(List.of(geometries));
    }


    /**
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geometry : geometries) {
            var temp = geometry.findIntersections(ray);
            if (temp != null) {
                if (intersections == null)
                    intersections = new LinkedList<>(temp);
                else
                    intersections.addAll(temp);
            }
        }
        return intersections;
    }
}
