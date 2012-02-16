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
* File: io/VOTableMapper.java
*
* Author:  jmiller              Created: Fri Nov 12 12:26:00 EST 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.net.URL;

//
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
//
import uk.ac.starlink.votable.VOStarTable;
import uk.ac.starlink.votable.TableElement;
import uk.ac.starlink.votable.VOElement;
import uk.ac.starlink.votable.dom.DelegatingAttr;
//
import uk.ac.starlink.table.ColumnInfo;
import uk.ac.starlink.table.DescribedValue;
//
import cfa.vo.sedlib.*;
import cfa.vo.sedlib.common.SedConstants;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedParsingException;
import cfa.vo.sedlib.common.ValidationErrorEnum;
import cfa.vo.sedlib.common.ValidationError;

import cfa.vo.sedlib.common.VOTableKeywords;
import cfa.vo.sedlib.common.Utypes;

/**
  Maps Sed objects to VOTable objects
*/
public class VOTableMapper extends SedMapper
{

    static final Logger logger = Logger.getLogger ("cfa.vo.sedlib");
    String namespace;
    Map <String, List <Param>> customData = new HashMap <String, List <Param>> ();
    int subGroupCount = 1;
    int customRefCount = 1;

    private boolean verbose = false;

    /**
      * Populates a Sed object with information from a VOTable object.
      *
      * @param data
      *    {@link VOElement}
      * @throws SedParsingException
      * @throws SedInconsistentException
      * @throws IOException
      */
    @Override
    public Sed populateSed (Object data, Sed sed) 
    	throws SedParsingException, SedInconsistentException, IOException, SedNoDataException
    {
        List<ValidationError> validationErrors = new ArrayList <ValidationError> ();
        String utype;

        if (sed == null)
           sed = new Sed ();

        VOElement root = (VOElement)data;


	// Declared namespace.
	// MCD NOTE: This needs to check for declared namespace(s) and verify
        //           that it recognizes those declarations.  Along the lines of
	//           "Does this file contain info that I understand"
	//           Allowed values s/b "sed", "spec", "phot"?
        this.namespace = this.extractNamespace(root);
        sed.setNamespace(this.namespace);

        // Get the RESOURCE elements using standard DOM methods.
        NodeList resources = root.getElementsByTagName (VOTableKeywords._RESOURCE);

        if (resources.getLength () > 1)
            throw new SedParsingException ("There cannot be multiple resources in the VOTable. Only one SED per document");
        if (resources.getLength () == 0)
            throw new SedParsingException ("The VOTable must have at least one resource.");
        
        VOElement resource = (VOElement) resources.item(0);

        // check that the Resource utype is valid
	// MCD NOTE: Should allow Spectrum instances to be valid Resource.
        utype = resource.getAttribute(VOTableKeywords._UTYPE);
        if ((utype != null) && (utype.length() > 0))
        {
            // Resource namespace should match declared SED model namespace, if any.
            if ((namespace != null) && 
            		!namespace.equalsIgnoreCase(VOTableKeywords.getNamespace(utype)))
                return sed;

	    // Check that we can use this Resource
            if (!VOTableKeywords.compare(utype, VOTableKeywords.SED))
            {
                // if it doesn't compare try adding a "spectrum." to the front.
                utype = "spectrum."+VOTableKeywords.removeNamespace(utype);
                if (!VOTableKeywords.compare (utype, VOTableKeywords.SED))
                {
                    ValidationError error = new ValidationError(ValidationErrorEnum.INVALID_RESOURCE_UTYPE);
                    error.addNote ("Value found "+utype);
                    validationErrors.add (error);
                }
            }
        }
        
        VOElement[] tables = resource.getChildrenByName (VOTableKeywords._TABLE);
        TableElement te;
        Segment segment;

        // Create a Segment from each TABLE and adds it to the sed
        for (int tblIdx = 0; tblIdx < tables.length; tblIdx++)
        {
            te = (TableElement)tables[tblIdx];

            // check Table for supported utype
            utype = te.getAttribute(VOTableKeywords._UTYPE);
            if ((utype != null) && (utype.length() > 0))
            {  
            	// Table namespace should match declared Spectrum model namespace, if any.
                if ((namespace != null) && 
                		!namespace.equalsIgnoreCase(VOTableKeywords.getNamespace(utype)))
                    continue;
                
                if (!VOTableKeywords.compare(utype, VOTableKeywords.SEG))
                {
                    if (!VOTableKeywords.compare(utype, VOTableKeywords.SPEC))
                    {
                        ValidationError error = new ValidationError (ValidationErrorEnum.INVALID_TABLE_UTYPE);
                        validationErrors.add (error);
                        error.addNote ("Value found "+utype);
                    }
                }
            }

	    // Extract segment from Table.
	    // NOTE: This will attempt to interpret the Table even if it does 
	    // not pass the above checks.. this is to allow for files which 
	    // do not specify a utype for the table.

            segment = this.extractSegmentFromTable(te);
	    if ( segment != null )
	    {
              // Returned Segment may be of any supported Spectrum class.
	      // Or null if the table not supported
	      // MCD NOTE: Throw/Catch Exception.

		sed.addSegment (segment);
	    }
        }
        if ((!sed.validate(validationErrors)) || (validationErrors.size() > 0))
        {
            logger.warning("Invalid Sed read.");
            for (ValidationError error : validationErrors)
		logger.warning(error.getErrorMessage ());
        }
        return sed;
    }

    /**
     * Populate the segment with the non-data parameter found in the table.
     * Creates a new segment for the table.
     * @param table
     *    {@link TableElement}
     * @return
     *    {@link Segment}
     */
    Segment extractSegmentFromTable(TableElement te)
       throws SedParsingException, IOException
    {
        Segment seg = null;

	String model  = null; 
	String flavor = null;
	String utype  = null;

	// Create a VOStarTable instance from input table.
        VOStarTable starTable = new VOStarTable( te );

	// Spectrum model tables should have a DataModel and Type parameter
	// which defines the model, version and flavor of the table contents.
	// Find and validate these
	List<DescribedValue> inparams = starTable.getParameters();

	// Find the DataModel parameter.  Is required and unique.
	for ( DescribedValue item : inparams )
	{
	    utype = item.getInfo().getUtype();
	    if ( utype != null &&  utype.toUpperCase().endsWith("DATAMODEL") )
	    {
		model = item.getValueAsString(80).toUpperCase();
		break;
	    }
	}
	if ( (model == null) || model.equals("") || !(model.startsWith("SPECTRUM")) )
	{
	    // No DataModel parameter found, assume this is NOT a Spectral Model Table.
	    // OR DataModel parameter not expected value.
	    return null;
	}

	// Type should be in same portion as model (in hierarchy), with same namespace.
	// Need to look for this explicitly because there is more than one Type.
	String tmpstr = utype.toUpperCase().replace("DATAMODEL", "TYPE");

	flavor = "SPECTRUM"; // default
	for ( DescribedValue item : inparams )
	{
	    utype = item.getInfo().getUtype();
	    if ( utype != null && utype.toUpperCase().equals(tmpstr) )
	    {
		flavor = item.getValueAsString(80).toUpperCase();
		break;
	    }
	}

	if ( this.verbose )
	{
	    System.out.println("INFO: DataModel      = "+ model);
	    System.out.println("INFO: Spectral Type  = "+ flavor);
	    System.out.println("INFO:");
	}

        // Get UType list appropriate for this DataModel and Type for processing 
	// the table contents.
	// utypes = new VOUtypes( model, flavor );
        Utypes utypes = new Utypes();


	// Use Type parameter to define the Spectral Model subclass being instantiated.
	if ( flavor.equals("SPECTRUM") )
	    seg = (Segment) new Spectrum();
	else
	    seg = new Segment();

	if ( this.verbose )
	{
	    System.out.println("INFO: Creating instance of " + seg.getClass().getName());
	    System.out.println("INFO:");
	    System.out.println("INFO: Process Parameters... ");
	    printParameterList( inparams );
	}

        // Process parameters
	processSegmentLevelParams( seg, utypes, inparams );

	if ( this.verbose )
	{
	    System.out.println("INFO:");
	    System.out.println("INFO: Segment Level Parameters Processed: " );
	    printParameterList( inparams );
	}

	// **********  Point Level Info **********

	// Process items which apply at the Point level.

	// Table columns
	if ( this.verbose )
	{
	    System.out.println("INFO:");
	    System.out.println("INFO: Process Point Data... ");
	}
	processPointLevelData( seg, utypes, starTable );

	if ( this.verbose )
	{
	    System.out.println("INFO:");
	    System.out.println("INFO: Point Level Data Processed: ");
	}

	// Point level constants
	processPointLevelParams( seg, utypes, inparams );

	if ( this.verbose )
	{
	    System.out.println("INFO:");
	    System.out.println("INFO: Point Level Parameters Processed: ");
	    printParameterList( inparams );
	}

	// Puts custom data onto Points
        this.distributeCustomData(seg);


	// **********  Custom Parameters **********

	if ( this.verbose )
	{
	    System.out.println("INFO:");
	    System.out.println("INFO: Process Custom Parameters... ");
	}

	// Process items which do not map to the model.
	// Custom columns are handled in processPointLevelData() where they
	// are put on the customData has table of this class.. 
	processCustomParams( seg, utypes, inparams );
	if ( this.verbose )
	{
	    System.out.println("INFO:");
	    System.out.println("INFO: Custom Parameters Processed: ");
	    printParameterList( inparams );
	}

        return seg;
    }

    /**
     * Handles Segment Level information from the input StarTable parameter list, 
     * which map to Param Type objects.
     *
     * The input segment will be updated.
     * Input parameters which are handled by this method are removed
     * from the List.
     *
     * @param segment
     *     {@link Segment}
     * @param utypes
     *     {@link Utypes}
     * @param List<DescribedValue>
     *    {@link DescribedValue}
     */
    private void  processSegmentLevelParams( Segment seg, Utypes utypes, List<DescribedValue> inparams ) 
           throws SedParsingException, IOException
    {
	List <CharacterizationAxis> axes = null;
	CharacterizationAxis axis = null;

	String utype  = null;
	int utypeNum;

	Iterator itr = inparams.iterator();
	DescribedValue item;
	Object value;

	while( itr.hasNext() )
	{
	    item = (DescribedValue)itr.next();
	    value = null;

	    // Translate utype to number (enum).
	    utype = item.getInfo().getUtype();
	    utypeNum = utypes.getUtypeNum( utype );

	    // Unrecognized utype.
	    if ( utypeNum == utypes.INVALID_UTYPE)
		continue;

	    // Immutable utypes.
	    if ( ( utypeNum == utypes.DATAMODEL ) ||
		 ( utypeNum == utypes.LENGTH ) )
	    {
		itr.remove();
		continue;
	    }

	    if ( utypes.isCharAxisUtype( utypeNum ) )
	    {
		if ( axes == null )
		{
		    axes = seg.createChar().createCharacterizationAxis();
		    if ( axes.isEmpty() )
			axes.add( new CharacterizationAxis() );

		    axis = axes.get( axes.size() - 1 );
		}

		if ( CharAxisFieldIsSet( axis, utypeNum ) )
		{
		    // Field is already set, assume new axis definition.
		    axis = new CharacterizationAxis();
		    axes.add( axis );
		}

		value = axis.getValueByUtype( utypeNum, true );
		value = loadValue( item, value );
		axis.setValueByUtype( utypeNum, value );

		itr.remove();
	    }
	    else
	    {
		try {

		    if ( utypes.isCollectionUtype( utypeNum ) || 
			 utypes.isContributorUtype( utypeNum ) )
		    {
			// These two are lists which must be added to.
			// The expectation is that there will be 1 String value per instance
			// of this utype.
			TextParam tmp = new TextParam();
			loadParam( item, tmp );

			List<TextParam> params = (List<TextParam>)seg.getValueByUtype( utypeNum, true );
			if ( params != null )
			{
			    params.add( tmp );
			    seg.setValueByUtype( utypeNum, params );
			}
			itr.remove();
		    }
		    else
		    {
			// Attempt to get value from  Utype interface
			value = seg.getValueByUtype( utypeNum, true );
			if ( value != null )
			{
			    value = loadValue( item, value );
			    seg.setValueByUtype( utypeNum, value );
			    itr.remove();
			}
		    }
		}
		catch ( SedInconsistentException expa)
		{
		  // not matched.. no problem.
		}
		catch (Exception expb)
		{
		    logger.warning(expb.getMessage());
		}
	    }
	}

	return;
    }

    /**
     * Populate the segment with data specific information. 
     * 
     * The input segment will be updated.
     * @param table
     *    {@link TableElement}
     * @param segment
     *     {@link Segment}
     */
    private void  processPointLevelData( Segment segment, Utypes utypes, VOStarTable starTable ) 
           throws SedParsingException, IOException
    {

        Object   values  = null;
        double[] dValues = null;
        int[]    iValues = null;
	String   strval  = null;

	String utype = null;
	int utypeNum;

	int nrows = (int)starTable.getRowCount();
	int ncols = (int)starTable.getColumnCount();

        //  Get a fresh array of data points for us to fill in and
        //  stick it in the segment.  We need to do this because the
        //  list will be traversed for each column and data added to
        //  the points so we cannot simply add a new Point as we loop
        //  over the rows.
        ArrayOfPoint points = new ArrayOfPoint();
        List<Point> pointList = points.createPoint();
        
        for ( int iRow = 0; iRow < starTable.getRowCount(); iRow++)
            pointList.add( iRow, new Point () );

	segment.setData( points );

        // Now set the values of all the datapoints for each column
        for ( int iCol = 0; iCol < ncols; iCol++ )
        {
            ColumnInfo infoCol = starTable.getColumnInfo(iCol);

            utype = infoCol.getUtype();
	    //if ( utype == null )
	    //  continue;

	    // Translate utype to number (enum).
	    utypeNum = utypes.getUtypeNum( utype );

	    // Unrecognized utype.
	    if ( utypeNum == utypes.INVALID_UTYPE)
	    {
		setCustomPointData( starTable, iCol );
		continue;
	    }

	    // MCD NOTE: At this point, the item could be ANY recognized utype.
	    // would like a further screen for PointLevel only.. to catch utypes
	    // which are not supposed to vary perPoint...

	    // Recognized column, extract StarTable data as array of values.
	    Class dataClass = infoCol.getContentClass();

	    if ((dataClass == Integer.class) || (dataClass == Long.class) || (dataClass == Short.class))
	    {
		iValues = new int[ nrows ];
		for (int iRow=0; iRow < nrows; iRow++)
		{
		    strval = starTable.getCell( iRow, iCol ).toString();
		    iValues[iRow] = Integer.parseInt(strval);//  throws NumberFormatException
		}
		values = iValues;
	    }
	    else if ((dataClass == Double.class) || (dataClass == Float.class))
	    {
		dValues = new double[ nrows ];
		for (int iRow=0; iRow < nrows; iRow++)
		{
		    strval = starTable.getCell( iRow, iCol ).toString();
		    dValues[iRow] = Double.valueOf(strval).doubleValue(); //  throws NumberFormatException
		}
		values = dValues;
	    }

	    // Assign values for this column to Points
	    try
	    {
		points.setDataValues( values, utypeNum );
	    }
	    catch (SedInconsistentException exp)
	    {
		throw new SedParsingException("Problem setting Data values for " + utype);
	    }

	    // Define Field info. for this column
	    try
	    {
		String id = null;
		if ( infoCol.getAuxDatum(VOStarTable.ID_INFO) != null )
		    id = infoCol.getAuxDatum(VOStarTable.ID_INFO).getValue().toString();

		Field field = new Field (infoCol.getName(),
					 infoCol.getUCD(), 
					 infoCol.getUnitString(), 
					 null,
					 id );
		points.setDataInfo( field, utypeNum );
	    }
	    catch (SedException exp)
	    {
		throw new SedParsingException("Problem setting field info for " + utype);
	    }
	}
     
    }


    /**
     * Handles information from the input StarTable parameter list which
     * map to Point Level model elements.
     *
     * These values are constant for all Points (rows).
     *
     * NOTE:
     *   The expectation is that the column data has already been processed 
     *   so that the Point array is already established (processPointLevelData).
     *
     * The input segment will be updated.
     * Input parameters which are handled by this method are removed
     * from the List.
     *
     * @param segment
     *     {@link Segment}
     * @param utypes
     *     {@link Utypes}
     * @param List<DescribedValue>
     *    {@link DescribedValue}
     */
    private void  processPointLevelParams( Segment seg, Utypes utypes, List<DescribedValue> inparams ) 
           throws SedParsingException, IOException
    {
        Object   values  = null;
        double[] dValues = null;
        int[]    iValues = null;
	String   strval  = null;

	String utype  = null;
	int utypeNum;

	Iterator itr = inparams.iterator();
	DescribedValue item;

        ArrayOfPoint points = seg.getData();
	int nrows = seg.getLength();

	while( itr.hasNext() )
	{
	    item = (DescribedValue)itr.next();

	    utype = item.getInfo().getUtype();

	    // Translate utype to number (enum).
	    utypeNum = utypes.getUtypeNum( utype );

	    if ( utypeNum == utypes.INVALID_UTYPE )
		continue;

	    // We want to know that this is a Point Level UType..
	    // So that the order of processing the parameter list is not important.
	    // For now.. use knowledge of UType enumerations, but would be better
	    // if the utypes class itself could tell us this.
	    if ( (utypeNum < utypes.SEG_DATA ) || ( utypeNum > utypes.SEG_DATA_BGM_ACC_CONFIDENCE ) )
		continue;


	    // Recognized column, define array of values.
	    Class dataClass = item.getInfo().getContentClass();

	    if ((dataClass == Integer.class) || (dataClass == Long.class) || (dataClass == Short.class))
	    {
		iValues = new int[ nrows ];
		for (int iRow=0; iRow < nrows; iRow++)
		{
		    strval = item.getValue().toString();
		    iValues[iRow] = Integer.parseInt(strval);//  throws NumberFormatException
		}
		values = iValues;
	    }
	    else if ((dataClass == Double.class) || (dataClass == Float.class))
	    {
		dValues = new double[ nrows ];
		for (int iRow=0; iRow < nrows; iRow++)
		{
		    strval = item.getValue().toString();
		    dValues[iRow] = Double.valueOf(strval).doubleValue(); //  throws NumberFormatException
		}
		values = dValues;
	    }

	    // Assign values for this column to Points
	    try
	    {
		points.setDataValues( values, utypeNum );
	    }
	    catch (SedInconsistentException exp)
	    {
		throw new SedParsingException("Problem setting Data values for " + utype);
	    }

	    // Define Field info. for this column
	    try
	    {
		Field field = new Field (item.getInfo().getName(),
					 item.getInfo().getUCD(), 
					 item.getInfo().getUnitString(), 
					 null,
					 null );

		// create an alternate id for when the custom object has no id.
		if (! field.isSetInternalId() )
		{
		    field.setInternalId("_customId"+this.customRefCount);
		    this.customRefCount++;
		}

		points.setDataInfo( field, utypeNum );
	    }
	    catch (SedException exp)
	    {
		throw new SedParsingException("Problem setting field info for " + utype);
	    }
	}

	return;
    }


    private void  processCustomParams( Segment seg, Utypes utypes, List<DescribedValue> inparams ) 
           throws SedParsingException, IOException
    {
	String utype  = null;
	int utypeNum;

	Iterator itr = inparams.iterator();
	DescribedValue item;

	while( itr.hasNext() )
	{
	    item = (DescribedValue)itr.next();

	    utype = item.getInfo().getUtype();

	    // Screen out known unwanted VOTable items.
	    if ( utype == null )
	    {
		// utype associated with the Table
		if ( item.getInfo().getName().equals("utype") )
		{
		    itr.remove();
		    continue;
		}
	    }

	    // Translate utype to number (enum).
	    utypeNum = utypes.getUtypeNum( utype );

	    if ( utypeNum != utypes.INVALID_UTYPE )
		continue;

	    // Create appropriate Parameter Type for this item.
	    Param param = null;
	    Class dataClass = item.getInfo().getContentClass();
	    if ((dataClass == Integer.class) || (dataClass == Long.class) || (dataClass == Short.class))
	    {
	        param = new IntParam();
	    }
	    else if ((dataClass == Double.class) || (dataClass == Float.class))
	    {
	        param = new DoubleParam();
	    }
	    else if (dataClass == String.class)
	    {
	        param = new TextParam();
	    }
	    else if ( dataClass == URL.class )
	    {
	        logger.log (Level.WARNING, "Parameter of unsupported type will be lost; ''{0}'' = {1} type.", new Object[]{item.getInfo().getName(), dataClass.getSimpleName()});
		continue;
	    }
	    else
	    {
	        logger.warning ("The data type, for the custom parameter is not supported. Supported datatypes include char, int, short, long, float and double");
	        continue;
	    }
	    
	    // Load our Param with StarTable parameter info.
	    this.loadParam( item, param );
	    
	    // How is Utype for custom parameters defined/propogated?
	    
	    // Set ID which is not included in the load (should it?)
	    //param.setId( item.getInfo().getAuxDatum(VOStarTable.ID_INFO).getValue().toString() );
	    if (! param.isSetInternalId())
	    {
	        // create an alternative id for when the custom id is not set
	        param.setInternalId( "_customId" + this.customRefCount );
	        this.customRefCount++;
	    }
	    
            try
	    {
	        // NOTE: Here we add all custom parameters on the Segment Level.
	        // May want to identify the base by interpreting the Utype according
	        // to the Utype extension rules and place the parameter on that object
	        // so that it would be serialized in the same grouping.
                seg.addCustomParam( param );
            }
            catch (SedException exp)
            {
                logger.warning(exp.getMessage());
            }

	    itr.remove();
	}
    }


    /**
     * Populate points with custom data found in the segment
     */
    private void  distributeCustomData (Segment segment)
    {
        List<Point> points;
        ArrayOfPoint dataPoints;
        dataPoints = segment.getData();
        List<Param> data;
        
        if (!segment.isSetData ())
        	return;

        points = dataPoints.getPoint ();

        // write the rest of the custom data to the points
        for (String id : this.customData.keySet ())
        {
            data = this.customData.get(id);

            for (int ii=0; ii<data.size (); ii++)
            {
                // NOTE we assume that the data was set up correctly such
                // that the number of groups will equal or excede the number of
                // data elements

            	try
            	{
                    points.get(ii).addCustomParam (data.get(ii));
            	}
            	catch (SedException exp)
            	{
            		logger.warning(exp.getMessage ());
            	}
            }
        }

        this.customData.clear ();
    }


    private void setCustomPointData( VOStarTable starTable, int iCol )
                throws SedParsingException, IOException
    {
	ColumnInfo infoCol = starTable.getColumnInfo(iCol);
	int nrows = (int)starTable.getRowCount();

	boolean updateCustomRefCount = false;

	String id = null;
	if ( infoCol.getAuxDatum(VOStarTable.ID_INFO) != null )
	    id = infoCol.getAuxDatum(VOStarTable.ID_INFO).getValue().toString();

	String idtag = null;
	if ( id == null )
	    idtag = "_customId"+this.customRefCount;
	else
	    idtag = id;

	/* Param list to hold custom data */
	List <Param> params = new ArrayList<Param>( nrows );

	/* Determine data type */
	Class dataClass = infoCol.getContentClass();

	for ( int iRow = 0; iRow < nrows; iRow++ )
	{
	    Param param = null;
	    if ((dataClass == Integer.class) || (dataClass == Long.class) || (dataClass == Short.class))
	    {
		param = this.newIntParam( starTable.getCell( iRow, iCol ), infoCol );
	    }
	    else if ((dataClass == Double.class) || (dataClass == Float.class))
	    {
		param = this.newDoubleParam( starTable.getCell( iRow, iCol ), infoCol );
	    }
	    else if (dataClass == String.class)
	    {
		param = this.newTextParam( starTable.getCell( iRow, iCol ), infoCol );
	    }
	    else
		logger.warning ("The data type, for the custom data is not supported. Supported datatypes include char, int, short, long, float and double");
                        
	    if (param == null)
	    	param = new Param();
                        
	    // if the field is potentially referenced somewhere
	    // store the params into a table otherwise add it
	    // directly to the point
	    if ( id != null )
	    {
	        param.setId( id );
		params.add(param);
	    }
	    else
	    {
	        // every field needs to have an id -- create one
	        param.setInternalId( idtag );
	        updateCustomRefCount = true;

		// Adding custom column to list.
		params.add(param);
	    }
            
	}
	if (updateCustomRefCount)
	    this.customRefCount++;
	
	if ( idtag != null)
	    this.customData.put( idtag, params );

    }

    /**
     *  Set a particular non-Param type field of a CharacterizationAxis 
     *   from StarTable information
     * 
     */
    private void setCharAxisField( CharacterizationAxis axis, int utypeNum, DescribedValue item )
    {
	String value = null;

	if ( axis == null )
	    return;

	// Extract the values (as string)
	if ( item.getValue() == null )
	    value = SedConstants.DEFAULT_STRING ;
	else
	    value = item.getValue().toString() ;

	switch(utypeNum)
	{
	case Utypes.SEG_CHAR_FLUXAXIS_NAME:
	case Utypes.SEG_CHAR_SPATIALAXIS_NAME:
	case Utypes.SEG_CHAR_SPECTRALAXIS_NAME:
	case Utypes.SEG_CHAR_TIMEAXIS_NAME:
	    axis.setName( value );

	    /* serializations may put ucd and unit on name parameter. */
	    value = item.getInfo().getUCD();
	    if ( value != null )
		axis.setUcd( value );

	    value = item.getInfo().getUnitString();
	    if ( value != null )
		axis.setUnit( value );
	    break;

	case Utypes.SEG_CHAR_FLUXAXIS_UNIT:
	case Utypes.SEG_CHAR_SPATIALAXIS_UNIT:
	case Utypes.SEG_CHAR_SPECTRALAXIS_UNIT:
	case Utypes.SEG_CHAR_TIMEAXIS_UNIT:
	    axis.setUnit( value );
	    break;

	case Utypes.SEG_CHAR_FLUXAXIS_UCD:
	case Utypes.SEG_CHAR_SPATIALAXIS_UCD:
	case Utypes.SEG_CHAR_SPECTRALAXIS_UCD:
	case Utypes.SEG_CHAR_TIMEAXIS_UCD:
	    axis.setUcd( value );
	    break;


	case Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_RANGE:
	case Utypes.SEG_CHAR_FLUXAXIS_COV_SUPPORT_RANGE:
	case Utypes.SEG_CHAR_TIMEAXIS_COV_SUPPORT_RANGE:
	case Utypes.SEG_CHAR_SPATIALAXIS_COV_SUPPORT_RANGE:
	case Utypes.SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_RANGE:
	    Interval range = new Interval();
	    loadParam( item, range );
	    axis.createCoverage().createSupport().createRange().add( range );

	default:
	    break;
	}

	return;
    }


    /**
     *  Set a particular non-Param type field of a CoordFrame Type object 
     *   from StarTable information
     * 
     */
    private void setCoordFrameField( CoordFrame frame, int utypeNum, DescribedValue item )throws SedInconsistentException
    {
	String value = null;

	if ( frame == null )
	    return;

	// Extract the values (as string)
	if ( item.getValue() == null )
	    value = SedConstants.DEFAULT_STRING ;
	else
	    value = item.getValue().toString() ;

	switch(utypeNum)
	{
	case Utypes.SEG_CS_GENFRAME_ID:
	case Utypes.SEG_CS_REDFRAME_ID:
	case Utypes.SEG_CS_SPECTRALFRAME_ID:
	case Utypes.SEG_CS_TIMEFRAME_ID:
	case Utypes.SEG_CS_SPACEFRAME_ID:
	    if ( frame.isSetId() )
		throw new SedInconsistentException ("CoordFrame field already set, cannot have multiple definitions.");
	    else
		frame.setId( value );

	    break;
	case Utypes.SEG_CS_GENFRAME_UCD:
	case Utypes.SEG_CS_REDFRAME_UCD:
	case Utypes.SEG_CS_SPECTRALFRAME_UCD:
	case Utypes.SEG_CS_TIMEFRAME_UCD:
	case Utypes.SEG_CS_SPACEFRAME_UCD:
	    if ( frame.isSetUcd() )
		throw new SedInconsistentException ("CoordFrame field already set, cannot have multiple definitions.");
	    else
		frame.setUcd( value );
	    break;
	case Utypes.SEG_CS_GENFRAME_NAME:
	case Utypes.SEG_CS_REDFRAME_NAME:
	case Utypes.SEG_CS_SPECTRALFRAME_NAME:
	case Utypes.SEG_CS_TIMEFRAME_NAME:
	case Utypes.SEG_CS_SPACEFRAME_NAME:
	    if ( frame.isSetName() )
		throw new SedInconsistentException ("CoordFrame field already set, cannot have multiple definitions.");
	    else
	    {
		frame.setName( value );

		/* serializations may put ucd on name parameter. */
		value = item.getInfo().getUCD();
		if ( value != null )
		    frame.setUcd( value );
	    }
	    break;
	case Utypes.SEG_CS_GENFRAME_REFPOS:
	case Utypes.SEG_CS_REDFRAME_REFPOS:
	case Utypes.SEG_CS_SPECTRALFRAME_REFPOS:
	case Utypes.SEG_CS_TIMEFRAME_REFPOS:
	case Utypes.SEG_CS_SPACEFRAME_REFPOS:
	    if ( frame.isSetReferencePosition() )
		throw new SedInconsistentException ("CoordFrame field already set, cannot have multiple definitions.");
	    else
		frame.setReferencePosition( value );

	    break;

	case Utypes.SEG_CS_REDFRAME_DOPPLERDEF:
	    try {
		((RedshiftFrame)frame).setDopplerDefinition( value );
	    } catch (ClassCastException e) {
		//logger.warning ("The utype, "+ utype + " is expected to be part of RedshiftFrame. The parameter will be ignored.");
	    }
	    break;

	default:
	    break;
	}

	return;
    }


    private Object loadValue( DescribedValue item, Object obj )
    {
	Object outval = null;

    	if ((item == null)|| (obj == null) )
	    return outval;


	if ( obj instanceof String )
	{
	    if ( item.getValue() == null )
		obj = SedConstants.DEFAULT_STRING ;
	    else
		obj = item.getValue().toString();
	}
	else if (( obj instanceof DoubleParam )||
		 ( obj instanceof IntParam ) ||
		 ( obj instanceof TextParam ) ||
		 ( obj instanceof DateParam )
		 )
	{
	    loadParam( item, (Param)obj );
	}
	else if ( obj instanceof Interval  ) 
	{
	    loadParam( item, (Interval)obj );
	}
	else if ( obj instanceof ArrayList  ) 
	{
	    loadParam( item, (ArrayList)obj );
	}
	else
	    logger.log (Level.WARNING, "loadValue:  {0} = Unsupported data type - {1}", new Object[]{item.getInfo().getName(), obj.getClass().getSimpleName()});

	outval = obj;

	return outval;
    }

    /**
     *  Load a Param Type object from StarTable information
     *  Param Types:
     *    Param
     *    DateParam
     *    DoubleParam
     *    IntParam
     *    RangeParam
     *    SkyRegion
     *    TextParam
     *    TimeParam
     */
    private void loadParam( DescribedValue item, Param param )
    {
    	if ((item == null)||(param == null))
	    return;

	if ( item.getValue() == null )
	    param.setValue( SedConstants.DEFAULT_STRING );
	else
	    param.setValue( item.getValue().toString() );
	
	if ( item.getInfo() != null )
	{
	    param.setName( item.getInfo().getName() );
	    param.setUcd( item.getInfo().getUCD() );
	
	    if ( param instanceof DoubleParam )
		((DoubleParam)param).setUnit( item.getInfo().getUnitString() );
	    else if ( param instanceof IntParam )
		((IntParam)param).setUnit( item.getInfo().getUnitString() );
	    else if ( param instanceof TimeParam )
		((TimeParam)param).setUnit( item.getInfo().getUnitString() );
	    else if ( param instanceof RangeParam )
		((RangeParam)param).setUnit( item.getInfo().getUnitString() );
	}

	return;
    }

    /**
     *  Load a Param from StarTable information
     */
    private void loadParam ( DescribedValue item, List<? extends Param> params )
    {
    	if ((item == null)||(params == null))
	    return;

	// Local variables for parameter info.. pertains to all members of the list.
	String name  = null;
	String ucd   = null;
	String unit  = null;
	String[] values = null;

	if ( item.getInfo() != null )
	{
	    name = item.getInfo().getName();
	    ucd  = item.getInfo().getUCD();
	    unit = item.getInfo().getUnitString();
	}

	values = splitValuesIntoStrings( item.getValue() );

	int ii = 0;
	for ( Param param : params )
	{
	    // Some items may have either 1 or 2 values (*Axis.Coverage.Location), 
	    // If we are provided values, only set as many parameters as values.
	    // Null 'extra' parameters on list to propogate this fact.
	    if ( ( values != null ) && ( ii == values.length ) )
	    {
		params.set( ii, null );
		continue;
	    }

	    if ( param != null )
	    {
	    	param.setName( name );
	    	param.setUcd( ucd );
		
	    	if ( param instanceof DoubleParam )
	    	    ((DoubleParam)param).setUnit( unit );
	    	else if ( param instanceof IntParam )
	    	    ((IntParam)param).setUnit( unit );
	    	else if ( param instanceof TimeParam )
	    	    ((TimeParam)param).setUnit( unit );
	    	else if ( param instanceof RangeParam )
	    	    ((RangeParam)param).setUnit( unit );

		if ( (values == null) || (values[ii] == null ) )
		    param.setValue( SedConstants.DEFAULT_STRING );
		else
		    param.setValue( values[ii] );
	    }
	    ii++;
	}

	return;
    }

    /**
     *  Load an Interval object from StarTable Info
     *  Note: this is not really a Param Type object, but pretty
     *        close, so we overload the method.
     */
    private void loadParam( DescribedValue item, Interval param )
    {
    	if ((item == null)||(param == null))
	    return;

	String name  = null;
	String ucd   = null;
	String unit  = null;

	String[] sValues = null;

	if ( item.getInfo() != null )
	{
	    name = item.getInfo().getName();
	    ucd  = item.getInfo().getUCD();
	    unit = item.getInfo().getUnitString();
	}

	sValues = splitValuesIntoStrings( item.getValue() );
	if ( sValues != null )
	{
	    if ( sValues.length < 2 )
		logger.log (Level.WARNING, "Insufficent values supplied for Interval. Expected two (2) got {0}.", sValues.length);

	    for ( int ii = 0; ii < sValues.length; ii++ )
	    {
		if ( ii == 0 )		
		    param.setMin( new DoubleParam( sValues[ii], "", ucd, unit) );
		else
		    param.setMax( new DoubleParam( sValues[ii], "", ucd, unit) );
	    }
	}

        return;
    }

    private String[] splitValuesIntoStrings( Object invals )
    {
	double[] dValues = null;
	float[]  fValues = null;
	String[] sValues = null;

	if ( invals == null )
	    return sValues;

	if ( ( invals instanceof double[]) )
	{
	    dValues = (double[])invals;
	    sValues = new String[ dValues.length ];
	    for ( int ii = 0; ii < dValues.length; ii++ )
		sValues[ii] = Double.toString( dValues[ii] );
	}
	else if ( invals instanceof float[] )
	{
	    fValues = (float[])invals;
	    sValues = new String[ fValues.length ];
	    for ( int ii = 0; ii < fValues.length; ii++ )
		sValues[ii] = Float.toString( fValues[ii] );
	}
	else if ( invals instanceof String )
	{
	    String tmpstr = (String)invals;
	    sValues = tmpstr.split(IOConstants.LIST_SEPARATOR_PATTERN );
	}
	else if ( invals instanceof Double )
	{
	    sValues = invals.toString().split(IOConstants.LIST_SEPARATOR_PATTERN );
	}
	else
	    logger.warning ("Unsupported data type for Interval, expected float[], double[], String or Double.");


	return sValues;
    }

    /**
     *  Create a DoubleParam from StarTable information
     */
    private DoubleParam newDoubleParam(Object value, ColumnInfo colInfo)
    {
        DoubleParam param;
    	if (colInfo == null)
    		return null;
    	
        if (value != null)
            param = new DoubleParam(value.toString(),
				    colInfo.getName(),
				    colInfo.getUCD(),
				    colInfo.getUnitString());
        else
            param = new DoubleParam(SedConstants.DEFAULT_STRING,
				    colInfo.getName(),
				    colInfo.getUCD(),
				    colInfo.getUnitString());

        return param;
    }

    /**
     *  Create a IntParam from a StarTable information
     */
    private IntParam newIntParam(Object value, ColumnInfo colInfo)
    {
        IntParam param;
    	if (colInfo == null)
    		return null;
    	
        if (value != null)
            param = new IntParam(value.toString (),
				 colInfo.getName(),
				 colInfo.getUCD(),
				 colInfo.getUnitString());
        else
            param = new IntParam(SedConstants.DEFAULT_STRING,
				 colInfo.getName(),
				 colInfo.getUCD(),
				 colInfo.getUnitString());

        return param;
    }

    /**
     *  Create a TextParam from a StarTable information
     */
    private TextParam newTextParam(Object value, ColumnInfo colInfo)
    {
        TextParam param;
    	if (colInfo == null)
    		return null;
    	
        if (value != null)
	{
	    param = new TextParam(value.toString(),
				  colInfo.getName(),
				  colInfo.getUCD());
	}
        else
	{
	    param = new TextParam(null, 
				  colInfo.getName(),
				  colInfo.getUCD());
	}
        return param;
    }


    /**
     *  Get the namespace from the VOTable associated with the spectrum schema.
     */
    private String extractNamespace (VOElement element)
    {
        NamedNodeMap map = element.getAttributes();
        String name, value;
        String ns = null;
        for (int ii=0; ii<map.getLength  (); ii++)
        {
            DelegatingAttr attr = (DelegatingAttr)map.item(ii);
            name = attr.getName();
            value = attr.getValue();
            
            if (name.matches ("^xmlns:.*$") && value.matches ("^.*Spectrum.*\\.xsd$"))
            {
                if (ns == null)
                    ns = name.replaceFirst ("^.*xmlns:","");
                else
                    logger.warning ("Multiple namespace declarations were found for " +
                             SedConstants.SPECTRUM_SCHEMA_VERSION +
                             " while searching for Spectrum*.xsd." +
                             " The following namespace will be used, "+
                             ns);
            }
        }
        return ns;
    }

    /** 
     * Display info from Parameter List to standard out.
     */
    private void printParameterList( List<DescribedValue> params )
    {
	String utype = null;

	if ( params.isEmpty() )
	{
	    System.out.println("INFO: Parameter list is empty.");
	}
	else
	{
	    System.out.println("INFO: Parameters: " + params.size());
	    for ( DescribedValue item : params )
	    {
		utype = item.getInfo().getUtype();
		if ( utype == null )
		    utype = "null - " + item.getInfo().getName();

		System.out.format("INFO:   utype= %1$-60s %n", utype);
	    }
	}
    }

    // ******************** General? ******************************
    private boolean CharAxisFieldIsSet( CharacterizationAxis axis, int utypeNum )
    {
	boolean retval = false;

	try{

	    switch ( utypeNum )
	    {
	    case Utypes.SEG_CHAR_CHARAXIS_NAME:
		retval = axis.isSetName();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_UNIT:
		retval = axis.isSetUnit();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_UCD:
		retval = axis.isSetUcd();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_CAL:
		retval = axis.isSetCalibration();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_RESOLUTION:
		retval = axis.isSetResolution();
		break;
		
	    case Utypes.SEG_CHAR_CHARAXIS_ACC_BINLOW:
		retval = axis.getAccuracy().isSetBinLow();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_ACC_BINHIGH:
		retval = axis.getAccuracy().isSetBinHigh();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_ACC_BINSIZE:
		retval = axis.getAccuracy().isSetBinSize();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERR:
		retval = axis.getAccuracy().isSetStatError();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERRLOW:
		retval = axis.getAccuracy().isSetStatErrLow();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERRHIGH:
		retval = axis.getAccuracy().isSetStatErrHigh();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_ACC_SYSERR:
		retval = axis.getAccuracy().isSetSysError();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_ACC_CONFIDENCE:
		retval = axis.getAccuracy().isSetConfidence();
		break;
		
	    case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_EXTENT:
		retval = axis.getCoverage().getBounds().isSetExtent();
		break;

	    case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_MIN:
	    case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_START:
		retval = axis.getCoverage().getBounds().getRange().isSetMin();
		break;

	    case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_MAX:
	    case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_STOP:
		retval = axis.getCoverage().getBounds().getRange().isSetMax();
		break;
		
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINLOW:
		retval = axis.getCoverage().getLocation().getAccuracy().isSetBinLow();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINHIGH:
		retval = axis.getCoverage().getLocation().getAccuracy().isSetBinHigh();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINSIZE:
		retval = axis.getCoverage().getLocation().getAccuracy().isSetBinSize();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERR:
		retval = axis.getCoverage().getLocation().getAccuracy().isSetStatError();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRLOW:
		retval = axis.getCoverage().getLocation().getAccuracy().isSetStatErrLow();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRHIGH:
		retval = axis.getCoverage().getLocation().getAccuracy().isSetStatErrHigh();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_SYSERR:
		retval = axis.getCoverage().getLocation().getAccuracy().isSetSysError();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_CONFIDENCE:
		retval = axis.getCoverage().getLocation().getAccuracy().isSetConfidence();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_RESOLUTION:
		retval = axis.getCoverage().getLocation().isSetResolution();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_VALUE:
		retval = axis.getCoverage().getLocation().isSetValue();
		break;
		
	    case Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_AREA:
		retval = axis.getCoverage().getSupport().isSetArea();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_EXTENT:
		retval = axis.getCoverage().getSupport().isSetExtent();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_RANGE:
		retval = axis.getCoverage().getSupport().isSetRange();
		break;
		
	    case Utypes.SEG_CHAR_CHARAXIS_SAMPPREC_SAMPEXT:
		retval = axis.getSamplingPrecision().isSetSampleExtent();
		break;
	    case Utypes.SEG_CHAR_CHARAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
		retval = axis.getSamplingPrecision().getSamplingPrecisionRefVal().isSetFillFactor();
		break;
		
	    default:
		break;
	    }

	}
        catch (NullPointerException exp)
        {
            retval = false;
        }

	return retval;
    }

}  
