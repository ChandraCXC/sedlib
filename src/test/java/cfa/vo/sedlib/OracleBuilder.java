/***********************************************************************
*
* File: OracleBuilder.java
*
* Author:  jmiller              Created: Tue Apr 12 10:29:22 2011
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
* 
***********************************************************************/

package cfa.vo.sedlib;

import java.util.List;
import cfa.vo.testtools.Oracle;


public class OracleBuilder
{

    /**
     * Build a new the oracle from the sed

    static public Oracle  buildFromSed (Sed sed)
    {
        Oracle oracle = new Oracle ();
        OracleBuilder.populateWithSed (sed, oracle, "");
        return oracle;
    }
*/


    /**
     * Build a new the oracle from the segment
     */
    static public Oracle  buildFromSegment (Segment segment)
    {
        Oracle oracle = new Oracle ();
        OracleBuilder.populateWithSegment ("", segment, oracle);
        return oracle;
    }


    /**
     * Populate the oracle from the segment
     *

NOTE: Currently doesn't work because no getSegments needed by the oracle

    static public void  populateWithSegment (String prefix, Sed sed, Oracle oracle)
    {
        if (sed == null)
            return;

        if (sed.isSetNamespace ()) 
            oracle.put (prefix+"namespace",sed.getNamespace ());

        List<Segments> segments = sed.getSegment ();
        for (int ii=0; ii<segments.size (); ii++)
            OracleBuilder.populateWithSegment (prefix+"segment."+ii, segment.get(ii), oracle);
            
    }
    */


    /**
     * Populate the oracle from the segment
     */
    static public void  populateWithSegment (String prefix, Segment segment, Oracle oracle)
    {
        if (segment == null)
            return;

        OracleBuilder.populateWithParam (prefix+"type.",segment.getType (), oracle);
        OracleBuilder.populateWithParam (prefix+"timeSI.",segment.getTimeSI (), oracle);
        OracleBuilder.populateWithParam (prefix+"spectralSI.",segment.getSpectralSI (), oracle);
        OracleBuilder.populateWithParam (prefix+"fluxSI.",segment.getFluxSI (), oracle);

        OracleBuilder.populateWithTarget (prefix+"target.", segment.getTarget (), oracle);
        OracleBuilder.populateWithCharacterization (prefix+"char.", segment.getChar (), oracle);
        OracleBuilder.populateWithCoordSys (prefix+"coordSys.", segment.getCoordSys (), oracle);
        OracleBuilder.populateWithCuration (prefix+"curation.", segment.getCuration (), oracle);
        OracleBuilder.populateWithDataID (prefix+"dataID.", segment.getDataID (), oracle);
        OracleBuilder.populateWithDerived (prefix+"derived.", segment.getDerived (), oracle);
        OracleBuilder.populateWithArrayOfPoint (prefix+"data.", segment.getData (), oracle);

    }


    /**
     * Populate the oracle from the target
     */
    static public void  populateWithTarget (String prefix, Target target, Oracle oracle)
    {
        if (target == null)
            return;

        OracleBuilder.populateWithParam (prefix+"name.",target.getName (), oracle);
        OracleBuilder.populateWithParam (prefix+"description.",target.getDescription (), oracle);
        OracleBuilder.populateWithParam (prefix+"targetClass.",target.getTargetClass (), oracle);
        OracleBuilder.populateWithParam (prefix+"spectralClass.",target.getSpectralClass (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"redshift.",target.getRedshift (), oracle);
        OracleBuilder.populateWithPositionParam (prefix+"pos.",target.getPos (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"varAmpl.",target.getVarAmpl (), oracle);
    }


    /**
     * Populate the oracle from the characterization
     */
    static public void  populateWithCharacterization (String prefix, Characterization _char, Oracle oracle)
    {
        if (_char == null)
            return;

        OracleBuilder.populateWithCharacterizationAxis (prefix+"spatialAxis.", _char.getSpatialAxis (), oracle);
        OracleBuilder.populateWithCharacterizationAxis (prefix+"timeAxis.", _char.getTimeAxis (), oracle);
        OracleBuilder.populateWithSpectralCharacterizationAxis (prefix+"spectralAxis.", _char.getSpectralAxis (), oracle);
        OracleBuilder.populateWithCharacterizationAxis (prefix+"fluxAxis.", _char.getFluxAxis (), oracle);


        if (_char.isSetCharacterizationAxis ())
        {
            List<CharacterizationAxis> axes = _char.getCharacterizationAxis ();
            for (int ii=0; ii<axes.size (); ii++)
                OracleBuilder.populateWithCharacterizationAxis (prefix+"characterizationAxis."+ii+".", axes.get(ii), oracle);
        }

    }

    /**
     * Populate the oracle from the characterization axis
     */
    static public void  populateWithCharacterizationAxis (String prefix, CharacterizationAxis axis, Oracle oracle)
    {
        if (axis == null)
            return;

        oracle.put (prefix+"name", axis.getName ());
        oracle.put (prefix+"ucd", axis.getUcd ());
        oracle.put (prefix+"unit", axis.getUnit ());
        OracleBuilder.populateWithDoubleParam (prefix+"resolution.", axis.getResolution (), oracle);
        OracleBuilder.populateWithParam (prefix+"calibration.", axis.getCalibration (), oracle);

        OracleBuilder.populateWithAccuracy (prefix+"accuracy.", axis.getAccuracy (), oracle);
        OracleBuilder.populateWithSamplingPrecision (prefix+"samplingPrecision.", axis.getSamplingPrecision (), oracle);
        OracleBuilder.populateWithCoordSys (prefix+"coordSystem.", axis.getCoordSystem (), oracle);
        OracleBuilder.populateWithCoverage (prefix+"coverage.", axis.getCoverage (), oracle);

    }

    /** 
     * Populate the oracle from the spectral characterization axis
     */
    static public void  populateWithSpectralCharacterizationAxis (String prefix, SpectralCharacterizationAxis axis, Oracle oracle)
    {
        if (axis == null)
            return;

        OracleBuilder.populateWithDoubleParam (prefix+"resPower.", axis.getResPower (), oracle);

        OracleBuilder.populateWithCharacterizationAxis (prefix, axis, oracle);
    }

    /**
     * Populate the oracle from the coord sys
     */
    static public void  populateWithCoordSys (String prefix, CoordSys coordsys, Oracle oracle)
    {
        if (coordsys == null)
            return;

        oracle.put (prefix+"id", coordsys.getId ());
        oracle.put (prefix+"ucd", coordsys.getUcd ());
        oracle.put (prefix+"type", coordsys.getType ());
        oracle.put (prefix+"href", coordsys.getHref ());

        if (coordsys.isSetCoordFrame ())
        {
            List<CoordFrame> coordFrames = coordsys.getCoordFrame ();

            for (int ii=0; ii<coordFrames.size (); ii++)
            {
                OracleBuilder.populateWithCoordFrame (prefix+"coordFrame."+ii+".", coordFrames.get(ii), oracle);
            }
        }
    }


    /**
     * Populate the oracle from the coord frame
     */
    static public void  populateWithCoordFrame (String prefix, CoordFrame coordFrame, Oracle oracle)
    {
        if (coordFrame == null)
            return;

        oracle.put (prefix+"id", coordFrame.getId ());
        oracle.put (prefix+"ucd", coordFrame.getUcd ());
        oracle.put (prefix+"name", coordFrame.getName ());
        oracle.put (prefix+"referencePosition", coordFrame.getReferencePosition ());
    }



    /**
     * Populate the oracle from the curation
     */
     static public void  populateWithCuration (String prefix, Curation curation, Oracle oracle)
     {
        if (curation == null)
            return;

        OracleBuilder.populateWithParam (prefix+"publisher.", curation.getPublisher (), oracle);
        OracleBuilder.populateWithParam (prefix+"publisherID.", curation.getPublisherID (), oracle);
        OracleBuilder.populateWithParam (prefix+"reference.", curation.getReference (), oracle);
        OracleBuilder.populateWithParam (prefix+"version.", curation.getVersion (), oracle);
        OracleBuilder.populateWithParam (prefix+"date.", curation.getDate (), oracle);
        OracleBuilder.populateWithParam (prefix+"publisherDID.", curation.getPublisherDID (), oracle);

        OracleBuilder.populateWithContact (prefix+"contact.", curation.getContact (), oracle);
    }


    /**
     * Populate the oracle from the contact
     */
    static public void  populateWithContact (String prefix, Contact contact, Oracle oracle)
    {
        if (contact == null)
            return;

        OracleBuilder.populateWithParam (prefix+"name.", contact.getName (), oracle);
        OracleBuilder.populateWithParam (prefix+"email.", contact.getEmail (), oracle);
    }


    /**
     * Populate the oracle from the dataID
     */
    static public void  populateWithDataID (String prefix, DataID dataID, Oracle oracle)
    {
        if (dataID == null)
            return;


        OracleBuilder.populateWithParam (prefix+"title.", dataID.getTitle (), oracle);
        OracleBuilder.populateWithParam (prefix+"creator.", dataID.getCreator (), oracle);
        OracleBuilder.populateWithParam (prefix+"datasetID.", dataID.getDatasetID (), oracle);
        OracleBuilder.populateWithParam (prefix+"date.", dataID.getDate (), oracle);
        OracleBuilder.populateWithParam (prefix+"version.", dataID.getVersion (), oracle);
        OracleBuilder.populateWithParam (prefix+"instrument.", dataID.getInstrument (), oracle);
        OracleBuilder.populateWithParam (prefix+"creationType.", dataID.getCreationType (), oracle);
        OracleBuilder.populateWithParam (prefix+"bandpass.", dataID.getBandpass (), oracle);
        OracleBuilder.populateWithParam (prefix+"creatorDID.", dataID.getCreatorDID (), oracle);
        OracleBuilder.populateWithParam (prefix+"logo.", dataID.getLogo (), oracle);
        OracleBuilder.populateWithParam (prefix+"dataSource.", dataID.getDataSource (), oracle);

        if (dataID.isSetCollection ())
        {
            List<TextParam> collection = dataID.getCollection ();
            for (int ii=0; ii<collection.size (); ii++)
                OracleBuilder.populateWithParam (prefix+"collection."+ii+".", collection.get (ii), oracle);
        }

        if (dataID.isSetContributor ())
        {   
            List<TextParam> contributors = dataID.getContributor ();
            for (int ii=0; ii<contributors.size (); ii++)
                OracleBuilder.populateWithParam (prefix+"contributor."+ii+".", contributors.get (ii), oracle);
        }
    }

    /**
     * Populate the oracle from the derived 
     */
    static public void  populateWithDerived (String prefix, DerivedData derived, Oracle oracle)
    {
        if (derived == null)
            return;

        OracleBuilder.populateWithDoubleParam (prefix+"SNR.", derived.getSNR (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"varAmpl.", derived.getVarAmpl (), oracle);

        OracleBuilder.populateWithSedQuantity (prefix+"redshift.",derived.getRedshift (), oracle);
    }


 
    /**
     * Populate the oracle from the array of points
     */
    static public void  populateWithArrayOfPoint (String prefix, ArrayOfPoint points, Oracle oracle)
    {
        if (points == null)
            return;

        if (points.isSetPoint ())
        {
            List<Point> point = points.getPoint ();
            for (int ii=0; ii<point.size (); ii++)
                OracleBuilder.populateWithPoint (prefix+"point."+ii+".", point.get (ii), oracle);
        }
    }

    /**
     * Populate the oracle from the point
     */
    static public void  populateWithPoint (String prefix, Point point, Oracle oracle)
    {
        if (point == null)
            return;

        OracleBuilder.populateWithSedCoord (prefix+"timeAxis.", point.getTimeAxis (), oracle);
        OracleBuilder.populateWithSedCoord (prefix+"spectralAxis.", point.getSpectralAxis (), oracle);
        OracleBuilder.populateWithSedQuantity (prefix+"fluxAxis.", point.getFluxAxis (), oracle);
        OracleBuilder.populateWithSedQuantity (prefix+"backgroundModel.", point.getBackgroundModel (), oracle);
    }


    /**
     * Populate the oracle from the sed coord
     */
    static public void  populateWithSedCoord (String prefix, SedCoord sedCoord, Oracle oracle)
    {
        if (sedCoord == null)
            return;

        OracleBuilder.populateWithDoubleParam (prefix+"value.", sedCoord.getValue (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"resolution.", sedCoord.getResolution (), oracle);
        OracleBuilder.populateWithAccuracy (prefix+"accuracy.",sedCoord.getAccuracy (), oracle);
    }

        
    /**
     * Populate the oracle from the sed quantity
     */
    static public void  populateWithSedQuantity (String prefix, SedQuantity sedQuantity, Oracle oracle)
    {
        if (sedQuantity == null)
            return;

        OracleBuilder.populateWithDoubleParam (prefix+"value.", sedQuantity.getValue (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"resolution.", sedQuantity.getResolution (), oracle);
        OracleBuilder.populateWithIntParam (prefix+"quality.", sedQuantity.getQuality (), oracle);

        OracleBuilder.populateWithAccuracy (prefix+"accuracy.",sedQuantity.getAccuracy (), oracle);
    }

    /**
     * Populate the oracle from the accuracy
     */
    static public void  populateWithAccuracy (String prefix, Accuracy accuracy, Oracle oracle)
    {
        if (accuracy == null)
            return;

        OracleBuilder.populateWithDoubleParam (prefix+"sysError.", accuracy.getSysError (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"confidence.", accuracy.getConfidence (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"binLow.", accuracy.getBinLow (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"binHigh.", accuracy.getBinHigh (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"binSize.", accuracy.getBinSize (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"statError.", accuracy.getStatError (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"statErrLow.", accuracy.getStatErrLow (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"statErrHigh.", accuracy.getStatErrHigh (), oracle);
    }


    /**
     * Populate the oracle from the coverage
     */
    static public void  populateWithCoverage (String prefix, Coverage coverage, Oracle oracle)
    {
        if (coverage == null)
            return;

        if (coverage.isSetBounds ())
        {
            OracleBuilder.populateWithDoubleParam (prefix+"bounds.extent.",coverage.getBounds().getExtent(), oracle);
            OracleBuilder.populateWithInterval (prefix+"bounds.range.",coverage.getBounds().getRange (), oracle);
        }

        if (coverage.isSetLocation ())
        {
            if (coverage.getLocation ().isSetValue ())
            {
                DoubleParam[] value = coverage.getLocation ().getValue ();
                for (int ii=0; ii<value.length; ii++)
                    OracleBuilder.populateWithDoubleParam (prefix+"location.value."+ii+".",coverage.getLocation().getValue()[ii], oracle);
            }

            OracleBuilder.populateWithDoubleParam (prefix+"location.resolution.", coverage.getLocation().getResolution (), oracle);
            OracleBuilder.populateWithAccuracy (prefix+"location.accuracy.",coverage.getLocation ().getAccuracy (), oracle);
        }

        if (coverage.isSetSupport ())
        {
            OracleBuilder.populateWithDoubleParam (prefix+"support.extent.", coverage.getSupport().getExtent (), oracle);
            OracleBuilder.populateWithParam (prefix+"support.area.", coverage.getSupport().getArea (), oracle);

            if (coverage.getSupport ().isSetRange ())
            {
                List<Interval> range = coverage.getSupport ().getRange ();
                for (int ii=0; ii<range.size (); ii++)
                    OracleBuilder.populateWithInterval (prefix+"support.range."+ii+".",coverage.getSupport().getRange ().get (ii), oracle);
            }
        }
    }

    /**
     * Populate the oracle from the sampling precision
     */
    static public void  populateWithSamplingPrecision (String prefix, SamplingPrecision samp, Oracle oracle)
    {
        if (samp == null)
            return;

        OracleBuilder.populateWithDoubleParam (prefix+"sampleExtent.", samp.getSampleExtent (), oracle);
        if (samp.isSetSamplingPrecisionRefVal ())
            OracleBuilder.populateWithDoubleParam (prefix+"samplingPrecisionRefVal.fillFactor.", samp.getSamplingPrecisionRefVal().getFillFactor (), oracle);
    }


    static public void populateWithGroup (String prefix, Group group, Oracle oracle)
    {
        if (group == null)
            return;

        if (group.isSetGroupId ())
            oracle.put (prefix+"groupId",group.getGroupId ());

        oracle.put (prefix+"linkRef",group.getLinkRef ());

        List<? extends Param> customParams = group.getCustomParams ();
        List<? extends Group> customGroups = group.getCustomGroups ();

        for (int ii=0; ii<customParams.size (); ii++)
        {
            Param param = customParams.get(ii);

            if (param instanceof DoubleParam)
                OracleBuilder.populateWithDoubleParam (prefix+"customParams."+ii+".", (DoubleParam)param, oracle);
            else if (param instanceof IntParam)
                OracleBuilder.populateWithIntParam (prefix+"customParams."+ii+".", (IntParam)param, oracle);
            else if (param instanceof TimeParam)
                OracleBuilder.populateWithParam (prefix+"customParams."+ii+".", (TimeParam)param, oracle);
            else
                OracleBuilder.populateWithParam (prefix+"customParams."+ii+".", param, oracle);
        }

        for (int ii=0; ii<customGroups.size (); ii++)
            OracleBuilder.populateWithGroup (prefix+"customGroups."+ii+".", customGroups.get (ii), oracle);

        
    }
        

    static public void populateWithPositionParam (String prefix, PositionParam pos, Oracle oracle)
    {
        if (pos == null)
            return;

        if (pos.isSetValue ())
        {
            DoubleParam value[] = pos.getValue ();
            for (int ii=0; ii<value.length; ii++)
                OracleBuilder.populateWithDoubleParam (prefix+"value."+ii+".", value[ii], oracle);
        }
    }


    static public void populateWithInterval (String prefix, Interval interval, Oracle oracle)
    {
        if (interval == null)
            return;

        OracleBuilder.populateWithDoubleParam (prefix+"min.", interval.getMin (), oracle);
        OracleBuilder.populateWithDoubleParam (prefix+"max.", interval.getMax (), oracle);
    }


    static public void populateWithDoubleParam (String prefix, DoubleParam param, Oracle oracle)
    {
        if (param == null)
            return;

        OracleBuilder.populateWithParam (prefix, param, oracle);
        oracle.put (prefix+"unit",param.getUnit ());
    }

    static public void populateWithIntParam (String prefix, IntParam param, Oracle oracle)
    {
        if (param == null)
            return;

        OracleBuilder.populateWithParam (prefix, param, oracle);
        oracle.put (prefix+"unit",param.getUnit ());
    }

    static public void populateWithParam (String prefix, TimeParam param, Oracle oracle)
    {
        if (param == null)
            return;

        OracleBuilder.populateWithParam (prefix, param, oracle);
        oracle.put (prefix+"unit",param.getUnit ());
    }

    static public void populateWithParam (String prefix, Param param, Oracle oracle)
    {
        if (param == null)
            return;

        oracle.put (prefix+"value",param.getValue ());
        oracle.put (prefix+"name",param.getName ());
        oracle.put (prefix+"ucd",param.getUcd ());
        oracle.put (prefix+"id",param.getId ());
        oracle.put (prefix+"internalId",param.getInternalId ());
    }

        
}


    
