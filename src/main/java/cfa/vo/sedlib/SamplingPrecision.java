package cfa.vo.sedlib;

/**
 * <p>Java class for samplingPrecision complex type.
 * 
 * 
 */
public class SamplingPrecision
    extends Group
{

    protected SamplingPrecisionRefVal samplingPrecisionRefVal;
    protected DoubleParam sampleExtent;

    /**
     * Gets the value of the samplingPrecisionRefVal property.
     * 
     * @return
     *     either null or
     *     {@link SamplingPrecisionRefVal }
     *     
     */
    public SamplingPrecisionRefVal getSamplingPrecisionRefVal() {
        return samplingPrecisionRefVal;
    }

    /**
     * Creates samplingPrecisionRefVal property if one does not exist.
     *
     * @return
     *     {@link SamplingPrecisionRefVal }
     *
     */
    public SamplingPrecisionRefVal createSamplingPrecisionRefVal() {
        if (this.samplingPrecisionRefVal == null)
           this.setSamplingPrecisionRefVal (new SamplingPrecisionRefVal ());
        return this.samplingPrecisionRefVal;
    }


    /**
     * Sets the value of the samplingPrecisionRefVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link SamplingPrecisionRefVal }
     *     
     */
    public void setSamplingPrecisionRefVal(SamplingPrecisionRefVal value) {
        this.samplingPrecisionRefVal = value;
    }

    public boolean isSetSamplingPrecisionRefVal() {
        return (this.samplingPrecisionRefVal!= null);
    }

    /**
     * Gets the value of the sampleExtent property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getSampleExtent() {
        return sampleExtent;
    }

    /**
     * Creates sampleExtent property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createSampleExtent() {
        if (this.sampleExtent == null)
           this.setSampleExtent (new DoubleParam ());
        return this.sampleExtent;
    }


    /**
     * Sets the value of the sampleExtent property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setSampleExtent(DoubleParam value) {
        this.sampleExtent = value;
    }

    public boolean isSetSampleExtent() {
        return (this.sampleExtent!= null);
    }

}
