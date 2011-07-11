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
* File: TestCurationIO.java
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
   Tests Sedlib ability to create, read and write the Curation object
*/	

public class TestCurationIO extends SedTestBase
{
    Sed m_sed = null;
    String m_fname = null;
    boolean keep = true;
    int rc = 1;

    public TestCurationIO( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestCurationIO.class );

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
     *  Tests ability to create and serialize Curation objects
     *  <p>
     *  Create a new Curation object which is fully defined
     *  and populated with values.  Write this object out in
     *  each of the supported formats.  
     */
    public void testCuration_new()
    {
	String testName = "testCuration_new";
	System.out.println("   run "+testName+"()");

	/* Create SED with one (empty) segment */
	m_sed = EmptyObjects.createSED( 1 );

	/* Set Spectrum namespace 
	m_sed.setNamespace( "spec:" );
*/

	/* Get the Segment */
	Segment segment = m_sed.getSegment(0);
	assertNotNull( "Failed to create SEGMENT: " , segment );

	/* Create populated Curation object and assign to Segment. */
	Curation curation = CompleteObjects.createCuration();
	segment.setCuration( curation );

	/* Write SED with Curation to file in each of the supported formats */
	for (SedFormat format : SedFormat.values())
	{
if (format == SedFormat.XML)
   continue;

	    m_fname = "Curation." + format.exten();
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
     *  Tests ability to load Curation objects from VOT format
     *  <p>
     *  Load file containing a fully defined Curation object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testCuration_copyVOT()
    {
	String testName = "testCuration_copyVOT";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.VOT;
	m_fname = "Curation." + format.exten();

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
     *  Tests ability to load Curation objects from FITS format
     *  <p>
     *  Load file containing a fully defined Curation object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testCuration_copyFITS()
    {
	String testName = "testCuration_copyFITS";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.FITS;
	m_fname = "Curation." + format.exten();

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
     *  Tests ability to load Curation objects from XML format
     *  <p>
     *  Load file containing a fully defined Curation object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void estCuration_copyXML()
    {
	String testName = "testCuration_copyXML";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.XML;
	m_fname = "Curation." + format.exten();

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
