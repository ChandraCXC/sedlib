package cfa.vo.sedlib;

import java.util.Arrays;


/**
 * <p>Java class for positionParam complex type.
 * 
 * 
 */
public class PositionParam implements Cloneable {

    protected DoubleParam value[] = null;

    @Override
    public Object clone ()
    {
        PositionParam positionParam = null;
        try
        {
            positionParam = (PositionParam) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // this should never happen
            throw new InternalError(e.toString());
        }

        if (this.isSetValue ())
        {
            positionParam.setValue (new DoubleParam[2]);
            for (int ii=0; ii<this.value.length; ii++)
                positionParam.value[ii] = this.value[ii];
        }
        return positionParam;
    }


    /**
     * Gets the value property.
     *
     * @return
     *     either null or DoubleParam[]
     *     {@link DoubleParam}
     *
     */
    public DoubleParam[] getValue() {
//        return Arrays.copyOf(value, value.length);
        return value;
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
//        return Arrays.copyOf(value, value.length);
        return value;
    }


    public boolean isSetValue() {
        return (this.value!= null);
    }

    public void setValue(DoubleParam[] value) {
//        this.value = Arrays.copyOf(value, value.length);
        this.value = value;
    }

}
