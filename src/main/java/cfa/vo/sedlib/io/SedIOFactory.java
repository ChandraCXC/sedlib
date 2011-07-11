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

/***********************************************************************
*
* File: io/SedIOFactory.java
*
* Author:  jmiller      Created: Wed Feb 23 12:14:17 2011
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/

package cfa.vo.sedlib.io;

/**
    A factory for retrieving an appropriate SED library deserializer and 
    serializer.
*/
public class SedIOFactory
{
    public SedIOFactory () 
    {
    }

    /**
     * Create a deserializer based on the specified format 
     * @param informat
     *   {@link SedFormat}
     * @return
     *   possible deserializers include
     *   {@link FitsDeserializer}
     *   {@link VOTableDeserializer}
     * @throws {@link UnsupportedOperationException}
     */
    static public ISedDeserializer createDeserializer (SedFormat informat)
    {
        ISedDeserializer deserializer = null;
        switch (informat){
        case FITS:
            deserializer = new FitsDeserializer();
            break;
        case VOT:
            deserializer = new VOTableDeserializer();
            break;
//        case XML:
//            deserializer = new XMLDeserializer();
//            break;
        default:
            throw new UnsupportedOperationException("This format is not supported by the current implementation");
        }

        return deserializer;
    }

    /**
     * Create a serializer based on the specified format
     * @param informat
     *   {@link SedFormat}
     * @return
     *   possible serializers include
     *   {@link FitsSerializer}
     *   {@link VOTableSerializer}
     */
    static public ISedSerializer createSerializer (SedFormat informat)
    {
        ISedSerializer serializer = null;
        switch (informat){
        case FITS:
            serializer = new FitsSerializer();
            break;
        case VOT:
            serializer = new VOTableSerializer();
            break;
        case XML:
            serializer = new XMLSerializer();
            break;
        default:
            break;
        }

        return serializer;
    }

}
