package cfa.vo.sedlib.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


/**
	Prettifies VOTable output.  Filters the stream that writes a
	VOTable and pretifies it by doing nice indentation.  Makes some
	assumtions about the form of the input so it may not work on
	arbitrary inputs.  Specifically, we assume that opening or closing
	tags are not split across lines and that the only '<' and '>'
	characters in the input are tag delimiters (meaning they won't
	occur in CDATA sections).  Also, multiple elements on a
	line are split into separate lines.

	Note: not all OutputStreamWriter write() methods are overridden.
*/
class PrettifyingOutputStreamWriter extends OutputStreamWriter
{

    // matches '<' followed by a 'word character' as 1st non-white on line
    static final String OPEN_PATTERN    = "^\\s*<\\w.*";

    // matches '</' followed by a 'word character' as last on line
    static final String CLOSE_PATTERN   = ".*</\\w[^<]*$";

    // Matches opening patters at beginning and close pattern at end
    // of line; any number of elements may interviene.
    static final String ELEMENT_PATTERN0 = OPEN_PATTERN + ".*" + CLOSE_PATTERN;

    // Match a complete element on a single line; it is closed with
    // '/>' rather than a separate closing tag.
    static final String ELEMENT_PATTERN1 = OPEN_PATTERN + ".*/>.*";

    public PrettifyingOutputStreamWriter( OutputStream ostream )
    {
	super( ostream );
    }

    int m_indentCount = 0;
    String m_partial = "";

    @Override
    public void write( char[] cbuf, int off, int len ) throws IOException
    {
	String allLines = new String( cbuf, off, len );

	// This separates contiguous elements with a newline.
	allLines = allLines.replaceAll( ">\\s*<", ">\n<" );

	// Split the input into separate line and write each out prettily.
	String[] lines = allLines.split( "\\n" );

        // A buffer not starting on a tag should be the remainder of
        // a partial line.. put them together.
        if ( ! lines[0].startsWith("<") )
        {
            if ( ! m_partial.equals("") )
            {
                lines[0] = m_partial + lines[0];
                m_partial = "";
            }
        }


	// The buffer may not end on a tag boundary.. check for this and
	// store the partial line in a local variable to combine with the
	// rest of it from the next buffer.
	if ( ! lines[lines.length-1].endsWith(">") )
	{
	    m_partial = lines[lines.length-1];
	    lines[lines.length-1] = "";
	}

	for ( int i = 0; i < lines.length; i++ )
	{
	    lines[ i ] = lines[ i ].trim();

	    // Skip empty line - beginning of partial line handled above.
	    if ( lines[i].equals("") ){ continue; }

	    if ( lines[ i ].matches( ELEMENT_PATTERN0 ) || 
		 lines[ i ].matches( ELEMENT_PATTERN1 ) )
	    {
		// tag begins and ends on same line
		writeLine( lines[i] );
	    }
	    else if ( lines[ i ].matches( OPEN_PATTERN ) )
	    {
		writeLine( lines[i] );
		m_indentCount++;
	    }
	    else if ( lines[ i ].matches( CLOSE_PATTERN ) )
	    {
		m_indentCount--;
		writeLine( lines[i] );
	    }
	    else
	    {
		writeLine( lines[i] );
	    }
	}
    }

    void writeLine( String aLine ) throws IOException
    {
	String INDENTATION = "  ";
	for ( int j = 0; j < m_indentCount; j++ )
	{
	    super.write( INDENTATION );
	}
	super.write( aLine + "\n" );
    }
}
