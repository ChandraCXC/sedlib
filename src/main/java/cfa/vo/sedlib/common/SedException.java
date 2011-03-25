package cfa.vo.sedlib.common;

/**
 *  A wrapper for Exception
 */
public class SedException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SedException()
    {
    }

    public SedException(String s)
    {
	super(s);
    }

    public SedException(String s, Throwable t)
    {
	super(s, t);
    }


}//end SedException
