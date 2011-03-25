package cfa.vo.sedlib;

/**
 * <p>Java class for derivedData complex type.
 * 
 * 
 */
public class DerivedData
    extends Group
{

    protected DoubleParam snr;
    protected DoubleParam varAmpl;
    protected SedQuantity redshift;

    /**
     * Gets the value of the snr property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getSNR() {
        return snr;
    }

    /**
     * Creates SNR property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createSNR() {
        if (this.snr == null)
           this.setSNR (new DoubleParam ());
        return this.snr;
    }


    /**
     * Sets the value of the snr property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setSNR(DoubleParam value) {
        this.snr = value;
    }

    public boolean isSetSNR() {
        return (this.snr!= null);
    }

    /**
     * Gets the value of the varAmpl property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getVarAmpl() {
        return varAmpl;
    }

    /**
     * Creates varAmpl property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createVarAmpl() {
        if (this.varAmpl == null)
           this.setVarAmpl (new DoubleParam ());
        return this.varAmpl;
    }


    /**
     * Sets the value of the varAmpl property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setVarAmpl(DoubleParam value) {
        this.varAmpl = value;
    }

    public boolean isSetVarAmpl() {
        return (this.varAmpl!= null);
    }

    /**
     * Gets the value of the redshift property.
     * 
     * @return
     *     either null or
     *     {@link SedQuantity }
     *     
     */
    public SedQuantity getRedshift() {
        return redshift;
    }

    /**
     * Creates redshift property if one does not exist.
     *
     * @return
     *     {@link SedQuantity }
     *
     */
    public SedQuantity createRedshift() {
        if (this.redshift == null)
           this.setRedshift (new SedQuantity ());
        return this.redshift;
    }


    /**
     * Sets the value of the redshift property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedQuantity }
     *     
     */
    public void setRedshift(SedQuantity value) {
        this.redshift = value;
    }

    public boolean isSetRedshift() {
        return (this.redshift!= null);
    }

}
