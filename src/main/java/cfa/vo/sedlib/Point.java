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

import java.util.List;
import java.util.ArrayList;

import cfa.vo.sedlib.common.ValidationError;
import cfa.vo.sedlib.common.ValidationErrorEnum;

/**
 * <p>Java class for point complex type.
 * 
 * 
 */
public class Point extends Group implements IPoint {

    protected SedCoord timeAxis;
    protected SedCoord spectralAxis;
    protected SedQuantity fluxAxis;
    protected SedQuantity backgroundModel;

    @Override
    public Object clone ()
    {
        Point point = (Point) super.clone();
        
        if (this.isSetTimeAxis ())
            point.timeAxis = (SedCoord)this.timeAxis.clone ();
        if (this.isSetSpectralAxis ())
            point.spectralAxis = (SedCoord)this.spectralAxis.clone ();
        if (this.isSetFluxAxis ())
            point.fluxAxis = (SedQuantity)this.fluxAxis.clone ();
        if (this.isSetBackgroundModel ())
            point.backgroundModel = (SedQuantity)this.backgroundModel.clone ();
        return point;
    }


    /**
     * Gets the value of the timeAxis property.
     * 
     * @return
     *     either null or
     *     {@link SedCoord }
     *     
     */
    @Override
    public SedCoord getTimeAxis() {
        return timeAxis;
    }

    /**
     * Creates timeAxis property if one does not exist.
     *
     * @return
     *     {@link SedCoord }
     *
     */
    @Override
    public SedCoord createTimeAxis() {
        if (this.timeAxis == null)
           this.setTimeAxis (new SedCoord ());
        return this.timeAxis;
    }


    /**
     * Sets the value of the timeAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedCoord }
     *     
     */
    @Override
    public void setTimeAxis(SedCoord value) {
        this.timeAxis = value;
    }

    @Override
    public boolean isSetTimeAxis() {
        return (this.timeAxis!= null);
    }

    /**
     * Gets the value of the spectralAxis property.
     * 
     * @return
     *     either null or
     *     {@link SedCoord }
     *     
     */
    @Override
    public SedCoord getSpectralAxis() {
        return spectralAxis;
    }

    /**
     * Creates spectralAxis property if one does not exist.
     *
     * @return
     *     {@link SedCoord }
     *
     */
    @Override
    public SedCoord createSpectralAxis() {
        if (this.spectralAxis == null)
           this.setSpectralAxis (new SedCoord ());
        return this.spectralAxis;
    }


    /**
     * Sets the value of the spectralAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedCoord }
     *     
     */
    @Override
    public void setSpectralAxis(SedCoord value) {
        this.spectralAxis = value;
    }

    @Override
    public boolean isSetSpectralAxis() {
        return (this.spectralAxis!= null);
    }

    /**
     * Gets the value of the fluxAxis property.
     * 
     * @return
     *     either null or
     *     {@link SedQuantity }
     *     
     */
    @Override
    public SedQuantity getFluxAxis() {
        return fluxAxis;
    }

    /**
     * Creates fluxAxis property if one does not exist.
     *
     * @return
     *     {@link SedQuantity }
     *
     */
    @Override
    public SedQuantity createFluxAxis() {
        if (this.fluxAxis == null)
           this.setFluxAxis (new SedQuantity ());
        return this.fluxAxis;
    }


    /**
     * Sets the value of the fluxAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedQuantity }
     *     
     */
    @Override
    public void setFluxAxis(SedQuantity value) {
        this.fluxAxis = value;
    }

    @Override
    public boolean isSetFluxAxis() {
        return (this.fluxAxis!= null);
    }

    /**
     * Gets the value of the backgroundModel property.
     * 
     * @return
     *     either null or
     *     {@link SedQuantity }
     *     
     */
    @Override
    public SedQuantity getBackgroundModel() {
        return backgroundModel;
    }

    /**
     * Creates backgroundModel property if one does not exist.
     *
     * @return
     *     {@link SedQuantity }
     *
     */
    @Override
    public SedQuantity createBackgroundModel() {
        if (this.backgroundModel == null)
           this.setBackgroundModel (new SedQuantity ());
        return this.backgroundModel;
    }


    /**
     * Sets the value of the backgroundModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedQuantity }
     *     
     */
    @Override
    public void setBackgroundModel(SedQuantity value) {
        this.backgroundModel = value;
    }

    @Override
    public boolean isSetBackgroundModel() {
        return (this.backgroundModel!= null);
    }

    /**
     * Validate the Point. The method returns true or false depending
     * on whether the Point validates.
     *
     * @return boolean; whether or not the Point is valid
     */
    public boolean validate ()
    {
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        return this.validate (errors);
    }


    /**
     * Validate the Point. The method returns true or false depending
     * on whether the Point validates. It also fills in the a list
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

        if (this.isSetFluxAxis ())
        {
            if (!this.fluxAxis.isSetValue ())
            {
            	valid = false;
                errors.add (new ValidationError (ValidationErrorEnum.MISSING_DATA_FLUXAXIS_VALUE));
            }
        }      
        else
        {
            valid = false;
            errors.add (new ValidationError (ValidationErrorEnum.MISSING_DATA_FLUXAXIS_VALUE, "Missing flux axis"));
        }

        if (this.isSetSpectralAxis ())
        {
            if (!this.spectralAxis.isSetValue ())
            {
            	valid = false;
                errors.add (new ValidationError (ValidationErrorEnum.MISSING_DATA_SPECTRALAXIS_VALUE));
            }
        }
        else
        {
            valid = false;
            errors.add (new ValidationError (ValidationErrorEnum.MISSING_DATA_SPECTRALAXIS_VALUE, "Missing spectral axis"));
        }
        
        return valid;

    }
}
