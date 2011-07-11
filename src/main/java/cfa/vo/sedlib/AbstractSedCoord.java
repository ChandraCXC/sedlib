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
 * <p>Java class for sedBaseCoord complex type.
 * 
 * 
 */
public abstract class AbstractSedCoord<V, R>
    extends Group implements Cloneable
{

    private V value;
    private Accuracy accuracy;
    private R resolution;

        /**
     * Gets the value property.
     *
     * @return
     *     either null or
     *     {@link DoubleParam }
     *
     */
    public V getValue() {
        return value;
    }

    /**
     * Creates value property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public abstract V createValue();

    public boolean isSetValue() {
        return this.value!= null;
    }

    public void setValue(V value) {
        this.value = value;
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
     * Creates accuracy property if one does not exist.
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
     * Gets the value of the resolution property.
     *
     * @return
     *     either null or
     *     {@link DoubleParam }
     *
     */
    public R getResolution() {
        return resolution;
    }

    /**
     * Creates resolution property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public abstract R createResolution();


    /**
     * Sets the value of the resolution property.
     *
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *
     */
    public void setResolution(R value) {
        this.resolution = value;
    }

    public boolean isSetResolution() {
        return (this.resolution!= null);
    }

    @Override
    public Object clone() {
        AbstractSedCoord sedCoord = (AbstractSedCoord) super.clone();

        if(isSetAccuracy())
            sedCoord.accuracy = (Accuracy) accuracy.clone();
        
        return sedCoord;
    }

}
