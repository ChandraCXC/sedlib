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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestSuite;

import cfa.vo.sedlib.io.SedFormat;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private void testMethod(String filename, SedFormat format, Map oracle) {
        List oracleList = new ArrayList();
        oracleList.add(oracle);
        testMethod(filename, format, oracleList);
    }

    private void testMethod(String filename, SedFormat format, List<Map> oracleList)
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
        }

        checkSeds(inputSed, outputSed);

        for(int i=0; i<outputSed.getNumberOfSegments(); i++)
        {
            Segment s = outputSed.getSegment(i);
            checkSegment(s, oracleList.get(i));
        }

	if ( !keep )
	    SedLibTestUtils.cleanupFiles( inputFilename );
    }

    /**
     * TestCase 4.2.2.1:
     * Read a votable spectrum (one segment) and check it has been correctly read.
     */
    public void testCase4_2_2_1()
    {

	SedFormat format = SedFormat.VOT;
	String inputName = "BasicSpectrum." + format.exten();

        HashMap oracle = new HashMap();

        oracle.put("targetName", "NGC_4321");
        oracle.put("fluxUnits", "W/m^2/Hz");
        oracle.put("spectralUnits", "Hz");
        oracle.put("publisherName", "Database");

        testMethod(inputName, format, oracle);

    }


    /**
     * TestCase 4.2.2.2
     * Read a votable photometry point (one segment) and check it has been correctly read.
     */
    public void testCase4_2_2_2()
    {

	SedFormat format = SedFormat.VOT;
	String inputName = "BasicPhotom." + format.exten();

        HashMap oracle = new HashMap();

        oracle.put("targetName", "3C 279");
        oracle.put("fluxUnits", "Jy");
        oracle.put("spectralUnits", "Hz");
        oracle.put("publisherName", "Database");

        testMethod(inputName, format, oracle);

    }

    /**
     * TestCase 4.2.2.3
     * Read a votable theoretical spectrum and check it has benn correctly read.
     * This test case refers to a year 2 requirement
     */
    public void testCase4_2_2_3()
    {
        //This test case refers to a year 2 requirement
    }

    /**
     * TestCase 4.2.2.4
     * Read a votable aggregated sed (2 segments, 1 spectrum + 1 photom) and check that it has been correctly read.
     */
    public void testCase4_2_2_4()
    {

	SedFormat format = SedFormat.VOT;
	String inputName = "BasicSpectrum+Photom." + format.exten();

        List oracleList = new ArrayList();

        HashMap oracle = new HashMap();

        oracle.put("targetName", "NGC_4321");
        oracle.put("fluxUnits", "W/m^2/Hz");
        oracle.put("spectralUnits", "Hz");
        oracle.put("publisherName", "Database");

        oracleList.add(oracle);

        oracle = new HashMap();

        oracle.put("targetName", "3C 279");
        oracle.put("fluxUnits", "Jy");
        oracle.put("spectralUnits", "Hz");
        oracle.put("publisherName", "Database");

        oracleList.add(oracle);

        testMethod(inputName, format, oracleList);

    }

    /**
     * TestCase 4.2.3
     * Read file from an external URL
     * This test case refers to a year 2 requirement
     */
    public void testCase4_2_3()
    {
        //This test case refers to a year 2 requirement
    }

    /**
     * TestCase 4.2.4
     * Read files from Input Stream.
     * This test case is just a stub, since this implementation always reads data from an InputStream.
     */
    public void testCase4_2_4()
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
            fail("the wrong exception (SedInconsistentException) has been thrown while reading the file "+inputFilename+" with format FITS");
        } catch (IOException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("the wrong exception (IOException) has been thrown while reading the file "+inputFilename+" with format FITS");
        }

        fail("strange, no exception was thrown while reading the file "+inputFilename+" with format FITS");

    }

    /**
     * TestCase 4.2.5.3
     * Error handling: a file with a misleading extension can be read if the client requests it to be read with the right format
     * (e.g. a votable with .fits extension)
     */
    public void testCase4_2_5_3()
    {
        SedFormat format = SedFormat.VOT;
	String inputName = "BasicSpectrum+Photom.fits";

        String inputFilename = SedLibTestUtils.mkInFileName( inputName );
        try {
            Sed sed = Sed.read(inputFilename, format);
        } catch (Exception ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("reading a file with a 'wrong' extension but the correct format should be allowed");
        }

        List oracleList = new ArrayList();

        HashMap oracle = new HashMap();

        oracle.put("targetName", "NGC_4321");
        oracle.put("fluxUnits", "W/m^2/Hz");
        oracle.put("spectralUnits", "Hz");
        oracle.put("publisherName", "Database");

        oracleList.add(oracle);

        oracle = new HashMap();

        oracle.put("targetName", "3C 279");
        oracle.put("fluxUnits", "Jy");
        oracle.put("spectralUnits", "Hz");
        oracle.put("publisherName", "Database");

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

        for(SedFormat format : SedFormat.values()) {

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

        File f = new File(inputFilename);
        f.setReadable(false);//FIXME doesn't work with JDK1.5
//        File f = new File (inputName);

        for(SedFormat format : SedFormat.values()) {

            try {
                FileInputStream fis = new FileInputStream(f);
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

        for(SedFormat format : SedFormat.values()) {

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
            }

            fail("strange, no exception was thrown while reading the file "+inputFilename+" with format "+format.name());

        }

    }


    protected static void checkSegment(Segment segment, Map<String, String> oracle)
    {
        assertEquals( "wrong publisher name", oracle.get("publisherName"), segment.getCuration().getPublisher().getValue());

        /* metadata check */
        /* target name */
        String outputTargetName = segment.getTarget().getName().getValue();
        assertEquals( "wrong target name", oracle.get("targetName"), outputTargetName);

        /* flux axis unit */
        String outputFluxAxisUnit;
        try {
            outputFluxAxisUnit = segment.getFluxAxisUnits();
            assertEquals( "wrong flux axis units", oracle.get("fluxUnits"), outputFluxAxisUnit);
        } catch (SedNoDataException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        /* spectral axis unit */
        String outputSpectralAxisUnit;
        try {
            outputSpectralAxisUnit = segment.getSpectralAxisUnits();
            assertEquals( "wrong spectral axis units", oracle.get("spectralUnits"), outputSpectralAxisUnit);
        } catch (SedNoDataException ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
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

}
