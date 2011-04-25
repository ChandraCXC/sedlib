/***********************************************************************
*
* File: CompleteObjects.java
*
* Author:  jcant		Created: Wed Jun 10 11:07:37 2009
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
* Update History:
*   2010-12-23:  MCD  Add methods for defining individual objects.
* 
***********************************************************************/


package cfa.vo.sedlib;

import java.util.List;
import java.util.Vector;

/**
   Contains methods that create objects that have every variable in the object set.
*/	
public class CompleteObjects
{

    /**
     *  Create Accuracy object and populate attributes with values.
     *  @return  The completed Accuracy object
     */
    static public Accuracy createAccuracy()
    {
	Accuracy result = new Accuracy();

	result.setSysError( new DoubleParam( 0.0 ) );
	result.setConfidence( new DoubleParam( 0.0 ) );

	result.setBinLow( new DoubleParam( 0.0 ) );
	result.setBinHigh( new DoubleParam( 0.0 ) );
	result.setBinSize( new DoubleParam( 0.0 ) );

	result.setStatError( new DoubleParam( 0.0 ) );
	result.setStatErrLow( new DoubleParam( 0.0 ) );
	result.setStatErrHigh( new DoubleParam( 0.0 ) );

	return result;
    }

    /**
     *  Create Characterization object and populate attributes with values.
     *  @return  The completed Characterization object
     */
    static public Characterization createCharacterization()
    {
	Characterization result = new Characterization();
	
	/* Create populated Characterization object and assign to Segment. */
	result.setSpectralAxis( createCharSpecAxis() );
	result.setTimeAxis( createCharTimeAxis() );
	result.setSpatialAxis( createCharSpatialAxis() );
	result.setFluxAxis( createCharFluxAxis() );

	java.util.List<CharacterizationAxis> axis = result.createCharacterizationAxis();
	for (int ii=1; ii < 4; ii++ )
	{
	    axis.add( createCharAxis( "AXIS"+ii, "TBD", null ) );
	}

	return result;
    }

    /**
     *  Create CharacterizationAxis object and populate attributes with values.
     *  @return  The completed CharacterizationAxis object
     */
    static public CharacterizationAxis createCharAxis( String name, String ucd, String unit )
    {
	CharacterizationAxis result = new CharacterizationAxis();

	result.setName( name );
	result.setUcd( ucd );
	result.setUnit( unit );

	result.setResolution( new DoubleParam( 0.0 ) );
	result.setCalibration( new TextParam( "TBD" ) );

	result.setAccuracy( createAccuracy() );
	result.setSamplingPrecision( createSamplingPrecision() );

	/* Skip Coverage in this test (has its own) */
	/* Skip Coordsys in this test (has its own) */
	
	return result;
    }
    static public CharacterizationAxis createCharFluxAxis()
    {
	CharacterizationAxis result = createCharAxis( "Flux", "TBD", null );

	return result;
    }
    static public CharacterizationAxis createCharSpatialAxis()
    {
	CharacterizationAxis result = createCharAxis( "Sky", "pos.eq", "deg" );

	return result;
    }
    static public CharacterizationAxis createCharTimeAxis()
    {
	CharacterizationAxis result = createCharAxis( "Time", "time", "s" );
	
	return result;
    }
    static public SpectralCharacterizationAxis createCharSpecAxis()
    {
	SpectralCharacterizationAxis result = new SpectralCharacterizationAxis();

	result.setName( "Wavelength" );
	result.setUcd( "em.wl" );
	result.setUnit( "Angstrom" );

	result.setResolution( new DoubleParam( 0.0 ) );
	result.setCalibration( new TextParam( "TBD" ) );
	result.setResPower( new DoubleParam( 0.0 ) );

	result.setAccuracy( createAccuracy() );
	result.setSamplingPrecision( createSamplingPrecision() );

	/* Skip Coverage in this test (has its own) */
	/* Skip Coordsys in this test (has its own) */
	
	return result;
    }

    /**
     *  Create Contact object and populate attributes with values.
     *  @return  The completed Contact object
     */
    static public Contact createContact()
    {
	Contact contact = new Contact();

	contact.setName( new TextParam( "Jonathan McDowell", "Contact", "meta.human;meta.curation" ) );
	contact.setEmail( new TextParam( "jcm@cfa.harvard.edu", "email", "meta.email" ) );

	return contact;
    }

    /**
     *  Create CoordFrame object and populate attributes with values.
     *  @return  The completed CoordFrame object
     */
    static public CoordFrame createCoordFrame()
    {
	CoordFrame result = new CoordFrame();

	result.setId( "TBD" );
	result.setReferencePosition( "TBD" );
	result.setUcd( "TBD" );
	result.setName( "TBD" );

	return result;
    }
    static public RedshiftFrame createRedshiftFrame()
    {
	RedshiftFrame result = new RedshiftFrame();

	result.setId( "TBD" );
	result.setReferencePosition( "TBD" );
	result.setUcd( "TBD" );
	result.setName( "ICRS" );
	result.setDopplerDefinition( "TBD" );

	return result;
    }
    static public SpaceFrame createSpaceFrame()
    {
	SpaceFrame result = new SpaceFrame();

	result.setId( "TBD" );
	result.setReferencePosition( "TBD" );
	result.setUcd( "TBD" );
	result.setName( "ICRS" );
	result.setEquinox( new DoubleParam(2000.0, "Equinox", "time.equinox;pos.eq", null) );

	return result;
    }
    static public SpectralFrame createSpectralFrame()
    {
	SpectralFrame result = new SpectralFrame();

	result.setId( "TBD" );
	result.setReferencePosition( "BARYCENTER" );
	result.setUcd( "TBD" );
	result.setName( "TBD" );
	result.setRedshift( new DoubleParam( 0.0 ) );

	return result;
    }
    static public TimeFrame createTimeFrame()
    {
	TimeFrame result = new TimeFrame();

	result.setName( "UTC" );
	result.setId( "TBD" );
	result.setReferencePosition( "TBD" );
	result.setUcd( "TBD" );
	result.setZero( new DoubleParam(0.0) );

	return result;
    }

    /**
     *  Create CoordSys object and populate attributes with values.
     *  @return  The completed CoordSys object
     */
    static public CoordSys createCoordSys()
    {
	CoordSys  result = new CoordSys();

	result.setId( "TBD" );

        result.createCoordFrame().clear();
        result.getCoordFrame().add( createTimeFrame() );
        result.getCoordFrame().add( createSpaceFrame() );
        result.getCoordFrame().add( createSpectralFrame() );
        result.getCoordFrame().add( createRedshiftFrame() );
        result.getCoordFrame().add( createCoordFrame() );

	return result;
    }

    /**
     *  Create Coverage object and populate attributes with values.
     *  @return  The completed Coverage object
     */
    static public Coverage createCoverage()
    {
	Coverage result = new Coverage();
	Interval interval = null;

	/* MCD NOTE: TBD
	 *   I have removed the units fields from BOUNDS.EXTENT, BOUNDS.RANGE, and SUPPORT.EXTENT
	 *   settings because the FITS deserialization does not read them (so they are not transferred),
	 *   even though it writes them just fine.
	 */

	result.setBounds( new CoverageBounds() );
	result.getBounds().setExtent( new DoubleParam( 1500.0, "TimeExtent", "time.duration", null ));
	interval = new Interval();
	interval.setMin( new DoubleParam( 52100.0, "TimeStart", "time", null ) );
	interval.setMax( new DoubleParam( 52300.0, "TimeStop", "time", null ) );
	result.getBounds().setRange( interval );

	result.setLocation( new CoverageLocation() );
        DoubleParam[] d = new DoubleParam[2];
        d[0] = new DoubleParam( 52148.3252, "TimeObs", "time.obs", null );
        result.getLocation().setValue(d);
	result.getLocation().setResolution( new DoubleParam( 0.125, "TimeRes", "TBD", "s" ) );
	result.getLocation().setAccuracy( createAccuracy() );

	result.setSupport( new CoverageSupport() );
	result.getSupport().setExtent(new DoubleParam( 1500.0, "TimeExtent", "time.duration;obs.exposure", null ));
	SkyRegion skyRegion = new SkyRegion();
	skyRegion.setName( "TBD" );
	skyRegion.setValue( "TBD" );
	skyRegion.setUcd( "TBD" );
	result.getSupport().setArea( skyRegion );
	
	interval = new Interval();
	interval.setMin( new DoubleParam( 52100.0, "TimeStart", "time", "s" ) );
	interval.setMax( new DoubleParam( 52300.0, "TimeStop", "time", "s" ) );
	java.util.List<Interval> range = result.getSupport().createRange();
	range.add( interval );
	
	return result;
    }
    static public Coverage createFluxCoverage()
    {
	Coverage result = new Coverage();

	result.setBounds( new CoverageBounds() );
	result.setLocation( new CoverageLocation() );
	result.getLocation().setResolution( new DoubleParam( 3.88 ) );
        DoubleParam[] d = new DoubleParam[2];
        d[0] = new DoubleParam( 3.61 );
        d[1] = new DoubleParam( 3.62 );
        result.getLocation().setValue(d);

	result.setSupport( new CoverageSupport() );	
	result.getSupport().setExtent( new DoubleParam( 3.8 ));
	SkyRegion skyRegion = new SkyRegion();
	skyRegion.setValue( "This is the #3 sky region" );
	result.getSupport().setArea( skyRegion );
	
	Interval interval = new Interval();
	interval.setMin( new DoubleParam( 3.02 ) );
	interval.setMax( new DoubleParam( 3.22 ) );
	java.util.List<Interval> range = result.getSupport().createRange();
	range.add( interval );
	
	return result;
    }
    static public Coverage createSpectralCoverage()
    {
	Coverage result = new Coverage();

	result.setBounds( new CoverageBounds() );

	result.setLocation( new CoverageLocation() );
        DoubleParam[] d = new DoubleParam[2];
        d[0] = new DoubleParam( 1.61 );
        d[1] = new DoubleParam( 1.62 );
        result.getLocation().setValue(d);
	result.getLocation().setResolution( new DoubleParam( 1.88 ) );
	
	result.setSupport( new CoverageSupport() );
	result.getSupport().setExtent( new DoubleParam( 1.8 ));
	SkyRegion skyRegion = new SkyRegion();
	skyRegion.setValue( "This is the #1 sky region" );
	result.getSupport().setArea( skyRegion );
	
	Interval interval = new Interval();
	interval.setMin( new DoubleParam( 1.02 ) );
	interval.setMax( new DoubleParam( 1.22 ) );
	java.util.List<Interval> range = result.getSupport().createRange();
	range.add( interval );
	
	return result;
    }
    static public Coverage createSpatialCoverage()
    {
	Coverage result = new Coverage();

	result.setBounds( new CoverageBounds() );

	result.setLocation( new CoverageLocation() );
        DoubleParam[] d = new DoubleParam[2];
        d[0] = new DoubleParam( 4.61 );
        d[1] = new DoubleParam( 4.62 );
        result.getLocation().setValue(d);
	result.getLocation().setResolution( new DoubleParam( 4.88 ) );
	
	result.setSupport( new CoverageSupport() );
	result.getSupport().setExtent( new DoubleParam( 4.8 ));
	SkyRegion skyRegion = new SkyRegion();
	skyRegion.setValue( "This is the #4 sky region" );
	result.getSupport().setArea( skyRegion );
	
	Interval interval = new Interval();
	interval.setMin( new DoubleParam( 4.02 ) );
	interval.setMax( new DoubleParam( 4.22 ) );
	java.util.List<Interval> range = result.getSupport().createRange();
	range.add( interval );
	
	return result;
    }

    /**
     *  Create Curation object and populate attributes with values.
     *  @return  The completed Curation object
     */
    static public Curation createCuration()
    {
	Curation curation = new Curation();

	curation.setPublisher( new TextParam( "SAO", "Publisher", "meta.organization;meta.curation" ) );
        curation.setPublisherID( new TextParam( "ivo://cfa.harvard.edu", "PubID", "meta.curation.pubid" ) );
	curation.setReference( new TextParam( "TBD", "Reference", "meta.bib.bibcode" ) );
	curation.setVersion( new TextParam( "TBD" ) );
	curation.setRights( new TextParam( "TBD" ) );
	curation.setDate( new DateParam( "2003-12-31T14:00:02" ) );
	curation.setPublisherDID( new TextParam( "TBD" ) );

	curation.setContact( CompleteObjects.createContact() );

	return curation;
    }

    /**
     *  Create DataID object and populate attributes with values.
     *  @return  The completed DataID object
     */
    static public DataID createDataID()
    {
	DataID result = new DataID();

	result.setTitle( new TextParam( "Arp 220 SED", "Title", null ) );
	result.setCreator( new TextParam( "ivo://sao/FLWO", "Creator", "meta.curation.creator" ) );
	result.setDatasetID( new TextParam( "TBD" ) );
	result.setDate( new DateParam( "2003-12-31T14:00:02Z", "DataDate", "time;soft.dataset;meta.curation" ) );
	result.setVersion( new TextParam( "1", "Version", "soft.dataset.version;meta.curation" ) );
	result.setInstrument( new TextParam( "BCS", "Instrument", "inst.id" ) );
	result.setCreationType( new TextParam( "Archival", "CreationType", null ) );
	result.setBandpass( new TextParam( "TBD" ) );
	result.setCreatorDID( new TextParam( "TBD" ) );
	result.setLogo( new TextParam( "http://cfa-www.harvard.edu/nvo/cfalogo.jpg", "Logo", "meta.curation.logo" ) );
	result.setDataSource( new TextParam( "TBD" ) );

	/* Collection and Contributor are List members. */
	result.createCollection().add( new TextParam( "G300", "Filter", "inst.filter.id" ) );
	result.createContributor().add( new TextParam( "TBD" ) );

	return result;
    }

    /**
     *  Create Derived object and populate attributes with values.
     *  @return  The completed Derived object
     */
    static public DerivedData createDerived()
    {
	DerivedData result = new DerivedData();

	result.setSNR( new DoubleParam( 3.0 ) );
	result.setVarAmpl( new DoubleParam( 0.0 ) );

	result.setRedshift( new SedQuantity() );
	result.getRedshift().setValue( new DoubleParam( 0.0 ) );
	result.getRedshift().setResolution( new DoubleParam( 0.0 ) );
	result.getRedshift().setQuality( new IntParam( 0 ) );

	result.getRedshift().setAccuracy( createAccuracy() );

	return result;
    }

    /**
     *  Create ArrayOfParam with fully populated set of Param object
     *  including all flavors of Param.
     *  @return  The completed ArrayOfParam 
     */
    static public List<Param> createParams()
    {
	List<Param> result = new Vector<Param>();
        PositionParam pos = new PositionParam ();
        DoubleParam[] d = new DoubleParam[2];
        d[0] = new DoubleParam( 233.737917, "TargetPos", null, "deg" );
        d[1] = new DoubleParam( 23.503330, "TargetPos", null, "deg" );
        pos.setValue(d);

	result.add( new DoubleParam( 3.14159, "pi", "TBD", "TBD") );
	result.add( new IntParam( 7, "daysofweek", "TBD", "TBD") );
	result.add( new TextParam( "Galaxy", "TargetType", "TBD" ) );
	result.add( new DateParam( "2011-01-04T22:01:59", "camdate", "TBD" ) );
	result.add( new TimeParam(  "1.8", "age", "TBD", "Gyr" ) );

	// PositionParam does not extend Param.. but is a list of DoubleParam
	result.add( pos.getValue ()[0] );
        result.add( pos.getValue ()[1] );

	return result;
    }

    /**
     *  Create Point object and populate attributes with values.
     *  @return  The completed Point object
     */
    static public Point createPoint(int seed )
    {
	Point point = new Point();

	point.setTimeAxis( new SedCoord() );
	point.setFluxAxis( new SedQuantity() );
	point.setSpectralAxis( new SedCoord() );
	point.setBackgroundModel( new SedQuantity() );

	point.getFluxAxis().setAccuracy( new Accuracy() );
	point.getTimeAxis().setAccuracy( new Accuracy() );
	point.getSpectralAxis().setAccuracy( new Accuracy() );
	point.getBackgroundModel().setAccuracy( new Accuracy() );

	// Fill out Time axis values (no quality)
	point.getTimeAxis().setValue( new DoubleParam( seed + .101, "timeValue", "timeValueUcd", "timeValueUnit" ) );
	point.getTimeAxis().setResolution( new DoubleParam( seed + .102, "timeResol", "timeResolUcd", "timeResolUnit" ) );

	point.getTimeAxis().getAccuracy().setSysError(   new DoubleParam( seed + .103, "timeSysErr" , "TBD", "TBD" ) );
	point.getTimeAxis().getAccuracy().setConfidence( new DoubleParam( seed + .104, "timeConf"   , "TBD", "TBD" ) );
	point.getTimeAxis().getAccuracy().setStatError(  new DoubleParam( seed + .105, "timeStatErr", "TBD", "TBD" ) );
	point.getTimeAxis().getAccuracy().setStatErrLow( new DoubleParam( seed + .106, "timeErrLow" , "TBD", "TBD" ) );
	point.getTimeAxis().getAccuracy().setStatErrHigh(new DoubleParam( seed + .107, "timeErrHigh", "TBD", "TBD" ) );
	point.getTimeAxis().getAccuracy().setBinLow(     new DoubleParam( seed + .108, "timeBinLow" , "TBD", "TBD" ) );
	point.getTimeAxis().getAccuracy().setBinHigh(    new DoubleParam( seed + .109, "timeBinHigh", "TBD", "TBD" ) );
	point.getTimeAxis().getAccuracy().setBinSize(    new DoubleParam( seed + .110, "timeBinSize", "TBD", "TBD" ) );

	// Fill out Flux axis values (SedQuantity)
	point.getFluxAxis().setValue( new DoubleParam( seed + .201, "fluxValue", "TBD", "TBD" ) );
	point.getFluxAxis().setResolution( new DoubleParam( seed + .202, "fluxResol", "TBD", "TBD" ) );
	point.getFluxAxis().setQuality( new IntParam( seed, "fluxQual", "TBD", "TBD" ) );

	point.getFluxAxis().getAccuracy().setSysError(   new DoubleParam( seed + .203, "fluxSysErr" , "TBD", "TBD" ) );
	point.getFluxAxis().getAccuracy().setConfidence( new DoubleParam( seed + .204, "fluxConf"   , "TBD", "TBD" ) );
	point.getFluxAxis().getAccuracy().setStatError(  new DoubleParam( seed + .205, "fluxStatErr", "TBD", "TBD" ) );
	point.getFluxAxis().getAccuracy().setStatErrLow( new DoubleParam( seed + .206, "fluxErrLow" , "TBD", "TBD" ) );
	point.getFluxAxis().getAccuracy().setStatErrHigh(new DoubleParam( seed + .207, "fluxErrHigh", "TBD", "TBD" ) );
	point.getFluxAxis().getAccuracy().setBinLow(     new DoubleParam( seed + .208, "fluxBinLow" , "TBD", "TBD" ) );
	point.getFluxAxis().getAccuracy().setBinHigh(    new DoubleParam( seed + .209, "fluxBinHigh", "TBD", "TBD" ) );
	point.getFluxAxis().getAccuracy().setBinSize(    new DoubleParam( seed + .210, "fluxBinSize", "TBD", "TBD" ) );

	// Fill out Spectral axis values (no quality)
	point.getSpectralAxis().setValue( new DoubleParam( seed + .301, "spectralValue", "TBD", "TBD" ) );
	point.getSpectralAxis().setResolution( new DoubleParam( seed + .302, "spectralResol", "TBD", "TBD" ) );

	point.getSpectralAxis().getAccuracy().setSysError(   new DoubleParam( seed + .303, "spectralSysErr" , "TBD", "TBD" ) );
	point.getSpectralAxis().getAccuracy().setConfidence( new DoubleParam( seed + .304, "spectralConf"   , "TBD", "TBD" ) );
	point.getSpectralAxis().getAccuracy().setStatError(  new DoubleParam( seed + .305, "spectralStatErr", "TBD", "TBD" ) );
	point.getSpectralAxis().getAccuracy().setStatErrLow( new DoubleParam( seed + .306, "spectralErrLow" , "TBD", "TBD" ) );
	point.getSpectralAxis().getAccuracy().setStatErrHigh(new DoubleParam( seed + .307, "spectralErrHigh", "TBD", "TBD" ) );
	point.getSpectralAxis().getAccuracy().setBinLow(     new DoubleParam( seed + .308, "spectralBinLow" , "TBD", "TBD" ) );
	point.getSpectralAxis().getAccuracy().setBinHigh(    new DoubleParam( seed + .309, "spectralBinHigh", "TBD", "TBD" ) );
	point.getSpectralAxis().getAccuracy().setBinSize(    new DoubleParam( seed + .310, "spectralBinSize", "TBD", "TBD" ) );

	// Fill out BackgroundModel values
	point.getBackgroundModel().setValue( new DoubleParam( seed + .401, "bkgValue", "TBD", "TBD" ) );
	point.getBackgroundModel().setResolution( new DoubleParam( seed + .402, "bkgResol", "TBD", "TBD" ) );
	point.getBackgroundModel().setQuality( new IntParam( seed, "bkgQual", "TBD", "TBD" ) );

	point.getBackgroundModel().getAccuracy().setSysError(   new DoubleParam( seed + .403, "bkgSysErr" , "TBD", "TBD" ) );
	point.getBackgroundModel().getAccuracy().setConfidence( new DoubleParam( seed + .404, "bkgConf"   , "TBD", "TBD" ) );
	point.getBackgroundModel().getAccuracy().setStatError(  new DoubleParam( seed + .405, "bkgStatErr", "TBD", "TBD" ) );
	point.getBackgroundModel().getAccuracy().setStatErrLow( new DoubleParam( seed + .406, "bkgErrLow" , "TBD", "TBD" ) );
	point.getBackgroundModel().getAccuracy().setStatErrHigh(new DoubleParam( seed + .407, "bkgErrHigh", "TBD", "TBD" ) );
	point.getBackgroundModel().getAccuracy().setBinLow(     new DoubleParam( seed + .408, "bkgBinLow" , "TBD", "TBD" ) );
	point.getBackgroundModel().getAccuracy().setBinHigh(    new DoubleParam( seed + .409, "bkgBinHigh", "TBD", "TBD" ) );
	point.getBackgroundModel().getAccuracy().setBinSize(    new DoubleParam( seed + .410, "bkgBinSize", "TBD", "TBD" ) );
		
	return point;
    }

    /**
     *  Create SamplingPrecision object and populate attributes with values.
     *  @return  The completed SamplingPrecision object
     */
    static public SamplingPrecision createSamplingPrecision()
    {
	SamplingPrecision result = new SamplingPrecision();

	result.setSampleExtent( new DoubleParam( 0.0 ) );

	result.setSamplingPrecisionRefVal( new SamplingPrecisionRefVal() );
	result.getSamplingPrecisionRefVal().setFillFactor( new DoubleParam( 0.0 ) );

	return result;
    }


    /**
     *  Create Spectrum object and populate attributes with values.
     *  This test pretty much assembles the parts of other Test*IO
     *  tests and puts them all together to test how the components
     *  work as a whole.
     *  @return  The completed Spectrum object
     */
    static public Spectrum createSpectrum()
    {
	Spectrum result = new Spectrum();
	int npoints = 3;  // Number of data points in Spectrum

	result.setType( new TextParam( "Spectrum" ) );
	result.setTimeSI( new TextParam( "ms" ) );
	result.setSpectralSI( new TextParam( "Angstrom" ) );
	result.setFluxSI( new TextParam( "ergs/m-2s-1Mev-1" ) );

	result.setTarget( CompleteObjects.createTarget() );

	result.setChar( CompleteObjects.createCharacterization() );
	result.getChar().getTimeAxis().setCoverage( CompleteObjects.createCoverage() );
	result.getChar().getFluxAxis().setCoverage( CompleteObjects.createFluxCoverage() );
  	result.getChar().getSpectralAxis().setCoverage( CompleteObjects.createSpectralCoverage() );
	result.getChar().getSpatialAxis().setCoverage( CompleteObjects.createSpatialCoverage() );

	result.setCoordSys( CompleteObjects.createCoordSys() );
	result.setCuration( CompleteObjects.createCuration() );
	result.setDataID( CompleteObjects.createDataID() );
	result.setDerived( CompleteObjects.createDerived() );
	result.setCustomParams( CompleteObjects.createParams() );

	/* Create a List of Point objects with values..  */
	/* Assign to Segment                                 */
        ArrayOfPoint pointData = new ArrayOfPoint ();
	List<Point> data = pointData.createPoint ();
	for ( int ii = 0; ii < npoints; ii++ )
	{
	    data.add( CompleteObjects.createPoint( ii ) );
	}
	result.setData (pointData);


	return result;
    }

    /**
     *  Create Target object and populate attributes with values.
     *  @return  The completed Target object
     */
    static public Target createTarget()
    {
	Target target = new Target();
        PositionParam pos = new PositionParam ();
        pos.createValue ()[0] = new DoubleParam (233.737917, "TargetPos", null, "deg");
        pos.getValue ()[1] = new DoubleParam (23.503330, "TargetPos", null, "deg");

	target.setName( new TextParam( "Arp 220" ) );
	target.setDescription( new TextParam( "Ultraluminous Infrared Galaxy" ) );
	target.setTargetClass( new TextParam( "TBD" ) );
	target.setSpectralClass( new TextParam( "TBD" ) );
	target.setRedshift( new DoubleParam( 0.0018 ) );
	target.setPos( pos );
	target.setVarAmpl( new DoubleParam( 0.0 ) );

	/* Skip CustomParams in this test (has its own) */

	return target;
    }
}
