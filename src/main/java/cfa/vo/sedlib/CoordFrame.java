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
 * Simplification of STC version: RefPos is string
 * 
 * <p>Java class for coordFrame complex type.
 * 
 */
public class CoordFrame extends Group implements IAccessByUtype
{

    protected String name;
    protected String referencePosition;
    protected String id;
    protected String ucd;


    /**
     * Gets the value of the name property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Gets the value of the referencePosition property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getReferencePosition() {
        return referencePosition;
    }

    /**
     * Sets the value of the referencePosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencePosition(String value) {
        this.referencePosition = value;
    }

    public boolean isSetReferencePosition() {
        return (this.referencePosition!= null);
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
    }

    /**
     * Gets the value of the ucd property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getUcd() {
        return ucd;
    }

    /**
     * Sets the value of the ucd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUcd(String value) {
        this.ucd = value;
    }

    public boolean isSetUcd() {
        return (this.ucd!= null);
    }


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************

    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isIdUtype( utypeNum ) )
	{
	    if ( this.isSetId() )
		value = this.getId();
	    else
		value = new String();
	}
	else if ( Utypes.isNameUtype( utypeNum ) )
	{
	    if ( this.isSetName() )
		value = this.getName();
	    else
		value = new String();
	}
	else if ( Utypes.isUCDUtype( utypeNum ) )
	{
	    if ( this.isSetUcd() )
		value = this.getUcd();
	    else
		value = new String();
	}
	else if ( Utypes.isReferencePositionUtype( utypeNum ) )
	{
	    if ( this.isSetReferencePosition() )
		value = this.getReferencePosition();
	    else
		value = new String();
	}

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {
	if ( Utypes.isIdUtype( utypeNum ) )
	{
	    this.setId( (String)value );
	}
	else if ( Utypes.isNameUtype( utypeNum ) )
	{
	    this.setName( (String)value );
	}
	else if ( Utypes.isUCDUtype( utypeNum ) )
	{
	    this.setUcd( (String)value );
	}
	else if ( Utypes.isReferencePositionUtype( utypeNum ) )
	{
	    this.setReferencePosition( (String)value );
	}

	return;
    }


}
