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

import cfa.vo.sedlib.common.Utypes;
import cfa.vo.sedlib.common.ValidationError;
import cfa.vo.sedlib.common.ValidationErrorEnum;

/**
 * <p>Java class for target complex type.
 * 
 */
public class Target
    extends Group implements IAccessByUtype
{

    protected TextParam name;
    protected TextParam description;
    protected TextParam targetClass;
    protected TextParam spectralClass;
    protected DoubleParam redshift;
    protected PositionParam pos;
    protected DoubleParam varAmpl;

    @Override
    public Object clone ()
    {
        Target target = (Target) super.clone();
        
        if (this.isSetName ())
            target.name = (TextParam)this.name.clone ();
        if (this.isSetDescription ())
             target.description = (TextParam)this.description.clone ();
        if (this.isSetTargetClass ())
             target.targetClass = (TextParam)this.targetClass.clone ();
        if (this.isSetSpectralClass ())
             target.spectralClass = (TextParam)this.spectralClass.clone ();
        if (this.isSetRedshift ())
             target.redshift = (DoubleParam)this.redshift.clone ();
        if (this.isSetPos ())
             target.pos = (PositionParam)this.pos.clone ();
        if (this.isSetVarAmpl ())
             target.varAmpl = (DoubleParam)this.varAmpl.clone ();

        return target;
    }


    /**
     * Gets the value of the name property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getName() {
        return name;
    }

    /**
     * Creates name property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createName() {
        if (this.name == null)
           this.setName (new TextParam ());
        return this.name;
    }


    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setName(TextParam value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getDescription() {
        return description;
    }

    /**
     * Creates description property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createDescription() {
        if (this.description == null)
           this.setDescription (new TextParam ());
        return this.description;
    }


    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setDescription(TextParam value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the targetClass property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getTargetClass() {
        return targetClass;
    }

    /**
     * Creates targetClass property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createTargetClass() {
        if (this.targetClass == null)
           this.setTargetClass (new TextParam ());
        return this.targetClass;
    }


    /**
     * Sets the value of the targetClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setTargetClass(TextParam value) {
        this.targetClass = value;
    }

    public boolean isSetTargetClass() {
        return (this.targetClass!= null);
    }

    /**
     * Gets the value of the spectralClass property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getSpectralClass() {
        return spectralClass;
    }

    /**
     * Creates spectralClass property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createSpectralClass() {
        if (this.spectralClass == null)
           this.setSpectralClass (new TextParam ());
        return this.spectralClass;
    }


    /**
     * Sets the value of the spectralClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setSpectralClass(TextParam value) {
        this.spectralClass = value;
    }

    public boolean isSetSpectralClass() {
        return (this.spectralClass!= null);
    }

    /**
     * Gets the value of the redshift property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getRedshift() {
        return redshift;
    }

    /**
     * Creates redshift property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createRedshift() {
        if (this.redshift == null)
           this.setRedshift (new DoubleParam ());
        return this.redshift;
    }


    /**
     * Sets the value of the redshift property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setRedshift(DoubleParam value) {
        this.redshift = value;
    }

    public boolean isSetRedshift() {
        return (this.redshift!= null);
    }

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     either null or
     *     {@link PositionParam }
     *     
     */
    public PositionParam getPos() {
        return pos;
    }

    /**
     * Creates pos property if one does not exist.
     *
     * @return
     *     {@link PositionParam }
     *
     */
    public PositionParam createPos() {
        if (this.pos == null)
           this.setPos (new PositionParam ());
        return this.pos;
    }


    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionParam }
     *     
     */
    public void setPos(PositionParam value) {
        this.pos = value;
    }

    public boolean isSetPos() {
        return (this.pos!= null);
    }

    /**
     * Gets the value of the varAmpl property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getVarAmpl() {
        return varAmpl;
    }

    /**
     * Creates varAmpl property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createVarAmpl() {
        if (this.varAmpl == null)
           this.setVarAmpl (new DoubleParam ());
        return this.varAmpl;
    }


    /**
     * Sets the value of the varAmpl property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setVarAmpl(DoubleParam value) {
        this.varAmpl = value;
    }

    public boolean isSetVarAmpl() {
        return (this.varAmpl!= null);
    }

    /**
     * Validate the Target. The method returns true or false depending
     * on whether the Target validates.
     *
     * @return boolean; whether or not the Target is valid
     */
    public boolean validate ()
    {
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        return this.validate (errors);
    }

    /**
     * Validate the Target. The method returns true or false depending
     * on whether the Target validates. It also fills in the a list
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

        if (!this.isSetName ())
        {
           valid = false;
           errors.add (new ValidationError (ValidationErrorEnum.MISSING_TARGET_NAME));
        }

        return valid;
    }


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isNameUtype( utypeNum ) )
	{
	    if (create)
		value = this.createName();
	    else
		value = this.getName();
	}
	else if ( Utypes.isDescriptionUtype( utypeNum ) )
	{
	    if (create)
		value = this.createDescription();
	    else
		value = this.getDescription();
	}
	else if ( Utypes.isTargetClassUtype( utypeNum ) )
	{
	    if (create)
		value = this.createTargetClass();
	    else
		value = this.getTargetClass();
	}
	else if ( Utypes.isSpectralClassUtype( utypeNum ) )
	{
	    if (create)
		value = this.createSpectralClass();
	    else
		value = this.getSpectralClass();
	}
	else if ( Utypes.isRedshiftUtype( utypeNum ) )
	{
	    if (create)
		value = this.createRedshift();
	    else
		value = this.getRedshift();
	}
	else if ( Utypes.isPositionUtype( utypeNum ) )
	{
	    ArrayList<DoubleParam> paramList = new ArrayList<DoubleParam>();
	    DoubleParam params[];
                    
	    value = paramList;

	    if ( create )
	    {
		params = this.createPos().createValue();
		// MCD NOTE: The create method should return an array with Params.
		int n = 0;
		for ( int ii=0; ii < params.length; ii++ )
		    if ( params[ii] != null ){ n++;}

		if ( n == 0 )
		{
		    // empty (new) array returned, fill with DoubleParams
		    for ( int ii=0; ii < params.length; ii++ )
			params[ii] = new DoubleParam();
		}
	    }
	    else
	    {
		params = this.getPos().getValue();
	    }

	    if (params != null)
	    {
		paramList.ensureCapacity(params.length);
		for (DoubleParam pp : params)
		{
		    if (pp != null)
			paramList.add((DoubleParam)pp.clone());
		    else
			paramList.add(null);
		}
	    }

	}
	else if ( Utypes.isVarAmplitudeUtype( utypeNum ) )
	{
	    if (create)
		value = this.createVarAmpl();
	    else
		value = this.getVarAmpl();
	}
	

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {
	if ( Utypes.isNameUtype( utypeNum ) )
	{
	    this.setName( (TextParam)value );
	}
	else if ( Utypes.isDescriptionUtype( utypeNum ) )
	{
	    this.setDescription( (TextParam)value );
	}
	else if ( Utypes.isTargetClassUtype( utypeNum ) )
	{
	    this.setTargetClass( (TextParam)value );
	}
	else if ( Utypes.isSpectralClassUtype( utypeNum ) )
	{
	    this.setSpectralClass( (TextParam)value );
	}
	else if ( Utypes.isRedshiftUtype( utypeNum ) )
	{
	    this.setRedshift( (DoubleParam)value );
	}
	else if ( Utypes.isPositionUtype( utypeNum ) )
	{
	    // expects an ArrayList, convert to array of Param
	    List<DoubleParam> paramList = (List<DoubleParam>)value;
	    DoubleParam params[];

	    // get current value array
	    params = this.createPos().createValue();
	    if ( paramList == null )
	    {
		// null input values initializes with empty params
		// (from SetMetaParamList)
		for ( int ii=0; ii < params.length; ii++ )
		    params[ii] = new DoubleParam();
	    }
	    else
	    {
		// copy list content to value array
		for (int ii=0; ii < paramList.size(); ii++)
		{
		    if (ii == params.length)
			break;
			
		    DoubleParam item = (DoubleParam)paramList.get(ii);
		    if ( item != null)
		    {
			params[ii] = (DoubleParam)item.clone();
		    }
		    else
		    {
			params[ii] = null;
		    }
		}
	    }
	    //	    this.setPos( (PositionParam)value );
	}
	else if ( Utypes.isVarAmplitudeUtype( utypeNum ) )
	{
	    this.setVarAmpl( (DoubleParam)value );
	}
	return;
    }

}
