package cfa.vo.sedlib;

/**
 * <p>Java class for sedQuantity complex type.
 * 
 * 
 */
public class SedQuantity
    extends SedCoord
{

    private IntParam quality;

    @Override
    public Object clone ()
    {
        SedQuantity sedQuantity = (SedQuantity) super.clone();
        
        if (this.isSetQuality ())
            sedQuantity.quality = (IntParam)this.quality.clone ();

        return sedQuantity;
    }


    /**
     * Gets the value of the quality property.
     * 
     * @return
     *     either null or
     *     {@link IntParam }
     *     
     */
    public IntParam getQuality() {
        return quality;
    }

    /**
     * Creates quality property if one does not exist.
     *
     * @return
     *     {@link IntParam }
     *
     */
    public IntParam createQuality() {
        if (this.quality == null)
           this.setQuality (new IntParam ());
        return this.quality;
    }


    /**
     * Sets the value of the quality property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntParam }
     *     
     */
    public void setQuality(IntParam value) {
        this.quality = value;
    }

    public boolean isSetQuality() {
        return (this.quality!= null);
    }

}
