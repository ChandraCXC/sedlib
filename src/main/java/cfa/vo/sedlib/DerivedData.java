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

/**
 * <p>Java class for derivedData complex type.
 * 
 * 
 */
public class DerivedData
    extends Group
{

    protected DoubleParam snr;
    protected DoubleParam varAmpl;
    protected SedQuantity redshift;

    @Override
    public Object clone ()
    {
        DerivedData derivedData = (DerivedData) super.clone();
        

        if (this.isSetSNR ())
            this.snr = (DoubleParam)this.snr.clone ();
        if (this.isSetVarAmpl ())
            this.varAmpl = (DoubleParam)this.varAmpl.clone ();
        if (this.isSetRedshift ())
            this.redshift = (SedQuantity)this.redshift.clone ();

        return derivedData;
    }


    /**
     * Gets the value of the snr property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getSNR() {
        return snr;
    }

    /**
     * Creates SNR property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createSNR() {
        if (this.snr == null)
           this.setSNR (new DoubleParam ());
        return this.snr;
    }


    /**
     * Sets the value of the snr property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setSNR(DoubleParam value) {
        this.snr = value;
    }

    public boolean isSetSNR() {
        return (this.snr!= null);
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
     * Gets the value of the redshift property.
     * 
     * @return
     *     either null or
     *     {@link SedQuantity }
     *     
     */
    public SedQuantity getRedshift() {
        return redshift;
    }

    /**
     * Creates redshift property if one does not exist.
     *
     * @return
     *     {@link SedQuantity }
     *
     */
    public SedQuantity createRedshift() {
        if (this.redshift == null)
           this.setRedshift (new SedQuantity ());
        return this.redshift;
    }


    /**
     * Sets the value of the redshift property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedQuantity }
     *     
     */
    public void setRedshift(SedQuantity value) {
        this.redshift = value;
    }

    public boolean isSetRedshift() {
        return (this.redshift!= null);
    }

}
