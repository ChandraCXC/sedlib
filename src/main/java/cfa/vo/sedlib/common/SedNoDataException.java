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

package cfa.vo.sedlib.common;

/**
 *  A wrapper for Exception
 */
public class SedNoDataException extends SedException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SedNoDataException()
    {
    }

    public SedNoDataException(String s)
    {
	super(s);
    }

    public SedNoDataException(String s, Throwable t)
    {
	super(s, t);
    }


}//end SedException
