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

import cfa.vo.sedlib.common.Utypes;

/**
 * <p>Java class for contact complex type.
 * 
 * 
 */
public class Contact
    extends Group implements IAccessByUtype
{

    protected TextParam name;
    protected TextParam email;

    @Override
    public Object clone ()
    {
        Contact contact = (Contact) super.clone();
        
        if (this.isSetName ())
            contact.name = (TextParam)this.name.clone ();
        if (this.isSetEmail ())
            contact.email = (TextParam)this.email.clone ();

        return contact;
    }


    /**
     * Gets the value of the name property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getName() {
        return name;
    }

    /**
     * Create the name property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createName() {
        if (this.name == null)
           this.setName (new TextParam ());
        return this.name;
    }


    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setName(TextParam value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getEmail() {
        return email;
    }

    /**
     * Create the email property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createEmail() {
        if (this.email == null)
           this.setEmail (new TextParam ());
        return this.email;
    }


    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setEmail(TextParam value) {
        this.email = value;
    }

    public boolean isSetEmail() {
        return (this.email!= null);
    }


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isNameUtype( utypeNum ) )
	{
	    if (create)
		value = this.createName();
	    else
		value = this.getName();
	}
	else if ( Utypes.isEmailUtype( utypeNum ) )
	{
	    if (create)
		value = this.createEmail();
	    else
		value = this.getEmail();
	}

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {

	if ( Utypes.isNameUtype( utypeNum ) )
	{
	    this.setName( (TextParam)value );
	}
	else if ( Utypes.isEmailUtype( utypeNum ) )
	{
	    this.setEmail( (TextParam)value );
	}
    }



}
