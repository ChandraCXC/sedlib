/***********************************************************************
*
* File: TestBasicSpectrumIO.java
*
* Author:  Joe Miller                                Created: 2011-03-18
*
* Virtual Astrophysical Observatory; contributed by Center for Astrophysics
*
* Update History:
*   2011-03-18:  JM  Create
* 
***********************************************************************/


package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;

import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedConstants;
import cfa.vo.testtools.SedLibTestUtils;
import junit.framework.Test;
import junit.framework.TestSuite;

import cfa.vo.sedlib.io.SedFormat;

/**
   Tests Sedlib ability to create, read and write the Spectrum objects
*/	

public class TestCase5 extends SedTestBase
{
    boolean keep = true;

    public TestCase5( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestCase5.class );

	return suite;
    }


    /**
     *  See TestPlan, TestCase 5.1 - 5.10
     */
    public void testCase5_1_to_5_10()
    {

        Sed sed;
        String testName = "testCase5_1_to_5_10";
        System.out.println("   run "+testName+"()");

	    SedFormat format = SedFormat.VOT;
	    String filename = "Extensions." + format.exten();
	    int rc;


        String inputFilename = SedLibTestUtils.mkInFileName( filename );
        String outputFilename = SedLibTestUtils.mkOutFileName( filename );

        /* Read input file */
        sed= readSED( format , inputFilename );
        assertNotNull( testName + ": Document load failed - " + filename, sed );

        /* Write output file */
        rc = writeSED( format , outputFilename, sed );
        assertEquals( testName + ": Failed to write " + filename, 0, rc );

        rc = SedLibTestUtils.DIFFIT( filename );
        assertEquals( testName + ": Diff failed - " + filename, 0, rc );

    }

    /**
     *  Test the api by manipulating the custom 
     *  parameters directory
     */
    public void testCase5_11()
    {

        String testName = "testCase5_11";
        System.out.println("   run "+testName+"()");
        Param param = null;


        Spectrum segment = CompleteObjects.createSpectrum();


        // get, remove and add custom params
        List<? extends Param>params = segment.getCustomParams ();
        List<Param>constParams = CompleteObjects.createParams();

        for (int ii=0; ii<params.size (); ii++)
        {
            assertTrue (testName + ": Custom parameters differed.", constParams.get(ii).equals(params.get(ii)));

            // set the parameter id
            param = new Param (params.get(ii));
            param.setId ("ID"+ii);
            try
            {
                segment.addCustomParam (param);
            }
            catch (SedException exp)
            {
                fail (testName + ": "+exp.getMessage ());
            }
            
        }

        try
        {
            segment.addCustomParam (param); 
            fail ("Should not be able to add a parameter with an existing id.");
        }
        catch (SedException exp)
        {
            ;
        }

        for (int ii=0; ii<params.size (); ii++)
        {
        	Param tmpParam = null;
            param = null;

            try
            {
                param = segment.findCustomParam ("ID"+ii);
                segment.removeCustomParam ("ID"+ii);
            }
            catch (SedException exp)
            {
                fail (testName + ": "+exp.getMessage ());
            }
            
            tmpParam = new Param (constParams.get(ii));
            tmpParam.setId ("ID"+ii);
            assertTrue (testName + ": Custom parameters differed.", tmpParam.equals(param));

        }

        params = segment.getCustomParams ();

        for (int ii=0; ii<params.size (); ii++)
            assertTrue (testName + ": Custom parameters differed.", constParams.get(ii).equals(params.get(ii)));

    }

    /**
     *  Test the api by manipulating the custom
     *  groups directory
     */
    public void testCase5_12()
    {

        String testName = "testCase5_12";
        System.out.println("   run "+testName+"()");


        Spectrum segment = CompleteObjects.createSpectrum();

        // get, remove and add custom groups
        List<Group>constGroups = new ArrayList<Group> (2);
        List<? extends Group>groups;
        Group group;
        constGroups.add (new Group ());
        constGroups.add (new Group ());

        group = constGroups.get (0);
        group.setGroupId ("Group0");
        group = constGroups.get (1);
        group.setGroupId ("Group1");

        segment.setCustomGroups (constGroups);
        groups = segment.getCustomGroups ();

        for (int ii=0; ii<groups.size (); ii++)
        {
            assertNotNull (testName + ": Custom group id should not be null.", groups.get(ii).getGroupId ());
            assertEquals (testName + ": Custom groups differed.", constGroups.get(ii).getGroupId(), groups.get(ii).getGroupId ());

        }
        // set the parameter id
        group = new Group ();
        group.setGroupId ("Group2");
        try
        {
            segment.addCustomGroup (group);
        }
        catch (SedException exp)
        {
        	fail (testName +": "+exp.getMessage ());
        }

        try
        {
            segment.addCustomGroup (groups.get(0));
            fail ("Should not be able to add a group with an existing id.");
        }
        catch (SedException exp)
        {
            ;
        }

        try
        {
            group = segment.findCustomGroup ("Group2");
            segment.removeCustomGroup ("Group2");
        }
        catch (SedException exp)
        {
            fail (testName + ": "+exp.getMessage ());
        }
        assertEquals (testName + ": Custom group differed.","Group2", group.getGroupId ());

 

        groups = segment.getCustomGroups ();

        for (int ii=0; ii<groups.size (); ii++)
            assertEquals (testName + ": Custom groups differed.", constGroups.get(ii).getGroupId (), groups.get(ii).getGroupId ());

    }

    /**
     *  Test the api by manipulating the custom
     *  data params directory
     */
    public void testCase5_13()
    {

        String testName = "testCase5_13";
        System.out.println("   run "+testName+"()");
        SedFormat format = SedFormat.VOT;
        String filename1 = "testCase5_13_1." + format.exten();
        String filename2 = "testCase5_13_2." + format.exten();
        
        int rc = 0;

        double dvalues[] = {1.1, 2.2, 3.3};
        int ivalues[] = {1,2,3};
        String svalues[] = {"ABC", "DEF", "GHI"};

        double tmpDvalues[] = null;
        int tmpIvalues[] = null;
        String tmpSvalues[] = null;
        
        Field field = null;
        Field fields[] = new Field[5];

        fields[0] = new Field ("nameA", null, null, null, "idA");
        fields[1] = new Field ("nameB", "unitB", "ucdB", "utypeB", "idB");
        fields[2] = new Field ("nameC", null, null, null, "idC");
        fields[3] = new Field ("nameC", null, null, null, null);
        fields[4] = new Field ("nameA2", "unitA", "ucdA", null, "idA");


        /* Create SED with one (empty) segment */
        Sed sed = EmptyObjects.createSED( 1 );

        /* Get the Segment */
        Segment segment = sed.getSegment(0);
        assertNotNull( "Failed to create SEGMENT: " , segment );

        /* Create a List of Point objects with values..  */
        /* Assign to Segment                                 */
        ArrayOfPoint data = new ArrayOfPoint ();
        List<Point> points = data.createPoint ();
        for ( int ii = 0; ii < 3; ii++ )
        {
            points.add( CompleteObjects.createPoint( ii ) );
        }
        segment.setData( data );


        try
        {
            segment.addCustomData (fields[0], dvalues);
            segment.addCustomData (fields[1], ivalues);
            segment.addCustomData (fields[2], svalues);

            // add a second param again
            segment.addCustomData (fields[4], dvalues);
        }
        catch (SedException exp)
        {
            fail (exp.getMessage ());
        }
        
        try
        {
            segment.addCustomData (fields[3], dvalues);
            fail (testName + ": Should not be able to add values with empty field id");
        }
        catch (SedException exp)
        {
        	;
        }

        // remove the data from one of the points
        try
        {
            points.get(1).removeCustomParam ("idA");
            points.get(0).removeCustomParam("idB");
            points.get(2).removeCustomParam ("idC");
        }
        catch (SedException exp)
        {
        	fail (exp.getMessage ());
        }

        try
        {
            tmpDvalues = (double [])segment.getCustomDataValues ("idA");
            tmpIvalues = (int [])segment.getCustomDataValues ("idB");
            tmpSvalues = (String [])segment.getCustomDataValues ("idC");
        }
        catch (SedException exp)
        {
            fail (exp.getMessage ());
        }

        for (int ii=0; ii<dvalues.length; ii++)
        {
        	if (ii == 1)
        		assertEquals (testName +": Retrieved custom double values aren't equal.", SedConstants.DEFAULT_DOUBLE, tmpDvalues[ii]);
        	else
        		assertEquals (testName +": Retrieved custom double values aren't equal.", dvalues[ii], tmpDvalues[ii]);
        	if (ii == 0)	
                assertEquals (testName +": Retrieved custom int values aren't equal.", (int)SedConstants.DEFAULT_INTEGER, tmpIvalues[ii]);
        	else
        		assertEquals (testName +": Retrieved custom int values aren't equal.", ivalues[ii], tmpIvalues[ii]);
        	if (ii == 2)
                assertEquals (testName +": Retrieved custom string values aren't equal.", SedConstants.DEFAULT_STRING, tmpSvalues[ii]);
        	else
                assertEquals (testName +": Retrieved custom string values aren't equal.", svalues[ii], tmpSvalues[ii]);
        	

        }

        try
        {
            tmpDvalues = (double [])segment.getCustomDataValues (null);
            fail (testName + ": Should not be able to get custom values from a null id");
        }
        catch (SedException exp)
        {
            ;
        }

        for (int ii=0; ii<tmpDvalues.length; ii++)
        {
            tmpDvalues[ii] += 10;
            tmpIvalues[ii] += 10;
            tmpSvalues[ii] += "_test";
        }

        try
        {
            segment.setCustomDataValues ("idA", tmpDvalues);
            segment.setCustomDataValues ("idB", tmpIvalues);
            segment.setCustomDataValues ("idC", tmpSvalues);
        }
        catch (SedException exp)
        {
            fail (exp.getMessage ());
        }

        try 
        {
            field =  segment.getCustomDataInfo ("idA");
        }
        catch (SedException exp)
        {
            fail (exp.getMessage ());
        }

        assertTrue (testName +": Custom fields don't match.", field.equals(fields[4]));

        try
        {
            segment.setCustomDataInfo (fields[0]);
        }
        catch (SedException exp)
        {
            fail (exp.getMessage ());
        }

        String outputFilename = SedLibTestUtils.mkOutFileName( filename1 );
        
        /* Write the created sed */
        rc = writeSED( format , outputFilename, sed );
        assertEquals( testName + ": Failed to write " + filename1, 0, rc );
        
        rc = SedLibTestUtils.DIFFIT( filename1 );
        assertEquals( testName + ": Diff failed - " + filename1, 0, rc );
        
        /* Read the sed from the output */
        sed= readSED( format , outputFilename );
        assertNotNull( testName + ": Document load failed - " + filename1, sed );
        
        /* Write it back out */
        outputFilename = SedLibTestUtils.mkOutFileName( filename2 );
        rc = writeSED( format , outputFilename, sed );
        assertEquals( testName + ": Failed to write " + filename2, 0, rc );
        
        rc = SedLibTestUtils.DIFFIT( filename2 );
        assertEquals( testName + ": Diff failed - " + filename2, 0, rc );
      

        
    }

}
