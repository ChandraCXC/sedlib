package cfa.vo.sedlib;

/**
 * <p>Java class for samplingPrecisionRefVal complex type.
 * 
 * 
 */
public class SamplingPrecisionRefVal
    extends Group
{

    protected DoubleParam fillFactor;

    @Override
    public Object clone ()
    {
        SamplingPrecisionRefVal smp = (SamplingPrecisionRefVal) super.clone();
        

        if (this.isSetFillFactor ())
            smp.fillFactor = (DoubleParam)this.fillFactor.clone ();

        return smp;
    }


    /**
     * Gets the value of the fillFactor property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getFillFactor() {
        return fillFactor;
    }

    /**
     * Creates fillFactor property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createFillFactor() {
        if (this.fillFactor == null)
           this.setFillFactor (new DoubleParam ());
        return this.fillFactor;
    }


    /**
     * Sets the value of the fillFactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setFillFactor(DoubleParam value) {
        this.fillFactor = value;
    }

    public boolean isSetFillFactor() {
        return (this.fillFactor!= null);
    }

}
