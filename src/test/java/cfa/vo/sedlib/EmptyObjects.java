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

/** ********************************************************************
* File: EmptyObjects.java
*
* Author:  jcant		Created: Wed Jun 10 11:07:37 2009
*
* Update History
*   2010-12-23:  MCD  Add methods for allocating individual objects.
*                     Gutted to creation of Empty objects only
*
********************************************************************* **/
package cfa.vo.sedlib;

import java.util.List;

/**
 * Create a SED object with specified number of empty Segments
 */
public class EmptyObjects
{
    static public Sed createSED(int nsegments )
    {
	Sed sed = null;
	List<Segment> seglist = null;
	
	/* Create SED */
	sed = new Sed();
	//MCD TEMP: Test default namespace mod.
        //sed.setNamespace ("spec");

	/* Create and Add empty Segment(s) to SED */
	for (int ii = 0; ii < nsegments; ii++ )
	{
            try
            {
	        sed.addSegment( new Segment() );
            }
            catch (Exception exp)
            {
                throw new RuntimeException (exp.getMessage (), exp);
            }
	}

	return sed ;
    }
}
