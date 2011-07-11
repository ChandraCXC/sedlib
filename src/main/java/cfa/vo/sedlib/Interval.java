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
 * <p>Java class for interval complex type.
 * 
 * 
 */
public class Interval
    extends Group
{

    protected DoubleParam min;
    protected DoubleParam max;

    @Override
    public Object clone ()
    {
        Interval interval = (Interval) super.clone();
        

        if (this.isSetMin ())
            interval.min = (DoubleParam)this.min.clone ();
        if (this.isSetMax ())
            interval.max = (DoubleParam)this.max.clone ();

        return interval;
    }


    /**
     * Gets the value of the min property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getMin() {
        return min;
    }

    /**
     * Creates min property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createMin() {
        if (this.min == null)
           this.setMin (new DoubleParam ());
        return this.min;
    }


    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setMin(DoubleParam value) {
        this.min = value;
    }

    public boolean isSetMin() {
        return (this.min!= null);
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getMax() {
        return max;
    }

    /**
     * Creates max property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createMax() {
        if (this.max == null)
           this.setMax (new DoubleParam ());
        return this.max;
    }


    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setMax(DoubleParam value) {
        this.max = value;
    }

    public boolean isSetMax() {
        return (this.max!= null);
    }

}
