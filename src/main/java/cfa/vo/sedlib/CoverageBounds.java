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

import cfa.vo.sedlib.common.Utypes;

/**
 * <p>Java class for coverageBounds complex type.
 * 
 * 
 */
public class CoverageBounds
    extends Group implements IAccessByUtype
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

    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isExtentUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createExtent();
	    else
		value = this.getExtent();
	}
	else if ( Utypes.isMinUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createRange().createMin();
	    else
		value = this.getRange().getMin();
	}
	else if ( Utypes.isMaxUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createRange().createMax();
	    else
		value = this.getRange().getMax();
	}

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {

	if ( Utypes.isExtentUtype( utypeNum ) )
	{
            this.setExtent( (DoubleParam)value );
	}
	else if ( Utypes.isMinUtype( utypeNum ) )
	{
            this.createRange().setMin( (DoubleParam)value );
	}
	else if ( Utypes.isMaxUtype( utypeNum ) )
	{
            this.createRange().setMax( (DoubleParam)value );
	}
    }


}
