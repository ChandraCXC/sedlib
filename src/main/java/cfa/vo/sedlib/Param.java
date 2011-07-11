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

/**
 * <p>Java class for param complex type.
 * 
 * 
 */
public class Param implements Cloneable {

    protected Field header;
    protected String value;

    public Param ()
    {
        this.header = new Field ();
    }

    public Param (String value, String name, String ucd)
    {
        this(value, name, ucd, null);
    }

    public Param (String value, String name, String ucd, String id)
    {
        this.header = new Field (name, ucd, null, null, id);
        this.value = value;
    }

    public Param (String value)
    {
       this.header = new Field ();
       this.value = value;
    }

    @Override
    public Object clone ()
    {
        Param param = null;
        try
        {
            param = (Param) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // this should never happen
            throw new InternalError(e.toString());
        }

        header = (Field)header.clone ();

        return param;
    }


    /**
     * Gets the value of the value property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets the value of the value property cast to the data type
     *
     * @return
     *     either null or String cast as Object
     *     {@link String }
     *
     */
    public Object getCastValue() {
        return value;
    }


    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getName() {
        return this.header.getName ();
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
        this.header.setName (value);
    }

    public boolean isSetName() {
        return this.header.isSetName ();
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
        return this.header.getUcd ();
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
        this.header.setUcd (value);
    }

    public boolean isSetUcd() {
        return this.header.isSetUcd ();
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
        return this.header.getId ();
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
        this.header.setId (value);
    }

    public boolean isSetId() {
        return this.header.isSetId ();
    }

    /**
     * Gets the value of the internal id property.
     *
     * @return
     *     either null or
     *     {@link String }
     *
     */
    public String getInternalId() {
        return this.header.getInternalId ();
    }

    /**
     * Sets the value of the internal id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInternalId(String value) {
        this.header.setInternalId (value);
    }

    public boolean isSetInternalId() {
        return this.header.isSetInternalId ();
    }

    /**
     * Overloaded equals operator to compare two Params
     *
     */
    @Override
    public boolean equals (Object other)
    {

        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        final Param param = (Param) other;

        boolean compValue = this.header.equals (param.header);

        if (compValue)
        {
            if (this.isSetValue () && param.isSetValue ())
                compValue = this.value.equals (param.value);
            else
                compValue = this.isSetValue () == param.isSetValue ();
        }

        return compValue;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.header != null ? this.header.hashCode() : 0);
        hash = 73 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }



}
