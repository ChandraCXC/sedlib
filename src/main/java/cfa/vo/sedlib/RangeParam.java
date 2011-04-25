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
    public void setValue(String value) {
        super.setValue (value.toString ());

        if (value != null)
        {
            String[] values = value.split (" ");

            try
            {
                this.min = Double.parseDouble (values[0]);
            }
            catch (Exception e)
            {
                this.min = null;
            }

            if (values.length == 2)
            {
                try
                {
                    this.max = Double.parseDouble (values[1]);
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


}
