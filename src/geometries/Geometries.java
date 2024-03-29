package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
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
     * A function that finds the intersection points of ray with a geometry
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : geometries) {
            var temp = geometry.findGeoIntersectionsHelper(ray,maxDistance);
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
