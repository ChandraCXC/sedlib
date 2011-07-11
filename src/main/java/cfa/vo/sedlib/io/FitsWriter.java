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
import java.io.DataOutputStream;
import java.io.OutputStream;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

/**
 *  Writes Fits objects to file.
 */
class FitsWriter extends AbstractWriter<Fits>
{

    public FitsWriter() {}

    /**
     * Writes data to a stream given appropriate IWrapper.  Returns status code.
     * @param outStream
     *   {@link java.io.OutputStream} 
     * @param wrapper
     *   {@link IWrapper}
     * @return 0 on success, non-zero otherwise.
     *   int
     */
    public void write( OutputStream outStream, Fits data) throws SedWritingException
    {
        //TODO  check other writers, interface
        Fits fits = (Fits)data;

        DataOutputStream dataOutStream = new DataOutputStream( outStream );

        try
        {
            fits.write( dataOutStream );
        }
        catch(FitsException fe) {

            throw new SedWritingException ("Problem writing to a fits file.", fe);
            
        }
        
    }//end write()

}//end FitsWriter class
