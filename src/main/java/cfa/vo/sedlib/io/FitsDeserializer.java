/**
 * Copyright (C) 2011 Smithsonian Astrophysical Observatory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/***********************************************************************
*
* File: io/FitsDeserializer.java
*
* Author:  jmiller      Created: Mon Nov 29 11:57:14 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import cfa.vo.sedlib.common.SedNoDataException;
import java.io.IOException;
import java.io.InputStream;

import nom.tam.fits.Fits;
import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedParsingException;

/**
Deserializes FITS formatted data to Sed objects.
*/
public class FitsDeserializer implements ISedDeserializer
{

    /**
     * Deserializes data from a file into a Sed object.
     * @param filename
     *   {@link String}
     * @return
     *   {@link Sed}
     *
     * @throws SedException
     * @throws IOException
     */
    public Sed deserialize(String filename) 
    	throws SedParsingException, SedInconsistentException, IOException, SedNoDataException
    {
        FitsReader reader = new FitsReader ();
        FitsMapper mapper = new FitsMapper ();

        Fits fits = (Fits)reader.read (filename);
        return mapper.populateSed (fits);
    }


    /**
     * Deserializes data from a stream into Sed object.
     *
     * @param iStream
     *    {@link InputStream}
     * @return
     *    {@link Sed}
     *
     * @throws SedException
     * @throws IOException
     */
    public Sed deserialize(InputStream iStream) 
    	throws SedParsingException, SedInconsistentException, IOException, SedNoDataException
    {

        FitsReader reader = new FitsReader ();
        FitsMapper mapper = new FitsMapper ();

        Fits fits = (Fits)reader.read (iStream);
        return mapper.populateSed (fits);
    }
}

