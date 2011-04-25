/***********************************************************************
*
* File: EmptyObjects.java
*
* Author:  jcant		Created: Wed Jun 10 11:07:37 2009
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
* Update History
*   2010-12-23:  MCD  Add methods for allocating individual objects.
*
***********************************************************************/


package cfa.vo.sedlib;

import java.util.List;


/**
   Contains static methods that create Segments with members defined down 
   to a specified level of the tree (thinking of a Segment as a tree-like 
   data structure).  Each level corresponds roughly to a 'term' in a utype. 
   When one of the createLevel(n) methods is called, all child nodes are 
   created down to a certain level.
   <p>
   These are designed to create objects in which certain fields are null which 
   is useful for testing.  When one has tested all the levels provided by 
   these methods, all possitilibies will have been covered.
   <p>
   createLevel0 returns a Segment with no children.
   <p>
   Data members derived from Param (e.g. DoubleParam) are not created; 
   they are considered as the leaves of the tree for this purpose.
*/ 
public class EmptyObjects
{
    static public Sed createSED(int nsegments )
    {
	Sed sed = null;
	List<Segment> seglist = null;
	
	/* Create SED */
	sed = new Sed();
        sed.setNamespace ("spec");

	/* Create and Add empty Segment(s) to SED */
	for (int ii = 0; ii < nsegments; ii++ )
	{
            try
            {
	        sed.addSegment( new Segment() );
            }
            catch (Exception exp)
            {
                throw new RuntimeException (exp.getMessage (), exp);
            }
	}

	return sed ;
    }

    // *********************************************************************************
    // MCD CUT LINE
    // *********************************************************************************

//    static public Segment createLevel0()
//    {
//	return new Segment();
//    }
//
//    static public Segment createLevel1()
//    {
//	Segment segment = createLevel0();
//
//	segment.setChar( new Characterization() );
//	segment.setCoordSys( new CoordSys() );
//	segment.setCuration( new Curation() );
//	segment.setDataID( new DataID() );
//	segment.setDerived( new DerivedData() );
//	segment.setTarget( new Target() );
//
//	return segment;
//    }
//
//
//    static public Segment createLevel2()
//    {
//	Segment segment = createLevel1();
//
//	segment.getCuration().setContact( new Contact() );
//	segment.getDerived().setRedshift( new SedQuantity() );
//	segment.getCoordSys().getCoordFrame().clear();
//	segment.getCoordSys().getCoordFrame().add( new CoordFrame() );
//
//	segment.getChar().setFluxAxis( new CharacterizationAxis() );
//	segment.getChar().setSpectralAxis( new SpectralCharacterizationAxis() );
//	segment.getChar().setSpatialAxis( new CharacterizationAxis() );
//	segment.getChar().setTimeAxis( new CharacterizationAxis() );
//	segment.getChar().getCharacterizationAxis().add(  new CharacterizationAxis() );
//	segment.getChar().getCharacterizationAxis().add(  new CharacterizationAxis() );
//
//	return segment;
//    }
//
//    static public Segment createLevel3()
//    {
//	Segment segment = createLevel2();
//
//	CharacterizationAxis axis = null;
//
//	// Flux Axis
//	axis = segment.getChar().getFluxAxis();
//	axis.setAccuracy( new Accuracy() );
//	axis.setCoordSystem( new CoordSys() );
//	axis.setCoverage( new Coverage() );
//	axis.getCoverage().setBounds( new CoverageBounds() );
//	axis.getCoverage().getBounds().setExtent( new DoubleParam( 1.21 ) );
//	axis.getCoverage().getBounds().setRange( new Interval() );
//	axis.getCoverage().getBounds().getRange().setMin( new DoubleParam( 1.001 ) );
//	axis.getCoverage().getBounds().getRange().setMax( new DoubleParam( 1.002 ) );
//	axis.setSamplingPrecision( new SamplingPrecision() );
//	axis.getSamplingPrecision().setSampleExtent( new DoubleParam( 1.39 ) );
//	axis.getSamplingPrecision().setSamplingPrecisionRefVal( new SamplingPrecisionRefVal() );
//	axis.getSamplingPrecision().getSamplingPrecisionRefVal().setFillFactor( new DoubleParam( 1.444 ) );
//
//	// Spectral
//	axis = segment.getChar().getSpectralAxis();
//	axis.setAccuracy( new Accuracy() );
//	axis.setCoordSystem( new CoordSys() );
//	axis.setCoverage( new Coverage() );
//	axis.getCoverage().setBounds( new CoverageBounds() );
//	axis.getCoverage().getBounds().setExtent( new DoubleParam( 2.21 ) );
//	axis.getCoverage().getBounds().setRange( new Interval() );
//	axis.getCoverage().getBounds().getRange().setMin( new DoubleParam( 2.001 ) );
//	axis.getCoverage().getBounds().getRange().setMax( new DoubleParam( 2.002 ) );
//	axis.setSamplingPrecision( new SamplingPrecision() );
//	axis.getSamplingPrecision().setSampleExtent( new DoubleParam( 2.39 ) );
//	axis.getSamplingPrecision().setSamplingPrecisionRefVal( new SamplingPrecisionRefVal() );
//	axis.getSamplingPrecision().getSamplingPrecisionRefVal().setFillFactor( new DoubleParam( 2.444 ) );
//
//	// Spatial
//	axis = segment.getChar().getSpatialAxis();
//	axis.setAccuracy( new Accuracy() );
//	axis.setCoordSystem( new CoordSys() );
//	axis.setCoverage( new Coverage() );
//	axis.getCoverage().setBounds( new CoverageBounds() );
//	axis.getCoverage().getBounds().setExtent( new DoubleParam( 3.21 ) );
//	axis.getCoverage().getBounds().setRange( new Interval() );
//	axis.getCoverage().getBounds().getRange().setMin( new DoubleParam( 3.001 ) );
//	axis.getCoverage().getBounds().getRange().setMax( new DoubleParam( 3.002 ) );
//	axis.setSamplingPrecision( new SamplingPrecision() );
//	axis.getSamplingPrecision().setSampleExtent( new DoubleParam( 3.39 ) );
//	axis.getSamplingPrecision().setSamplingPrecisionRefVal( new SamplingPrecisionRefVal() );
//	axis.getSamplingPrecision().getSamplingPrecisionRefVal().setFillFactor( new DoubleParam( 3.444 ) );
//
//	// Time
//	axis = segment.getChar().getTimeAxis();
//	axis.setAccuracy( new Accuracy() );
//	axis.setCoordSystem( new CoordSys() );
//	axis.setCoverage( new Coverage() );
//	axis.getCoverage().setBounds( new CoverageBounds() );
//	axis.getCoverage().getBounds().setExtent( new DoubleParam( 4.21 ) );
//	axis.getCoverage().getBounds().setRange( new Interval() );
//	axis.getCoverage().getBounds().getRange().setMin( new DoubleParam( 4.001 ) );
//	axis.getCoverage().getBounds().getRange().setMax( new DoubleParam( 4.002 ) );
//	axis.setSamplingPrecision( new SamplingPrecision() );
//	axis.getSamplingPrecision().setSampleExtent( new DoubleParam( 4.39 ) );
//	axis.getSamplingPrecision().setSamplingPrecisionRefVal( new SamplingPrecisionRefVal() );
//	axis.getSamplingPrecision().getSamplingPrecisionRefVal().setFillFactor( new DoubleParam( 4.444 ) );
//
//	return segment;
//    }
//
//    static public Segment createLevel4()
//    {
//	Segment segment = createLevel3();
//
//	// note: call setSamplingPrecision() for each axis to erase data added in
//	// createLevel3
//
//	// Spectral
//	CharacterizationAxis axis = segment.getChar().getSpectralAxis();
//
//	axis.getCoverage().setBounds( new CoverageBounds() );
//	axis.getCoverage().setLocation( new CoverageLocation() );
//	axis.getCoverage().getLocation().setResolution( new DoubleParam( 1.88 ) );
//	axis.getCoverage().getLocation().createValue()[0] = new DoubleParam( 1.61 );
//	axis.getCoverage().getLocation().getValue()[1] = new DoubleParam( 1.62 );
//	axis.getCoverage().setSupport( new CoverageSupport() );
//	axis.setSamplingPrecision( new SamplingPrecision() );
//	axis.getCoverage().getSupport().setExtent( new DoubleParam( 1.8 ));
//	SkyRegion skyRegion = new SkyRegion();
//	skyRegion.setValue( "This is the #1 sky region" );
//	axis.getCoverage().getSupport().setArea( skyRegion );
//
//	java.util.List<Interval> range = axis.getCoverage().getSupport().createRange();
//	Interval interval = new Interval();
//	interval.setMin( new DoubleParam( 1.02 ) );
//	interval.setMax( new DoubleParam( 1.22 ) );
//	range.add( interval );
//
//	axis.getCoverage().getLocation().setAccuracy( new Accuracy() );
//	axis.getCoverage().getLocation().getAccuracy().setBinLow( new DoubleParam( 1.011 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinHigh( new DoubleParam( 1.012 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinSize( new DoubleParam( 1.014 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatErrLow( new DoubleParam( 1.014 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatErrHigh( new DoubleParam( 1.015 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatError( new DoubleParam( 1.016 ) );
//	axis.getCoverage().getLocation().getAccuracy().setSysError( new DoubleParam( 1.017 ) );
//	axis.getCoverage().getLocation().getAccuracy().setConfidence( new DoubleParam( 1.018 ) );
//
//	// Time
//	axis = segment.getChar().getTimeAxis();
//	axis.getCoverage().setBounds( new CoverageBounds() );
//	axis.getCoverage().setLocation( new CoverageLocation() );
//	axis.getCoverage().getLocation().setResolution( new DoubleParam( 2.88 ) );
//	axis.getCoverage().getLocation().createValue()[0] = new DoubleParam( 2.61 );
//	axis.getCoverage().getLocation().getValue()[1] = new DoubleParam( 2.62 );
//	axis.getCoverage().setSupport( new CoverageSupport() );
//	axis.setSamplingPrecision( new SamplingPrecision() );
//	axis.getCoverage().getSupport().setExtent( new DoubleParam( 2.8 ));
//	skyRegion = new SkyRegion();
//	skyRegion.setValue( "This is the #3 sky region" );
//	axis.getCoverage().getSupport().setArea( skyRegion );
//
//	range = axis.getCoverage().getSupport().getRange();
//	interval = new Interval();
//	interval.setMin( new DoubleParam( 2.02 ) );
//	interval.setMax( new DoubleParam( 2.22 ) );
//	range.add( interval );
//
//	axis.getCoverage().getLocation().setAccuracy( new Accuracy() );
//	axis.getCoverage().getLocation().getAccuracy().setConfidence( new DoubleParam( 2.018 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinLow( new DoubleParam( 2.011 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinHigh( new DoubleParam( 2.012 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinSize( new DoubleParam( 2.014 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatErrLow( new DoubleParam( 2.014 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatErrHigh( new DoubleParam( 2.015 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatError( new DoubleParam( 2.016 ) );
//	axis.getCoverage().getLocation().getAccuracy().setSysError( new DoubleParam( 2.017 ) );
//	axis.getCoverage().getLocation().getAccuracy().setConfidence( new DoubleParam( 2.018 ) );
//
//	// Flux
//	axis = segment.getChar().getFluxAxis();
//	axis.getCoverage().setBounds( new CoverageBounds() );
//	axis.getCoverage().setLocation( new CoverageLocation() );
//	axis.getCoverage().getLocation().setResolution( new DoubleParam( 3.88 ) );
//	axis.getCoverage().getLocation().createValue()[0] = new DoubleParam( 3.61 );
//	axis.getCoverage().getLocation().getValue()[1] = new DoubleParam( 3.62 );
//	axis.getCoverage().setSupport( new CoverageSupport() );
//	axis.setSamplingPrecision( null );
//	axis.getCoverage().getSupport().setExtent( new DoubleParam( 3.8 ));
//	skyRegion = new SkyRegion();
//	skyRegion.setValue( "This is the #3 sky region" );
//	axis.getCoverage().getSupport().setArea( skyRegion );
//
//	range = axis.getCoverage().getSupport().getRange();
//	interval = new Interval();
//	interval.setMin( new DoubleParam( 3.02 ) );
//	interval.setMax( new DoubleParam( 3.22 ) );
//	range.add( interval );
//
//	axis.getCoverage().getLocation().setAccuracy( new Accuracy() );
//	axis.getCoverage().getLocation().getAccuracy().setConfidence( new DoubleParam( 3.018 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinLow( new DoubleParam( 3.011 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinHigh( new DoubleParam( 3.012 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinSize( new DoubleParam( 3.014 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatErrLow( new DoubleParam( 3.014 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatErrHigh( new DoubleParam( 3.015 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatError( new DoubleParam( 3.016 ) );
//	axis.getCoverage().getLocation().getAccuracy().setSysError( new DoubleParam( 3.017 ) );
//	axis.getCoverage().getLocation().getAccuracy().setConfidence( new DoubleParam( 3.018 ) );
//
//	// Spatial
//	axis = segment.getChar().getSpatialAxis();
//	axis.getCoverage().setBounds( new CoverageBounds() );
//	axis.getCoverage().setLocation( new CoverageLocation() );
//	axis.getCoverage().getLocation().setResolution( new DoubleParam( 4.88 ) );
//	axis.getCoverage().getLocation().createValue()[0] = new DoubleParam( 4.61 );
//	axis.getCoverage().getLocation().getValue()[1] = new DoubleParam( 4.62 );
//	axis.getCoverage().setSupport( new CoverageSupport() );
//	axis.setSamplingPrecision( new SamplingPrecision() );
//	axis.getCoverage().getSupport().setExtent( new DoubleParam( 4.8 ));
//	skyRegion = new SkyRegion();
//	skyRegion.setValue( "This is the #4 sky region" );
//	axis.getCoverage().getSupport().setArea( skyRegion );
//
//	range = axis.getCoverage().getSupport().getRange();
//	interval = new Interval();
//	interval.setMin( new DoubleParam( 4.02 ) );
//	interval.setMax( new DoubleParam( 4.22 ) );
//	range.add( interval );
//
//	axis.getCoverage().getLocation().setAccuracy( new Accuracy() );
//	axis.getCoverage().getLocation().getAccuracy().setBinLow( new DoubleParam( 4.011 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinHigh( new DoubleParam( 4.012 ) );
//	axis.getCoverage().getLocation().getAccuracy().setBinSize( new DoubleParam( 4.014 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatErrLow( new DoubleParam( 4.014 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatErrHigh( new DoubleParam( 4.015 ) );
//	axis.getCoverage().getLocation().getAccuracy().setStatError( new DoubleParam( 4.016 ) );
//	axis.getCoverage().getLocation().getAccuracy().setSysError( new DoubleParam( 4.017 ) );
//	axis.getCoverage().getLocation().getAccuracy().setConfidence( new DoubleParam( 4.018 ) );
//	return segment;
//    }
}
