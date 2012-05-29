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
 * <p>Java class for samplingPrecision complex type.
 * 
 * 
 */
public class SamplingPrecision
    extends Group implements IAccessByUtype
{

    protected SamplingPrecisionRefVal samplingPrecisionRefVal;
    protected DoubleParam sampleExtent;

    @Override
    public Object clone ()
    {
        SamplingPrecision smp = (SamplingPrecision) super.clone();
        

        if (this.isSetSamplingPrecisionRefVal ())
            smp.samplingPrecisionRefVal = (SamplingPrecisionRefVal)this.samplingPrecisionRefVal.clone ();
        if (this.isSetSampleExtent ())
            smp.sampleExtent = (DoubleParam)this.sampleExtent.clone ();

        return smp;
    }


    /**
     * Gets the value of the samplingPrecisionRefVal property.
     * 
     * @return
     *     either null or
     *     {@link SamplingPrecisionRefVal }
     *     
     */
    public SamplingPrecisionRefVal getSamplingPrecisionRefVal() {
        return samplingPrecisionRefVal;
    }

    /**
     * Creates samplingPrecisionRefVal property if one does not exist.
     *
     * @return
     *     {@link SamplingPrecisionRefVal }
     *
     */
    public SamplingPrecisionRefVal createSamplingPrecisionRefVal() {
        if (this.samplingPrecisionRefVal == null)
           this.setSamplingPrecisionRefVal (new SamplingPrecisionRefVal ());
        return this.samplingPrecisionRefVal;
    }


    /**
     * Sets the value of the samplingPrecisionRefVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link SamplingPrecisionRefVal }
     *     
     */
    public void setSamplingPrecisionRefVal(SamplingPrecisionRefVal value) {
        this.samplingPrecisionRefVal = value;
    }

    public boolean isSetSamplingPrecisionRefVal() {
        return (this.samplingPrecisionRefVal!= null);
    }

    /**
     * Gets the value of the sampleExtent property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getSampleExtent() {
        return sampleExtent;
    }

    /**
     * Creates sampleExtent property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createSampleExtent() {
        if (this.sampleExtent == null)
           this.setSampleExtent (new DoubleParam ());
        return this.sampleExtent;
    }


    /**
     * Sets the value of the sampleExtent property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setSampleExtent(DoubleParam value) {
        this.sampleExtent = value;
    }

    public boolean isSetSampleExtent() {
        return (this.sampleExtent!= null);
    }


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isSamplingPrecisionRefValUtype( utypeNum ) )
	{
            value = this.createSamplingPrecisionRefVal().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isSampleExtentUtype( utypeNum ) )
	{
	    if ( create )
		value = this.createSampleExtent();
	    else
		value = this.getSampleExtent();
	}

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {

	if ( Utypes.isSamplingPrecisionRefValUtype( utypeNum ) )
	{
            this.createSamplingPrecisionRefVal().setValueByUtype( utypeNum, value );
	}
	else if ( Utypes.isSampleExtentUtype( utypeNum ) )
	{
            this.setSampleExtent( (DoubleParam)value );
	}
    }

}
