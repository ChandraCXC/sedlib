package cfa.vo.sedlib;

/**
 * <p>Java class for arrayOfGenPoint complex type.
 * 
 */
public abstract class ArrayOfGenPoint implements Cloneable {

    @Override
    public Object clone ()
    {
    	try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // this should never happen
            throw new InternalError(e.toString());
        }
    }
    
    /**
     * Gets the length of the point list.
     *
     */
    abstract public int getLength();

}
