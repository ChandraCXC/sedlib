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
 * <p>Java class for sedCoord complex type.
 * 
 * 
 */
public class SedCoord
    extends AbstractSedCoord<DoubleParam, DoubleParam>
{

    @Override
    public DoubleParam createValue() {
        if(isSetValue()) {
            return getValue();
        }

        setValue(new DoubleParam());

        return getValue();
    }

    @Override
    public DoubleParam createResolution() {
        if(isSetResolution()) {
            return getResolution();
        }

        setValue(new DoubleParam());
        return getValue();
    }

    @Override
    public Object clone() {
        SedCoord sedCoord = (SedCoord) super.clone();

        if(isSetResolution())
            sedCoord.setResolution((DoubleParam)getResolution().clone());

        if(isSetValue())
            sedCoord.setValue((DoubleParam)getValue().clone());

        return sedCoord;
    }



}
