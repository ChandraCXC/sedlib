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

import java.io.OutputStream;

import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedWritingException;
import java.io.IOException;

/**
    Defines management of writing Sed objects to file and streams.
*/
public interface ISedSerializer
{

    /**
     * Serializes Sed object to an stream
     * in the serializer's format.
     * @param oStream
     *   {@link OutputStream} 
     * @param sed
     *   {@link Sed}
     * @throws SedInconsistentException, SedWritingException
     */
    public void serialize(OutputStream oStream, Sed sed)
                    throws SedInconsistentException, SedWritingException, IOException;

    /**
     * Serializes Sed object tree to a file in the serializer's
     * format.
     * @param filename
     *   {@link String} 
     * @param sed
     *   {@link Sed}
     * @throws SedInconsistentException, SedWritingException
     */
    public void serialize(String filename, Sed sed)
                    throws SedInconsistentException, SedWritingException, IOException;
}
