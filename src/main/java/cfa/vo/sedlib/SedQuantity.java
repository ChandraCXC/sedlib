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


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isQualityUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createQuality();
	    else
		value = this.getQuality();
	}
	else
	{
	    value = super.getValueByUtype( utypeNum, create );
	}

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {

	if ( Utypes.isQualityUtype( utypeNum ) )
	{
	    this.setQuality( (IntParam)value );
	}
	else
	{
	    super.setValueByUtype( utypeNum, value );
	}

	return;
    }


}
