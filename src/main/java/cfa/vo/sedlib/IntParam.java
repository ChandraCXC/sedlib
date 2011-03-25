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

    public IntParam (IntParam param)
    {
        super (param);
        this.header.setUnit (param.header.getUnit ());
    }

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
    public Object getCastValue() {
        if (value != null)
            return Integer.valueOf(value);
        return null;
    }


}
