package cfa.vo.sedlib;

/**
 * <p>Java class for doubleParam complex type.
 * 
 * 
 */
public class DoubleParam
    extends Param
{

    protected String unit;

    public DoubleParam () {};

    public DoubleParam (DoubleParam param)
    {
        super (param);
        this.header.setUnit (param.header.getUnit ());
    }

    public DoubleParam (String value, String name, String ucd, String unit)
    {
        super(value, name, ucd);

        this.header.setUnit (unit);

        try
        {
            Double.parseDouble (value);
        }
        catch (Exception e)
        {
            this.value = null;
        }

    }

    public DoubleParam (String value, String name, String ucd, String unit, String id)
    {
        super (value, name, ucd, id);
        this.header.setUnit (unit);
        
        try
        {
            Double.parseDouble (value);
        }
        catch (Exception e)
        {
            this.value = null;
        }
    } 

    public DoubleParam (String value)
    {
        super (value);

        try
        {
            Double.parseDouble (value);
        }
        catch (Exception e)
        {
            this.value = null;
        }

    }

    public DoubleParam (Double value)
    {
        super (value.toString ());
    }
    public DoubleParam (Double value, String name, String ucd, String unit)
    {
        this(value.toString (), name, ucd, unit);
    }

    public DoubleParam (Double value, String name, String ucd, String unit, String id)
    {
        this(value.toString (), name, ucd, unit, id);
    }

    /**
     * Sets the value of the value property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setValue(Double value) {
        this.setValue (value.toString ());
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
     * Gets the value of the value property cast to the data type.
     *
     * @return
     *     either null or a Double cast as Object
     *     {@link Double }
     *
     */
    public Object getCastValue() {
        if (this.value != null)
            return new Double(this.value);
        return null;
    }


}
