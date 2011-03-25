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
