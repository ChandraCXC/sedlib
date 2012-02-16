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
 * <p>Java class for sedCoord complex type.
 * 
 * 
 */
public class SedCoord
    extends AbstractSedCoord<DoubleParam, DoubleParam>
{

    @Override
    public DoubleParam createValue() {
        if(isSetValue()) {
            return getValue();
        }

        setValue(new DoubleParam());

        return getValue();
    }

    @Override
    public DoubleParam createResolution() {
        if(isSetResolution()) {
            return getResolution();
        }

	setResolution(new DoubleParam());
	return getResolution();
    }

    @Override
    public Object clone() {
        SedCoord sedCoord = (SedCoord) super.clone();

        if(isSetResolution())
            sedCoord.setResolution((DoubleParam)getResolution().clone());

        if(isSetValue())
            sedCoord.setValue((DoubleParam)getValue().clone());

        return sedCoord;
    }


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isAccuracyUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createAccuracy().getValueByUtype( utypeNum, create );
	    else
		value = this.getAccuracy().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isValueUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createValue();
	    else
		value = this.getValue();
	}
	else if ( Utypes.isResolutionUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createResolution();
	    else
		value = this.getResolution();
	}

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {

	if ( Utypes.isAccuracyUtype( utypeNum ) )
	{
	    this.createAccuracy().setValueByUtype( utypeNum, value );
	}
	else if ( Utypes.isValueUtype( utypeNum ) )
	{
	    this.setValue( (DoubleParam)value );
	}
	else if ( Utypes.isResolutionUtype( utypeNum ) )
	{
	    this.setResolution( (DoubleParam)value );
	}

	return;
    }

}
