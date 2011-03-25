package cfa.vo.sedlib.common;

/**
 *  A wrapper for Exception
 */
public class SedWritingException extends SedException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SedWritingException()
    {
    }

    public SedWritingException(String s)
    {
	super(s);
    }

    public SedWritingException(String s, Throwable t)
    {
	super(s, t);
    }


}//end SedException
