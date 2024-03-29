package lighting;
import primitives.*;

/**
 * Class that represents a directional light, i.e.
 * light source with direction and without attenuation by distance.
 * This class extends the abstract class Light and the interface LightSource.
 * @author Yonatan Dahary
 */
public class DirectionalLight extends Light implements LightSource  {

    private final Vector direction;

    /**
     * Constructor to initialize the fields (intensity and direction)
     *
     * @param color The color for the intensity field
     * @param direction The vector for the direction field
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return this.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return this.direction;
    }
    @Override
    public double getDistance(Point point) {
        //Since directional light doesn't have a real source and comes from infinity
        return Double.POSITIVE_INFINITY;
    }
}
