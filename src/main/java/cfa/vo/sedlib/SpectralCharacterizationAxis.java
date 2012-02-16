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
 * <p>Java class for spectralCharacterizationAxis complex type.
 * 
 * 
 */

public class SpectralCharacterizationAxis
    extends CharacterizationAxis
{

    protected DoubleParam resPower;

    /**
     * Gets the value of the resPower property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getResPower() {
        return resPower;
    }

    /**
     * Creates resPower property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createResPower() {
        if (this.resPower == null)
           this.setResPower (new DoubleParam ());
        return this.resPower;
    }


    /**
     * Sets the value of the resPower property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setResPower(DoubleParam value) {
        this.resPower = value;
    }

    public boolean isSetResPower() {
        return (this.resPower!= null);
    }

    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isResolvingPowerUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createResPower();
	    else
		value = this.getResPower();
	}
	else
	    value = super.getValueByUtype( utypeNum, create );

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {
	if ( Utypes.isResolvingPowerUtype( utypeNum ) )
	{
	    this.setResPower( (DoubleParam)value );
	}
	else 
	    super.setValueByUtype( utypeNum, value );

	return;
    }
}
