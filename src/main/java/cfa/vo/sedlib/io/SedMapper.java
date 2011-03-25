package cfa.vo.sedlib.io;

import java.io.IOException;

import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedParsingException;

/**
 *  Defines management of updating Sed objects from other well defined objects.
 */
public abstract class SedMapper {

    public SedMapper () {}

    /**
     * Converts data in some format to a Sed object. 
     * @param data
     *   Object
     * @param sed
     *   {@link Sed}
     * @return
     *   The returned Sed is the same reference to the input Sed.
     * @throws  SedException
     * @throws  IOException
     */
    abstract public Sed populateSed(Object data, Sed sed) 
    	throws SedParsingException, SedInconsistentException, IOException;

    /**
     * Converts data in some format to a Sed object
     * @param data
     *   Object
     * @throws  SedException
     * @throws  IOException
     */
    public Sed populateSed(Object data) 
    	throws SedParsingException, SedInconsistentException, IOException
    {
        return this.populateSed (data, new Sed ());
    }

}

