package lighting;

import primitives.*;

/**
 * Class that represents an ambient light, extends the abstract class Light.
 * Ambient light is an omnidirectional, fixed intensity and fixed color type of light.
 */
public class AmbientLight extends Light{
    //private field
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);

    /**
     * Constructor that takes a Color object and an attenuation coefficient (Double3) and calculates
     * the color object scaled by the attenuation coefficient, by calling
     * the base constructor.
     *
     * @param color Color of the ambient light
     * @param k     Attenuation coefficient
     */
    public AmbientLight(Color color, Double3 k) {
        super(color.scale(k));
    }

    /**
     * Constructor that takes a Color object and an attenuation coefficient (Double) and calculates
     * the color object scaled by the attenuation coefficient, by calling
     * the base constructor.
     *
     * @param color Color of the ambient light
     * @param k     Attenuation coefficient
     */
    public AmbientLight(Color color, Double k) {
        super(color.scale(k));
    }

    /**
     * Default constructor, set the intensity field to Color. BLACK
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

}
