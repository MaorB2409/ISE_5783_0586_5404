import geometries.Plane;
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

public class FinalTest {


    @Test
    public void imageTest() throws IllegalAccessException {

        //parameters//
        Color Pyramid = new Color(101, 67, 33);
        Color Sky = new Color(0, 51, 102);
        Color moonlight = new Color(183,201,226);
        Color sand = new Color(150, 105, 25);
        Color dune1 = new Color(152, 118, 84);
        Color dune2 = new Color(111, 78, 55);
        Color dune3 = new Color(152, 118, 84);


        //scene and camera//
        Scene scene = new Scene.SceneBuilder("Test test scene").setBackground(Sky).build();
        Camera camera = new Camera(new Point(-2500, 0, 200), new Vector(10, 0, 0), new Vector(0, 0, 1))//
                .setVPSize(200, 1200).setVPDistance(1000);

        //sky
        Sphere moon =new Sphere(new Point(5000, 400, 2600),100d);
        Sphere st1 =new Sphere(new Point(6000, -400, 4000),5);
        Sphere st2 =new Sphere(new Point(4000, 500, 4500),5);
        Sphere st3 =new Sphere(new Point(5000, 300, 3000),5);
        Sphere st4 =new Sphere(new Point(5000, 400, 3000),5);
        Sphere st5 =new Sphere(new Point(5000, 400, 2000),5);
        Sphere st6 =new Sphere(new Point(5000, 400, 4000),5);
        Sphere st7 =new Sphere(new Point(5000, -400, 4500),5);
        Sphere st8 =new Sphere(new Point(5000, -600, 3000),5);
        Sphere st9 =new Sphere(new Point(6000, -500, 4000),5);
        Sphere st10 =new Sphere(new Point(6000, -200, 3000),5);
        Sphere st11 =new Sphere(new Point(3000, -150, 3050),5);
        Sphere st12 =new Sphere(new Point(8000, -800, 2000),5);
        Sphere st13 =new Sphere(new Point(2000, -300, 5000),5);
        Sphere st14 =new Sphere(new Point(2000, -450, 5000),5);
        Sphere st15 =new Sphere(new Point(6000, -300, 3890),5);
        Sphere st16 =new Sphere(new Point(5000, -300, 2444),5);
        Sphere st17 =new Sphere(new Point(2000, -200, 3456),5);
        Sphere st18 =new Sphere(new Point(2000, -250, 5666),5);
        Sphere st19 =new Sphere(new Point(6500, -400, 4000),5);
        Sphere st20 =new Sphere(new Point(4444, 500, 3000),5);
        Sphere st21 =new Sphere(new Point(5333, 300, 1000),5);
        Sphere st22 =new Sphere(new Point(5000, 200, 1111),5);
        Sphere st23 =new Sphere(new Point(1000, 100, 3050),5);
        Sphere st24 =new Sphere(new Point(800, 200, 3890),5);
        Sphere st25 =new Sphere(new Point(1500, 500, 3478),5);
        Sphere st26 =new Sphere(new Point(1234, 600, 2098),5);
        Sphere st27 =new Sphere(new Point(4322, 540, 1200),5);
        Sphere st28 =new Sphere(new Point(2222, 333, 1111),5);





        //create sand
        Sphere a1 = new Sphere(new Point(519.070390744984252, 440.107162582189176, -10), 50);
        Sphere a2 = new Sphere(new Point(568.560487306058349, 330.381775783034186, -20), 100);
        Sphere a3 = new Sphere(new Point(10507.452522044068246, 192.73059510366852, -300), 650);
        Sphere a4 = new Sphere(new Point(200500.713789922087472, 78.895843857937507, -200), 1450);
        Sphere a5 = new Sphere(new Point(1500.713789922087472, 78.895843857937507, -60), 117.774);
        Sphere a6 = new Sphere(new Point(10531.970492287884554, -117.758343954578777, -250), 320);
        Sphere a7 = new Sphere(new Point(10555.040695576198686, -2075.299697925167038, -50), 92.138);
        Sphere a8 = new Sphere(new Point(-10626.427425256983042, 450.751787923952179, -65), 102.8875);
        Sphere a9 = new Sphere(new Point(1600, 440.107162582189176, -100), 250);
        Sphere a10 = new Sphere(new Point(-40, 330.381775783034186, -5), 25);
        Sphere a11 = new Sphere(new Point(-507.452522044068246, 300, -40), 65);
        Sphere a12 = new Sphere(new Point(-160, 78.895843857937507, -80), 100);
        Sphere a13 = new Sphere(new Point(-700, 78.895843857937507, -250), 300);
        Sphere a14 = new Sphere(new Point(-650, -150, -50), 90);
        Sphere a15 = new Sphere(new Point(-90, -300, 0), 92);
        Sphere a16 = new Sphere(new Point(-150, -150.751787923952179, 0), 16);
        Sphere a17 = new Sphere(new Point(-25, 34, 0), 5);
        Sphere a18 = new Sphere(new Point(90, 450, 0), 19);
        Sphere a19 = new Sphere(new Point(420, 250, 0), 65);
        Sphere a20 = new Sphere(new Point(1640, 10, 0), 75);
        Sphere a21 = new Sphere(new Point(1800, 150, -50), 118);
        Sphere a22 = new Sphere(new Point(-670, 117, 0), 73);
        Sphere a23 = new Sphere(new Point(325, -75, -60), 92.138);
        Sphere a24 = new Sphere(new Point(-348, -162, -60), 102);
        Sphere a25 = new Sphere(new Point(-400, 440.107162582189176, 0), 16);
        Sphere a26 = new Sphere(new Point(-385, 721, 0), 64);
        Sphere a27 = new Sphere(new Point(-95, 100, 0), 38);
        Sphere a28 = new Sphere(new Point(-26, 30, 0), 40);
        Sphere a29 = new Sphere(new Point(1000, 78.895843857937507, -15), 62);
        Sphere a30 = new Sphere(new Point(-42, -100, 0), 34);
        Sphere a31 = new Sphere(new Point(-269, -742, 0), 79);
        Sphere a32 = new Sphere(new Point(-430, 150, 0), 23);

        Sphere a33 = new Sphere(new Point(-1150, 150, -15), 50);
        Sphere a34 = new Sphere(new Point(-900, 340, -350), 400);
        Sphere a35 = new Sphere(new Point(10090, 2450, -1300), 1550);
        Sphere a36 = new Sphere(new Point(-1420, 2500, -500), 600);
        Sphere a37 = new Sphere(new Point(-1640, -100, -50), 75);
        Sphere a38 = new Sphere(new Point(-1800, 150, -150), 200);
        Sphere a39 = new Sphere(new Point(-1670, -1107, -650), 730);
        Sphere a40 = new Sphere(new Point(-1325, -750, -850), 900);
        Sphere a41 = new Sphere(new Point(-1348, -162, -75), 102);
        Sphere a42 = new Sphere(new Point(-1400, 440.107162582189176, -7), 16);
        Sphere a43 = new Sphere(new Point(-1385, 721, -250), 320);
        Sphere a44 = new Sphere(new Point(10195, -3000, -1490), 1800);
        Sphere a45 = new Sphere(new Point(-126, 300, -340), 400);
        Sphere a46 = new Sphere(new Point(-1300, -780, -600), 620);
        Sphere a47 = new Sphere(new Point(-1942, -100, -240), 280);
        Sphere a48 = new Sphere(new Point(-1430, 150, -180), 235);
        Sphere a49 = new Sphere(new Point(-1269, -742, -350), 451);
        Sphere a50 = new Sphere(new Point(-1430, 150, -190), 230);



        //pyramids
        Triangle pyramidAWigA = new Triangle(new Point(0, -200, 0), new Point(200, 0, 0), new Point(0, 0, 500));
        Triangle pyramidAWigB = new Triangle(new Point(200, 0, 0), new Point(0, 200, 0), new Point(0, 0, 500));
        Triangle pyramidAWigC = new Triangle(new Point(0, 200, 0), new Point(-200, 0, 0), new Point(0, 0, 500));
        Triangle pyramidAWigD = new Triangle(new Point(-200, 0, 0), new Point(0, 0, 500), new Point(0, -200, 0));

        Triangle pyramidB1 = new Triangle(new Point(200, 0, 0), new Point(400, -200, 0), new Point(200, -200, 500));
        Triangle pyramidB2 = new Triangle(new Point(400, -200, 0), new Point(200, 0, 0), new Point(200, 200, 500));
        Triangle pyramidB3 = new Triangle(new Point(200, 200, 0), new Point(0, 0, 0), new Point(200, 200, 500));
        Triangle pyramidB4 = new Triangle(new Point(0, 0, 0), new Point(200, -200, 0), new Point(200, 200, 500));


        //scene geometries
        scene.getGeometries().add(
                new Plane(new Point(0, -600, 0), new Point(0, -650, 0), new Point(-400, 0, 0))
                        .setEmission(sand)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(5)),
                moon.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st1.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st2.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st3.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st4.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st5.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st6.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st7.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st8.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st9.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st10.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st11.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st12.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st13.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st14.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st15.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st16.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st17.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st18.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st19.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st20.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st21.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st22.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st23.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st24.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st25.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st26.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st27.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                st28.setEmission(moonlight)
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(40)),
                a1.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a2.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a3.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a4.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a5.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a6.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a7.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a8.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a9.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a10.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a11.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a12.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a13.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a14.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a15.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a16.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a17.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a18.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a19.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a20.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a21.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a22.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a23.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a24.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a25.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a26.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a27.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a28.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a29.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a30.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a31.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a32.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),

                a33.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a34.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a35.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a36.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a37.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a38.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a39.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a40.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a41.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a42.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a43.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a45.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a44.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a46.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a47.setEmission(dune3).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a48.setEmission(dune2).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a49.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),
                a50.setEmission(dune1).setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(5)),

                pyramidAWigA.setEmission(Pyramid).setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(30)),
                pyramidAWigB.setEmission(Pyramid).setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(30)),
                pyramidAWigC.setEmission(Pyramid).setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(30)),
                pyramidAWigD.setEmission(Pyramid).setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(30)),

                pyramidB1.setEmission(Pyramid).setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(30)),
                pyramidB2.setEmission(Pyramid).setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(30)),
                pyramidB3.setEmission(Pyramid).setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(30)),
                pyramidB4.setEmission(Pyramid).setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(30))


                );

        //scene lighting
        scene.getLights().add(new SpotLight(moonlight, new Point(5000, 400, 2600),
               new Vector(6000, 300, 100)).setKq(40)
        );



        //render image
        camera.setImageWriter(new ImageWriter("finalTestImage", 1500, 1500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();

    }

}
