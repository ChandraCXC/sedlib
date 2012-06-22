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
* File: TestParamsIO.java
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

import java.util.List;
import java.util.HashMap;

/**
   Tests Sedlib ability to create, read and write the Params objects
*/	

public class TestParamsIO extends SedTestBase
{
    Sed m_sed = null;
    String m_fname = null;
    boolean keep = true;
    int rc = 1;

    public TestParamsIO( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestParamsIO.class );

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
     *  Tests ability to create and serialize Param objects
     *  <p>
     *  Create a new List of Param objects which are fully defined
     *  and populated with values.  Write this object out in
     *  each of the supported formats.  
     */
    public void testParams_new()
    {
	String testName = "testParams_new";
	System.out.println("   run "+testName+"()");

	/* Create SED with one (empty) segment */
	m_sed = EmptyObjects.createSED( 1 );

	/* Get the Segment */
	Segment segment = m_sed.getSegment(0);
	assertNotNull( "Failed to create SEGMENT: " , segment );

	/* Create a List of Point objects with values..  */
	/* Assign to Segment                                 */
	segment.setCustomParams( CompleteObjects.createParams() );


	/* Write SED with Params to file in each of the supported formats */
	for (SedFormat format : SedFormat.values())
	{
	    if (format == SedFormat.XML)
		continue;

	    m_fname = "Params." + format.exten();
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
     *  Tests Param object's getCastValue() behavior.
     *  <p>
     *  Load file containing a fully defined Params object, 
     *  extract values using getCastValue method and compare to 
     *  known values.
     */
    public void testParams_getCast()
    {
	String testName = "testParams_getCast";
		System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.VOT;
	m_fname = "Params." + format.exten();

	/* Read input file */
	m_sed = readSED( format, tu.mkInFileName( m_fname ) );
	assertNotNull( testName + ": Document load failed - " + m_fname, m_sed );

	/* Set up expected value list */
	HashMap<String,String> goodVals = new HashMap<String,String>();
	goodVals.put( "pi", "3.14159" );
	goodVals.put( "baddbl1", "NaN" );
	goodVals.put( "baddbl2", "NaN" );
	goodVals.put( "baddbl3", "NaN" );
	goodVals.put( "baddbl4", "NaN" );
	goodVals.put( "daysofweek", "7" );
	goodVals.put( "TargetType", "Galaxy" );
	goodVals.put( "camdate", "2011-01-04T22:01:59" );
	goodVals.put( "age", "1.8" );
	goodVals.put( "TargetPosA", "233.737917" );
	goodVals.put( "TargetPosB", "23.50333" );

	List<? extends Param> params;
	String strval;
	String pname;
	String suffix = "A";

	/* Get params list from segment */
	params = m_sed.getSegment(0).getCustomParams();

	rc = 0;
        for (int ii=0; ii<params.size(); ii++)
	{
	    pname = params.get(ii).getName();

	    if ( pname.equals("TargetPos") )
	    {
		/* there are 2 of them.. need to separate */
		pname = pname.concat( suffix );
		suffix = "B";
	    }

            strval = params.get(ii).getCastValue().toString();
	    if ( ! strval.equals( goodVals.get( pname ) ) )
		rc = 1;

	    assertEquals( testName + ": Param " + pname + " Failed Value check.", 0, rc );
        }

    }

    /**
     *  Tests ability to load Params objects from VOT format
     *  <p>
     *  Load file containing a fully defined Params object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testParams_copyVOT()
    {
	String testName = "testParams_copyVOT";
		System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.VOT;
	String outFileName = "Params2."+format.exten ();
	m_fname = "Params." + format.exten();

	/* Read input file */
	m_sed = readSED( format, tu.mkInFileName( m_fname ) );
	assertNotNull( testName + ": Document load failed - " + m_fname, m_sed );
	
	/* Write back out.. same format */
	rc = writeSED( format , tu.mkOutFileName( outFileName ), m_sed );
	assertEquals( testName + ": Failed to write " + outFileName, 0, rc );

	rc = tu.DIFFIT( outFileName );
	assertEquals( testName + ": Diff failed - " + outFileName, 0, rc );

	if ( (rc == 0 ) && (!keep) )
	    tu.cleanupFiles( outFileName );
    }

    /**
     *  Tests ability to load Params objects from FITS format
     *  <p>
     *  Load file containing a fully defined Params object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void testParams_copyFITS()
    {
	String testName = "testParams_copyFITS";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.FITS;
	m_fname = "Params." + format.exten();

	/* Read input file */
	m_sed = readSED( format, tu.mkInFileName( m_fname ) );
	assertNotNull( testName + ": Document load failed - " + m_fname, m_sed );

	/* Write back out.. same format */
	m_fname = "Params_copy." + format.exten();
	rc = writeSED( format , tu.mkOutFileName( m_fname ), m_sed );
	assertEquals( testName + ": Failed to write " + m_fname, 0, rc );

	rc = tu.diffFits( m_fname );
	assertEquals( testName + ": Diff failed - " + m_fname, 0, rc );

	if ( (rc == 0 ) && (!keep) )
	    tu.cleanupFiles( m_fname );
    }

    /**
     *  Tests ability to load Params objects from XML format
     *  <p>
     *  Load file containing a fully defined Params object
     *  and write the object back out in the same format.
     *  If all information is extracted properly, the output file
     *  should identically match the input.
     */
    public void estParams_copyXML()
    {
	String testName = "testParams_copyXML";
	System.out.println("   run "+testName+"()");

	SedFormat format = SedFormat.XML;
	m_fname = "Params." + format.exten();

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
