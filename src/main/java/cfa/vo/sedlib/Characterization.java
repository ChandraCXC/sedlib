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


import cfa.vo.sedlib.common.Utypes;
import cfa.vo.sedlib.common.SedInconsistentException;

import cfa.vo.sedlib.common.ValidationError;
import cfa.vo.sedlib.common.ValidationErrorEnum;


/**
 * <p>Java class for characterization complex type.
 * 
 * 
 * 
 */
public class Characterization
    extends Group implements IAccessByUtype
{

    protected CharacterizationAxis spatialAxis;
    protected CharacterizationAxis timeAxis;
    protected SpectralCharacterizationAxis spectralAxis;
    protected CharacterizationAxis fluxAxis;
    protected List<CharacterizationAxis> characterizationAxis;

    @Override
    public Object clone ()
    {
        Characterization _char = null;

        _char = (Characterization) super.clone();

        if (this.isSetSpatialAxis ())
            _char.spatialAxis = (CharacterizationAxis)this.spatialAxis.clone ();
        if (this.isSetTimeAxis ())
            _char.timeAxis = (CharacterizationAxis)this.timeAxis.clone ();
        if (this.isSetSpectralAxis ())
            _char.spectralAxis = (SpectralCharacterizationAxis)this.spectralAxis.clone ();
        if (this.isSetFluxAxis ())
            _char.fluxAxis = (CharacterizationAxis)this.fluxAxis.clone ();
        if (this.isSetCharacterizationAxis ())
        {
            _char.characterizationAxis = new ArrayList<CharacterizationAxis>();
            for (CharacterizationAxis charAxis : this.characterizationAxis)
                _char.characterizationAxis.add ((CharacterizationAxis)charAxis.clone ());
        }
        return _char;
    }


    /**
     * Gets the value of the spatialAxis property.
     * 
     * @return
     *     either null or
     *     {@link CharacterizationAxis }
     *     
     */
    public CharacterizationAxis getSpatialAxis() {
        return spatialAxis;
    }

    /**
     * Create the spatialAxis property if one does not exist.
     *
     * @return
     *     {@link CharacterizationAxis }
     *
     */
    public CharacterizationAxis createSpatialAxis() {
        if (this.spatialAxis == null)
           this.setSpatialAxis (new CharacterizationAxis ());
        return this.spatialAxis;
    }


    /**
     * Sets the value of the spatialAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterizationAxis }
     *     
     */
    public void setSpatialAxis(CharacterizationAxis value) {
        this.spatialAxis = value;
    }

    public boolean isSetSpatialAxis() {
        return (this.spatialAxis!= null);
    }

    /**
     * Gets the value of the timeAxis property.
     * 
     * @return
     *     either null or
     *     {@link CharacterizationAxis }
     *     
     */
    public CharacterizationAxis getTimeAxis() {
        return timeAxis;
    }

    /**
     * Create the timeAxis property if one does not exist.
     *
     * @return
     *     {@link CharacterizationAxis }
     *
     */
    public CharacterizationAxis createTimeAxis() {
        if (this.timeAxis == null)
           this.setTimeAxis (new CharacterizationAxis ());
        return this.timeAxis;
    }


    /**
     * Sets the value of the timeAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterizationAxis }
     *     
     */
    public void setTimeAxis(CharacterizationAxis value) {
        this.timeAxis = value;
    }

    public boolean isSetTimeAxis() {
        return (this.timeAxis!= null);
    }

    /**
     * Gets the value of the spectralAxis property.
     * 
     * @return
     *     either null or
     *     {@link SpectralCharacterizationAxis }
     *     
     */
    public SpectralCharacterizationAxis getSpectralAxis() {
        return spectralAxis;
    }

    /**
     * Create the spectralAxis property if one does not exist.
     *
     * @return
     *     {@link SpectralCharacterizationAxis }
     *
     */
    public SpectralCharacterizationAxis createSpectralAxis() {
        if (this.spectralAxis == null)
           this.setSpectralAxis (new SpectralCharacterizationAxis ());
        return this.spectralAxis;
    }


    /**
     * Sets the value of the spectralAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpectralCharacterizationAxis }
     *     
     */
    public void setSpectralAxis(SpectralCharacterizationAxis value) {
        this.spectralAxis = value;
    }

    public boolean isSetSpectralAxis() {
        return (this.spectralAxis!= null);
    }

    /**
     * Gets the value of the fluxAxis property.
     * 
     * @return
     *     either null or
     *     {@link CharacterizationAxis }
     *     
     */
    public CharacterizationAxis getFluxAxis() {
        return fluxAxis;
    }

    /**
     * Create the fluxAxis property if one does not exist.
     *
     * @return
     *     {@link CharacterizationAxis }
     *
     */
    public CharacterizationAxis createFluxAxis() {
        if (this.fluxAxis == null)
           this.setFluxAxis (new CharacterizationAxis ());
        return this.fluxAxis;
    }


    /**
     * Sets the value of the fluxAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterizationAxis }
     *     
     */
    public void setFluxAxis(CharacterizationAxis value) {
        this.fluxAxis = value;
    }

    public boolean isSetFluxAxis() {
        return (this.fluxAxis!= null);
    }


    /**
     * Gets the CharacterizationAxis list.
     *
     * @return List<CharacterizationAxis>
     *   either null or List<CharacterizationAxis>
     *   {@link CharacterizationAxis}
     *
     */
    public List<CharacterizationAxis> getCharacterizationAxis() {
        return this.characterizationAxis;
    }

    /**
     * Creates the CharacterizationAxis list if one does not exist.
     *
     * @return List<CharacterizationAxis>
     *   {@link CharacterizationAxis}
     *
     */
    public List<CharacterizationAxis> createCharacterizationAxis() {
        if (characterizationAxis == null) {
            characterizationAxis = new ArrayList<CharacterizationAxis>();
        }
        return this.characterizationAxis;
    }


    public boolean isSetCharacterizationAxis() {
        return (this.characterizationAxis!= null);
    }

    /**
     * Sets the point list to a new list
     *
     * @param characterizationAxis
     *     allowed object is List<CharacterizationAxis>
     *     {@link CharacterizationAxis }
     *
     */
    public void setCharacterizationAxis(List <CharacterizationAxis> characterizationAxis) {
        this.characterizationAxis = characterizationAxis;
    }

    /**
     * Validate the Characterization. The method returns true or false depending
     * on whether the Characterization validates.
     *
     * @return boolean; whether or not the Characterization is valid
     */
    public boolean validate ()
    {
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        return this.validate (errors);
    }


    /**
     * Validate the Characterization. The method returns true or false depending
     * on whether the Characterization validates. It also fills in the a list
     * of errors that occurred when validating
     *
     * @param errors
     *    List<ValidationError>
     *    {@link ValidationError}
     * @return boolean; whether or not the Sed is valid
     */
    public boolean validate (List<ValidationError> errors)
    {
        List<ValidationError> errorList = new ArrayList<ValidationError> ();
        ValidationError error;
        String message;

        if (this.isSetFluxAxis ())
        {
            if (!this.fluxAxis.isSetUcd ())
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_FLUXAXIS_UCD));
            if (!this.fluxAxis.isSetUnit ())
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_FLUXAXIS_UNIT));
        }
        else
        {
           message = "Missing entire flux axis";
           errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_FLUXAXIS_UCD, message));
           errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_FLUXAXIS_UNIT, message));

        }

        if (this.isSetSpectralAxis ())
        {
            if (!this.spectralAxis.isSetUcd ())
                errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_UCD));
            if (!this.spectralAxis.isSetUnit ())
                errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_UNIT));
            if (this.spectralAxis.isSetCoverage ())
            {
                Coverage coverage = this.spectralAxis.getCoverage ();
                if (coverage.isSetLocation ())
                {
                    if (!coverage.getLocation ().isSetValue ())
                        errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_LOCATION_VALUE));

                }
                else
                {
                    message = "Missing entire location";
                    errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_LOCATION_VALUE, message));
                }

                if (coverage.isSetBounds ())
                {
                    if (!coverage.getBounds ().isSetExtent ())
                        errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT));
                    if (coverage.getBounds ().isSetRange ())
                    {
                        if (!coverage.getBounds ().getRange ().isSetMin ())
                            errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_START));
                        if (!coverage.getBounds ().getRange ().isSetMin ())
                            errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP));
                    }
                    else
                    {
                        message = "Missing entire range";
                        errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_START, message));
                        errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP, message)); 
                    }
                }
                else
                {
                    message = "Missing entire bounds";
                    errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT, message));
                    errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_START, message));
                    errorList.add (new ValidationError(ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP, message));
                }

            }
            else
            {
                message = "Missing entire coverage";
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_LOCATION_VALUE, message));
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT, message));
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_START, message));
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP, message));
            }
        }
        else
        {
            message = "Missing entire spectral axis";
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_UCD, message));
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_UNIT, message));
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_LOCATION_VALUE, message));
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT, message));
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_START, message));
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP, message));
        }

        if (this.isSetSpatialAxis ())
        {
            if (this.spatialAxis.isSetCoverage ())
            {
                Coverage coverage = this.spatialAxis.getCoverage ();
                if (coverage.isSetLocation ())
                {
                    if (!coverage.getLocation ().isSetValue ())
                        errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_LOCATION_VALUE));
                }
                else
                {
                    errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_LOCATION_VALUE, "Missing entire location"));
                }

                if (coverage.isSetBounds ())
                {
                    if (!coverage.getBounds ().isSetExtent ())
                        errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT));
                }
                else
                {
                    errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT, "Missing entire bounds"));
                }
            }
            else
            {
                message = "Missing entire coverage";
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_LOCATION_VALUE, message));
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT, message));
            }
        }
        else
        {
            message = "Missing entire spatial axis";
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_LOCATION_VALUE, message));
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT, message));
        }

        if (this.isSetTimeAxis ())
        {
            if (this.timeAxis.isSetCoverage ())
            {
                Coverage coverage = this.timeAxis.getCoverage ();
                if (coverage.isSetLocation ())
                {
                    if (!coverage.getLocation ().isSetValue ())
                        errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_LOCATION_VALUE));
                }
                else
                {
                    errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_LOCATION_VALUE,"Missing entire location"));
                }

                if (coverage.isSetBounds ())
                {
                    if (!coverage.getBounds ().isSetExtent ())
                        errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT));
                }
                else
                {
                    errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT,"Missing entire bounds"));
                }
            }
            else
            {
                message = "Missing entire coverage";
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_LOCATION_VALUE, message));
                errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT, message));
            }
        }
        else
        {
            message = "Missing entire time axis";
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_LOCATION_VALUE, message));
            errorList.add (new ValidationError (ValidationErrorEnum.MISSING_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT, message));
        }


        errors.addAll (errorList);

        return errorList.isEmpty ();
    }


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create ) throws SedInconsistentException
    {
	CharacterizationAxis axis = null;
	Object value = null;

	if ( Utypes.isCharAxisUtype( utypeNum ) )
	{
	    List <CharacterizationAxis> charAxes = null;
	    if (create)
		charAxes = this.createCharacterizationAxis();
	    else
		charAxes = this.getCharacterizationAxis();

	    if (charAxes != null)
            {
		if (charAxes.size() > 1)
		    throw new SedInconsistentException ("Multiple copies of the Characterization Axis found. It's ambiguous as to which param should be retrieved.");
		if ( create && charAxes.isEmpty() )
		    charAxes.add( new CharacterizationAxis() );
		
		if (charAxes.size() == 1)
		    axis = charAxes.get(0);
	    }
	}
	else if ( Utypes.isFluxAxisUtype( utypeNum ) )
	{
	    if (create)
		axis = this.createFluxAxis();
	    else
		axis = this.getFluxAxis();
	}
	else if ( Utypes.isSpatialAxisUtype( utypeNum ) )
	{
	    if (create)
		axis = this.createSpatialAxis();
	    else
		axis = this.getSpatialAxis();
	}
	else if ( Utypes.isSpectralAxisUtype( utypeNum ) )
	{
	    if (create)
		axis = this.createSpectralAxis();
	    else
		axis = this.getSpectralAxis();
	}
	else if ( Utypes.isTimeAxisUtype( utypeNum ) )
	{
	    if (create)
		axis = this.createTimeAxis();
	    else
		axis = this.getTimeAxis();
	}

        try
        {
	    if ( axis != null)
		value = axis.getValueByUtype( utypeNum, create );
        }
        catch (NullPointerException exp)
        {
            value = null;
        }

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value ) throws SedInconsistentException
    {
	CharacterizationAxis axis = null;

	if ( Utypes.isCharAxisUtype( utypeNum ) )
	{
	    List <CharacterizationAxis> charAxes = null;
	    charAxes = this.createCharacterizationAxis();

	    if (charAxes != null)
            {
		if ( charAxes.size() > 1 )
		    throw new SedInconsistentException ("Multiple copies of the Characterization Axis found. It's ambiguous as to which param should be retrieved.");

		if ( charAxes.isEmpty() )
		    charAxes.add( new CharacterizationAxis() );
		
		if ( charAxes.size() == 1 )
		    axis = charAxes.get(0);
	    }
	}
	else if ( Utypes.isFluxAxisUtype( utypeNum ) )
	{
	    axis = this.createFluxAxis();
	}
	else if ( Utypes.isSpatialAxisUtype( utypeNum ) )
	{
	    axis = this.createSpatialAxis();
	}
	else if ( Utypes.isSpectralAxisUtype( utypeNum ) )
	{
	    axis = this.createSpectralAxis();
	}
	else if ( Utypes.isTimeAxisUtype( utypeNum ) )
	{
	    axis = this.createTimeAxis();
	}

	if ( axis != null )
	    axis.setValueByUtype( utypeNum, value );

	return;
    }
}
