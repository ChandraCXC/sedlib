package cfa.vo.sedlib.io;

import cfa.vo.sedlib.common.SedWritingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 *  Interface for writing wrapped objects to files, streams.
 */
public abstract class AbstractWriter<T>
{

    /**
     * Writes data to a file given an appropriate object.
     * @param filename
     *   {@link java.lang.String} 
     * @param data
     */
    public void write(String filename, T data) throws IOException, SedWritingException
    {

        FileOutputStream fos = new FileOutputStream( filename );
        this.write( fos, data );

    }//end write()

    /**
     * Writes data to a file given an appropriate object.
     * @param ostream
     *   {@link java.io.OutputStream} 
     * @param data
     */
    public abstract void write(OutputStream ostream, T data) throws SedWritingException;


}//end AbstractWriter interface
