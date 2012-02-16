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


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************

    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isEquinoxUtype( utypeNum ) )
	{
	    if (create)
		value = this.createEquinox();
	    else
		value = this.getEquinox();
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
	if ( Utypes.isEquinoxUtype( utypeNum ) )
	{
	    this.setEquinox( (DoubleParam)value );
	}
	else
	{
	    super.setValueByUtype( utypeNum, value );
	}

	return;
    }

}
