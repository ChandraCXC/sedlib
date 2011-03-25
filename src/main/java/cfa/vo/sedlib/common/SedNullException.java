package cfa.vo.sedlib.common;

/**
 *  A wrapper for Exception
 */
public class SedNullException extends SedException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SedNullException()
    {
    }

    public SedNullException(String s)
    {
	super(s);
    }

    public SedNullException(String s, Throwable t)
    {
	super(s, t);
    }


}//end SedException
