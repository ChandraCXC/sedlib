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
* File: TestCoordsysIO.java
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
   Tests Sedlib ability to create, read and write the CoordSys object
*/	

public class TestCoordSysIO extends SedTestBase
{
    Sed m_sed = null;
    String m_fname = null;
    boolean keep = true;
    int rc = 1;

    public TestCoordSysIO( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestCoordSysIO.class );

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
     *  Tests ability to create and serialize CoordSys objects
     *  <p>
     *  Create a new CoordSys object which is fully defined
     *  and populated with values.  Write this object out in
     *  each of the supported formats.  
     */
    public void testCoordSys_new()
    {
	String testName = "testCoordSys_new";
	System.out.println("   run "+testName+"()");

	/* Create SED with one (empty) segment */
	m_sed = EmptyObjects.createSED( 1 );

	/* Set Spectrum namespace 
	m_sed.setNamespace( "spec:" );
*/

	/* Get the Segment */
	Segment segment = m_sed.getSegment(0);
	assertNotNull( "Failed to create SEGMENT: " , segment );

	/* Create populated CoordSys object and assign to Segment. */
	CoordSys coordsys = CompleteObjects.createCoordSys();
	segment.setCoordSys( coordsys );

	/* Write SED with CoordSys to file in each of the supported formats */
	for (SedFormat format : SedFormat.values())
	{
if (format == SedFormat.XML)
   continue;

	    m_fname = "CoordSys." + format.exten();
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
     *  Tests ability to load CoordSys objects from VOT format
     *  <p>
     *  Load file containing a fully defined CoordSys object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testCoordSys_copyVOT()
    {
	String testName = "testCoordSys_copyVOT";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.VOT;
	m_fname = "CoordSys." + format.exten();

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
     *  Tests ability to load CoordSys objects from FITS format
     *  <p>
     *  Load file containing a fully defined CoordSys object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testCoordSys_copyFITS()
    {
	String testName = "testCoordSys_copyFITS";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.FITS;
	m_fname = "CoordSys." + format.exten();

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
     *  Tests ability to load CoordSys objects from XML format
     *  <p>
     *  Load file containing a fully defined CoordSys object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void estCoordSys_copyXML()
    {
	String testName = "testCoordSys_copyXML";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.XML;
	m_fname = "CoordSys." + format.exten();

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
