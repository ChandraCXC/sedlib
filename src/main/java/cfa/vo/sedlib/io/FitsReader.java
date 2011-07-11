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
