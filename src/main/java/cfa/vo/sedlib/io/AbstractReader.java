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

import cfa.vo.sedlib.common.SedParsingException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *  Declares methods for reading data files and implements a common read(String filename) method.
 */
abstract class AbstractReader<T>
{

    /**
     * Reads data in from a file and returns an object.
     * @param file
     *    {@link java.lang.String}
     * @return
     *    {@link <T>}
     * @throws {@link SedParsingException}, {@link IOException}
     */
    public T read(String file) throws SedParsingException, IOException
    {
        FileInputStream fis = new FileInputStream(file);

        T result = read(fis);
        return result;
    }

    /**
     * Reads data in from a file and returns an object.
     * @param file
     *    {@link java.io.InputStream}
     * @return
     *    {@link <T>}
     * @throws {@link SedParsingException}
     */
    public abstract T read(InputStream file) throws SedParsingException, IOException;


}//end IReader interface
