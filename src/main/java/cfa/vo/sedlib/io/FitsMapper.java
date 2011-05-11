/***********************************************************************
*
* File: io/FitsMapper.java
*
* Author:  jmiller              Created: Fri Nov 12 12:26:00 EST 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.HeaderCard;
import nom.tam.fits.TableHDU;
import nom.tam.util.Cursor;
import cfa.vo.sedlib.ArrayOfPoint;
import cfa.vo.sedlib.CharacterizationAxis;
import cfa.vo.sedlib.CoordFrame;
import cfa.vo.sedlib.DateParam;
import cfa.vo.sedlib.DoubleParam;
import cfa.vo.sedlib.IntParam;
import cfa.vo.sedlib.Param;
import cfa.vo.sedlib.Point;
import cfa.vo.sedlib.PositionParam;
import cfa.vo.sedlib.RedshiftFrame;
import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.SedCoord;
import cfa.vo.sedlib.SedQuantity;
import cfa.vo.sedlib.Segment;
import cfa.vo.sedlib.SkyRegion;
import cfa.vo.sedlib.SpaceFrame;
import cfa.vo.sedlib.SpectralFrame;
import cfa.vo.sedlib.TextParam;
import cfa.vo.sedlib.TimeFrame;
import cfa.vo.sedlib.common.FitsKeywords;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedParsingException;
import cfa.vo.sedlib.common.ValidationError;
import java.io.IOException;

/**
Maps Sed objects to Fits objects
*/
public class FitsMapper extends SedMapper
{
    static final Logger logger = Logger.getLogger ("cfa.vo.sedlib");
    enum ParamInfoKeys {
    	INVALID (""),
        UCD ("^TUCD[1-9][0-9]{0,3}$"),
        NAME ("^TTYPE[1-9][0-9]{0,2}$"),
        UTYPE ("^TUTYP[1-9][0-9]{0,2}$"),
        UNIT ("^TUNIT[1-9][0-9]{0,2}$"),
        FORM ("^TFORM[1-9][0-9]{0,2}$");

        String pattern;
        static int size = values ().length;

        ParamInfoKeys (String pattern) {this.pattern = pattern;};
    }

        


    static private final ArrayList<String> fitsKeys = new ArrayList<String>();
    static
    {
        fitsKeys.add( "XTENSION" );
        fitsKeys.add( "BITPIX" );
        fitsKeys.add( "NAXIS" );
        fitsKeys.add( "NAXIS1" );
        fitsKeys.add( "NAXIS2" );
        fitsKeys.add( "PCOUNT" );
        fitsKeys.add( "GCOUNT" );
        fitsKeys.add( "TFIELDS" );
    }


    /**
      * Populates a Sed object with information from a Fits object.
      *
      * @param data
      *    {@link Fits}
      * @return 
      *    {@link Sed}
      * @throws SedException
      */
    public Sed populateSed (Object data, Sed sed) throws SedParsingException, SedInconsistentException, IOException, SedNoDataException
    {
        Segment segment;
        BasicHDU[] hdus;
        List<ValidationError> validationErrors = new ArrayList <ValidationError> ();

        if (sed == null)
           sed = new Sed ();

        Fits fits = (Fits)data;

        try
        {
            hdus = fits.read();

            if(hdus==null)
                throw new SedParsingException("The fits file has no HDUs?");

            // hdus[0] is Primary Array.
            // Loop through rest of HDU's for each segment.
            for (int ii=1; ii<hdus.length; ii++)
            {
                // Use HDU object to get data.
                // Use Header object to get keywords/column names and types.
                segment = this.extractSegmentFromTable ((TableHDU)hdus[ii]);
                sed.addSegment (segment);
            }
        }
        catch (FitsException exp) {
            if(exp.getMessage().contains("java.io.IOException")
                    && !exp.getMessage().contains("Not FITS format"))
                        throw new IOException(exp.getMessage ());

            throw new SedParsingException (exp.getMessage(), exp);
        }

        if (!sed.validate (validationErrors))
        {
            logger.warning("Invalid Sed read.");
            for (ValidationError error : validationErrors)
                logger.warning(error.getErrorMessage ());
        }

        return sed;
    }


    /**
     * Extract a segment from an hdu
     * @param table
     *    {@link TableHDU}
     * @return
     *    {@link Segment}
     */
    private Segment extractSegmentFromTable(TableHDU table) throws SedParsingException
    {
        Segment segment;
        Header header = table.getHeader();

        segment = new Segment ();

        // load data into the segment
        this.loadDataIntoSegment (table, segment);

        // load metadata into the semgent
        this.loadFitsKeywordsIntoSegments (header, segment);

        // match data and metadata 
        this.matchMetaDataWithData (segment);

        return segment;
    }


    /**
     *  Loops over all list of FITS keywords and for those with values in
     *  document, loads the values into the appropriate data object.
     */
    private void loadFitsKeywordsIntoSegments(
                                        Header header,
                                        Segment segment ) throws SedParsingException
    {
        Cursor cursor = header.iterator();
        String value, key;
        String unit;

        while (cursor.hasNext())
        {
            HeaderCard card = (HeaderCard) cursor.next();

            key = card.getKey();

            // ignore comments
            if ( key.compareTo("END")==0 )
                continue;

            // ignore keywords for data and fits specific
            // keywords
            if ((this.getDataKeyword (key) != ParamInfoKeys.INVALID) ||
                fitsKeys.contains (key))
                continue;

            value = card.getValue();
            unit = this.getUnitFromComment (card.getComment ());

            Integer enumKeys[] = FitsKeywords.getUtypeFromKeyword( key );

            // in some cases the same keyword can map to multiple
            // utypes. map keyword values to all utypes
            for (int enumKey : enumKeys)
            {
                switch ( enumKey )
                {
                    case FitsKeywords.DATAMODEL:
                        break; // already set
                    case FitsKeywords.LENGTH:
                        break; // this is the number of points
                    case FitsKeywords.TYPE:
                        segment.setType (new TextParam (value));
                        break;
                    case FitsKeywords.TARGET_SPECTRALCLASS:
                        segment.createTarget ().setSpectralClass (new TextParam (value));
                        break;
                    case FitsKeywords.TARGET_REDSHIFT:
                        segment.createTarget ().setRedshift (new DoubleParam (value, null, null, unit, null));
                        break;
                    case FitsKeywords.TARGET_NAME:
                        segment.createTarget ().setName (new TextParam (value));
                        break;
                    case FitsKeywords.TARGET_DESCRIPTION:
                        segment.createTarget ().setDescription (new TextParam (value));
                        break;
                    case FitsKeywords.TARGET_CLASS:
                        segment.createTarget ().setTargetClass (new TextParam (value));
                        break;
                    case FitsKeywords.TARGET_VARAMPL:
                        segment.createTarget ().setVarAmpl (new DoubleParam (value, null, null, unit, null));
                        break;
                    case FitsKeywords.TARGET_RA:
                    {
                        PositionParam pos = segment.createTarget ().createPos();
                        DoubleParam targetPos[] = pos.createValue ();
    
                        // ensure ra is added in the right place
                        targetPos[0] = new DoubleParam (value, null, null, unit, null);
                        segment.getTarget().setPos(pos);
                        break;
                    }
                    case FitsKeywords.TARGET_DECL:
                    {
                        PositionParam pos = segment.createTarget ().createPos ();
                        DoubleParam targetPos[] = pos.createValue ();

                        // ensure dec is added after ra
                        targetPos[1] = new DoubleParam (value, null, null, unit, null);
                        segment.getTarget().setPos(pos);
                        break;
                    }

                    // Segment level cases
                    case FitsKeywords.TIMESI:
                        segment.setTimeSI( new TextParam( value) );
                        break;
    
                    case FitsKeywords.FLUXSI:
                        segment.setFluxSI( new TextParam( value) );
                        break;
    
                    case FitsKeywords.SPECTRALSI:
                        segment.setSpectralSI( new TextParam( value) );
                        break;
    
                    // CoordSys cases
                    case FitsKeywords.SEG_CS_SPECTRALFRAME_REFPOS:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_SPECTRALFRAME);

                        coordFrame.setReferencePosition( value );
                        break;
                    }
                    case FitsKeywords.SEG_CS_SPECTRALFRAME_REDSHIFT:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_SPECTRALFRAME);

                        ((SpectralFrame)coordFrame).setRedshift (new DoubleParam(value, null, null, unit, null));
                        break;
                    }
                    case FitsKeywords.SEG_CS_SPECTRALFRAME_NAME:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_SPECTRALFRAME);
                        coordFrame.setName (value);
                        break;
                    }

                    case FitsKeywords.SEG_CS_SPECTRALFRAME_UCD:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_SPECTRALFRAME);
                        coordFrame.setUcd (value);
                        break;
                    }

/*                    case FitsKeywords.SEG_CS_FLUXFRAME_NAME:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_FLUXFRAME);
                        coordFrame.setName (value);
                        break;
                    }

                    case FitsKeywords.SEG_CS_FLUXFRAME_UCD:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_FLUXFRAME);
                        coordFrame.setUcd (value);
                        break;
                    }
                    case FitsKeywords.SEG_CS_FLUXFRAME_ID:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_FLUXFRAME);
                        coordFrame.setId (value);
                        break;
                    }

*/


                    case FitsKeywords.SEG_CS_SPACEFRAME_EQUINOX:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_SPACEFRAME);
                        ((SpaceFrame)coordFrame).setEquinox (new DoubleParam (value, null, null, unit, null));
                        break;
                    }

                    case FitsKeywords.SEG_CS_SPACEFRAME_NAME:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_SPACEFRAME);
                        coordFrame.setName (value);
                        break;
                    }

                    case FitsKeywords.SEG_CS_SPACEFRAME_UCD:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_SPACEFRAME);
                        coordFrame.setUcd (value);
                        break;
                    }
                    case FitsKeywords.SEG_CS_SPACEFRAME_REFPOS:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_SPACEFRAME);
                        coordFrame.setReferencePosition (value);
                        break;
                    }

                    case FitsKeywords.SEG_CS_TIMEFRAME_ZERO:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_TIMEFRAME);
                        ((TimeFrame)coordFrame).setZero (new DoubleParam (value, null, null, unit, null));
                        break;
                    }
                    case FitsKeywords.SEG_CS_TIMEFRAME_NAME:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_TIMEFRAME);
                        coordFrame.setName (value);
                        break;
                    }
                    case FitsKeywords.SEG_CS_REDFRAME_NAME:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_REDFRAME);
                        coordFrame.setName (value);
                        break;
                    }
                    case FitsKeywords.SEG_CS_REDFRAME_REFPOS:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_REDFRAME);
                        coordFrame.setReferencePosition (value);
                        break;
                    }
                    case FitsKeywords.SEG_CS_REDFRAME_DOPPLERDEF:
                    {
                        CoordFrame coordFrame = this.getCoordFrame (
                                                  segment.createCoordSys ().createCoordFrame (),
                                                  FitsKeywords.SEG_CS_REDFRAME);
                        ((RedshiftFrame)coordFrame).setDopplerDefinition (value);
                        break;
                    }


                    case FitsKeywords.SEG_CS_ID:
                        segment.createCoordSys().setId( value );
                        break;

                    //
                    // DataID cases (Contributor and Collection are indexed and handled above)
                    //

                    case FitsKeywords.SEG_DATAID_TITLE:
                        segment.createDataID().setTitle( new TextParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_CREATOR:
                        segment.createDataID().setCreator( new TextParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_DATASETID:
                        segment.createDataID().setDatasetID( new TextParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_CREATORDID:
                        segment.createDataID().setCreatorDID( new TextParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_DATE:
                        segment.createDataID().setDate( new DateParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_VERSION:
                        segment.createDataID().setVersion( new TextParam( value ) );
                        break;
                    case FitsKeywords.SEG_DATAID_INSTRUMENT:
                        segment.createDataID().setInstrument( new TextParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_CREATIONTYPE:
                        segment.createDataID().setCreationType( new TextParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_LOGO:
                        segment.createDataID().setLogo( new TextParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_DATASOURCE:
                        segment.createDataID().setDataSource( new TextParam( value ) );
                        break;

                    case FitsKeywords.SEG_DATAID_BANDPASS:
                        segment.createDataID().setBandpass( new TextParam( value ) );
                        break;
                    case FitsKeywords.SEG_DATAID_COLLECTION:
                        segment.createDataID().createCollection().add (new TextParam(value));
                        break;
                    case FitsKeywords.SEG_DATAID_CONTRIBUTOR:
                        segment.createDataID().createContributor().add (new TextParam(value));
                        break;


                    //
                    // Curation cases
                    //
                    case FitsKeywords.SEG_CURATION_PUBLISHER:
                        segment.createCuration().setPublisher( new TextParam( value ) );
                    break;

                    case FitsKeywords.SEG_CURATION_REF:
                        segment.createCuration().setReference( new TextParam( value ) );
                    break;

                    case FitsKeywords.SEG_CURATION_PUBID:
                        segment.createCuration().setPublisherID( new TextParam( value ) );
                    break;

                    case FitsKeywords.SEG_CURATION_PUBDID:
                        segment.createCuration().setPublisherDID( new TextParam( value ) );
                    break;
    
                    case FitsKeywords.SEG_CURATION_VERSION:
                        segment.createCuration().setVersion( new TextParam( value ) );
                    break;
    
                    case FitsKeywords.SEG_CURATION_CONTACT_NAME:
                        segment.createCuration().createContact().setName( new TextParam( value ) );
                    break;
    
                    case FitsKeywords.SEG_CURATION_CONTACT_EMAIL:
                        segment.createCuration().createContact().setEmail( new TextParam( value ) );
                    break;

                    case FitsKeywords.SEG_CURATION_RIGHTS:
                        segment.createCuration().setRights( new TextParam( value ) );
                    break;

                    case FitsKeywords.SEG_CURATION_DATE:
                        segment.createCuration().setDate( new DateParam( value ) );
                    break;


                    //
                    // Derived cases
                    //
                    case FitsKeywords.SEG_DD_SNR:
                        segment.createDerived().setSNR( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_DD_REDSHIFT_VALUE:
                        segment.createDerived().createRedshift().setValue( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_DD_REDSHIFT_ACC_STATERR:
                        segment.createDerived().createRedshift().createAccuracy().setStatError( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_DD_REDSHIFT_ACC_CONFIDENCE:
                        segment.createDerived().createRedshift().createAccuracy().setConfidence( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_DD_VARAMPL:
                        segment.createDerived().setVarAmpl( new DoubleParam( value, null, null, unit , null) );
                    break;


                    //
                    // Characterization Flux Axis cases
                    //
                    case FitsKeywords.SEG_CHAR_FLUXAXIS_CAL:
                        segment.createChar().createFluxAxis().setCalibration( new TextParam( value ) ) ;
                        break;
                    case FitsKeywords.SEG_CHAR_FLUXAXIS_NAME:
                        segment.createChar().createFluxAxis().setName( value );
                        break;
                    case FitsKeywords.SEG_CHAR_FLUXAXIS_UNIT:
                        segment.createChar().createFluxAxis().setUnit( value );
                        break;
                    case FitsKeywords.SEG_CHAR_FLUXAXIS_UCD:
                        segment.createChar().createFluxAxis().setUcd( value );
                        break;
                    case FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_STATERR:
                        segment.createChar().createFluxAxis().createAccuracy ().setStatError(new DoubleParam ( value, null, null, unit , null));
                        break;
                    case FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_SYSERR:
                        segment.createChar().createFluxAxis().createAccuracy ().setSysError(new DoubleParam ( value, null, null, unit , null));
                        break;

                    //
                    // Characterization Spectral Axis cases
                    //
                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE:
                    {
                        DoubleParam valueList[] = segment.createChar ().createSpectralAxis ().createCoverage ().createLocation ().createValue();
                        if (valueList[0] == null)
                            valueList[0] = new DoubleParam( value, null, null, unit , null);
                        else
                            valueList[1] = new DoubleParam( value, null, null, unit , null);
                    }
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT:
                        segment.createChar ().createSpectralAxis ().createCoverage ().createBounds ().setExtent (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN:
                        segment.createChar ().createSpectralAxis ().createCoverage ().createBounds ().createRange ().setMin (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX:
                        segment.createChar ().createSpectralAxis ().createCoverage ().createBounds ().createRange ().setMax (new DoubleParam (value, null, null, unit, null));
                    break;
                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_EXTENT:
                        segment.createChar ().createSpectralAxis ().createCoverage ().createSupport ().setExtent (new DoubleParam (value, null, null, unit, null));
                    break;


                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                        segment.createChar ().createSpectralAxis ().createSamplingPrecision ().createSamplingPrecisionRefVal ().setFillFactor (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_ACC_BINSIZE:
                        segment.createChar ().createSpectralAxis ().createAccuracy ().setBinSize (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_ACC_STATERR:
                        segment.createChar ().createSpectralAxis ().createAccuracy ().setStatError (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_ACC_SYSERR:
                        segment.createChar ().createSpectralAxis ().createAccuracy ().setSysError (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_CAL:
                        segment.createChar ().createSpectralAxis ().setCalibration (new TextParam (value));
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_RESOLUTION:
                        segment.createChar ().createSpectralAxis ().setResolution( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_RESPOW:
                        segment.createChar ().createSpectralAxis ().setResPower( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_UNIT:
                        segment.createChar ().createSpectralAxis ().setUnit( value );
                    break;
                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_UCD:
                        segment.createChar ().createSpectralAxis ().setUcd( value );
                    break;
                    case FitsKeywords.SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPEXT:
                    	segment.createChar ().createSpectralAxis().createSamplingPrecision().setSampleExtent(new DoubleParam (value, null, null, unit, null));
                    break;


                    //
                    // Characterization Spatial Axis cases
                    //
                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_UCD:
                        segment.createChar ().createSpatialAxis ().setUcd (value);
                    break;
                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_RESOLUTION:
                        segment.createChar ().createSpatialAxis ().setResolution( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_CAL:
                        segment.createChar ().createSpatialAxis ().setCalibration( new TextParam( value ) );
                    break;

                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT:
                        segment.createChar ().createSpatialAxis ().createCoverage ().createBounds ().setExtent( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_COV_SUPPORT_AREA:
                        segment.createChar ().createSpatialAxis ().createCoverage ().createSupport ().setArea (new SkyRegion (value));
                    break;

                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_COV_SUPPORT_EXTENT:
                        segment.createChar ().createSpatialAxis ().createCoverage ().createSupport ().setExtent( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_ACC_STATERR:
                        segment.createChar ().createSpatialAxis ().createAccuracy ().setStatError( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_ACC_SYSERR:
                        segment.createChar ().createSpatialAxis ().createAccuracy ().setSysError( new DoubleParam( value, null, null, unit , null) );
                    break;

                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                        segment.createChar ().createSpatialAxis ().createSamplingPrecision ().createSamplingPrecisionRefVal ().setFillFactor (new DoubleParam (value, null, null, unit, null));
                    break;
   
                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT:
                        segment.createChar ().createSpatialAxis ().createSamplingPrecision ().setSampleExtent (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_RA:
                    {
                        DoubleParam valueList[] = segment.createChar ().createSpatialAxis ().createCoverage ().createLocation ().createValue ();
                        valueList[0] = new DoubleParam (value, null, null, unit, null); 
                    }
                    break;
                    case FitsKeywords.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_DEC:
                    {
                        DoubleParam valueList[] = 
                            segment.createChar ().createSpatialAxis ().createCoverage ().createLocation ().createValue ();
                        valueList[1] = new DoubleParam (value, null, null, unit, null);
                    }
                    break;


                    //
                    // Characterization Time Axis cases
                    //
                    case FitsKeywords.SEG_CHAR_TIMEAXIS_UNIT:
                        segment.createChar ().createTimeAxis ().setUnit( value );
                    break;

                    case FitsKeywords.SEG_CHAR_TIMEAXIS_ACC_BINSIZE:
                        segment.createChar ().createTimeAxis ().createAccuracy ().setBinSize( new DoubleParam( value, null, null, unit, null ) );
                    break;
                    
                    case FitsKeywords.SEG_CHAR_TIMEAXIS_ACC_STATERR:
                        segment.createChar ().createTimeAxis ().createAccuracy ().setStatError( new DoubleParam( value, null, null, unit, null ) );
                    break;
                    
                    case FitsKeywords.SEG_CHAR_TIMEAXIS_ACC_SYSERR:
                        segment.createChar ().createTimeAxis ().createAccuracy ().setSysError( new DoubleParam( value, null, null, unit, null ) );
                    break;

                    case FitsKeywords.SEG_CHAR_TIMEAXIS_CAL:
                        segment.createChar ().createTimeAxis ().setCalibration( new TextParam( value ) );
                    break;
                    
                    case FitsKeywords.SEG_CHAR_TIMEAXIS_RESOLUTION:
                        segment.createChar ().createTimeAxis ().setResolution( new DoubleParam( value, null, null, unit, null ) );
                    break;
                    

                    case FitsKeywords.SEG_CHAR_TIMEAXIS_COV_LOC_VALUE:
                    {
                        DoubleParam valueList[] = segment.createChar ().createTimeAxis ().createCoverage ().createLocation ().createValue ();
                        if (valueList[0] == null)
                            valueList[0] = new DoubleParam (value, null, null, unit, null);
                        else
                            valueList[1] = new DoubleParam (value, null, null, unit, null);
                    }
                    break;

                    case FitsKeywords.SEG_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT:
                        segment.createChar ().createTimeAxis ().createCoverage ().createBounds ().setExtent (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_TIMEAXIS_COV_BOUNDS_MIN:
                        segment.createChar ().createTimeAxis ().createCoverage ().createBounds ().createRange ().setMin (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_TIMEAXIS_COV_BOUNDS_MAX:
                        segment.createChar ().createTimeAxis ().createCoverage ().createBounds ().createRange ().setMax (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_TIMEAXIS_COV_SUPPORT_EXTENT:
                        segment.createChar ().createTimeAxis ().createCoverage ().createSupport ().setExtent (new DoubleParam (value, null, null, unit, null));
                    break;

                    case FitsKeywords.SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                        segment.createChar ().createTimeAxis ().createSamplingPrecision ().createSamplingPrecisionRefVal ().setFillFactor (new DoubleParam (value, null, null, unit, null));

                    break;

                    default:
                    {
                    	try
                    	{

                            Param param;
                            // Since there can be multiple copies of the history and comments
                            // we don't include an id for these since id's need to be unique
                            if (key.equalsIgnoreCase ("HISTORY") || key.equalsIgnoreCase ("COMMENT"))
                            {
                                // history and comment values are being recognized as comments
                                if (value == null)
                                    value = card.getComment ();
                                param = new TextParam (value, key, null, null);
                            }
                            else
                            {
                                // the type isn't included in the module. try to 
                                // guess which type best fits the data
                            	
                                if (card.isStringValue())
                                    param = new TextParam (value, key, null, key);
                                else
                                {
                                    try
                                    {
                                        param = new IntParam (Integer.valueOf (value), key, null, unit, key);
                                    }
                                    catch (NumberFormatException exp1)
                                    {
                                        try
                                        {
                                            param = new DoubleParam (Double.valueOf (value), key, null, unit, key);
                                        }
                                        catch (NumberFormatException exp2)
                                        {
                                            param = new TextParam (value, key, null, key);
                                        }
                                    }
                                }
                            }
                            segment.addCustomParam (param);
                    	}
                    	catch (SedException exp)
                    	{
                    		logger.warning(exp.getMessage ());
                    	}
                    }
                    break;
                }
            }
        }
    }


    /**
     *  Gets the actual data from the table in the specified
     *  TableHDU and put it in a segment.
     */
    private void loadDataIntoSegment( TableHDU table, Segment segment ) throws SedParsingException
    {
 /*       Object[] row;
  * 
  */
        int columns;
        ArrayOfPoint pointData;
        List<Point> pointList;
        Object column;
        Point currentPoint;
        int dim;


        if (table.getNRows() == 0)
           return;

        if (table.getNRows() > 1)
           throw new SedParsingException ("An Unexpected number of rows were found. Expected one row per segment, but found " + table.getNRows () + " rows.");
        
/*
        try
        {
            row = table.getRow( 0 );
        }
        catch( FitsException exp )
        {
            throw new SedParsingException (
                    "A fits exception was encountered when attempting to access row 0.", exp);

        }
 */       
        columns = table.getNCols ();

        // create tables of the 
        String[][] paramInfoTable = this.createParamInfoTable (table.getHeader(), columns);


        pointData = new ArrayOfPoint ();
        pointList = pointData.createPoint ();


        // Create the points we need.  We could create an array of points and
        // convert it to a List with Arrays.asList() but this returns a fixed
        // size list and the user needs to be allowed to grow or shrink it.
/*
        int numPoints = this.getMaxFieldDepth( table );
        for ( int ii = 0; ii < numPoints; ii++ )
            pointList.add( new Point () );
*/

        // loop over the row; each elements contains all the data one
        // field in the Fits document
        for ( int ii = 0; ii <  columns; ii++ )
        {
            try
            {
                column = table.getColumn (ii);
            }
            catch (FitsException exp)
            {
                throw new SedParsingException ("Problems accessing fits column "+ii, exp);
            }

            // Get the utupe for this field.
            String utype   = paramInfoTable[ParamInfoKeys.UTYPE.ordinal()][ii];
            String ucd     = paramInfoTable[ParamInfoKeys.UCD.ordinal ()][ii];
            String name    = paramInfoTable[ParamInfoKeys.NAME.ordinal()][ii];
            String unit    = paramInfoTable[ParamInfoKeys.UNIT.ordinal()][ii];

            char dataType = '\0';
            String columnFormat = null;
            try
            {
                columnFormat = table.getColumnFormat(ii);
                dataType = columnFormat.replaceAll("[0-9]", "").charAt(0);
            }
            catch( FitsException exp )
            {
                throw new SedParsingException (
                            "A fits exception was encountered attempting to get column format, column="
                            + ii
                            + ";  Fits error: ");
            }

            dim = FitsMapper.getObjectDim (column);

            switch ( dataType )
            {
                case IOConstants.FITS_FORMAT_CODE_INTEGER16:
                {
                    short[] data;

                    // this is true if there are more than one point in the array
                    // the first element should be the first row
                    if (dim == 1)
                        data = (short[])column;
                    else if (dim == 2)
                        data = ((short[][])column)[0];
                    else
                    {
                        logger.log (Level.WARNING, "Points with multiple dimensional data is not supported. The data for column {0} will be ingored.", ii);
                        break;
                    }

                    for ( int jj= 0; jj <  data.length; jj++ )
                    { 
                        if (pointList.size () <= jj)
                        {
                            currentPoint = new Point ();
                            pointList.add(currentPoint);
                        }
                        else
                            currentPoint = pointList.get (jj);

                        this.setPointDataField( currentPoint,
                                utype,
                                new IntParam ((int)data[jj], name, ucd, unit, null));
                    }
                }
                break;

                case IOConstants.FITS_FORMAT_CODE_INTEGER32:
                {
                    int[] data;

                    // this is true if there are more than one point in the array
                    // the first element should be the first row
                    if (dim == 1)
                        data = (int[])column;
                    else if (dim == 2)
                        data = ((int[][])column)[0];
                    else
                    {
                        logger.log (Level.WARNING, "Points with multiple dimensional data is not supported. The data for column {0} will be ingored.", ii);
                        break;
                    }


                    for ( int jj = 0; jj <  data.length; jj++ )
                    {
                        if (pointList.size () <= jj)
                        {
                            currentPoint = new Point ();
                            pointList.add(currentPoint);
                        }
                        else
                            currentPoint = pointList.get (jj);
                        
                        this.setPointDataField( currentPoint,
                                utype,
                                new IntParam (data[jj], name, ucd, unit, null));
                    }
                }
                break;
                case IOConstants.FITS_FORMAT_CODE_DOUBLE_PRECISION:
                {
                    double[] data;

                    // this is true if there are more than one point in the array
                    // the first element should be the first row
                    if (dim == 1)
                        data = (double[])column;
                    else if (dim == 2)
                        data = ((double[][])column)[0];
                    else
                    {
                        logger.log (Level.WARNING, "Points with multiple dimensional data is not supported. The data for column {0} will be ingored.", ii);
                        break;
                    }

                    for ( int jj = 0; jj <  data.length; jj++ )
                    {
                        if (pointList.size () <= jj)
                        {
                            currentPoint = new Point ();
                            pointList.add(currentPoint);
                        }
                        else
                            currentPoint = pointList.get (jj);

                        this.setPointDataField( currentPoint,
                                utype,
                                new DoubleParam (data[jj], name, ucd, unit, null));
                    }
                }
                break;
                case IOConstants.FITS_FORMAT_CODE_SINGLE_PRECISION:
                {
                    float[] data;

                    // this is true if there are more than one point in the array
                    // the first element should be the first row
                    if (dim == 1)
                        data = (float[])column;
                    else if (dim == 2)
                        data = ((float[][])column)[0];
                    else
                    {
                        logger.log (Level.WARNING, "Points with multiple dimensional data is not supported. The data for column {0} will be ingored.", ii);
                        break;
                    }

                    for ( int jj = 0; jj <  data.length; jj++ )
                    {
                        if (pointList.size () <= jj)
                        {
                            currentPoint = new Point ();
                            pointList.add(currentPoint);
                        }
                        else
                            currentPoint = pointList.get (jj);

                        this.setPointDataField( currentPoint,
                                utype,
                                new DoubleParam ((double)data[jj], name, ucd, unit, null));
                    }
                }
                break;
                case IOConstants.FITS_FORMAT_CODE_STRING:
                {
                    String[] data;
                    int maxStringSize = 1;
                    int pointIndex = 0;
                    
                    // this is true if there are more than one point in the array
                    // the first element should be the first row
                    if (dim == 1)
                        data = (String[])column;
                    else if (dim == 2)
                        data = ((String[][])column)[0];
                    else
                    {
                        logger.log (Level.WARNING, "Points with multiple dimensional data is not supported. The data for column {0} will be ingored.", ii);
                        break;
                    }

                    // FIXME verify the length of each string. a bug in the module creates
                    // a situation where #A# creates a single string instead of 
                    // multiple strings. extract the actual sizes
                    if (columnFormat.matches ("^[0-9]+A[0-9]+$"))
                    {
                        maxStringSize = Integer.valueOf (columnFormat.replaceFirst ("^[0-9]+A",""));
                    }
                    else if (columnFormat.matches ("^[0-9]+A$"))
                    {
                        maxStringSize = Integer.valueOf (columnFormat.replaceFirst ("A$",""));
                    }

                    for ( int jj = 0; jj <  data.length; jj++ )
                    {
                    	String dataString = data[jj];
                        while (dataString.length () > 0)
                        {
                    	
                            if (pointList.size () <= pointIndex)
                            {
                                currentPoint = new Point ();
                                pointList.add(currentPoint);
                            }
                            else
                                currentPoint = pointList.get (pointIndex);

                            TextParam tparam;
                            if (dataString.length () > maxStringSize)
                            {
                                tparam = new TextParam (dataString.substring (0, maxStringSize), name, ucd, null);
                                dataString = dataString.substring (maxStringSize);
                            }
                            else
                            {
                                tparam = new TextParam (dataString, name, ucd, null);
                                dataString = "";
                            }
                            this.setPointDataField( currentPoint, utype, tparam);

                            pointIndex++;
                        }
                    }
                }
                break;

                default:
                    logger.warning (String.format (
                        "The specified data format code, %s for column %d, is not supported and will be ignored.", columnFormat, ii));
            }

        }

        // only add point data if there are points
        if (pointList.size() > 0)
           segment.setData( pointData );
    }


    /**
     *  Set the specified param to the a given point.
     */
    private void setPointDataField( Point point, String utype,
                Param param)
                throws SedParsingException
    {
        int utypeIdx = FitsKeywords.getUtypeFromString( utype );

        switch ( utypeIdx )
        {
            // for Flux
            case FitsKeywords.SEG_DATA_FLUXAXIS_VALUE :
                point.createFluxAxis().setValue((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRLOW:
                point.createFluxAxis().createAccuracy().setStatErrLow((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRHIGH:
                point.createFluxAxis().createAccuracy().setStatErrHigh((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC_SYSERR:
                point.createFluxAxis().createAccuracy().setSysError((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_QUALITY:
                point.createFluxAxis().setQuality((IntParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINLOW:
                point.createFluxAxis().createAccuracy().setBinLow((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINHIGH:
                point.createFluxAxis().createAccuracy().setBinHigh((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINSIZE:
                point.createFluxAxis().createAccuracy().setBinSize((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERR:
                point.createFluxAxis().createAccuracy().setStatError((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_RESOLUTION:
                point.createFluxAxis().setResolution((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC_CONFIDENCE:
                point.createFluxAxis().createAccuracy().setConfidence((DoubleParam)param);
                break;

            // for Spectral
            case FitsKeywords.SEG_DATA_SPECTRALAXIS_VALUE :
                point.createSpectralAxis().setValue((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW:
                point.createSpectralAxis().createAccuracy().setStatErrLow((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH:
                point.createSpectralAxis().createAccuracy().setStatErrHigh((DoubleParam)param);
                break;
            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_SYSERR:
                point.createSpectralAxis().createAccuracy().setSysError((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_SPECTRALAXIS_RESOLUTION:
                point.createSpectralAxis().setResolution((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINLOW:
                point.createSpectralAxis().createAccuracy().setBinLow((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINHIGH:
                point.createSpectralAxis().createAccuracy().setBinHigh((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINSIZE:
                point.createSpectralAxis().createAccuracy().setBinSize((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERR:
                point.createSpectralAxis().createAccuracy().setStatError((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_CONFIDENCE:
                point.createSpectralAxis().createAccuracy().setConfidence((DoubleParam)param);
                break;

            // for Time
            case FitsKeywords.SEG_DATA_TIMEAXIS_VALUE :
                point.createTimeAxis().setValue((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC_STATERRLOW:
                point.createTimeAxis().createAccuracy().setStatErrLow((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC_STATERRHIGH:
                point.createTimeAxis().createAccuracy().setStatErrHigh((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC_SYSERR:
                point.createTimeAxis().createAccuracy().setSysError((DoubleParam)param);
                break;
            case FitsKeywords.SEG_DATA_TIMEAXIS_RESOLUTION:
                point.createTimeAxis().setResolution((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC_BINLOW:
                point.createTimeAxis().createAccuracy().setBinLow((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC_BINHIGH:
                point.createTimeAxis().createAccuracy().setBinHigh((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC_BINSIZE:
                point.createTimeAxis().createAccuracy().setBinSize((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC_STATERR:
                point.createTimeAxis().createAccuracy().setStatError((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC_CONFIDENCE:
                point.createTimeAxis().createAccuracy().setConfidence((DoubleParam)param);
                break;

            // for background model
            case FitsKeywords.SEG_DATA_BGM_VALUE :
                point.createBackgroundModel().setValue((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_ACC_STATERRLOW:
                point.createBackgroundModel().createAccuracy().setStatErrLow((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_ACC_STATERRHIGH:
                point.createBackgroundModel().createAccuracy().setStatErrHigh((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_ACC_SYSERR:
                point.createBackgroundModel().createAccuracy().setSysError((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_QUALITY:
                point.createBackgroundModel().setQuality((IntParam)param);
                break;
            case FitsKeywords.SEG_DATA_BGM_ACC_BINLOW:
                point.createBackgroundModel().createAccuracy().setBinLow((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_ACC_BINHIGH:
                point.createBackgroundModel().createAccuracy().setBinHigh((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_ACC_BINSIZE:
                point.createBackgroundModel().createAccuracy().setBinSize((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_ACC_STATERR:
                point.createBackgroundModel().createAccuracy().setStatError((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_RESOLUTION:
                point.createBackgroundModel().setResolution ((DoubleParam)param);
                break;

            case FitsKeywords.SEG_DATA_BGM_ACC_CONFIDENCE:
                point.createBackgroundModel().createAccuracy().setConfidence((DoubleParam)param);
                break;

            default:
            {
            	try
            	{
                    // set the id of the parameter to be the name of the column 
                    param.setId (param.getName ());
                    point.addCustomParam (param);
            	}
            	catch (SedException exp)
            	{
            		logger.warning(exp.getMessage ());
            	}
            }
        }
    }



    /**
     *  Extract param information for specfied keyword.
     */
    private ParamInfoKeys getDataKeyword( String keyword )
    {
        for (ParamInfoKeys pp : ParamInfoKeys.values ())
            if ( keyword.matches( pp.pattern ) )
                return pp;
        return ParamInfoKeys.INVALID;
    }


    /**
     *  Retrieve the specified CoordFrame if it exists, otherwise create it.
     */
    private CoordFrame getCoordFrame (List<CoordFrame> coordFrames, int utype)
    {

        CoordFrame coordFrame = null;

        for (int ii=0; ii<coordFrames.size (); ii++)
        {
            coordFrame = coordFrames.get (ii);

            if ((utype == FitsKeywords.SEG_CS_SPECTRALFRAME) &&
                (coordFrame instanceof SpectralFrame))
                break;

            if ((utype == FitsKeywords.SEG_CS_TIMEFRAME) &&
                (coordFrame instanceof TimeFrame))
                break;

            if ((utype == FitsKeywords.SEG_CS_SPACEFRAME) &&
                (coordFrame instanceof SpaceFrame))
                break;

            if ((utype == FitsKeywords.SEG_CS_REDFRAME) &&
                (coordFrame instanceof RedshiftFrame))
                break;

            if (utype == FitsKeywords.SEG_CS_GENFRAME)
                break;

            coordFrame = null;

       }

        // the coord frame doesn't exist so create it
        if (coordFrame == null)
        {
            switch (utype)
            {
                case FitsKeywords.SEG_CS_SPECTRALFRAME:
                    coordFrame = new SpectralFrame (); 
                    break;
                case FitsKeywords.SEG_CS_TIMEFRAME:
                    coordFrame = new TimeFrame (); 
                    break;
                case FitsKeywords.SEG_CS_SPACEFRAME:
                    coordFrame = new SpaceFrame (); 
                    break;
                case FitsKeywords.SEG_CS_REDFRAME:
                    coordFrame = new RedshiftFrame (); 
                    break;
                case FitsKeywords.SEG_CS_GENFRAME:
                    coordFrame = new CoordFrame ();
                    break;
            }
            coordFrames.add (coordFrame);
        }

        return coordFrame;
    }

    /**
     *  Returns the length of the cell (element ) in the table row with the greatest
     *  length.  The elements of tableRow are arrays of primitives and it is the
     *  length of those arrays whose maximum is returned.
     */
    int getMaxFieldDepth( TableHDU table ) throws SedParsingException
    {
        int maxDepth = 0;
        for ( int ii = 0; ii < table.getNCols (); ii++ )
        {
        	try
        	{
                maxDepth = Math.max( maxDepth, this.getFieldDepth( table.getColumn(ii) ) );
        	}
        	catch (FitsException exp)
        	{
        		throw new SedParsingException ("Problem accessing fits column "+ii, exp);
        	}
        }

        return maxDepth;
    }


    /**
     *  Returns the depth of the specified cell in a table row.
     *  The cell is an array of primitives whose length is returned.
     */
    private int getFieldDepth( Object column )
    {
        // get the class name so we know what we need to cast to. 
        String className;
        
        if (column == null)
        	return 0;
        
        className = column.getClass().getName();
        if ( className.equals( "[[S" ) )
        {
            short element[] = ((short[][])column)[0];
            if (element != null)
            	return element.length;
        }
        else if ( className.equals( "[[I" ) )
        {
            int element[] = ((int[][])column)[0];
            if (element != null)
            	return element.length;
        }
       	
        else if ( className.equals( "[[D" ) )
        {
            double element[] = ((double[][])column)[0];
            if (element != null)
            	return element.length;
        }
        else if ( className.equals( "[[F" ) )
        {
            float element[] = ((float[][])column)[0];
            if (element != null)
            	return element.length;
        }
        else if ( className.equals( "[[L") )
        {
            String element[] = ((String[][])column)[0];
            if (element != null)
            	return element.length;
        }

        return 0;
    }

    /**
     * Loop over the metadata associated with the columns (ucd, utype, etc.)
     * and create a table (information type x column number).
     */
    private String[][] createParamInfoTable( Header header, int listSize)
    {
        Cursor cursor = header.iterator();
        String key;

        // create a table for the utype, name, ucd, unit
        String[][] paramInfo = new String[ParamInfoKeys.size][listSize];
        ParamInfoKeys index;
        String pos;

        for (int ii=0; ii<4; ii++)
            for (int jj=0; jj<listSize; jj++)
                paramInfo[ii][jj] = null;

        while (cursor.hasNext())
        {
            HeaderCard card = (HeaderCard) cursor.next();

            key = card.getKey();

            // look to see if it's a relevant keyword
            index = this.getDataKeyword (key);

            // skip any of non-param keywords
            if ((index == ParamInfoKeys.INVALID) || 
                (index == ParamInfoKeys.FORM))
                continue;

            // find the appropriate index into the array
            if (index == ParamInfoKeys.UCD)
               pos = key.substring (4);
            else
               pos = key.substring (5);

            paramInfo[index.ordinal ()][Integer.valueOf (pos)-1] = card.getValue();
        }

        return paramInfo;
    }
              
    /**
     *  Extract the units from keyword description.
     */
    private String getUnitFromComment (String comment)
    {
        String unit = null;

        if (comment == null)
        	return null;
        
        comment = comment.trim ();
        if (comment.matches ("^\\[[a-zA-Z0-9]+\\]"))
        {
            int end = comment.indexOf ("]");
            unit = comment.substring (1,end);
        }
        return unit; 
    }
              

    /**
     * Several keywords have values which are supposed to be the same as
     * the data metadata. Update these values to make sure the keyword
     * and the metadata match.
     */
    private void matchMetaDataWithData (Segment segment)
    {

        ArrayOfPoint data;
        List<Point> pointList;
        Point point;

        if (!segment.isSetData ())
            return;

        data = segment.getData();
        pointList = data.createPoint ();

        if (pointList.isEmpty())
            return;

        // use the first point to get relevant information
        point = pointList.get (0);

        if (point.isSetSpectralAxis ())
        {
            SedCoord pointAxis = point.getSpectralAxis ();
            CharacterizationAxis charAxis;
            if (pointAxis.isSetValue ())
            {

                // set CharSpectralAxis.name
                charAxis = segment.createChar ().createSpectralAxis ();
                charAxis.setName (pointAxis.getValue ().getName ());

                // set CharSpectralAxis.ucd
                if (pointAxis.getValue ().isSetUcd ())
                    charAxis.setUcd (pointAxis.getValue ().getUcd ());
                else if (charAxis.isSetUcd ())
                    pointAxis.getValue ().setUcd (charAxis.getUcd ());

                // set CharSpectralAxis.unit
                if (pointAxis.getValue ().isSetUnit ())
                    charAxis.setUnit (pointAxis.getValue ().getUnit ());
                else if (charAxis.isSetUnit ())
                    pointAxis.getValue ().setUnit (charAxis.getUnit ());

                // set CoordSys.SpectralFrame.ucd
                SpectralFrame spectralFrame = new SpectralFrame ();
                spectralFrame.setUcd (pointAxis.getValue ().getUcd ());
                segment.createCoordSys ().createCoordFrame ().add (spectralFrame);

            }
        }

        if (point.isSetFluxAxis ())
        {
            SedQuantity pointAxis = point.getFluxAxis ();
            CharacterizationAxis charAxis;
            if (pointAxis.isSetValue ())
            {

                // set CharSpectralAxis.name
                charAxis = segment.createChar ().createFluxAxis ();
                charAxis.setName (pointAxis.getValue ().getName ());

                // set CharSpectralAxis.ucd
                if (pointAxis.getValue ().isSetUcd ())
                    charAxis.setUcd (pointAxis.getValue ().getUcd ());
                else if (charAxis.isSetUcd ())
                    pointAxis.getValue ().setUcd (charAxis.getUcd ());

                // set CharSpectralAxis.unit
                if (pointAxis.getValue ().isSetUnit ())
                    charAxis.setUnit (pointAxis.getValue ().getUnit ());
                else if (charAxis.isSetUnit ())
                    pointAxis.getValue ().setUnit (charAxis.getUnit ());
            }
        }

        // set values for properties which dont have keywords
        if (segment.isSetChar ())
        {
           if (segment.getChar ().isSetTimeAxis ())
           {
               segment.getChar ().getTimeAxis ().setName ("Time");
               segment.getChar ().getTimeAxis ().setUcd ("time");
           }

           if (segment.getChar ().isSetSpatialAxis ())
               segment.getChar ().getSpatialAxis ().setUnit ("deg");
        }
    }

    public static int getObjectDim(Object obj)
    {
        int dim = 0;
        Class objClass = obj.getClass();
        while (objClass.isArray())
        {
            dim++;
            objClass = objClass.getComponentType();
        }
        return dim;
    }

}
    

