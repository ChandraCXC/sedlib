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
* File: TestCase4_2.java
*
* Author:  olaurino                                Created: 2011-03-14
*
* Virtual Astrophysical Observatory; contributed by Center for Astrophysics
*
* Update History:
*   2011-03-14:  OL  Create
* 
***********************************************************************/


package cfa.vo.sedlib;

import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedParsingException;
import cfa.vo.sedlib.common.SedWritingException;
import cfa.vo.testtools.SedLibTestUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestSuite;

import cfa.vo.sedlib.io.SedFormat;
import cfa.vo.testtools.Oracle;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests Sedlib ability to read Sed Data (Requirement 4.2)
 * Since the library always reads the data from an input stream, all the unit tests in this test case
 * cover both the string path name (req4.2.1) and the file stream (req4.2.3).
 * Req.4.2.2 (read data from URL) is y2 priority.
 *
 * All tests involving y2 priorities are included as stubs, and they will always pass.
 * Such tests are clearly indicated in the method documentation.
*/	

public class TestCase4_2 extends SedTestBase
{
    boolean keep = true;

    public TestCase4_2( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestCase4_2.class );

	return suite;
    }

    private void testMethod(String filename, SedFormat format, Oracle oracle) {
        List oracleList = new ArrayList();
        oracleList.add(oracle);
        testMethod(filename, format, oracleList);
    }

    private void testMethod(String filename, SedFormat format, List<Oracle> oracleList)
    {

        String inputFilename = SedLibTestUtils.mkInFileName( filename );
        String outputFilename = SedLibTestUtils.mkOutFileName( filename );

	/* Read input file */
        Sed inputSed=null;
        try {
            inputSed = Sed.read(inputFilename, format);
        } catch (SedParsingException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("error while parsing the document: " +inputFilename);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("inconsistency detected in document: "+inputFilename);
        } catch (IOException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("IO problems reading document: "+inputFilename);
        }  catch (SedNoDataException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("No data found in segment: "+inputFilename);
        }


        /* Write file to disk */
        try {
            inputSed.write(outputFilename, format);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("inconsistencies found in sed object. Can't serialize it to: " + outputFilename);
        } catch (SedWritingException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("low level error while serializing sed object to " + outputFilename);
        } catch (IOException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("I/O error while serializing sed object to " + outputFilename);
        }


        /* Read the new file */
        Sed outputSed=null;
        try {
            outputSed = Sed.read(outputFilename, format);
        } catch (SedParsingException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("error parsing file: " + outputFilename);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("inconsistencies found in file: " + outputFilename);
        } catch (IOException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("I/O error accessing" + outputFilename);
        }  catch (SedNoDataException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("No data found in segment: "+inputFilename);
        }

        checkSeds(inputSed, outputSed);

        for(int i=0; i<outputSed.getNumberOfSegments(); i++)
        {
            Segment s = outputSed.getSegment(i);
            Oracle oracle = oracleList.get(i);
            try {
                oracle.test(s);
            } catch (Exception ex) {
                fail(ex.getMessage());
            }
        }

	if ( !keep )
	    SedLibTestUtils.cleanupFiles( inputFilename );
    }

    /**
     * TestCase 4.2.1:
     * Read a votable/fits spectrum (one segment) and check it has been correctly read.
     */
    public void testCase4_2_1()
    {

        
        

        for(SedFormat format : formats)
        {
            String inputName = "BasicSpectrum." + format.exten();

            Oracle oracle = new Oracle();

            oracle.put("target.name.value", "NGC_4321");
            oracle.put("char.fluxAxis.unit", "W/m^2/Hz");
            oracle.put("char.spectralAxis.unit", "Hz");
            oracle.put("curation.publisher.value", "Database");

            testMethod(inputName, format, oracle);
        }

    }


    /**
     * TestCase 4.2.2
     * Read a votable/fits photometry point (one segment) and check it has been correctly read.
     */
    public void testCase4_2_2() throws SedParsingException, SedInconsistentException, IOException, SedWritingException, InterruptedException
    {

	for(SedFormat format : formats)
        {

            String inputName = "BasicPhotom." + format.exten();

            Oracle oracle = new Oracle();

            oracle.put("target.name.value", "3C 279");
            oracle.put("fluxAxisUnits", "Jy");
            oracle.put("spectralAxisUnits", "Hz");
            oracle.put("curation.publisher.value", "Database");

            testMethod(inputName, format, oracle);
        }

    }

    /**
     * TestCase 4.2.3
     * Read a votable/fits theoretical spectrum and check it has been correctly read.
     * This test case refers to a year 2 requirement
     */
    public void testCase4_2_3()
    {
        //This test case refers to a year 2 requirement
    }

    /**
     * TestCase 4.2.4
     * Read a votable/fits aggregated sed (2 segments, 1 spectrum + 1 photom) and check that it has been correctly read.
     */
    public void testCase4_2_4()
    {

	for(SedFormat format : formats)
        {
            String inputName = "BasicSpectrum+Photom." + format.exten();

            List oracleList = new ArrayList();

            Oracle oracle = new Oracle();

            oracle.put("target.name.value", "NGC_4321");
            oracle.put("fluxAxisUnits", "W/m^2/Hz");
            oracle.put("spectralAxisUnits", "Hz");
            oracle.put("curation.publisher.value", "Database");

            oracleList.add(oracle);

            oracle = new Oracle();

            oracle.put("target.name.value", "3C 279");
            oracle.put("fluxAxisUnits", "Jy");
            oracle.put("spectralAxisUnits", "Hz");
            oracle.put("curation.publisher.value", "Database");

            oracleList.add(oracle);

            testMethod(inputName, format, oracleList);
        }

    }

    /**
     * TestCase 4.2.3
     * Read file from an external URL
     * This test case refers to a year 2 requirement
     */
    public void testCase4_2_6()
    {
        //This test case refers to a year 2 requirement
    }

    /**
     * TestCase 4.2.4
     * Read files from Input Stream.
     * This test case is just a stub, since this implementation always reads data from an InputStream.
     */
    public void testCase4_2_7()
    {
        //This test case is always passed, since this implementation always reads data from an InputStream 
    }


    /**
     * TestCase 4.2.5.1
     * Error handling: requesting to read an XML file results in an UnsupportedOperationException
     */
    public void testCase4_2_5_1()
    {
        SedFormat format = SedFormat.XML;
	String inputName = "BasicSpectrum+Photom.vot";

        String inputFilename = SedLibTestUtils.mkInFileName( inputName );
        try {
            Sed sed = Sed.read(inputFilename, format);
        } catch (UnsupportedOperationException ex) {
            // Ok, right exception
            return;
        } catch (Exception ex) {
            fail("the wrong exception has been thrown while reading the file "+inputFilename+" with format XML");
        }

        fail("strange, no exception was thrown while reading the file "+inputFilename+" with format XML");

    }

    /**
     * TestCase 4.2.5.2
     * Error handling: trying to read a file with the wrong format results in a SedParsingException
     */
    public void testCase4_2_5_2()
    {
        SedFormat format = SedFormat.FITS;
	String inputName = "BasicSpectrum+Photom.vot";

        String inputFilename = SedLibTestUtils.mkInFileName( inputName );
        try {
            Sed sed = Sed.read(inputFilename, format);
        } catch (SedParsingException ex) {
            // Ok, right exception
            return;
        } catch (SedInconsistentException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("the wrong exception (SedInconsistentException) has been thrown while reading the file "+inputFilename+" with format "+format);
        } catch (IOException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("the wrong exception (IOException) has been thrown while reading the file "+inputFilename+" with format "+format);
        } catch (SedNoDataException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("No data found in segment: "+inputFilename);
        }

        fail("strange, no exception was thrown while reading the file "+inputFilename+" with format "+format);

        format = SedFormat.VOT;
	inputName = "BasicSpectrum+Photom.fits";

        inputFilename = SedLibTestUtils.mkInFileName( inputName );
        try {
            Sed sed = Sed.read(inputFilename, format);
        } catch (SedParsingException ex) {
            // Ok, right exception
            return;
        } catch (SedInconsistentException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("the wrong exception (SedInconsistentException) has been thrown while reading the file "+inputFilename+" with format "+format);
        } catch (IOException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("the wrong exception (IOException) has been thrown while reading the file "+inputFilename+" with format "+format);
        } catch (SedNoDataException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("No data found in segment: "+inputFilename);
        }

        fail("strange, no exception was thrown while reading the file "+inputFilename+" with format "+format);

    }

    /**
     * TestCase 4.2.5.3
     * Error handling: a file with a misleading extension can be read if the client requests it to be read with the right format
     * (e.g. a votable with .fits extension)
     */
    public void testCase4_2_5_3()
    {
        SedFormat format = SedFormat.VOT;
	String inputName = "BasicSpectrum+Photom.fake.fits";

        String inputFilename = SedLibTestUtils.mkInFileName( inputName );
        try {
            Sed sed = Sed.read(inputFilename, format);
        } catch (Exception ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("reading a file with a 'wrong' extension but the correct format should be allowed");
        }

        List oracleList = new ArrayList();

        Oracle oracle = new Oracle();

        oracle.put("target.name.value", "NGC_4321");
        oracle.put("fluxAxisUnits", "W/m^2/Hz");
        oracle.put("spectralAxisUnits", "Hz");
        oracle.put("curation.publisher.value", "Database");

        oracleList.add(oracle);

        oracle = new Oracle();

        oracle.put("target.name.value", "3C 279");
        oracle.put("fluxAxisUnits", "Jy");
        oracle.put("spectralAxisUnits", "Hz");
        oracle.put("curation.publisher.value", "Database");

        oracleList.add(oracle);

        format = SedFormat.FITS;
	inputName = "BasicSpectrum+Photom.fake.vot";

        inputFilename = SedLibTestUtils.mkInFileName( inputName );
        try {
            Sed sed = Sed.read(inputFilename, format);
        } catch (Exception ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("reading a file with a 'wrong' extension but the correct format should be allowed");
        }

        oracleList = new ArrayList();

        oracle = new Oracle();

        oracle.put("target.name.value", "NGC_4321");
        oracle.put("fluxUnits", "W/m^2/Hz");
        oracle.put("spectralAxisUnits", "Hz");
        oracle.put("curation.publisher.value", "Database");

        oracleList.add(oracle);

        oracle = new Oracle();

        oracle.put("target.name.value", "3C 279");
        oracle.put("fluxUnits", "Jy");
        oracle.put("spectralAxisUnits", "Hz");
        oracle.put("curation.publisher.value", "Database");

        oracleList.add(oracle);

    }
    
    /**
     * Since the validation is a y2 requirement, at the moment the library can parse a votable or fits file which doesn't represent an SED without complaining.
     * That's why for year one this test case is just a stub.
     *
     * See TestPlan, TestCase 4.2.5.4
     */
    public void testCase4_2_5_4()
    {
//        for(SedFormat format : SedFormat.values())
//        {
//            String inputName = "CSC."+format.exten();
//
//            String inputFilename = SedLibTestUtils.mkInFileName( inputName );
//            try {
//                Sed sed = Sed.read(inputFilename, format);
//            } catch (SedParsingException ex) {
//                // Ok, right exception
//                return;
//            } catch (SedInconsistentException ex) {
//                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
//                fail("the wrong exception (SedInconsistentException) has been thrown while reading the file "+inputFilename+" with format "+format.name());
//            } catch (IOException ex) {
//                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
//                fail("the wrong exception (IOException) has been thrown while reading the file "+inputFilename+" with format "+format.name());
//            }
//
//            fail("strange, no exception was thrown while reading the file "+inputFilename+" with format "+format.name());
//        }

        

    }


    /**
     * TestCase 4.2.5.5
     * Error handling: reading an empty file results in a SedParsingException.
     */
    public void testCase4_2_5_5()
    {

	String inputName = "empty_file.foo";
        String inputFilename = SedLibTestUtils.mkInFileName( inputName );

        for(SedFormat format : formats) {

            try {
                Sed sed = Sed.read(inputFilename, format);
            } catch (SedParsingException ex) {
                // Ok, right exception
                continue;
            } catch (SedInconsistentException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (SedInconsistentException) has been thrown while reading the file "+inputFilename+" with format "+format.name());
            } catch (IOException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (IOException) has been thrown while reading the file "+inputFilename+" with format "+format.name());
            } catch (UnsupportedOperationException ex) {
                //Ok, we hit an unsupported format, continuing...
                continue;
            } catch (SedNoDataException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("No data found in segment: "+inputFilename);
            }

            fail("strange, no exception was thrown while reading the file "+inputFilename+" with format "+format.name());

        }
        
    }

    /**
     * TestCase 4.2.5.6
     * Error handling: reading a file which exists but is not accessible (e.g. no read permission) should result in a IOException.
     */
    public void testCase4_2_5_6()
    {

	String inputName = "Unaccessible.vot";
        String inputFilename = SedLibTestUtils.mkInFileName( inputName );

        for(SedFormat format : formats) {

            try {

                InputStream fis = new FakeInputStream(new File(inputFilename));

                Sed sed = Sed.read(fis, format);
            } catch (SedParsingException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (SedParsingException) has been thrown while reading the file "+inputFilename+" with format "+format.name());
            } catch (SedInconsistentException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (SedInconsistentException) has been thrown while reading the file "+inputFilename+" with format "+format.name());
            } catch (IOException ex) {
                // Ok, right exception
                continue;
            } catch (SedNoDataException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("No data found in segment: "+inputFilename);
            }

            fail("strange, no exception was thrown while reading the file "+inputFilename+" with format "+format.name());

        }

    }

    /**
     * TestCase 4.2.5.7
     * Error handling: reading a file which doesn't exist should result in a IOException.
     */
    public void testCase4_2_5_7()
    {

	String inputName = "DoesntExist";
        String inputFilename = SedLibTestUtils.mkInFileName( inputName );

        for(SedFormat format : formats) {

            try {
                Sed sed = Sed.read(inputFilename, format);
            } catch (SedParsingException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (SedParsingException) has been thrown while reading the file "+inputFilename+" with format "+format.name());
            } catch (SedInconsistentException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (SedInconsistentException) has been thrown while reading the file "+inputFilename+" with format "+format.name());
            } catch (IOException ex) {
                // Ok, right exception
                continue;
            } catch (SedNoDataException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("No data found in segment: "+inputFilename);
            }

            fail("strange, no exception was thrown while reading the file "+inputFilename+" with format "+format.name());

        }

    }


    protected static void checkSegments(Segment inputSegment, Segment outputSegment)
    {
        assertTrue("input and output segments are different", outputSegment.equals(inputSegment));
    }

    protected static void checkSeds(Sed inputSed, Sed outputSed)
    {
        /* be sure the seds are considered equal by the library (data check) */
        assertTrue("input and output seds are different", outputSed.equals(inputSed));
    }

    private class FakeInputStream extends FileInputStream{
        public FakeInputStream(File f) throws FileNotFoundException {
            super(f);
        }

        @Override
        public int read(byte[] b) throws IOException {
            throw new IOException();
        }
        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            throw new IOException();
        }
        @Override
        public int read() throws IOException {
            throw new IOException();
        }
    }

}
