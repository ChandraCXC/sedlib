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


package cfa.vo.sedlib.io;

import cfa.vo.sedlib.common.SedConstants;

/**
   Defines constant values used throughout the IO portion of the library.
*/
public class IOConstants
{
    // Fits Constants

    // size of IEEE double precision
    public static final int FITS_BITS_PER_BYTE = 8;

    // for double precision,float
    public static final int FITS_BYTES_PER_DOUBLE_PRECISION = 8;
    public static final int FITS_BYTES_PER_INTEGER32 = 4;
    public static final int FITS_BYTES_PER_CHAR = 1;

    // code for double precision float from Fits standard specification.
    public static final char FITS_FORMAT_CODE_SINGLE_PRECISION = 'E';
    public static final char FITS_FORMAT_CODE_DOUBLE_PRECISION = 'D';
    public static final char FITS_FORMAT_CODE_INTEGER16 = 'I';
    public static final char FITS_FORMAT_CODE_INTEGER32 = 'J';
    public static final char FITS_FORMAT_CODE_STRING = 'A';


    // VOTable Constants

    // a comma, space, or tab followed by optional whitespace
    static public final String LIST_SEPARATOR_PATTERN = "[, \\t][ \\t]*";
    static public final String LIST_SEPARATOR = ", ";


    // Matches namespace prefixes in XML
    static final public String NAMESPACE_PATTERN = "^[a-zA-Z0-9_.-][a-zA-Z0-9_.-]*:";

    static final public String IVOA_VOTABLE_SCHEMA
                = "http://www.ivoa.net/xml/VOTable/v1.1";

    static final public String IVOA_SPECTRUM_SCHEMA
                = "http://www.ivoa.net/xml/Spectrum/"
                + SedConstants.SPECTRUM_SCHEMA_VERSION + ".xsd";

    static final public String WWW_XMLSCHEMA_INSTANCE
                = "http://www.w3.org/2001/XMLSchema-instance";

    // Note backslashes to escape quotes
    public final static String XML_VERSION_ENCODING =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

}
