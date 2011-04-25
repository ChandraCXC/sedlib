package cfa.vo.sedlib;

/**
 * <p>Java class for timeFrame complex type.
 * 
 * 
 */
public class TimeFrame
    extends CoordFrame
{

    protected DoubleParam zero;

    @Override
    public Object clone ()
    {
        TimeFrame timeFrame = (TimeFrame) super.clone();
        

        if (this.isSetZero ())
            timeFrame.zero = (DoubleParam)this.zero.clone ();

        return timeFrame;
    }


    /**
     * Gets the value of the zero property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getZero() {
        return zero;
    }

    /**
     * Creates zero property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createZero() {
        if (this.zero == null)
           this.setZero (new DoubleParam ());
        return this.zero;
    }


    /**
     * Sets the value of the zero property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setZero(DoubleParam value) {
        this.zero = value;
    }

    public boolean isSetZero() {
        return (this.zero!= null);
    }

}
