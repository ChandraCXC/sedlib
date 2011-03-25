package cfa.vo.sedlib.common;

/**
 *  A wrapper for Exception
 */
public class SedParsingException extends SedException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SedParsingException()
    {
    }

    public SedParsingException(String s)
    {
	super(s);
    }

    public SedParsingException(String s, Throwable t)
    {
	super(s, t);
    }


}//end SedException
