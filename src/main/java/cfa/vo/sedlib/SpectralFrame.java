package cfa.vo.sedlib;

/**
 * <p>Java class for spectralFrame complex type.
 * 
 * 
 */
public class SpectralFrame
    extends CoordFrame
{

    protected DoubleParam redshift;

    @Override
    public Object clone ()
    {
        SpectralFrame spectralFrame = (SpectralFrame) super.clone();
        

        if (this.isSetRedshift ())
            spectralFrame.redshift = (DoubleParam)this.redshift.clone ();

        return spectralFrame;
    }


    /**
     * Gets the value of the redshift property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getRedshift() {
        return redshift;
    }

    /**
     * Creates redshift property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createRedshift() {
        if (this.redshift == null)
           this.setRedshift (new DoubleParam ());
        return this.redshift;
    }


    /**
     * Sets the value of the redshift property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setRedshift(DoubleParam value) {
        this.redshift = value;
    }

    public boolean isSetRedshift() {
        return (this.redshift!= null);
    }

}
