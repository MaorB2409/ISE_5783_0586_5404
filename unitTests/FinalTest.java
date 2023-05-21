import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.LightSource;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.util.List;
public class FinalTest {
    private Scene scene = new Scene.SceneBuilder("Test scene").build();

    private Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(200, 200).setVPDistance(1000) //
            .setRayTracer(new RayTracerBasic(scene));


    @Test
    public void imageTest() throws IllegalAccessException {

        Point spotLocation = new Point(1,0,1);//////////////
        scene.getLights().add( //
                new SpotLight(new Color(400, 240, 0), spotLocation, new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));
        Color RED = new Color(255, 0 , 0);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));//white light for the scene
        scene.setBackground(RED);

        scene.getGeometries().add( //adding all the geometries of the scene
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Sphere(new Point(0, 0, -11), 30d) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.getLights().add( //
                new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        camera.setImageWriter(new ImageWriter("finalTestImage", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

}
