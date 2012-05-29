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
import java.util.logging.Logger;

import cfa.vo.sedlib.common.SedConstants;
import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedNullException;
import cfa.vo.sedlib.common.Utypes;
import cfa.vo.sedlib.common.ValidationError;
import cfa.vo.sedlib.common.ValidationErrorEnum;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class describes a spectral segment. It provides accessors to
 * components of the segments. It also contains methods to access
 * and manipulate the data directly.
 *
 * 
 */
public class Segment
    extends Group implements IAccessByUtype, ISegment
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
    protected ArrayOfPoint data;

    @Override
    public Object clone ()
    {
        Segment segment = (Segment) super.clone();


        if (this.isSetTarget ())
            segment.target = (Target)this.target.clone ();
        if (this.isSetChar ())
            segment._char = (Characterization)this._char.clone ();
        if (this.isSetCoordSys ())
            segment.coordSys = (CoordSys)this.coordSys.clone ();
        if (this.isSetCuration ())
            segment.curation = (Curation)this.curation.clone ();
        if (this.isSetDataID ())
            segment.dataID = (DataID)this.dataID.clone ();
        if (this.isSetDerived ())
            segment.derived = (DerivedData)this.derived.clone ();
        if (this.isSetType ())
            segment.type = (TextParam)this.type.clone ();
        if (this.isSetTimeSI ())
            segment.timeSI = (TextParam)this.timeSI.clone ();
        if (this.isSetSpectralSI ())
            segment.spectralSI = (TextParam)this.spectralSI.clone ();
        if (this.isSetFluxSI ())
            segment.fluxSI = (TextParam)this.fluxSI.clone ();
        if (this.isSetData ())
            segment.data = (ArrayOfPoint)this.data.clone ();

        return segment;
    }



    /**
     * Gets the value of the target property.
     * 
     * @return
     *     either null or
     *     {@link Target }
     *     
     */
    @Override
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
    @Override
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
    @Override
    public void setTarget(Target value) {
        this.target = value;
    }

    @Override
    public boolean isSetTarget() {
        return (this.target!= null);
    }

    /**
     * Gets the value of the characterization property.
     * 
     * @return
     *     either null or
     *     {@link Characterization }
     *     
     */
    @Override
    public Characterization getChar() {
        return _char;
    }

    /**
     * Creates the characterization property if one does not exist.
     *
     * @return
     *     {@link Characterization }
     *
     */
    @Override
    public Characterization createChar() {
        if (this._char == null)
           this.setChar (new Characterization ());
        return this._char;
    }


    /**
     * Sets the value of the characterization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Characterization }
     *     
     */
    @Override
    public void setChar(Characterization value) {
        this._char = value;
    }

    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setCoordSys(CoordSys value) {
        this.coordSys = value;
    }

    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setCuration(Curation value) {
        this.curation = value;
    }

    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setDataID(DataID value) {
        this.dataID = value;
    }

    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setDerived(DerivedData value) {
        this.derived = value;
    }

    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setType(TextParam value) {
        this.type = value;
    }

    @Override
    public boolean isSetType() {
        return (this.type!= null);
    }

    /**
     * Gets the length of the point list.
     * 
     * @return int
     *     
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setTimeSI(TextParam value) {
        this.timeSI = value;
    }

    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setSpectralSI(TextParam value) {
        this.spectralSI = value;
    }

    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setFluxSI(TextParam value) {
        this.fluxSI = value;
    }

    @Override
    public boolean isSetFluxSI() {
        return (this.fluxSI!= null);
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     {@link ArrayOfPoint }
     *     
     */
    @Override
    public ArrayOfPoint getData() {
        return this.data;
    }

    /**
     * Creates data property if one does not exist.
     * The data property will be an ArrayOfPoint.
     *
     * @return
     *     {@link ArrayOfPoint }
     *
     */
    @Override
    public ArrayOfPoint createData() {
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
     *     
     */
    @Override
    public void setData(ArrayOfPoint value) {
        this.data = value;
    }

    @Override
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
    @Override
    public String getDataModel () {
        return SedConstants.DATAMODEL;
    }

    
    /**
     * Sets the values of the spectral axis.
     * If the provided values array is larger then the number of points,
     * new points are created to hold the extra values.
     *
     * @param values
     *   double[]
     *
     */
    @Override
    public void setSpectralAxisValues (double values[])
    {
        try { //This statement can't actually throw the exception, since it passes a supported utype
            this.setDataValues (values, Utypes.SEG_DATA_SPECTRALAXIS_VALUE);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the values of the spectral axis. 
     * If no data exists then SedNoDataExeption is thrown.
     * For points where the spectral axis is not set a NaN value is used.
     *
     * @return 
     *    double[]
     *
     * @throws SedNoDataException
     *
     */
    @Override
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
     * Sets the values of the flux axis.
     * If the provided values array is larger then the number of points,
     * new points are created to hold the extra values.
     *
     * @param values
     *   double[]
     *
     * @throws SedNoDataException
     *
     */
    @Override
    public void setFluxAxisValues (double values[])
    {
        try { //This statement can't actually throw the exception, since it is passing double[]
            this.setDataValues(values, Utypes.SEG_DATA_FLUXAXIS_VALUE);
        } catch (SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the values of the flux axis. 
     * If no data exists then SedNoDataException is thrown.
     * For points where the flux axis is not set a NaN value is used.
     *
     * @return 
     *   either double[]
     *
     * @throws SedNoDataException
     *
     */
    @Override
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
     * Sets the units of the spectral axis. 
     * If there are no data a SedNoDataException is thrown.
     *
     * @param units
     *    {@link String}
     *
     * @throws SedNoDataException
     *
     */
    @Override
    public void setSpectralAxisUnits (String units) throws SedNoDataException
    {
        try { //This code can't actually throw the exception, since we are using a supported utype

            Field field = null;
            field = this.getDataInfo (Utypes.SEG_DATA_SPECTRALAXIS_VALUE);
            if (field == null)
                    field = new Field ();
            field.setUnit (units);
            this.setDataInfo (field, Utypes.SEG_DATA_SPECTRALAXIS_VALUE);

        } catch(SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Gets the units of the spectral axis.
     * If no data exists then a SedNoDataException is thrown
     *
     * @return
     *    {@link String}
     *
     * @throws SedNoDataException
     */
    @Override
    public String getSpectralAxisUnits () throws SedNoDataException
    {
        try {//This code can't actually throw the exception, since we are using a supported utype
            Field field = null;
            String unit = null;

            field = this.getDataInfo (Utypes.SEG_DATA_SPECTRALAXIS_VALUE);
            if (field == null)
                throw new SedNoDataException("No Spectral Axis found.");

            unit = field.getUnit();
            if ( unit == null )
            {
                // Char provides default for Data.SpectralAxis.Units
                unit = (String)this.getValueByUtype(Utypes.SEG_CHAR_SPECTRALAXIS_UNIT, false);
            }
            return unit;

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
    @Override
    public void setFluxAxisUnits (String units) throws SedNoDataException
    {
        try {//This code can't actually throw the exception, since we are using a supported utype

            Field field = null;

            field = this.getDataInfo (Utypes.SEG_DATA_FLUXAXIS_VALUE);
            if (field == null)
                    field = new Field ();
            field.setUnit (units);
            this.setDataInfo (field, Utypes.SEG_DATA_FLUXAXIS_VALUE);

        } catch (SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the units of the flux axis. 
     * If no data exists then a SedNoDataException is thrown.
     *
     * @return
     *    {@link String}
     *
     * @throws SedNoDataException
     *
     */
    @Override
    public String getFluxAxisUnits () throws SedNoDataException
    {
        try {//This code can't actually throw the exception, since we are using a supported utype

            Field field = null;
            String unit = null;

            field = this.getDataInfo(Utypes.SEG_DATA_FLUXAXIS_VALUE);
            unit = field.getUnit();
            if (unit == null )
            {
                // Char provides default for Data.FluxAxis.Units
                unit = (String)this.getValueByUtype(Utypes.SEG_CHAR_FLUXAXIS_UNIT, false);
            }
            return unit;

        } catch(SedInconsistentException ex) {
            Logger.getLogger(Segment.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    /**
     *
     * <p>Check whether this segment is compatible with another one. This method is invoked each time a new segment is added to a Sed.</p>
     *
     * <p>Compatibility is assessed using the ucd string for the flux axis. The basic rule is that different units are allowed for the flux axis
     * as long as they refer to the same quantity, i.e. as long as they can be converted one into the other. For the spectral axis both units and quantities may differ.</p>
     *
     * <p>More in detail, two flux axis quantities are assumed to be compatible if they have the same ucd, unless they have a "flux density per unit wavelength".
     * In the latter case, the units string is checked to verify that both densities are expressed in either linear wavelengths or log wavelengths.</p>
     *
     * <p>The segments are assumed to be compatible if their flux axis differs only for the unit in which they are expressed, i.e. wavelength, frequency or energy.</p>
     *
     * <p>They are compatible if they have compatible pairs of flux axis quantities:</p>
     * <ol>
     * <li>phot.mag and phot.flux.density;em.*</li>
     * <li>phot.mag.sb and phot.flux.density.sb;em.*</li>
     * <li>phys.magAbs and phys.luminosity;em.*</li>
     * <li>phot.fluence;em.* and phot.flux.density;em.*</li>
     *
     * @param other The segment to check compatibility with
     * @return true if the segments are compatible, false otherwise.
     * @throws SedNoDataException if the <code>other</code> segment contains no data.
     * @throws SedInconsistentException if one of the segments is found to have no ucd for the flux axis.
     */
    public boolean isCompatibleWith(Segment other) throws SedNoDataException, SedInconsistentException {

        // assume they are incompatible
        boolean isCompatible = false;

        String myUcdString = this.createChar().createFluxAxis().getUcd().toLowerCase();
        String otherUcdString = other.createChar().createFluxAxis().getUcd().toLowerCase();

        if(myUcdString.equals("") || otherUcdString.equals(""))
            throw new SedInconsistentException("empty ucds");

        String myUnitString = "";
        String otherUnitString = "";

        if(this.getFluxAxisUnits()!=null) {
            myUnitString = this.getFluxAxisUnits();
        } else {
            if(this.createChar().createFluxAxis().isSetUnit())
                myUnitString = this.getChar().getFluxAxis().getUnit();
        }

        if(other.getFluxAxisUnits()!=null) {
            otherUnitString = other.getFluxAxisUnits();
        } else {
            otherUnitString = other.createChar().createFluxAxis().getUnit();
        }

        if(myUcdString.equals(otherUcdString)) {
            // if the ucds are the same, assume they are compatible
            isCompatible = true;

            // however, they could still be incompatible

            // check if they are flux densities with wl related units
            if(myUcdString.equals("phot.flux.density;em.wl")) {

                // if they don't have the same units check if one of them has log units
                // and the other has not
                if(!myUnitString.equals(otherUnitString)) {
                    boolean oneIsLog = myUnitString.equals("Jy Hz") || otherUnitString.equals("Jy Hz");
                    boolean oneIsLinear = myUnitString.equals("Jy") || otherUnitString.equals("Jy");
                    if(oneIsLog && oneIsLinear)
                        isCompatible = false; // if ucds are equal, are phot.flux.density;em.wl but one is log and one is linear
                                        // then they are not compatible.
                }
            } // end check if they are flux densities

            return isCompatible;
        } // end check if their ucds are the same


            
        // Do they differ just for a ;em.*?
        String myRegex = "(.*);em.(wl|freq|energy)";
        Pattern pattern = Pattern.compile(myRegex);
        Matcher matcher = pattern.matcher(myUcdString);

        if(matcher.find()) { // My ucd matches the pattern

            String otherRegex = matcher.group(1)+";em.(wl|freq|energy)";

            if(otherUcdString.matches(otherRegex)) // Ok, they are compatible
                return true;

        }

        // are they a compatible pair of magnitude and flux or luminosity?
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("phot.mag", "phot.flux.density;em.(wl|freq|energy)");
        map.put("phot.mag.sb", "phot.flux.density.sb;em.(wl|freq|energy)");
        map.put("phys.magAbs", "phys.luminosity;em.(wl|freq|energy)");

        for(Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            boolean myUcdInMap = myUcdString.matches(key) || myUcdString.matches(value);
            boolean otherUcdIsHere = otherUcdString.matches(key) || otherUcdString.matches(value);
            if(myUcdInMap && otherUcdIsHere)
                return true;
        }


        // Do they follow the pattern phot.fluence;em.* and phot.flux.density;em.*?
        String regex1 = "phot.fluence;em.(wl|freq|energy)";
        String regex2 = "phot.flux.density;em.(wl|freq|energy)";

        boolean oneIsFluence = myUcdString.matches(regex1) || otherUcdString.matches(regex1);
        boolean oneIsDensity = myUcdString.matches(regex2) || otherUcdString.matches(regex2);

        if(oneIsFluence && oneIsDensity) {
            return true;
        }


        return isCompatible;
    }


    /**
     * Gets the values of the specified utype.
     * If no data exists then SedNoDataException is thrown. For points where
     * the axis is not set a default value is used;
     *    + NaN for double type values
     *    + -9999 for int type values
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

        ArrayOfPoint pointData = this.data;

        if (pointData == null)
            throw new SedNoDataException("no data were found");

        return pointData.getDataValues (utype);
    }

    /**
     * Sets the values of the custom data id.
     * If the array is larger then the number of points, then the points array
     * is grown to accommodate them.
     *
     * @param values
     *   Valid values are double[], float[], int[], short[], long[] or String[]
     *
     * @throws SedNoDataException, SedInconsistentException, SedNullException
     *
     */
    @Override
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


        ArrayOfPoint pointData = this.data;

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
     * Sets the parameter info of the specified custom parameter on each point
     * of the Segment.
     * The 'id' property of the input Field is used to identify the custom
     * parameter to update.  All Field properties of the matching custom 
     * parameters will be replaced.
     *
     * If a particular pont does not contain a custom parameter with the
     * specified id, no action will occur.
     *
     * @param field
     *   {@link Field}
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    @Override
    public void setCustomDataInfo (Field field) throws SedNoDataException, SedNullException
    {
        ArrayOfPoint pointData = this.data;
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
    @Override
    public void addCustomData (Field field, Object values) 
        throws SedException, SedNoDataException, SedNullException, SedInconsistentException
    {
        ArrayOfPoint pointData = this.data;
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
     * Gets the values of the specified custom data parameter.
     * The values of the custom parameter matching the input id, are accumulated
     * into a data array and returned.  For points where no matching custom
     * parameter is found, a default value is used:
     *    +   NaN for double type
     *    + -9999 for int type
     *    + empty strings for string type
     *
     * If no Points are defined, a SedException will be thrown.
     *
     * @param id
     *   {@link String}
     *
     * @return 
     *   null if no points contain the specified custom parameter.
     *   Object cast of double[], int[], or string[] depending on the data.
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    @Override
    public Object getCustomDataValues (String id) 
        throws SedNoDataException, SedNullException
    {

        Object values = null;
        List<Point> points;
        int ivalues[];
        String svalues[];
        double dvalues[];


        ArrayOfPoint pointData = this.data;

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
     * Gets the parameter info of the specified id.
     *
     * @param id The custom id to be fetched.
     *
     * @return
     *   {@link Field} a Field object representing the data required.
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    @Override
    public Field getCustomDataInfo (String id) throws SedNoDataException, SedNullException
    {

        Field dataInfo = null;

        ArrayOfPoint pointData = this.data;
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
     * Gets the values of properties identified by the specified utype.
     * It is expected that the properties are under the Data node of the
     * Spectral model.  For points where the axis is not set
     * a default value is used:
     *    +   NaN for double types
     *    + -9999 for int types
     *
     * If no Point data exists, a SedNoDataException is thrown.
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
        ArrayOfPoint pointData = this.data;

        if (pointData == null)
            throw new SedNoDataException("no data were found");

        return pointData.getDataValues (utype);
    }


    /**
     * Sets the values of properties identified by the specified utype.
     * It is expected that the properties are under the Data node of the
     * Spectral model.
     * If more values exist then points, new points will be created to
     * accommodate the extra values.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
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
        ArrayOfPoint pointData = this.createData ();

        pointData.setDataValues (values, utype);
    }


    /**
     * Sets the values of properties identified by the specified utype.
     * It is expected that the properties are under the Data node of the
     * Spectral model.
     * If more values exist then points, new points will be created to 
     * accommodate the extra values.
     *
     * If the utype is not supported a SedInconsistentException is thrown
     *
     * @param values
     *   either a double[] or int[] depending on the data.
     * @param utype
     *   int
     *
     * @throws SedInconsistentException
     *
     */
    public void setDataValues (Object values, int utype) throws SedInconsistentException
    {
        ArrayOfPoint pointData = this.createData ();

        pointData.setDataValues(values, utype);
    }


    /**
     * Gets the parameter info of the specified Data property.
     *
     * If no Point data exists, a SedNoDataException is thrown.
     * If the utype is not supported a SedInconsistentException is thrown
     *
     * @param utype
     *   {@link String}
     *
     * @return
     *   {@link Field}
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    @Override
    public Field getDataInfo (String utype) throws SedNoDataException, SedInconsistentException
    {
        ArrayOfPoint pointData = this.data;

        if (pointData == null)
            throw new SedNoDataException("No pointData found.");

        return pointData.getDataInfo (utype);
    }


    /**
     * Gets the parameter info of the specified Data property.
     *
     * If no Point data exists, a SedNoDataException is thrown.
     * If the utype is not supported a SedInconsistentException is thrown
     *
     * @param utype
     *   int
     *
     * @return
     *   {@link Field}
     *
     * @throws SedInconsistentException, SedNoDataException
     *
     */
    @Override
    public Field getDataInfo (int utype) throws SedInconsistentException, SedNoDataException
    {
        ArrayOfPoint pointData = (ArrayOfPoint)this.data;

        if (pointData == null)
            throw new SedNoDataException("No pointData found");

        return pointData.getDataInfo (utype);
    }


    /**
     * Sets the parameter info of the specified Data property.
     * The Field information will be updated for each point.
     *
     * If a particular point does not define the indicated utype,
     * no update will occur.
     *
     * If no Point data is defined, a SedNoDataException is thrown.
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
    @Override
    public void setDataInfo (Field field, String utype) throws SedInconsistentException, SedNoDataException
    {
        ArrayOfPoint pointData = this.data;

        if (pointData == null)
            throw new SedNoDataException ("There is currently no data to set. Try createData () to create the PointArray.");

        pointData.setDataInfo (field, utype);
    }


    /**
     * Sets the parameter info of the specified Data property.
     * The Field information will be updated for each point.
     *
     * If a particular point does not define the indicated utype,
     * no update will occur.
     *
     * If no Point data is defined, a SedNoDataException is thrown.
     * If utype is not supported, a SedInconsistentException is thrown.
     *
     * @param field
     *   {@link Field}
     * @param utype
     *   int
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    @Override
    public void setDataInfo (Field field, int utype) throws SedNoDataException, SedInconsistentException
    {
        ArrayOfPoint pointData = this.data;

        if (pointData == null)
            throw new SedNoDataException ("There is currently no data to set. Try createData () to create the PointArray.");

        pointData.setDataInfo (field, utype);
    }


    /**
     * Gets the parameter identified by the specified utype.
     * The returned param will be a copy of the one in the segment. To update the segment use
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
            outParam = (Param)param.clone ();

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
	Object value = null;
	//        List <? extends Param> outParams = new ArrayList<Param>();
        List <? extends Param> outParams = null;
        
        try
        {
	    if ( Utypes.isDataIDUtype( utype ) && 
		 ( Utypes.isCollectionUtype( utype )|| Utypes.isContributorUtype( utype ) ) )
	    {
		value = this.getValueByUtype( utype, false );
		if ( value != null )
		{
		    outParams = ( List<TextParam> )value;
		}
	    }
	    else if ( Utypes.isCharacterizationUtype( utype ) && 
		      Utypes.isCoverageLocationUtype( utype ) && 
		      Utypes.isValueUtype( utype ) )
	    {
		value = this.getValueByUtype( utype, false );
		if ( value != null )
		{
		    outParams = ( List<DoubleParam> )value;
		}

	    }
	    else if ( Utypes.isTargetUtype( utype ) && 
		      Utypes.isPositionUtype( utype )   )
	    {
		value = this.getValueByUtype( utype, false );
		if ( value != null )
		{
		    outParams = ( List<DoubleParam> )value;
		}

	    }


	    if ( outParams == null )
		throw new SedInconsistentException ("Cannot find a parameter associated with the specified utype, "+Utypes.getName (utype)+".");
	    
        }
        catch (NullPointerException exp)
        {
	    // return empty list
	    outParams = new ArrayList<Param>();
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
        Param sedParam = this.getMetaParam(utype, true);

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
	boolean found = false;

	    if ( Utypes.isDataIDUtype( utype ) && 
		 ( Utypes.isCollectionUtype( utype )|| Utypes.isContributorUtype( utype ) ) )
	    {
		found = true;
	    }
	    else if ( Utypes.isCharacterizationUtype( utype ) && 
		      Utypes.isCoverageLocationUtype( utype ) && 
		      Utypes.isValueUtype( utype ) )
	    {
		found = true;
	    }
	    else if ( Utypes.isTargetUtype( utype ) && 
		      Utypes.isPositionUtype( utype )   )
	    {
		found = true;
	    }

	    if ( found )
		this.setValueByUtype( utype, params );
	    else
                throw new SedInconsistentException ("Cannot find a parameter associated with the specified utype, "+Utypes.getName (utype)+".");

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
	    Object value = null;

	    value = this.getValueByUtype( utype, create );
	    if ( value != null )
	    {
		if ( value instanceof Param )
		    param = ( Param )value;
	    }
	    else
	    {
		// These are not actually valid utypes..
		switch (utype)
		{
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

                default:
                    throw new SedInconsistentException ("Cannot find a parameter associated with the specified utype, "+Utypes.getName (utype)+".");
		} // end switch
	    } // end else
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

        return data.getPoint ();
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


    /**
     * Filter the segment based on the spectral axis value and the
     * specificed list of sorted ranges . Spectral values which fall 
     * outside  of the specified ranges will be removed. The input
     * range list is expected to be sorted.
     *
     */
    void filter (List<RangeParam> rangeParams)
    {
        List<Point> points = this.getPointsFromData ();
        List<Point> newPoints = new ArrayList<Point>(points.size ());

        for (Point point : points)
        {
            if (point.isSetSpectralAxis () &&
                point.getSpectralAxis ().isSetValue () &&
                point.getSpectralAxis ().getValue ().isSetValue ())
            {
                Double value = (Double)point.getSpectralAxis ().getValue ().getCastValue ();
                String unit = point.getSpectralAxis ().getValue ().getUnit ();

                for (RangeParam rangeParam : rangeParams)
                {
                    // verify the units are the same
                    if ((point.getSpectralAxis ().getValue ().isSetUnit () &&
                        rangeParam.isSetUnit () &&
                        !unit.equals (rangeParam.getUnit ())) ||
                        (point.getSpectralAxis ().getValue ().isSetUnit () ^ 
                        		rangeParam.isSetUnit ()))
                    	continue;
                    	
                    
                    if (value <= rangeParam.getMax ())
                    {
                        if (value >= rangeParam.getMin ())
                            newPoints.add(point);
                        else
                            break;  // point not included
                    }
                }
            }
        }

        if (this.isSetData ())
        {
            ArrayOfPoint pointData = (ArrayOfPoint)this.data;
            pointData.setPoint (newPoints);
        }
    }

    /**
     * Validate the Segment. The method returns true or false depending
     * on whether the Segment validates. 
     *
     * @return boolean; whether or not the Segment is valid
     */
    public boolean validate ()
    {
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        return this.validate (errors);
    }


    /**
     * Validate the Segment. The method returns true or false depending
     * on whether the Segment validates. It also fills in the a list
     * of errors that occurred when validating
     *
     * @param errors 
     *    List<ValidationError>
     *    {@link ValidationError}
     * @return boolean; whether or not the Sed is valid
     */
    public boolean validate (List<ValidationError> errors)
    {
        boolean valid = true;
        ValidationError error;

        if (this.isSetCuration ())
            valid &= this.curation.validate (errors);
        else 
        {
            valid = false;
            error = new ValidationError (ValidationErrorEnum.MISSING_CURATION_PUB);
            error.addNote ("Missing entire curation");
            errors.add (error);
        }

        if (this.isSetTarget ())
            valid &= this.target.validate (errors);
        else
        {
            valid = false;
            error = new ValidationError (ValidationErrorEnum.MISSING_TARGET_NAME);
            error.addNote ("Missing entire target");
            errors.add (error);
        }

        if (this.isSetChar ())
            valid &= this._char.validate (errors);
        else
        {
            valid = false;
            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_FLUXAXIS_UCD);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_FLUXAXIS_UNIT);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_UCD);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_UNIT);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_LOCATION_VALUE);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_LOCATION_VALUE);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_LOCATION_VALUE);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_START);
            error.addNote ("Missing entire characterization");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP);
            error.addNote ("Missing entire characterization");
            errors.add (error);

        }

        if (this.isSetData ())
            valid &= this.data.validate (errors);
        else
        {
            valid = false;
            error = new ValidationError (ValidationErrorEnum.MISSING_DATA_FLUXAXIS_VALUE);
            error.addNote ("Missing data");
            errors.add (error);

            error = new ValidationError (ValidationErrorEnum.MISSING_DATA_SPECTRALAXIS_VALUE);
            error.addNote ("Missing data");
            errors.add (error);

        }

        if ( this.isSetType() && this.type.isSetValue() )
        {
            String val = type.getValue().toLowerCase();
            if (!val.startsWith("photometry") &&
                !val.startsWith("spectrum") &&
                !val.startsWith("timeseries"))
            {
                valid = false;
                error = new ValidationError(ValidationErrorEnum.INVALID_SEGMENT_TYPE);
                error.addNote("Value found "+val);
                errors.add(error);
            }
        }

       

        return valid;
    }

    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create ) throws SedInconsistentException
    {
	Object value = null;

	if ( Utypes.isCharacterizationUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createChar().getValueByUtype( utypeNum, create );
	    else
		value = this.getChar().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isCoordSysUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createCoordSys().getValueByUtype( utypeNum, create );
	    else
		value = this.getCoordSys().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isCurationUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createCuration().getValueByUtype( utypeNum, create );
	    else
		value = this.getCuration().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isDataIDUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createDataID().getValueByUtype( utypeNum, create );
	    else
		value = this.getDataID().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isDerivedUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createDerived().getValueByUtype( utypeNum, create );
	    else
		value = this.getDerived().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isTargetUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createTarget().getValueByUtype( utypeNum, create );
	    else
		value = this.getTarget().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isFluxSIUtype( utypeNum ) )
	{
	    if (create)
		value = this.createFluxSI();
	    else
		value = this.getFluxSI();
	}
	else if ( Utypes.isSpectralSIUtype( utypeNum ) )
	{
	    if (create)
		value = this.createSpectralSI();
	    else
		value = this.getSpectralSI();
	}
	else if ( Utypes.isTimeSIUtype( utypeNum ) )
	{
	    if (create)
		value = this.createTimeSI();
	    else
		value = this.getTimeSI();
	}
	else if ( Utypes.isTypeUtype( utypeNum ) )
	{
	    if (create)
		value = this.createType();
	    else
		value = this.getType();
	}
	

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value ) throws SedInconsistentException
    {
	if ( Utypes.isCharacterizationUtype( utypeNum ) )
	{
	    this.createChar().setValueByUtype( utypeNum, value );
	}
	else if ( Utypes.isCoordSysUtype( utypeNum ) )
	{
	    this.createCoordSys().setValueByUtype( utypeNum, value );
	}	
	else if ( Utypes.isCurationUtype( utypeNum ) )
	{
	    this.createCuration().setValueByUtype( utypeNum, value );
	}
	else if ( Utypes.isDataIDUtype( utypeNum ) )
	{
	    this.createDataID().setValueByUtype( utypeNum, value );
	}
	else if ( Utypes.isDerivedUtype( utypeNum ) )
	{
	    this.createDerived().setValueByUtype( utypeNum, value );
	}
	else if ( Utypes.isTargetUtype( utypeNum ) )
	{
	    this.createTarget().setValueByUtype( utypeNum, value );
	}
	else if ( Utypes.isFluxSIUtype( utypeNum ) )
	{
	    this.setFluxSI( (TextParam)value );
	}
	else if ( Utypes.isSpectralSIUtype( utypeNum ) )
	{
	    this.setSpectralSI( (TextParam)value );
	}
	else if ( Utypes.isTimeSIUtype( utypeNum ) )
	{
	    this.setTimeSI( (TextParam)value );
	}
	else if ( Utypes.isTypeUtype( utypeNum ) )
	{
	    this.setType( (TextParam)value );
	}
    }
}

