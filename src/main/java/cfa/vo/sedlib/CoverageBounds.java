package cfa.vo.sedlib;

/**
 * <p>Java class for coverageBounds complex type.
 * 
 * 
 */
public class CoverageBounds
    extends Group
{

    protected DoubleParam extent;
    protected Interval range;

    @Override
    public Object clone ()
    {
        CoverageBounds coverageBounds = (CoverageBounds) super.clone();
        

        if (this.isSetExtent ())
            coverageBounds.extent = (DoubleParam)this.extent.clone ();
        if (this.isSetRange ())
            coverageBounds.range = (Interval)this.range.clone ();
        return coverageBounds;
    }


    /**
     * Gets the value of the extent property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getExtent() {
        return extent;
    }

    /**
     * Creates extent property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createExtent() {
        if (this.extent == null)
           this.setExtent (new DoubleParam ());
        return this.extent;
    }


    /**
     * Sets the value of the extent property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setExtent(DoubleParam value) {
        this.extent = value;
    }

    public boolean isSetExtent() {
        return (this.extent!= null);
    }

    /**
     * Gets the value of the range property.
     * 
     * @return
     *     either null or
     *     {@link Interval }
     *     
     */
    public Interval getRange() {
        return range;
    }

    /**
     * Creates extent property if one does not exist.
     *
     * @return
     *     {@link Interval }
     *
     */
    public Interval createRange() {
        if (this.range == null)
           this.setRange (new Interval ());
        return this.range;
    }


    /**
     * Sets the value of the range property.
     * 
     * @param value
     *     allowed object is
     *     {@link Interval }
     *     
     */
    public void setRange(Interval value) {
        this.range = value;
    }

    public boolean isSetRange() {
        return (this.range!= null);
    }

}
