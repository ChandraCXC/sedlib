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

import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedParsingException;

/**
    Defines management of reading Sed objects from files and streams.
*/
public interface ISedDeserializer
{

    /**
     * Deserializes a Sed object from a stream
     * in the deserializer's format. 
     * @param iStream
     *   {@link InputStream} 
     * @return
     *   {@link Sed}
     *
     * @throws {@link SedParsingException}, {@link SedInconsistentException}, {@link IOException}
     */
    public Sed deserialize(InputStream iStream) 
    	throws SedParsingException, SedInconsistentException, IOException, SedNoDataException;

    /**
     * Deserializes Sed object from a file in deserializer's format.  
     * @param filename
     *   {@link String} 
     * @return 
     *   {@link Sed}
     *
     * @throws {@link SedParsingException}, {@link SedInconsistentException}, {@link IOException}
     */
    public Sed deserialize(String filename) 
    	throws SedParsingException, SedInconsistentException, IOException, SedNoDataException;
}
