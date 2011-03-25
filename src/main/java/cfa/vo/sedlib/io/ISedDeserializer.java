package cfa.vo.sedlib.io;

import java.io.IOException;
import java.io.InputStream;

import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedParsingException;

/**
    Defines management of reading Sed objects from files and streams.
*/
public interface ISedDeserializer
{

    /**
     * Deserializes a Sed object from a stream
     * in the deserializer's format. 
     * @param iStream
     *   {@link InputStream} 
     * @return
     *   {@link Sed}
     *
     * @throws {@link SedParsingException}, {@link SedInconsistentException}, {@link IOException}
     */
    public Sed deserialize(InputStream iStream) 
    	throws SedParsingException, SedInconsistentException, IOException;

    /**
     * Deserializes Sed object from a file in deserializer's format.  
     * @param filename
     *   {@link String} 
     * @return 
     *   {@link Sed}
     *
     * @throws {@link SedParsingException}, {@link SedInconsistentException}, {@link IOException}
     */
    public Sed deserialize(String filename) 
    	throws SedParsingException, SedInconsistentException, IOException;
}
