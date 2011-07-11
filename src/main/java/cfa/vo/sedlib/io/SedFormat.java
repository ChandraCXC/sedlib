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

import java.io.File;

/**
   Defines (de)serialization formats supported by the SED Library
*/	
public enum SedFormat
{
    VOT,
    FITS,
    XML;

    /** Returns the filename extension associated with the enum constant
     */
    public String exten()
    {
	String retval = "Unknown";

	if (this == FITS)
	    retval = "fits";
	else if ( this == VOT )
	    retval = "vot";
	else if ( this == XML )
	    retval = "xml";

	return retval;
    }

    /** Returns the "user friendly" name of this enum constant.
     */
    @Override
    public String toString()
    {
	String retval = "Unknown";

	if (this == FITS)
	    retval = "FITS";
	else if ( this == VOT )
	    retval = "VOTable";
	else if ( this == XML )
	    retval = "XML";
	
	return retval;	
    }

    /** Returns the enum constant of this type for the specified file.
     *  <p>
     *  Determines the appropriate format by matching the extension of
     *  the provided filename against the extension representations of
     *  the enumeration.
     *  <p>
     *  The extension is defined as all characters following the last
     *  occurance of the '.' character in the filename.
     *
     @param filename full name of file, may include path.
     *
     @throws java.lang.IllegalArgumentException  if this enum type has no constant matching the extension name.
    */
    public static SedFormat fromFilename( String filename )
    {
	String tmpstr = new File(filename).getName();
	String DOT = ".";

	// strip off extension
	int p = tmpstr.lastIndexOf(DOT);
	if ( p > 0 ){ 
	    tmpstr = tmpstr.substring(p+1);
	}
	return SedFormat.valueOf(tmpstr.toUpperCase());
    }

}
