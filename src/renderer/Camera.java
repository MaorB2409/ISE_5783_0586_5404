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

            //rendering the image
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    castRay(nX, nY, i, j);
                }
            }
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
        Ray ray = constructRay(nX, nY, jrow, icol); //build a ray with the given parameters
        Color pixelColor = rayTracer.traceRay(ray);
        imageWriter.writePixel(jrow, icol, pixelColor); //color the pixel
    }


    /**
     * chaining functios
     */
    public void printGrid(int interval, Color color) {
        imageWriter.printGrid(interval, color);
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
