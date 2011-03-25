package cfa.vo.sedlib;

/**
 * <p>Java class for redshiftFrame complex type.
 * 
 * 
 */
public class RedshiftFrame
    extends CoordFrame
{

    protected String dopplerDefinition;

    /**
     * Gets the value of the dopplerDefinition property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getDopplerDefinition() {
        return dopplerDefinition;
    }

    /**
     * Sets the value of the dopplerDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDopplerDefinition(String value) {
        this.dopplerDefinition = value;
    }

    public boolean isSetDopplerDefinition() {
        return (this.dopplerDefinition!= null);
    }

}
