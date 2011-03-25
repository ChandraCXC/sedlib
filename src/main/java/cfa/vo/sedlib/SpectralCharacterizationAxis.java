package cfa.vo.sedlib;

/**
 * <p>Java class for spectralCharacterizationAxis complex type.
 * 
 * 
 */

public class SpectralCharacterizationAxis
    extends CharacterizationAxis
{

    protected DoubleParam resPower;

    /**
     * Gets the value of the resPower property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getResPower() {
        return resPower;
    }

    /**
     * Creates resPower property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createResPower() {
        if (this.resPower == null)
           this.setResPower (new DoubleParam ());
        return this.resPower;
    }


    /**
     * Sets the value of the resPower property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setResPower(DoubleParam value) {
        this.resPower = value;
    }

    public boolean isSetResPower() {
        return (this.resPower!= null);
    }

}
