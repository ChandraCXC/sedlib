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
