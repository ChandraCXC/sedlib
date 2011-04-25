package cfa.vo.sedlib.io;

import java.io.InputStream;

import cfa.vo.sedlib.common.SedParsingException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *  Declares methods for reading data files and implements a common read(String filename) method.
 */
abstract class AbstractReader<T>
{

    /**
     * Reads data in from a file and returns an object.
     * @param file
     *    {@link java.lang.String}
     * @return
     *    {@link <T>}
     * @throws {@link SedParsingException}, {@link IOException}
     */
    public T read(String file) throws SedParsingException, IOException
    {
        FileInputStream fis = new FileInputStream(file);

        T result = read(fis);
        return result;
    }

    /**
     * Reads data in from a file and returns an object.
     * @param file
     *    {@link java.io.InputStream}
     * @return
     *    {@link <T>}
     * @throws {@link SedParsingException}
     */
    public abstract T read(InputStream file) throws SedParsingException, IOException;


}//end IReader interface
