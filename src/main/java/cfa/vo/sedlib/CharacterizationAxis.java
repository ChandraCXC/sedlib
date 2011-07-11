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
 * <p>Java class for characterizationAxis complex type.
 * 
 * 
 */
public class CharacterizationAxis
    extends Group
{

    protected CoordSys coordSystem;
    protected Coverage coverage;
    protected DoubleParam resolution;
    protected Accuracy accuracy;
    protected SamplingPrecision samplingPrecision;
    protected TextParam calibration;
    protected String name;
    protected String ucd;
    protected String unit;

    @Override
    public Object clone ()
    {
        CharacterizationAxis charAxis = (CharacterizationAxis) super.clone();

        if (this.isSetCoordSystem ())
            charAxis.coordSystem = (CoordSys)this.coordSystem.clone ();
        if (this.isSetCoverage ())
            charAxis.coverage = (Coverage)this.coverage.clone ();
        if (this.isSetResolution ())
            charAxis.resolution = (DoubleParam)this.resolution.clone ();
        if (this.isSetAccuracy ())
            charAxis.accuracy = (Accuracy)this.accuracy.clone ();
        if (this.isSetSamplingPrecision ())
            charAxis.samplingPrecision = (SamplingPrecision)this.samplingPrecision.clone ();
        if (this.isSetCalibration ())
            charAxis.calibration = (TextParam)this.calibration.clone ();

        return charAxis;
    }


    /**
     * Gets the value of the coordSystem property.
     * 
     * @return
     *     either null or
     *     {@link CoordSys }
     *     
     */
    public CoordSys getCoordSystem() {
        return coordSystem;
    }

    /**
     * Create the coordSystem property if it does not exist.
     *
     * @return
     *     {@link CoordSys }
     *
     */
    public CoordSys createCoordSystem() {

        if (this.coordSystem == null)
           this.setCoordSystem (new CoordSys ());
        return this.coordSystem;
    }


    /**
     * Sets the value of the coordSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordSys }
     *     
     */
    public void setCoordSystem(CoordSys value) {
        this.coordSystem = value;
    }

    public boolean isSetCoordSystem() {
        return (this.coordSystem!= null);
    }

    /**
     * Gets the value of the coverage property.
     * 
     * @return
     *     either null or
     *     {@link Coverage }
     *     
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * Create the coverage property if it does not exist.
     *
     * @return
     *     {@link Coverage }
     *
     */
    public Coverage createCoverage() {

        if (this.coverage == null)
           this.setCoverage (new Coverage ());
        return this.coverage;
    }


    /**
     * Sets the value of the coverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Coverage }
     *     
     */
    public void setCoverage(Coverage value) {
        this.coverage = value;
    }

    public boolean isSetCoverage() {
        return (this.coverage!= null);
    }

    /**
     * Gets the value of the resolution property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getResolution() {
        return resolution;
    }

    /**
     * Create the resolution property if it does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createResolution() {

        if (this.resolution == null)
           this.setResolution (new DoubleParam ());
        return this.resolution;
    }


    /**
     * Sets the value of the resolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setResolution(DoubleParam value) {
        this.resolution = value;
    }

    public boolean isSetResolution() {
        return (this.resolution!= null);
    }

    /**
     * Gets the value of the accuracy property.
     * 
     * @return
     *     either null or
     *     {@link Accuracy }
     *     
     */
    public Accuracy getAccuracy() {
        return accuracy;
    }

    /**
     * Create the accuracy property if it does not exist.
     *
     * @return
     *     {@link Accuracy }
     *
     */
    public Accuracy createAccuracy() {

        if (this.accuracy == null) 
           this.setAccuracy (new Accuracy ());
        return this.accuracy;
    }


    /**
     * Sets the value of the accuracy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Accuracy }
     *     
     */
    public void setAccuracy(Accuracy value) {
        this.accuracy = value;
    }

    public boolean isSetAccuracy() {
        return (this.accuracy!= null);
    }

    /**
     * Gets the value of the samplingPrecision property.
     * 
     * @return
     *     either null or
     *     {@link SamplingPrecision }
     *     
     */
    public SamplingPrecision getSamplingPrecision() {
        return samplingPrecision;
    }

    /**
     * Create the samplingPrecision property if it does not exist.
     *
     * @return
     *     {@link SamplingPrecision }
     *
     */
    public SamplingPrecision createSamplingPrecision() {

        if (this.samplingPrecision == null)
           this.setSamplingPrecision (new SamplingPrecision ());
        return this.samplingPrecision;
    }


    /**
     * Sets the value of the samplingPrecision property.
     * 
     * @param value
     *     allowed object is
     *     {@link SamplingPrecision }
     *     
     */
    public void setSamplingPrecision(SamplingPrecision value) {
        this.samplingPrecision = value;
    }

    public boolean isSetSamplingPrecision() {
        return (this.samplingPrecision!= null);
    }

    /**
     * Gets the value of the calibration property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getCalibration() {
        return calibration;
    }

    /**
     * Create the calibration property if it does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createCalibration() {

        if (this.calibration == null)
           this.setCalibration (new TextParam ());
        return this.calibration;
    }


    /**
     * Sets the value of the calibration property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setCalibration(TextParam value) {
        this.calibration = value;
    }

    public boolean isSetCalibration() {
        return (this.calibration!= null);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Gets the value of the ucd property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getUcd() {
        return ucd;
    }

    /**
     * Sets the value of the ucd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUcd(String value) {
        this.ucd = value;
    }

    public boolean isSetUcd() {
        return (this.ucd!= null);
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    public boolean isSetUnit() {
        return (this.unit!= null);
    }

}
