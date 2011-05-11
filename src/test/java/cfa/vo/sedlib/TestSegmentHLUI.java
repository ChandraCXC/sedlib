/***********************************************************************
*
* File: TestSegmentHLUI.java
*
* Author:  jmiller              Created: Mon Mar  7 09:08:14 2011
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
* Update History:
*
***********************************************************************/

package cfa.vo.sedlib;


import junit.framework.Test;
import junit.framework.TestSuite;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.Utypes;
import cfa.vo.sedlib.io.SedFormat;
import cfa.vo.testtools.SedLibTestUtils;

import java.util.List;
import java.util.TreeSet;

public class TestSegmentHLUI extends SedTestBase
{
	
    Sed m_sed = null;
    String m_fname = null;
    boolean keep = true;
    int rc = 1;

    public TestSegmentHLUI (String name)
    {
        super(name);
    }

    public static Test suite ()
    {
        TestSuite suite = new TestSuite( TestSegmentHLUI.class );

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
     *  Tests the spectral and flux axis high level user
     *  interfaces against segments that contain data and units.
     *  
     */
    public void testFluxAndSpectralAxisHLUI ()
    {
        String testName = "testFluxAndSpectralAxisHLUI";
        System.out.println("   run "+testName+"()");
        double values[];
        List<Point> points;
        String units;

        /* Create SED with one (empty) segment */
        m_sed = EmptyObjects.createSED( 0 );

        /* Get the Segment */
        Spectrum segment = CompleteObjects.createSpectrum();
        assertNotNull( "Failed to create SEGMENT: " , segment );

        /* Assign to SED                                 */
        try
        {
            m_sed.addSegment(segment);
        }
        catch (Exception exp)
        {
            throw new RuntimeException (exp.getMessage (), exp);
        }

        // Test the spectral and flux value routines

        // get and set the spectral axis values
        try
        {
            values = segment.getSpectralAxisValues ();
        
            for (int ii=0; ii<values.length; ii++)
                values[ii]++;

            segment.setSpectralAxisValues (values);
 
            // get and set the flux axis values       
            values = segment.getFluxAxisValues ();

            for (int ii=0; ii<values.length; ii++)
                values[ii]++;

            segment.setFluxAxisValues (values);

            // Test the specral and flux unit routines

            // get and set the spectral axis units
            units = segment.getSpectralAxisUnits ();

            units += "_test";

            segment.setSpectralAxisUnits (units);

            // get and set the flux axis units
            units = segment.getFluxAxisUnits ();
        
            units += "_test";

            segment.setFluxAxisUnits (units);
        }
        catch (Exception exp)
        {
            throw new RuntimeException (exp.getMessage (), exp);
        }


        // write out the sed and compare the files
        m_fname = "SpectrumHLUI.vot";
        rc = writeSED( SedFormat.VOT , SedLibTestUtils.mkOutFileName( m_fname ), m_sed );
        assertEquals( testName + ": Failed to write " + m_fname, 0, rc );

        rc = SedLibTestUtils.DIFFIT( m_fname );

        assertEquals( testName + ": Diff failed - " + m_fname, 0, rc );
        if ( (rc == 0 ) && (!keep) )
            SedLibTestUtils.cleanupFiles( m_fname );

        // Go through all the points and verify that the units are in fact the same
        // All units are not written out to file just one.

        points = segment.getData ().getPoint ();
        for (int ii=0; ii<points.size (); ii++)
        {
            String fluxUnits = points.get(ii).getFluxAxis ().getValue ().getUnit ();
            String spectralUnits = points.get(ii).getSpectralAxis ().getValue ().getUnit ();
   
            assertEquals( testName + ": Flux axis units did no match", units, fluxUnits );
            assertEquals( testName + ": Spectral axis units did no match", units, spectralUnits );

        }
        
    }


    /**
     *  Tests the spectral and flux axis high level user
     *  interfaces against segments that contain partial and
     *  empty data and units values.
     *
     */
    public void testPartialFluxAndSpectralAxisHLUI ()
    {
        String testName = "testPartialFluxAndSpectralAxisHLUI";
        System.out.println("   run "+testName+"()");
        double fluxValues[] = {0.0, 0.1, Double.NaN, .3};
        double specValues[] = {Double.NaN, 1.1, 1.2, 1.3};
        double values[];
        List<Point> points;
        String units;

        /* Create SED with one (empty) segment */
        m_sed = EmptyObjects.createSED( 1 );

        /* Get the Segment */
        Segment segment = m_sed.getSegment(0);
        segment.setChar( CompleteObjects.createCharacterization() );
        assertNotNull( "Failed to create SEGMENT: " , segment );

        /* Assign to SED                                 */
        try
        {
            m_sed.addSegment(segment);
        }
        catch (SedInconsistentException exp)
        {
            throw new RuntimeException (exp.getMessage (), exp);
        }
        catch (SedNoDataException ex) {
            // don't do anything, we still have to add the data
        }


        try
        {
            // Try to set and get units from an empty data set
            segment.setFluxAxisUnits ("TEST");
            fail ("Set flux axis should have failed with an error on no data");
        }
        catch (Exception exp)
        {
        	;
        }
        
        try
        {
            units = segment.getFluxAxisUnits ();
            fail ("Get flux axis should have failed with an error on no data");
        }
        catch (Exception exp)
        {
            
        }
      
        try
        {
            segment.setSpectralAxisUnits ("TEST");
            fail ("Set flux axis should have failed with an error on no data");
        }    
        catch (Exception exp)
        {
        	;
        }
        
        try
        {
            units = segment.getSpectralAxisUnits ();
            fail ("Set flux axis should have failed with an error on no data");
        }
        catch (Exception exp)
        {
            
        }    


        try
        {
            // Try to set and get data from an empty data set
            segment.setFluxAxisValues (fluxValues);
            values = segment.getFluxAxisValues ();

            for (int ii=0; ii<values.length; ii++)
                assertEquals( testName + ": Checking values in setFluxAxisValues", fluxValues[ii], values[ii]);

            segment.setSpectralAxisValues (specValues);
            values = segment.getSpectralAxisValues ();
            for (int ii=0; ii<values.length; ii++)
                assertEquals( testName + ": Checking values in setFluxAxisValues", specValues[ii], values[ii]);


            // Try to set and get units from an empty data set
            segment.setFluxAxisUnits ("FLUXTEST");
            units = segment.getFluxAxisUnits ();
            assertEquals( testName + ": Flux axis units did no match", "FLUXTEST", units );

            segment.setSpectralAxisUnits ("SPECTEST");
            units = segment.getSpectralAxisUnits ();
            assertEquals( testName + ": Spectral axis units did no match", "SPECTEST", units );


            // Remove points values and reset the units
            points = segment.getData ().getPoint ();
            points.get(2).getFluxAxis ().setValue (null);
            points.get(0).getSpectralAxis ().setValue (null);

            segment.setFluxAxisUnits ("FLUXTEST2");
            units = segment.getFluxAxisUnits ();
            assertEquals( testName + ": Spectral axis units did no match", "FLUXTEST2", units );

            segment.setSpectralAxisUnits ("SPECTEST2");
            units = segment.getSpectralAxisUnits ();
            assertEquals( testName + ": Spectral axis units did no match", "SPECTEST2", units );

            assertNull( testName + ": Verify flux axis value is null", points.get(2).getFluxAxis ().getValue () );
            assertNull( testName + ": Verify spectral axis value is null", points.get(0).getSpectralAxis ().getValue () );

        }

        catch (Exception exp)
        {
            throw new RuntimeException (exp.getMessage (), exp);
        }

    }


    /**
     *  Tests the high level user interface for all
     *  utypes, using utype enumeration
     *
     */
    public void testGeneralParameterHLUI ()
    {
        String testName = "testGeneralParameterHLUI";
        System.out.println("   run "+testName+"()");

        /* Create SED with one (empty) segment */
        m_sed = EmptyObjects.createSED( 0 );

        /* Get the Segment */
        Spectrum segment = CompleteObjects.createSpectrum();
        assertNotNull( "Failed to create SEGMENT: " , segment );

        /* Assign to SED                                 */
        try
        {
            m_sed.addSegment(segment);

        }
        catch (Exception exp)
        {
            throw new RuntimeException (exp.getMessage (), exp);
        }

        // try to get char axis from multiple axis list
        try
        {
            Param param = segment.getMetaParam (Utypes.SEG_CHAR_CHARAXIS_ACC_CONFIDENCE);
            fail ("Should not be able to get a parameter from multiple axes");
        }
        catch (SedException exp)
        {
            ;
        }

        

        // try to get coord frame from multiple coord frame list

        // add one of each type of frame
        List<CoordFrame> cf = segment.getCoordSys ().getCoordFrame ();
        int frameSize = cf.size ();

        cf.add (new SpaceFrame ());
        cf.add (new SpaceFrame ());
        cf.add (new SpectralFrame ());
        cf.add (new SpectralFrame ());
        cf.add (new TimeFrame ());
        cf.add (new TimeFrame ());

        try
        {
            Param param = segment.getMetaParam (Utypes.SEG_CS_SPACEFRAME_EQUINOX);
            fail ("Should not be able to get a parameter from multiple space frame");
        }
        catch (SedException exp)
        {
            ;
        }

        try
        {
            Param param = segment.getMetaParam (Utypes.SEG_CS_SPECTRALFRAME_REDSHIFT);
            fail ("Should not be able to get a parameter from multiple spectral frame");
        }
        catch (SedException exp)
        {
            ;
        }

        try
        {
            Param param = segment.getMetaParam (Utypes.SEG_CS_TIMEFRAME_ZERO);
            fail ("Should not be able to get a parameter from multiple time frame");
        }
        catch (SedException exp)
        {
            ;
        }


        // remove extra coord frames
        
        for (int ii=cf.size ()-1; ii>=frameSize; ii--)
            cf.remove (ii);

        this.runParameterTest (segment, false);

        // write out the sed and compare the files
        m_fname = "GeneralHLUI.vot";
        rc = writeSED( SedFormat.VOT , SedLibTestUtils.mkOutFileName( m_fname ), m_sed );
        assertEquals( testName + ": Failed to write " + m_fname, 0, rc );

        rc = SedLibTestUtils.DIFFIT( m_fname );

        assertEquals( testName + ": Diff failed - " + m_fname, 0, rc );
        if ( (rc == 0 ) && (!keep) )
            SedLibTestUtils.cleanupFiles( m_fname );
    }


    /**
     *  Tests the high level user interface for all
     *  utypes, using utype names
     *
     */
    public void testGeneralParameterNameHLUI ()
    {
        String testName = "testGeneralParameterHLUI";
        System.out.println("   run "+testName+"()");

        /* Create SED with one (empty) segment */
        m_sed = EmptyObjects.createSED( 0 );

        /* Get the Segment */
        Spectrum segment = CompleteObjects.createSpectrum();
        assertNotNull( "Failed to create SEGMENT: " , segment );

        /* Assign to SED                                 */
        try
        {
            m_sed.addSegment(segment);

        }
        catch (Exception exp)
        {
            throw new RuntimeException (exp.getMessage (), exp);
        }

        this.runParameterTest (segment, true);

        // write out the sed and compare the files
        m_fname = "GeneralNameHLUI.vot";
        rc = writeSED( SedFormat.VOT , SedLibTestUtils.mkOutFileName( m_fname ), m_sed );
        assertEquals( testName + ": Failed to write " + m_fname, 0, rc );

        rc = SedLibTestUtils.DIFFIT( m_fname );

        assertEquals( testName + ": Diff failed - " + m_fname, 0, rc );
        if ( (rc == 0 ) && (!keep) )
            SedLibTestUtils.cleanupFiles( m_fname );
    }



    private void runParameterTest (Spectrum segment, boolean useString)
    {

        // a list of utypes that are in MetaParamList
        TreeSet<Integer> listUtypes = new TreeSet <Integer> ();

        // a list of utypes not supported by param
        TreeSet<Integer> unusedUtypes = new TreeSet <Integer> ();

        // a list of data utypes
        TreeSet<Integer> dataUtypes = new TreeSet <Integer> ();

        listUtypes.add (Utypes.SEG_DATAID_COLLECTION);
        listUtypes.add (Utypes.SEG_DATAID_CONTRIBUTOR);
        listUtypes.add (Utypes.SEG_CHAR_CHARAXIS_COV_LOC_VALUE);
        listUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE);
        listUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE);
        listUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_VALUE);
        listUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_VALUE);
        listUtypes.add (Utypes.TARGET_POS);

        unusedUtypes.add (Utypes.SED);
        unusedUtypes.add (Utypes.DATAMODEL);
        unusedUtypes.add (Utypes.LENGTH);
        unusedUtypes.add (Utypes.TYPE);
        unusedUtypes.add (Utypes.TARGET);
        unusedUtypes.add (Utypes.SEG_CS);
        unusedUtypes.add (Utypes.SEG_CS_ID);
        unusedUtypes.add (Utypes.SEG_CS_HREF);
        unusedUtypes.add (Utypes.SEG_CS_UCD);
        unusedUtypes.add (Utypes.SEG_CS_TYPE);
        unusedUtypes.add (Utypes.SEG_CS_IDREF);
        unusedUtypes.add (Utypes.SEG_CS_SPACEFRAME);
        unusedUtypes.add (Utypes.SEG_CS_SPACEFRAME_ID);
        unusedUtypes.add (Utypes.SEG_CS_SPACEFRAME_NAME);
        unusedUtypes.add (Utypes.SEG_CS_SPACEFRAME_UCD);
        unusedUtypes.add (Utypes.SEG_CS_SPACEFRAME_REFPOS);
        unusedUtypes.add (Utypes.SEG_CS_TIMEFRAME);
        unusedUtypes.add (Utypes.SEG_CS_TIMEFRAME_ID);
        unusedUtypes.add (Utypes.SEG_CS_TIMEFRAME_NAME);
        unusedUtypes.add (Utypes.SEG_CS_TIMEFRAME_UCD);
        unusedUtypes.add (Utypes.SEG_CS_TIMEFRAME_REFPOS);
        unusedUtypes.add (Utypes.SEG_CS_SPECTRALFRAME);
        unusedUtypes.add (Utypes.SEG_CS_SPECTRALFRAME_ID);
        unusedUtypes.add (Utypes.SEG_CS_SPECTRALFRAME_NAME);
        unusedUtypes.add (Utypes.SEG_CS_SPECTRALFRAME_UCD);
        unusedUtypes.add (Utypes.SEG_CS_SPECTRALFRAME_REFPOS);
        unusedUtypes.add (Utypes.SEG_CS_REDFRAME);
        unusedUtypes.add (Utypes.SEG_CS_REDFRAME_ID);
        unusedUtypes.add (Utypes.SEG_CS_REDFRAME_NAME);
        unusedUtypes.add (Utypes.SEG_CS_REDFRAME_UCD);
        unusedUtypes.add (Utypes.SEG_CS_REDFRAME_REFPOS);
        unusedUtypes.add (Utypes.SEG_CS_REDFRAME_DOPPLERDEF);
        unusedUtypes.add (Utypes.SEG_CS_GENFRAME);
        unusedUtypes.add (Utypes.SEG_CS_GENFRAME_ID);
        unusedUtypes.add (Utypes.SEG_CS_GENFRAME_NAME);
        unusedUtypes.add (Utypes.SEG_CS_GENFRAME_UCD);
        unusedUtypes.add (Utypes.SEG_CS_GENFRAME_REFPOS);
        unusedUtypes.add (Utypes.SEG_CURATION);
        unusedUtypes.add (Utypes.SEG_CURATION_CONTACT);
        unusedUtypes.add (Utypes.SEG_DATAID);
        unusedUtypes.add (Utypes.SEG_DD);
        unusedUtypes.add (Utypes.SEG_DD_REDSHIFT);
        unusedUtypes.add (Utypes.SEG_DD_REDSHIFT_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_NAME);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_UNIT);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_UCD);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_COV);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_COV_LOC);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_RANGE);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_SAMPPREC);
        unusedUtypes.add (Utypes.SEG_CHAR_CHARAXIS_SAMPPREC_SAMPPRECREFVAL);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_NAME);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_UNIT);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_UCD);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_SAMPPREC);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_COV);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_COV_LOC);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_COV_BOUNDS);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_COV_SUPPORT);
        unusedUtypes.add (Utypes.SEG_CHAR_FLUXAXIS_COV_SUPPORT_RANGE);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_NAME);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_UNIT);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_UCD);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_COV);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_COV_SUPPORT);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_RANGE);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_SAMPPREC);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL);
        unusedUtypes.add (Utypes.SEG_CHAR_SPECTRALAXIS_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_NAME);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_UNIT);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_UCD);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_COV);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_COV_BOUNDS);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_COV_SUPPORT);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_COV_SUPPORT_RANGE);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_SAMPPREC);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL);
        unusedUtypes.add (Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_NAME);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_UNIT);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_UCD);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_COV);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_COV_LOC);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_COV_SUPPORT);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_ACC);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_SAMPPREC);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL);
        unusedUtypes.add (Utypes.SEG_CHAR_TIMEAXIS_COV_SUPPORT_RANGE);
        unusedUtypes.add (Utypes.SEG_DATA);
        unusedUtypes.add (Utypes.SEG_DATA_FLUXAXIS);
        unusedUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC);
        unusedUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS);
        unusedUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC);
        unusedUtypes.add (Utypes.SEG_DATA_TIMEAXIS);
        unusedUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC);
        unusedUtypes.add (Utypes.SEG_DATA_BGM);
        unusedUtypes.add (Utypes.SEG_DATA_BGM_ACC);
        unusedUtypes.add (Utypes.CUSTOM);
        unusedUtypes.add (Utypes.SEG);
        unusedUtypes.add (Utypes.SPEC);

 

        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_VALUE);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_QUALITY);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC_BINLOW);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC_BINHIGH);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC_BINSIZE);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC_STATERRLOW);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC_STATERRHIGH);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC_STATERR);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC_SYSERR);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_ACC_CONFIDENCE);
        dataUtypes.add (Utypes.SEG_DATA_FLUXAXIS_RESOLUTION);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_VALUE);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC_STATERR);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC_BINLOW);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC_BINHIGH);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC_BINSIZE);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC_SYSERR);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_ACC_CONFIDENCE);
        dataUtypes.add (Utypes.SEG_DATA_SPECTRALAXIS_RESOLUTION);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_VALUE);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC_STATERR);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC_STATERRLOW);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC_STATERRHIGH);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC_SYSERR);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC_BINLOW);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC_BINHIGH);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC_BINSIZE);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_ACC_CONFIDENCE);
        dataUtypes.add (Utypes.SEG_DATA_TIMEAXIS_RESOLUTION);
        dataUtypes.add (Utypes.SEG_DATA_BGM_VALUE);
        dataUtypes.add (Utypes.SEG_DATA_BGM_QUALITY);
        dataUtypes.add (Utypes.SEG_DATA_BGM_ACC_STATERR);
        dataUtypes.add (Utypes.SEG_DATA_BGM_ACC_STATERRLOW);
        dataUtypes.add (Utypes.SEG_DATA_BGM_ACC_STATERRHIGH);
        dataUtypes.add (Utypes.SEG_DATA_BGM_ACC_SYSERR);
        dataUtypes.add (Utypes.SEG_DATA_BGM_RESOLUTION);
        dataUtypes.add (Utypes.SEG_DATA_BGM_ACC_BINLOW);
        dataUtypes.add (Utypes.SEG_DATA_BGM_ACC_BINHIGH);
        dataUtypes.add (Utypes.SEG_DATA_BGM_ACC_BINSIZE);
        dataUtypes.add (Utypes.SEG_DATA_BGM_ACC_CONFIDENCE);

        

        // remove extra axes
        List<CharacterizationAxis> axes = segment.getChar ().getCharacterizationAxis ();
        CharacterizationAxis axis = axes.get (0);
        axes.clear ();
        axes.add (axis);

        axis.setCoverage (CompleteObjects.createCoverage());
        axis = segment.getChar ().getFluxAxis ();
        axis.setCoverage (CompleteObjects.createCoverage ());
        axis = segment.getChar ().getSpatialAxis ();
        axis.setCoverage (CompleteObjects.createCoverage());
        axis = segment.getChar ().getTimeAxis ();
        axis.setCoverage (CompleteObjects.createCoverage ());
        axis = segment.getChar ().getSpectralAxis ();
        axis.setCoverage (CompleteObjects.createCoverage ());



        try
        {
            for (int utype=0; utype<Utypes.getNumberOfUtypes (); utype++)
            {
                String utypeName = Utypes.getName (utype);

                // make sure it's a valid utype
                if ((utypeName == null) || unusedUtypes.contains (utype))
                    continue;

                // modify list parameters
                if (listUtypes.contains (utype))
                {

                    List<? extends Param> paramList;

                    if (useString)
                        paramList = segment.getMetaParamList (utypeName);
                    else
                        paramList = segment.getMetaParamList (utype);

                    for (Param param : paramList)
                    {

                    	if (param == null)
                    		continue;
                    	
                        if (param instanceof DoubleParam)
                        {
                            DoubleParam dparam = (DoubleParam)param;
                            dparam.setValue ((Double)dparam.getCastValue () + 1000.0 );
                            dparam.setName (dparam.getName ()+"_double");
                            dparam.setUnit (dparam.getUnit ()+"_double");
                            dparam.setUcd (dparam.getUcd ()+"_double");
                        }
                        else if (param instanceof IntParam)
                        {
                            IntParam iparam = (IntParam)param;
                            iparam.setValue ((Integer)iparam.getCastValue () + 1000 );
                            iparam.setName (iparam.getName ()+"_int");
                            iparam.setUnit (iparam.getUnit ()+"_int");
                            iparam.setUcd (iparam.getUcd ()+"_int");
                        }
                        else
                        {
                            param.setValue (param.getValue () + "text" );
                            param.setName (param.getName ()+"_text");
                            param.setUcd (param.getUcd ()+"_text");
                        }
                    }

                    if (useString)
                        segment.setMetaParamList (paramList, utypeName);
                    else
                        segment.setMetaParamList (paramList, utype);

                }
                // modify data parameters
                else if (dataUtypes.contains (utype))
                {
                    Object values;
                    Field field;

                    if (useString)
                    {
                        values = segment.getDataValues (utypeName);
                        field = segment.getDataInfo (utypeName);
                    }
                    else
                    {
                        values = segment.getDataValues (utype);
                        field = segment.getDataInfo (utype);
                    }

                    field.setName (field.getName ()+"_data");
                    field.setUnit (field.getUnit ()+"_data");
                    field.setUcd (field.getUcd ()+"_data");

                    if (values instanceof double[])
                    {
                        double dvalues[] = (double [])values;
                        for (int ii=0; ii<dvalues.length; ii++)
                            dvalues[ii] += 1000.0;
                    }
                    else
                    {
                        int ivalues[] = (int [])values;
                        for (int ii=0; ii<ivalues.length; ii++)
                            ivalues[ii] += 1000;
                    }

                    if (useString)
                    {
                        segment.setDataValues (values, utypeName);
                        segment.setDataInfo (field, utypeName);
                    }
                    else
                    {
                        segment.setDataValues (values, utype);
                        segment.setDataInfo (field, utype);
                    }
                }
                // modify all other parameters
                else
                {
                    Param param;

                    if (useString)
                        param = segment.getMetaParam (utypeName);
                    else
                        param = segment.getMetaParam (utype);
                    
                    if (param == null)
                    	continue;

                    if (param instanceof DoubleParam)
                    {
                        DoubleParam dparam = (DoubleParam)param;
                        dparam.setValue ((Double)dparam.getCastValue () + 1000.0 );
                        dparam.setName (dparam.getName ()+"_double");
                        dparam.setUnit (dparam.getUnit ()+"_double");
                        dparam.setUcd (dparam.getUcd ()+"_double");
                    }
                    else if (param instanceof IntParam)
                    {
                        IntParam iparam = (IntParam)param;
                        iparam.setValue ((Integer)iparam.getCastValue () + 1000 );
                        iparam.setName (iparam.getName ()+"_int");
                        iparam.setUnit (iparam.getUnit ()+"_int");
                        iparam.setUcd (iparam.getUcd ()+"_int");
                    }
                    else
                    {
                        param.setValue (param.getValue () + "_text" );
                        param.setName (param.getName ()+"_text");
                        param.setUcd (param.getUcd ()+"_text");
                    }

                    if (useString)
                        segment.setMetaParam (param, utypeName);
                    else
                        segment.setMetaParam (param, utype);
                }

            }
        }
        catch (Exception exp)
        {
            throw new RuntimeException (exp.getMessage (), exp);
        }


    }


}




        
        

