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

import cfa.vo.sedlib.common.SedWritingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 *  Interface for writing wrapped objects to files, streams.
 */
public abstract class AbstractWriter<T>
{

    /**
     * Writes data to a file given an appropriate object.
     * @param filename
     *   {@link java.lang.String} 
     * @param data
     */
    public void write(String filename, T data) throws IOException, SedWritingException
    {

        FileOutputStream fos = new FileOutputStream( filename );
        
        this.write( fos, data );

    }//end write()

    /**
     * Writes data to a file given an appropriate object.
     * @param ostream
     *   {@link java.io.OutputStream} 
     * @param data
     */
    public abstract void write(OutputStream ostream, T data) throws SedWritingException;


}//end AbstractWriter interface
