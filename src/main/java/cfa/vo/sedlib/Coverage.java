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
 * <p>Java class for coverage complex type.
 * 
 * 
 */
public class Coverage
    extends Group
{

    protected CoverageLocation location;
    protected CoverageBounds bounds;
    protected CoverageSupport support;

    @Override
    public Object clone ()
    {
        Coverage coverage = (Coverage) super.clone();
        

        if (this.isSetLocation ())
            coverage.location = (CoverageLocation)this.location.clone ();
        if (this.isSetBounds ())
            coverage.bounds = (CoverageBounds)this.bounds.clone ();
        if (this.isSetSupport ())
            coverage.support = (CoverageSupport)this.support.clone ();

        return coverage;
    }


    /**
     * Gets the value of the location property.
     * 
     * @return
     *     either null or
     *     {@link CoverageLocation }
     *     
     */
    public CoverageLocation getLocation() {
        return location;
    }

    /**
     * Create the location property if one does not exist.
     *
     * @return
     *     {@link CoverageLocation }
     *
     */
    public CoverageLocation createLocation() {
        if (this.location == null)
            this.setLocation (new CoverageLocation ());
        return location;
    }


    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoverageLocation }
     *     
     */
    public void setLocation(CoverageLocation value) {
        this.location = value;
    }

    public boolean isSetLocation() {
        return (this.location!= null);
    }

    /**
     * Gets the value of the bounds property.
     * 
     * @return
     *     either null or
     *     {@link CoverageBounds }
     *     
     */
    public CoverageBounds getBounds() {
        return bounds;
    }

    /**
     * Create the bounds property if one does not exist.
     *
     * @return
     *     {@link CoverageBounds }
     *
     */
    public CoverageBounds createBounds() {
        if (this.bounds == null)
            this.setBounds (new CoverageBounds ());
        return this.bounds;
    }


    /**
     * Sets the value of the bounds property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoverageBounds }
     *     
     */
    public void setBounds(CoverageBounds value) {
        this.bounds = value;
    }

    public boolean isSetBounds() {
        return (this.bounds!= null);
    }

    /**
     * Gets the value of the support property.
     * 
     * @return
     *     either null or
     *     {@link CoverageSupport }
     *     
     */
    public CoverageSupport getSupport() {
        return support;
    }

    /**
     * Create the support property if one does not exist.
     *
     * @return
     *     {@link CoverageSupport }
     *
     */
    public CoverageSupport createSupport() {
        if (this.support == null)
            this.setSupport (new CoverageSupport ());
        return this.support;
    }


    /**
     * Sets the value of the support property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoverageSupport }
     *     
     */
    public void setSupport(CoverageSupport value) {
        this.support = value;
    }

    public boolean isSetSupport() {
        return (this.support!= null);
    }

}
