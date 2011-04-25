package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for coverageSupport complex type.
 * 
 * 
 */
public class CoverageSupport
    extends Group
{

    protected SkyRegion area;
    protected DoubleParam extent;
    protected List<Interval> range;

    @Override
    public Object clone ()
    {
        CoverageSupport coverageSupport = (CoverageSupport) super.clone();
        
        
        if (this.isSetArea ())
            coverageSupport.area = (SkyRegion)this.area.clone ();
        if (this.isSetExtent ())
            coverageSupport.extent = (DoubleParam)this.extent.clone ();
        if (this.isSetRange ())
        {
            coverageSupport.range = new ArrayList<Interval>();
            for (Interval interval : this.range)
                coverageSupport.range.add ((Interval)interval.clone ());
        }

        return coverageSupport;
    }


    /**
     * Gets the value of the area property.
     * 
     * @return
     *     either null or
     *     {@link SkyRegion }
     *     
     */
    public SkyRegion getArea() {
        return area;
    }

    /**
     * Creates area property if one does not exist.
     *
     * @return
     *     {@link SkyRegion }
     *
     */
    public SkyRegion createArea() {
        if (this.area == null)
           this.setArea (new SkyRegion ());
        return this.area;
    }


    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link SkyRegion }
     *     
     */
    public void setArea(SkyRegion value) {
        this.area = value;
    }

    public boolean isSetArea() {
        return (this.area!= null);
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
     * Gets the range list.
     *
     * @return List<Interval>
     *   {@link Interval}
     *
     */
    public List<Interval> getRange() {
        return this.range;
    }

    /**
     * Creates the range list if one does not exist.
     *
     * @return List<Interval>
     *   {@link Interval}
     *
     */
    public List<Interval> createRange() {
        if (this.range == null) {
            this.range = new ArrayList<Interval>();
        }
        return this.range;
    }
    public boolean isSetRange() {
        return (this.range!= null);
    }

    public void setRange(List<Interval> range) {
        this.range = range;
    }

}
