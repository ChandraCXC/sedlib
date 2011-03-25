package cfa.vo.sedlib.common;

/**
 *  A wrapper for Exception
 */
public class SedNoDataException extends SedException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SedNoDataException()
    {
    }

    public SedNoDataException(String s)
    {
	super(s);
    }

    public SedNoDataException(String s, Throwable t)
    {
	super(s, t);
    }


}//end SedException
