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
