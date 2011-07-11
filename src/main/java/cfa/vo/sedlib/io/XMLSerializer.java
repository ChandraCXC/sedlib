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
* File: io/XMLSerializer.java
*
* Author:  jmiller      Created: Mon Nov 29 11:57:14 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import java.io.OutputStream;

import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedWritingException;
import java.io.IOException;

/**
THIS CLASS IS NOT SUPPORTED YET. This class is a stub for future implementation.

Serializes a Sed object to an XML formatted file.
*/

class XMLSerializer implements ISedSerializer
{
    /**
     * Serializes the specified Sed object to the specified file in
     * XML format.
     * @param filename
     *   {@link String}
     * @param sed
     *   {@link Sed}
     */
    public void serialize(String filename, Sed sed) throws SedInconsistentException, SedWritingException, IOException
    {
    }


    /**
     * Serializes the specified Sed object to the specified stream in
     * XML format.
     * @param oStream
     *   {@link OutputStream}
     * @param sed
     *   {@link Sed}
     */
    public void serialize(OutputStream  oStream, Sed sed) throws SedInconsistentException, SedWritingException, IOException
    {
    }

}
