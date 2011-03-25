/***********************************************************************
*
* File: io/VOTablePointGroup.java
*
* Author:  jmiller      Created: Mon Nov 29 11:57:14 2010
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.TreeSet;
import java.util.logging.Logger;


import org.w3c.dom.Document;

import uk.ac.starlink.votable.VOElement;
import cfa.vo.sedlib.DoubleParam;
import cfa.vo.sedlib.Field;
import cfa.vo.sedlib.IntParam;
import cfa.vo.sedlib.Param;
import cfa.vo.sedlib.common.VOTableKeywords;

class VOTablePointGroup
{
    protected List<Boolean> constants;
    protected List<String> fieldIds;
    protected Map <String,Field> fields;
    protected List<List <Param>> data;
    protected TreeSet <Integer> fieldUtypes;
    protected int utype;
    protected String groupId;
    protected int id;
    
    static Logger logger = Logger.getLogger ("cfa.vo.sedlib");

    // keep the insert order of the subgroups while
    // being able to search for them by utype 
    // we're not expecting large numbers of subgroups so
    // a list should be sufficient 
    protected List <VOTablePointGroup> subgroups;

    public VOTablePointGroup (int id)
    {
        this.constants = new ArrayList <Boolean> ();
        this.data = new ArrayList <List<Param>> ();
        this.subgroups = new ArrayList <VOTablePointGroup> ();
        this.fields = new HashMap <String,Field> ();
        this.fieldIds = new ArrayList <String> ();
        this.fieldUtypes = new TreeSet <Integer> ();
        this.id = id;
        this.groupId = null;
    }

    public int getUtype ()
    {
        return this.utype;
    }

    public boolean hasField (int utype)
    {
        return this.fieldUtypes.contains (utype);
    }

    public boolean hasField (String id)
    {
        return fields.containsKey (id);
    }

    public void addField (Field field)
    {
        String id = field.getInternalId ();
        if (!field.isSetInternalId ())
        {
            logger.warning ("Cannot serialize fields with no ID. The field");
            return;
        }

        if (fields.containsKey (id))
            return;


        this.constants.add (true);
        this.fields.put (id, field);
        this.fieldIds.add (id);
        if (field.isSetUtype ())
            this.fieldUtypes.add (VOTableKeywords.getUtypeFromString (field.getUtype ()));
    }

    public void addData (int row, Param param)
    {
        Param firstParam = null;
        List <Param> paramCol;
        List <Param> firstCol;
        int fieldColumn;

        if (param.getInternalId () == null)
        {
            logger.warning ("Cannot serialize data parameters with no ID");
            return;
        }

        if (this.data.size () == row)
            this.data.add (new Vector<Param> ());

        paramCol = this.data.get(row);
        firstCol = this.data.get (0);

        fieldColumn = this.fieldIds.indexOf (param.getInternalId ());

        while (paramCol.size () <= fieldColumn)
            paramCol.add(null);

        paramCol.set(fieldColumn, param);

        
        // find a row where the parameter is not null
        for (int ii=1; ii<this.data.size (); ii++)
        {
            if (firstCol.size () <= fieldColumn)
            {
            	firstCol = this.data.get (ii);
            	continue;
            }
            
            firstParam = firstCol.get (fieldColumn);
            if ((firstParam != null) && (firstParam != param))
            	break;
            firstCol = this.data.get (ii);
        }
        // determine if there's a difference between this value
        // and other values in the column
        if (firstParam != null)
        {
        
            if ((firstParam.getValue () != null) ^ (param.getValue () != null))
                this.constants.set (fieldColumn, false);
            else if ((firstParam.getValue () != null) && 
                     (!firstParam.getValue ().equals (param.getValue ())))
                this.constants.set (fieldColumn, false);
        }
    }

    public VOTablePointGroup createSubGroup (int id, String groupId, int utype)
    {
        VOTablePointGroup subgroup = null;

        // check if the subgroup already exists
        if (id != -1)
        {
            for (VOTablePointGroup subgrp : this.subgroups)
            {
                if (subgrp.id == id)
                {
                    subgroup = subgrp;
                    break;
                }
            }
        }
        
        if (subgroup == null)
        {
            subgroup = new VOTablePointGroup (id);
            subgroup.groupId = groupId;
            subgroup.utype = utype;
            this.subgroups.add (subgroup);
        }

        return subgroup;
    }

    public void addToVOTable (VOElement parent, String namespace)
    {
        Document document = parent.getOwnerDocument ();
        VOElement group = (VOElement)document.createElement(VOTableKeywords._GROUP);

        if (utype != VOTableKeywords.INVALID_UTYPE)
            group.setAttribute (VOTableKeywords._UTYPE, VOTableKeywords.getName (this.utype, namespace));   

        if (this.groupId != null)
            group.setAttribute (VOTableKeywords._ID, this.groupId);
      
        for (int ii=0; ii<fieldIds.size (); ii++)
        {
            VOElement fieldRef = (VOElement)document.createElement("FIELDref");
            Field field = this.fields.get (this.fieldIds.get(ii));
            
            // create a field ref for non-constants or if there's only
            // a single row
            if ((!this.constants.get(ii)) || (this.data.size () == 1))
            {
                fieldRef.setAttribute("ref", field.getId());
                group.appendChild (fieldRef);
            }
            else
            {
                VOElement param = (VOElement) document.createElement(VOTableKeywords._PARAM);
                Param data = null;
                for (int jj=0; jj<this.data.size (); jj++)
                {
                	if (this.data.get(jj).size () > ii)
                	{
                	    data = this.data.get (jj).get(ii);
                	    if (data != null)
                		    break;
                	}
                }
                if (data != null)
                {
                    if (field.isSetUtype ())
                        param.setAttribute( VOTableKeywords._UTYPE, field.getUtype ());
                    if (field.isSetName ())
                        param.setAttribute(VOTableKeywords._NAME, field.getName());
                    if (field.isSetUcd ())
                        param.setAttribute (VOTableKeywords._UCD, field.getUcd());
                    if (field.isSetUnit ())
                        param.setAttribute (VOTableKeywords._UNIT, field.getUnit());
                    param.setAttribute (VOTableKeywords._VALUE, data.getValue ());
                    if (field.isSetId ())
                        param.setAttribute (VOTableKeywords._ID, field.getId ());

                    if (data instanceof DoubleParam)
                        param.setAttribute(  VOTableKeywords._DATATYPE, "double" );
                    else if (data instanceof IntParam)
                        param.setAttribute(  VOTableKeywords._DATATYPE, "int" );
                    else 
                    {
                        param.setAttribute(  VOTableKeywords._DATATYPE, "char" );
                        param.setAttribute(VOTableKeywords._ARRAYSIZE, "*");
                    }
                    group.appendChild (param);
                }
            }
        }

        for(VOTablePointGroup subgroup : this.subgroups)
            subgroup.addToVOTable (group, namespace);

        if (group.getChildren ().length > 0)
            parent.appendChild (group);

    }

    public Param[][] getDataTable ()
    {
        Param dataTable[][];
        int rows;
        int columns = 0;
        int col = 0;
        List<Boolean> allConstants = new ArrayList <Boolean>();
        List<List <Param>> allData = new ArrayList <List <Param>>();

        this.getAllConstants (allConstants);
        this.getAllData (allData);

        if (allData.isEmpty ())
            return null;
        if (allData.get(0).isEmpty ())
            return null;

        // figure out the number of row and cols
        for (boolean constant : allConstants)
            if (!constant)
                columns++;

        rows = allData.size ();

        dataTable = new Param[rows][columns];

        // add data to the table
        for (int row=0; row<rows; row++)
        {
            List<Param> dataRow = allData.get(row);
            col = 0;
            for (int ii=0; ii<allConstants.size (); ii++)
            {
                if (!allConstants.get(ii))
                {
                    // this accounts for the row missing columns
                	if (dataRow.size () > ii)
                        dataTable[row][col++] = dataRow.get(ii);
                	else
                		dataTable[row][col++] = null;
                }
            }
        }

        return dataTable;
    }

    public void getDataColumns (List<Field> fields)
    {
        List<Boolean> allConstants = new ArrayList <Boolean>();
        int fieldIndex = 0;

        this.getAllConstants (allConstants);
        this.getAllFields (fields);

        if (fields.isEmpty ())
            return;

        // remove any fields that are constants
        for (int ii=0; ii<allConstants.size (); ii++)
        {
            // if there's only one row then it's not considered
            // a constant
            if (allConstants.get(ii) && this.data.size () != 1)
                fields.remove (fieldIndex);
            else
            	fieldIndex++;
        }

    }

    private void getAllConstants (List <Boolean> allConstants)
    {
        for (boolean cc : this.constants)
        {
        	// if there's only one row then then the row is
        	// considered not to be constant
        	if (this.data.size () == 1)
        		allConstants.add(false);
        	else
                allConstants.add (cc);
        }
   
        for(VOTablePointGroup subgroup : this.subgroups)
            subgroup.getAllConstants (allConstants);
    }

    private void getAllData (List <List<Param>> allData)
    {
    	if (allData.isEmpty ())
    	{
            for (int row=0; row<this.data.size (); row++)
        	    allData.add (new Vector<Param> ());
    	}
        
        for (int row=0; row<allData.size (); row++)
        {
        	if (row == this.data.size ())
        		break;
        	
        	List<Param> currentRow = allData.get(row);
        	List<Param> dataRow = this.data.get(row);
                currentRow.addAll (dataRow);
                // use the field ids to verify that every column is the same length
                for (int ii=dataRow.size (); ii<this.fieldIds.size (); ii++)
                    currentRow.add (null);
        }

        for(VOTablePointGroup subgroup : this.subgroups)
            subgroup.getAllData (allData);
    }

    private void getAllFields (List<Field> allFields)
    {
        for (String ii : this.fieldIds)
            allFields.add (this.fields.get(ii));

        for(VOTablePointGroup subgroup : this.subgroups)
            subgroup.getAllFields (allFields);
    }


}

