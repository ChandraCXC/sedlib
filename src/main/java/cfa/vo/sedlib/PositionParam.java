package cfa.vo.sedlib;


/**
 * <p>Java class for positionParam complex type.
 * 
 * 
 */
public class PositionParam {

    protected DoubleParam value[] = null;

    /**
     * Gets the value property.
     *
     * @return
     *     either null or DoubleParam[]
     *     {@link DoubleParam}
     *
     */
    public DoubleParam[] getValue() {
        return this.value;
    }

    /**
     * Creates value property if one does not exist.
     *
     * @return
     *     {@link DoubleParam}[]
     *
     */
    public DoubleParam[] createValue() {
        if (this.value == null)
           this.setValue (new DoubleParam[2]);
        return this.value;
    }


    public boolean isSetValue() {
        return (this.value!= null);
    }

    public void setValue(DoubleParam[] value) {
        this.value = value;
    }

}
