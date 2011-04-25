package cfa.vo.sedlib;

/**
 * <p>Java class for timeParam complex type.
 * 
 * 
 */
public class TimeParam
    extends Param
{

    protected String unit;

    public TimeParam () {}

    public TimeParam (String value, String name, String ucd, String unit)
    {
        super (value, name, ucd);
        this.header.setUnit (unit);
    }

    public TimeParam (String value, String name, String ucd, String unit, String id)
    {
        super (value, name, ucd, id);
        this.header.setUnit (unit);
    }

    public TimeParam (String value)
    {
        super (value);
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

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
