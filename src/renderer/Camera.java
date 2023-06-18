package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;

import static primitives.Util.alignZero;
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
    private double distance;

    /**
     * object's actual width
     */
    private double width;

    /**
     * object's actual height
     */
    private double height;

    /**
     * center point of geometry
     */
    private Point centerPoint;

    private int antiAliasing=1;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private boolean adaptive = false;
    private int threadsCount = 1;

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

        this.vRight = this.vTo.crossProduct(this.vUp);
    }



    //region Getters/Setters
    /**
     * get of p0
     *
     * @return point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get of vRight
     *
     * @return vector
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * get of vUp
     *
     * @return vector
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * get of vTo
     *
     * @return vector
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * get of distance
     *
     * @return double
     */
    public double getDistance() {
        return distance;
    }

    /**
     * get of width
     *
     * @return double
     */
    public double getWidth() {
        return width;
    }

    /**
     * get of height
     *
     * @return double
     */
    public double getHeight() {
        return height;
    }

    /**
     * get of centerPoint
     *
     * @return point
     */
    public Point get_centerVPPoint() {
        return centerPoint;
    }

    /**
     * set the view plane size
     *
     * @param width  the width of the view plane
     * @param height the height of the view plane
     * @return this camera (like builder pattern)
     * @throws IllegalArgumentException if width or height equal to 0
     */
    public Camera setVPSize(double width, double height) {
        if (isZero(width) || isZero(height)) {
            throw new IllegalArgumentException("width or height cannot be zero");
        }

        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * set the distance from the camera to the view plane
     *
     * @param distance the distance
     * @return this camera (like builder pattern)
     * @throws IllegalArgumentException if distance = 0
     */
    public Camera setVPDistance(double distance) {
        if (isZero(distance)) {
            throw new IllegalArgumentException("distance cannot be zero");
        }

        this.distance = distance;
        // every time that we chang the distance from the view plane
        // we will calculate the center point of the view plane aging
        centerPoint = p0.add(vTo.scale(this.distance));
        return this;
    }

    /**
     * setter for imageWriter
     *
     * @param imageWriter
     * @return the camera with set image writer for the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     *
     * @param rayTracer
     * @return the camera with set rayTracer for the camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     *
     * @param antiAliasing
     * @return the camera with set antiAliasing for the camera
     */
    public Camera setantiAliasing(int antiAliasing) {
        this.antiAliasing = antiAliasing;
        return this;
    }
    /**
     *
     * @param adaptive
     * @return the camera with set adaptive for the camera
     */
    public Camera setadaptive(boolean adaptive) {
        this.adaptive = adaptive;
        return this;
    }
    /**
     *
     * @param threadsCount
     * @return the camera with set threadsCount for the camera
     */
    public Camera setthreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
        return this;
    }



    // ****** Operations ******* //

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
        if (p0 == null || vRight == null
                || vUp == null || vTo == null || distance == 0
                || width == 0 || height == 0 || centerPoint == null
                || imageWriter == null || rayTracer == null) {
            throw new MissingResourceException("Missing camera data", Camera.class.getName(), null);
        }
        Pixel.initialize(imageWriter.getNy(), imageWriter.getNx(), 1);


        if (!adaptive) {
            while (threadsCount-- > 0) {
                new Thread(() -> {
                    for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                        imageWriter.writePixel(pixel.col, pixel.row, rayTracer.TraceRays(constructRays(imageWriter.getNx(), imageWriter.getNy(), pixel.col, pixel.row, antiAliasing)));
                }).start();
            }
            Pixel.waitToFinish();
        } else {
            while (threadsCount-- > 0) {
                new Thread(() -> {
                    for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                        imageWriter.writePixel(pixel.col, pixel.row, AdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), pixel.col, pixel.row, antiAliasing));
                }).start();
            }
            Pixel.waitToFinish();
        }
        return this;

    }




    /**
     * Checks the color of the pixel with the help of individual rays and averages between them and only
     * if necessary continues to send beams of rays in recursion
     * @param nX Pixel length
     * @param nY Pixel width
     * @param j The position of the pixel relative to the y-axis
     * @param i The position of the pixel relative to the x-axis
     * @param numOfRays The amount of rays sent
     * @return Pixel color
     */
    private Color AdaptiveSuperSampling(int nX, int nY, int j, int i,  int numOfRays)  {
        Vector Vright = vRight;
        Vector Vup = vUp;
        Point cameraLoc = this.getP0();
        int numOfRaysInRowCol = (int)Math.floor(Math.sqrt(numOfRays));
        if(numOfRaysInRowCol == 1)  return rayTracer.traceRay(constructRayThroughPixel(nX, nY, j, i));

        Point pIJ = getCenterOfPixel(nX, nY, j, i);

        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);


        double PRy = rY/numOfRaysInRowCol;
        double PRx = rX/numOfRaysInRowCol;
        return rayTracer.AdaptiveSuperSamplingRec(pIJ, rX, rY, PRx, PRy,cameraLoc,Vright, Vup,null);
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
     * get the center point of the pixel in the view plane
     *
     * @param nX number of pixels in the width of the view plane
     * @param nY number of pixels in the height of the view plane
     * @param j  index row in the view plane
     * @param i  index column in the view plane
     * @return the center point of the pixel
     */
    private Point getCenterOfPixel(int nX, int nY, int j, int i) {
        // calculate the ratio of the pixel by the height and by the width of the view plane
        // the ratio Ry = h/Ny, the height of the pixel
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);

        // Xj = (j - (Nx -1)/2) * Rx
        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX);
        // Yi = -(i - (Ny - 1)/2) * Ry
        double yI = alignZero(-(i - ((nY - 1d) / 2d)) * rY);

        Point pIJ = centerPoint;

        if (!isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI));
        }
        return pIJ;
    }



    /**
     * construct ray through a pixel in the view plane
     * nX and nY create the resolution
     *
     * @param nX number of pixels in the width of the view plane
     * @param nY number of pixels in the height of the view plane
     * @param j  index row in the view plane
     * @param i  index column in the view plane
     * @return ray that goes through the pixel (j, i)  Ray(p0, Vi,j)
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point pIJ = getCenterOfPixel(nX, nY, j, i); // center point of the pixel

        //Vi,j = Pi,j - P0, the direction of the ray to the pixel(j, i)
        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }


    /**
     * Creates a beam of rays into a square grid
     * @param nX Pixel length
     * @param nY Pixel width
     * @param j Position the pixel on the y-axis inside the grid
     * @param i Position the pixel on the x-axis inside the grid
     * @param numOfRays The root of the number of beams sent per pixel
     * @return List of beams of rays
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i, int numOfRays) {
        if (numOfRays== 0) {
            throw new IllegalArgumentException("num Of Rays can not be 0");
        }
        if (numOfRays == 1) {
            return List.of(constructRayThroughPixel(nX, nY, j, i));
        }
        else {
            List<Ray> rays = new LinkedList<>();
            Point pIJ = getCenterOfPixel(nX, nY, j, i);

            double rY = alignZero(height / nY);
            // the ratio Rx = w/Nx, the width of the pixel
            double rX = alignZero(width / nX);

            double pY = alignZero(rY / numOfRays);
            double pX = alignZero(rX / numOfRays);
            Point PijTemP = pIJ;
            for (int p = 1; p < numOfRays; p++) {
                for (int m = 1; m < numOfRays; m++) {
                    PijTemP = pIJ.add(vRight.scale(pX * m)).add(vUp.scale(pY * p));
                    rays.add(new Ray(p0, PijTemP.subtract(p0).normalize()));
                }
            }


            return rays;
        }

    }


    /**
     * chaining functios
     */
    public void printGrid(int interval, Color color) {
        imageWriter.printGrid(interval, color);
    }

//    /**
//     * moving the camera from her location
//     * @param newPosition the new position of the camera
//     * @param newPointOfView new point of view of the camera
//     * @return the new camera from the new position to the new point of view
//     */
//    public Camera moveCamera(Point newPosition, Point newPointOfView) {
//        // the new vTo of the the camera
//        Vector new_vTo = newPointOfView.subtract(newPosition).normalize();
//        // the angle between the new vTo and the old
//        double theta = new_vTo.dotProduct(vTo);
//        // axis vector for the rotation
//        Vector k = vTo.crossProduct(new_vTo).normalize();
//
//        vTo = new_vTo;
//        p0 = newPosition;
//
//        return rotateCamera(theta, k);
//    }

    /**
     * Rotates the camera around the axes with the given angles
     *
     * @param x angles to rotate around the x axis
     * @param y angles to rotate around the y axis
     * @param z angles to rotate around the z axis
     * @return the current camera
     */
    public Camera rotateCamera(double x, double y, double z) {
        vTo = vTo.rotateX(x).rotateY(y).rotateZ(z);
        vUp = vUp.rotateX(x).rotateY(y).rotateZ(z);
        vRight = vTo.crossProduct(vUp);

        return this;
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