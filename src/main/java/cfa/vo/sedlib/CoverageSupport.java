/**
 * Copyright (C) 2011 Smithsonian Astrophysical Observatory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cfa.vo.sedlib;

import java.util.List;
import java.util.ArrayList;

import cfa.vo.sedlib.common.Utypes;

/**
 * <p>Java class for coverageSupport complex type.
 * 
 * 
 */
public class CoverageSupport
    extends Group implements IAccessByUtype
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


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isAreaUtype( utypeNum ) )
	{
	    if (create)
		value = this.createArea();
	    else
		value = this.getArea();
	}
	else if ( Utypes.isExtentUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createExtent();
	    else
		value = this.getExtent();
	}
	else if ( Utypes.isRangeUtype( utypeNum ) )
	{
	    // make a new interval to put on the list
	    // MCD NOTE: NOT A GOOD interface.. this is what createRange should do,
	    //           with a different method for manipulating the list (createRanges)
	    this.createRange();
	    value = new Interval();
	}

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {
	if ( Utypes.isAreaUtype( utypeNum ) )
	{
	    this.setArea( (SkyRegion)value );
	}
	else if ( Utypes.isExtentUtype( utypeNum ) )
	{
	    this.setExtent( (DoubleParam)value );
	}
	else if ( Utypes.isRangeUtype( utypeNum ) )
	{
	    // Add range to list.
	    this.createRange().add( (Interval)value );
	}

	return;
    }

}
