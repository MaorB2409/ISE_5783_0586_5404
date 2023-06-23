import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class FinalTest {

    @Test
    public void renderImage() throws IllegalAccessException {
        //parameters//
        Color Pyramid = new Color(74, 34, 4);
        Color Sky = new Color(0, 51, 102);
        Color moonlight = new Color(183,201,226);
        Color sand = new Color(120, 87, 33);
        Color dune1 = new Color(120, 87, 33);
        Color dune2 = new Color(101, 67, 33);
        Color dune3 = new Color(101, 67, 33);

        //scene and camera of the image//
        Scene scene = new Scene.SceneBuilder("Test scene").setBackground(Sky).build();
        Camera camera = new Camera(new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000); //

        scene.setAmbientLight(
                new AmbientLight(
                        new Color(java.awt.Color.WHITE),
                        new Double3(0.15)));//white light for the scene

        //geometries for the picture//
        scene.getGeometries().add( //adding all the geometries of the scene
                //sand
                new Polygon(
                        new Point(-300, -80, -1100),
                        new Point(-300, -80, 200),
                        new Point(300, -80, 200),
                        new Point(300, -80, -1100))
                        .setEmission(sand)
                        .setMaterial(
                                new Material()
                                        .setKd(0.25)
                                        .setKs(0.5)
                                        .setShininess(30)),
                // Big Pyramid
                new Triangle(
                        new Point(0, -80, -400),
                        new Point(-100, -80, -1000),
                        new Point(100, -80, -1000))
                        .setEmission(Pyramid)
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(20)),
                new Triangle(
                        new Point(0, -80, -400),
                        new Point(-100, -80, -1000),
                        new Point(0, 40, -700))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(20)),
                new Triangle(
                        new Point(0, -80, -400),
                        new Point(100, -80, -1000),
                        new Point(0, 40, -700))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(20)),
                new Triangle(
                        new Point(-100, -80, -1000),
                        new Point(100, -80, -1000),
                        new Point(0, 40, -700))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(20)),

                //small pyramid 1
                new Triangle(
                        new Point(-80, -320, -100),
                        new Point(-150, -320, -250),
                        new Point(80, -320, -250))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(30)),
                new Triangle(
                        new Point(-80, -320, -100),
                        new Point(-150, -320, -250),
                        new Point(-80, 5, -175))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(30)),
                new Triangle(
                        new Point(-80, -320, -100),
                        new Point(80, -320, -250),
                        new Point(-80, 5, -175))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(30)),
                new Triangle(
                        new Point(-150, -320, -250),
                        new Point(80, -320, -250),
                        new Point(-80, 5, -175))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(30)),
                //small pyramid 2
                new Triangle(
                        new Point(160, -320, -250),
                        new Point(300, -320, -700),
                        new Point(-150, -320, -700))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(30)),
                new Triangle(
                        new Point(160, -320, -250),
                        new Point(300, -320, -700),
                        new Point(160, 5, -475))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(30)),
                new Triangle(
                        new Point(160, -320, -250),
                        new Point(-150, -320, -700),
                        new Point(160, 5, -475))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(30)),
                new Triangle(
                        new Point(300, -320, -700),
                        new Point(-150, -320, -700),
                        new Point(160, 5, -475))
                        .setEmission(Pyramid)
                        .setMaterial(
                                new Material()
                                        .setKd(0.5)
                                        .setKs(0.5)
                                        .setShininess(30)),

                //moon
                new Sphere(new Point(-70, 55, -11), 15d) //
                        .setEmission(new Color(241,235,156)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                //Stars
                new Sphere(new Point(-90, 80, -100), 0.66d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-65, 50, -100), 0.7d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-60, 75, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-50 , 66,-100), 0.55d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-10, 70, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-45, 55, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-20, 70, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-25, 66, -100), 0.66d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(65, 50, -100), 0.7d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(90, 75, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(50 , 100,-100), 0.55d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(40, 60, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(90, 85, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(100, 90, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-90, 80, -100), 0.66d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-65, 50, -100), 0.7d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-60, 75, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-50 , 66,-100), 0.55d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-10, 70, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(15, 55, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-20, 70, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-5, 66, -100), 0.66d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(30, 80, -100), 0.7d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

//                new Sphere(new Point(-75, -10, -100), 0.6d)
//                        .setEmission(new Color(241,235,156))
//                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-95 , 95,-100), 0.55d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(40, 60, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-100, 90, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(70, 90, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-68, 30, -100), 0.66d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-65, 50, -100), 0.7d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(50 , 46,-100), 0.55d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-10, 35, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(11, 25, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-20, 26, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-40, -16, -100), 0.66d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(25, 25, -100), 0.7d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(-95, -20, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(30 , 20,-100), 0.55d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(40, 60, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(39, 10, -100), 1d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(new Point(90, 14, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(30)),

                new Sphere(
                        new Point(-90, 16, -100), 0.66d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-66, 53, -100), 0.7d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-43, 18, -100), 0.6d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(73 , 31,-100), 0.55d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-15, 33, -100), 1d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-18, 27, -100), 0.6d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-100, 5, -100), 0.66d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(100, 12, -100), 0.7d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(75, 5, -100), 0.6d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-35 , 10,-100), 0.55d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(100, 37, -100), 1d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-40, 40, -100), 1d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(20, 38, -100), 0.6d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-11, 88, -100), 1d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-15, 95, -100), 1d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(new Point(66, -27, -100), 0.6d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-100, 5, -100), 0.66d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(97, -12, -100), 0.7d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-95 , -7,-100), 0.55d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(-90, -10, -100), 1d)
                        .setEmission(
                                new Color(241,235,156))
                        .setMaterial(
                                new Material()
                                        .setKd(0.2)
                                        .setKs(0.5)
                                        .setShininess(30)),

                new Sphere(
                        new Point(5, 100, -100), 0.6d)
                        .setEmission(new Color(241,235,156))
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.5)
                                .setShininess(30)),
                //pond sphere
                new Sphere(
                        new Point(30, -160, -80), 100d)
                        .setEmission(Sky)
                        .setMaterial(
                                new Material().setKd(0.25)
                                        .setKs(0.25)
                                        .setKr(1)
                                        .setShininess(10))
        );

        //lighting
        scene.getLights().add(
                new SpotLight(
                        new Color(700, 400, 400),
                        new Point(40, 40, 115),
                        new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));
        scene.getLights().add(
                new SpotLight(
                        new Color(700, 400, 400),
                        new Point(-50, 35, -750),
                        new Vector(1,1,4))
                        .setKl(4E-4).setKq(2E-5));
        scene.getLights().add(
                new PointLight(moonlight,
                        new Point(-200, 200, 0))
                        .setKl(0.0001).setKq(0.00001));
        scene.getLights().add(
                new DirectionalLight(
                        new Color(10,40,50),
                        new Vector(1,-0.5,1))
        );


        //render the image//
        camera.setImageWriter(new ImageWriter("finalTestImage", 600, 600))
                .setthreadsCount(4)//set the amount of threads to use
                .setadaptive(true)//use adaptive super sampling
                .setantiAliasingRays(10)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();


//        camera.setImageWriter(new ImageWriter("finalTestImageRotate", 600, 600)).rotateCamera(0,0,-10) //
//                .renderImage() //
//                .writeToImage();
    }
}




