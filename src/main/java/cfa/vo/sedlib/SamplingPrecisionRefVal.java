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
 * <p>Java class for samplingPrecisionRefVal complex type.
 * 
 * 
 */
public class SamplingPrecisionRefVal
    extends Group
{

    protected DoubleParam fillFactor;

    @Override
    public Object clone ()
    {
        SamplingPrecisionRefVal smp = (SamplingPrecisionRefVal) super.clone();
        

        if (this.isSetFillFactor ())
            smp.fillFactor = (DoubleParam)this.fillFactor.clone ();

        return smp;
    }


    /**
     * Gets the value of the fillFactor property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getFillFactor() {
        return fillFactor;
    }

    /**
     * Creates fillFactor property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createFillFactor() {
        if (this.fillFactor == null)
           this.setFillFactor (new DoubleParam ());
        return this.fillFactor;
    }


    /**
     * Sets the value of the fillFactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setFillFactor(DoubleParam value) {
        this.fillFactor = value;
    }

    public boolean isSetFillFactor() {
        return (this.fillFactor!= null);
    }

}
