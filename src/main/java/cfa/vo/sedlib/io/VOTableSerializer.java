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
* File: io/VOTableSerializer.java
*
* Author:  jmiller      Created: Mon Nov 29 11:57:14 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import cfa.vo.sedlib.common.SedWritingException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;

import uk.ac.starlink.votable.TableElement;
import uk.ac.starlink.votable.VODocument;
import uk.ac.starlink.votable.VOElement;
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
import cfa.vo.sedlib.Field;
import cfa.vo.sedlib.IntParam;
import cfa.vo.sedlib.Interval;
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
import cfa.vo.sedlib.Group;
import cfa.vo.sedlib.common.SedConstants;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.VOTableKeywords;


/**
Serializes a Sed object to a VOTable formatted file.
*/

public class VOTableSerializer implements ISedSerializer
{

    static final Logger logger = Logger.getLogger ("cfa.vo.sedlib");
    static final String[] paramAttributeNames
                        = { VOTableKeywords._NAME,
                            VOTableKeywords._UTYPE,
                            VOTableKeywords._UCD,
                            VOTableKeywords._UNIT,
                            VOTableKeywords._DATATYPE,
                            VOTableKeywords._ARRAYSIZE,
                            VOTableKeywords._VALUE};

    // NOTE: for now always use spectrum namespace. In the future
    // we'll probably want to allow namespace to be set by the 
    // segment type. 
    protected String namespace = "spec";

    protected String baseId = "ID_"; // used to setup field ids
    protected int refCount = 0; // a counter for each field

    protected HashMap <Integer,String> ucdOverrides = new HashMap<Integer, String> ();


    /**
     * Serializes the specified Sed object to the specified file in
     * VOTable format.
     * @param filename
     *   String
     * @param sed
     *   {@link Sed}
     * @throws IOException, SedWritingException, SedInconsistentException
     */
    public void serialize(String filename, Sed sed) throws IOException, SedWritingException, SedInconsistentException
    {
    	VOTableWriter writer = new VOTableWriter ();
        VOTableObject votable = this.processSed (sed);
        writer.write( filename, votable );
    }


    /**
     * Serializes the specified Sed object to the specified stream in
     * VOTable format.
     * @param oStream
     *   {@link OutputStream}
     * @param sed
     *   {@link Sed}
     * @throws SedWritingException, SedInconsistentException
     */
    public void serialize(OutputStream  oStream, Sed sed) throws SedWritingException, SedInconsistentException
    {
    	VOTableWriter writer = new VOTableWriter ();
        VOTableObject votable = this.processSed (sed);
        writer.write( oStream, votable );
    }


    /**
     * Extract a VOTableObject from the Sed
     */
    protected VOTableObject processSed(Sed sed) throws SedInconsistentException
    {
        VOTableObject voTableObject = new VOTableObject ();

        VODocument voDocument = new VODocument();
        VOElement root = (VOElement) voDocument.createElement(VOTableKeywords._VOTABLE);


        String namespaceDeclaration=null;

        this.namespace = sed.getNamespace ();

        if ( this.namespace != null && !this.namespace.equals( "" ) )
        {
            // Replace the trailing colon (it is suppossed to be there)
            // because it doesn't go in the 'xmlns' attribute.
            namespaceDeclaration = this.namespace.replaceFirst( ":$", "" );

            namespaceDeclaration = "xmlns:" + namespaceDeclaration;
        }

        String[] nameArray = {"version",
                                "xmlns",
                                namespaceDeclaration,
                                "xmlns:xsi",
                                "xsi:noNamespaceSchemaLocation"};

        String[] valArray = {SedConstants.VOTABLE_VERSION,
                                IOConstants.IVOA_VOTABLE_SCHEMA,
                                IOConstants.IVOA_SPECTRUM_SCHEMA,
                                IOConstants.WWW_XMLSCHEMA_INSTANCE,
                                IOConstants.IVOA_VOTABLE_SCHEMA};

        // store off the root
        voTableObject.root = root;

        // add header information
        this.addAttributes(root, nameArray, valArray);
        voDocument.appendChild(root);

        //RESOURCE element
        VOElement resource = (VOElement) voDocument.createElement(VOTableKeywords._RESOURCE);

        //NOTE: what is correct utype to set to ?
        resource.setAttribute(VOTableKeywords._UTYPE, VOTableKeywords.getName(VOTableKeywords.SED, this.namespace));
        root.appendChild(resource);

        //On to the Data TABLE
        if (sed.getNumberOfSegments () == 0)
        	return voTableObject;


        // Now add each segment in its own table.  
        for (int ii=0; ii<sed.getNumberOfSegments (); ii++)
        {
            Segment segment = sed.getSegment (ii);
        
            TableElement voTable = null;
            VOTablePointGroup pointsGroup = null;

            this.initializeSegment ();

            voTable = (TableElement) voDocument.createElement(VOTableKeywords._TABLE);
            voTable.setAttribute(VOTableKeywords._UTYPE, VOTableKeywords.getName(VOTableKeywords.SEG, this.namespace));

            resource.appendChild(voTable);

            this.overrideParamInfo (segment);

            // add all the group information to the table
            this.addSegmentToTable(segment, voTable);

            if (segment.isSetData ())
            {
                pointsGroup = this.extractData (segment.getData (), voTable);

                SedStarTable starTable = this.addDataToTable (pointsGroup, voTable);
                voTableObject.starTableList.add (starTable);

            }
            else
                // add null spacer for segments without data
                voTableObject.starTableList.add (null);

        }

        return voTableObject;
    }


    /**
     * Add a list of attributes and their values to a VOElement
     */
    private void addAttributes(VOElement parent, String[] names, String[] vals)
    {
        for(int ii=0; ii<names.length; ii++)
        {
            if( (names[ii] != null) && (!names[ii].equals("")) &&
                 (vals[ii] != null) && (!vals[ii].equals("")) )
                parent.setAttribute(names[ii], vals[ii]);
        }
    }

    /**
     * Map Param attribute to a VOElement Param
     */
    private void addSedParam(Param value, VOElement parent, int utype)
    {
       if (value == null)
            return;

       VOElement param = this.createParam (value, parent, utype); 

       parent.appendChild (param);
    }

    /**
     * Map an array of Param attribute to a VOElement Param. Multiple
     * values will be space separated.
     */
    private void addSedParamArray(Param[] valueList,
    		                     VOElement parent,  
                                     int utype)
    {
        if (valueList.length == 0)
            return;

        Param firstValue = valueList[0];
        VOElement param = this.createParam (firstValue, parent, utype);
        int arraySize = 0;
       
        if (firstValue.isSetValue() )
        {
            StringBuilder sbuf = new StringBuilder();
            for ( int ii = 0; ii <  valueList.length; ii++ )
            {
                if (valueList[ii] == null)
                    break;
                if ( ii > 0)
                     sbuf.append(" ");
                sbuf.append(valueList[ii].getValue());
                arraySize += 1;
            }
            param.setAttribute (VOTableKeywords._VALUE, sbuf.toString());
        }

        if ((arraySize > 1) && !(firstValue instanceof TextParam))
                param.setAttribute(VOTableKeywords._ARRAYSIZE, Integer.toString (arraySize));


        parent.appendChild (param);
    }

    /**
     * Create a VOElement Param attribute and map a SED Param to it
     */
    private VOElement createParam (Param sedParam, 
                                   VOElement parent,
                                   int utype)
    {
        if (sedParam == null)
            return null;

        Document document = parent.getOwnerDocument ();
        VOElement param = (VOElement) document.createElement(VOTableKeywords._PARAM);
        String utypeName = "";
        String ucd = null;

        if (utype != VOTableKeywords.INVALID_UTYPE)
        {
            utypeName = VOTableKeywords.getName (utype, this.namespace);
            param.setAttribute( VOTableKeywords._UTYPE, utypeName);
        }


        if (sedParam.isSetName ())
            param.setAttribute(VOTableKeywords._NAME, sedParam.getName());
        else
        {
            // if we don't have a name for this param, take the end
            // of the utype and use it as the name.  We do this because the
            // VOTable schema requires PARAMs to have a name.
        	if (utype != VOTableKeywords.INVALID_UTYPE)
        		param.setAttribute( VOTableKeywords._NAME, VOTableKeywords.getLastPartOfUtype( utypeName) );
        }
        
        ucd = this.getParamUcd (sedParam, utype);
        if (ucd != null)
            param.setAttribute (VOTableKeywords._UCD, ucd);
        

        if (sedParam.isSetValue() )
            param.setAttribute (VOTableKeywords._VALUE, sedParam.getValue ());


        if (sedParam instanceof DoubleParam)
        {
            DoubleParam dvalue = (DoubleParam)sedParam;
            if (dvalue.isSetUnit ())
                param.setAttribute (VOTableKeywords._UNIT, dvalue.getUnit());
            param.setAttribute(  VOTableKeywords._DATATYPE, "double" );
        }
        else if (sedParam instanceof IntParam)
        {
            IntParam ivalue = (IntParam)sedParam;
            if (ivalue.isSetUnit ())
                param.setAttribute (VOTableKeywords._UNIT, ivalue.getUnit());
            param.setAttribute(  VOTableKeywords._DATATYPE, "int" );
        }
        else if (sedParam instanceof TimeParam)
        {
            TimeParam tvalue = (TimeParam)sedParam;
            if (tvalue.isSetUnit ())
                param.setAttribute (VOTableKeywords._UNIT, tvalue.getUnit());
            param.setAttribute(  VOTableKeywords._DATATYPE, "char" );
            param.setAttribute(VOTableKeywords._ARRAYSIZE, "*");
        }
        else
        {
            // this should cover TextParam, DateParam, SkyRegion
            param.setAttribute(  VOTableKeywords._DATATYPE, "char" );
            param.setAttribute(VOTableKeywords._ARRAYSIZE, "*");
        }

        return param;
    }


    /**
     * Add information from the segment to the VOTable
     */
    private void addSegmentToTable( Segment segment, TableElement parent) throws SedInconsistentException
   
    {
        VOElement group;

        // add spectrum infomation
        this.addSedParam(new TextParam (segment.getDataModel ()), parent, VOTableKeywords.DATAMODEL);
        this.addSedParam(segment.getType(), parent, VOTableKeywords.TYPE);
        this.addSedParam(segment.getTimeSI(), parent, VOTableKeywords.TIMESI);
        this.addSedParam(segment.getSpectralSI(), parent, VOTableKeywords.SPECTRALSI);
        this.addSedParam(segment.getFluxSI(), parent, VOTableKeywords.FLUXSI);

        if (segment.isSetTarget ())
        {
            // create a group for the target
            group = this.addGroup (VOTableKeywords.TARGET, segment.getTarget ().getGroupId (), parent);
            this.addTargetToTable (segment.getTarget (), group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (segment.isSetChar ())
        {
            // create a group for the characterization
            group = this.addGroup (VOTableKeywords.SEG_CHAR, segment.getChar ().getGroupId (), parent);
            this.addCharacterizationToTable (segment.getChar (), group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (segment.isSetCoordSys ())
        {
            // create a group for the coordsys
            group = this.addGroup (VOTableKeywords.SEG_CS, segment.getCoordSys ().getGroupId (), parent);
            this.addCoordSysToTable (segment.getCoordSys (), group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (segment.isSetCuration ())
        {
            // create a group for the curation
            group = this.addGroup (VOTableKeywords.SEG_CURATION, segment.getCuration ().getGroupId (), parent);
            this.addCurationToTable (segment.getCuration (), group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (segment.isSetDataID ())
        {
            // create a group for the dataid
            group = this.addGroup (VOTableKeywords.SEG_DATAID, segment.getDataID ().getGroupId (), parent);
            this.addDataIDTable (segment.getDataID (), group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (segment.isSetDerived ())
        {
            // create a group for the derived
            group = this.addGroup (VOTableKeywords.SEG_DD, segment.getDerived ().getGroupId (), parent);
            this.addDerivedTable (segment.getDerived (), group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
/*        if (segment.isSetCustomParams ())
        {
            // create a group for the customparams
            group = this.addGroup (VOTableKeywords.CUSTOM, parent);
            this.addArrayOfParamsToTable (segment.getCustomParams (), group, VOTableKeywords.CUSTOM);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
*/
        this.addCustomInfoToTable (segment, parent);

    }

    /**
     * Add information from the target to the VOTable
     */
    private void addTargetToTable (Target target, VOElement parent)

    {
        if (target.isSetName ())
           this.addSedParam (target.getName (), parent, VOTableKeywords.TARGET_NAME);
        if (target.isSetDescription ())
           this.addSedParam (target.getDescription (), parent, VOTableKeywords.TARGET_DESCRIPTION);
        if (target.isSetTargetClass ())
           this.addSedParam (target.getTargetClass (), parent, VOTableKeywords.TARGET_CLASS);
        if (target.isSetSpectralClass ())
           this.addSedParam (target.getSpectralClass (), parent, VOTableKeywords.TARGET_SPECTRALCLASS);
        if (target.isSetRedshift ())
           this.addSedParam (target.getRedshift (), parent, VOTableKeywords.TARGET_REDSHIFT);
        if (target.isSetPos ())
           this.addSedParamArray (target.getPos ().getValue (), parent, VOTableKeywords.TARGET_POS );
        if (target.isSetVarAmpl ())
           this.addSedParam (target.getVarAmpl (), parent, VOTableKeywords.TARGET_VARAMPL);
/*       if (target.isSetCustomParams ())
        {

            // create a group for the customparams
            group = this.addGroup (VOTableKeywords.CUSTOM, parent);

            //NOTE: target has no custom param utype. use an invalid utype
            //to indicate that none exists
            this.addArrayOfParamsToTable (target.getCustomParams (), group, VOTableKeywords.INVALID_UTYPE);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }

*/
        this.addCustomInfoToTable (target, parent);
    }

    /**
     * Add information from the Characterization to the VOTable
     */
    private void addCharacterizationToTable (Characterization _char, VOElement parent) throws SedInconsistentException
    {
        VOElement group;

        if (_char.isSetSpatialAxis ())
        {
            group = this.addGroup (VOTableKeywords.SEG_CHAR_SPATIALAXIS, _char.getSpatialAxis ().getGroupId (), parent);
            this.addCharacterizationAxisToTable (_char.getSpatialAxis (), group,VOTableKeywords.SEG_CHAR_SPATIALAXIS);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (_char.isSetTimeAxis ())
        {
            group = this.addGroup (VOTableKeywords.SEG_CHAR_TIMEAXIS, _char.getTimeAxis ().getGroupId (), parent);
            this.addCharacterizationAxisToTable (_char.getTimeAxis (), group, VOTableKeywords.SEG_CHAR_TIMEAXIS);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (_char.isSetSpectralAxis ())
        {
            SpectralCharacterizationAxis spectralAxis = _char.getSpectralAxis ();
            group = this.addGroup (VOTableKeywords.SEG_CHAR_SPECTRALAXIS, spectralAxis.getGroupId (), parent);
            if (spectralAxis.isSetResPower ())
                this.addSedParam (spectralAxis.getResPower (), group, VOTableKeywords.SEG_CHAR_SPECTRALAXIS_RESPOW);

            this.addCharacterizationAxisToTable (spectralAxis, group, VOTableKeywords.SEG_CHAR_SPECTRALAXIS);
            
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (_char.isSetFluxAxis ())
        {
            group = this.addGroup (VOTableKeywords.SEG_CHAR_FLUXAXIS, _char.getFluxAxis ().getGroupId (), parent);
            this.addCharacterizationAxisToTable (_char.getFluxAxis (), group, VOTableKeywords.SEG_CHAR_FLUXAXIS);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (_char.isSetCharacterizationAxis ())
        {
            List <CharacterizationAxis> charAxisList = _char.getCharacterizationAxis ();
            for (CharacterizationAxis charAxis : charAxisList)
            {
                group = this.addGroup (VOTableKeywords.SEG_CHAR_CHARAXIS, charAxis.getGroupId (), parent);
                this.addCharacterizationAxisToTable (charAxis, group, VOTableKeywords.SEG_CHAR_CHARAXIS);
                if (group.getChildren ().length > 0)
                    parent.appendChild (group);
            }
        }

        this.addCustomInfoToTable (_char, parent);
    }

    /**
     * Add information from the CharacterizationAxis to the VOTable
     */
    private void addCharacterizationAxisToTable (CharacterizationAxis charAxis, VOElement parent, int utype) throws SedInconsistentException

    {
        Document document = parent.getOwnerDocument ();
        VOElement group;
        int newUtype;
        String functionName = "addCharacterizationAxisToTable";

        // add the name, ucd, and unit in a single param line
        if (charAxis.isSetName () || charAxis.isSetUcd () || charAxis.isSetUnit ())
        {
            VOElement param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            String name;

            // use the name utype for this param
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_NAME, functionName);

           // force the name to be set to something
           if (charAxis.isSetName ())
              name = charAxis.getName ();
           else
           {
              name = VOTableKeywords.getName (utype);
              name = VOTableKeywords.getLastPartOfUtype (name);
           }

           String values[] = {name, VOTableKeywords.getName (newUtype,this.namespace), charAxis.getUcd (), charAxis.getUnit (), "char", "*", charAxis.getName ()};

           if (!charAxis.isSetUcd ())
               values[2] = VOTableKeywords.getUcd (utype);


           this.addAttributes(param, paramAttributeNames, values);
           parent.appendChild (param);  
        }
        
        // add the rest
        if (charAxis.isSetResolution ())
        {
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_RESOLUTION, functionName);
            this.addSedParam (charAxis.getResolution (), parent, newUtype);
        }
        if (charAxis.isSetCalibration ())
        {
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_CAL, functionName);
            this.addSedParam (charAxis.getCalibration (), parent, newUtype);

        }
        if (charAxis.isSetCoordSystem ())
        {
            // no utype
        }
        if (charAxis.isSetCoverage ())
        {
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_COV, functionName);
            group = this.addGroup (newUtype, charAxis.getCoverage ().getGroupId (), parent);
            this.addCoverageToTable (charAxis.getCoverage (), group, newUtype);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (charAxis.isSetAccuracy ())
        {
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_ACC, functionName);
            group = this.addGroup (newUtype, charAxis.getAccuracy ().getGroupId (), parent);
            this.addAccuracyToTable (charAxis.getAccuracy (), group, newUtype);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (charAxis.isSetSamplingPrecision ())
        {
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_SAMPPREC, functionName);
            group = this.addGroup (newUtype, charAxis.getSamplingPrecision().getGroupId (), parent);
            this.addSamplingPrecisionToTable (charAxis.getSamplingPrecision (), group, newUtype);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }


        this.addCustomInfoToTable (charAxis, parent);

    }

    /**
     * Add information from the Coverage to the VOTable
     */
    private void addCoverageToTable (Coverage coverage, VOElement parent, int utype) throws SedInconsistentException

    {
        VOElement group;
        int newUtype;
        String functionName = "addCoverageToTable";

        // verify the utype is an axis. this is more
        // for maintance to simply ensure if the utypes change
        // that this function doesn't accidently pass
        switch (utype)
        {
            case VOTableKeywords.SEG_CHAR_CHARAXIS_COV:
            case VOTableKeywords.SEG_CHAR_TIMEAXIS_COV:
            case VOTableKeywords.SEG_CHAR_SPATIALAXIS_COV:
            case VOTableKeywords.SEG_CHAR_SPECTRALAXIS_COV:
            case VOTableKeywords.SEG_CHAR_FLUXAXIS_COV:
                break;
            default:
/*                throw new SedException ("addCoverageToTable: Unexpected utype, " + VOTableKeywords.getName (utype));
*/
        }

        if (coverage.isSetLocation ())
        {
            CoverageLocation location = coverage.getLocation ();
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_COV_LOC, functionName);
            group = this.addGroup (newUtype, location.getGroupId (), parent);
            if (location.isSetValue ())
            {
                int resUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_VALUE, functionName);
                this.addSedParamArray (location.getValue (), group, resUtype );
            }
            if (location.isSetResolution ())
            {
                int valUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_RESOLUTION, functionName);
                this.addSedParam (location.getResolution (), group, valUtype );
            }
            if (location.isSetAccuracy ())
            {
                VOElement subGroup;
                int accUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC, functionName);
                subGroup = this.addGroup (accUtype, location.getAccuracy().getGroupId (), group);
                this.addAccuracyToTable (location.getAccuracy (), subGroup, accUtype);
                if (subGroup.getChildren ().length > 0)
                    group.appendChild (subGroup);
            }
            this.addCustomInfoToTable (location, group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);

        }
        if (coverage.isSetBounds ())
        {
            CoverageBounds bounds = coverage.getBounds ();
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_COV_BOUNDS, functionName);
            group = this.addGroup (newUtype, bounds.getGroupId (), parent);

            if (bounds.isSetExtent ())
            {
                int extUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_CHARAXIS_COV_BOUNDS_EXTENT, functionName);
                this.addSedParam (bounds.getExtent (), group, extUtype );
            }
            if (bounds.isSetRange ())
            {
                int rngUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_BOUNDS_MIN, functionName);     
                this.addSedParam (bounds.getRange ().getMin (), group, rngUtype );
                rngUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_BOUNDS_MAX, functionName);
                this.addSedParam (bounds.getRange ().getMax (), group, rngUtype );
            }
            this.addCustomInfoToTable (bounds, group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }
        if (coverage.isSetSupport ())
        {
            CoverageSupport support = coverage.getSupport ();
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_CHARAXIS_COV_SUPPORT, functionName);
            group = this.addGroup (newUtype, support.getGroupId (), parent);

            if (support.isSetArea ())
            {
                int areaUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_SUPPORT_AREA, functionName);
                this.addSedParam (support.getArea (), group, areaUtype );
            }
            if (support.isSetExtent ())
            {
                int extUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_SUPPORT_EXTENT, functionName);
                this.addSedParam (support.getExtent (), group, extUtype );
            }
            if (support.isSetRange ())
            {
                int rngUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_SUPPORT_RANGE, functionName);
                List<Interval> rangeList = support.getRange ();
                for (Interval range : rangeList)
                {
                	DoubleParam minMaxList[] = { range.getMin (), range.getMax () };
                	String name = null;
                    // JBM -- this is a workaround because the name is not
                	// part of the interval. We don't want the name to be
                	// the value of the min element. So we default it to "range"
                	if (minMaxList[0] != null)
                	{
                	    name = minMaxList[0].getName ();
                	    minMaxList[0].setName ("Range");
                	}
                	
                	this.addSedParamArray (minMaxList, group, rngUtype );
                	
                	// JBM -- set the name back
                	if (name != null)
                	    minMaxList[0].setName (name);

                }
            }
            this.addCustomInfoToTable (support, group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }

        this.addCustomInfoToTable (coverage, parent);
    }

    /**
     * Add information from the Accuracy to the VOTable
     */
    private void addAccuracyToTable (Accuracy accuracy, VOElement parent, int utype) throws SedInconsistentException
    {
        HashMap <String, Integer> utypeTable = new HashMap <String, Integer> ();
        int newUtype;
        String functionName = "addAccuracyToTable";

        // create a table of valid utypes to merge with.
        switch (utype)
        {
            case VOTableKeywords.SEG_CHAR_CHARAXIS_ACC:
            case VOTableKeywords.SEG_CHAR_TIMEAXIS_ACC:
            case VOTableKeywords.SEG_CHAR_SPATIALAXIS_ACC:
            case VOTableKeywords.SEG_CHAR_SPECTRALAXIS_ACC:
            case VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC:
                utypeTable.put ("binLow", VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC_BINLOW);
                utypeTable.put ("binHigh", VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC_BINHIGH);
                utypeTable.put ("binSize", VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC_BINSIZE);
                utypeTable.put ("statErrLow", VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh", VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC_STATERRHIGH);
                utypeTable.put ("statError", VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC_STATERR);
                utypeTable.put ("sysError", VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC_SYSERR);
                utypeTable.put ("confidence", VOTableKeywords.SEG_CHAR_FLUXAXIS_ACC_CONFIDENCE);
                break;
            case VOTableKeywords.SEG_CHAR_CHARAXIS_COV_LOC_ACC:
            case VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC:
            case VOTableKeywords.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC:
            case VOTableKeywords.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC:
            case VOTableKeywords.SEG_CHAR_TIMEAXIS_COV_LOC_ACC:
                utypeTable.put ("binLow", VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINLOW);
                utypeTable.put ("binHigh", VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINHIGH);
                utypeTable.put ("binSize", VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINSIZE);
                utypeTable.put ("statErrLow", VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh", VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRHIGH);
                utypeTable.put ("statError", VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERR);
                utypeTable.put ("sysError", VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_SYSERR);
                utypeTable.put ("confidence", VOTableKeywords.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_CONFIDENCE);
                break;
            case VOTableKeywords.SEG_DD_REDSHIFT_ACC:
                utypeTable.put ("binLow", VOTableKeywords.SEG_DD_REDSHIFT_ACC_BINLOW);
                utypeTable.put ("binHigh", VOTableKeywords.SEG_DD_REDSHIFT_ACC_BINHIGH);
                utypeTable.put ("binSize", VOTableKeywords.SEG_DD_REDSHIFT_ACC_BINSIZE);
                utypeTable.put ("statErrLow", VOTableKeywords.SEG_DD_REDSHIFT_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh", VOTableKeywords.SEG_DD_REDSHIFT_ACC_STATERRHIGH); 
                utypeTable.put ("statError", VOTableKeywords.SEG_DD_REDSHIFT_ACC_STATERR);
                utypeTable.put ("sysError", VOTableKeywords.SEG_DD_REDSHIFT_ACC_SYSERR);
                utypeTable.put ("confidence", VOTableKeywords.SEG_DD_REDSHIFT_ACC_CONFIDENCE);

                break;
            case VOTableKeywords.SEG_DATA_FLUXAXIS_ACC:
            case VOTableKeywords.SEG_DATA_SPECTRALAXIS_ACC:
            case VOTableKeywords.SEG_DATA_TIMEAXIS_ACC:
                utypeTable.put ("binLow", VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_BINLOW);
                utypeTable.put ("binHigh", VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_BINHIGH);
                utypeTable.put ("binSize", VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_BINSIZE);
                utypeTable.put ("statErrLow", VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh",VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRHIGH);
                utypeTable.put ("statError", VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_STATERR);
                utypeTable.put ("sysError", VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_SYSERR);
                utypeTable.put ("confidence", VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_CONFIDENCE);
                break;
            case VOTableKeywords.SEG_DATA_BGM_ACC:
                utypeTable.put ("binLow", VOTableKeywords.SEG_DATA_BGM_ACC_BINLOW);
                utypeTable.put ("binHigh", VOTableKeywords.SEG_DATA_BGM_ACC_BINHIGH);
                utypeTable.put ("binSize", VOTableKeywords.SEG_DATA_BGM_ACC_BINSIZE);
                utypeTable.put ("statErrLow", VOTableKeywords.SEG_DATA_BGM_ACC_STATERRLOW);
                utypeTable.put ("statErrHigh",VOTableKeywords.SEG_DATA_BGM_ACC_STATERRHIGH);
                utypeTable.put ("statError", VOTableKeywords.SEG_DATA_BGM_ACC_STATERR);
                utypeTable.put ("sysError", VOTableKeywords.SEG_DATA_BGM_ACC_SYSERR);
                utypeTable.put ("confidence", VOTableKeywords.SEG_DATA_BGM_ACC_CONFIDENCE);
                break;
            default:
                logger.log (Level.WARNING, "The utype, {0}, cannot be included as part of the Accuracy.", VOTableKeywords.getName(utype));
        }

        if (accuracy.isSetBinLow ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("binLow"), functionName);
            this.addSedParam (accuracy.getBinLow (), parent, newUtype);
        }
        if (accuracy.isSetBinHigh ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("binHigh"), functionName);
            this.addSedParam (accuracy.getBinHigh (), parent, newUtype);
        }
        if (accuracy.isSetBinSize ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("binSize"), functionName);
            this.addSedParam (accuracy.getBinSize (), parent, newUtype);
        }
        if (accuracy.isSetStatError ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("statError"), functionName);
            this.addSedParam (accuracy.getStatError (), parent, newUtype);
        }
        if (accuracy.isSetStatErrLow ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("statErrLow"), functionName);
            this.addSedParam (accuracy.getStatErrLow (), parent, newUtype);
        }
        if (accuracy.isSetStatErrHigh ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("statErrHigh"), functionName);
            this.addSedParam (accuracy.getStatErrHigh (), parent, newUtype);
        }
        if (accuracy.isSetSysError ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("sysError"), functionName);
            this.addSedParam (accuracy.getSysError (), parent, newUtype);
        }
        if (accuracy.isSetConfidence ())
        {
            newUtype = this.mergeUtypes (utype, utypeTable.get("confidence"), functionName);
            this.addSedParam (accuracy.getConfidence (), parent, newUtype);
        }

        this.addCustomInfoToTable (accuracy, parent);
    }


    /**
     * Add information from the SamplingPrecision to the VOTable
     */
    private void addSamplingPrecisionToTable (SamplingPrecision samplingPrecision, VOElement parent, int utype) throws SedInconsistentException

    {
        VOElement group;
        int newUtype;
        String functionName = "addSamplingPrecisionToTable";

        // verify the utype is an axis. this is more
        // for maintance to simply ensure if the utypes change
        // that this function doesn't accidently pass
        switch (utype)
        {
            case VOTableKeywords.SEG_CHAR_CHARAXIS_SAMPPREC:
            case VOTableKeywords.SEG_CHAR_TIMEAXIS_SAMPPREC:
            case VOTableKeywords.SEG_CHAR_SPATIALAXIS_SAMPPREC:
            case VOTableKeywords.SEG_CHAR_SPECTRALAXIS_SAMPPREC:
            case VOTableKeywords.SEG_CHAR_FLUXAXIS_SAMPPREC:
                break;
            default:
                logger.log (Level.WARNING, "The utype, {0}, cannot be included as part of the SamplingPrecision.", VOTableKeywords.getName(utype));
        }

        if (samplingPrecision.isSetSampleExtent ())
        {
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPEXT, functionName);
            this.addSedParam (samplingPrecision.getSampleExtent (), parent, newUtype);
        }
        if (samplingPrecision.isSetSamplingPrecisionRefVal ())
        {
            SamplingPrecisionRefVal samplingPrecisionRefVal = 
                          samplingPrecision.getSamplingPrecisionRefVal ();
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL, functionName);
            group = this.addGroup (newUtype, samplingPrecisionRefVal.getGroupId (), parent);
            
            newUtype = this.mergeUtypes (newUtype, VOTableKeywords.SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL_FILL, functionName);
            this.addSedParam (samplingPrecisionRefVal.getFillFactor (), group, newUtype);
            this.addCustomInfoToTable (samplingPrecisionRefVal, group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);

        }

        this.addCustomInfoToTable (samplingPrecision, parent);
    }


    /**
     * Add information from the CoordSys to the VOTable
     */
    private void addCoordSysToTable (CoordSys coordSys, VOElement parent) throws SedInconsistentException
    {
        Document document = parent.getOwnerDocument ();
        VOElement group;

        String attVals[];
        VOElement param;
        int newUtype;
        String ucd = null;

        if (coordSys.isSetId ())
        {
            param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            attVals = new String[] {"id", VOTableKeywords.getName(VOTableKeywords.SEG_CS_ID, this.namespace),
                            null, null, "char", "*", coordSys.getId () };
            this.addAttributes(param, paramAttributeNames, attVals);
            parent.appendChild (param);
        }
        if (coordSys.isSetUcd ())
            ucd = coordSys.getUcd ();
        else
            ucd = VOTableKeywords.getUcd (VOTableKeywords.SEG_CS_UCD);
        if (ucd != null)
        {
            param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            attVals = new String[] {"ucd", VOTableKeywords.getName (VOTableKeywords.SEG_CS_UCD, this.namespace),
                            null, null, "char", "*", coordSys.getUcd () };
            this.addAttributes(param, paramAttributeNames, attVals);
            parent.appendChild (param);
        }
        if (coordSys.isSetType ())
        {
            param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            attVals = new String[] {"type", VOTableKeywords.getName (VOTableKeywords.SEG_CS_TYPE, this.namespace),
                            null, null, "char", "*", coordSys.getType () };
            this.addAttributes(param, paramAttributeNames, attVals);
            parent.appendChild (param);
        }
        if (coordSys.isSetHref ())
        {
            param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            attVals = new String[] {"type", VOTableKeywords.getName (VOTableKeywords.SEG_CS_HREF, this.namespace),
                            null, null, "char", "*", coordSys.getHref () };
            this.addAttributes(param, paramAttributeNames, attVals);
            parent.appendChild (param);
        }

        // NOTE: we don't include idref here because it's not clear how to
        // serialize it


        if (coordSys.isSetCoordFrame ())
        {
            for (CoordFrame coordFrame : coordSys.getCoordFrame ())
            {
                if (coordFrame instanceof SpaceFrame)
                {
                    newUtype = VOTableKeywords.SEG_CS_SPACEFRAME;
                    group = this.addGroup (newUtype, coordFrame.getGroupId (), parent);

                    if (((SpaceFrame)coordFrame).isSetEquinox ())
                        this.addSedParam (((SpaceFrame)coordFrame).getEquinox (), group, VOTableKeywords.SEG_CS_SPACEFRAME_EQUINOX);
                }
                else if (coordFrame instanceof TimeFrame)
                {
                    newUtype = VOTableKeywords.SEG_CS_TIMEFRAME;
                    group = this.addGroup (newUtype, coordFrame.getGroupId (), parent);
                    if (((TimeFrame)coordFrame).isSetZero ())
                        this.addSedParam (((TimeFrame)coordFrame).getZero (), group, VOTableKeywords.SEG_CS_TIMEFRAME_ZERO);
                }
                else if (coordFrame instanceof SpectralFrame)
                {
                    newUtype = VOTableKeywords.SEG_CS_SPECTRALFRAME;
                    group = this.addGroup (newUtype, coordFrame.getGroupId (), parent);
                    if (((SpectralFrame)coordFrame).isSetRedshift ())
                        this.addSedParam (((SpectralFrame)coordFrame).getRedshift (), group, VOTableKeywords.SEG_CS_SPECTRALFRAME_REDSHIFT);
                }
                else if (coordFrame instanceof RedshiftFrame)
                {
                    newUtype = VOTableKeywords.SEG_CS_REDFRAME;
                    group = this.addGroup (newUtype, coordFrame.getGroupId (), parent);

                    if (((RedshiftFrame)coordFrame).isSetDopplerDefinition ())
                    {
                        param = (VOElement) document.createElement(VOTableKeywords._PARAM);
                        attVals = new String[] {"dopplerDefinition", VOTableKeywords.getName (VOTableKeywords.SEG_CS_REDFRAME_DOPPLERDEF, this.namespace),
                            null, null, "char", "*", ((RedshiftFrame)coordFrame).getDopplerDefinition () };
                        this.addAttributes(param, paramAttributeNames, attVals);
                        group.appendChild (param);
                    }
                }
                else
                {
                    newUtype = VOTableKeywords.SEG_CS_GENFRAME;
                    group = this.addGroup (newUtype, coordFrame.getGroupId (), parent);
                }
                this.addCoordFrameToTable (coordFrame, group, newUtype);
                if (group.getChildren ().length > 0)
                    parent.appendChild (group);
            }
        }

        this.addCustomInfoToTable (coordSys, parent);
    }


    /**
     * Add information from the CoordFrame to the VOTable
     */
    private void addCoordFrameToTable (CoordFrame coordFrame, VOElement parent, int utype) throws SedInconsistentException

    {
        int newUtype;
        String functionName = "addCoordFrameToTable";
        String attVals[] = new String[] {null, null, null, 
                                         null, "char", "*", null};
        VOElement param;
        Document document = parent.getOwnerDocument ();

        if (coordFrame.isSetId ())
        {
            param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CS_GENFRAME_ID, functionName);
            attVals[0] = "id";
            attVals[1] = VOTableKeywords.getName (newUtype, this.namespace);
            attVals[6] = coordFrame.getId ();
            this.addAttributes(param, paramAttributeNames, attVals);
            parent.appendChild (param);
        }
        if (coordFrame.isSetName ())
        {
            param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CS_GENFRAME_NAME, functionName);
            attVals[0] = "name";
            attVals[1] = VOTableKeywords.getName(newUtype, this.namespace);
            attVals[6] = coordFrame.getName ();
            this.addAttributes(param, paramAttributeNames, attVals);
            parent.appendChild (param);
        }
        if (coordFrame.isSetReferencePosition ())
        {
            param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CS_GENFRAME_REFPOS, functionName);
            attVals[0] = "referencePosition";
            attVals[1] = VOTableKeywords.getName(newUtype, this.namespace);
            attVals[6] = coordFrame.getReferencePosition ();
            this.addAttributes(param, paramAttributeNames, attVals);
            parent.appendChild (param);
        }
        if (coordFrame.isSetUcd ())
        {
            param = (VOElement) document.createElement(VOTableKeywords._PARAM);
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_CS_GENFRAME_UCD, functionName);
            attVals[0] = "ucd";
            attVals[1] = VOTableKeywords.getName (newUtype, this.namespace);
            if (coordFrame.isSetUcd ())
                attVals[6] = coordFrame.getUcd ();
            else
                attVals[6] = VOTableKeywords.getUcd (newUtype);
            this.addAttributes(param, paramAttributeNames, attVals);
            parent.appendChild (param);
        }

        this.addCustomInfoToTable (coordFrame, parent);
    }

    /**
     * Add information from the Curation to the VOTable
     */
    private void addCurationToTable (Curation curation, VOElement parent)
    {
        VOElement group;
        if (curation.isSetPublisher ())
            this.addSedParam (curation.getPublisher (), parent, VOTableKeywords.SEG_CURATION_PUBLISHER);
        if (curation.isSetPublisherID ())
            this.addSedParam (curation.getPublisherID (), parent, VOTableKeywords.SEG_CURATION_PUBID);
        if (curation.isSetPublisherDID ())
            this.addSedParam (curation.getPublisherDID (), parent, VOTableKeywords.SEG_CURATION_PUBDID);
        if (curation.isSetReference ())
            this.addSedParam (curation.getReference (), parent, VOTableKeywords.SEG_CURATION_REF);
        if (curation.isSetVersion ())
            this.addSedParam (curation.getVersion (), parent, VOTableKeywords.SEG_CURATION_VERSION);
        if (curation.isSetRights ())
            this.addSedParam (curation.getRights (), parent, VOTableKeywords.SEG_CURATION_RIGHTS);
        if (curation.isSetDate ())
            this.addSedParam (curation.getDate (), parent, VOTableKeywords.SEG_CURATION_DATE);
        if (curation.isSetContact ())
        {
            Contact contact = curation.getContact ();
            group = this.addGroup (VOTableKeywords.SEG_CURATION_CONTACT, contact.getGroupId (), parent);
            if (contact.isSetName ())
                this.addSedParam (contact.getName (), group, VOTableKeywords.SEG_CURATION_CONTACT_NAME);
            if (contact.isSetEmail ())
                this.addSedParam (contact.getEmail (), group, VOTableKeywords.SEG_CURATION_CONTACT_EMAIL);
            this.addCustomInfoToTable (contact, group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }

        this.addCustomInfoToTable (curation, parent);
    }

    /**
     * Add information from the DataID to the VOTable
     */
    private void addDataIDTable (DataID dataID, VOElement parent)
    {
        if (dataID.isSetTitle ())
            this.addSedParam (dataID.getTitle (), parent, VOTableKeywords.SEG_DATAID_TITLE);
        if (dataID.isSetCreator ())
            this.addSedParam (dataID.getCreator (), parent, VOTableKeywords.SEG_DATAID_CREATOR);
        if (dataID.isSetDatasetID ())
            this.addSedParam (dataID.getDatasetID (), parent, VOTableKeywords.SEG_DATAID_DATASETID);
        if (dataID.isSetDate ())
            this.addSedParam (dataID.getDate (), parent, VOTableKeywords.SEG_DATAID_DATE);
        if (dataID.isSetVersion ())
            this.addSedParam (dataID.getVersion (), parent, VOTableKeywords.SEG_DATAID_VERSION);
        if (dataID.isSetInstrument ())
            this.addSedParam (dataID.getInstrument (), parent, VOTableKeywords.SEG_DATAID_INSTRUMENT);
        if (dataID.isSetCreationType ())
            this.addSedParam (dataID.getCreationType (), parent, VOTableKeywords.SEG_DATAID_CREATIONTYPE);
        if (dataID.isSetBandpass ())
            this.addSedParam (dataID.getBandpass (), parent, VOTableKeywords.SEG_DATAID_BANDPASS);
        if (dataID.isSetCreatorDID ())
            this.addSedParam (dataID.getCreatorDID (), parent, VOTableKeywords.SEG_DATAID_CREATORDID);
        if (dataID.isSetLogo ())
            this.addSedParam (dataID.getLogo (), parent, VOTableKeywords.SEG_DATAID_LOGO);
        if (dataID.isSetDataSource ())
            this.addSedParam (dataID.getDataSource (), parent, VOTableKeywords.SEG_DATAID_DATASOURCE);
        if (dataID.isSetCollection ())
        {
            for (TextParam cc : dataID.getCollection ())
                this.addSedParam (cc, parent, VOTableKeywords.SEG_DATAID_COLLECTION);
        }
        if (dataID.isSetContributor ())
        {
            for (TextParam cc : dataID.getContributor ())
                this.addSedParam (cc, parent, VOTableKeywords.SEG_DATAID_CONTRIBUTOR);
        }

        this.addCustomInfoToTable (dataID, parent);
    }

    /**
     * Add information from the Derived to the VOTable
     */
    private void addDerivedTable (DerivedData derived, VOElement parent) throws SedInconsistentException
    {
        VOElement group;
        if (derived.isSetSNR ())
            this.addSedParam (derived.getSNR (), parent, VOTableKeywords.SEG_DD_SNR);
        if (derived.isSetVarAmpl ())
            this.addSedParam (derived.getVarAmpl (), parent, VOTableKeywords.SEG_DD_VARAMPL);
        if (derived.isSetRedshift ())
        {
            SedQuantity redshift = derived.getRedshift ();
            group = this.addGroup (VOTableKeywords.SEG_DD_REDSHIFT, redshift.getGroupId (), parent);

            if (redshift.isSetValue ())
                this.addSedParam (redshift.getValue (), group, VOTableKeywords.SEG_DD_REDSHIFT_VALUE);
            if (redshift.isSetResolution ())
                this.addSedParam (redshift.getResolution (), group, VOTableKeywords.SEG_DD_REDSHIFT_RESOLUTION);
            if (redshift.isSetQuality ())
                this.addSedParam (redshift.getQuality (), group, VOTableKeywords.SEG_DD_REDSHIFT_QUALITY);
            if (redshift.isSetAccuracy ())
            {
                VOElement subGroup = this.addGroup (VOTableKeywords.SEG_DD_REDSHIFT_ACC, redshift.getAccuracy ().getGroupId (), group);
                this.addAccuracyToTable (redshift.getAccuracy (), subGroup, VOTableKeywords.SEG_DD_REDSHIFT_ACC);
                if (subGroup.getChildren ().length > 0)
                    group.appendChild (subGroup);
            }
            this.addCustomInfoToTable (redshift, group);
            if (group.getChildren ().length > 0)
                parent.appendChild (group);
        }

        this.addCustomInfoToTable (derived, parent);
    }

    /**
     * Add information from the custom parameters and groups to the VOTable
     */
    private void addCustomInfoToTable (Group group, VOElement parent)
    {
        List<? extends Param> paramList = group.getCustomParams ();
        List<? extends Group> groupList = group.getCustomGroups ();

        for (Param param : paramList)
            this.addSedParam (param, parent, VOTableKeywords.INVALID_UTYPE); 

        for (Group subgroup : groupList)
        {
            VOElement newParent = this.addGroup ("", group.getGroupId (), parent);
            this.addCustomInfoToTable (subgroup, newParent);

            if (newParent.getChildren ().length > 0)
                parent.appendChild (newParent);
        }
    }

    /**
     * Combine two utypes into a single utype. Throw an error if the merged
     * utype is invalid.
     */
    private int mergeUtypes (int baseUtype, int suffixUtype, String function)
        throws SedInconsistentException
    {

        int newUtype = VOTableKeywords.mergeUtypes (baseUtype, suffixUtype);
        if (newUtype == VOTableKeywords.INVALID_UTYPE)
            throw new SedInconsistentException (function + ": Invalid utype created through merge. Merged types "+VOTableKeywords.getName (baseUtype) + ", " + VOTableKeywords.getName (suffixUtype));

        return newUtype;
    }

    /**
     * Create VOElement group and assign the utype to it. The new
     * group is returned.
     * 
     */
    private VOElement addGroup (int utype, String id, VOElement parent)
    {
        return this.addGroup (VOTableKeywords.getName (utype, this.namespace), id, parent);
    }

    /**
     * Create VOElement group and assign the utype to it. The new
     * group is returned.
     *
     */
    private VOElement addGroup (String utype, String id, VOElement parent)
    {
        Document document = parent.getOwnerDocument ();
        VOElement group = (VOElement)document.createElement(VOTableKeywords._GROUP);

        if ((utype != null) && (!utype.equals ("")))
            group.setAttribute (VOTableKeywords._UTYPE, utype);
        if ((id != null) && (!id.equals ("")))
            group.setAttribute (VOTableKeywords._ID, id);
        return group;

    }


    /**
     * Extract the data from the segment into a VOTablePointGroup. This
     * class maps the data to a generic table while storing the group
     * hierarchy.
     * 
     */
    private VOTablePointGroup extractData (ArrayOfPoint pointData, TableElement voTable) throws SedInconsistentException

    {
        VOTablePointGroup pointsGroup;

        pointsGroup = new VOTablePointGroup(VOTableKeywords.SEG_DATA);
        pointsGroup.utype = VOTableKeywords.SEG_DATA;

        if (pointData.getLength () == 0)
            return pointsGroup;

        List <Point> pointList = pointData.getPoint ();

        for (int ii=0; ii<pointList.size (); ii++)
        {
            Point point = pointList.get(ii);
            if (point.isSetTimeAxis ())
            {
                this.processSedCoordData (point.getTimeAxis (),
                                          pointsGroup,
                                          ii,
                                          VOTableKeywords.SEG_DATA_TIMEAXIS);

            }
            if (point.isSetSpectralAxis ())
            {
                this.processSedCoordData (point.getSpectralAxis (),
                		                  pointsGroup,
                                          ii,
                                          VOTableKeywords.SEG_DATA_SPECTRALAXIS);
            }
            if (point.isSetFluxAxis ())
            {
                this.processSedQuantityData (point.getFluxAxis (),
                		                  pointsGroup,
                                          ii,
                                          VOTableKeywords.SEG_DATA_FLUXAXIS);
            }
            if (point.isSetBackgroundModel ())
            {
                this.processSedQuantityData (point.getBackgroundModel (),
                		                  pointsGroup,
                                          ii,
                                          VOTableKeywords.SEG_DATA_BGM);
            }

            this.processCustomData (point, pointsGroup, ii);

        }
        
        return pointsGroup;
    }

    /**
     * Add data from the VOTablePointGroup to the VOTable and to a startable.
     * Data values are stored in the startable, while field references and groups
     * are written to the votable.
     * 
     * Return null if there is no data to be stored in the star table
     */
    private SedStarTable addDataToTable (VOTablePointGroup pointsGroup, TableElement voTable)
    {

        SedStarTable starTable = null;
        List<Field> fields = new ArrayList<Field> ();
        Field fieldArray[] = {};
        String fieldIdArray[];

        // write the group information
        pointsGroup.addToVOTable (voTable, this.namespace);

        // store the data in the star table
        pointsGroup.getDataColumns (fields);
        fieldArray = fields.toArray (fieldArray);
        fieldIdArray = new String[fieldArray.length];
        for (int ii=0; ii<fields.size (); ii++)
            fieldIdArray[ii] = fields.get(ii).getId ();

        // if there are no columns then there's no data and no point to a startable
        if (fieldArray.length > 0)
            starTable = new SedStarTable (fieldArray, fieldIdArray, pointsGroup.getDataTable ());

        return starTable;

    }

    /**
     * Extract the data and metadata from the SedCoord into the data table.
     */
    private void processSedCoordData ( SedCoord axis,
                                  VOTablePointGroup pointsGroup,
                                  int row,
                                  int utype) throws SedInconsistentException

    {
        Param param;
        int newUtype;
        String functionName = "processSedCoordData";
        VOTablePointGroup subgroup;

        subgroup = pointsGroup.createSubGroup (utype, axis.getGroupId (), utype);

        if (axis.isSetValue ())
        {
            param = (DoubleParam)axis.getValue ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_TIMEAXIS_VALUE, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (axis.isSetResolution ())
        {
            param = (DoubleParam)axis.getResolution ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_TIMEAXIS_RESOLUTION, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (axis.isSetAccuracy ())
        {
            Accuracy accuracy = axis.getAccuracy ();
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_TIMEAXIS_ACC, functionName);
            this.processAccuracyData (accuracy, subgroup, row, newUtype);
        }

        this.processCustomData (axis, subgroup, row);
    }


    /**
     * Extract the data and metadata from the SedQuantity into the data table.
     */
    private void processSedQuantityData ( SedQuantity axis,
                                  VOTablePointGroup pointsGroup,
                                  int row,
                                  int utype) throws SedInconsistentException

    {
        Param param;
        int newUtype;
        String functionName = "processSedQuantityData";
        VOTablePointGroup subgroup;

        subgroup = pointsGroup.createSubGroup (utype, axis.getGroupId (), utype);

        if (axis.isSetValue ())
        {
            param = (DoubleParam)axis.getValue ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_VALUE, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (axis.isSetResolution ())
        {
            param = (DoubleParam)axis.getResolution ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_RESOLUTION, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (axis.isSetQuality ())
        {
            param = (IntParam)axis.getQuality ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_QUALITY, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (axis.isSetAccuracy ())
        {
            Accuracy accuracy = axis.getAccuracy ();
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC, functionName);
            this.processAccuracyData (accuracy, subgroup, row, newUtype);
        }

        this.processCustomData (axis, subgroup, row);
    }

    /**
     * Extract the data and metadata from the Accuracy into the data table.
     */
    private void processAccuracyData (Accuracy accuracy,
                                  VOTablePointGroup pointsGroup,
                                  int row,
                                  int utype) throws SedInconsistentException
    {
        Param param;
        int newUtype;
        String functionName = "processAccuracyData";

        VOTablePointGroup subgroup = pointsGroup.createSubGroup (utype, accuracy.getGroupId (), utype);

        if (accuracy.isSetBinLow ())
        {
            param = (DoubleParam)accuracy.getBinLow ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_BINLOW, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));
            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (accuracy.isSetBinHigh ())
        {
            param = (DoubleParam)accuracy.getBinHigh ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_BINHIGH, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));


            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));
            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (accuracy.isSetBinSize ())
        {
            param = (DoubleParam)accuracy.getBinSize ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_BINSIZE, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (accuracy.isSetStatError ())
        {
            param = (DoubleParam)accuracy.getStatError ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_STATERR, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (accuracy.isSetStatErrLow ())
        {
            param = (DoubleParam)accuracy.getStatErrLow ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRLOW, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (accuracy.isSetStatErrHigh ())
        {
            param = (DoubleParam)accuracy.getStatErrHigh ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_STATERRHIGH, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (accuracy.isSetSysError ())
        {
            param = (DoubleParam)accuracy.getSysError ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_SYSERR, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }
        if (accuracy.isSetConfidence ())
        {
            param = (DoubleParam)accuracy.getConfidence ().clone ();
            param.setUcd (this.getParamUcd (param, utype));
            newUtype = this.mergeUtypes (utype, VOTableKeywords.SEG_DATA_FLUXAXIS_ACC_CONFIDENCE, functionName);
            if (!param.isSetInternalId ())
                param.setInternalId (VOTableKeywords.getName (newUtype));

            if (!subgroup.hasField (newUtype))
            {
                Field field = this.newField (param, newUtype);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
                subgroup.addField (field);
            }
            subgroup.addData (row, param);
        }

        this.processCustomData (accuracy, subgroup, row);
    }

    private void processCustomData (Group parent, 
                                    VOTablePointGroup pointsGroup,
                                    int row)
    {

        List<? extends Param> params = parent.getCustomParams ();
        List<? extends Group> groups = parent.getCustomGroups ();
        VOTablePointGroup subgroup;

        for (Param param : params) 
        {
            Field field = this.newField (param, VOTableKeywords.INVALID_UTYPE);
            if (!pointsGroup.hasField (field.getInternalId ()))
            {
                pointsGroup.addField (field);
                if (!field.isSetId ())
                    field.setId (this.baseId+this.refCount++);
            }
            pointsGroup.addData (row,param);
        }

        for (Group group : groups)
        {
            if (group.getLinkRef () != -1)
            {
                // push the id past the known utypes
                subgroup = pointsGroup.createSubGroup (
                               VOTableKeywords.getNumberOfUtypes ()+group.getLinkRef (), 
                               group.getGroupId (), VOTableKeywords.INVALID_UTYPE);
            }
            else
                subgroup = pointsGroup.createSubGroup (
                               group.getLinkRef (),
                               group.getGroupId (), VOTableKeywords.INVALID_UTYPE);


            this.processCustomData (group, subgroup, row);
        }

    }


    /**
     * Create a field from a Param 
     */
    private Field newField (Param param, int utype)
    {
    	Field field;
        if (param == null)
            return null;

        String utypeName;
        String ucd;

        if (utype != VOTableKeywords.INVALID_UTYPE)
        {
            utypeName = VOTableKeywords.getName(utype, this.namespace);
            ucd = this.getParamUcd (param, utype);
        }
        else
        {
            utypeName = null;
            ucd = param.getUcd ();
        }

    	if (param instanceof DoubleParam)
    		field = new Field ( param.getName(),
                             ucd,
                             ((DoubleParam)param).getUnit(),
                             utypeName,
                             param.getId ());
    	else if (param instanceof IntParam)
    		field = new Field ( param.getName(),
                            ucd,
                            ((IntParam)param).getUnit(),
                            utypeName,
                            param.getId ());
    	else
    		field = new Field ( param.getName(),
                            ucd,
                            null,
                            utypeName,
                            param.getId ());
    	field.setInternalId(param.getInternalId ());
    	return field;
    }

    /**
     * Update the ucd's which are dependent on other data information.
     */
    private void overrideParamInfo (Segment segment)
    {
        ArrayOfPoint data;
        List<Point> pointList;
        String spectralAxisName = null;

        if (!segment.isSetData ())
            return;

        data = segment.getData();

        pointList = data.getPoint ();

        if ((pointList == null) || pointList.isEmpty ())

            return;

        for (Point point : pointList)
        {
            if (point.isSetSpectralAxis () && point.getSpectralAxis().isSetValue())
            {
            	Param spectralAxis = point.getSpectralAxis ().getValue ();

                // get the spectral axis name
                if (spectralAxis.isSetName ())
                {
                    spectralAxisName = spectralAxis.getName ();
                    break;
                }
            }
        }


        // go through the ucds and replace variable components

        if (spectralAxisName != null)
        {
            for (int ii=0; ii<VOTableKeywords.getNumberOfUtypes (); ii++)
            {

                // update the spectral axis ucds with the spectral axis name
                String ucd = VOTableKeywords.overrideUcd (ii, "em", spectralAxisName);
                if (ucd != null)
                    this.ucdOverrides.put (ii, ucd);
            }
        }
    }

    /**
     * Get a ucd from Param. If the ucd is null it function looks for
     * default ucd values.
     */
    private String getParamUcd (Param param, int utype)
    {
        if (param.isSetUcd() )
            return param.getUcd();
        else if (this.ucdOverrides.containsKey (utype))
            return this.ucdOverrides.get (utype);
        else if (utype != VOTableKeywords.INVALID_UTYPE)
            return VOTableKeywords.getUcd (utype);
        
        return param.getUcd();
    }


    /**
     * Initialize members for each segments
     */

    private void initializeSegment ()
    {
        ucdOverrides = new HashMap<Integer, String> ();
    }

}

