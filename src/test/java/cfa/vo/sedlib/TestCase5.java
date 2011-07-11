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
import cfa.vo.sedlib.common.SedParsingException;
import cfa.vo.sedlib.common.SedWritingException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedConstants;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.testtools.SedLibTestUtils;
import junit.framework.Test;
import junit.framework.TestSuite;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cfa.vo.sedlib.io.SedFormat;

/**
   Test Exensibility (Requirement 8.2)
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
     *  5.1 The table has an unknown and unreferenced Field
     *
     * <pre>
     *   &lt;FIELD  name="DBref" datatype="char" arraysize="*"&gt;
     *    &lt;DESCRIPTION&gt; unknown, unreferenced column&lt;/DESCRIPTION&gt;
     *   &lt;/FIELD&gt;
     * </pre>
     *
     * 5.2 The table has an unknown field referenced in an unknown group
     *
     * <pre>
     * &lt;FIELD ID="col2" name="col2" datatype="double" utype="spec:Spectrum.Model.FluxAxis.Value" unit="Jy"&gt;
     *   &lt;DESCRIPTION&gt; unknown column referenced in unknown group.&lt;/DESCRIPTION&gt;
     * &lt;/FIELD&gt;
     *
     * &lt;GROUP utype="spec:Spectrum.Model.FluxAxis"&gt;
     *    &lt;DESCRIPTION&gt; unknown group &lt;/DESCRIPTION&gt;
     *    &lt;FIELDRef ref="col2"/&gt;
     * &lt;/GROUP&gt;
     * </pre>
     *
     * 5.3 The table has an unknown field reference in a known group outside of Data
     *
     * <pre>
     *   &lt;FIELD ID="col3" name="col3" datatype="double"&gt;
     *       &lt;DESCRIPTION&gt; unknown column referenced in known group outside Data.&lt;/DESCRIPTION&gt;
     *   &lt;/FIELD&gt;
     *
     *   &lt;GROUP ID="DataID" name="DataID" utype="spec:Spectrum.DataID"&gt;
     *        &lt;FIELDref ref="col3" utype="spec:Spectrum.DataID.Code"/&gt;
     *    &lt;/GROUP&gt;
     * </pre>
     *
     * 5.4 The table has an unknown field referenced in a known group inside of Data
     *
     * <pre>
     *   &lt;FIELD ID="col4" name="col4" datatype="double" utype="spec:Spectrum.Data.FluxAxis.Fluff" unit="Jy"&gt;
     *       &lt;DESCRIPTION&gt; unknown column referenced in known group inside Data.&lt;/DESCRIPTION&gt;
     *   &lt;/FIELD&gt;
     *
     *   &lt;GROUP ID="Data.FluxAxis" name="Data.FluxAxis" utype="spec:Spectrum.Data.FluxAxis"&gt;
     *       &lt;FIELDref ref="col4"/&gt;
     *   &lt;/GROUP&gt;
     * </pre>
     *
     * 5.5 The table has an unknown, unreferenced parameter not in the data
     *
     * <pre>
     *   &lt;PARAM datatype="char" name="p1" value="blah" arraysize="*"&gt;
     *       &lt;DESCRIPTION&gt; unknown, unreferenced parameter.&lt;/DESCRIPTION&gt;
     *   &lt;/PARAM&gt;
     * </pre>
     *
     * 5.6 The table has an unknown, referenced parameter in unknown group
     *
     * <pre>
     *   &lt;PARAM ID="p2" datatype="char" name="p2" utype="spec:Spectrum.Model.FluxAxis.Method" value="blah" arraysize="*"&gt;
     *       &lt;DESCRIPTION&gt; unknown, referenced in unknown group.&lt;/DESCRIPTION&gt;
     *   &lt;/PARAM&gt;
     *
     *   &lt;GROUP ID="Model.FluxAxis" name="Model.FluxAxis" utype="spec:Spectrum.Model.FluxAxis"&gt;
     *       &lt;PARAMref ref="p2"/&gt;
     *    &lt;/GROUP&gt;
     * </pre>
     *
     * 5.7 The table has an unknown, referenced parameter in a known group
     *
     * <pre>
     *   &lt;PARAM ID="p3" datatype="double" name="p3"
     *           utype="spec:Spectrum.Model.FluxAxisConfidence" value="0.102"&gt;
     *       &lt;DESCRIPTION&gt; unknown, referenced in known group.&lt;/DESCRIPTION&gt;
     *   &lt;/PARAM&gt;
     *
     *   &lt;GROUP ID="DataID" name="DataID" utype="spec:Spectrum.DataID"&gt;
     *       &lt;PARAMref ref="p3"/&gt;
     *   &lt;/GROUP&gt;
     * </pre>
     *
     * 5.8 The table has an unknown parameter in a data group
     *
     * <pre>
     *   &lt;PARAM ID="p5" datatype="char" name="p5" utype="spec:Spectrum.Data.FluxAxis.test" value="test" arraysize="*"&gt;
     *       &lt;DESCRIPTION&gt; unknown param referenced in known data group.&lt;/DESCRIPTION&gt;
     *   &lt;/PARAM&gt;
     *
     *   &lt;GROUP ID="Data.FluxAxis" name="Data.FluxAxis" utype="spec:Spectrum.Data.FluxAxis"&gt;
     *      &lt;PARAMref ref="p5"&gt;
     *   &lt;/GROUP&gt;
     * </pre>
     *
     * 5.9 The table has a parameter referenced in multiple groups.
     *
     * <pre>
     *   &lt;PARAM ID="p6" datatype="char" name="p6" value="test2" arraysize="*"&gt;
     *       &lt;DESCRIPTION&gt; unknown param referenced in multiple group.&lt;/DESCRIPTION&gt;
     *   &lt;/PARAM&gt;
     *
     *   &lt;GROUP ID="DataID" name="DataID" utype="spec:Spectrum.DataID"&gt;
     *     &lt;PARAMref ref="p6" utype="my.utype"/&gt;
     *   &lt;/GROUP&gt;
     *
     *   &lt;GROUP ID="Model" name="Model" utype="spec:Spectrum.Model"&gt;
     *     &lt;DESCRIPTION&gt;Model Data&lt;/DESCRIPTION&gt;
     *     &lt;PARAMref ref="p6" utype="my.other.utype"/&gt;
     *   &lt;/GROUP&gt;
     * </pre>
     *
     * 5.10 The table has an unknown group with a subgroup
     *
     * <pre>
     *   &lt;GROUP ID="Model" name="Model" utype="spec:Spectrum.Model"&gt;
     *     &lt;DESCRIPTION&gt;Model Data&lt;/DESCRIPTION&gt;
     *     &lt;GROUP ID="Model.FluxAxis" name="Model.FluxAxis" utype="spec:Spectrum.Model.FluxAxis"&gt;
     *     &lt;/GROUP&gt;
     *   &lt;/GROUP&gt;
     * </pre>
     * 
     */
    public void testCase5_1_to_5_10()
    {

        Sed sed;
        String testName = "testCase5_1_to_5_10";
        System.out.println("   run "+testName+"()");

	    String votFilename = "Extensions." + SedFormat.VOT.exten();
        String fitsFilename = "Extensions." + SedFormat.FITS.exten();
        String fits2Filename = "Extensions2." + SedFormat.FITS.exten();


        /* Read input file */
        sed = this.readSed (votFilename, SedFormat.VOT );

        /* Write output file */
        this.writeSed (votFilename, SedFormat.VOT, sed);
        this.writeSed (fitsFilename, SedFormat.FITS, sed);  
        
        /* Read and write fits file */
        sed = this.readSed (fits2Filename, SedFormat.FITS );
        this.writeSed (fits2Filename, SedFormat.FITS, sed);  

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
            param = (Param)params.get(ii).clone ();
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
            
            tmpParam = (Param)constParams.get(ii).clone ();
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
        
        /* Write the created sed */
        this.writeSed (filename1, SedFormat.VOT, sed);
        
        /* Read the sed from the output */        
        String inputFilename = SedLibTestUtils.mkOutFileName( filename1 );

        try
        {
            sed = Sed.read( inputFilename, SedFormat.VOT );
        } catch (SedParsingException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex)
;
            fail("error while parsing the document: " +inputFilename);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex)
;
            fail("inconsistency detected in document: "+inputFilename);
        } catch (IOException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex)
;
            fail("IO problems reading document: "+inputFilename);
        } catch (SedNoDataException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex);
            fail("No data found in segment: "+inputFilename);
        }

        /* Write it back out */
        this.writeSed (filename2, SedFormat.VOT, sed);

        
    }

    Sed readSed (String filename, SedFormat format)
    {
        String inputFilename = SedLibTestUtils.mkInFileName( filename );
        Sed sed = null;

        try
        {
            sed = Sed.read( inputFilename, format );
        } catch (SedParsingException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex)
;
            fail("error while parsing the document: " +inputFilename);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex)
;
            fail("inconsistency detected in document: "+inputFilename);
        } catch (IOException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex)
;
            fail("IO problems reading document: "+inputFilename);
        } catch (SedNoDataException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex);
            fail("No data found in segment: "+inputFilename);
        }

        return sed;

    }

    void writeSed (String filename, SedFormat format, Sed sed)
    {
        String outputFilename = SedLibTestUtils.mkOutFileName( filename );

        try
        {
            sed.write( outputFilename, format );
        } catch (SedWritingException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex);
            fail("error while parsing the document: " +outputFilename);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex);
            fail("inconsistency detected in document: "+outputFilename);
        } catch (IOException ex) {
            Logger.getLogger(TestCase5.class.getName()).log(Level.SEVERE, null, ex);
            fail("IO problems writing document: "+outputFilename);
        }

        // verify the output file name
        int rc = 0;
        
        if (format == SedFormat.FITS)
        	rc = SedLibTestUtils.diffFits(filename);
        else
        	rc = SedLibTestUtils.DIFFIT( filename );
        
        	
        assertEquals( TestCase5.class.getName () + ": Diff failed - " + filename, 0, rc );       

    }

}
