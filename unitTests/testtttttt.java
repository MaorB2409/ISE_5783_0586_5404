import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.awt.Color.*;

public class testtttttt {

    @Test
    public void renderImage() throws IllegalAccessException {
        Scene scene = new Scene.SceneBuilder("Test test scene").build();

        Camera camera = new Camera(new Point(-2500, 0, 200), new Vector(10, 0, 0), new Vector(0, 0, 1))
                .setVPSize(200, 1200).setVPDistance(1000);




        // Set the background color
        scene.getBackground().add(new Color(20, 20, 20));

        // Create the moon
        Sphere moon = new Sphere(new Point(5000, 400, 2600), 100);
        moon.setEmission(new Color(183, 201, 226));
        moon.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40));
        scene.getGeometries().add(moon);

        // Create the stars
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            double x = rand.nextDouble() * 10000 - 5000;
            double y = rand.nextDouble() * 800 - 400;
            double z = rand.nextDouble() * 5000;
            double size = rand.nextDouble() * 2 + 1;

            Sphere star = new Sphere(new Point(x, y, z), size);
            int intensity = rand.nextInt(100) + 155;
            star.setEmission(new Color(intensity, intensity, intensity));
            star.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40));
            scene.getGeometries().add(star);
        }

        // Create the sand
        Plane sand = new Plane(
                new Point(0, -600, 0),
                new Point(0, -650, 0),
                new Point(-400, 0, 0)
        );
        sand.setEmission(new Color(150, 105, 25));
        sand.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(5));
        scene.getGeometries().add(sand);

        // Create the pyramids
        Triangle pyramid1 = new Triangle(
                new Point(0, -200, 0),
                new Point(200, 0, 0),
                new Point(0, 0, 500)
        );
        pyramid1.setEmission(new Color(101, 67, 33));
        pyramid1.setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30));
        scene.getGeometries().add(pyramid1);

        Triangle pyramid2 = new Triangle(
                new Point(-500, -100, 0),
                new Point(-100, -200, 0),
                new Point(-200, 0, 400)
        );
        pyramid2.setEmission(new Color(101, 67, 33));
        pyramid2.setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30));
        scene.getGeometries().add(pyramid2);

        // Add more pyramids...

        // Create a spot light
        Point lightPosition = new Point(4000, -2000, 2000);
        Color lightColor = new Color(255, 255, 255);
        double lightKc = 1;
        double lightKl = 0.0005;
        double lightKq = 0.0005;
        SpotLight spotLight = new SpotLight(lightColor, new Point(4000, -2000, 2000), new Vector(-1, 1, -0.5));
        spotLight.setKq(lightKq);
        spotLight.setKl(lightKl);
        spotLight.setKc(lightKc);
        List<LightSource> lightSourceList = new ArrayList<>();
        lightSourceList.add(spotLight);
        scene.setLights(lightSourceList);



        //render image
        camera.setImageWriter(new ImageWriter("projectfocus", 1500, 1500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}




