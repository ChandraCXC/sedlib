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
 * <p>Java class for redshiftFrame complex type.
 * 
 * 
 */
public class RedshiftFrame
    extends CoordFrame
{

    protected String dopplerDefinition;

    /**
     * Gets the value of the dopplerDefinition property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getDopplerDefinition() {
        return dopplerDefinition;
    }

    /**
     * Sets the value of the dopplerDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDopplerDefinition(String value) {
        this.dopplerDefinition = value;
    }

    public boolean isSetDopplerDefinition() {
        return (this.dopplerDefinition!= null);
    }

}
