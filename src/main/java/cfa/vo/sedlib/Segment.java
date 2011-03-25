package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cfa.vo.sedlib.common.SedConstants;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedNullException;
import cfa.vo.sedlib.common.Utypes;
import java.util.Arrays;
import java.util.logging.Level;

/**
 * This class describes a spectral segment. It provides accessors to
 * components of the segments. It also contains methods to access
 * and manipulate the data directly.
 *
* Notice that this implementation doesn't support FlatPoints. If FlatPoints are detected
 * an (unchecked) UnsupportedOperationException will be thrown.
 * 
 */
public class Segment
    extends Group
{
    static final Logger logger = Logger.getLogger ("cfa.vo.sedlib");
	
    protected Target target;
    protected Characterization _char;
    protected CoordSys coordSys;
    protected Curation curation;
    protected DataID dataID;
    protected DerivedData derived;
    protected TextParam type;
    protected TextParam timeSI;
    protected TextParam spectralSI;
    protected TextParam fluxSI;
    protected ArrayOfGenPoint data;


    /**
     * Gets the value of the target property.
     * 
     * @return
     *     either null or
     *     {@link Target }
     *     
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Creates target property if one does not exist.
     *
     * @return
     *     {@link Target }
     *
     */
    public Target createTarget() {
        if (this.target == null)
           this.setTarget (new Target ());
        return this.target;
    }


    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link Target }
     *     
     */
    public void setTarget(Target value) {
        this.target = value;
    }

    public boolean isSetTarget() {
        return (this.target!= null);
    }

    /**
     * Gets the value of the char property.
     * 
     * @return
     *     either null or
     *     {@link Characterization }
     *     
     */
    public Characterization getChar() {
        return _char;
    }

    /**
     * Creates _char property if one does not exist.
     *
     * @return
     *     {@link Characterization }
     *
     */
    public Characterization createChar() {
        if (this._char == null)
           this.setChar (new Characterization ());
        return this._char;
    }


    /**
     * Sets the value of the char property.
     * 
     * @param value
     *     allowed object is
     *     {@link Characterization }
     *     
     */
    public void setChar(Characterization value) {
        this._char = value;
    }

    public boolean isSetChar() {
        return (this._char!= null);
    }

    /**
     * Gets the value of the coordSys property.
     * 
     * @return
     *     either null or
     *     {@link CoordSys }
     *     
     */
    public CoordSys getCoordSys() {
        return coordSys;
    }

    /**
     * Creates coordSys property if one does not exist.
     *
     * @return
     *     {@link CoordSys }
     *
     */
    public CoordSys createCoordSys() {
        if (this.coordSys == null)
           this.setCoordSys (new CoordSys ());
        return this.coordSys;
    }


    /**
     * Sets the value of the coordSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordSys }
     *     
     */
    public void setCoordSys(CoordSys value) {
        this.coordSys = value;
    }

    public boolean isSetCoordSys() {
        return (this.coordSys!= null);
    }

    /**
     * Gets the value of the curation property.
     * 
     * @return
     *     either null or
     *     {@link Curation }
     *     
     */
    public Curation getCuration() {
        return curation;
    }

    /**
     * Creates curation property if one does not exist.
     *
     * @return
     *     {@link Curation }
     *
     */
    public Curation createCuration() {
        if (this.curation == null)
           this.setCuration (new Curation ());
        return this.curation;
    }

    /**
     * Sets the value of the curation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Curation }
     *     
     */
    public void setCuration(Curation value) {
        this.curation = value;
    }

    public boolean isSetCuration() {
        return (this.curation!= null);
    }

    /**
     * Gets the value of the dataID property.
     * 
     * @return
     *     either null or
     *     {@link DataID }
     *     
     */
    public DataID getDataID() {
        return dataID;
    }

    /**
     * Creates dataID property if one does not exist.
     *
     * @return
     *     {@link DataID }
     *
     */
    public DataID createDataID() {
        if (this.dataID == null)
           this.setDataID (new DataID ());
        return this.dataID;
    }


    /**
     * Sets the value of the dataID property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataID }
     *     
     */
    public void setDataID(DataID value) {
        this.dataID = value;
    }

    public boolean isSetDataID() {
        return (this.dataID!= null);
    }

    /**
     * Gets the value of the derived property.
     * 
     * @return
     *     either null or
     *     {@link DerivedData }
     *     
     */
    public DerivedData getDerived() {
        return derived;
    }

    /**
     * Creates derived property if one does not exist.
     *
     * @return
     *     {@link DerivedData }
     *
     */
    public DerivedData createDerived() {
        if (this.derived == null)
           this.setDerived (new DerivedData ());
        return this.derived;
    }


    /**
     * Sets the value of the derived property.
     * 
     * @param value
     *     allowed object is
     *     {@link DerivedData }
     *     
     */
    public void setDerived(DerivedData value) {
        this.derived = value;
    }

    public boolean isSetDerived() {
        return (this.derived!= null);
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getType() {
        return type;
    }

    /**
     * Creates type property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createType() {
        if (this.type == null)
           this.setType (new TextParam ());
        return this.type;
    }


    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setType(TextParam value) {
        this.type = value;
    }

    public boolean isSetType() {
        return (this.type!= null);
    }

    /**
     * Gets the length of the point list.
     * 
     * @return int
     *     
     */
    public int getLength() {
        return data.getLength ();
    }

    /**
     * Gets the value of the timeSI property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getTimeSI() {
        return timeSI;
    }

    /**
     * Creates timeSI property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createTimeSI() {
        if (this.timeSI == null)
           this.setTimeSI (new TextParam ());
        return this.timeSI;
    }


    /**
     * Sets the value of the timeSI property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setTimeSI(TextParam value) {
        this.timeSI = value;
    }

    public boolean isSetTimeSI() {
        return (this.timeSI!= null);
    }

    /**
     * Gets the value of the spectralSI property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getSpectralSI() {
        return spectralSI;
    }

    /**
     * Creates spectralSI property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createSpectralSI() {
        if (this.spectralSI == null)
           this.setSpectralSI (new TextParam ());
        return this.spectralSI;
    }


    /**
     * Sets the value of the spectralSI property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setSpectralSI(TextParam value) {
        this.spectralSI = value;
    }

    public boolean isSetSpectralSI() {
        return (this.spectralSI!= null);
    }

    /**
     * Gets the value of the fluxSI property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getFluxSI() {
        return fluxSI;
    }

    /**
     * Creates fluxSI property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createFluxSI() {
        if (this.fluxSI == null)
           this.setFluxSI (new TextParam ());
        return this.fluxSI;
    }


    /**
     * Sets the value of the fluxSI property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setFluxSI(TextParam value) {
        this.fluxSI = value;
    }

    public boolean isSetFluxSI() {
        return (this.fluxSI!= null);
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     {@link ArrayOfGenPoint }
     *     
     */
    public ArrayOfGenPoint getData() {
        return this.data;
    }

    /**
     * Creates data property if one does not exist.
     * The data property will be an ArrayOfPoint. Use
     * setData to set the list to ArrayOfFlatPoint
     *
     * @return
     *     {@link ArrayOfPoint }
     *
     */
    public ArrayOfGenPoint createData() {
        if (this.data == null)
           this.setData (new ArrayOfPoint ());
        return this.data;
    }


    boolean isPoint()
    {
        return (this.data instanceof ArrayOfPoint);
    }


    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPoint }
     *     {@link ArrayOfFlatPoint }
     *     
     */
    public void setData(ArrayOfGenPoint value) {
        this.data = value;
    }

    public boolean isSetData() {
        return (this.data!= null);
    }

 
    /**
     * Gets the value of the dataModel property.
     * 
     * @return
     *     {@link String }
     *     
     */   
    public String getDataModel () {
        return SedConstants.DATAMODEL;
    }

    
    /**
     * Sets the values of the spectral axis. The first n values of the
     * are set in the spectral axis. If the array is larger then the
     * number of points, then the extra values are ignored.
     *
     * @param values
     *   double[]
     *
     */
    public void setSpectralAxisValues (double values[])
    {
        try { //This statement can't actually throw the exception, since it passes a supported utype
            this.setDataValues (values, Utypes.SEG_DATA_SPECTRALAXIS_VALUE);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the values of the spectral axis. If no data exists then
     * SedNoDataExeption is thrown. For points where the spectral axis is not set
     * a NaN value is used.
     *
     * @return 
     *    double[]
     *
     * @throws SedNoDataException
     *
     */
    public double[] getSpectralAxisValues () throws SedNoDataException
    {

        try {//This statement can't actually throw the exception, since it passes a supported utype
            double[] values = null;
            values = (double[]) this.getDataValues (Utypes.SEG_DATA_SPECTRALAXIS_VALUE);
            return values;
        } catch (SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
            return new double[0];
        }
    }

    /**
     * Sets the values of the flux axis. The first n values of the array
     * are set in the flux axis. If the array is larger then the
     * number of points, the extra values are ignored.
     *
     * @param values
     *   double[]
     *
     * @throws SedNoDataException
     *
     */
    public void setFluxAxisValues (double values[])
    {
        try { //This statement can't actually throw the exception, since it is passing double[]
            this.setDataValues(values, Utypes.SEG_DATA_FLUXAXIS_VALUE);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the values of the flux axis. If no data exists then
     * SedNoDataException is thrown. For points where the flux axis is not set
     * a NaN value is used.
     *
     * @return 
     *   either double[]
     *
     * @throws SedNoDataException
     *
     */
    public double[] getFluxAxisValues () throws SedNoDataException
    {
        double []values = null;

        try
        {
            values = (double [])this.getDataValues (Utypes.SEG_DATA_FLUXAXIS_VALUE);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }


        return values;
    }


    /**
     * Sets the units of the spectral axis. If there are no data
     * a SedNoDataException is thrown.
     *
     * @param units
     *    {@link String}
     *
     * @throws SetNoDataException
     *
     */
    public void setSpectralAxisUnits (String units) throws SedNoDataException
    {
        try { //This code can't actually throw the exception, since we are using a supported utype

            Field field = null;
            field = this.getDataInfo (Utypes.SEG_DATA_SPECTRALAXIS_VALUE);
            if (field == null)
                    field = new Field ();
            field.setUnit (units);
            this.setDataInfo (field, Utypes.SEG_DATA_SPECTRALAXIS_VALUE);

            if (data instanceof ArrayOfFlatPoint)
                throw new UnsupportedOperationException("FlatPoints are not supported by this version.");
        } catch(SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Gets the units of the spectral axis. If no data exists then
     * SedNoDataException is thrown
     *
     * @return
     *    {@link String}
     *
     * @throws SedNoDataException
     */
    public String getSpectralAxisUnits () throws SedNoDataException
    {
        try {//This code can't actually throw the exception, since we are using a supported utype
            Field field = null;

            field = this.getDataInfo (Utypes.SEG_DATA_SPECTRALAXIS_VALUE);
        
            if (data instanceof ArrayOfFlatPoint)
                throw new UnsupportedOperationException("FlatPoints are not supported by this version.");

            field = this.getDataInfo (Utypes.SEG_DATA_SPECTRALAXIS_VALUE);

            if (field == null)
                throw new SedNoDataException("No Spectral Axis found.");
        
            return field.getUnit ();
        } catch(SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }


    /**
     * Sets the units of the flux axis.
     *
     * If there is no data on the flux axis a SedNoDataException is thrown
     *
     * @param units
     *    {@link String}
     *
     * @throws SedNoDataException
     */
    public void setFluxAxisUnits (String units) throws SedNoDataException
    {
        try {//This code can't actually throw the exception, since we are using a supported utype

            Field field = null;

            field = this.getDataInfo (Utypes.SEG_DATA_FLUXAXIS_VALUE);
            if (field == null)
                    field = new Field ();
            field.setUnit (units);
            this.setDataInfo (field, Utypes.SEG_DATA_FLUXAXIS_VALUE);

            if (data instanceof ArrayOfFlatPoint)
                throw new UnsupportedOperationException("FlatPoints are not supported by this version.");

        } catch (SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the units of the flux axis. If no data exists then
     * a SedNoDataException is thrown.
     *
     * @return
     *    {@link String}
     *
     * @throws SedNoDataException
     *
     */
    public String getFluxAxisUnits () throws SedNoDataException
    {
        try {//This code can't actually throw the exception, since we are using a supported utype

            Field field = null;

            field = this.getDataInfo (Utypes.SEG_DATA_FLUXAXIS_VALUE);

            if (data instanceof ArrayOfFlatPoint)
                throw new UnsupportedOperationException("FlatPoints are not supported by this version.");

            return field.getUnit ();

        } catch(SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }


    /**
     * Gets the values of the specified utype. If no data exists then
     * SedNoDataException is thrown. For points where the axis is not set
     * a default value is used; for doubles its NaN for ints its -9999.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
     *
     * If no data are found a SedNoDataException is thrown.
     *
     *
     * If the utype is not supported a SedInconsistentException is thrown.
     *
     * If no data are found a SedNoDataException is thrown.
     *
     * @param utype
     *   {@link String}
     *
     * @return
     *   either double[] or int[] depending on the data. The
     *   arrays will be cast to an Object.
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    public Object getDataValues (String utype) throws SedNoDataException, SedInconsistentException
    {

        if (this.data instanceof ArrayOfFlatPoint)
        {
            throw new UnsupportedOperationException("FlatPoints are not supported in this version");
        }


        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (pointData == null)
            throw new SedNoDataException("no data were found");

        return pointData.getDataValues (utype);
    }

    /**
     * Sets the values of the custom data id. The first n values of the
     * are set in the spectral axis. If the array is larger then the
     * number of points, then the extra values are ignored.
     *
     * @param values
     *   Valid values are double[], float[], int[], short[], long[] or String[]
     *
     * @throws SedNoDataException, SedInconsistentException, SedNullException
     *
     */
    public void setCustomDataValues (String id, Object values)  
        throws SedNoDataException, SedInconsistentException, SedNullException
    {

        List<Point> points;
        int ivalues[] = null;
        String svalues[] = null;
        double dvalues[] = null;
        float fvalues[] = null;
        short shvalues[] = null;
        long lvalues[] = null;

        int length = 0;


        if (this.data instanceof ArrayOfFlatPoint)
        {
            throw new UnsupportedOperationException("FlatPoints are not supported in this version");
        }

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (!this.isSetData () || !pointData.isSetPoint ())
            throw new SedNoDataException("no data were found");

        points = pointData.getPoint ();

        if (values instanceof double[])
        {
            dvalues = (double[])values;
            length = dvalues.length;
        }
        else if (values instanceof float[])
        {
            fvalues = (float[])values;
            length = fvalues.length;
        }
        else if (values instanceof int[])
        {
            ivalues = (int [])values;
            length = ivalues.length;
        }
        else if (values instanceof short[])
        {
            shvalues = (short [])values;
            length = shvalues.length;
        }
        else if (values instanceof long[])
        {
            lvalues = (long [])values;
            length = lvalues.length;
        }

        else if(values instanceof String[])
        {
            svalues = (String [])values;
            length = svalues.length;
        }
        else
            throw new SedInconsistentException ("Unknown input data type. The input is expected to be an int, short, long, float, double or String array");

        for (int ii=0; ii<length; ii++)
        {
            Point point;
            Param param;
            if (points.size () == ii)
            {
                point = new Point ();
                points.add(point);
            }
            else
                point  = points.get(ii);

            try
            {
                param = point.findCustomParam (id);
            }
            catch (SedNoDataException exp)
            {
            	continue; // could not find a parameter for this point
            }

            // if it's an integer
            if (ivalues != null)
                param.setValue (Integer.toString (ivalues[ii]));
            // if it's a double
            else if (dvalues != null)
                param.setValue (Double.toString (dvalues[ii]));
            // if it's a float 
            else if (fvalues != null)
                param.setValue (Float.toString (fvalues[ii]));
            // if it's a short 
            else if (shvalues != null)
                param.setValue (Short.toString (shvalues[ii]));
            // if it's a long
            else if (lvalues != null)
                param.setValue (Long.toString (lvalues[ii]));
            else
                param.setValue (svalues[ii]);
        }
    }

    /**
     * Sets the parameter info of the specified custom parameters. The field id
     * is used to determine which parameter should be changed. The specified field
     * will replace all information on each point except for the value. If no data
     * exists for the specified id on a particular point the point will be ignored.
     * Creates new data custom data parameters to each point for a given field id.
     * If the point already has a data parameter with the given id, parameter
     * information will be replaced by the new information.
     *
     * @param field
     *   {@link Field}
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    public void setCustomDataInfo (Field field) throws SedNoDataException, SedNullException
    {
        if (this.data instanceof ArrayOfFlatPoint)
        {
            throw new UnsupportedOperationException("FlatPoints are not supported in this version");
        }

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;
        List<Point> points;
        String id = field.getId ();

        if (!this.isSetData () || !pointData.isSetPoint ())
            throw new SedNoDataException("no data were found");

        points = pointData.getPoint ();

        // set up the values to the correct primitive
        for (Point point : points)
        {
            Param param;
            try
            {
                param = point.findCustomParam (id);
            }
            catch (SedNoDataException exp)
            {
                continue;
            }

            param.header = new Field (field);
        }

    }


    /**
     * Creates new data custom data parameters to each point for a given field.
     * The field id is used to determine which parameter should be added/changed
     * If the point already has a data parameter with the given id, parameter
     * information will be replaced by the new information.

     * @param field
     *   {@link Field}
     * @param values
     *   {@link Object}
     *
     * @throws SedException, SedNoDataException, SedNullException, SedInconsistentException
     *
     */
    public void addCustomData (Field field, Object values) 
        throws SedException, SedNoDataException, SedNullException, SedInconsistentException
    {
        if (this.data instanceof ArrayOfFlatPoint)
        {
            throw new UnsupportedOperationException("FlatPoints are not supported in this version");
        }

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;
        List<Point> points;
        int castType;  // type of the object

        if (!this.isSetData () || !pointData.isSetPoint ())
            throw new SedNoDataException("no data were found");

        points = pointData.getPoint ();

        // determine the cast type
        if ((values instanceof double[]) || (values instanceof float[]))
            castType = 0;
        else if ((values instanceof int[]) || 
                 (values instanceof short[]) || 
                 (values instanceof long[]))
            castType = 1;
        else if(values instanceof String[])
            castType = 2;
        else
            throw new SedInconsistentException ("Unknown input data type. The input is expected to be an int, short, long, float, double or String array");


        // set up the values to the correct primitive
        for (Point point : points)
        {
            Param param = null;
            if (castType == 0) // double
                param = new DoubleParam ();
            else if (castType == 1) // int
                param = new IntParam ();
            else   // string
                param = new TextParam ();

            param.header = new Field (field);

            try
            {
                // try to add the parameter 
                point.addCustomParam (param);
            }
            catch (SedInconsistentException exp)
            {
                // if that failed try to find it and change it
                param = point.findCustomParam (param.getId ());
                param.header = new Field (field);
            }
        }

        this.setCustomDataValues (field.getId (), values);
    }





    /**
     * Gets the values of the specified custom data id. If no data exists
     * a SedException will be thrown. For points where the axis is not set
     * a default value is used; for doubles its NaN for ints its -9999 and
     * empty strings for strings.
     * @param id
     *   {@link String}
     *
     * @return 
     *   either null or double[] or int[] depending on the data. The
     *   arrays will be cast to an Object.
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    public Object getCustomDataValues (String id) 
        throws SedNoDataException, SedNullException
    {

        Object values = null;
        List<Point> points;
        int ivalues[];
        String svalues[];
        double dvalues[];


        if (this.data instanceof ArrayOfFlatPoint)
        {
            throw new UnsupportedOperationException("FlatPoints are not supported in this version");
        }

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (!this.isSetData () || !pointData.isSetPoint ())
            throw new SedNoDataException("no data were found");


        points = pointData.getPoint ();

        ivalues = new int[points.size ()];
        svalues = new String[points.size ()];
        dvalues = new double[points.size ()];

        // set up the values to the correct primitive
        for (Point point : points)
        {
            Param param = null;
            try
            {
                param = point.findCustomParam (id);
            }
            catch (SedNoDataException exp)
            {
                continue;
            }

            if (param instanceof DoubleParam)
            {
                dvalues = new double[points.size ()];
                values = dvalues;
            }
            else if (param instanceof IntParam)
            {
                ivalues = new int[points.size ()];
                values = ivalues;
            }
            else
            {
                svalues = new String[points.size ()];
                values = svalues;
            }

            // all parameters should be of the same type
            break;
        }

        // go through all the point and create a primitive array
        for (int ii=0; ii<points.size (); ii++)
        {
            Param param = null;
            try
            {
                param = points.get(ii).findCustomParam (id);
            }
            catch (SedNoDataException exp)
            {
//                continue;
            }


            if (values == dvalues)
            {
                if (param == null)
                    dvalues[ii] = SedConstants.DEFAULT_DOUBLE;
                else
                    dvalues[ii] = (Double)param.getCastValue ();
            }
            else if (values == ivalues)
            {
                if (param == null)
                    ivalues[ii] = SedConstants.DEFAULT_INTEGER;
                else
                    ivalues[ii] = (Integer)param.getCastValue ();
            }
            else
            {
                if (param == null)
                    svalues[ii] = SedConstants.DEFAULT_STRING;
                else
                    svalues[ii] = param.getValue ();
            }
        }


        return values;
    }


    /**
     * Gets the parameter info of the specified utype. 
     * @param utype
     *   int
     *
     * @return
     *   either {@link Field}
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    public Field getCustomDataInfo (String id) throws SedNoDataException, SedNullException
    {

        Field dataInfo = null;

        if (this.data instanceof ArrayOfFlatPoint)
        {
            throw new UnsupportedOperationException("FlatPoints are not supported in this version");
        }

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;
        List<Point> points;

        if (!this.isSetData () || !pointData.isSetPoint ())
            throw new SedNoDataException("no data were found");

        points = pointData.getPoint ();

        for (int ii=0; ii<points.size (); ii++)
        {
            Param param;

            try
            {
                param = points.get(ii).findCustomParam (id);
            }
            catch (SedNoDataException exp)
            {
                continue;
            }

            dataInfo = new Field (param.header);
            break;
        }

        return dataInfo;

    }



    /**
     * Gets the values of the specified utype. If no data exists then
     * SedNoDataException is thrown. For points where the axis is not set
     * a default value is used; for doubles its NaN for ints its -9999.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
     *
     * If no data are found a SedNoDataException is thrown.
     *
     *
     * If the utype is not supported a SedInconsistentException is thrown.
     *
     * @param utype
     *   int
     *
     * @return
     *   either double[] or int[] depending on the data. The
     *   arrays will be cast to an Object.
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    public Object getDataValues (int utype) throws SedNoDataException, SedInconsistentException
    {
        if (this.data instanceof ArrayOfFlatPoint)
            throw new UnsupportedOperationException("FlatPoints are not supported in this version");

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (pointData == null)
            throw new SedNoDataException("no data were found");

        return pointData.getDataValues (utype);
    }


    /**
     * Sets the values of the specified axis. If more values exist then points,
     * new points will be created to accomodate the extra values.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
     *
     * @param values
     *   either a double[] or int[] depending on the data.
     * @param utype
     *   {@link String}
     *
     * @throws SedNoDataException, SedInconsistentException 
     *
     */
    public void setDataValues (Object values, String utype) throws SedInconsistentException
    {
        if (this.data instanceof ArrayOfFlatPoint)
            throw new UnsupportedOperationException("FlatPoints are not supported by this version.");

        ArrayOfPoint pointData = (ArrayOfPoint)this.createData ();

        pointData.setDataValues (values, utype);
    }


    /**
     * Sets the values of the specified axis. If more values exist then points,
     * new points will be created to accomodate the extra values.
     *
     * If the utype is not supported a SedInconsistentException is thrown
     *
     * @param values
     *   either a double[] or int[] depending on the data.
     * @param utype
     *   int
     *
     * @throws SedException
     *
     */
    public void setDataValues (Object values, int utype) throws SedInconsistentException
    {
        if (this.data instanceof ArrayOfFlatPoint)
        {
            throw new UnsupportedOperationException("FlatPoints are not supported by this version.");
        }

        ArrayOfPoint pointData = (ArrayOfPoint)this.createData ();

        pointData.setDataValues(values, utype);
    }


    /**
     * Gets the parameter info of the specified axis. If no data exists then
     * SedNoDataException is thrown.
     * @param utype
     *   {@link String}
     *
     * @return
     *   {@link Field}
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    public Field getDataInfo (String utype) throws SedNoDataException, SedInconsistentException
    {
        if (this.data instanceof ArrayOfFlatPoint)
            throw new UnsupportedOperationException("FlatPoints are not supported by this version.");

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (pointData == null)
            throw new SedNoDataException("No pointData found.");

        return pointData.getDataInfo (utype);
    }


    /**
     * Gets the parameter info of the specified axis. If no data exists then
     * SedNoDataException is thrown.
     * @param utype
     *   int
     *
     * @return
     *   {@link Field}
     *
     * @throws SedInconsistentException, SedNoDataException
     *
     */
    public Field getDataInfo (int utype) throws SedInconsistentException, SedNoDataException
    {
        if (this.data instanceof ArrayOfFlatPoint)
            throw new UnsupportedOperationException("FlatPoints are not supported by this version.");

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (pointData == null)
            throw new SedNoDataException("No pointData found");

        return pointData.getDataInfo (utype);
    }


    /**
     * Sets the parameter info of the specified axis. The specfied field
     * will replace all information on each point except for the value. If no data
     * exists for the specified utype on a particular point the point will be ignored.
     *
     * If no data are found, a SedNoDataException is thrown.
     *
     * If utype is not supported, a SedInconsistentException is thrown.
     *
     * @param field
     *   {@link Field}
     * @param utype
     *   {@link String}
     *
     * @throws SedInconsistentException, SedNoDataException
     *
     */
    public void setDataInfo (Field field, String utype) throws SedInconsistentException, SedNoDataException
    {
        if (this.data instanceof ArrayOfFlatPoint)
            throw new UnsupportedOperationException("FlatPoints are not supported by this version.");

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (pointData == null)
            throw new SedNoDataException ("There is currently no data to set. Try createData () to create the PointArray.");

        pointData.setDataInfo (field, utype);
    }


    /**
     * Sets the parameter info of the specified axis. The specfied field
     * will replace all information on each point except for the value. If no data
     * exists for the specified utype on a particular point the point will be ignored.
     *
     * If there are no points to set, a SedNoDataException is thrown.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
     *
     * @param field
     *   {@link Field}
     * @param utype
     *   int
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    public void setDataInfo (Field field, int utype) throws SedNoDataException, SedInconsistentException
    {
        if (this.data instanceof ArrayOfFlatPoint)
            throw new UnsupportedOperationException("FlatPoints are not supported by this version.");

        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (pointData == null)
            throw new SedNoDataException ("There is currently no data to set. Try createData () to create the PointArray.");

        pointData.setDataInfo (field, utype);
    }


    /**
     * Gets the parameter of the specified utype. The returned param will
     * be a copy of the one in the segment. To update the segment use
     * <code>setMetaParam</code>. If no param exists then null is returned.
     * @param utype
     *   {@link String}
     *
     * @return
     *   either null or {@link Param}.
     *
     * @throws SedInconsistentException
     *
     */
    public Param getMetaParam (String utype) throws SedInconsistentException
    {
        int utypeEnum = Utypes.getUtypeFromString (utype);

        if (utypeEnum == Utypes.INVALID_UTYPE)
            throw new SedInconsistentException ("The utype, "+utype+", is not supported. There is no parameter associated with this utype.");

        return this.getMetaParam (utypeEnum);
    }


    /**
     * Gets the parameter of the specified utype. The returned param will
     * be a copy of the one in the segment. To update the segment use 
     * <code>setMetaParam</code>. If no param exists then null is returned.
     * @param utype
     *   int
     *
     * @return
     *   either null or {@link Param}. 
     *
     * @throws SedInconsistentException
     *
     */
    public Param getMetaParam (int utype) throws SedInconsistentException
    {
        Param param = this.getMetaParam (utype, false);
        Param outParam = null;

        // copy the param 
        if (param != null)
        {
            if (param instanceof SkyRegion)
                outParam = new SkyRegion ((SkyRegion)param);
            else if (param instanceof TextParam)
                outParam = new TextParam ((TextParam)param);
            else if (param instanceof DateParam)
                outParam = new DateParam ((DateParam)param);
            else if (param instanceof TimeParam)
                outParam = new TimeParam ((TimeParam)param);
            else if (param instanceof DoubleParam)
                outParam = new DoubleParam ((DoubleParam)param);
            else if (param instanceof IntParam)
                outParam = new IntParam ((IntParam)param);
            else 
                outParam = new Param (param);
        }

        return outParam;
    }

    /**
     * Gets a list of parameters of a specified utype. The returned param list
     * contains be a copies of the parameters found in the segment. To update the
     * segment use  <code>setMetaParamList</code>. If no param exists then null is
     * returned.
     * @param utype
     *   int
     *
     * @return
     *   List<? extends Param>.
     *   {@link Param}
     *
     * @throws SedInconsistentException
     *
     */
    public List <? extends Param> getMetaParamList (String utype) throws SedInconsistentException
    {
        int utypeEnum = Utypes.getUtypeFromString (utype);

        if (utypeEnum == Utypes.INVALID_UTYPE)
            throw new SedInconsistentException ("The utype, "+utype+", is not supported. There is no parameter associated with this utype.");

        return this.getMetaParamList (utypeEnum);
    }



    /**
     * Gets a list of parameters of a specified utype. The returned param list
     * contains be a copies of the parameters found in the segment. To update the
     * segment use  <code>setMetaParamList</code>. 
     *
     * @param utype
     *   int
     *
     * @return
     *   List<? extends Param>.
     *   {@link Param}
     *
     * @throws SedInconsistentException
     *
     */
    public List <? extends Param> getMetaParamList (int utype) throws SedInconsistentException
    {
        List <? extends Param> outParams = new ArrayList<Param> ();
        
        try
        {
            switch (utype)
            {
                case Utypes.SEG_DATAID_COLLECTION:  // fallthrough intended
                case Utypes.SEG_DATAID_CONTRIBUTOR:
                {
                    ArrayList<TextParam>paramList = new ArrayList<TextParam> ();
                    List<TextParam> params = null;
                    outParams = paramList;

                    if (utype == Utypes.SEG_DATAID_COLLECTION)
                        params = this.getDataID ().getCollection ();
                    else
                        params = this.getDataID ().getContributor ();

                    if (params != null)
                    {
                        paramList.ensureCapacity(params.size ());
                        for (TextParam pp : params)
                            paramList.add (new TextParam (pp));
                    }

                    outParams = paramList;

                }
                break;
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_VALUE:
                {
                    ArrayList<DoubleParam> paramList = new ArrayList<DoubleParam> ();
                    List<CharacterizationAxis> charAxis = null;
                    DoubleParam params[];
                    
                    outParams = paramList;

                    charAxis = this.getChar ().getCharacterizationAxis ();

                    if (charAxis != null)
                    {

                        if (charAxis.size () > 1)
                            throw new SedInconsistentException ("Multiple copies of the Characterization Axis found. It's ambiguous as to which param should be retrieved.");

                        if (charAxis.size () > 0)
                        {
                            params = charAxis.get (0).getCoverage ().getLocation ().getValue ();
                            paramList.ensureCapacity (params.length);
                            for (DoubleParam pp : params)
                            {
                            	if (pp != null)
                            		paramList.add (new DoubleParam (pp));
                            	else
                            		paramList.add (null);
                            }
                        }
                    }

                    outParams = paramList;

                }
                break;
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE:   // fallthrough intended
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE:  // fallthrough intended
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_VALUE:      // fallthrough intended
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_VALUE:      // fallthrough intended
                case Utypes.TARGET_POS:
                {
                    ArrayList<DoubleParam> paramList = new ArrayList<DoubleParam>();
                    DoubleParam params[];
                    
                    outParams = paramList;

                    if (utype == Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE)
                        params = this.getChar ().getSpatialAxis ().getCoverage().getLocation ().getValue ();
                    else if (utype == Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE)
                        params = this.getChar ().getSpectralAxis ().getCoverage().getLocation ().getValue ();
                    else if (utype == Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_VALUE)
                        params = this.getChar ().getFluxAxis ().getCoverage().getLocation ().getValue ();
                    else if (utype == Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_VALUE)
                        params = this.getChar ().getTimeAxis ().getCoverage().getLocation ().getValue ();
                    else
                        params = this.getTarget ().getPos ().getValue ();

                    if (params != null)
                    {
                        paramList.ensureCapacity (params.length);
                        for (DoubleParam pp : params)
                        {
                            if (pp != null)
                                paramList.add (new DoubleParam (pp));
                            else
                                paramList.add (null);
                        }
                    }

                }
                break;
                default:
                    throw new SedInconsistentException ("Cannot find a parameter associated with the specified utype, "+Utypes.getName (utype)+".");
            }
        }
        catch (NullPointerException exp)
        {
        	;// return empty list
        }

        return outParams;
    }




    /**
     * Sets the parameter to the specified utype. The parameter will
     * be an explicit copy into the segment. If the parameter didn't
     * exists in the Sed object it will be created.
     * @param utype
     *   {@link String}
     *
     * @param param
     *   {@link Param}
     *
     * @throws SedInconsistentException
     *
     */
    public void setMetaParam (Param param, String utype) throws SedInconsistentException

    {
        int utypeEnum = Utypes.getUtypeFromString (utype);

        if (utypeEnum == Utypes.INVALID_UTYPE)
            throw new SedInconsistentException ("The utype, "+utype+", is not supported. There is no parameter associated with this utype.");

        this.setMetaParam (param, utypeEnum);
    }

    /**
     * Sets the parameter to the specified utype. The parameter will
     * be an explicit copy into the segment. If the parameter didn't
     * exists in the Sed object it will be created.
     * @param utype
     *   int
     *
     * @param param
     *   {@link Param}.
     *
     * @throws SedException
     *
     */
    public void setMetaParam (Param param, int utype) throws SedInconsistentException
    {
        Param sedParam = this.getMetaParam (utype, true);

        if (param == null)
            return;

        if ((sedParam instanceof DoubleParam) && (param instanceof DoubleParam))
        {
            DoubleParam dParam = (DoubleParam)sedParam;
            dParam.setUnit (((DoubleParam)param).getUnit ());
        }
        else if ((sedParam instanceof IntParam) && (param instanceof IntParam))
        {
            IntParam iParam = (IntParam)sedParam;
            iParam.setUnit (((IntParam)param).getUnit ());
        }
        
        else if ((sedParam instanceof TimeParam) && (param instanceof TimeParam))
        {
            TimeParam tParam = (TimeParam)sedParam;
            tParam.setUnit (((TimeParam)param).getUnit ());
        }
        sedParam.setName (param.getName ());
        sedParam.setUcd (param.getUcd ());
        sedParam.setValue (param.getValue ());

    }


    /**
     * Sets the parameter list of the specified utype. The parameter list will
     * be explicitly copied into the segment. If the parameter didn't
     * exists in the Sed object it will be created.
     * @param utype
     *   {@link String}
     *
     * @param params
     *   {@link Param}.
     *
     * @throws SedInconsistentException
     *
     */
    public void setMetaParamList (List<? extends Param> params, String utype)
         throws SedInconsistentException

    {
        int utypeEnum = Utypes.getUtypeFromString (utype);

        if (utypeEnum == Utypes.INVALID_UTYPE)
            throw new SedInconsistentException ("The utype, "+utype+", is not supported. There is no parameter associated with this utype.");

        this.setMetaParamList (params, utypeEnum);
    }



    /**
     * Sets the parameter list of the specified utype. The parameter list will
     * be explicitly copied into the segment. If the parameter didn't
     * exists in the Sed object it will be created.
     * @param utype
     *   int
     *
     * @param params
     *   {@link Param}.
     *
     * @throws SedInconsistentException
     *
     */
    public void setMetaParamList (List<? extends Param> params, int utype)
         throws SedInconsistentException
    {
        switch (utype)
        {
            case Utypes.SEG_DATAID_COLLECTION:  // fallthrough intended
            case Utypes.SEG_DATAID_CONTRIBUTOR:
            {
                List<TextParam> paramList = null;

                if (utype == Utypes.SEG_DATAID_COLLECTION)
                    paramList = this.createDataID ().createCollection ();
                else
                    paramList = this.createDataID ().createContributor ();

                for (Param pp : params)
                    paramList.add (new TextParam ((TextParam)pp));

            }
            break;
            case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_VALUE:
            {
                List<CharacterizationAxis> charAxis = null;
                DoubleParam paramArray[];

                charAxis = this.createChar ().createCharacterizationAxis ();


                if (charAxis.size () > 1)
                    throw new SedInconsistentException ("Multiple copies of the Characterization Axis found. It's ambiguous as to which param should be retrieved.");

                paramArray = charAxis.get (0).createCoverage ().createLocation ().createValue ();
                for (int ii=0; ii<params.size (); ii++)
                {
                    if (ii == paramArray.length)
                        break;

                    DoubleParam pp = (DoubleParam)params.get(ii);
                    if (pp != null)
                    	paramArray[ii] = new DoubleParam (pp);
                    else
                    	paramArray[ii] = null;
                }

            }
            break;
            case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE:   // fallthrough intended
            case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE:  // fallthrough intended
            case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_VALUE:      // fallthrough intended
            case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_VALUE:      // fallthrough intended
            case Utypes.TARGET_POS:
            {
                DoubleParam paramArray[];

                if (utype == Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE)
                    paramArray = this.createChar ().createSpatialAxis ().createCoverage ().createLocation ().createValue ();
                else if (utype == Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE)
                    paramArray = this.createChar ().createSpectralAxis ().createCoverage ().createLocation ().createValue ();
                else if (utype == Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_VALUE)
                    paramArray = this.createChar ().createFluxAxis ().createCoverage ().createLocation ().createValue ();
                else if (utype == Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_VALUE)
                    paramArray = this.createChar ().createTimeAxis ().createCoverage ().createLocation ().createValue ();
                else
                    paramArray = this.createTarget().createPos ().createValue ();

                for (int ii=0; ii < params.size (); ii++)
                {
                    if (ii == paramArray.length)
                        break;

                    DoubleParam pp = (DoubleParam)params.get(ii);

                    if (pp != null)
                        paramArray[ii] = new DoubleParam (pp);
                    else
                        paramArray[ii] = null;
                }
            }
            break;
            default:
                throw new SedInconsistentException ("Cannot find a parameter associated with the specified utype, "+Utypes.getName (utype)+".");
        }

    }


    /**
     * Get the Param associated specified utype. If the parameter does not
     * exists null is returned
     */
    Param getMetaParam (int utype, boolean create ) throws SedInconsistentException
    {
        Param param = null;

        try
        {
            switch (utype)
            {
                case Utypes.TYPE:
                    if (create)
                        param = this.createType ();
                    else
                        param = this.getType ();
                break;
                case Utypes.TIMESI:
                    if (create)
                        param = this.createTimeSI ();
                    else
                        param = this.getTimeSI ();
                break;
                case Utypes.SPECTRALSI:
                    if (create)
                        param = this.createSpectralSI ();
                    else
                        param = this.getSpectralSI ();
                break;
                case Utypes.FLUXSI:
                    if (create)
                        param = this.createFluxSI ();
                    else
                        param = this.getFluxSI ();
                break;
                case Utypes.TARGET_NAME:
                    if (create)
                        param = this.createTarget ().createName ();
                    else
                        param = this.getTarget ().getName ();
                break;
                case Utypes.TARGET_DESCRIPTION:
                    if (create)
                        param = this.createTarget ().createDescription ();
                    else
                        param = this.getTarget ().getDescription ();
                break;
                case Utypes.TARGET_CLASS:
                    if (create)
                        param = this.createTarget ().createTargetClass ();
                    else
                        param = this.getTarget ().getTargetClass ();
                break;
                case Utypes.TARGET_SPECTRALCLASS:
                    if (create)
                        param = this.createTarget ().createSpectralClass ();
                    else
                        param = this.getTarget ().getSpectralClass ();
                break;
                case Utypes.TARGET_REDSHIFT:
                    if (create)
                        param = this.createTarget ().createRedshift ();
                    else
                        param = this.getTarget ().getRedshift ();
                break;
                case Utypes.TARGET_VARAMPL:
                    if (create)
                        param = this.createTarget ().createVarAmpl ();
                    else
                        param = this.getTarget ().getVarAmpl ();
                break;
                case Utypes.SEG_CS_SPACEFRAME_EQUINOX:
                {
                    List <CoordFrame> coordFrames;
                    if (create)
                        coordFrames = this.createCoordSys ().createCoordFrame ();
                    else
                        coordFrames = this.getCoordSys ().getCoordFrame ();

                    if (coordFrames != null)
                    {
                        SpaceFrame spaceFrame = null;
                        
                        for (CoordFrame coordFrame : coordFrames)
                        {
                            if (coordFrame instanceof SpaceFrame)
                            {
                                if (spaceFrame != null)
                                    throw new SedInconsistentException ("Multiple copies of the spaceframe found. It's ambiguous as to which param should be retrieved.");
                                else
                                    spaceFrame = (SpaceFrame)coordFrame;
                            }
                        }
                        if (create)
                        {
                            if (spaceFrame == null)
                            {
                                spaceFrame = new SpaceFrame ();
                                coordFrames.add (spaceFrame);
                            }
                            param = spaceFrame.createEquinox ();
                        }
                        else if (spaceFrame != null)
                            param = spaceFrame.getEquinox ();
                    }
                }
                break;
                case Utypes.SEG_CS_TIMEFRAME_ZERO:
                {
                    List <CoordFrame> coordFrames;
                    if (create)
                        coordFrames = this.createCoordSys ().createCoordFrame ();
                    else
                        coordFrames = this.getCoordSys ().getCoordFrame ();

                    if (coordFrames != null)
                    {
                        TimeFrame timeFrame = null;

                        for (CoordFrame coordFrame : coordFrames)
                        {
                            if (coordFrame instanceof TimeFrame)
                            {
                                if (timeFrame != null)
                                    throw new SedInconsistentException ("Multiple copies of the spaceframe found. It's ambiguous as to which param should be retrieved.");
                                else
                                    timeFrame = (TimeFrame)coordFrame;
                            }
                        }
                        if (create)
                        {
                            if (timeFrame == null)
                            {
                                timeFrame = new TimeFrame ();
                                coordFrames.add (timeFrame);
                            }
                            param = timeFrame.createZero ();
                        }
                        else if (timeFrame != null)
                            param = timeFrame.getZero ();
                    }
                }
                break;
                case Utypes.SEG_CS_SPECTRALFRAME_REDSHIFT:
                {
                    List <CoordFrame> coordFrames;
                    if (create)
                        coordFrames = this.createCoordSys ().createCoordFrame ();
                    else
                        coordFrames = this.getCoordSys ().getCoordFrame ();

                    if (coordFrames != null)
                    {
                        SpectralFrame spectralFrame = null;

                        for (CoordFrame coordFrame : coordFrames)
                        {
                            if (coordFrame instanceof SpectralFrame)
                            {
                                if (spectralFrame != null)
                                    throw new SedInconsistentException ("Multiple copies of the spaceframe found. It's ambiguous as to which param should be retrieved.");
                                else
                                    spectralFrame = (SpectralFrame)coordFrame;
                            }
                        }
                        if (create)
                        {
                            if (spectralFrame == null)
                            {
                                spectralFrame = new SpectralFrame ();
                                coordFrames.add (spectralFrame);
                            }
                            param = spectralFrame.createRedshift ();
                        }
                        else if (spectralFrame != null)
                            param = spectralFrame.getRedshift ();
                    }
                }
                break;
                case Utypes.SEG_CURATION_PUBLISHER:
                    if (create)
                        param = this.createCuration ().createPublisher ();
                    else
                        param = this.getCuration ().getPublisher ();
                break;
                case Utypes.SEG_CURATION_REF:
                    if (create)
                        param = this.createCuration ().createReference ();
                    else
                        param = this.getCuration ().getReference ();
                break;
                case Utypes.SEG_CURATION_PUBID:
                    if (create)
                        param = this.createCuration ().createPublisherID ();
                    else
                        param = this.getCuration ().getPublisherID ();
                break;
                case Utypes.SEG_CURATION_PUBDID:
                    if (create)
                        param = this.createCuration ().createPublisherDID ();
                    else
                        param = this.getCuration ().getPublisherDID ();
                break;
                case Utypes.SEG_CURATION_VERSION:
                    if (create)
                        param = this.createCuration ().createVersion ();
                    else
                        param = this.getCuration ().getVersion ();
                break;
                case Utypes.SEG_CURATION_CONTACT_NAME:
                    if (create)
                        param = this.createCuration ().createContact ().createName ();
                    else
                        param = this.getCuration ().getContact ().getName ();
                break;
                case Utypes.SEG_CURATION_CONTACT_EMAIL:
                    if (create)
                        param = this.createCuration ().createContact ().createEmail ();
                    else
                        param = this.getCuration ().getContact ().getEmail ();
                break;
                case Utypes.SEG_CURATION_RIGHTS:
                    if (create)
                        param = this.createCuration ().createRights ();
                    else
                        param = this.getCuration ().getRights ();
                break;
                case Utypes.SEG_CURATION_DATE:
                    if (create)
                        param = this.createCuration ().createDate ();
                    else
                        param = this.getCuration ().getDate ();
                break;
                case Utypes.SEG_DATAID_TITLE:
                    if (create)
                        param = this.createDataID ().createTitle ();
                    else
                        param = this.getDataID ().getTitle ();
                break;
                case Utypes.SEG_DATAID_CREATOR:
                    if (create)
                        param = this.createDataID ().createCreator ();
                    else
                        param = this.getDataID ().getCreator ();
                break;
                case Utypes.SEG_DATAID_DATASETID:
                    if (create)
                        param = this.createDataID ().createDatasetID ();
                    else
                        param = this.getDataID ().getDatasetID ();
                break;
                case Utypes.SEG_DATAID_CREATORDID:
                    if (create)
                        param = this.createDataID ().createCreatorDID ();
                    else
                        param = this.getDataID ().getCreatorDID ();
                break;
                case Utypes.SEG_DATAID_DATE:
                    if (create)
                        param = this.createDataID ().createDate ();
                    else
                        param = this.getDataID ().getDate ();
                break;
                case Utypes.SEG_DATAID_VERSION:
                    if (create)
                        param = this.createDataID ().createVersion ();
                    else
                        param = this.getDataID ().getVersion ();
                break;
                case Utypes.SEG_DATAID_INSTRUMENT:
                    if (create)
                        param = this.createDataID ().createInstrument ();
                    else
                        param = this.getDataID ().getInstrument ();
                break;
                case Utypes.SEG_DATAID_CREATIONTYPE:
                    if (create)
                        param = this.createDataID ().createCreationType ();
                    else
                        param = this.getDataID ().getCreationType ();
                break;
                case Utypes.SEG_DATAID_LOGO:
                    if (create)
                        param = this.createDataID ().createLogo ();
                    else
                        param = this.getDataID ().getLogo ();
                break;
                case Utypes.SEG_DATAID_DATASOURCE:
                    if (create)
                        param = this.createDataID ().createDataSource ();
                    else
                        param = this.getDataID ().getDataSource ();
                break;
                case Utypes.SEG_DATAID_BANDPASS:
                    if (create)
                        param = this.createDataID ().createBandpass ();
                    else
                        param = this.getDataID ().getBandpass ();
                break;
                case Utypes.SEG_DD_SNR:
                    if (create)
                        param = this.createDerived ().createSNR ();
                    else
                        param = this.getDerived ().getSNR ();
                break;
                case Utypes.SEG_DD_REDSHIFT_VALUE:
                    if (create)
                        param = this.createDerived ().createRedshift ().createValue ();
                    else
                        param = this.getDerived ().getRedshift ().getValue ();
                break;
                case Utypes.SEG_DD_REDSHIFT_ACC_STATERR:
                    if (create)
                        param = this.createDerived ().createRedshift ().createAccuracy ().createStatError ();
                    else
                        param = this.getDerived ().getRedshift ().getAccuracy ().getStatError ();
                break;
                case Utypes.SEG_DD_REDSHIFT_ACC_CONFIDENCE:
                    if (create)
                        param = this.createDerived ().createRedshift ().createAccuracy ().createConfidence ();
                    else
                        param = this.getDerived ().getRedshift ().getAccuracy ().getConfidence ();
                break;
                case Utypes.SEG_DD_VARAMPL:
                    if (create)
                        param = this.createDerived ().createVarAmpl ();
                    else
                        param = this.getDerived ().getVarAmpl ();
                break;
                case Utypes.SEG_DD_REDSHIFT_ACC_BINLOW:
                    if (create)
                        param = this.createDerived ().createRedshift ().createAccuracy ().createBinLow ();
                    else
                        param = this.getDerived ().getRedshift ().getAccuracy ().getBinLow ();
                break;
                case Utypes.SEG_DD_REDSHIFT_ACC_BINHIGH:
                    if (create)
                        param = this.createDerived ().createRedshift ().createAccuracy ().createBinHigh ();
                    else
                        param = this.getDerived ().getRedshift ().getAccuracy ().getBinHigh ();
                break;
                case Utypes.SEG_DD_REDSHIFT_ACC_BINSIZE:
                    if (create)
                        param = this.createDerived ().createRedshift ().createAccuracy ().createBinSize ();
                    else
                        param = this.getDerived ().getRedshift ().getAccuracy ().getBinSize ();
                break;
                case Utypes.SEG_DD_REDSHIFT_ACC_STATERRLOW:
                    if (create)
                        param = this.createDerived ().createRedshift ().createAccuracy ().createStatErrLow ();
                    else
                        param = this.getDerived ().getRedshift ().getAccuracy ().getStatErrLow ();
                break;
                case Utypes.SEG_DD_REDSHIFT_ACC_STATERRHIGH:
                    if (create)
                        param = this.createDerived ().createRedshift ().createAccuracy ().createStatErrHigh ();
                    else
                        param = this.getDerived ().getRedshift ().getAccuracy ().getStatErrHigh ();
                break;
                case Utypes.SEG_DD_REDSHIFT_ACC_SYSERR:
                    if (create)
                        param = this.createDerived ().createRedshift ().createAccuracy ().createSysError ();
                    else
                        param = this.getDerived ().getRedshift ().getAccuracy ().getSysError ();
                break;
                case Utypes.SEG_DD_REDSHIFT_QUALITY:
                    if (create)
                        param = this.createDerived ().createRedshift ().createQuality ();
                    else
                        param = this.getDerived ().getRedshift ().getQuality ();
                break;
                case Utypes.SEG_DD_REDSHIFT_RESOLUTION:
                    if (create)
                        param = this.createDerived ().createRedshift ().createResolution ();
                    else
                        param = this.getDerived ().getRedshift ().getResolution ();
                break;


                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_START:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_STOP:
                case Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_EXTENT:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_CHARAXIS_CAL:
                case Utypes.SEG_CHAR_CHARAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_CHARAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_CHARAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_RESOLUTION:
                {

                    List <CharacterizationAxis> charAxes;
                    if (create)
                        charAxes = this.createChar ().createCharacterizationAxis ();
                    else
                        charAxes = this.getChar ().getCharacterizationAxis ();

                    if (charAxes != null)
                    {

                        if (charAxes.size () > 1)
                            throw new SedInconsistentException ("Multiple copies of the Characterization Axis found. It's ambiguous as to which param should be retrieved.");

                        if (charAxes.size () == 1)
                            param = getParamFromCharAxis (charAxes.get (0), utype, create);
                    }
                }
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_FLUXAXIS_CAL:
                case Utypes.SEG_CHAR_FLUXAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_RESOLUTION:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_FLUXAXIS_COV_SUPPORT_EXTENT:
                {
                    CharacterizationAxis fluxAxis;
                    if (create)
                        fluxAxis = this.createChar ().createFluxAxis ();
                    else
                        fluxAxis = this.getChar ().getFluxAxis ();
                    if (fluxAxis != null)
                        param = getParamFromCharAxis (fluxAxis, utype, create);
                }
                break;

                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_SPECTRALAXIS_CAL:
                case Utypes.SEG_CHAR_SPECTRALAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_RESOLUTION:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_START:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_EXTENT:
                {
                    CharacterizationAxis spectralAxis;
                    if (create)
                        spectralAxis = this.createChar ().createSpectralAxis ();
                    else
                        spectralAxis = this.getChar ().getSpectralAxis ();
                    if (spectralAxis != null)
                        param = getParamFromCharAxis (spectralAxis, utype, create);
                }
                break;
                case Utypes.SEG_CHAR_SPECTRALAXIS_RESPOW:
                    if (create)
                        param = this.createChar ().createSpectralAxis ().createResPower ();
                    else
                        param = this.getChar ().getSpectralAxis ().getResPower ();
                break;

                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_SPATIALAXIS_CAL:
                case Utypes.SEG_CHAR_SPATIALAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_RESOLUTION:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_SUPPORT_EXTENT:
                {
                    CharacterizationAxis spatialAxis;
                    if (create)
                        spatialAxis = this.createChar ().createSpatialAxis ();
                    else
                        spatialAxis = this.getChar ().getSpatialAxis ();
                    if (spatialAxis != null)
                        param = getParamFromCharAxis (spatialAxis, utype, create);
                }
                break;
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_RA:
                {
                    Param []params;
                    if (create)
                        params = this.createChar ().createSpatialAxis ().createCoverage ().createLocation ().createValue ();
                    else
                        params = this.getChar ().getSpatialAxis ().getCoverage ().getLocation ().getValue ();

                    if (params != null)
                        param = params[0];
                }
                break;
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_DEC:
                {
                    Param []params;
                    if (create)
                        params = this.createChar ().createSpatialAxis ().createCoverage ().createLocation ().createValue ();
                    else
                        params = this.getChar ().getSpatialAxis ().getCoverage ().getLocation ().getValue ();

                    if (params != null)
                        param = params[1];
                }
                break;

                case Utypes.SEG_CHAR_TIMEAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_TIMEAXIS_CAL:
                case Utypes.SEG_CHAR_TIMEAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_RESOLUTION:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_START:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_STOP:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_SUPPORT_EXTENT:
                {
                    CharacterizationAxis timeAxis;
                    if (create)
                        timeAxis = this.createChar ().createTimeAxis ();
                    else
                        timeAxis = this.getChar ().getTimeAxis ();
                    if (timeAxis != null)
                        param = getParamFromCharAxis (timeAxis, utype, create);
                }
                break;
                default:
                    throw new SedInconsistentException ("Cannot find a parameter associated with the specified utype, "+Utypes.getName (utype)+".");
            }
        }
        catch (NullPointerException exp)
        {
            param = null;
        }

        return param;
    }



    /**
     * Gets the list of points from the data.
     *
     * @return 
     *    List<Point>
     *    {@link Point}
     *
     */
    protected List<Point> getPointsFromData ()
    {
        if (data == null)
            return new ArrayList<Point>();

        if (data instanceof ArrayOfFlatPoint)
            throw new UnsupportedOperationException("FlatPoints are not supported in this version");

        return ((ArrayOfPoint)data).getPoint ();
    }

    protected Param getParamFromCharAxis (CharacterizationAxis axis, int utype, boolean create)
    {
        Param param = null;
        
        try
        {
            switch (utype)
            {
                case Utypes.SEG_CHAR_FLUXAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_START:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_MIN:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_START:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_START:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_MIN:
                    if (create)
                        param = axis.createCoverage ().createBounds ().createRange ().createMin ();
                    else
                        param = axis.createCoverage ().getBounds ().getRange ().getMin ();
                break;

                case Utypes.SEG_CHAR_FLUXAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_MAX:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_STOP:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_STOP:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_MAX:
                    if (create)
                        param = axis.createCoverage ().createBounds ().createRange ().createMax ();
                    else
                        param = axis.createCoverage ().getBounds ().getRange ().getMax ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT:
                case Utypes.SEG_CHAR_CHARAXIS_COV_BOUNDS_EXTENT:
                    if (create)
                        param = axis.createCoverage ().createBounds ().createExtent ();
                    else
                        param = axis.createCoverage ().getBounds ().getExtent ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_SUPPORT_AREA:
                case Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_AREA:
                    if (create)
                        param = axis.createCoverage ().createSupport ().createArea ();
                    else
                        param = axis.createCoverage ().getSupport ().getArea ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_BINSIZE:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_BINSIZE:
                    if (create)
                        param = axis.createAccuracy ().createBinSize ();
                    else
                        param = axis.getAccuracy ().getBinSize ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_STATERR:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERR:
                    if (create)
                        param = axis.createAccuracy ().createStatError ();
                    else
                        param = axis.getAccuracy ().getStatError ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_SYSERR:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_SYSERR:
                    if (create)
                        param = axis.createAccuracy ().createSysError ();
                    else
                        param = axis.getAccuracy ().getSysError ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_CAL:
                case Utypes.SEG_CHAR_SPATIALAXIS_CAL:
                case Utypes.SEG_CHAR_SPECTRALAXIS_CAL:
                case Utypes.SEG_CHAR_TIMEAXIS_CAL:
                case Utypes.SEG_CHAR_CHARAXIS_CAL:
                    if (create)
                        param = axis.createCalibration ();
                    else
                        param = axis.getCalibration ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_SPECTRALAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_SPATIALAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_TIMEAXIS_RESOLUTION:
                case Utypes.SEG_CHAR_CHARAXIS_RESOLUTION:
                    if (create)
                        param = axis.createResolution ();
                    else
                        param = axis.getResolution ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPEXT:
                case Utypes.SEG_CHAR_CHARAXIS_SAMPPREC_SAMPEXT:
                    if (create)
                        param = axis.createSamplingPrecision ().createSampleExtent ();
                    else
                        param = axis.getSamplingPrecision ().getSampleExtent ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_BINLOW:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_BINLOW:
                    if (create)
                        param = axis.createAccuracy ().createBinLow ();
                    else
                        param = axis.getAccuracy ().getBinLow ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_BINHIGH:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_BINHIGH:
                    if (create)
                        param = axis.createAccuracy ().createBinHigh ();
                    else
                        param = axis.getAccuracy ().getBinHigh ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERRLOW:
                    if (create)
                        param = axis.createAccuracy ().createStatErrLow ();
                    else
                        param = axis.getAccuracy ().getStatErrLow ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_STATERRHIGH:
                    if (create)
                        param = axis.createAccuracy ().createStatErrHigh ();
                    else
                        param = axis.getAccuracy ().getStatErrHigh ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_SPECTRALAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_SPATIALAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_TIMEAXIS_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_CHARAXIS_ACC_CONFIDENCE:
                    if (create)
                        param = axis.createAccuracy ().createConfidence ();
                    else
                        param = axis.getAccuracy ().getConfidence ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                case Utypes.SEG_CHAR_CHARAXIS_SAMPPREC_SAMPPRECREFVAL_FILL:
                    if (create)
                        param = axis.createSamplingPrecision ().createSamplingPrecisionRefVal ().createFillFactor ();
                    else
                        param = axis.getSamplingPrecision ().getSamplingPrecisionRefVal ().getFillFactor ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_SUPPORT_EXTENT:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_EXTENT:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_SUPPORT_EXTENT:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_SUPPORT_EXTENT:
                case Utypes.SEG_CHAR_CHARAXIS_COV_SUPPORT_EXTENT:
                    if (create)
                        param = axis.createCoverage ().createSupport ().createExtent ();
                    else
                        param = axis.getCoverage ().getSupport ().getExtent ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_SYSERR:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_SYSERR:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createAccuracy ().createSysError ();
                    else
                        param = axis.getCoverage ().getLocation ().getAccuracy ().getSysError ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_CONFIDENCE:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_CONFIDENCE:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createAccuracy ().createConfidence ();
                    else
                        param = axis.getCoverage ().getLocation ().getAccuracy ().getConfidence ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINSIZE:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINSIZE:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createAccuracy ().createBinSize ();
                    else
                        param = axis.getCoverage ().getLocation ().getAccuracy ().getBinSize ();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINLOW:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINLOW:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createAccuracy ().createBinLow ();
                    else
                        param = axis.getCoverage ().getLocation ().getAccuracy ().getBinLow();
                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINHIGH:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINHIGH:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createAccuracy ().createBinHigh ();
                    else
                        param = axis.getCoverage ().getLocation ().getAccuracy ().getBinHigh ();

                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERR:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERR:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createAccuracy ().createStatError ();
                    else
                        param = axis.getCoverage ().getLocation ().getAccuracy ().getStatError ();

                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERRLOW:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRLOW:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createAccuracy ().createStatErrLow ();
                    else
                        param = axis.getCoverage ().getLocation ().getAccuracy ().getStatErrLow ();

                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERRHIGH:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRHIGH:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createAccuracy ().createStatErrHigh ();
                    else
                        param = axis.getCoverage ().getLocation ().getAccuracy ().getStatErrHigh ();

                break;
                case Utypes.SEG_CHAR_FLUXAXIS_COV_LOC_RESOLUTION:
                case Utypes.SEG_CHAR_SPECTRALAXIS_COV_LOC_RESOLUTION:
                case Utypes.SEG_CHAR_SPATIALAXIS_COV_LOC_RESOLUTION:
                case Utypes.SEG_CHAR_TIMEAXIS_COV_LOC_RESOLUTION:
                case Utypes.SEG_CHAR_CHARAXIS_COV_LOC_RESOLUTION:
                    if (create)
                        param = axis.createCoverage ().createLocation ().createResolution ();
                    else
                        param = axis.getCoverage ().getLocation ().getResolution ();
                break;
            }

        }
        catch (NullPointerException exp)
        {
            param = null;
        }

        return param;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Segment other = (Segment) obj;


        try {
                /*
                 * if the spectralAxis values are different then the segments are different
                 */
                if (!Arrays.equals(this.getSpectralAxisValues(), other.getSpectralAxisValues())) {
                    return false;
                }

                /*
                 * if the fluxAxis values are different then the segments are different
                 */
                if (!Arrays.equals(this.getFluxAxisValues(), other.getFluxAxisValues())) {
                    return false;
                }


            } catch (Exception ex) {
                Logger.getLogger(Sed.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }


        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

}

