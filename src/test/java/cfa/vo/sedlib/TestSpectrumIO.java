/***********************************************************************
*
* File: TestSpectrumIO.java
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

import cfa.vo.sedlib.common.SedInconsistentException;
import junit.framework.Test;
import junit.framework.TestSuite;

import cfa.vo.sedlib.io.SedFormat;

/**
   Tests Sedlib ability to create, read and write the Spectrum objects
*/	

public class TestSpectrumIO extends SedTestBase
{
    Sed m_sed = null;
    String m_fname = null;
    boolean keep = true;
    int rc = 1;

    public TestSpectrumIO( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestSpectrumIO.class );

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
     *  Tests ability to create and serialize Spectrum objects
     *  <p>
     *  Create a new Spectrum object which is fully defined
     *  and populated with values.  Write this object out in
     *  each of the supported formats.  
     */
    public void testSpectrum_new()
    {
	String testName = "testSpectrum_new";
	System.out.println("   run "+testName+"()");

	/* Create SED with one (empty) segment */
	m_sed = EmptyObjects.createSED( 0 );

	/* Set Spectrum namespace 
	m_sed.setNamespace( "spec:" );
*/

	/* Get the Segment */
	Spectrum segment = CompleteObjects.createSpectrum();
	assertNotNull( "Failed to create SEGMENT: " , segment );

	/* Assign to SED                                 */
        try
        {
	    m_sed.addSegment(segment);
        }
        catch (SedInconsistentException exp)
        {
            throw new RuntimeException (exp.getMessage (), exp);
        }


	/* Write SED with Spectrum to file in each of the supported formats */
	for (SedFormat format : SedFormat.values())
	{
if (format == SedFormat.XML)
   continue;

	    m_fname = "Spectrum." + format.exten();
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
     *  Tests ability to load Spectrum objects from VOT format
     *  <p>
     *  Load file containing a fully defined Spectrum object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testSpectrum_copyVOT()
    {
	String testName = "testSpectrum_copyVOT";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.VOT;
	m_fname = "Spectrum." + format.exten();

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
     *  Tests ability to load Spectrum objects from FITS format
     *  <p>
     *  Load file containing a fully defined Spectrum object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testSpectrum_copyFITS()
    {
	String testName = "testSpectrum_copyFITS";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.FITS;
	m_fname = "Spectrum." + format.exten();

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
     *  Tests ability to load Spectrum objects from XML format
     *  <p>
     *  Load file containing a fully defined Spectrum object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void estSpectrum_copyXML()
    {
	String testName = "testSpectrum_copyXML";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.XML;
	m_fname = "Spectrum." + format.exten();

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
