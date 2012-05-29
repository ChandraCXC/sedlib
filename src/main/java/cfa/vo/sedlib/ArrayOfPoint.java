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

package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;

import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.Utypes;
import cfa.vo.sedlib.common.SedConstants;
import cfa.vo.sedlib.common.ValidationError;
import cfa.vo.sedlib.common.ValidationErrorEnum;


/**
 * <p>Java class for arrayOfPoint complex type.
 * 
 * 
 */
public class ArrayOfPoint implements Cloneable
{

    protected List<Point> point;

    @Override
    public Object clone ()
    {
    	ArrayOfPoint arrayOfPoint;
        try
        {
        	arrayOfPoint = (ArrayOfPoint) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // this should never happen
            throw new InternalError(e.toString());
        }        

        if (this.isSetPoint ())
        {
            arrayOfPoint.point = new ArrayList<Point>();
            for (Point pnt : this.point)
                arrayOfPoint.point.add ((Point)pnt.clone ());
        }
        return arrayOfPoint;
    }


    /**
     * Gets the point list.
     *
     * @return List<Point>
     *   either null or List<Point>
     *   {@link Point}
     *
     */
    public List<Point> getPoint() {
        return this.point;
    }

    /**
     * Creates the point list if one does not exist.
     *
     * @return List<Point>
     *   {@link Point}
     *
     */
    public List<Point> createPoint() {
        if (this.point == null) {
            this.point = new ArrayList<Point>();
        }
        return this.point;
    }
    public boolean isSetPoint() {
        return (this.point!= null);
    }

    /**
     * Sets the point list to a new list
     *
     * @param point
     *     allowed object is List<Point>
     *     {@link Point }
     *
     */
    public void setPoint(List<Point> point) {
        this.point = point;
    }

    /**
     * Gets the length of the point list.
     *
     */
    public int getLength()
    {
        if (this.point == null)
            return 0;
        return this.point.size ();
    }

    /**
     * Gets the values of the specified utype. If no data exists then
     * a SedNoDataException is thrown.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
     *
     * For points where the utype is not set
     * a default value is used; for doubles its NaN for ints its -9999.
     * @param utype
     *   {@link String}
     *
     * @return
     *   either double[] or int[] depending on the data. The
     *   arrays will be cast to an Object.
     *
     * @throws SedInconsistentException, SedNoDataException
     *
     */
    public Object getDataValues (String utype) throws SedInconsistentException, SedNoDataException
    {

        int utypeEnum = Utypes.getUtypeFromString (utype);

        if (utypeEnum == Utypes.INVALID_UTYPE)
            throw new SedInconsistentException ("The utype, "+utype+", is not supported. There is no parameter associated with this utype.");

        return this.getDataValues (utypeEnum);
    }


    /**
     * Gets the values of the specified utype. If no data exists then
     * SedNoDataException is returned. For points where the utype is not set
     * a default value is used; for doubles its NaN for ints its -9999.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
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

        Object returnValues = null;
        double []dValues = null;
        int []iValues = null;

        if (this.point == null)
        	throw new SedNoDataException("No data values found");

        switch (utype)
        {
            case Utypes.SEG_DATA_FLUXAXIS_QUALITY:
            case Utypes.SEG_DATA_BGM_QUALITY:
                iValues = new int[this.point.size ()];
                returnValues = iValues;
                break;
            default:
                dValues = new double[this.point.size ()];
                returnValues = dValues;
                break;
        }
 
        for (int ii=0; ii<this.point.size (); ii++)
        {
            Point pnt = this.point.get(ii);
            Param param = this.getDataParam (utype, pnt);

            // if it's an integer
            if (iValues != null)
            {
                if (param != null)
                    iValues[ii] = (Integer)(param.getCastValue ());
                else
                    iValues[ii] = SedConstants.DEFAULT_INTEGER;
            }
            // it must be a double
            else 
            {
                if (param != null)
                    dValues[ii] = (Double)(param.getCastValue ());
                else
                    dValues[ii] = SedConstants.DEFAULT_DOUBLE;
            }
        }

        
        return returnValues;

    }

    /**
     * Sets the values of the specified utype.
     *
     * If the input datatype is not String[], double[] or int[] a SedInconsistentException is thrown.
     *
     * If the utype is not supported, a SedInconsistentException is thrown.
     *
     * @param values
     *   either a double[] or int[] depending on the data.
     * @param utype
     *   {@link String}
     *
     * @throws SedInconsistentException
     *
     */
    public void setDataValues (Object values, String utype) throws SedInconsistentException
    {

        int utypeEnum = Utypes.getUtypeFromString (utype);

        if (utypeEnum == Utypes.INVALID_UTYPE)
            throw new SedInconsistentException ("The utype, "+utype+", is not supported. There is no parameter associated with this utype.");

        this.setDataValues (values, utypeEnum);
    }



    public void setDataAttribute(Object values, int utype) throws SedInconsistentException
    {

        String []sValues = null;
        int length = 0;

        if (this.point == null)
            this.createPoint ();

        if (values instanceof String[])
        {
       	    sValues = (String[])values;
            length = sValues.length;
        }
        else
            throw new SedInconsistentException ("Unknown input data type. The input is expected to be a String array");

        for (int ii=0; ii<length; ii++)
        {
            Point pnt;
            Param param = null;
            if (this.point.size () == ii)
            {
                pnt = new Point ();
                this.point.add(pnt);
            }
            else
                pnt  = this.point.get(ii);

            if ( (utype == Utypes.SEG_DATA_FLUXAXIS_UNIT) || (utype == Utypes.SEG_DATA_FLUXAXIS_UCD ) )
            {
		param = this.getDataParam(Utypes.SEG_DATA_FLUXAXIS_VALUE, pnt, false);
            }
            else if ( (utype == Utypes.SEG_DATA_SPECTRALAXIS_UNIT) || (utype == Utypes.SEG_DATA_SPECTRALAXIS_UCD) )
            {
		param = this.getDataParam(Utypes.SEG_DATA_SPECTRALAXIS_VALUE, pnt, false);
            }
            else if ((utype == Utypes.SEG_DATA_TIMEAXIS_UNIT) || (utype == Utypes.SEG_DATA_TIMEAXIS_UCD))
            {
                param = this.getDataParam (Utypes.SEG_DATA_TIMEAXIS_VALUE, pnt, false);
            }
            else if ((utype == Utypes.SEG_DATA_BGM_UNIT) || (utype == Utypes.SEG_DATA_BGM_UCD))
            {
		param = this.getDataParam (Utypes.SEG_DATA_BGM_VALUE, pnt, false);
            }

            if ( param != null )
            {
              if ( (utype == Utypes.SEG_DATA_FLUXAXIS_UNIT)     ||
                   (utype == Utypes.SEG_DATA_SPECTRALAXIS_UNIT) ||
                   (utype == Utypes.SEG_DATA_TIMEAXIS_UNIT)     ||
                   (utype == Utypes.SEG_DATA_BGM_UNIT) )
              {
                ((DoubleParam)param).setUnit( sValues[ii]);
              }
              else if ( (utype == Utypes.SEG_DATA_FLUXAXIS_UCD)     ||
                        (utype == Utypes.SEG_DATA_SPECTRALAXIS_UCD) ||
                        (utype == Utypes.SEG_DATA_TIMEAXIS_UCD)     ||
                        (utype == Utypes.SEG_DATA_BGM_UCD))
              {
                param.setUcd( sValues[ii]);
              }
            }
        }
    }


    /**
     * Sets the values of the specified utype.
     *
     * If the input datatype is not String[], double[] or int[] a SedInconsistentException is thrown.
     *
     * @param values
     *   either a string[], double[] or int[] depending on the data.
     * @param utype
     *   int
     *
     * @throws SedInconsistentException
     *
     */
    public void setDataValues (Object values, int utype) throws SedInconsistentException
    {

        double []dValues = null;
        int []iValues = null;
        String []sValues = null;
        int length = 0;

        if (this.point == null)
            this.createPoint ();
//            throw new SedException ("There are no points to set values to.");


        if (values instanceof int[])
        {
            iValues = (int[])values;
            length = iValues.length;
        }
        else if (values instanceof double[])
        {
            dValues = (double[])values;
            length = dValues.length;
        }
        else if (values instanceof String[])
        {
       	    sValues = (String[])values;
            length = sValues.length;
        }
        else
            throw new SedInconsistentException ("Unknown input data type. The input is expected to be an int, double or String array");

        for (int ii=0; ii<length; ii++)
        {
            Point pnt;
            Param param;
            if (this.point.size () == ii)
            {
                pnt = new Point ();
                this.point.add(pnt);
            }
            else 
                pnt  = this.point.get(ii);

            param = this.getDataParam (utype, pnt, true);

            // if it's an integer
            if (iValues != null)
              param.setValue (Integer.toString (iValues[ii]));
            // if it's a double
            else if (dValues != null)
               param.setValue (Double.toString (dValues[ii]));
            else
               param.setValue (sValues[ii]);

        }
    }


    /**
     * Gets the parameter info of the specified utype. If no data exists then
     * a SedNoDataException is thrown.
     *
     * If the utype is not supported, a SedInconsistentException is thrown.
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

        int utypeEnum = Utypes.getUtypeFromString (utype);

        if (utypeEnum == Utypes.INVALID_UTYPE)
            throw new SedInconsistentException ("The utype, "+utype+", is not supported. There is no parameter associated with this utype.");

        return this.getDataInfo (utypeEnum);
    }


    /**
     * Gets the parameter info of the specified utype. If no data exists then
     * a SedNoDataException is thrown.
     * @param utype
     *   int
     *
     * @return
     *   {@link Field}
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    public Field getDataInfo (int utype) throws SedNoDataException, SedInconsistentException
    {

        Field dataInfo = null;

        if (this.point == null)
            throw new SedNoDataException("No data values found");

        for (int ii=0; ii<this.point.size (); ii++)
        {
            Point pnt = this.point.get(ii);
            Param param = this.getDataParam (utype, pnt);

            if (param == null)
                continue;

            dataInfo = new Field (param.header); 

            break;
                
        }

        return dataInfo;

    }

    /**
     * Sets the parameter info of the specified utype. The specfied field
     * will replace all information on each point except for the value. If no data
     * exists for the specified utype on a particular point the point will be ignored.
     *
     * If there are no points to set, a SedNoDataException is thrown.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
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

        int utypeEnum = Utypes.getUtypeFromString (utype);

        if (utypeEnum == Utypes.INVALID_UTYPE)
            throw new SedInconsistentException ("The utype, "+utype+", is not supported. There is no parameter associated with this utype.");

        this.setDataInfo (field, utypeEnum);
    }


    /**
     * Sets the parameter info of the specified utype. The specfied field
     * will replace all information on each point except for the value. If no data 
     * exists for the specified utype on a particular point the point will be ignored.
     *
     * If there are no point to set, a SedNoDataException is thrown.
     *
     * If the library cannot find a parameter associated with the specified utype
     * a SedInconsistentException will be thrown.
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
        if (this.point == null)
            throw new SedNoDataException ("There are currently no points to set. Try createPoint () to create the point list.");

        for (int ii=0; ii<this.point.size (); ii++)
        {
            Point pnt = this.point.get(ii);
            Param param = this.getDataParam (utype, pnt);

            if (param == null)
                continue;

            param.header = new Field (field);
        }

    }


    /**
     * Get the Param associated specified utype. If the parameter does not
     * exists null is returned
     */
    Param getDataParam (int utype, Point point) throws SedInconsistentException
    {
        return this.getDataParam (utype, point, false);
    }


    /**
     * Get the Param associated specified utype. The create option allows
     * users to specify whether to create the param if it doesn't exists. If
     * create is false and the param doesn't exists null is returned
     */
    Param getDataParam (int utype, Point point, boolean create ) throws SedInconsistentException
    {
        Param param = null;

        try
        {
            switch (utype)
            {
                case Utypes.SEG_DATA_FLUXAXIS_VALUE:
                    if (create)
                        param = point.createFluxAxis ().createValue ();
                    else
                        param = point.getFluxAxis ().getValue ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_QUALITY:
                    if (create)
                        param = point.createFluxAxis ().createQuality ();
                    else
                        param = point.getFluxAxis ().getQuality ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_ACC_BINLOW:
                    if (create)
                        param = point.createFluxAxis ().createAccuracy ().createBinLow ();
                    else
                        param = point.getFluxAxis ().getAccuracy ().getBinLow ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_ACC_BINHIGH:
                    if (create)
                        param = point.createFluxAxis ().createAccuracy ().createBinHigh ();
                    else
                        param = point.getFluxAxis ().getAccuracy ().getBinHigh ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_ACC_BINSIZE:
                    if (create)
                        param = point.createFluxAxis ().createAccuracy ().createBinSize ();
                    else
                        param = point.getFluxAxis ().getAccuracy ().getBinSize ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_ACC_STATERRLOW:
                    if (create)
                        param = point.createFluxAxis ().createAccuracy ().createStatErrLow ();
                    else
                        param = point.getFluxAxis ().getAccuracy ().getStatErrLow ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_ACC_STATERRHIGH:
                    if (create)
                        param = point.createFluxAxis ().createAccuracy ().createStatErrHigh ();
                    else
                        param = point.getFluxAxis ().getAccuracy ().getStatErrHigh ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_ACC_STATERR:
                    if (create)
                        param = point.createFluxAxis ().createAccuracy ().createStatError ();
                    else
                        param = point.getFluxAxis ().getAccuracy ().getStatError ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_ACC_SYSERR:
                    if (create)
                        param = point.createFluxAxis ().createAccuracy ().createSysError ();
                    else
                        param = point.getFluxAxis ().getAccuracy ().getSysError ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_ACC_CONFIDENCE:
                    if (create)
                        param = point.createFluxAxis ().createAccuracy ().createConfidence ();
                    else
                        param = point.getFluxAxis ().getAccuracy ().getConfidence ();
                    break;
                case Utypes.SEG_DATA_FLUXAXIS_RESOLUTION:
                    if (create)
                        param = point.createFluxAxis ().createResolution ();
                    else
                        param = point.getFluxAxis ().getResolution ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_VALUE:
                    if (create)
                        param = point.createSpectralAxis ().createValue ();
                    else
                        param = point.getSpectralAxis ().getValue ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_ACC_STATERR:
                    if (create)
                        param = point.createSpectralAxis ().createAccuracy ().createStatError ();
                    else
                        param = point.getSpectralAxis ().getAccuracy ().getStatError ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW:
                    if (create)
                        param = point.createSpectralAxis ().createAccuracy ().createStatErrLow ();
                    else
                        param = point.getSpectralAxis ().getAccuracy ().getStatErrLow ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH:
                    if (create)
                        param = point.createSpectralAxis ().createAccuracy ().createStatErrHigh ();
                    else
                        param = point.getSpectralAxis ().getAccuracy ().getStatErrHigh ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_ACC_BINLOW:
                    if (create)
                        param = point.createSpectralAxis ().createAccuracy ().createBinLow ();
                    else
                        param = point.getSpectralAxis ().getAccuracy ().getBinLow ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_ACC_BINHIGH:
                    if (create)
                        param = point.createSpectralAxis ().createAccuracy ().createBinHigh ();
                    else
                        param = point.getSpectralAxis ().getAccuracy ().getBinHigh ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_ACC_BINSIZE:
                    if (create)
                        param = point.createSpectralAxis ().createAccuracy ().createBinSize ();
                    else
                        param = point.getSpectralAxis ().getAccuracy ().getBinSize ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_ACC_SYSERR:
                    if (create)
                        param = point.createSpectralAxis ().createAccuracy ().createSysError ();
                    else
                        param = point.getSpectralAxis ().getAccuracy ().getSysError ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_RESOLUTION:
                    if (create)
                        param = point.createSpectralAxis ().createResolution ();
                    else
                        param = point.getSpectralAxis ().getResolution ();
                    break;
                case Utypes.SEG_DATA_SPECTRALAXIS_ACC_CONFIDENCE:
                    if (create)
                        param = point.createSpectralAxis ().createAccuracy ().createConfidence ();
                    else
                        param = point.getSpectralAxis ().getAccuracy ().getConfidence ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_VALUE:
                    if (create)
                        param = point.createTimeAxis ().createValue ();
                    else
                        param = point.getTimeAxis ().getValue ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_ACC_STATERR:
                    if (create)
                        param = point.createTimeAxis ().createAccuracy ().createStatError ();
                    else
                        param = point.getTimeAxis ().getAccuracy ().getStatError ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_ACC_STATERRLOW:
                    if (create)
                        param = point.createTimeAxis ().createAccuracy ().createStatErrLow ();
                    else
                        param = point.getTimeAxis ().getAccuracy ().getStatErrLow ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_ACC_STATERRHIGH:
                    if (create)
                        param = point.createTimeAxis ().createAccuracy ().createStatErrHigh ();
                    else
                        param = point.getTimeAxis ().getAccuracy ().getStatErrHigh ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_ACC_SYSERR:
                    if (create)
                        param = point.createTimeAxis ().createAccuracy ().createSysError ();
                    else
                        param = point.getTimeAxis ().getAccuracy ().getSysError ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_ACC_BINLOW:
                    if (create)
                        param = point.createTimeAxis ().createAccuracy ().createBinLow ();
                    else
                        param = point.getTimeAxis ().getAccuracy ().getBinLow ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_ACC_BINHIGH:
                    if (create)
                        param = point.createTimeAxis ().createAccuracy ().createBinHigh ();
                    else
                        param = point.getTimeAxis ().getAccuracy ().getBinHigh ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_ACC_BINSIZE:
                    if (create)
                        param = point.createTimeAxis ().createAccuracy ().createBinSize ();
                    else
                        param = point.getTimeAxis ().getAccuracy ().getBinSize ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_ACC_CONFIDENCE:
                    if (create)
                        param = point.createTimeAxis ().createAccuracy ().createConfidence ();
                    else
                        param = point.getTimeAxis ().getAccuracy ().getConfidence ();
                    break;
                case Utypes.SEG_DATA_TIMEAXIS_RESOLUTION:
                    if (create)
                        param = point.createTimeAxis ().createResolution ();
                    else
                        param = point.getTimeAxis ().getResolution ();
                    break;
                case Utypes.SEG_DATA_BGM_VALUE:
                    if (create)
                        param = point.createBackgroundModel ().createValue ();
                    else
                        param = point.getBackgroundModel ().getValue ();
                    break;
                case Utypes.SEG_DATA_BGM_QUALITY:
                    if (create)
                        param = point.createBackgroundModel ().createQuality ();
                    else
                        param = point.getBackgroundModel ().getQuality ();
                    break;
                case Utypes.SEG_DATA_BGM_ACC_STATERR:
                    if (create)
                        param = point.createBackgroundModel ().createAccuracy ().createStatError ();
                    else
                        param = point.getBackgroundModel ().getAccuracy ().getStatError ();
                    break;
                case Utypes.SEG_DATA_BGM_ACC_STATERRLOW:
                    if (create)
                        param = point.createBackgroundModel ().createAccuracy ().createStatErrLow ();
                    else
                        param = point.getBackgroundModel ().getAccuracy ().getStatErrLow ();
                    break;
                case Utypes.SEG_DATA_BGM_ACC_STATERRHIGH:
                    if (create)
                        param = point.createBackgroundModel ().createAccuracy ().createStatErrHigh ();
                    else
                        param = point.getBackgroundModel ().getAccuracy ().getStatErrHigh ();
                    break;
                case Utypes.SEG_DATA_BGM_ACC_SYSERR:
                    if (create)
                        param = point.createBackgroundModel ().createAccuracy ().createSysError ();
                    else
                        param = point.getBackgroundModel ().getAccuracy ().getSysError ();
                    break;
                case Utypes.SEG_DATA_BGM_RESOLUTION:
                    if (create)
                        param = point.createBackgroundModel ().createResolution ();
                    else
                        param = point.getBackgroundModel ().getResolution ();
                    break;
                case Utypes.SEG_DATA_BGM_ACC_BINLOW:
                    if (create)
                        param = point.createBackgroundModel ().createAccuracy ().createBinLow ();
                    else
                        param = point.getBackgroundModel ().getAccuracy ().getBinLow ();
                    break;
                case Utypes.SEG_DATA_BGM_ACC_BINHIGH:
                    if (create)
                        param = point.createBackgroundModel ().createAccuracy ().createBinHigh ();
                    else
                        param = point.getBackgroundModel ().getAccuracy ().getBinHigh ();
                    break;
                case Utypes.SEG_DATA_BGM_ACC_BINSIZE:
                    if (create)
                        param = point.createBackgroundModel ().createAccuracy ().createBinSize ();
                    else
                        param = point.getBackgroundModel ().getAccuracy ().getBinSize ();
                    break;
                case Utypes.SEG_DATA_BGM_ACC_CONFIDENCE:
                    if (create)
                        param = point.createBackgroundModel ().createAccuracy ().createConfidence ();
                    else
                        param = point.getBackgroundModel ().getAccuracy ().getConfidence ();
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
     * Validate the ArrayOfPoint. The method returns true or false depending
     * on whether the ArrayOfPoint validates.
     *
     * @return boolean; whether or not the ArrayOfPoint is valid
     */
    public boolean validate ()
    {
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        return this.validate (errors);
    }

    /**
     * Validate the ArrayOfPoint. The method returns true or false depending
     * on whether the ArrayOfPoint validates. It also fills in the a list
     * of errors that occurred when validating
     *
     * @param errors
     *    List<ValidationError>
     *    {@link ValidationError}
     * @return boolean; whether or not the ArrayOfPoint is valid
     */
    public boolean validate (List<ValidationError> errors)
    {
        boolean valid = true;

        if (this.isSetPoint ())
        {
            for (Point pnt : this.point)
                valid &= pnt.validate (errors);
        }
        else
        {
            String message = "There are no points";
            valid = false;
            errors.add (new ValidationError (ValidationErrorEnum.MISSING_DATA_FLUXAXIS_VALUE, message));
            errors.add (new ValidationError (ValidationErrorEnum.MISSING_DATA_SPECTRALAXIS_VALUE, message));
        }

        return valid;
    }

}
