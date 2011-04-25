package cfa.vo.sedlib;

/**
 * <p>Java class for field complex type.
 * 
 * 
 */
public class Field implements Cloneable {

    protected String name;
    protected String unit;
    protected String ucd;
    protected String utype;
    protected String id;
    protected String altId;

    public Field () {};

    public Field (Field f)
    {
        this.id = f.id;
        this.unit = f.unit;
        this.name = f.name;
        this.ucd = f.ucd;
        this.utype = f.utype;
        this.altId = f.altId;

    }

    public Field (String name, 
                  String ucd, 
                  String unit, 
                  String utype,
                  String id)
    {
        this.unit = unit;
        this.name = name;
        this.ucd = ucd;
        this.utype = utype;
        this.id = id;
        this.altId = id;
    }

    @Override
    public Object clone ()
    {
        Field field = null;
        try
        {
            field = (Field) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // this should never happen
            throw new InternalError(e.toString());
        }
        return field;
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
     * Gets the value of the unit property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
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
        this.unit = value;
    }

    public boolean isSetUnit() {
        return (this.unit!= null);
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

    /**
     * Gets the value of the utype property.
     *
     * @return
     *     either null or
     *     {@link String }
     *
     */
    public String getUtype() {
        return this.utype;
    }

    /**
     * Sets the value of the utype property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUtype(String value) {
        this.utype = value;
    }

    public boolean isSetUtype() {
        return (this.utype!= null);
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
        return this.id;
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

        // set the intenal id if it has not been set
        if (!this.isSetInternalId ())
            this.setInternalId (this.id);
    }

    public boolean isSetId() {
        return (this.id!= null);
    }

    /**
     * Gets the value of the alternative id property.
     *
     * @return
     *     either null or
     *     {@link String }
     *
     */
    public String getInternalId() {
        return this.altId;
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
        this.altId = value;
    }

    public boolean isSetInternalId() {
        return (this.altId!= null);
    }


    /**
     * Overloaded equals operator to compare two Fields
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
        final Field field = (Field) other;

        boolean compValue = true;

        if (this.isSetName () && field.isSetName ())
            compValue = this.name.equals (field.name);
        else
            compValue = this.isSetName () == field.isSetName ();

        if (compValue)  
        {
            if (this.isSetUnit () && field.isSetUnit ())
                compValue = this.unit.equals (field.unit);
            else
                compValue = this.isSetUnit () == field.isSetUnit ();
        }

        if (compValue)
        {
            if (this.isSetUcd () && field.isSetUcd ())
                compValue = this.ucd.equals (field.ucd);
            else
                compValue = this.isSetUcd () == field.isSetUcd ();
        }

        if (compValue)
        {
            if (this.isSetUtype () && field.isSetUtype ())
                compValue = this.utype.equals (field.utype);
            else
                compValue = this.isSetUtype () == field.isSetUtype ();
        }

        if (compValue)
        {
            if (this.isSetId () && field.isSetId ())
                compValue = this.id.equals (field.id);
            else
                compValue = this.isSetId () == field.isSetId ();
        }

        //NOTE: Skip the altId. This is an internal id and limit
        //the use of this compare function with external programs

        return compValue;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.unit != null ? this.unit.hashCode() : 0);
        hash = 29 * hash + (this.ucd != null ? this.ucd.hashCode() : 0);
        hash = 29 * hash + (this.utype != null ? this.utype.hashCode() : 0);
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }



}
