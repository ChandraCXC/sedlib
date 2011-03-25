package cfa.vo.sedlib;

/**
 * <p>Java class for param complex type.
 * 
 * 
 */
public class Param {

    protected Field header;
    protected String value;

    public Param ()
    {
        this.header = new Field ();
    }

    public Param (Param param)
    {
        this.header = new Field (param.header);
        this.value = param.value;
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
    public boolean equals (Param param)
    {
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



}
