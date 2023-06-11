package primitives;

public class Vector extends Point{

    /**
     * public constructor for Vector
     * @param x x coordinate of Point
     * @param y y coordinate of Point
     * @param z z coordinate of Point
     * @throws IllegalArgumentException in case of Vector (0,0,0)
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Entered a zero vector");
    }

    /**
     *
     * @param xyz Double3 xyz
     * @throws IllegalArgumentException if the arguments are illegal
     */
    Vector(Double3 xyz)  {
        //super(xyz);
        this(xyz.d1, xyz.d2, xyz.d3);
    }

    /**
     *
     * @param vector Vector to add
     * @return newV
     */
    public Vector add(Vector vector){
        Vector newV=new Vector(xyz.add(vector.xyz));
        return (newV);
    }

    /**
     *
     * @param num number to multiply the vector with
     * @return new vector after multiplied with vector
     */
    public Vector scale(double num){
        Vector newV=new Vector(xyz.scale(num));
        return newV;
    }

    /**
     *
     * @param vector vector to calculate dot product with
     * @return dot product with given Vector
     */
    public double dotProduct(Vector vector){
        double result=xyz.d1*vector.xyz.d1+xyz.d2*vector.xyz.d2+xyz.d3*vector.xyz.d3;
        return result;
    }

    /**
     *
     * @param vector vector to calculate cross product with
     * @return cross product with given Vector
     */
    public Vector crossProduct(Vector vector){
        return new Vector(this.xyz.d2*vector.xyz.d3-this.xyz.d3*vector.xyz.d2,this.xyz.d3*vector.xyz.d1-this.xyz.d1*vector.xyz.d3,
                this.xyz.d1*vector.xyz.d2-this.xyz.d2*vector.xyz.d1);
    }

    /**
     *
     * @return length squared
     */
    public double lengthSquared(){
        return (xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3);
    }

    /**
     *
     * @return square root of squared length (aka actual length)
     */
    public double length(){
        return (Math.sqrt(lengthSquared()));
    }

    /**
     *
     * @return newV
     */
    public Vector normalize(){
        Vector newV=new Vector(xyz.reduce(this.length()));
        return newV;
    }



    /**
     *Compares two vectors
     * @param o Vector for comparison
     * @return Boolean value
     */
    /**
     * rotate the vectors by Rodrigues' rotation formula:
     * vRot = V * cos(theta) + (K x V) * sin(theta) + K * (K*V) * (1 - cos(theta))
     * V is this vector
     * @param k the axis vector of rotation
     * @param cosTheta cos(theta)
     * @param sinTheta sin(theta)
     */
    public void rotateVector(Vector k,  double cosTheta, double sinTheta) {
        Vector vRot;
        if (cosTheta == 0d) {
            vRot = k.crossProduct(this).scale(sinTheta);
        }
        else {
            vRot = this.scale(cosTheta);
            if (sinTheta != 0d) {
                vRot = vRot.add(k.crossProduct(this).scale(sinTheta));
            }
        }
        xyz = vRot.normalize().xyz;
    }
//    /**
//     * Given a vector and an angle, rotate the vector about the given axis by the given angle
//     *
//     * @param axis The axis of rotation.
//     * @param theta the angle of rotation in degrees
//     * @return A rotated new vector.
//     */
//    public Vector rotateVector(Vector axis, double theta) {
//        double x = getX();
//        double y = getY();
//        double z = getZ();
//        double u = axis.getX();
//        double v = axis.getY();
//        double w = axis.getZ();
//        double v1 = u * x + v * y + w * z;
//        double thetaRad = Math.toRadians(theta);
//        double thetaCos = Math.cos(thetaRad);
//        double thetaSin = Math.sin(thetaRad);
//        double xPrime = u * v1 * (1d - thetaCos) + x * thetaCos + (-w * y + v * z) * thetaSin;
//        double yPrime = v * v1 * (1d - thetaCos) + y * thetaCos + (w * x - u * z) * thetaSin;
//        double zPrime = w * v1 * (1d - thetaCos) + z * thetaCos + (-v * x + u * y) * thetaSin;
//
//        return new Vector(xPrime, yPrime, zPrime);
//    }
}
