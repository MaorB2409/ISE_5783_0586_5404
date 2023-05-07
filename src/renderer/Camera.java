package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

/**
 * Camera class to take the picture
 */
public class Camera {
    //position point
    Point p0;
    //3 camera directions
    private Vector vUp, vRight, vTo;
    //view plane attributes
    double width, height, distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;


    /**
     * Constructor to camera
     *
     * @param p0  camera position
     * @param vUp camera upward vector
     * @param vTo camera front vector
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        this.p0 = p0;
        //check if vUp and Vto are orthogonal.
        if (vUp.dotProduct(vTo) != 0) //if not, throw.
            throw new IllegalArgumentException("vUp and vTo are not orthogonal");
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = (vTo.crossProduct(vUp)).normalize();
    }

    /**
     * V up getter
     *
     * @return V up vector
     */
    public Vector getVup() {
        return vUp;
    }

    /**
     * V to getter
     *
     * @return V to vector
     */
    public Vector getVto() {
        return vTo;
    }

    /**
     * V right getter
     *
     * @return V right vector
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * P0 getter
     *
     * @return camera's position
     */
    public Point getP0() {
        return p0;
    }

    /**
     * height getter
     *
     * @return view plane's height
     */
    public double getHeight() {
        return height;
    }

    /**
     * width getter
     *
     * @return view plane's width
     */
    public double getWidth() {
        return width;
    }

    /**
     * distance getter
     *
     * @return view plane's distance from the camera
     */
    public double getDistance() {
        return distance;
    }

    /**
     * View plane size setter
     *
     * @param width  view plane's width
     * @param height view plane's height
     * @return the camera with changed view plane size
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * View plane's distance setter
     *
     * @param distance distance from camera
     * @return the camera with changed view plane distance
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * A method that generates a ray, starting at the point and going through
     * specific pixel in the view plane. Gets the resolution of the view plane and
     * the coordinates of the requested pixel as parameters.
     *
     * @param nX Horizontal component of the resolution. Number of partitions for the horizontal axis in the view plane.
     * @param nY Vertical component of the resolution. Number of partitions for the vertical axis in the view plane.
     * @param j The horizontal index of the pixel
     * @param i The vertical index of the pixel
     * @return Ray that goes through the requested pixel in the view plane
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pc = p0.add(vTo.scale(distance)); // center point of the view plane
        double pixelWidth = width / nX; // width of a pixel
        double pixelHeight = height / nY; // height of a pixel
        double pcX = (nX - 1) / 2.0; // center pixel value in x direction
        double pcY = (nY - 1) / 2.0; // center pixel value in y direction
        double rightDistance = (j - pcX) * pixelWidth; // x offset of j from the center pixel
        double upDistance = -1 * (i - pcY) * pixelHeight; // y offset of i from the center pixel

        Point pij = pc; // start at the center of the view plane

        // we need to check if the distance is zero, because we can't scale a vector by
        // zero
        if (rightDistance!=0) {
            // if the distance to move right is not zero, move right
            pij = pij.add(vRight.scale(rightDistance));
        }

        if (upDistance!=0) {
            // if the distance to move up is not zero, move up
            pij = pij.add(vUp.scale(upDistance));
        }

        // construct a ray from the camera origin in the direction of the pixel at (j,i)
        return new Ray(p0, pij.subtract(p0));
    }


    /**
     * Given a vector axis and a double theta, rotate the camera's up, right, and to vectors by theta radians about axis
     *
     * @param axis the axis about which the camera will be rotated
     * @param theta the angle of rotation in radians
     * @return the rotated camera.
     */
    public Camera turnCamera(Vector axis, double theta) {
        if (theta == 0) return this; //there is nothing to turn
        this.vUp = this.vUp.rotateVector(axis, theta);
        this.vRight = this.vRight.rotateVector(axis, theta);
        this.vTo = this.vTo.rotateVector(axis, theta);
        return this;
    }


    /**
     * Move the camera by the given amounts
     *
     * @param up the distance to move the camera up
     * @param right the distance to move right
     * @param to the distance to move the camera in the direction of the to vector
     * @return the moved camera.
     */
    public Camera moveCamera(double up, double right, double to) {
        if (up == 0 && right == 0 && to == 0) return this; //there is nothing to move
        if (up != 0) this.p0.add(this.vUp.scale(up));
        if (right != 0) this.p0.add(this.vRight.scale(right));
        if (to != 0) this.p0.add(this.vTo.scale(to));
        return this;
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * RayTracerBase setter
     *
     * @param rayTracerBase a Ray Tracer Base
     * @return the camera with modified Ray Tracer Base
     */
    public Camera setRayTracer(RayTracerBasic rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    /**
     *
     * @param nX resolution on X axis = number of pixels in row
     * @param nY resolution on Y axis = number of pixels in column
     * @param icol pixels column number
     * @param jrow pixels row number
     */
    private void castRay(int nX,int nY,int icol,int jrow){
        Ray ray=constructRay(nX,nY,icol,jrow);
        Color pixelColor=rayTracerBase.traceRay(ray);
        imageWriter.writePixel(jrow,icol,pixelColor);
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */

    public Camera renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracerBase == null) {
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
                    castRay(nX, nY, i, j);///////
                }
            }
//            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        return this;
    }


    /**
     * Prints a grid
     *
     * @param interval the interval of the distance between ech grid line
     * @param color    the color of the grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("One of the camera's attributes are missing", "imageWriter", "7");
        //move over the coordinates of the grid
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                //Coordinates of the net
                if (i % interval == 0 || j % interval == 0) {
                    //print in Red
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
    }


    /**
     * uses the writeToImage function by delegation of imageWriter
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("One of the camera's attributes are missing", "imageWriter", "7");
        imageWriter.writeToImage();
    }




}
