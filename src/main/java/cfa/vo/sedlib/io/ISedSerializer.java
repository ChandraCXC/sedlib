package cfa.vo.sedlib.io;

import java.io.OutputStream;

import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedWritingException;
import java.io.IOException;

/**
    Defines management of writing Sed objects to file and streams.
*/
public interface ISedSerializer
{

    /**
     * Serializes Sed object to an stream
     * in the serializer's format.
     * @param oStream
     *   {@link OutputStream} 
     * @param sed
     *   {@link Sed}
     * @throws SedInconsistentException, SedWritingException
     */
    public void serialize(OutputStream oStream, Sed sed)
                    throws SedInconsistentException, SedWritingException, IOException;

    /**
     * Serializes Sed object tree to a file in the serializer's
     * format.
     * @param filename
     *   {@link String} 
     * @param sed
     *   {@link Sed}
     * @throws SedInconsistentException, SedWritingException
     */
    public void serialize(String filename, Sed sed)
                    throws SedInconsistentException, SedWritingException, IOException;
}
