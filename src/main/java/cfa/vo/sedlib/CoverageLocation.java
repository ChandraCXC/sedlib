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

import java.util.List;
import java.util.ArrayList;

/**
 * <p>Java class for coverageLocation complex type.
 * 
 * 
 */
public class CoverageLocation
    extends AbstractSedCoord<DoubleParam[], DoubleParam>
{

    @Override
    public DoubleParam[] createValue() {
        if(!isSetValue()) {
            setValue(new DoubleParam[2]);
        }
        
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
        CoverageLocation location = (CoverageLocation) super.clone();

        if(isSetResolution())
            location.setResolution((DoubleParam)getResolution().clone());
        
        if(isSetValue())
            location.setValue((DoubleParam[])getValue().clone());

        return location;
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
	    if (create)
		value = this.createAccuracy().getValueByUtype( utypeNum, create );
	    else
		value = this.getAccuracy().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isResolutionUtype( utypeNum ) )
	{
	    if (create)
		value = this.createResolution();
	    else
		value = this.getResolution();
	}
	else if ( Utypes.isValueUtype( utypeNum ) )
	{
	    // send an ArrayList, not array of Param
	    DoubleParam paramArray[];
	    ArrayList<DoubleParam> params = new ArrayList<DoubleParam>();
		    
	    if ( this.isSetValue() ) 
		paramArray = this.getValue();
	    else
	    {
		// MCD NOTE: This should happen in createValue()
		paramArray = this.createValue();
		for ( int ii=0; ii < paramArray.length; ii++ )
		    paramArray[ii] = new DoubleParam();
	    }
	    params.ensureCapacity(paramArray.length);
	    for (DoubleParam item : paramArray)
	    {
		if ( item != null)
		{
		    params.add( (DoubleParam)item.clone() );
		}
		else
		{
		    params.add(null);
		}
	    }
	    value = params;
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
	else if ( Utypes.isResolutionUtype( utypeNum ) )
	{
	    this.setResolution( (DoubleParam)value );
	}
	else if ( Utypes.isValueUtype( utypeNum ) )
	{
	    // expects an ArrayList, convert to array of Param
	    DoubleParam paramArray[];
	    List<DoubleParam> params = (List<DoubleParam>)value;

	    // get current value array
	    paramArray = this.createValue();
	    if ( params == null )
	    {
		// null input values initializes with empty params
		// (from SetMetaParamList)
		for ( int ii=0; ii < paramArray.length; ii++ )
		    paramArray[ii] = new DoubleParam();
	    }
	    else
	    {
		// copy list content to value array
		for (int ii=0; ii<params.size(); ii++)
		{
		    if (ii == paramArray.length)
			break;
			
		    DoubleParam item = (DoubleParam)params.get(ii);
		    if ( item != null)
		    {
			paramArray[ii] = (DoubleParam)item.clone();
		    }
		    else
		    {
			paramArray[ii] = null;
		    }
		}
	    }
	}
    }

}
