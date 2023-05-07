package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;

import static primitives.Util.isZero;

/**
 * Class Camera represent a view of the geometric world through the view plane (which represent the picture),
 * Through the view plane the camera captures the geometric world.
 * it produces graphic views of the objects using the view plane and rays and object intersections.
 * The rays converge according to the location of the pixel centers in the view plane.
 * directions of the camera to the right, up and front  (compared to the original x,y,z axis),
 * all vectors orthogonal to each other
 */
public class Camera {

    /**
     * _P0 - the camera location
     */
    private Point p0;

    /**
     * X axis vector
     */
    private Vector vTo;

    /**
     * _Vup - Y axis vector
     */
    private Vector vUp;

    /**
     * Z axis vector
     */
    private Vector vRight;

    /**
     * object's actual distance from the camera center
     */
    private int distance;

    /**
     * object's actual width
     */
    private double width;

    /**
     * object's actual height
     */
    private double height;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private double superSampling;
    private double aperture;
    private double focus;
    private double dofSampling;
    private boolean isMultithreading = true;
    private int numOfThreads = 4;

    /**
     * simple Camera constructor which get as input location point and two orthogonal vectors represent the direction
     *
     * @param p0  - the camera location
     * @param vTo - Y axis vector
     * @param vUp - X axis vector
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("vto  and vup are not orthogonal");
        }
        this.p0 = p0;

        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();

        vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * setter - chaining method
     *
     * @param distance - the object's actual distance from the camera center
     * @return the camera with the configured distance
     */
    public Camera setVPDistance(int distance) {
        this.distance = distance;
        return this;
    }

    /**
     * setter - chaining method
     *
     * @param width   - the width of the view plane
     * @param height- the height of the view plane
     * @return the camera with the configured view plane
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * setter for imageWriter
     *
     * @param imageWriter
     * @return the image writer for the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Enable Super Sampling feature, providing superSampling density
     *
     * @param density amount of rays per pixel width or height
     * @return the camera itself - for chaining
     */
    public Camera enableSuperSampling(int density) {
        this.superSampling = density;
        return this;
    }

    /**
     * Setter of builder patters
     * sets the multithreading
     * If set to 1, the render won't use the thread pool.
     * If set to 0, the thread pool will pick the number of threads.
     *
     * @param threads number of threads to use
     * @return the current render
     * @throws IllegalArgumentException when threads is less than 0
     */
    public Camera setMultithreading(int threads) {
        if (threads < -1)//threads cannot be less than zero
            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
        if(threads== -1){
            isMultithreading = false;
            return this;
        }
        isMultithreading = true;

        if (threads != 0)//any number that is not zero is acceptable
            numOfThreads = threads;

        else {//if number received was zero:
            int cores = Runtime.getRuntime().availableProcessors() - 2; //leave 2 spare threads
            numOfThreads = cores <= 2 ? 1 : cores;//if cores is smaller than 2 than threads is 1, i=otherwise it is equal to cores
        }
        return this;
    }

    /**
     * Enable Depthe of Field feature, providing aperture and focus distance
     *
     * @param aperture at the view plane
     * @param distance from view plane to focal plane
     * @param density  amount of rays per aperture window width or height
     * @return the camera itself - for chaining
     */
    public Camera enableDepthOfField(double aperture, double distance, int density) {
        this.aperture = aperture;
        this.focus = distance;
        this.dofSampling = density;
        return this;
    }
    // ***************** Operations ******************** //

    /**
     * this function gets the view plane size and a selected pixel,
     * and return the ray from the camera which intersects this pixel
     *
     * @param nX - amount of rows in view plane (number of pixels)
     * @param nY - amount of columns in view plane (number of pixels)
     * @param j  - X's index
     * @param i  - Y's index
     * @return - the ray which goes through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        //view plane center Point
        Point Pc = p0.add(vTo.scale(distance));

        //pixels ratios
        double Rx = width / nX;
        double Ry = height / nY;

        //Pij point[i,j] in view-plane coordinates
        Point Pij = Pc;

        //delta values for moving on the view=plane
        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (!isZero(Xj)) {
            Pij = Pij.add(vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(vUp.scale(Yi));
        }

        // vector from camera's eye in the direction of point(i,j) in the viewplane
        Vector Vij = Pij.subtract(p0);

        return new Ray(p0, Vij);

    }


    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
      * @return Camera after making changes
     */
    public Camera renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
//            if (isMultithreading) {
//                Pixel.initialize(nX, nY, 1);
//                while (numOfThreads-- > 0) {
//                    new Thread(() -> {
//                        for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
//                            castRay(nX, nY, pixel.row, pixel.col);
//                    }).start();
//                }
//               Pixel.waitToFinish();
//            } else {
            //rendering the image
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    castRay(nX, nY, i, j);
                }
            }
//            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        return this;
    }

    /**
     * Cast ray from camera in order to color a pixel
     *
     * @param nX   - resolution on X axis (number of pixels in row)
     * @param nY   - resolution on Y axis (number of pixels in column)
     * @param icol - pixel's column number (pixel index in row)
     * @param jrow - pixel's row number (pixel index in column)
     */
    private void castRay(int nX, int nY, int icol, int jrow) {
        Ray ray = constructRay(nX, nY, jrow, icol);
        Color pixelColor = rayTracer.traceRay(ray);
        imageWriter.writePixel(jrow, icol, pixelColor);
    }

    /**
     * Cast ray from camera in order to color a pixel
     *
     * @param nX   - resolution on X axis (number of pixels in row)
     * @param nY   - resolution on Y axis (number of pixels in column)
     * @param icol - pixel's column number (pixel index in row)
     * @param jrow - pixel's row number (pixel index in column)
     */
    private void castBeamRay(int nX, int nY, int icol, int jrow) {
        Ray mainRay = constructRay(nX, nY, jrow, icol);

        Color pixelColor = rayTracer.traceRay(mainRay);
        imageWriter.writePixel(jrow, icol, pixelColor);
    }

    /**
     * chaining functios
     */
    public void printGrid(int interval, Color color) {
        imageWriter.printGrid(interval, color);
    }


    /**
     * The function constructs a beam of rays from Camera location throw the pixel
     * (i,j) in the view plane - the ray starts at the pixel if Depth of Field is
     * activated!!!
     *
     * @param nX     number of pixels in a row of view plane
     * @param nY     number of pixels in a column of view plane
     * @param j      number of the pixel in a row
     * @param i      number of the pixel in a column
     * @param dist   distance from the camera to the view plane
     * @param width  view plane width
     * @param height view plane height
     * @return the beam of rays from pixel (if DoF is active) or from camera
     */
    public List<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i, double dist, double width, double height) {
        double rX = width / nX;
        double rY = height / nY;
        double xJ = (j - (nX - 1) / 2d) * rX;
        double yI = (i - (nY - 1) / 2d) * rY;
        Point pIJ = p0.add(vTo.scale(dist)); // the view plane center point
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(-yI)); // it's possible pIJ.subtract(_vUp.scale(yI));

        if (superSampling == 0)
            return constructFocalRays(pIJ);

        List<Ray> rays = new LinkedList<>();
        double y = -rY / 2d;
        double dY = rY / superSampling;
        double xStart = -rX / 2d;
        double dX = rX / superSampling;
        for (double row = superSampling; row >= 0; --row, y += dY) {
            double x = xStart;
            for (double col = superSampling; col >= 0; --col, x += dX) {
                Point p = pIJ;
                if (!isZero(x))
                    p = pIJ.add(vRight.scale(x));
                if (!isZero(y))
                    p = p.add(vUp.scale(y));
                rays.addAll(constructFocalRays(p));
            }
        }
        return rays;
    }

    private static Random rnd = new Random();

    /**
     * Create beam of rays from view plane aperture hole through focal point
     *
     * @param pnt point at View Plane
     * @return beam of rays
     */
    private List<Ray> constructFocalRays(Point pnt) {
        Vector v = pnt.subtract(p0);
        if (dofSampling == 0)
            return List.of(new Ray(p0, v));

        v.normalize();
        Point f = pnt.add(v.scale(focus / vTo.dotProduct(v)));

        List<Ray> rays = new LinkedList<>();
        for (double i = dofSampling; i > 0; --i) {
            double x = rnd.nextDouble() * 2 - 1;
            double y = Math.sqrt(1 - x * x);
            Point p = pnt;
            double mult = (rnd.nextDouble() - 0.5) * aperture;
            if (!isZero(x))
                p.add(vRight.scale(x * mult));
            if (!isZero(y))
                p.add(vUp.scale(y * mult));
            rays.add(new Ray(p, f.subtract(p)));
        }
        return rays;
    }

    /**
     *
     * @return Camera after changes
     */
    public Camera writeToImage() {
        imageWriter.writeToImage();
        return this;
    }
}
