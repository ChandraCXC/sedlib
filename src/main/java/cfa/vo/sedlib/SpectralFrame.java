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


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************

    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isRedshiftUtype( utypeNum ) )
	{
	    if (create)
		value = this.createRedshift();
	    else
		value = this.getRedshift();
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
	if ( Utypes.isRedshiftUtype( utypeNum ) )
	{
	    this.setRedshift( (DoubleParam)value );
	}
	else
	{
	    super.setValueByUtype( utypeNum, value );
	}
    }

}
