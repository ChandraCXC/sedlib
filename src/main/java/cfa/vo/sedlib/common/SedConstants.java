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
