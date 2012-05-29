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
 * <p>Java class for RangeParam complex type. This class is synomous with the 
 * the interval class; however, the min and max are true Doubles and not
 * DoubleParams
 * 
 * 
 */
public class RangeParam
    extends Param
{

    protected Double min;
    protected Double max;

    public RangeParam () {};

    public RangeParam (String value, String unit)
    {
        super(value);

        this.setValue (value);
        this.header.setUnit (unit);
    }

    public RangeParam (String value, String name, String ucd, String unit)
    {
        super(value, name, ucd);

        this.setValue (value);
        this.header.setUnit (unit);
    }

    public RangeParam (String value, String name, String ucd, String unit, String id)
    {
        super (value, name, ucd, id);
        this.setValue (value);
        this.header.setUnit (unit);
    } 

    public RangeParam (String value)
    {
        super (value);
        this.setValue (value);
    }

    public RangeParam (Double min, Double max)
    {
        super (min.toString ()+ " " + max.toString ());
        this.min = min;
        this.max = max;
    }

    public RangeParam (Double min, Double max, String unit)
    {
        super(min.toString ()+ " " + max.toString ());
        this.header.setUnit (unit);
        this.min = min;
        this.max = max;
    }

    public RangeParam (Double min, Double max, String name, String ucd, String unit)
    {
        super(min.toString ()+ " " + max.toString (), name, ucd);
        this.header.setUnit (unit);
        this.min = min;
        this.max = max;
    }

    public RangeParam (Double min, Double max, String name, String ucd, String unit, String id)
    {
        super(min.toString ()+ " " + max.toString (), name, ucd, id);
        this.header.setUnit (unit);
        this.min = min;
        this.max = max;

    }

    /**
     * Sets the value of the value property. The value should be
     * two numbers separated by a space where the first number
     * is the min and the second number is the max.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setValue(String value) 
    {
        super.setValue( value.toString() );

        if ( ! value.isEmpty() )
        {
            String[] values = value.split(" ");

            try
            {
                this.min = Double.parseDouble(values[0]);
            }
            catch (Exception e)
            {
                this.min = null;
            }

            if (values.length == 2)
            {
                try
                {
                    this.max = Double.parseDouble(values[1]);
                }
                catch (Exception e)
                {
                    this.min = null;
                }
            }
        }
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
     * Gets the min value
     *
     * @return
     *     either null or
     *     {@link Double }
     *
     */
    public Double getMin() {
        return this.min;
    }

    /**
     * Sets the min value
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setMin(Double value) {
        this.min = value;

        this.value = this.min + " " + this.max;
    }


    public boolean isSetMin() {
        return this.min != null;
    }


    /**
     * Gets the max value
     *
     * @return
     *     either null or
     *     {@link Double }
     *
     */
    public Double getMax() {
        
        return this.max;

    }

    /**
     * Sets the max value
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setMax(Double value) {
        this.max = value;

        this.value = this.min + " " + this.max;
    }


    public boolean isSetMax() {
        return this.max != null;
    }


    /**
     * equals operator to compare two RangeParams
     *
     */
    @Override
    public boolean equals (Object other)
    {
        boolean compValue = super.equals(other);
        final RangeParam param = (RangeParam) other;

        if (compValue)
        {
            compValue = ((this.min == param.min)&&
			 (this.max == param.max));
        }

        return compValue;
    }


}
