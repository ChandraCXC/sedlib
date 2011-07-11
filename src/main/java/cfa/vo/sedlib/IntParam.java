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
 * <p>Java class for intParam complex type.
 * 
 * 
 */
public class IntParam
    extends Param
{

    protected String unit;

    public IntParam () {};

    public IntParam (String value)
    {
        super (value);

        try
        {
            Integer.parseInt(value);
        }
        catch (Exception e)
        {
            this.value = null;
        }

    }

    public IntParam (String value, String name, String ucd, String unit)
    {
        super (value, name, ucd);
        this.header.setUnit (unit);

        try
        {
            Integer.parseInt (value);
        }
        catch (Exception e)
        {
            this.value = null;
        }

    }


    public IntParam (String value, String name, String ucd, String unit, String id)
    {
        super (value, name, ucd, id);
        this.header.setUnit (unit);

        try
        {
            Integer.parseInt (value);
        }
        catch (Exception e)
        {
            this.value = null;
        }

    }

    public IntParam (Integer value)
    {
        super (value.toString ());
    }

    public IntParam (Integer value, String name, String ucd, String unit)
    {
        this(value.toString (), name, ucd, unit);
    }


    public IntParam (Integer value, String name, String ucd, String unit, String id)
    {
        this(value.toString (), name, ucd, unit, id);
    }


    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getUnit() {
        return this.header.getUnit ();
    }

    /**
     * Sets the value of the value property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setValue(Integer value) {
        this.setValue (value.toString ());
    }


    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.header.setUnit (value);
    }


    public boolean isSetUnit() {
        return (this.header.isSetUnit ());
    }

    /**
     * Gets the value of the value property cast to the data type
     *
     * @return
     *     either null or a Integer cast as Object
     *     {@link String }
     *
     */
    @Override
    public Object getCastValue() {
        if (value != null)
            return Integer.valueOf(value);
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
