package cfa.vo.sedlib.common;

/**
 *  A wrapper for Exception
 */
public class SedInconsistentException extends SedException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SedInconsistentException()
    {
    }

    public SedInconsistentException(String s)
    {
	super(s);
    }

    public SedInconsistentException(String s, Throwable t)
    {
	super(s, t);
    }


}//end SedException
