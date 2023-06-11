package primitives;

/**
 * Class that represents the material of a geometric object.
 * The class is PDS.
 */
public class Material {
    //public fields
    /**
     * diffusive coefficient
     */
    public Double3 kD = Double3.ZERO;
    /**
     * specular coefficient
     */
    public Double3 kS = Double3.ZERO;
    /**
     * transparency coefficient
     */
    public Double3 kT = Double3.ZERO;
    /**
     * reflection coefficient
     */
    public Double3 kR = Double3.ZERO;
    public int nShininess = 0;

    public double Glossy=0;





    //region Getters
    /**
     * getting of nShininess
     * @return nShininess
     */
    public int getnShininess() {return nShininess;}
    /**
     * getting of kD
     * @return kD
     */
    public Double3 getkD() {return kD;}
    /**
     * getting of kS
     * @return kS
     */
    public Double3 getkS() {return kS;}
    /**
     * getting of kD
     * @return kD
     */
    public Double3 getkT() {return kT;}
    /**
     * getting of kD
     * @return kD
     */
    public Double3 getkR() {return kR;}
    //endregion


    /**
     * Setter for the kD field
     * @param kD parameter for the kD field
     * @return The object itself
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Setter for the kD field
     * @param kD double parameter for all three values in the the kD field
     * @return The object itself
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Setter for the kS field
     * @param kS parameter for the kS field
     * @return The object itself
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Setter for the kS field
     * @param kS double parameter for all three values in the the kS field
     * @return The object itself
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }
    /**
     * Setter for the kT field
     * @param kT parameter for the kT field
     * @return The object itself
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Setter for the kT field
     * @param kT double parameter for all three values in the kT field
     * @return The object itself
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Setter for the kR field
     * @param kR parameter for the kR field
     * @return The object itself
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Setter for the kR field
     * @param kR double parameter for all three values in the kR field
     * @return The object itself
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Setter for the nShininess field
     * @param nShininess parameter for the nShininess field
     * @return The object itself
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     *
     * @param Glossy parameter for the glossy field
     * @return The object itself
     */
    public Material setGlossy(double Glossy) {
        this.Glossy = Glossy;
        return this;
    }
    //*********Getters for the fields*********
    public Double3 getKs() {
        return kS;
    }

    public Double3 getKd() {
        return kD;
    }

    public int getShininess() {
        return nShininess;
    }

    public Double3 getKt() {
        return kT;
    }

    public Double3 getKr() {
        return kR;
    }


    public double getGlossy() {
        return Glossy;
    }


}
