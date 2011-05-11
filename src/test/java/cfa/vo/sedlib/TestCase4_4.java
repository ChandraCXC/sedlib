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
import cfa.vo.testtools.SedLibTestUtils;
import cfa.vo.testtools.Oracle;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;

import cfa.vo.sedlib.io.SedFormat;


/**
 * Tests Sedlib ability to manipulate Sed Data (Requirement 6)
 *
*/	

public class TestCase4_4 extends SedTestBase
{
    boolean keep = true;

    public TestCase4_4( String name )
    {
	super(name);
       
    }

    public static Test suite()
    {
	TestSuite suite = new TestSuite( TestCase4_4.class );

	return suite;
    }

    
    /**
     * Requirement 6.1.1 is covered by Read Test Cases (Test*IO and TestCase4_2), so this test is just a stub
     */
    public void testCase4_4_1() {

    }

    /**
     * Requirement 6.1.2: verify segments have the same observable quantity
     * Requirement 6.1.3: segments may have different x axis quantities
     * Requirement 6.1.4: segments may have different units in either axis
     *
     * This test case present the library with a file which contains two segments. The segments have the same y-axis quantity but different units
     * on both the x and y axes, and different quantities on the x axis. The test passes if both segments are added to the Sed and no exception is thrown.
     *
     */
    public void testCase4_4_2_1() {
            String inputName = "MultipleSpectraDifferentUnits.";

            for(SedFormat format : formats) {

                String inputFilename = SedLibTestUtils.mkInFileName( inputName+format.exten() );

                Sed sed = null;

                try {
                    sed = Sed.read(inputFilename, format);

                    //verify the sed has two segments;
                    int n = sed.getNumberOfSegments();
                    assertEquals("less than 2 segments have been found", n, 2);

                } catch(SedInconsistentException ex) {
                    fail("inconsistent exception thrown. But it shouldn't have.");
                } catch (Exception ex) {
                    Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                    fail("An exception was thrown while reading "+inputName+" with format "+format.name());
                }



            }
    }


    /**
     * Requirement 6.1.2: verify segments have the same observable quantity
     *
     * This test case presents the library with a file which contains two segments. The segments have different y-axis quantity.
     * The test passes if a SedInconsistentException is thrown.
     *
     */
    public void testCase4_4_2_2() {
        String inputName = "MultipleSpectraDifferentQuantities.";

        for(SedFormat format : formats) {

            String inputFilename = SedLibTestUtils.mkInFileName( inputName+format.exten() );

            Sed sed = null;

            try {
                sed = Sed.read(inputFilename, format);

            } catch(SedInconsistentException ex) {
                continue; //OK, correct Exception
            } catch (Exception ex) {
                Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
                fail("An exception was thrown while reading "+inputName+" with format "+format.name());
            }

        }

    }

    /**
     * Requirement 6.1.2: verify segments have the same observable quantity
     *
     * Additional low level test cases for requirement 6.1.2. They just excercise the
     * Segment.isCompatibleWith(Segment other) method.
     *
     * To probe the simmetry of this method all asserts are performed using both segments' methods.
     *
     * If two segments have the same flux ucds and they are not incompatible because of a linear-logarithmic mismatch
     * then they are compatible.
     *
     * If two segments have the same flux ucds but one is linear and the other is logarithmic
     * then they are not compatible.
     *
     * If two segments have ucds that differ only for a ";em.*" then they are compatible
     *
     * If two segments have ucds representing an acceptable pair of magnitudes and flux/luminosity
     * then they are compatible
     *
     * If two segments have ucds representing a non acceptable pair of magnitudes and flux/luminosity
     * then they are not compatible
     *
     * If two segments have ucds following the patterns: phot.fluence;em.* and phot.flux.density;em.*
     * then they are compatible.
     *
     */
    public void testCase4_4_2_3() throws SedNoDataException, SedInconsistentException {

        String inputName = "MultipleSpectraDifferentUnits.";

        SedFormat format = SedFormat.VOT;

        String inputFilename = SedLibTestUtils.mkInFileName( inputName+format.exten() );

        Sed sed = null;

        try {
            sed = Sed.read(inputFilename, format);

            //verify the sed has two segments;
            int n = sed.getNumberOfSegments();
            assertEquals("less than 2 segments have been found", n, 2);

        } catch(SedInconsistentException ex) {
            fail("inconsistent exception thrown. But it shouldn't have.");
        } catch (Exception ex) {
            Logger.getLogger(TestCase4_2.class.getName()).log(Level.SEVERE, null, ex);
            fail("An exception was thrown while reading "+inputName+" with format "+format.name());
        }


        Segment s1 = sed.getSegment(0);
        Segment s2 = sed.getSegment(1);

        s1.createChar().createFluxAxis().setUcd("phot.flux.density;em.wl");
        s1.getChar().getFluxAxis().setUnit("Jy");
        s1.setFluxAxisUnits("Jy");

        s2.createChar().createFluxAxis().setUcd("phot.flux.density;em.wl");
        s2.getChar().getFluxAxis().setUnit("Jy");
        s2.setFluxAxisUnits("Jy");

        compatibleTrue(s1, s2);

        //---------------

        s2.setFluxAxisUnits("Jy Hz");

        compatibleFalse(s1, s2);

        //---------------

        s2.getChar().getFluxAxis().setUcd("phot.flux.density;em.freq");
        compatibleTrue(s1, s2);

        //---------------

        s1.getChar().getFluxAxis().setUcd("phys.magAbs");
        compatibleFalse(s1, s2);

        //---------------

        s1.getChar().getFluxAxis().setUcd("phot.mag");
        compatibleTrue(s1, s2);

        //---------------

        s1.getChar().getFluxAxis().setUcd("phot.fluence;em.wl");
        s2.getChar().getFluxAxis().setUcd("phot.flux.density;em.freq");
        compatibleTrue(s1, s2);
        
    }

    /**
     * Requirement 6.4: verify filtering on spectral axis values works on 
     * single segments.
     *
     */
    public void testCase4_4_8_1() throws SedNoDataException, SedInconsistentException 
    {
        Sed sed = EmptyObjects.createSED (0);
        Sed sed2;
        Segment segment = CompleteObjects.createSpectrum();
        ArrayList<Oracle> oracleList = new ArrayList<Oracle>(1);
        List<Point> pointList = segment.getData ().getPoint ();
        List<Point> pointListCopy = new ArrayList <Point> (pointList);
        List<RangeParam> rangeParamList = new ArrayList<RangeParam> ();

        Oracle oracle;

        sed.setNamespace ("spec");

        // Try to filter on an empty sed
        sed2 = sed.filterSed (0, 100, "test");

        if (!sed.getNamespace ().equals (sed2.getNamespace ()))
            fail ("The new sed did not have same namespace as the original. Original: "+sed.getNamespace ()+" "+"New: "+sed2.getNamespace ());

        if (sed2.getNumberOfSegments () != 0)
            fail ("The new sed should have no segments; it has "+sed2.getNumberOfSegments ());


        sed.addSegment(segment);
        
        //filter a single range -- all values
        oracle = OracleBuilder.buildFromSegment (segment);
        oracleList.add (oracle);

        sed2 = sed.filterSed (0, 10, "TBD");
        this.testSed (sed2, oracleList);

        //filter a single range -- no values
        oracleList.clear ();
        sed2 = sed.filterSed (0, 0, "TBD");
        this.testSed (sed2, oracleList);

        //filter two elements
        pointList.remove (0);
        segment.getData ().setPoint (pointList);
        oracle = OracleBuilder.buildFromSegment (segment);
        oracleList.clear ();
        oracleList.add (oracle);
       
        segment.getData ().setPoint (pointListCopy);
        sed2 = sed.filterSed (1,3, "TBD");
        this.testSed (sed2, oracleList);

        //filter the first and last element
        pointList = new ArrayList<Point> (pointListCopy);
        pointList.remove (1);
        segment.getData ().setPoint (pointList);
        oracle = OracleBuilder.buildFromSegment (segment);
        oracleList.clear ();
        oracleList.add (oracle);

        segment.getData ().setPoint (pointListCopy);
        rangeParamList.clear ();
        rangeParamList.add (new RangeParam (2.,3.,"TBD"));
        rangeParamList.add (new RangeParam (0.,1.,"TBD"));
        sed2 = sed.filterSed (rangeParamList);
        this.testSed (sed2, oracleList);
        
        // error check verify that both min need to be set        
        rangeParamList.clear ();
        rangeParamList.add (new RangeParam ("5.0"));
        try
        {
            sed2 = sed.filterSed (rangeParamList);
            fail ("The range parameter should be invalid");
        }
        catch (SedInconsistentException e)
        {
        	; // pass the error
        }
        
    }



    /**
     * Requirement 6.4: verify filtering on spectral axis values works on
     * single segments.
     *
     */
    public void testCase4_4_8_2() throws SedNoDataException, SedInconsistentException
    {
        Sed sed = EmptyObjects.createSED (0);
        Sed sed2;
        Segment segment1 = CompleteObjects.createSpectrum();
        Segment segment2 = CompleteObjects.createSpectrum ();
        ArrayList<Oracle> oracleList = new ArrayList<Oracle>(1);
        List<Point> pointList;
        List<Point> pointListCopy1 = segment1.getData ().getPoint ();
        List<Point> pointListCopy2 = segment2.getData ().getPoint ();

        List<RangeParam> rangeParamList = new ArrayList<RangeParam> ();

        Oracle oracle;

        sed.setNamespace ("spec");

        sed.addSegment(segment1);
        sed.addSegment(segment2);

        // change the data in the second segment
        for (int ii=0; ii<pointListCopy2.size (); ii++)
        {
            DoubleParam param = pointListCopy2.get (ii).getSpectralAxis ().getValue ();
            param.setValue ((Double)param.getCastValue ()+1.5);
            param.setUnit ("xyz");

            param = pointListCopy1.get (ii).getSpectralAxis ().getValue ();
            param.setUnit ("xyz");

        }
        // add a new point with different units
        Point newPoint = (Point)pointListCopy2.get (2).clone ();
        newPoint.getSpectralAxis ().getValue ().setValue (1.5);
        newPoint.getSpectralAxis ().getValue ().setUnit ("abc");
        pointListCopy2.add (newPoint);


        // filter first value of first segment
        pointList = new ArrayList<Point> (pointListCopy1);
        pointList.remove (1);
        pointList.remove (1);
        segment1.getData ().setPoint (pointList);
        
        oracleList.clear ();
        oracle = OracleBuilder.buildFromSegment (segment1);
        oracleList.add (oracle);
        

        segment1.getData ().setPoint (pointListCopy1);
        sed2 = sed.filterSed (0.0, 0.5, "xyz");
        this.testSed (sed2, oracleList);
       

        // filter last value of last segment
        pointList = new ArrayList<Point> (pointListCopy2);
        pointList.remove (0);
        pointList.remove (0);
        pointList.remove (1);
        segment2.getData ().setPoint (pointList);

        oracleList.clear ();
        oracle = OracleBuilder.buildFromSegment (segment2);
        oracleList.add (oracle);

        segment2.getData ().setPoint (pointListCopy2);
        sed2 = sed.filterSed (3.0, 4.0, "xyz");
        System.out.println(sed.getNumberOfSegments());
        this.testSed (sed2, oracleList);

        // filter overlapping range from both segments
        pointList = new ArrayList<Point> (pointListCopy1);
        pointList.remove (0);
        pointList.remove (1);
        segment1.getData ().setPoint (pointList);
        pointList = new ArrayList<Point> (pointListCopy2);
        pointList.remove (1);
        pointList.remove (1);
        pointList.remove (1);
        segment2.getData ().setPoint (pointList);

        oracleList.clear ();
        oracle = OracleBuilder.buildFromSegment (segment1);
        oracleList.add (oracle);
        oracle = OracleBuilder.buildFromSegment (segment2);
        oracleList.add (oracle);

        segment1.getData ().setPoint (pointListCopy1);
        segment2.getData ().setPoint (pointListCopy2);
        sed2 = sed.filterSed (1.0, 2.0, "xyz");
        this.testSed (sed2, oracleList);


        // filter first value first segment and last value of last segment
        pointList = new ArrayList<Point> (pointListCopy1);
        pointList.remove (0);
        pointList.remove (1);
        segment1.getData ().setPoint (pointList);
        pointList = new ArrayList<Point> (pointListCopy2);
        pointList.remove (1);
        pointList.remove (1);
        segment2.getData ().setPoint (pointList);

        oracleList.clear ();
        oracle = OracleBuilder.buildFromSegment (segment1);
        oracleList.add (oracle);
        oracle = OracleBuilder.buildFromSegment (segment2);
        oracleList.add (oracle);

        segment1.getData ().setPoint (pointListCopy1);
        segment2.getData ().setPoint (pointListCopy2);
        rangeParamList.clear ();
        rangeParamList.add (new RangeParam (1.0,2.0,"xyz"));
        rangeParamList.add (new RangeParam (1.0,2.0,"abc"));
        sed2 = sed.filterSed (rangeParamList);

        this.testSed (sed2, oracleList);
        
        // filter on order (1 point first segment 2 pionts second segment)
        pointList = new ArrayList<Point> (pointListCopy1);
        pointList.remove (2);
        segment1.getData ().setPoint (pointList);
        pointList = new ArrayList<Point> (pointListCopy2);
        pointList.remove (0);
        pointList.remove (2);
        segment2.getData ().setPoint (pointList);

        oracleList.clear ();
        oracle = OracleBuilder.buildFromSegment (segment1);
        oracleList.add (oracle);
        oracle = OracleBuilder.buildFromSegment (segment2);
        oracleList.add (oracle);

        segment1.getData ().setPoint (pointListCopy1);
        segment2.getData ().setPoint (pointListCopy2);
        rangeParamList.clear ();
        rangeParamList.add (new RangeParam (0.5,1.8,"xyz"));
        rangeParamList.add (new RangeParam (0.0,0.75,"xyz"));
        rangeParamList.add (new RangeParam (2.5,5.0,"xyz"));
        rangeParamList.add (new RangeParam (0.0,0.75,"xyz"));
        rangeParamList.add (new RangeParam ("0.0 5.0"));
        sed2 = sed.filterSed (rangeParamList);

        this.testSed (sed2, oracleList);
                

    }



    private void compatibleTrue(Segment segment1, Segment segment2) throws SedNoDataException, SedInconsistentException {
        assertTrue(segment1.isCompatibleWith(segment2));
        assertTrue(segment2.isCompatibleWith(segment1));
    }

    private void compatibleFalse(Segment segment1, Segment segment2) throws SedNoDataException, SedInconsistentException {
        assertFalse(segment1.isCompatibleWith(segment2));
        assertFalse(segment2.isCompatibleWith(segment1));
    }

    private void testSed (Sed newSed, List<Oracle> oracleList)
    {

        if (oracleList.size () != newSed.getNumberOfSegments ())
            fail ("The new sed has different number of segments from the original sed. Oringal: "+oracleList.size ()+" New: "+newSed.getNumberOfSegments ());

        for (int ii=0; ii<oracleList.size (); ii++)
        {
            Segment segment = newSed.getSegment(ii);
            Oracle oracle = oracleList.get (ii);
            try {
                oracle.test(segment);
            } catch (Exception ex) {
                fail(ex.getMessage());
            }
        }


    }

}
