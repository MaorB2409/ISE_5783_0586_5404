package scene;

import lighting.AmbientLight;
import geometries.Geometries;
//import lighting.Light;
import lighting.LightSource;
import primitives.Color;
import primitives.Point;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    private final String name;
    private final Color background;
    private final Geometries geometries;
    private final List<LightSource> lights;
    private AmbientLight ambientLight;

    private Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
        lights = builder.lights;
    }

    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public List<LightSource> getLights() {
        return lights;
    }

    public static class SceneBuilder {

        private final String name;
        private List<LightSource> lights = new LinkedList<>();
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        public SceneBuilder(String name) {
            this.name = name;
        }

        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        public Scene build() {
            //            validateObject(scene);
            return new Scene(this);
        }

        private void validateObject(Scene scene) {
            //nothing to do
        }

        public SceneBuilder readXmlFile(String filename) {
            //to do
            return this;
        }

















//    private final String name;               //name of the scene
//    private final Color background;          //the background color
//    private final AmbientLight ambientLight; //the ambient light
//    private final Geometries geometries;     //geometries in the scene
//    public List<LightSource> lights= new LinkedList<>();
//
//    public List<LightSource> getLights() {
//        return lights;
//    }
//
//    public Scene setLights(List<LightSource> lights) {
//        this.lights = lights;
//        return this;
//    }
//
//    private Scene(SceneBuilder builder){
//        name = builder.name;
//        background = builder.background;
//        ambientLight = builder.ambientLight;
//        geometries = builder.geometries;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Color getBackground() {
//        return background;
//    }
//
//    public AmbientLight getAmbientLight() {
//        return ambientLight;
//    }
//
//    public Geometries getGeometries() {
//        return geometries;
//    }
//
//    public void setAmbientLight(AmbientLight ambientLight) {
//
//    }
//
//    /**
//     * the builder class
//     */
//    public static class SceneBuilder {
//
//        private final String name;
//        private Color background = Color.BLACK; //default color
//        private AmbientLight ambientLight = new AmbientLight();
//        private Geometries geometries = new Geometries();
//
//        /**
//         * constructor
//         * @param name name of the scene
//         */
//        public SceneBuilder(String name){
//            this.name = name;
//        }
//
//        //chaining methods
//
//        public SceneBuilder setBackground(Color background) {
//            this.background = background;
//            return this;
//        }
//
//        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
//            this.ambientLight = ambientLight;
//            return this;
//        }
//
//        public SceneBuilder setGeometries(Geometries geometries) {
//            this.geometries = geometries;
//            return this;
//        }
//
//        public Scene build(){
//            Scene scene = new Scene(this);
//            return scene;
//
//        }
    }
}