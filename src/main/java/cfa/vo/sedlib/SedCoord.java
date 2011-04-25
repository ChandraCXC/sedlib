package cfa.vo.sedlib;

/**
 * <p>Java class for sedCoord complex type.
 * 
 * 
 */
public class SedCoord
    extends AbstractSedCoord<DoubleParam, DoubleParam>
{

    @Override
    public DoubleParam createValue() {
        if(isSetValue()) {
            return getValue();
        }

        setValue(new DoubleParam());

        return getValue();
    }

    @Override
    public DoubleParam createResolution() {
        if(isSetResolution()) {
            return getResolution();
        }

        setValue(new DoubleParam());
        return getValue();
    }

    @Override
    public Object clone() {
        SedCoord sedCoord = (SedCoord) super.clone();

        if(isSetResolution())
            sedCoord.setResolution((DoubleParam)getResolution().clone());

        if(isSetValue())
            sedCoord.setValue((DoubleParam)getValue().clone());

        return sedCoord;
    }



}
