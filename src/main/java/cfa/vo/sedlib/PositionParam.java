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

import java.util.Arrays;


/**
 * <p>Java class for positionParam complex type.
 * 
 * 
 */
public class PositionParam implements Cloneable {

    protected DoubleParam value[] = null;

    @Override
    public Object clone ()
    {
        PositionParam positionParam = null;
        try
        {
            positionParam = (PositionParam) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // this should never happen
            throw new InternalError(e.toString());
        }

        if (this.isSetValue ())
        {
            positionParam.setValue (new DoubleParam[2]);
            for (int ii=0; ii<this.value.length; ii++)
                positionParam.value[ii] = this.value[ii];
        }
        return positionParam;
    }


    /**
     * Gets the value property.
     *
     * @return
     *     either null or DoubleParam[]
     *     {@link DoubleParam}
     *
     */
    public DoubleParam[] getValue() {
//        return Arrays.copyOf(value, value.length);
        return value;
    }

    /**
     * Creates value property if one does not exist.
     *
     * @return
     *     {@link DoubleParam}[]
     *
     */
    public DoubleParam[] createValue() {
        if (this.value == null)
           this.setValue (new DoubleParam[2]);
//        return Arrays.copyOf(value, value.length);
        return value;
    }


    public boolean isSetValue() {
        return (this.value!= null);
    }

    public void setValue(DoubleParam[] value) {
//        this.value = Arrays.copyOf(value, value.length);
        this.value = value;
    }

}
