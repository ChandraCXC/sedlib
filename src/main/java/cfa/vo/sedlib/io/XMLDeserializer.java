/***********************************************************************
*
* File: io/XMLDeserializer.java
*
* Author:  jmiller      Created: Mon Nov 29 11:57:14 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import java.io.IOException;
import java.io.InputStream;

import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedParsingException;


/**
THIS CLASS IS NOT SUPPORTED YET. This class is a stub for future implementation.

Serializes a Sed object to an XML formatted file.
*/
class XMLDeserializer implements ISedDeserializer
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
        throws SedParsingException, IOException
    {
/*        XMLReader reader = new XMLReader ();
        XMLMapper mapper = new XMLMapper ();

        VOElement voElement = (VOElement)reader.read (filename);
        mapper.populateSed (voElement);
        return mapper.getSed ();
*/
        return new Sed ();
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
        throws SedParsingException, IOException
    {

/*        XMLReader reader = new XMLReader ();
        XMLMapper mapper = new XMLMapper ();

        VOElement voElement = (VOElement)reader.read (iStream);
        mapper.populateSed (voElement);
        return mapper.getSed ();
*/
        return new Sed ();
    }
}

