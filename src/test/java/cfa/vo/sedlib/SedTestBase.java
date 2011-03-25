/***********************************************************************
*
* File: /data/nvo/staff/jcant/workspace/src/sed/../test/cfa.vo.sedlib/SedTestBase.java
*
* Author:  jcant		Created: Tue Dec  9 13:14:52 2008
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
* Update History:
*   2010-12-23:  MCD  Add writeSED,
*  
***********************************************************************/


package cfa.vo.sedlib;

import cfa.vo.sedlib.common.SedNoDataException;
import org.apache.log4j.Logger;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import cfa.vo.testtools.SedLibTestUtils;

import cfa.vo.sedlib.io.SedFormat;
import cfa.vo.sedlib.io.SedIOFactory;
import cfa.vo.sedlib.io.ISedDeserializer;
import cfa.vo.sedlib.io.ISedSerializer;
//import cfa.vo.sedlib.io.XMLDeserializer;
//import cfa.vo.sedlib.io.XMLSerializer;
//import cfa.vo.sedlib.io.FitsDeserializer;
//import cfa.vo.sedlib.io.FitsSerializer;
import cfa.vo.sedlib.io.VOTableDeserializer;
import cfa.vo.sedlib.io.VOTableSerializer;
import java.net.URL;
import java.util.Map;
import org.apache.log4j.Level;


public class SedTestBase extends TestCase
{
	static Logger logger = Logger.getLogger( SedTestBase.class );

	static SedLibTestUtils  tu = SedLibTestUtils.getTestUtils();

	static public final int SUCCESS = 0;
	static public final int FAILURE = 1;
	static public final String  EXT_VOT  = ".vot";
	static public final String  EXT_XML  = ".xml";
	static public final String  EXT_FITS = ".fits";

	static public String getOutputDir() { return m_outputDir; }
	static String m_dataRoot = "";
        static String m_testRoot = "";
        static String m_outputDir = "";

	static
	{
//                m_testRoot = System.getProperty( "TEST_ROOT" );
                URL testDataUrl = SedTestBase.class.getResource("/test_data");

                m_testRoot = testDataUrl.getPath();
		m_dataRoot = m_testRoot+"/data/";
                m_outputDir = m_testRoot+"/out/";
                SedLibTestUtils.setOutputDirectories (m_outputDir);
                SedLibTestUtils.GOOD_DIR = m_testRoot+"/baseline/";
                SedLibTestUtils.DATA_DIR = m_dataRoot;
	}


    /** 
     *  Read file of the specified format to populate SED object 
     */
    static public Sed readSED( SedFormat format, String fname )
    {
	Sed doc;

	try
	{
	    ISedDeserializer deserializer = SedIOFactory.createDeserializer( format );
	    doc = deserializer.deserialize( fname );
	}
	catch ( Exception exp )
	{
	    handleException( exp );
	    doc = null;
	}

	return doc;
    }

    /** 
     *  Write the SED object in the specified format.
     */
    static public int writeSED( SedFormat format, String fname, Sed doc )
    {
	int result = SUCCESS;
	
	try
	{
	    ISedSerializer serializer = SedIOFactory.createSerializer( format );
	    serializer.serialize( fname, doc );
	}
	catch ( Exception exp )
	{
	    handleException( exp );
	    result = FAILURE;
	}

	return result;
    }

    // ********************************************************************************
    //  MCD CUT LINE
    // ********************************************************************************

	static public Sed loadVOTableAndWriteMessages( String inFileName, String messageFile )
	{
		Sed document = null;

		document = loadVOTable( inFileName );

		PrintStream messageStream = null;
		try
		{
			messageStream = new PrintStream( new File( messageFile ) );
		}
		catch( FileNotFoundException exp )
		{
			throw new RuntimeException( "Can't write messages to '"
					+ messageFile +"' " + exp.getMessage() );
		}

/*		SEDMessager.reportMessages( SEDMessager.INFO,
			"Messages after loading votable", messageStream );
		SEDMessager.clear();
*/
			
		return document;
	}

	static public int grepSedLog( String grepFor, String testName )
	{
		return tu.grepIt( grepFor, getOutputDir() + testName + ".sedlog",
						SedLibTestUtils.mkStdoutFileName( testName),
						SedLibTestUtils.mkStderrFileName( testName) );
	}

	static public int diffSedLog( String testName )
	{
		return tu.DIFFIT( getOutputDir() + testName + ".sedlog",
						SedLibTestUtils.mkGoodFileName( testName ),
						SedLibTestUtils.mkDiffFileName( testName ),
						SedLibTestUtils.mkDiffErrorFileName( testName) );
	}

	static public Sed loadVOTable( String inFileName )
	{
		Sed document = null;

		try
		{
			VOTableDeserializer de = new VOTableDeserializer();

			document = de.deserialize( new FileInputStream(
							new File( m_dataRoot + inFileName ) ) );
		}
		catch ( FileNotFoundException exp )
		{
			assertTrue( "Failed to load document: " + exp.getMessage(), false );
		}
		catch ( Exception exp )
		{
			handleException( exp );
		}
		return document;
	}

	static public Sed loadFits( String inFileName )
	{
		Sed document = null;
/*
		try
		{
			FitsDeserializer de = new FitsDeserializer();

			document = de.deserialize( new FileInputStream(
							new File( m_dataRoot + inFileName ) ) );
		}
		catch ( FileNotFoundException exp )
		{
			assertTrue( exp.getMessage(), false );
		}
		catch ( Exception exp )
		{
			handleException( exp );
		}
*/
		return document;
	}

	static public Sed loadXML( String inFileName )
	{
		Sed document = null;

/*		try
		{
			XMLDeserializer de = new XMLDeserializer();

			document = de.deserialize( new FileInputStream(
							new File( m_dataRoot + inFileName ) ) );
		}
		catch ( FileNotFoundException exp )
		{
			assertTrue( exp.getMessage(), false );
		}
		catch ( Exception exp )
		{
			handleException( exp );
		}
*/
		return document;
	}

	static public int writeVOTable( String outFileName, Sed sed )
	{
		int result = SUCCESS;

		try
		{
			VOTableSerializer se = new VOTableSerializer();
			se.serialize( outFileName, sed );
		}
		catch ( Exception exp )
		{
			handleException( exp );
			result = FAILURE;
		}

		return result;
	}


	static public int writeFits( String outFileName, Sed sed )
	{
		int result = SUCCESS;

		try
		{
//			FitsSerializer se = new FitsSerializer();
//			se.serialize( outFileName, sed );
		}
		catch ( Exception exp )
		{
			handleException( exp );
			result = FAILURE;
		}

		return result;
	}

	static public int writeXML( String outFileName, Sed sed )
	{
		int result = SUCCESS;

		try
		{
//			XMLSerializer se = new XMLSerializer();
//			se.serialize( outFileName, sed );
		}
		catch ( Exception exp )
		{
			handleException( exp );
			result = FAILURE;
		}

		return result;
	}



	boolean copySedLog( String testName )
	{
		Runtime runtime = Runtime.getRuntime();

		String cmd = "cp sed.log " + getOutputDir()+testName+".sedlog";

		try
		{
			runtime.exec( cmd );
		}
		catch( IOException exp )
		{
			return false;
		}

		// wait for the file to be copied; I think it's possible to
		// return and to try to used the file before it's 'settled'.

		try
		{
			Thread.sleep( 2000 );
		}
		catch( InterruptedException exp )
		{
			System.err.println( "copySedLog: caught iterupped exception" );
			return false;
		}

		return true;
	}

	PrintStream m_savedSystemErr = null;
	PrintStream m_savedSystemOut = null;
	public void setSystemErr( String fileName )
	{
		if ( fileName == null )
		{
			System.setErr( m_savedSystemErr );
			m_savedSystemErr = null;
		}
		else
		{
			m_savedSystemErr = System.err;

			try
			{
				PrintStream printStream = new PrintStream( new File( fileName ) );
				System.setErr( printStream );
			}
			catch( FileNotFoundException exp )
			{
				System.out.println( exp.getMessage());
				exp.printStackTrace( System.out );
			}

		}
	}

	public void setSystemOut( String fileName )
	{
		if ( fileName == null )
		{
			System.setOut( m_savedSystemOut );
			m_savedSystemOut = null;
		}
		else
		{
			m_savedSystemOut = System.out;

			try
			{
				PrintStream printStream = new PrintStream( new File( fileName ) );
				System.setOut( printStream );
			}
			catch( FileNotFoundException exp )
			{
				System.out.println( exp.getMessage());
				exp.printStackTrace( System.out );
			}

		}
	}




	// This has to be here or we get this error when compiling derived
	// classes:
	//  testRead (java.lang.NoSuchMethodError: cfa.vo.sedlib.SedTestBase:
	//  method <init>(Ljava/lang/String;)V not found
	//
	public SedTestBase( String name )
	{
		super(name);
	}

    static void handleException( Throwable exp )
    {
	System.err.println( exp );

	StackTraceElement[] elems = exp.getStackTrace();
	for ( int ii = 0; ii < Math.min(10,elems.length); ii++ )
	{
	    String msg = elems[ii].toString();
	    System.err.println( "  @ " + msg.substring(0,Math.min(msg.length(), 90) ) );
	}
    }
}
