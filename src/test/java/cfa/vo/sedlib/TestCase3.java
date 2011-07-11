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
* Author:  Joe Miller                                Created: 2011-05-06
*
* Virtual Astrophysical Observatory; contributed by Center for Astrophysics
*
* Update History:
*   2011-05-06:  JM  Create
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
import cfa.vo.sedlib.common.ValidationError;
import cfa.vo.sedlib.common.ValidationErrorEnum;
import cfa.vo.testtools.SedLibTestUtils;
import junit.framework.Test;
import junit.framework.TestSuite;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.Collections;
import cfa.vo.sedlib.io.SedFormat;

/**
   Test Validation 
*/	

public class TestCase3 extends SedTestBase
{
	
	/**
    A class to tailor the format of the output messages to for
    the SED library.
    */
    public class TestCase3Formatter extends Formatter
    {

       /**
        * Override the format of the output message of the logger.
        */
       public String format (LogRecord record)
       {
           return record.getLevel() + ": " + record.getMessage()+"\n";
       }

}

    boolean keep = true;

    public TestCase3( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestCase5.class );

	return suite;
    }


    /**
     *  3.1.1 Test Sed validation of required elements. This includes:
     *   <ul>
     *   <li> curation.publisher </li>
     *   <li> target.name </li>
     *   <li> a number of characterization properties </li>
     *   <li> data.flux and data.spectral properties </li>
     *   <li> valid segment.type </li>
     *   </ul>
     * 
     */
    public void testCase3_1_1() throws SedNoDataException, SedInconsistentException
    {

        Sed sed = EmptyObjects.createSED (0);
        String testName = "testCase3_1_1";
        System.out.println("   run "+testName+"()");
        Segment segment = CompleteObjects.createSpectrum ();
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        List<ValidationErrorEnum> expectedErrors;


        // test empty sed
        assertTrue ("Validation of an empty Sed should pass.", sed.validate ());

        sed.addSegment (segment);
        segment.getChar ().getFluxAxis().setUnit ("FOO");
        segment.getChar ().getSpectralAxis ().getCoverage ().getBounds ().setExtent (new DoubleParam(1.0));
        segment.getChar ().getSpectralAxis ().getCoverage ().getBounds ().createRange ().setMin (new DoubleParam (0.0));
        segment.getChar ().getSpectralAxis ().getCoverage ().getBounds ().createRange ().setMin (new DoubleParam (1.0));
        segment.getChar ().getSpatialAxis ().getCoverage ().getBounds ().setExtent (new DoubleParam(1.0));

        sed.validate (errors);
        // completely valid segment
        assertTrue ("Sed with a single complete segment should pass.", sed.validate ());

        
        // test valid segment type
        segment.setType (null);
        assertTrue ("Segment with no segment type should pass", segment.validate ());
        
        segment.setType (new TextParam ("Photometry"));
        assertTrue ("Segment with photometry segment type should pass", segment.validate ());
        segment.setType (new TextParam ("spectrum test"));
        assertTrue ("Segment with spectrum segment type should pass", segment.validate ());
        segment.setType (new TextParam ("timeSeries "));
        assertTrue ("Segment with timeseries segment type should pass", segment.validate ());

        // test invalid segment type
        segment.setType (new TextParam ("My Photometry"));
        assertFalse ("Sed with invalid segment should not pass", sed.validate (errors));
        assertEquals ("Invalid segment type error list should have a single element", 1, errors.size ());
        assertEquals ("Invalid segment type error type should be INVALID_SEGMENT_TYPE", ValidationErrorEnum.INVALID_SEGMENT_TYPE, errors.get(0).getError ());

        errors.clear ();
        segment.getType().setValue("spectrum");
        
        // test Curation
        Curation curation = (Curation)segment.getCuration ().clone ();
        segment.setCuration (null);

        assertFalse ("Segment with missing curation should not pass", segment.validate ());
        assertFalse ("Sed with missing curation should not pass", sed.validate (errors));
        assertEquals ("Missing curation error list should have a single element", 1, errors.size ());
        assertEquals ("Missing curation error type should be MISSING CURATION PUB", ValidationErrorEnum.MISSING_CURATION_PUB, errors.get(0).getError ());

        errors.clear ();
        segment.setCuration ((Curation)curation.clone ());
        
        segment.getCuration ().setPublisher (null);
        assertFalse ("Curation with missing curation.publisher should not pass", segment.getCuration().validate ());
        assertFalse ("Sed with missing curation.publisher should not pass", sed.validate (errors));
        assertEquals ("Missing curation error list should have a single element", 1, errors.size ());
        assertEquals ("Missing curation error type should be MISSING CURATION PUB", ValidationErrorEnum.MISSING_CURATION_PUB, errors.get(0).getError ());

        segment.setCuration (curation);
        errors.clear ();

        // test invalid target
        Target target = (Target)segment.getTarget ().clone ();
        segment.setTarget (null);

        assertFalse ("Segment with missing target should not pass", segment.validate ());

        assertFalse ("Sed with missing target should not pass", sed.validate (errors));
        assertEquals ("Missing target error list should have a single element", 1, errors.size ());

        assertEquals ("Missing target error type should be MISSING TARGET NAME", ValidationErrorEnum.MISSING_TARGET_NAME, errors.get(0).getError ());

        errors.clear ();
        segment.setTarget ((Target)target.clone ());
        segment.getTarget ().setName (null);
        assertFalse ("Target with missing target.name should not pass", segment.getTarget ().validate ());
        assertFalse ("Sed with missing target.name should not pass", sed.validate (errors));
        assertEquals ("Missing target error list should have a single element", 1, errors.size ());
        assertEquals ("Missing target error type should be MISSING TARGET NAME", ValidationErrorEnum.MISSING_TARGET_NAME, errors.get(0).getError ());
        
        segment.setTarget ((Target)target.clone ());
        errors.clear ();
        
        // test invalid characterization
        Characterization _char = (Characterization)segment.getChar ().clone ();

        expectedErrors = new ArrayList<ValidationErrorEnum> (12);

        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_FLUXAXIS_UCD);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_FLUXAXIS_UNIT);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_UCD);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_UNIT);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_LOCATION_VALUE);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_LOCATION_VALUE);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_LOCATION_VALUE);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_START);
        expectedErrors.add(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP);


        // entire characterization missing
        segment.setChar (null);
        assertFalse ("Segment with missing characterization should not pass", segment.validate ());

        assertFalse ("Sed with missing characterization should not pass", sed.validate (errors));
        assertEquals ("Unexpected number of errors for missing characterization. Expected: "+expectedErrors.size ()+"Got: "+errors.size (), expectedErrors.size (), errors.size ());

        this.checkErrorList (errors, expectedErrors, "", "missing characterization");

        // characterization flux axis missing
        errors.clear ();
        segment.setChar ((Characterization)_char.clone ());
        segment.getChar ().setFluxAxis (null);
        assertFalse ("Characterization with missing flux axis should not pass", segment.getChar ().validate ());
        assertFalse ("Sed with missing flux axis should not pass", sed.validate (errors));
        assertEquals ("Unexpected number of errors for missing characterization flux axis. Expected: 2 Got: "+errors.size (),  2, errors.size ());
        this.checkErrorList (errors, expectedErrors.subList (0,2), "", "missing characterization flux axis");

        // characterization spectral axis missing
        errors.clear ();
        segment.setChar ((Characterization)_char.clone ());
        segment.getChar ().setSpectralAxis (null);
        assertFalse ("Characterization with missing spectral axis should not pass", segment.getChar ().validate ());
        assertFalse ("Sed with missing spectral axis should not pass", sed.validate (errors));
        assertEquals ("Unexpected number of errors for missing characterization spectral axis. Expected: 6 Got: "+errors.size (),  6, errors.size ());
        this.checkErrorList (errors, expectedErrors, "MISSING_CHAR_SPECTRALAXIS","missing characterization spectral axis");

        // characterization time and spatial axis missing
        errors.clear ();
        segment.setChar ((Characterization)_char.clone ());
        segment.getChar ().setTimeAxis (null);
        segment.getChar ().setSpatialAxis (null);
        assertFalse ("Characterization with missing time and spatial axis should not pass", segment.getChar ().validate ());
        assertFalse ("Sed with missing time and spatial axis should not pass", sed.validate (errors));
        assertEquals ("Unexpected number of errors for missing characterization time and spatial axis. Expected: 4 Got: "+errors.size (),  4, errors.size ());
        this.checkErrorList (errors, expectedErrors.subList (4,8), "", "missing characterization time and spatial axis");


        // characterization axis coverage missing
        errors.clear ();
        segment.setChar ((Characterization)_char.clone ());
        segment.getChar ().getTimeAxis().setCoverage (null);
        segment.getChar ().getSpatialAxis().setCoverage (null);
        segment.getChar ().getSpectralAxis().setCoverage (null);
        assertFalse ("Sed with missing characterization *.coverage should not pass", sed.validate (errors));
        assertEquals ("Unexpected number of errors for missing characterization*.coverage. Expected: 8 Got: "+errors.size (),  8, errors.size ());
        this.checkErrorList (errors, expectedErrors.subList (4,12), "", "missing characterization*.coverage");

        // characterization axis coverage components missing
        errors.clear ();
        segment.setChar ((Characterization)_char.clone ());
        segment.getChar ().getTimeAxis().getCoverage().setLocation (null);
        segment.getChar ().getTimeAxis().getCoverage().setBounds (null);
        segment.getChar ().getSpatialAxis().getCoverage().setLocation (null);
        segment.getChar ().getSpatialAxis().getCoverage().setBounds (null);
        segment.getChar ().getSpectralAxis().getCoverage().setLocation (null);
        segment.getChar ().getSpectralAxis().getCoverage().setBounds (null);
        assertFalse ("Sed with missing characterization *.coverage.* should not pass", sed.validate (errors));
        assertEquals ("Unexpected number of errors for missing characterization.*.coverage.*. Expected: 8 Got: "+errors.size (),  8, errors.size ());
        this.checkErrorList (errors, expectedErrors.subList (4,12), "", "missing characterization.*.coverage.*");


        // characterization spectral axis range missing
        errors.clear ();
        segment.setChar ((Characterization)_char.clone ());
        segment.getChar ().getSpectralAxis().getCoverage().getBounds().setRange (null);
        assertFalse ("Sed with missing characterization spectral axis range should not pass", sed.validate (errors));
        assertEquals ("Unexpected number of errors for missing characterization spectral axis range. Expected: 2 Got: "+errors.size (),  2, errors.size ());
        this.checkErrorList (errors, expectedErrors.subList (10,12), "", "missing characterization spectral axis range");


        // characterization axis all individual elements missing
        errors.clear ();
        segment.setChar ((Characterization)_char.clone ());
        segment.getChar ().getFluxAxis().setUcd (null);
        segment.getChar ().getFluxAxis().setUnit (null);
        segment.getChar ().getSpectralAxis().setUcd (null);
        segment.getChar ().getSpectralAxis().setUnit (null);
        segment.getChar ().getTimeAxis().getCoverage().getLocation().setValue (null);
        segment.getChar ().getTimeAxis().getCoverage().getBounds().setExtent (null);
        segment.getChar ().getSpatialAxis().getCoverage().getLocation().setValue (null);
        segment.getChar ().getSpatialAxis().getCoverage().getBounds().setExtent (null);
        segment.getChar ().getSpectralAxis().getCoverage().getLocation().setValue (null);
        segment.getChar ().getSpectralAxis().getCoverage().getBounds().setExtent (null);
        segment.getChar ().getSpectralAxis().getCoverage().getBounds().getRange ().setMin (null);
        segment.getChar ().getSpectralAxis().getCoverage().getBounds().getRange ().setMin (null);

        assertFalse ("Sed with missing characterization individual components should not pass", sed.validate (errors));
        assertEquals ("Unexpected number of errors for missing characterization individual components. Expected: 12 Got: "+errors.size (),  expectedErrors.size (), errors.size ());

        this.checkErrorList (errors, expectedErrors, "", "missing characterization individual components");
        errors.clear ();
        segment.setChar ((Characterization)_char.clone ());
        

        // test invalid data
        ArrayOfPoint data = (ArrayOfPoint)segment.getData ().clone ();

        expectedErrors = new ArrayList<ValidationErrorEnum> (2);

        expectedErrors.add(ValidationErrorEnum.MISSING_DATA_FLUXAXIS_VALUE);
        expectedErrors.add(ValidationErrorEnum.MISSING_DATA_SPECTRALAXIS_VALUE);

        segment.setData (null);

        assertFalse ("Segment with missing data should not pass", segment.validate ());

        assertFalse ("Sed with missing data should not pass", sed.validate (errors));
        assertEquals ("Missing data error list should have a 2 elements", 2, errors.size ());

        this.checkErrorList (errors, expectedErrors, "", "missing data");

        errors.clear ();
        segment.setData ((ArrayOfPoint)data.clone ());

        // data check missing flux
        segment.getData ().getPoint ().get(0).setFluxAxis (null);
        assertFalse ("Data with missing point.fluxaxis should not pass", segment.getData().validate ());
        assertFalse ("Sed with missing point.fluxaxis should not pass", sed.validate (errors));
        assertEquals ("Missing point.fluxaxis  error list should have a single element", 1, errors.size ());
        assertEquals ("Missing point.fluxaxis error type should be MISSING_DATA_FLUXAXIS_VALUE", ValidationErrorEnum.MISSING_DATA_FLUXAXIS_VALUE, errors.get(0).getError ());

        errors.clear ();
        segment.setData ((ArrayOfPoint)data.clone ());

        // data check missing spectral
        segment.getData ().getPoint ().get(1).setSpectralAxis (null);
        assertFalse ("Data with missing point.spectralaxis should not pass", segment.getData().validate ());
        assertFalse ("Sed with missing point.spectralaxis should not pass", sed.validate (errors));
        assertEquals ("Missing point.spectralaxis  error list should have a single element", 1, errors.size ());
        assertEquals ("Missing point.spectralaxis error type should be MISSING_DATA_FLUXAXIS_VALUE", ValidationErrorEnum.MISSING_DATA_SPECTRALAXIS_VALUE, errors.get(0).getError ());

        errors.clear ();
        segment.setData ((ArrayOfPoint)data.clone ());

        // data check missing spectral and flux value
        segment.getData ().getPoint ().get(1).getFluxAxis ().setValue (null);
        segment.getData ().getPoint ().get(2).getSpectralAxis ().setValue (null);
        assertFalse ("Data with missing point.spectralaxis should not pass", segment.getData().getPoint ().get(1).validate ());
        assertFalse ("Sed with missing point.spectralaxis should not pass", sed.validate (errors));
        assertEquals ("Missing point.spectralaxis  error list should have a single element", 2, errors.size ());
        this.checkErrorList (errors, expectedErrors, "", "missing spectral and flux values");

        errors.clear ();
        segment.setData ((ArrayOfPoint)data.clone ());
        

    }
    
    /**
     *  3.2 Test Sed validation of required elements with multiple segments.
     *
     */
    public void testCase3_1_2() throws SedNoDataException, SedInconsistentException
    {
    	
        Sed sed = EmptyObjects.createSED (0);
        String testName = "testCase3_1_2";
        System.out.println("   run "+testName+"()");
        Segment segment = CompleteObjects.createSpectrum ();
        Segment segment2;
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        List<ValidationErrorEnum> expectedErrors = new ArrayList<ValidationErrorEnum> ();
        
        segment.getChar ().getFluxAxis().setUnit ("FOO");
        segment.getChar ().getSpectralAxis ().getCoverage ().getBounds ().setExtent (new DoubleParam(1.0));
        segment.getChar ().getSpectralAxis ().getCoverage ().getBounds ().createRange ().setMin (new DoubleParam (0.0));
        segment.getChar ().getSpectralAxis ().getCoverage ().getBounds ().createRange ().setMin (new DoubleParam (1.0));
        segment.getChar ().getSpatialAxis ().getCoverage ().getBounds ().setExtent (new DoubleParam(1.0));   
        segment2 = (Segment)segment.clone ();

        sed.addSegment(segment);
        sed.addSegment(segment2);
        
        // completely valid segment
        assertTrue ("Sed with a two complete segment should pass.", sed.validate ());
        
        // test one invalid segment
        Curation curation = (Curation)segment.getCuration ().clone ();
        segment.setCuration(null);
        
        assertFalse ("Sed with missing curation should not pass", sed.validate (errors));
        assertEquals ("Missing target error list should have a single element", 1, errors.size ());
        assertEquals ("Missing curation error type should be MISSING CURATION PUB", ValidationErrorEnum.MISSING_CURATION_PUB, errors.get(0).getError ());

        errors.clear ();
        segment.setCuration(curation);
       

        // test 2nd invalid segment
        Target target = (Target)segment2.getTarget().clone ();
        segment2.setTarget(null);
        assertFalse ("Sed with missing target.name should not pass", sed.validate (errors));
        assertEquals ("Missing target error list should have a single element", 1, errors.size ());
        assertEquals ("Missing target error type should be MISSING TARGET NAME", ValidationErrorEnum.MISSING_TARGET_NAME, errors.get(0).getError ());
        
        
        errors.clear ();
        segment2.setTarget(target);

        // test both invalid segments
        expectedErrors.clear ();
        expectedErrors.add (ValidationErrorEnum.MISSING_CURATION_PUB);
        expectedErrors.add (ValidationErrorEnum.MISSING_CURATION_PUB);
        expectedErrors.add (ValidationErrorEnum.MISSING_DATA_FLUXAXIS_VALUE);
        expectedErrors.add (ValidationErrorEnum.MISSING_DATA_SPECTRALAXIS_VALUE);
        
        segment.setCuration(null);
        segment2.getCuration ().setPublisher(null);
        segment2.setData(null);
        
        assertFalse ("Sed with invalid multiple segments should not pass", sed.validate (errors));
        assertEquals ("Invalid multiple segments has unexpected number of errors.", 4, errors.size ());     
        this.checkErrorList (errors, expectedErrors, "", "multiple segments invalid");

        

    }
   
    /**
     *  3.2 Test Sed validation of required elements during file io. This includes:
     *   <ul>
     *   <li> required elements </li>
     *   <li> resource utype == Sed</li>
     *   <li> table utype == Spectrum </li>
     *   </ul>
     * 
     */
    public void testCase3_2() throws SedException, IOException
    {

        Sed sed;
        String testName = "testCase3_2";
        System.out.println("   run "+testName+"()");
        int rc = 0;

        String inputFilename = SedLibTestUtils.mkInFileName ("Spectrum.vot");
        String logFilename = "validate1.log";
        

        FileHandler handler = new FileHandler(SedLibTestUtils.mkOutFileName (logFilename));
        handler.setFormatter(new TestCase3Formatter ());
        sedLogger.addHandler(handler);
        sedLogger.setUseParentHandlers (false);

        /* Read vot file */
        sed = Sed.read (inputFilename, SedFormat.VOT);
        handler.close ();
        rc = SedLibTestUtils.DIFFIT( logFilename );

        assertEquals( TestCase3.class.getName () + ": Diff failed - " + logFilename, 0, rc );
        
        
        /* Read fits file */
        logFilename = "validate2.log";
        inputFilename = SedLibTestUtils.mkInFileName ("Spectrum.fits");
        
        sedLogger.removeHandler(handler);
        
        handler = new FileHandler(SedLibTestUtils.mkOutFileName (logFilename));
        handler.setFormatter(new TestCase3Formatter ());
        sedLogger.addHandler(handler);
        sed = Sed.read (inputFilename, SedFormat.FITS);
        
        handler.close ();
        
        rc = SedLibTestUtils.DIFFIT( logFilename );

        assertEquals( TestCase3.class.getName () + ": Diff failed - " + logFilename, 0, rc );
        
        

        /* Valid vot utypes */
        logFilename = "validate3.log";
        inputFilename = SedLibTestUtils.mkInFileName ("ValidateFile.vot");
        
        sedLogger.removeHandler(handler);
        
        handler = new FileHandler(SedLibTestUtils.mkOutFileName (logFilename));
        handler.setFormatter(new TestCase3Formatter ());
        sedLogger.addHandler(handler);
        sed = Sed.read (inputFilename, SedFormat.VOT);
        
        handler.close ();
        
        rc = SedLibTestUtils.DIFFIT( logFilename );

        assertEquals( TestCase3.class.getName () + ": Diff failed - " + logFilename, 0, rc );
        


    }

    public void checkErrorList (List<ValidationError> errors, List<ValidationErrorEnum> expectedErrors, String basename, String test)
    {
        List<ValidationErrorEnum> foundErrors = new ArrayList<ValidationErrorEnum> ();
        for (ValidationError ee : errors)
            foundErrors.add (ee.getError());
        Collections.sort (foundErrors);
        for (int ii=0,jj=0; ii<expectedErrors.size (); ii++)
        {
            if ((basename.length () > 0) && (!expectedErrors.get(ii).name ().startsWith (basename)))
                continue;
            assertEquals ("Mismatching errors found for enumerations when testing "+test+".", expectedErrors.get(ii), foundErrors.get(jj++));
        }
    }

}
