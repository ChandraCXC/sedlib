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

import cfa.vo.sedlib.common.Utypes;

/**
 * <p>Java class for accuracy complex type.
 * 
 * 
 */
public class Accuracy
    extends Group implements IAccessByUtype
{

    protected DoubleParam binLow;
    protected DoubleParam binHigh;
    protected DoubleParam binSize;
    protected DoubleParam statError;
    protected DoubleParam statErrLow;
    protected DoubleParam statErrHigh;
    protected DoubleParam sysError;
    protected DoubleParam confidence;

    public Accuracy () {}

    @Override
    public Object clone ()
    {
        Accuracy accuracy = (Accuracy) super.clone();

        if (this.isSetBinLow ())
            accuracy.binLow = (DoubleParam)this.binLow.clone ();
        if (this.isSetBinHigh ())
            accuracy.binHigh = (DoubleParam)this.binHigh.clone ();
        if (this.isSetBinSize ())
            accuracy.binSize = (DoubleParam)this.binSize.clone ();
        if (this.isSetStatError ())
            accuracy.statError = (DoubleParam)this.statError.clone ();
        if (this.isSetStatErrLow ())
            accuracy.statErrLow = (DoubleParam)this.statErrLow.clone ();
        if (this.isSetStatErrHigh ())
            accuracy.statErrHigh = (DoubleParam)this.statErrHigh.clone ();
        if (this.isSetSysError ())
            accuracy.sysError = (DoubleParam)this.sysError.clone ();
        if (this.isSetConfidence ())
            accuracy.confidence = (DoubleParam)this.confidence.clone ();

        return accuracy;
    }


     
    /**
     * Gets the value of the binLow property.
     * 
     * @return
     *     either null or 
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getBinLow() {
        return binLow;
    }

    /**
     * Creates binLow property if one doesn't exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createBinLow() {
        if (this.binLow == null)
           setBinLow (new DoubleParam ());
        return binLow;
    }


    /**
     * Sets the value of the binLow property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setBinLow(DoubleParam value) {
        this.binLow = value;
    }

    public boolean isSetBinLow() {
        return (this.binLow!= null);
    }

    /**
     * Gets the value of the binHigh property.
     * 
     * @return
     *     either null or 
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getBinHigh() {
        return binHigh;
    }

    /**
     * Creates binHigh property if one doesn't exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createBinHigh() {
        if (this.binHigh == null)
           setBinHigh (new DoubleParam ());
        return binHigh;
    }


    /**
     * Sets the value of the binHigh property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setBinHigh(DoubleParam value) {
        this.binHigh = value;
    }

    public boolean isSetBinHigh() {
        return (this.binHigh!= null);
    }

    /**
     * Gets the value of the binSize property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getBinSize() {
        return binSize;
    }

    /**
     * Creates binSize property if one doesn't exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createBinSize() {
        if (this.binSize == null)
           setBinSize (new DoubleParam ());
        return binSize;
    }


    /**
     * Sets the value of the binSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setBinSize(DoubleParam value) {
        this.binSize = value;
    }

    public boolean isSetBinSize() {
        return (this.binSize!= null);
    }

    /**
     * Gets the value of the statError property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getStatError() {
        return statError;
    }

    /**
     * Creates statError property if one doesn't exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createStatError() {
        if (this.statError == null)
           setStatError (new DoubleParam ());
        return statError;
    }


    /**
     * Sets the value of the statError property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setStatError(DoubleParam value) {
        this.statError = value;
    }

    public boolean isSetStatError() {
        return (this.statError!= null);
    }

    /**
     * Gets the value of the statErrLow property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getStatErrLow() {
        return statErrLow;
    }

    /**
     * Creates statErrLow property if one doesn't exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createStatErrLow() {
        if (this.statErrLow == null)
           setStatErrLow (new DoubleParam ());
        return statErrLow;
    }


    /**
     * Sets the value of the statErrLow property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setStatErrLow(DoubleParam value) {
        this.statErrLow = value;
    }

    public boolean isSetStatErrLow() {
        return (this.statErrLow!= null);
    }

    /**
     * Gets the value of the statErrHigh property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getStatErrHigh() {
        return statErrHigh;
    }

    /**
     * Creates statErrHigh property if one doesn't exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createStatErrHigh() {
        if (this.statErrHigh == null)
           this.setStatErrHigh (new DoubleParam ());
        return this.statErrHigh;
    }


    /**
     * Sets the value of the statErrHigh property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setStatErrHigh(DoubleParam value) {
        this.statErrHigh = value;
    }

    public boolean isSetStatErrHigh() {
        return (this.statErrHigh!= null);
    }

    /**
     * Gets the value of the sysError property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getSysError() {
        return sysError;
    }

    /**
     * Creates sysError property if one doesn't exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createSysError() {
        if (this.sysError == null)
           setSysError (new DoubleParam ());
        return sysError;
    }


    /**
     * Sets the value of the sysError property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setSysError(DoubleParam value) {
        this.sysError = value;
    }

    public boolean isSetSysError() {
        return (this.sysError!= null);
    }

    /**
     * Gets the value of the confidence property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getConfidence() {
        return confidence;
    }

    /**
     * Creates confidence property if one doesn't exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createConfidence() {
        if (this.confidence == null)
           setConfidence (new DoubleParam ());
        return confidence;
    }


    /**
     * Sets the value of the confidence property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setConfidence(DoubleParam value) {
        this.confidence = value;
    }

    public boolean isSetConfidence() {
        return (this.confidence!= null);
    }

    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************

    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isBinLowUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createBinLow();
	    else
		value = this.getBinLow();
	}
	else if ( Utypes.isBinHighUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createBinHigh();
	    else
		value = this.getBinHigh();
	}
	else if ( Utypes.isBinSizeUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createBinSize();
	    else
		value = this.getBinSize();
	}
	else if ( Utypes.isStatErrorUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createStatError();
	    else
		value = this.getStatError();
	}
	else if ( Utypes.isStatErrorLowUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createStatErrLow();
	    else
		value = this.getStatErrLow();
	}
	else if ( Utypes.isStatErrorHighUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createStatErrHigh();
	    else
		value = this.getStatErrHigh();
	}
	else if ( Utypes.isSysErrorUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createSysError();
	    else
		value = this.getSysError();
	}
	else if ( Utypes.isConfidenceUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createConfidence();
	    else
		value = this.getConfidence();
	}
	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {
	if ( Utypes.isBinLowUtype( utypeNum ) )
	{
	    this.setBinLow( (DoubleParam)value );
	}
	else if ( Utypes.isBinHighUtype( utypeNum ) )
	{
	    this.setBinHigh( (DoubleParam)value );
	}
	else if ( Utypes.isBinSizeUtype( utypeNum ) )
	{
	    this.setBinSize( (DoubleParam)value );
	}
	else if ( Utypes.isStatErrorUtype( utypeNum ) )
	{
	    this.setStatError( (DoubleParam)value );
	}
	else if ( Utypes.isStatErrorLowUtype( utypeNum ) )
	{
	    this.setStatErrLow( (DoubleParam)value );
	}
	else if ( Utypes.isStatErrorHighUtype( utypeNum ) )
	{
	    this.setStatErrHigh( (DoubleParam)value );
	}
	else if ( Utypes.isSysErrorUtype( utypeNum ) )
	{
	    this.setSysError( (DoubleParam)value );
	}
	else if ( Utypes.isConfidenceUtype( utypeNum ) )
	{
	    this.setConfidence( (DoubleParam)value );
	}
	return;
    }

}
