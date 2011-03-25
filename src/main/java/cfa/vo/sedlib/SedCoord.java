package cfa.vo.sedlib;

/**
 * <p>Java class for sedCoord complex type.
 * 
 * 
 */
public class SedCoord
    extends SedBaseCoord
{

    protected DoubleParam value;
    protected Accuracy accuracy;
    protected DoubleParam resolution;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getValue() {
        return value;
    }

    /**
     * Creates value property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createValue() {
        if (this.value == null)
           this.setValue (new DoubleParam ());
        return this.value;
    }


    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setValue(DoubleParam value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Gets the value of the accuracy property.
     * 
     * @return
     *     either null or
     *     {@link Accuracy }
     *     
     */
    public Accuracy getAccuracy() {
        return accuracy;
    }

    /**
     * Creates accuracy property if one does not exist.
     *
     * @return
     *     {@link Accuracy }
     *
     */
    public Accuracy createAccuracy() {
        if (this.accuracy == null)
           this.setAccuracy (new Accuracy ());
        return this.accuracy;
    }


    /**
     * Sets the value of the accuracy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Accuracy }
     *     
     */
    public void setAccuracy(Accuracy value) {
        this.accuracy = value;
    }

    public boolean isSetAccuracy() {
        return (this.accuracy!= null);
    }

    /**
     * Gets the value of the resolution property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getResolution() {
        return resolution;
    }

    /**
     * Creates resolution property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createResolution() {
        if (this.resolution == null)
           this.setResolution (new DoubleParam ());
        return this.resolution;
    }


    /**
     * Sets the value of the resolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setResolution(DoubleParam value) {
        this.resolution = value;
    }

    public boolean isSetResolution() {
        return (this.resolution!= null);
    }

}
