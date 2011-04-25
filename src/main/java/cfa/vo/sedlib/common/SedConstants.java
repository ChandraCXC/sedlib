/***********************************************************************
*
* File: SedConstants.java
*
* Author:  jcant        Created: Wed Aug  6 11:47:52 2008
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/


package cfa.vo.sedlib.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
   Defines constant values used throughout the library.
*/    
public class SedConstants
{

    static final public Properties props = new Properties();

    static {
        InputStream resource = SedConstants.class.getResourceAsStream("/sedlib.properties");
        try {
            props.load(resource);
        } catch (IOException ex) {
            Logger.getLogger(SedConstants.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resource.close();
            } catch (IOException ex) {
                Logger.getLogger(SedConstants.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    static final public String    VOTABLE_VERSION  = "1.1";
    static final public String    SEDLIB_VERSION   = props.getProperty("version");

    static final public String SPECTRUM_SCHEMA_VERSION = "Spectrum-1.01";

    static final public String DATAMODEL           = "Spectrum-1.0";

    static final public Double DEFAULT_DOUBLE      = Double.NaN;
    static final public Integer DEFAULT_INTEGER    = -9999;
    static final public String DEFAULT_STRING      = "";

}
