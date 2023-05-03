package lighting;

import primitives.*;

public class AmbientLight {
    //private field
    private final Color intensity;
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);

    /**
     * Constructor that takes a Color object and an attenuation coefficient (Double3) and return
     * the color object scaled by the attenuation coefficient.
     * @param color Color of the ambient light
     * @param double3 Attenuation coefficient
     */
    public AmbientLight(Color color, Double3 double3){
        this.intensity = color;
        this.intensity.scale(double3);
    }

    /**
     *
     * @param color Color of the ambient light
     * @param kA Attenuation coefficient
     */
    public AmbientLight(Color color, double kA){
        this.intensity = color;
        this.intensity.scale(kA);
        // super(color.scale(kA));
    }

    /**
     * Default constructor, set the intensity field to Color.BLACK
     */
    public AmbientLight(){
        this.intensity = Color.BLACK;
    }

    /**
     * Intensity field getter
     * @return intensity (Color)
     */
    public Color getIntensity(){
        return this.intensity;
    }
}
