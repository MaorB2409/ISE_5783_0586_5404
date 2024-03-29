package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;


public abstract class Intersectable {
    /**
     * PDS that includes a point and the geometric entity its on
     */
    public static class GeoPoint {
        /**
         * Geometric entity
         */
        public final Geometry geometry;
        /**
         * the point on the geometric entity
         */
        public final Point point;

        /**
         * GeoPoint constructor
         *
         * @param geometry Geometric entity
         * @param point    the point on the geometric entity
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof GeoPoint other)) return false;
            //checks if it's the same geometric entity by pointer(therefore no use of equals)
            return this.geometry == other.geometry && this.point.equals(other.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * Finds all intersection points of a ray and a geometric entity.
     *
     * @param ray the ray that intersect with the geometric entity.
     * @return list of intersection points.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * Finds all intersection GeoPoints of a ray and a geometric entity.
     *
     * @param ray the ray that intersect with the geometric entity.
     * @return list of intersection Geopoints.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Finds all intersection GeoPoints of a ray and a geometric entity within a given distance.
     *
     * @param ray         the ray that intersect with the geometric entity.
     * @param maxDistance the maximal distance to find intersections points.
     * @return list of intersection Geopoints.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Finds all intersection GeoPoints of a ray and a geometric entity within a given distance.
     *
     * @param ray         the ray that intersect with the geometric entity.
     * @param maxDistance the maximal distance to find intersections points.
     * @return list of intersection Geopoints.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);


}
