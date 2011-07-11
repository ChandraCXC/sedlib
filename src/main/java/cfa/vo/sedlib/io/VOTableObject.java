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

package cfa.vo.sedlib.io;

import java.util.ArrayList;
import java.util.List;

import uk.ac.starlink.votable.VOElement;


/**
 *  Stores VOTable data including a VOElement and a list of star tables. The
 *  class contains the root of the document and then a startable for each
 *  table inside the document.
 */
public class VOTableObject
{

    VOElement root;
    List<SedStarTable> starTableList;

    public VOTableObject ()
    {
        this.root = null;
        this.starTableList = new ArrayList<SedStarTable>();
    }

}
