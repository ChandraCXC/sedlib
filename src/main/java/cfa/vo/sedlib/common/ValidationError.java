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
 * This class provides provides a method to expand the validation error 
 * enumeration by adding notes. 
 *
 *
 */
public class ValidationError
{

    protected ValidationErrorEnum error;
    protected String note = "";

    public ValidationError (ValidationErrorEnum error) 
    {
        this.error = error;
    };

    public ValidationError (ValidationErrorEnum error, String note)
    {
        this.error = error;
        this.note = note;
    };

    public void addNote (String note)
    {
        this.note = note;
    }

    public ValidationErrorEnum getError ()
    {
        return this.error;
    }

    public String getErrorMessage ()
    {
        String message = error.getErrorMessage ();

        if (this.note.length () > 0)
            message = message +" ("+note+")";

        return message;
    }

}

