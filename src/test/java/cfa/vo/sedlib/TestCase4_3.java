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
import java.io.File;
import java.io.FileOutputStream;

/**
 * Tests Sedlib ability to write Sed Data (Requirement 4.5)
 *
 * This Test Case just includes tests for error handling. The Test*IO test cases cover the other writing tests.
 *
 * All tests involving y2 priorities are included as stubs, and they will always pass.
 * Such tests are clearly indicated in the method documentation.
*/	

public class TestCase4_3 extends SedTestBase
{
    boolean keep = true;

    public TestCase4_3( String name )
    {
	super(name);
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestCase4_3.class );

	return suite;
    }

    /**
     * For each supported format, verify that the correct Exception (SedWritingException) when a generic I/O problem arise.
     */
    public void testCase4_3_1() {
        String inputName = "BasicSpectrum.";

        for(SedFormat format : formats) {

            String inputFilename = SedLibTestUtils.mkInFileName( inputName+format.exten() );

            String outputName = SedLibTestUtils.mkOutFileName( inputName+format.exten() );

            try {
                Sed sed = Sed.read(inputFilename, format);

                FileOutputStream fos = new FakeOutputStream(new File(outputName));

                sed.write(fos, format);

            } catch (SedWritingException ex) {
                // Ok, right exception
                continue;
            } catch (SedParsingException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (SedParsingException) has been thrown while writing the file "+outputName+" with format "+format.name());
            } catch (SedInconsistentException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (SedInconsistentException) has been thrown while writing the file "+outputName+" with format "+format.name());
            } catch (IOException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("the wrong exception (SedIOException) has been thrown while writing the file "+outputName+" with format "+format.name());
            } catch (SedNoDataException ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("No data found in segment: "+inputFilename);
            }

            fail("strange, no exception was thrown while writing the file "+outputName+" with format "+format.name());

        }
    }

    private class FakeOutputStream extends FileOutputStream{
        public FakeOutputStream(File f) throws FileNotFoundException {
            super(f);
        }

        @Override
        public void write(byte[] b) throws IOException {
            throw new IOException();
        }
        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            throw new IOException();
        }
        @Override
        public void write(int i) throws IOException {
            throw new IOException();
        }
    }

}
