/***********************************************************************
*
* File: /data/nvo/staff/jcant/workspace/testtools/src/cfa/vo/testtools/TestOutputStream.java
*
* Author:  jcant		Created: Wed Nov 19 17:00:03 2008
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/


package cfa.vo.testtools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/*
 *  Wraps an output stream to hide all the exception handling from the
 *  user.  If anything goes wrong, throws RuntimeException.
 */
public class SedLibTestOutputStream
{
	PrintStream m_stream = null;

	/*
	 *  Constructs a TestOutputStream; the file is nameed with the
	 *  testName and the coventional extension and put in the
	 *  conventional director for output.
	 *  @see TestUtils
	 */
	public SedLibTestOutputStream( String testName )
	{
		try
		{
			m_stream = new PrintStream(
					new File( SedLibTestUtils.mkOutFileName( testName ) ) );
		}
		catch( FileNotFoundException exp )
		{
			throw new RuntimeException( "TestOutputStream: cant open" );
		}
	}

	// Give 'em the stream so they can write directly.
	public PrintStream getStream() { return m_stream; }

	// Overridews to they can write through the TestOutputStream object
	// transparently
	public void print( String arg ) { m_stream.println( arg ); }
	public void print( double arg ) { m_stream.println( arg ); }
	public void print( int    arg ) { m_stream.println( arg ); }

	public void println( String arg ) { m_stream.println( arg ); }
	public void println( double arg ) { m_stream.println( arg ); }
	public void println( int    arg ) { m_stream.println( arg ); }

	public void close()
	{

		try
		{
			m_stream.close();
		}
		catch( Exception exp )
		{
			System.out.println( exp.getMessage());
			exp.printStackTrace( System.out );
			throw new RuntimeException( "TestOutputStream: cant close" );
		}

	}

}
