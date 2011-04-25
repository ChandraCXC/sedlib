package cfa.vo.sedlib;

/**
 * <p>Java class for spaceFrame complex type.
 * 
 * 
 */
public class SpaceFrame
    extends CoordFrame
{

    protected DoubleParam equinox;

    @Override
    public Object clone ()
    {
        SpaceFrame spaceFrame = (SpaceFrame) super.clone();
        

        if (this.isSetEquinox ())
            spaceFrame.equinox = (DoubleParam)this.equinox.clone ();

        return spaceFrame;
    }


    /**
     * Gets the value of the equinox property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getEquinox() {
        return equinox;
    }

    /**
     * Creates equinox property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createEquinox() {
        if (this.equinox == null)
           this.setEquinox (new DoubleParam ());
        return this.equinox;
    }


    /**
     * Sets the value of the equinox property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setEquinox(DoubleParam value) {
        this.equinox = value;
    }

    public boolean isSetEquinox() {
        return (this.equinox!= null);
    }

}
