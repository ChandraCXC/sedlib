/***********************************************************************
*
* File: io/VOTableDeserializer.java
*
* Author:  jmiller      Created: Mon Nov 29 11:57:14 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import java.io.IOException;
import java.io.InputStream;

import uk.ac.starlink.votable.VOElement;
import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedParsingException;


/**
Deserializes VOTable formatted data to Sed objects.
*/
public class VOTableDeserializer implements ISedDeserializer
{

    /**
     * Deserializes data from a file into a Sed object
     * @param filename
     *   String
     * @return
     *   {@link Sed}
     *
     * @throws SedException
     * @throws IOException
     */
    public Sed deserialize(String filename) 
    	throws SedParsingException, SedInconsistentException, IOException
    {
        VOTableReader reader = new VOTableReader ();
        VOTableMapper mapper = new VOTableMapper ();

        VOElement voElement = (VOElement)reader.read (filename);
        return mapper.populateSed (voElement);
    }


    /**
     * Deserializes data from a stream into Sed object.
     *
     * @param iStream
     *    {@link java.io.InputStream}
     * @return
     *    {@link cfa.vo.sedlib.Sed}
     *
     * @throws SedException
     * @throws IOException
     */
    public Sed deserialize(InputStream iStream) 
    	throws SedParsingException, SedInconsistentException, IOException
    {

        VOTableReader reader = new VOTableReader ();
        VOTableMapper mapper = new VOTableMapper ();

        VOElement voElement = (VOElement)reader.read (iStream);
        return mapper.populateSed (voElement);
    }
}

