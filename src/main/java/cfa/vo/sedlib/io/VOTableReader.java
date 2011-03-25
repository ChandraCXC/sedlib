package cfa.vo.sedlib.io;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;

import uk.ac.starlink.votable.VOElement;
import uk.ac.starlink.votable.VOElementFactory;
import cfa.vo.sedlib.common.SedParsingException;


/**
 *  Reads documents in VOTable format from files, streams into a VOElement which is 
 *  then returned. 
 */
class VOTableReader extends AbstractReader<VOElement>
{

    /**
     * Reads (or marshals) data from a file and returns an IWrapper (VOTableWrapper) object; returns null if the file cannot be read.
     * @param  file
     *    {@link java.io.InputStream}
     * @return
     *    {@link IWrapper}
     */
    public VOElement read(InputStream file) throws SedParsingException, IOException
    {
	VOElement voElement = null;

	try {
	    
	    // Create a tree of VOElements from the given XML file.
	    voElement = new VOElementFactory().makeVOElement( file, null );
	}
	catch(SAXException saxe) {
            throw new SedParsingException ("Problem reading the VOTable stream.", saxe);
	}

	return voElement;

    }//end read()

}
