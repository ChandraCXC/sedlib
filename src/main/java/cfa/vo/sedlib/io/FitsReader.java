package cfa.vo.sedlib.io;

import java.io.InputStream;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import cfa.vo.sedlib.common.SedParsingException;

/**
 *  Reads Fits files; loads the contents of a file into a Fits object.
 */
class FitsReader extends AbstractReader<Fits>
{

    public FitsReader() {}

    
    /**
     * Reads data from a file and returns an IWrapper (FitsWrapper) object.
     * @param file
     *    {@link java.io.InputStream}
     * @return
     *    {@link Fits}
     */
    public Fits read(InputStream inStream ) throws SedParsingException
    {
	Fits fits = null;

	try {
	    fits = new Fits(inStream );
	}
	catch (FitsException fe) {
            throw new SedParsingException ("Error while parsing fits content.", fe);
	}

        return fits;

    }//end read()

}//end FitsReader class
