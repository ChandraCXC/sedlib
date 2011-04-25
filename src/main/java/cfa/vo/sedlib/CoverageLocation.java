package cfa.vo.sedlib;


/**
 * <p>Java class for coverageLocation complex type.
 * 
 * 
 */
public class CoverageLocation
    extends AbstractSedCoord<DoubleParam[], DoubleParam>
{

    @Override
    public DoubleParam[] createValue() {
        if(!isSetValue()) {
            setValue(new DoubleParam[2]);
        }
        
        return getValue();
    }

    @Override
    public DoubleParam createResolution() {
        if(isSetResolution()) {
            return getResolution();
        }

        setResolution(new DoubleParam());

        return getResolution();
    }

    @Override
    public Object clone() {
        CoverageLocation location = (CoverageLocation) super.clone();

        if(isSetResolution())
            location.setResolution((DoubleParam)getResolution().clone());
        
        if(isSetValue())
            location.setValue((DoubleParam[])getValue().clone());

        return location;
    }


}
