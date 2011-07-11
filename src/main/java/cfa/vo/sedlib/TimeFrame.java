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
 * <p>Java class for timeFrame complex type.
 * 
 * 
 */
public class TimeFrame
    extends CoordFrame
{

    protected DoubleParam zero;

    @Override
    public Object clone ()
    {
        TimeFrame timeFrame = (TimeFrame) super.clone();
        

        if (this.isSetZero ())
            timeFrame.zero = (DoubleParam)this.zero.clone ();

        return timeFrame;
    }


    /**
     * Gets the value of the zero property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getZero() {
        return zero;
    }

    /**
     * Creates zero property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createZero() {
        if (this.zero == null)
           this.setZero (new DoubleParam ());
        return this.zero;
    }


    /**
     * Sets the value of the zero property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setZero(DoubleParam value) {
        this.zero = value;
    }

    public boolean isSetZero() {
        return (this.zero!= null);
    }

}
