package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.*;

/**
 * geometry interface as a base for all the shapes
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * Emission getter
     *
     * @return the emission
     */
    public Color getEmission() {
        return emission;
    }
    /**
     * Emission setter
     *
     * @param emission the new emission
     * @return the modified geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
    /**
     * material field getter
     * @return the material field
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * material field setter
     * @param material parameter for the material
     * @return The object itself
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
    /**
     * get normal function that receives a point
     * @param p point to calculate the normal for
     * @return orthogonal vector that is the normal to point p
     */
    public abstract Vector getNormal(Point p);
}