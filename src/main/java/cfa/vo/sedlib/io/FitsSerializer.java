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
* File: io/FitsSerializer.java
*
* Author:  jmiller      Created: Mon Nov 29 11:57:14 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.BinaryTable;
import nom.tam.fits.BinaryTableHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.HeaderCard;
import nom.tam.util.Cursor;
import nom.tam.fits.HeaderCardException;
import cfa.vo.sedlib.Accuracy;
import cfa.vo.sedlib.ArrayOfPoint;
import cfa.vo.sedlib.Characterization;
import cfa.vo.sedlib.CharacterizationAxis;
import cfa.vo.sedlib.Contact;
import cfa.vo.sedlib.CoordFrame;
import cfa.vo.sedlib.CoordSys;
import cfa.vo.sedlib.Coverage;
import cfa.vo.sedlib.CoverageBounds;
import cfa.vo.sedlib.CoverageLocation;
import cfa.vo.sedlib.CoverageSupport;
import cfa.vo.sedlib.Curation;
import cfa.vo.sedlib.DataID;
import cfa.vo.sedlib.DerivedData;
import cfa.vo.sedlib.DoubleParam;
import cfa.vo.sedlib.Group;
import cfa.vo.sedlib.IntParam;
import cfa.vo.sedlib.Param;
import cfa.vo.sedlib.Point;
import cfa.vo.sedlib.RedshiftFrame;
import cfa.vo.sedlib.SamplingPrecision;
import cfa.vo.sedlib.SamplingPrecisionRefVal;
import cfa.vo.sedlib.Sed;
import cfa.vo.sedlib.SedCoord;
import cfa.vo.sedlib.SedQuantity;
import cfa.vo.sedlib.Segment;
import cfa.vo.sedlib.SpaceFrame;
import cfa.vo.sedlib.SpectralCharacterizationAxis;
import cfa.vo.sedlib.SpectralFrame;
import cfa.vo.sedlib.Target;
import cfa.vo.sedlib.TextParam;
import cfa.vo.sedlib.TimeFrame;
import cfa.vo.sedlib.TimeParam;
import cfa.vo.sedlib.common.FitsKeywords;
import cfa.vo.sedlib.common.SedConstants;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedWritingException;

/**
Serializes a Sed object to a Fits formatted file.
*/

public class FitsSerializer implements ISedSerializer
{

    static final Logger logger = Logger.getLogger ("cfa.vo.sedlib");

    // enumerated values associated with column metadata 
    enum ParamInfoKeys {
        UCD ("TUCD"),
        NAME ("TTYPE"),
        UTYPE ("TUTYP"),
        UNIT ("TUNIT");

        String keyword;

        ParamInfoKeys (String keyword) {this.keyword = keyword;};
    }

    // actual values of metadata and data associated with a column
    protected class Column {
        String ucd = "";
        String name = "";
        String utype = "";
        String unit = ""; 
        Class dataClass = null;
        ArrayList data = null;
    }

    protected int numFields = 0;
    protected int numPoints = 0;
    protected Vector<String> columnOrder = null;
    protected HashMap <String,Column> dataTable = null;
    protected HashMap <Integer,String> overrides = new HashMap<Integer, String> ();
    protected HashMap <Integer,String> ucdOverrides = new HashMap<Integer, String> ();

    /**
     * Serializes the specified Sed object to the specified file in
     * Fits format.
     * @param filename
     *   {@link String}
     * @param sed
     *   {@link Sed}
     *
     * @throws IOException, SedWritingException, SedInconsistentException
     */
    public void serialize(String filename, Sed sed) throws SedWritingException, IOException, SedInconsistentException
    {
        FitsWriter writer = new FitsWriter ();
        Fits fits = this.processSed (sed);
        writer.write( filename, fits );
    }


    /**
     * Serializes the specified Sed object to the specified stream in
     * Fits format.
     * @param oStream
     *   {@link OutputStream}
     * @param sed
     *   {@link Sed}
     *
     * @throws IOException, SedWritingException, SedInconsistentException
     */
    public void serialize(OutputStream  oStream, Sed sed) throws SedWritingException, SedInconsistentException
    {
        FitsWriter writer = new FitsWriter ();
        Fits fits = this.processSed (sed);
        writer.write( oStream, fits );
    }

    /**
     * Initialize the serialization attributes.
     */
    protected void initialize ()
    {
        this.numFields = 0;
        this.numPoints = 0;
        this.dataTable = null;
        this.columnOrder = null;
        this.overrides = new HashMap<Integer, String> ();
        this.ucdOverrides = new HashMap<Integer, String> ();
    }   



    /**
     * Extract a Fits object from the Sed
     */
    protected Fits processSed(Sed sed) throws SedWritingException, SedInconsistentException
    {
        Fits fits = new Fits();
        Header header;
        BinaryTable binaryTable;
        BinaryTableHDU binaryTableHDU;

        if (sed.getNumberOfSegments () == 0)
            return fits;

        // Now add each segment in its own hdu.
        for (int ii=0; ii<sed.getNumberOfSegments(); ii++)
        {
            Segment segment = sed.getSegment (ii);

            // reset internal tables for each segment
            this.initialize ();

            // extract the data from the segment to a generic table
            if (segment.isSetData())
            {
                this.overrideDataColumnInfo(segment);
                this.extractDataTable (segment.getData());
            }

            // handle the data 
            if (segment.isSetData())
            {
                // add data to table
                try
                {
                    binaryTable = new BinaryTable();
                }
                catch (FitsException exp)
                {
                    throw new SedWritingException (exp.getMessage (), exp);
                }

                // add the data to the binaryTable
                this.addDataToTable (binaryTable);
            } 
            else
            {
                try
                {
                    binaryTable = new BinaryTable();
                }
                catch (FitsException exp)
                {
                    throw new SedWritingException (exp.getMessage (), exp);
                }
            }

            try
            {
                binaryTableHDU = (BinaryTableHDU) Fits.makeHDU (binaryTable);
                fits.addHDU (binaryTableHDU);

            }
            catch (FitsException exp)
            {
            	throw new SedWritingException (exp.getMessage (), exp);
            }

	    // get table header 
            header = binaryTableHDU.getHeader ();

            // make sure the header is at the end of it's default information
            this.setHeaderCursor(header);

            this.addSegmentToHeader (segment, header);

            if (segment.isSetData())
            {

                // override specific attributes with data values
                this.overrideKeywords( segment.getData (), header );

                // add the data information to the header
                this.addDataToHeader (header);
            }
        }

	// MCD NOTE 20110810:
	//   Removed manual setting of NAXIS=0.
	//   FITS-3.0 is for NAXIS=0 with NO NAXIS1 keyword
	//   This segment set NAXIS=0 but kept NAXIS1=0 which is a violation of the standard.

        return fits;
    }

    /**
     * Add information from the segment to the header of the HDU
     */
    private void addSegmentToHeader( Segment segment, Header header) throws SedInconsistentException, SedWritingException
    {

        // add spectrum infomation
        this.addSedParam(new TextParam (segment.getDataModel()), header, FitsKeywords.DATAMODEL);

        if (segment.isSetType ())
            this.addSedParam(segment.getType(), header, FitsKeywords.TYPE);
        if (segment.isSetTimeSI ())
            this.addSedParam(segment.getTimeSI(), header, FitsKeywords.TIMESI);
        if (segment.isSetSpectralSI())
            this.addSedParam(segment.getSpectralSI(), header, FitsKeywords.SPECTRALSI);
        if (segment.isSetFluxSI())
            this.addSedParam(segment.getFluxSI(), header, FitsKeywords.FLUXSI);

        if (segment.isSetTarget ())
            this.addTargetToHeader (segment.getTarget (), header);
        if (segment.isSetChar ())
            this.addCharacterizationToHeader (segment.getChar (), header);
        if (segment.isSetCoordSys ())
            this.addCoordSysToHeader (segment.getCoordSys (), header);
        if (segment.isSetCuration ())
            this.addCurationToHeader (segment.getCuration (), header);
        if (segment.isSetDataID ())
            this.addDataIDToHeader (segment.getDataID (), header);
        if (segment.isSetDerived ())
            this.addDerivedToHeader (segment.getDerived (), header);
        
        this.addCustomParamsToHeader (segment, header);
        
    }

    /**
     * Add information from the target to the header of the HDU
     */
    private void addTargetToHeader (Target target, Header header)
        throws SedInconsistentException, SedWritingException
    {
        if (target.isSetName ())
           this.addSedParam (target.getName (), header, FitsKeywords.TARGET_NAME);
        if (target.isSetDescription ())
           this.addSedParam (target.getDescription (), header, FitsKeywords.TARGET_DESCRIPTION);
        if (target.isSetTargetClass ())
           this.addSedParam (target.getTargetClass (), header, FitsKeywords.TARGET_CLASS);
        if (target.isSetSpectralClass ())
           this.addSedParam (target.getSpectralClass (), header, FitsKeywords.TARGET_SPECTRALCLASS);
        if (target.isSetRedshift ())
           this.addSedParam (target.getRedshift (), header, FitsKeywords.TARGET_REDSHIFT);
        if (target.isSetPos ())
        {
            DoubleParam posList[] = target.getPos().getValue ();
            if ((posList[0] == null) || (posList[1] == null))
            {
                throw new SedInconsistentException ("The wrong number of position values found when serializing coverage position. Two positions were expected; however, one or both were set to null.");
            }

            this.addSedParam (posList[0], header, FitsKeywords.TARGET_RA );
            this.addSedParam (posList[1], header, FitsKeywords.TARGET_DECL );
        }
        if (target.isSetVarAmpl ())
           this.addSedParam (target.getVarAmpl (), header, FitsKeywords.TARGET_VARAMPL);

        this.addCustomParamsToHeader (target, header);
    }

    /**
     * Add information from the Characterization to the header of the HDU
     */
    private void addCharacterizationToHeader (Characterization _char, Header header)
        throws SedInconsistentException, SedWritingException
    {

        if (_char.isSetSpatialAxis ())
            this.addCharacterizationAxisToHeader (_char.getSpatialAxis (), header,FitsKeywords.SEG_CHAR_SPATIALAXIS);
        if (_char.isSetTimeAxis ())
            this.addCharacterizationAxisToHeader (_char.getTimeAxis (), header, FitsKeywords.SEG_CHAR_TIMEAXIS);
        if (_char.isSetSpectralAxis ())
        {
            SpectralCharacterizationAxis spectralAxis = _char.getSpectralAxis ();
            if (spectralAxis.isSetResPower ())
                this.addSedParam (spectralAxis.getResPower (), header, FitsKeywords.SEG_CHAR_SPECTRALAXIS_RESPOW);

            this.addCharacterizationAxisToHeader (spectralAxis, header, FitsKeywords.SEG_CHAR_SPECTRALAXIS);

        }
        if (_char.isSetFluxAxis ())
            this.addCharacterizationAxisToHeader (_char.getFluxAxis (), header, FitsKeywords.SEG_CHAR_FLUXAXIS);
            
        this.addCustomParamsToHeader (_char, header);
    }

    /**
     * Add information from the CharacterizationAxis to the header of the HDU
     */
    private void addCharacterizationAxisToHeader (CharacterizationAxis charAxis, Header header, int utype)
        throws SedInconsistentException, SedWritingException
    {
        int newUtype;
        String functionName = "addCharacterizationAxisToHeader";

        if (charAxis.isSetName ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_NAME, functionName);
            this.addSedParam (new TextParam (charAxis.getName ()), header, newUtype);
        }
        if (charAxis.isSetUcd ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_UCD, functionName);
            this.addSedParam (new TextParam (charAxis.getUcd ()), header, newUtype);
        }
        if (charAxis.isSetUnit ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_UNIT, functionName);
            this.addSedParam (new TextParam (charAxis.getUnit ()), header, newUtype);
        }


        if (charAxis.isSetResolution ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_RESOLUTION, functionName);
            this.addSedParam (charAxis.getResolution (), header, newUtype);
        }
        if (charAxis.isSetCalibration ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_CAL, functionName);
            this.addSedParam (charAxis.getCalibration (), header, newUtype);

        }
        if (charAxis.isSetCoverage ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_COV, functionName);
            this.addCoverageToHeader (charAxis.getCoverage (), header, newUtype);
        }
        if (charAxis.isSetAccuracy ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_ACC, functionName);
            this.addAccuracyToHeader (charAxis.getAccuracy (), header, newUtype);
        }
        if (charAxis.isSetSamplingPrecision ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_SAMPPREC, functionName);
            this.addSamplingPrecisionToHeader (charAxis.getSamplingPrecision (), header, newUtype);
        }

        this.addCustomParamsToHeader (charAxis, header);

    }

    /**
     * Add information from the Coverage to the header of the HDU
     */
    private void addCoverageToHeader (Coverage coverage, Header header, int utype)
        throws SedInconsistentException, SedWritingException
    {
        int newUtype;
        String functionName = "addCoverageToHeader";

        // verify the utype is an axis. this is more
        // for maintance to simply ensure if the utypes change
        // that this function doesn't accidently pass
        switch (utype)
        {
            case FitsKeywords.SEG_CHAR_CHARAXIS_COV:
            case FitsKeywords.SEG_CHAR_TIMEAXIS_COV:
            case FitsKeywords.SEG_CHAR_SPATIALAXIS_COV:
            case FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV:
            case FitsKeywords.SEG_CHAR_FLUXAXIS_COV:
                break;
            default:
                logger.log (Level.WARNING, "The keyword, {0}, cannot be included as part of the Coverage.", FitsKeywords.getName(utype));
        }

        if (coverage.isSetLocation ())
        {
            CoverageLocation location = coverage.getLocation ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_COV_LOC, functionName);
            if (location.isSetValue ())
            {
                int valUtype = this.mergeUtypes (newUtype, FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_VALUE, functionName);
                int raUtype = FitsKeywords.INVALID_UTYPE;
                int decUtype = FitsKeywords.INVALID_UTYPE;
                DoubleParam posList[] = location.getValue();

                try {
                    raUtype = this.mergeUtypes (valUtype, FitsKeywords.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_RA, functionName);
                    decUtype = this.mergeUtypes (valUtype, FitsKeywords.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_DEC, functionName);
                } catch (Exception e) {
                }

                if (raUtype != FitsKeywords.INVALID_UTYPE)
                {
                    if ((posList[0] == null) || (posList[1] == null))
                    {
                        throw new SedInconsistentException ("The wrong number of position values found when serializing coverage position. Two positions were expected; however, one or both were set to null.");
                    }

                    this.addSedParam (posList[0], header, raUtype );
                    this.addSedParam (posList[1], header, decUtype );
                }
                else
                    this.addSedParam (posList[0], header, valUtype );
            }
            if (location.isSetResolution ())
            {
                int resUtype = this.mergeUtypes (newUtype, FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_RESOLUTION, functionName);
                this.addSedParam (location.getResolution (), header, resUtype );
            }
            if (location.isSetAccuracy ())
            {
                int accUtype = this.mergeUtypes (newUtype, FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC, functionName);
                this.addAccuracyToHeader (location.getAccuracy (), header, accUtype);
            }

            this.addCustomParamsToHeader (location, header);
        }
        if (coverage.isSetBounds ())
        {
            CoverageBounds bounds = coverage.getBounds ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_COV_BOUNDS, functionName);

            if (bounds.isSetExtent ())
            {
                int extUtype = this.mergeUtypes (newUtype, FitsKeywords.SEG_CHAR_CHARAXIS_COV_BOUNDS_EXTENT, functionName);
                this.addSedParam (bounds.getExtent (), header, extUtype );
            }
            if (bounds.isSetRange ())
            {
                boolean keywordMatch = true;
                // set the min bound
                int rngUtype = this.mergeUtypes (newUtype, FitsKeywords.SEG_CHAR_FLUXAXIS_COV_BOUNDS_MIN, functionName);
                if (rngUtype == FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN)
                    keywordMatch = this.matchKeywordToColumn (FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN, FitsKeywords.SEG_DATA_SPECTRALAXIS_VALUE);

                if (keywordMatch)
                    this.addSedParam (bounds.getRange ().getMin (), header, rngUtype );
                else
                    logger.warning ("The keyword for the 'spectral axis coverage bounds start' could not be written because the spectral axis column could not be found.");

                // set the max bound
                keywordMatch = true;
                rngUtype = this.mergeUtypes (newUtype, FitsKeywords.SEG_CHAR_FLUXAXIS_COV_BOUNDS_MAX, functionName);
                if (rngUtype == FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX)
                    keywordMatch = this.matchKeywordToColumn (FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX, FitsKeywords.SEG_DATA_SPECTRALAXIS_VALUE);
                if (keywordMatch)
                    this.addSedParam (bounds.getRange ().getMax (), header, rngUtype );
                else
                    logger.warning ("The keyword for the 'spectral axis coverage bounds stop' could not be written because the spectral axis column could not be found.");

            }
            this.addCustomParamsToHeader (bounds, header);
        }
        if (coverage.isSetSupport ())
        {
            CoverageSupport support = coverage.getSupport ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_CHARAXIS_COV_SUPPORT, functionName);

            if (support.isSetArea ())
            {
                int areaUtype = this.mergeUtypes (newUtype, FitsKeywords.SEG_CHAR_FLUXAXIS_COV_SUPPORT_AREA, functionName);
                this.addSedParam (support.getArea (), header, areaUtype );
            }
            if (support.isSetExtent ())
            {
                int extUtype = this.mergeUtypes (newUtype, FitsKeywords.SEG_CHAR_FLUXAXIS_COV_SUPPORT_EXTENT, functionName);
                this.addSedParam (support.getExtent (), header, extUtype );
            }
/*
            if (support.isSetRange ())
            {
                // this isn't defined and it's not obvious how 
                // it would be handled
            }
*/
            this.addCustomParamsToHeader (support, header);
        }
    }

    /**
     * Add information from the Accuracy to the header of the HDU
     */
    private void addAccuracyToHeader (Accuracy accuracy, Header header, int utype) throws SedInconsistentException, SedWritingException
    {
        HashMap <String, Integer> utypeTable = new HashMap <String, Integer> ();
        int newUtype;
        String functionName = "addAccuracyToHeader";

        // create a table of valid utypes to merge with.
        switch (utype)
        {
            case FitsKeywords.SEG_CHAR_CHARAXIS_ACC:
            case FitsKeywords.SEG_CHAR_TIMEAXIS_ACC:
            case FitsKeywords.SEG_CHAR_SPATIALAXIS_ACC:
            case FitsKeywords.SEG_CHAR_SPECTRALAXIS_ACC:
            case FitsKeywords.SEG_CHAR_FLUXAXIS_ACC:
                utypeTable.put ("binLow", FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_BINLOW);
                utypeTable.put ("binHigh", FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_BINHIGH);
                utypeTable.put ("binSize", FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_BINSIZE);
                utypeTable.put ("statErrLow", FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh", FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_STATERRHIGH);
                utypeTable.put ("statError", FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_STATERR);
                utypeTable.put ("sysError", FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_SYSERR);
                utypeTable.put ("confidence", FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_CONFIDENCE);
                break;
            case FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC:
            case FitsKeywords.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC:
            case FitsKeywords.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC:
            case FitsKeywords.SEG_CHAR_TIMEAXIS_COV_LOC_ACC:
                utypeTable.put ("binLow", FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINLOW);
                utypeTable.put ("binHigh", FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINHIGH);
                utypeTable.put ("binSize", FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINSIZE);
                utypeTable.put ("statErrLow", FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh", FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRHIGH);
                utypeTable.put ("statError", FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERR);
                utypeTable.put ("sysError", FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_SYSERR);
                utypeTable.put ("confidence", FitsKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_CONFIDENCE);
                break;
            case FitsKeywords.SEG_DD_REDSHIFT_ACC:
                utypeTable.put ("binLow", FitsKeywords.SEG_DD_REDSHIFT_ACC_BINLOW);
                utypeTable.put ("binHigh", FitsKeywords.SEG_DD_REDSHIFT_ACC_BINHIGH);
                utypeTable.put ("binSize", FitsKeywords.SEG_DD_REDSHIFT_ACC_BINSIZE);
                utypeTable.put ("statErrLow", FitsKeywords.SEG_DD_REDSHIFT_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh", FitsKeywords.SEG_DD_REDSHIFT_ACC_STATERRHIGH);
                utypeTable.put ("statError", FitsKeywords.SEG_DD_REDSHIFT_ACC_STATERR);
                utypeTable.put ("sysError", FitsKeywords.SEG_DD_REDSHIFT_ACC_SYSERR);
                utypeTable.put ("confidence", FitsKeywords.SEG_DD_REDSHIFT_ACC_CONFIDENCE);

                break;
            case FitsKeywords.SEG_DATA_FLUXAXIS_ACC:
            case FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC:
            case FitsKeywords.SEG_DATA_TIMEAXIS_ACC:
                utypeTable.put ("binLow", FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINLOW);
                utypeTable.put ("binHigh", FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINHIGH);
                utypeTable.put ("binSize", FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINSIZE);
                utypeTable.put ("statErrLow", FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh",FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRHIGH);
                utypeTable.put ("statError", FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERR);
                utypeTable.put ("sysError", FitsKeywords.SEG_DATA_FLUXAXIS_ACC_SYSERR);
                utypeTable.put ("confidence", FitsKeywords.SEG_DATA_FLUXAXIS_ACC_CONFIDENCE);
                break;
            case FitsKeywords.SEG_DATA_BGM_ACC:
                utypeTable.put ("binLow", FitsKeywords.SEG_DATA_BGM_ACC_BINLOW);
                utypeTable.put ("binHigh", FitsKeywords.SEG_DATA_BGM_ACC_BINHIGH);
                utypeTable.put ("binSize", FitsKeywords.SEG_DATA_BGM_ACC_BINSIZE);
                utypeTable.put ("statErrLow", FitsKeywords.SEG_DATA_BGM_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh",FitsKeywords.SEG_DATA_BGM_ACC_STATERRHIGH);
                utypeTable.put ("statError", FitsKeywords.SEG_DATA_BGM_ACC_STATERR);
                utypeTable.put ("sysError", FitsKeywords.SEG_DATA_BGM_ACC_SYSERR);
                utypeTable.put ("confidence", FitsKeywords.SEG_DATA_BGM_ACC_CONFIDENCE);
                break;
            default:
                logger.log (Level.WARNING, "The keyword, {0}, cannot be included as part of the Accuracy.", FitsKeywords.getName(utype));
        }

        if (accuracy.isSetBinLow ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("binLow"), functionName);
            this.addSedParam (accuracy.getBinLow (), header, newUtype);
        }
        if (accuracy.isSetBinHigh ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("binHigh"), functionName);
            this.addSedParam (accuracy.getBinHigh (), header, newUtype);
        }
        if (accuracy.isSetBinSize ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("binSize"), functionName);
            this.addSedParam (accuracy.getBinSize (), header, newUtype);
        }
        if (accuracy.isSetStatError ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("statError"), functionName);
            this.addSedParam (accuracy.getStatError (), header, newUtype);
        }
        if (accuracy.isSetStatErrLow ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("statErrLow"), functionName);
            this.addSedParam (accuracy.getStatErrLow (), header, newUtype);
        }
        if (accuracy.isSetStatErrHigh ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("statErrHigh"), functionName);
            this.addSedParam (accuracy.getStatErrHigh (), header, newUtype);
        }
        if (accuracy.isSetSysError ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("sysError"), functionName);
            this.addSedParam (accuracy.getSysError (), header, newUtype);
        }
        if (accuracy.isSetConfidence ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("confidence"), functionName);
            this.addSedParam (accuracy.getConfidence (), header, newUtype);
        }

        this.addCustomParamsToHeader (accuracy, header);
    }

    /**
     * Add information from the SamplingPrecision to the header of the HDU
     */
    private void addSamplingPrecisionToHeader (SamplingPrecision samplingPrecision, Header header, int utype)
           throws SedInconsistentException, SedWritingException
    {
        int newUtype;
        String functionName = "addSamplingPrecisionToHeader";

        // verify the utype is an axis. this is more
        // for maintance to simply ensure if the utypes change
        // that this function doesn't accidently pass
        switch (utype)
        {
            case FitsKeywords.SEG_CHAR_CHARAXIS_SAMPPREC:
            case FitsKeywords.SEG_CHAR_TIMEAXIS_SAMPPREC:
            case FitsKeywords.SEG_CHAR_SPATIALAXIS_SAMPPREC:
            case FitsKeywords.SEG_CHAR_SPECTRALAXIS_SAMPPREC:
            case FitsKeywords.SEG_CHAR_FLUXAXIS_SAMPPREC:
                break;
            default:
                logger.log (Level.WARNING, "The keyword, {0}, cannot be included as part of the SamplingPrecision.", FitsKeywords.getName(utype));
        }

        if (samplingPrecision.isSetSampleExtent ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPEXT, functionName);
/*            if (newUtype == FitsKeywords.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT)
                this.matchKeywordToColumn (FitsKeywords.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT, ?);
*/
            this.addSedParam (samplingPrecision.getSampleExtent (), header, newUtype);
        }
        if (samplingPrecision.isSetSamplingPrecisionRefVal ())
        {
            SamplingPrecisionRefVal samplingPrecisionRefVal =
                          samplingPrecision.getSamplingPrecisionRefVal ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL_FILL, functionName);
            this.addSedParam (samplingPrecisionRefVal.getFillFactor (), header, newUtype);

            this.addCustomParamsToHeader (samplingPrecisionRefVal, header);
        }

        this.addCustomParamsToHeader (samplingPrecision, header);
    }

    /**
     * Add information from the CoordSys to the header of the HDU
     */
    private void addCoordSysToHeader (CoordSys coordSys, Header header)
        throws SedInconsistentException, SedWritingException
    {
        int newUtype;

        if (coordSys.isSetId ())
            this.addSedParam (new TextParam (coordSys.getId ()), header, FitsKeywords.SEG_CS_ID);
        if (coordSys.isSetUcd ())
            this.addSedParam (new TextParam (coordSys.getUcd ()), header, FitsKeywords.SEG_CS_UCD);
        if (coordSys.isSetType ())
            this.addSedParam (new TextParam (coordSys.getType ()), header, FitsKeywords.SEG_CS_TYPE);
        if (coordSys.isSetHref ())
            this.addSedParam (new TextParam (coordSys.getHref ()), header, FitsKeywords.SEG_CS_HREF);

        this.addCustomParamsToHeader (coordSys, header);

        // NOTE: we don't include idref here because it's not clear how to
        // serialize it


        if (coordSys.isSetCoordFrame ())
        {
            for (CoordFrame coordFrame : coordSys.getCoordFrame ())
            {
                if (coordFrame instanceof SpaceFrame)
                {
                    newUtype = FitsKeywords.SEG_CS_SPACEFRAME;
                    if (((SpaceFrame)coordFrame).isSetEquinox ())
                        this.addSedParam (((SpaceFrame)coordFrame).getEquinox (), header, FitsKeywords.SEG_CS_SPACEFRAME_EQUINOX);
                }
                else if (coordFrame instanceof TimeFrame)
                {
                    newUtype = FitsKeywords.SEG_CS_TIMEFRAME;
                    if (((TimeFrame)coordFrame).isSetZero ())
                        this.addSedParam (((TimeFrame)coordFrame).getZero (), header, FitsKeywords.SEG_CS_TIMEFRAME_ZERO);
                }
                else if (coordFrame instanceof SpectralFrame)
                {
                    newUtype = FitsKeywords.SEG_CS_SPECTRALFRAME;
                    if (((SpectralFrame)coordFrame).isSetRedshift ())
                        this.addSedParam (((SpectralFrame)coordFrame).getRedshift (), header, FitsKeywords.SEG_CS_SPECTRALFRAME_REDSHIFT);
                }
                else if (coordFrame instanceof RedshiftFrame)
                {
                    newUtype = FitsKeywords.SEG_CS_REDFRAME;
                    if (((RedshiftFrame)coordFrame).isSetDopplerDefinition ())
                    {
                        boolean keywordMatch = this.matchKeywordToColumn (FitsKeywords.SEG_CS_REDFRAME_DOPPLERDEF, FitsKeywords.SEG_DATA_SPECTRALAXIS_VALUE);
                        if (keywordMatch)
                            this.addSedParam (new TextParam (((RedshiftFrame)coordFrame).getDopplerDefinition ()), header, FitsKeywords.SEG_CS_REDFRAME_DOPPLERDEF);
                        else
                            logger.warning ("The keyword for the 'redshift frame doppler definition' could not be written because the spectral axis column could not be found.");
                    }
                }
                else
                    newUtype = FitsKeywords.SEG_CS_GENFRAME;

                this.addCoordFrameToHeader (coordFrame, header, newUtype);
            }
        }
    }

    /**
     * Add information from the CoordFrame to the header of the HDU
     */
    private void addCoordFrameToHeader (CoordFrame coordFrame, Header header, int utype)
        throws SedInconsistentException, SedWritingException
    {
        int newUtype;
        String functionName = "addCoordFrameToHeader";

        if (coordFrame.isSetId ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CS_GENFRAME_ID, functionName);
            this.addSedParam (new TextParam (coordFrame.getId ()), header, newUtype);
        }
        if (coordFrame.isSetName ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CS_GENFRAME_NAME, functionName);
            this.addSedParam (new TextParam (coordFrame.getName ()), header, newUtype);
        }
        if (coordFrame.isSetReferencePosition ())
        {
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CS_GENFRAME_REFPOS, functionName);
            this.addSedParam (new TextParam (coordFrame.getReferencePosition ()), header, newUtype);
        }
        if (coordFrame.isSetUcd ())
        {
            boolean keywordMatch = true;
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_CS_GENFRAME_UCD, functionName);

            if (coordFrame instanceof SpectralFrame)
            {
                keywordMatch = this.matchKeywordToColumn (FitsKeywords.SEG_CS_SPECTRALFRAME_UCD, FitsKeywords.SEG_DATA_SPECTRALAXIS_VALUE);
                if (!keywordMatch)
                    logger.warning ("The keyword for the 'spectral frame ucd' could not be written because the spectral axis column could not be found.");
            }
            if (keywordMatch)
                this.addSedParam (new TextParam (coordFrame.getUcd ()), header, newUtype);
        }

        this.addCustomParamsToHeader (coordFrame, header);
    }

    /**
     * Add information from the Curation to the header of the HDU
     */
    private void addCurationToHeader (Curation curation, Header header) throws SedInconsistentException, SedWritingException
    {
        if (curation.isSetPublisher ())
            this.addSedParam (curation.getPublisher (), header, FitsKeywords.SEG_CURATION_PUBLISHER);
        if (curation.isSetPublisherID ())
            this.addSedParam (curation.getPublisherID (), header, FitsKeywords.SEG_CURATION_PUBID);
        if (curation.isSetPublisherDID ())
            this.addSedParam (curation.getPublisherDID (), header, FitsKeywords.SEG_CURATION_PUBDID);
        if (curation.isSetReference ())
            this.addSedParam (curation.getReference (), header, FitsKeywords.SEG_CURATION_REF);
        if (curation.isSetVersion ())
            this.addSedParam (curation.getVersion (), header, FitsKeywords.SEG_CURATION_VERSION);
        if (curation.isSetRights ())
            this.addSedParam (curation.getRights (), header, FitsKeywords.SEG_CURATION_RIGHTS);
        if (curation.isSetDate ())
            this.addSedParam (curation.getDate (), header, FitsKeywords.SEG_CURATION_DATE);

        this.addCustomParamsToHeader (curation, header);

        if (curation.isSetContact ())
        {
            Contact contact = curation.getContact ();
            if (contact.isSetName ())
                this.addSedParam (contact.getName (), header, FitsKeywords.SEG_CURATION_CONTACT_NAME);
            if (contact.isSetEmail ())
                this.addSedParam (contact.getEmail (), header, FitsKeywords.SEG_CURATION_CONTACT_EMAIL);

            this.addCustomParamsToHeader (contact, header);
        }

    }

    /**
     * Add information from the DataID to the header of the HDU
     */
    private void addDataIDToHeader (DataID dataID, Header header) throws SedInconsistentException, SedWritingException
    {
        if (dataID.isSetTitle ())
            this.addSedParam (dataID.getTitle (), header, FitsKeywords.SEG_DATAID_TITLE);
        if (dataID.isSetCreator ())
            this.addSedParam (dataID.getCreator (), header, FitsKeywords.SEG_DATAID_CREATOR);
        if (dataID.isSetDatasetID ())
            this.addSedParam (dataID.getDatasetID (), header, FitsKeywords.SEG_DATAID_DATASETID);
        if (dataID.isSetDate ())
            this.addSedParam (dataID.getDate (), header, FitsKeywords.SEG_DATAID_DATE);
        if (dataID.isSetVersion ())
            this.addSedParam (dataID.getVersion (), header, FitsKeywords.SEG_DATAID_VERSION);
        if (dataID.isSetInstrument ())
            this.addSedParam (dataID.getInstrument (), header, FitsKeywords.SEG_DATAID_INSTRUMENT);
        if (dataID.isSetCreationType ())
            this.addSedParam (dataID.getCreationType (), header, FitsKeywords.SEG_DATAID_CREATIONTYPE);
        if (dataID.isSetBandpass ())
            this.addSedParam (dataID.getBandpass (), header, FitsKeywords.SEG_DATAID_BANDPASS);
        if (dataID.isSetCreatorDID ())
            this.addSedParam (dataID.getCreatorDID (), header, FitsKeywords.SEG_DATAID_CREATORDID);
        if (dataID.isSetLogo ())
            this.addSedParam (dataID.getLogo (), header, FitsKeywords.SEG_DATAID_LOGO);
        if (dataID.isSetDataSource ())
            this.addSedParam (dataID.getDataSource (), header, FitsKeywords.SEG_DATAID_DATASOURCE);
        if (dataID.isSetCollection())
        {
            List<TextParam> collection = dataID.getCollection();
            String keyword = FitsKeywords.getKeyword(FitsKeywords.SEG_DATAID_COLLECTION);
	    if ( collection.size() == 1 )
	    {
		// only one.. write as scalar key
		this.addSedParam(collection.get(0), header, keyword);
	    }
	    else
	    {
		// write as array key
		for (int ii=0; ii < collection.size(); ii++)
		    this.addSedParam(collection.get(ii), header, keyword+(ii+1));
	    }
        }
        if (dataID.isSetContributor())
        {
            List<TextParam> collection = dataID.getContributor ();
            String keyword = FitsKeywords.getKeyword(FitsKeywords.SEG_DATAID_CONTRIBUTOR);
	    if ( collection.size() == 1 )
	    {
		// only one.. write as scalar key
		this.addSedParam(collection.get(0), header, keyword);
	    }
	    else
	    {
		// write as array key
		for (int ii=0; ii < collection.size (); ii++)
		    this.addSedParam (collection.get(ii), header, keyword+(ii+1));
	    }
        }

        this.addCustomParamsToHeader (dataID, header);
    }

    /**
     * Add information from the Derived to the header of the HDU
     */
    private void addDerivedToHeader (DerivedData derived, Header header) throws SedInconsistentException, SedWritingException
    {
        if (derived.isSetSNR ())
            this.addSedParam (derived.getSNR (), header, FitsKeywords.SEG_DD_SNR);
        if (derived.isSetVarAmpl ())
            this.addSedParam (derived.getVarAmpl (), header, FitsKeywords.SEG_DD_VARAMPL);

        this.addCustomParamsToHeader (derived, header);

        if (derived.isSetRedshift ())
        {
            SedQuantity redshift = derived.getRedshift ();

            if (redshift.isSetValue ())
                this.addSedParam (redshift.getValue (), header, FitsKeywords.SEG_DD_REDSHIFT_VALUE);
            if (redshift.isSetResolution ())
                this.addSedParam (redshift.getResolution (), header, FitsKeywords.SEG_DD_REDSHIFT_RESOLUTION);
            if (redshift.isSetQuality ())
                this.addSedParam (redshift.getQuality (), header, FitsKeywords.SEG_DD_REDSHIFT_QUALITY);
            if (redshift.isSetAccuracy ())
                this.addAccuracyToHeader (redshift.getAccuracy (), header, FitsKeywords.SEG_DD_REDSHIFT_ACC);

            this.addCustomParamsToHeader (redshift, header);
        }
    }

    /**
     * Add information from the custom params to the header of the HDU
     */
    private void addCustomParamsToHeader (Group group, Header header) throws SedInconsistentException, SedWritingException
    {
        List<? extends Param> params = group.getCustomParams ();
        List<? extends Group> groups = group.getCustomGroups ();

        // add custom params to header use the id as the keyword
        for (Param param : params)
        {
            if (param.isSetName ())
                this.addSedParam (param, header, param.getName ());
            else if (param.isSetId())
                this.addSedParam (param, header, param.getId ());
            else if (param.isSetInternalId ())
                this.addSedParam (param, header, param.getInternalId ());
            else
            	logger.warning ("A parameter was found that has no id or name. It will not be serialized.");
        }

        // flatten out the groups and add all custom params
        for (Group grp : groups)
            this.addCustomParamsToHeader (grp, header);
    }

    /**
     *  There are a number of FITS keywords whose values may be found in
     *  either the characterization or the data; we want the values on the 
     *  data to take precedence.  These 5 fields are described in the 
     *  SpectrumDM document in utype table (Table 1) whose fits keywords are
     *  define as "(as Data)"
     */
    private void overrideKeywords( ArrayOfPoint data, Header header ) throws SedInconsistentException, SedWritingException
    {

    	List<Point> pointData;

        if (data == null)
            return;

        pointData = data.getPoint ();
        
        // Iterate over all the data and if we find any of the fields of 
        // interest to be set, add the value to the header (overwriting any
        // value that may have  been there).  Note that if a value is set
        // on more than one data point, the last guy wins.
        for (Point point : pointData)
        {
            if (point.isSetFluxAxis ())
            {
                SedQuantity fluxAxis = point.getFluxAxis ();
                if (fluxAxis.isSetValue ())
                {
                    DoubleParam param = fluxAxis.getValue();

                    if (param.isSetName ())
                        this.addSedParam (new TextParam (param.getName ()), header, FitsKeywords.SEG_CHAR_FLUXAXIS_NAME);
                    if (param.isSetUnit ())
                        this.addSedParam (new TextParam (param.getUnit ()), header, FitsKeywords.SEG_CHAR_FLUXAXIS_UNIT);
                    if (param.isSetUcd ())
                        this.addSedParam (new TextParam (param.getUcd ()), header, FitsKeywords.SEG_CHAR_FLUXAXIS_UCD);
                    try
                    {
                        param = fluxAxis.getAccuracy().getSysError();
                        this.addSedParam (param, header, FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_SYSERR);
                    }
                    catch ( NullPointerException exp )
                    { } // drop it, some intermediate 'get' call returned null.

                    try
                    {
                        param = fluxAxis.getAccuracy().getStatError();
                        this.addSedParam (param, header, FitsKeywords.SEG_CHAR_FLUXAXIS_ACC_STATERR);
                    }
                    catch ( NullPointerException exp )
                    { } // drop it, some intermediate 'get' call returned null.
                }

            }
            if (point.isSetSpectralAxis ())
            {
                SedCoord spectralAxis = point.getSpectralAxis ();
                DoubleParam param;
                
                if (spectralAxis.isSetValue ())
                {
                    param = spectralAxis.getValue();
                    if (param.isSetName ())
                        this.addSedParam (new TextParam (param.getName ()), header, FitsKeywords.SEG_CHAR_SPECTRALAXIS_NAME);
                    if (param.isSetUnit ())
                        this.addSedParam (new TextParam (param.getUnit ()), header, FitsKeywords.SEG_CHAR_SPECTRALAXIS_UNIT);
                    if (param.isSetUcd ())
                        this.addSedParam (new TextParam (param.getUcd ()), header, FitsKeywords.SEG_CHAR_SPECTRALAXIS_UCD);
                }
            }
            if (point.isSetTimeAxis ())
            {
                SedCoord timeAxis = point.getTimeAxis ();
                DoubleParam param;

                try
                {
                    param = timeAxis.getAccuracy().getSysError();
                    this.addSedParam (param, header, FitsKeywords.SEG_CHAR_TIMEAXIS_ACC_SYSERR);
                }
                catch ( NullPointerException exp )
                { } // drop it, some intermediate 'get' call returned null. 
               
                try
                {
                    param = timeAxis.getAccuracy().getStatError();
                    this.addSedParam (param, header, FitsKeywords.SEG_CHAR_TIMEAXIS_ACC_STATERR);
                }
                catch ( NullPointerException exp )
                { } // drop it, some intermediate 'get' call returned null. 


                try
                {
                    param = timeAxis.getResolution();
                    this.addSedParam (param, header, FitsKeywords.SEG_CHAR_TIMEAXIS_RESOLUTION);
                }
                catch ( NullPointerException exp )
                { } // drop it, some intermediate 'get' call returned null. 
            }
        }
    }


    /**
     * Extract all data and column metadata into a generic table. Also
     * calculates the number of columns which are going to be used.
     */
    private void extractDataTable (ArrayOfPoint data) throws SedInconsistentException
    {

        List<Point> pointList;
        Point point;

        pointList = data.getPoint ();

        if ((pointList == null) || (pointList.isEmpty()))
            return;

        this.numPoints = pointList.size ();

        this.dataTable = new HashMap <String, Column> ();
        this.columnOrder = new Vector <String> ();

        // extract the point information values and create a table
        for (int row=0; row<this.numPoints; row++)
        {
            point = pointList.get (row);

            if (point.isSetTimeAxis ())
            {
                this.processSedCoordData (point.getTimeAxis (),
                                          row,
                                          FitsKeywords.SEG_DATA_TIMEAXIS);

            }
            if (point.isSetSpectralAxis ())
            {
                this.processSedCoordData (point.getSpectralAxis (),
                                          row,
                                          FitsKeywords.SEG_DATA_SPECTRALAXIS);
            }
            if (point.isSetFluxAxis ())
            {
                this.processSedQuantityData (point.getFluxAxis (),
                                          row,
                                          FitsKeywords.SEG_DATA_FLUXAXIS);
            }
            if (point.isSetBackgroundModel ())
            {
                this.processSedQuantityData (point.getBackgroundModel (),
                                          row,
                                          FitsKeywords.SEG_DATA_BGM);
            }

            processCustomData (point, row);
            
        }

        // number of columns 
        this.numFields = this.columnOrder.size ();
    }

    /**
     * Add the column meta data to the header. This is retrieved from
     * table of columns extracted from the segment.
     */
    private void addDataToHeader (Header header) throws SedWritingException
    {
        String keyword; 
        Column column;
        String colId;

        // go through the columns and add the respective
        // keywords to the header
        for (int col=0; col<this.numFields; col++)
        {
        	
            colId = this.columnOrder.get (col);
            column = this.dataTable.get (colId);
            try
            {
                keyword = ParamInfoKeys.NAME.keyword+(col+1);
                header.addValue (keyword, column.name, null);

                if ((column.utype != null) && (column.utype.length () > 0))
                {
                    keyword = ParamInfoKeys.UTYPE.keyword+(col+1);
                    header.addValue (keyword, column.utype, null);
                }
                if ((column.ucd != null) && (column.ucd.length () > 0))
                {
                    keyword = ParamInfoKeys.UCD.keyword+(col+1);
                    header.addValue (keyword, column.ucd, null);
                }

                if ((column.unit != null) && (column.unit.length () > 0))
                {
                    keyword = ParamInfoKeys.UNIT.keyword+(col+1);
                    header.addValue (keyword, column.unit, null);
                }

            }
            catch (HeaderCardException exp)
            {
        	throw new SedWritingException (exp.getMessage(), exp);
            }
        }
    }
     
    /**
     * Add columns data to a binary table.
     */
    private void addDataToTable (BinaryTable binaryTable) throws SedWritingException
    {
        if ((this.numFields == 0) || (this.numPoints == 0))
            return;

        int iCol[][] = new int[1][this.numPoints];
        double dCol[][] = new double[1][this.numPoints];
        String sCol[][] = new String[1][this.numPoints];
        Column column;
        String colId;

        // go through the columns and add the respective
        // keywords to the header
        for (int col=0; col<this.numFields; col++)
        {
            colId = this.columnOrder.get (col);
            column = this.dataTable.get (colId);
            try
            {
                if (column.dataClass == Double.class)
                {
                    for (int ii=0; ii<this.numPoints; ii++)
                        dCol[0][ii] = (Double)(column.data.get(ii));
                    binaryTable.addColumn (dCol);
                }
                else if (column.dataClass == Integer.class)
                {
                    for (int ii=0; ii<this.numPoints; ii++)
                        iCol[0][ii] = (Integer)(column.data.get(ii));
                    binaryTable.addColumn (iCol);
                }
                else if (column.dataClass == String.class)
                {
                    for (int ii=0; ii<this.numPoints; ii++)
                        sCol[0][ii] = (String)(column.data.get(ii));
                    binaryTable.addColumn (sCol);
                }


            }
            catch (Exception exp)
            {
                throw new SedWritingException (exp.getMessage(), exp);
            }
        }

    }

    /**
     * Extract the data and metadata from the SedCoord into the data table.
     */
    private void processSedCoordData ( SedCoord coord,
                                  int row,
                                  int utype) throws SedInconsistentException

    {
        Param param;
        int newUtype;
        String functionName =  "processSedCoordData";

        if (coord.isSetValue ())
        {
            param = coord.getValue ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_TIMEAXIS_VALUE, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (coord.isSetResolution ())
        {
            param = coord.getResolution ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_TIMEAXIS_RESOLUTION, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (coord.isSetAccuracy ())
        {
            Accuracy accuracy = coord.getAccuracy ();

            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_TIMEAXIS_ACC, functionName);

            this.processAccuracyData (accuracy, row, newUtype);
        }

        processCustomData (coord, row);
    }

    /**
     * Extract the data and metadata from the SedQuantity into the data table.
     */
    private void processSedQuantityData ( SedQuantity quantity,
                                  int row,
                                  int utype) throws SedInconsistentException

    {
        Param param;
        int newUtype;
        String functionName = "processSedQuantityData";

        if (quantity.isSetValue ())
        {
            param = quantity.getValue ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_VALUE, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (quantity.isSetResolution ())
        {
            param = quantity.getResolution ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_RESOLUTION, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (quantity.isSetQuality ())
        {
            param = quantity.getQuality ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_QUALITY, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (quantity.isSetAccuracy ())
        {
            Accuracy accuracy = quantity.getAccuracy ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC, functionName);
            this.processAccuracyData (accuracy, row, newUtype);
        }

        processCustomData (quantity, row);

    }


    /**
     * Extract the data and metadata from the Accuracy into the data table.
     */
    private void processAccuracyData (Accuracy accuracy,
                                  int row,
                                  int utype) throws SedInconsistentException
    {

        Param param;
        int newUtype;
        String functionName = "processAccuracyData";


        if (accuracy.isSetBinLow ())
        {
            param = accuracy.getBinLow ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINLOW, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (accuracy.isSetBinHigh ())
        {
            param = accuracy.getBinHigh ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINHIGH, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (accuracy.isSetBinSize ())
        {
            param = accuracy.getBinSize ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC_BINSIZE, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (accuracy.isSetStatError ())
        {
            param = accuracy.getStatError ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERR, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);

        }
        if (accuracy.isSetStatErrLow ())
        {
            param = accuracy.getStatErrLow ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRLOW, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (accuracy.isSetStatErrHigh ())
        {
            param = accuracy.getStatErrHigh ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRHIGH, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (accuracy.isSetSysError ())
        {
            param = accuracy.getSysError ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC_SYSERR, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }
        if (accuracy.isSetConfidence ())
        {
            param = accuracy.getConfidence ();
            newUtype = this.mergeUtypes (utype, FitsKeywords.SEG_DATA_FLUXAXIS_ACC_CONFIDENCE, functionName);
            this.addColumn (param, FitsKeywords.getName (newUtype), newUtype, row);
        }

        processCustomData (accuracy, row);
    }

    
    private void processCustomData (Group parent,
                                    int row)
    {

        List<? extends Param> params = parent.getCustomParams ();
        List<? extends Group> groups = parent.getCustomGroups ();

        for (Param param : params)
        {

            if (!param.isSetInternalId ())
                logger.warning ("A column parameter was found that has no id. It will not be serialized.");

            this.addColumn (param, param.getInternalId (), FitsKeywords.INVALID_UTYPE, row);
        }
        
 
        for (Group group : groups)
            this.processCustomData (group, row);
    }


    /**
     * Finish adding the specified Param to the HDU header.
     */
    private void finalizeAddSedParam(Param param, Header header, String keyword) throws HeaderCardException
    {
        // extract the units from the parameter
        String value = param.getValue ();

        if (keyword.equalsIgnoreCase ("HISTORY"))
            header.insertHistory (value);
        else if (keyword.equalsIgnoreCase ("COMMENT"))
            header.insertComment (value);
        else if (keyword.equalsIgnoreCase (""))
            header.insertCommentStyle(keyword, value);
        else
            header.addValue(keyword, value, null);
    }


    /**
     * Finish adding the specified TimeParam to the HDU header.
     */
    private void finalizeAddSedParam(TimeParam param, Header header, String keyword) throws HeaderCardException
    {
        // extract the units from the parameter
        String comment = null;
        if (param.isSetUnit ())
            comment =  "[" + param.getUnit () + "]";

        String value = param.getValue ();

        header.addValue(keyword, value, comment);
    }



    /**
     * Finish adding the specified DoubleParam to the HDU header.
     */
    private void finalizeAddSedParam(DoubleParam param, Header header, String keyword) throws HeaderCardException
    {
        String comment = null;

        // extract the units from the parameter
        if ( param.isSetUnit() && !param.getUnit().isEmpty() )
            comment =  "[" + param.getUnit() + "]";

        Double value = (Double)param.getCastValue();

	/* MCD TEMP NOTE:
	   Need to handle NaN values specifically.
	   This FITS module will actually a NaN keyword value, which is not legal FITS.
	*/
	if ( value.isNaN() )
	    header.addValue(keyword, null, comment);
	else
	    header.addValue(keyword, value.doubleValue(), comment);
    }

    /**
     * Add the specified IntParam to the HDU header.
     */
    private void finalizeAddSedParam(IntParam param, Header header, String keyword) throws HeaderCardException
    {

        // extract the units from the parameter
        String comment = null;
        if (param.isSetUnit ())
            comment =  "[" + param.getUnit () + "]";

        int value = ((Integer)param.getCastValue ());

        header.addValue(keyword, value, comment);
    }

    /**
     * Add the specified generic Param to the HDU header.
     */
    private void addSedParam(Param param, Header header, int utype) throws SedInconsistentException, SedWritingException
    {
        String keyword;
        if (utype == FitsKeywords.INVALID_UTYPE)
            throw new SedInconsistentException ("addSedParam: Could not add parameter. There was an invalid utype");

        keyword = FitsKeywords.getKeyword(utype);

        // if the keyword ends in an 'n' then replace it with the column number
        if (this.overrides.containsKey (utype))
            keyword = overrides.get(utype);


        this.addSedParam (param, header, keyword);

    }

    /**
     * Add the specified generic Param to the HDU header. This also allows for
     * keyword to be specified (opposed to just using the default)
     */
    private void addSedParam(Param param, Header header, String keyword) throws SedInconsistentException, SedWritingException
    {
        if (!param.isSetValue ())
        {
	    String msg = String.format("The parameter '%1$s', does not have a value set and will be ignored.", keyword );
            logger.log (Level.WARNING, msg);
            return;
        }

        // if the keyword starts with todo then no keyword has been
        // defined for the enumeration
        if (keyword.startsWith ("_todo"))
            return;

        keyword = keyword.toUpperCase();
        try
        {
	    if (param instanceof IntParam)
		this.finalizeAddSedParam ((IntParam)param, header, keyword);
	    else if (param instanceof DoubleParam)
		this.finalizeAddSedParam ((DoubleParam)param, header, keyword);
	    else if (param instanceof TimeParam)
		this.finalizeAddSedParam ((TimeParam)param, header, keyword);
	    else
		this.finalizeAddSedParam (param, header, keyword);
        }
        catch (HeaderCardException exp)
        {
	    String msg = String.format("Failed to add keyword '%1$s' to header; value = XX%2$sXX. FITS exception msg = \"%3$s\".", keyword, param.getValue(), exp.getMessage());
	    logger.log(Level.WARNING, msg );
        }
    }

    /**
     * Combine two utypes into a single utype. Throw an error if the merged
     * utype is invalid.
     */
    private int mergeUtypes (int baseUtype, int suffixUtype, String function)
        throws SedInconsistentException
    {

        int newUtype = FitsKeywords.mergeUtypes (baseUtype, suffixUtype);
        if (newUtype == FitsKeywords.INVALID_UTYPE)
            throw new SedInconsistentException (function + ": Invalid utype created through merge. Merged types "+FitsKeywords.getName (baseUtype) + ", " + FitsKeywords.getName (suffixUtype));

        return newUtype;
    }

    /**
     * Update a column in the data table with information from the Param.
     * If the column does not exist in the table then a new one is created.
     */
    private void addColumn (Param param, String id, int utype, int row)
        
    {
        Column column;


        if (!this.dataTable.containsKey (id))
        {
            column = new Column();
            this.dataTable.put (id, column);
            this.columnOrder.add (id);
            

            if (param.isSetName ())
                column.name = param.getName ();
            else if (utype != FitsKeywords.INVALID_UTYPE)
            {   
                if (this.overrides.containsKey (utype))
                    column.name = this.overrides.get (utype);
                else
                {
                    column.name = FitsKeywords.getDefaultColumnName (utype);

                    // if the column doesn't have a default name it can't be added
                    if (column.name ==  null)
                    {
                        logger.log (Level.WARNING, "The utype, {0} does not have a column name associated with it. This column will be ignored", FitsKeywords.getName(utype));
                        return;
                    }
                }
            }
            else
                column.name = id;
        }
        else
            column = this.dataTable.get (id);

      
            
        if (param.isSetUcd ())
            column.ucd = param.getUcd ();
        else if (this.ucdOverrides.containsKey (utype))
            column.ucd = this.ucdOverrides.get (utype);
        else if (utype != FitsKeywords.INVALID_UTYPE)
            column.ucd = FitsKeywords.getUcd (utype);

        if (utype != FitsKeywords.INVALID_UTYPE)
            column.utype = FitsKeywords.getName (utype);

        if (param instanceof DoubleParam)
        {
            column.unit = ((DoubleParam)param).getUnit ();
            column.dataClass = Double.class;

            if (column.data == null)
            {
               column.data = new ArrayList<Double>(this.numPoints);
               for (int ii=0; ii<this.numPoints; ii++)
                  column.data.add(SedConstants.DEFAULT_DOUBLE);
            }

            if (param.isSetValue ())
                column.data.set (row, (Double)(param.getCastValue ()));
               
        }
        else if (param instanceof IntParam)
        {
            column.unit = ((IntParam)param).getUnit ();

            column.dataClass = Integer.class;


            if (column.data == null)
            {
               column.data = new ArrayList<Integer>(this.numPoints);
               for (int ii=0; ii<this.numPoints; ii++)
                  column.data.add(SedConstants.DEFAULT_INTEGER);
            }

            if (param.isSetValue ())
                column.data.set (row, (Integer)(param.getCastValue ()));


        }
        else
        {
            column.dataClass = String.class;
        
            if (column.data == null)
            {
               column.data = new ArrayList<String>(this.numPoints);
               for (int ii=0; ii<this.numPoints; ii++)
                  column.data.add(SedConstants.DEFAULT_STRING);
            }

            if (param.isSetValue ())
                column.data.set (row, param.getValue ());

        }
    }

    /**
     * Replace keywords variable 'n' with the appropriate column number.
     */
    private boolean matchKeywordToColumn (int keywordUtype, int columnUtype)
    {

        String newKeyword = null;
        boolean keywordSet = false;

        if (this.columnOrder == null)
            return keywordSet;

        for (int ii=0; ii<this.columnOrder.size (); ii++)
        {
      
            int utype = FitsKeywords.getUtypeFromString (this.columnOrder.get(ii));
            if (utype == columnUtype)
            {
                // replace "n" with the column number
                newKeyword = FitsKeywords.getKeyword (keywordUtype);
                newKeyword = newKeyword.replace ("n", Integer.toString (ii+1));
                this.overrides.put (keywordUtype, newKeyword); 
                keywordSet = true;
                break;
            }
        }

        return keywordSet;
    }

    /**
     * Update column names and ucd's which are dependent on other 
     * column information.
     */
    private void overrideDataColumnInfo (Segment segment)
    {
        ArrayOfPoint data;
        List<Point> pointList;
        String spectralAxisName = null;

        if (!segment.isSetData ())
            return;

        data = segment.getData();
        pointList = data.getPoint ();

        if(pointList==null)
            return;

        if (pointList.isEmpty())
            return;

        for (Point point : pointList)
        {
            if (point.isSetSpectralAxis () && point.getSpectralAxis().isSetValue())
            {
                Param spectralAxis = point.getSpectralAxis ().getValue ();
                String defaultName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_VALUE);
                String overrideName;

                // replace the spectral sub components with the spectral name
                if (spectralAxis.isSetName ())
                {
                	
                    // override the names
                    spectralAxisName = spectralAxis.getName ();
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_VALUE, spectralAxisName);
                    overrideName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINSIZE);
                    overrideName = overrideName.replace (defaultName, spectralAxisName);
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINSIZE, overrideName);
                    overrideName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINLOW);
                    overrideName = overrideName.replace (defaultName, spectralAxisName);
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINLOW, overrideName);
                    overrideName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINHIGH);
                    overrideName = overrideName.replace (defaultName, spectralAxisName);
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_BINHIGH, overrideName);
                    overrideName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERR);
                    overrideName = overrideName.replace (defaultName, spectralAxisName);
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERR, overrideName);
                    overrideName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW);
                    overrideName = overrideName.replace (defaultName, spectralAxisName);
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW, overrideName);
                    overrideName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH);
                    overrideName = overrideName.replace (defaultName, spectralAxisName);
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH, overrideName);
                    overrideName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_SYSERR);
                    overrideName = overrideName.replace (defaultName, spectralAxisName);
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_ACC_SYSERR, overrideName);
                    overrideName = FitsKeywords.getDefaultColumnName (FitsKeywords.SEG_DATA_SPECTRALAXIS_RESOLUTION);
                    overrideName = overrideName.replace (defaultName, spectralAxisName);
                    this.overrides.put (FitsKeywords.SEG_DATA_SPECTRALAXIS_RESOLUTION, overrideName);
                }
            }
        }

        // go through the ucds and replace variable components
        if (spectralAxisName != null)
        {
            for (int ii=0; ii<FitsKeywords.getNumberOfUtypes (); ii++)
            {

                // update the spectral axis ucds with the spectral axis name
                String ucd = FitsKeywords.overrideUcd (ii, "em", spectralAxisName);
                if (ucd != null)
                    this.ucdOverrides.put (ii, ucd);
            }
        }

    }

    /**
     * Set the header to the end of the keywords
     **/
    private void setHeaderCursor (Header header)
    {
    	
    	//TODO This is a hack to push the header to the end. The front
    	// of the header must be the basic header for the module to validate
    	// correctly. Go to the of data. Changes to the fits module may
    	// render this code useless or even incorrect.
    	Cursor cursor = header.iterator();
    	String key = "";
    	while (cursor.hasNext())
    	{
    		key = ((HeaderCard)cursor.next()).getKey();
    		// stop right after the field to try and keep column cards together
    		if (key.equalsIgnoreCase("TFIELDS") && cursor.hasNext())
    		{
    			key = ((HeaderCard)cursor.next()).getKey();
    			break;
    		}
    	}
    	header.findCard (key);
    	
    }
}
