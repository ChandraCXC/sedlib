package cfa.vo.sedlib;

/**
 * Simplification of STC version: RefPos is string
 * 
 * <p>Java class for coordFrame complex type.
 * 
 */
public class CoordFrame extends Group {

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

}
