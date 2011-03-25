/***********************************************************************
*
* File: TestDataIdIO.java
*
* Author:  mdittmar                                Created: 2010-12-23
*
* Virtual Astrophysical Observatory; contributed by Center for Astrophysics
*
* Update History:
*   2010-12-23:  MCD  Create
* 
***********************************************************************/


package cfa.vo.sedlib;

import junit.framework.Test;
import junit.framework.TestSuite;

import cfa.vo.sedlib.io.SedFormat;

/**
   Tests Sedlib ability to create, read and write the DataID object
*/	

public class TestDataIdIO extends SedTestBase
{
    Sed m_sed = null;
    String m_fname = null;
    boolean keep = false;
    int rc = 1;

    public TestDataIdIO( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestDataIdIO.class );

	return suite;
    }

    /**
     *  Preparatory steps to be executed before each test.
     */
    protected void setUp()
    {
    }

    /**
     *  Cleanup steps to be executed after each test.
     */
    protected void tearDown()
    {
	m_sed = null;
	m_fname = null;
	rc = 1;
    }

    /**
     *  Tests ability to create and serialize DataID objects
     *  <p>
     *  Create a new DataID object which is fully defined
     *  and populated with values.  Write this object out in
     *  each of the supported formats.  
     */
    public void testDataId_new()
    {
	String testName = "testDataId_new";
	System.out.println("   run "+testName+"()");

	/* Create SED with one (empty) segment */
	m_sed = EmptyObjects.createSED( 1 );

	/* Set Spectrum namespace 
	m_sed.setNamespace( "spec:" );
*/

	/* Get the Segment */
	Segment segment = m_sed.getSegment(0);
	assertNotNull( "Failed to create SEGMENT: " , segment );

	/* Create populated DataId object and assign to Segment. */
	DataID dataid = CompleteObjects.createDataID();
	segment.setDataID( dataid );

	/* Write SED with DataId to file in each of the supported formats */
	for (SedFormat format : SedFormat.values())
	{
if (format == SedFormat.XML)
   continue;

	    m_fname = "DataID." + format.exten();
	    rc = writeSED( format , tu.mkOutFileName( m_fname ), m_sed );
	    assertEquals( testName + ": Failed to write " + m_fname, 0, rc );

	    /* Compare output file with baseline */
	    if ( format == SedFormat.FITS )
		rc = tu.diffFits( m_fname );
	    else
		rc = tu.DIFFIT( m_fname );

	    assertEquals( testName + ": Diff failed - " + m_fname, 0, rc );
	    if ( (rc == 0 ) && (!keep) )
		tu.cleanupFiles( m_fname );
	}
    }

    /**
     *  Tests ability to load DataID objects from VOT format
     *  <p>
     *  Load file containing a fully defined DataID object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testDataId_copyVOT()
    {
	String testName = "testDataId_copyVOT";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.VOT;
	m_fname = "DataID." + format.exten();

	/* Read input file */
	m_sed = readSED( format, tu.mkInFileName( m_fname ) );
	assertNotNull( testName + ": Document load failed - " + m_fname, m_sed );
	
	/* Write back out.. same format */
	rc = writeSED( format , tu.mkOutFileName( m_fname ), m_sed );
	assertEquals( testName + ": Failed to write " + m_fname, 0, rc );

	rc = tu.DIFFIT( m_fname );
	assertEquals( testName + ": Diff failed - " + m_fname, 0, rc );

	if ( (rc == 0 ) && (!keep) )
	    tu.cleanupFiles( m_fname );
    }

    /**
     *  Tests ability to load DataID objects from FITS format
     *  <p>
     *  Load file containing a fully defined DataID object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void  testDataId_copyFITS()
    {
	String testName = "testDataId_copyFITS";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.FITS;
	m_fname = "DataID." + format.exten();

	/* Read input file */
	m_sed = readSED( format, tu.mkInFileName( m_fname ) );
	assertNotNull( testName + ": Document load failed - " + m_fname, m_sed );

	/* Write back out.. same format */
	rc = writeSED( format , tu.mkOutFileName( m_fname ), m_sed );
	assertEquals( testName + ": Failed to write " + m_fname, 0, rc );

	rc = tu.diffFits( m_fname );
	assertEquals( testName + ": Diff failed - " + m_fname, 0, rc );

	if ( (rc == 0 ) && (!keep) )
	    tu.cleanupFiles( m_fname );
    }

    /**
     *  Tests ability to load DataID objects from XML format
     *  <p>
     *  Load file containing a fully defined DataID object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void estDataId_copyXML()
    {
	String testName = "testDataId_copyXML";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.XML;
	m_fname = "DataID." + format.exten();

	/* Read input file */
	m_sed = readSED( format, tu.mkInFileName( m_fname ) );
	assertNotNull( testName + ": Document load failed - " + m_fname, m_sed );
	
	/* Write back out.. same format */
	rc = writeSED( format , tu.mkOutFileName( m_fname ), m_sed );
	assertEquals( testName + ": Failed to write " + m_fname, 0, rc );

	rc = tu.DIFFIT( m_fname );
	assertEquals( testName + ": Diff failed - " + m_fname, 0, rc );

	if ( (rc == 0 ) && (!keep) )
	    tu.cleanupFiles( m_fname );
    }



}
