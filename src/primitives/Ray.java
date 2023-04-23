package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {
    Point p0;
    Vector dir;

    /**
     *
     * @param p0 Point
     * @param dir Vector to calculate normalization of
     */
    public Ray(Point p0, Vector dir) {
        dir.normalize();
        this.p0 = p0;
        this.dir = dir;
    }

    /**
     * getter for p0
     * @return the Point p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for dir
     * @return the Vector dir
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Getter for point at a given distance from ray origin
     *
     * @param t distance from ray origin
     * @return point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     *
     * @param o Object
     * @return true if equals and false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        if (o instanceof Ray other){
            return this.dir.equals(other.dir) && this.p0.equals((other.p0));
        }
        return false;
    }

    /**
     *
     * @return hash of Object with p0 and dir
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    /**
     * Overriding the toString function
     * @return Ray- p0 and dir
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
