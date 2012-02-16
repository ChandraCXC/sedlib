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

import java.util.List;
import java.util.ArrayList;

import cfa.vo.sedlib.common.Utypes;
import cfa.vo.sedlib.common.ValidationError;
import cfa.vo.sedlib.common.ValidationErrorEnum;

/**
 * <p>Java class for curation complex type.
 * 
 * 
 */
public class Curation
    extends Group implements IAccessByUtype
{

    protected TextParam publisher;
    protected TextParam publisherID;
    protected TextParam reference;
    protected TextParam version;
    protected Contact contact;
    protected TextParam rights;
    protected DateParam date;
    protected TextParam publisherDID;

    @Override
    public Object clone ()
    {
        Curation curation = (Curation) super.clone();
        

        if (this.isSetPublisher ())
            curation.publisher = (TextParam)this.publisher.clone ();
        if (this.isSetPublisherID ())
            curation.publisherID = (TextParam)this.publisherID.clone ();
        if (this.isSetReference ())
            curation.reference = (TextParam)this.reference.clone ();
        if (this.isSetVersion ())
            curation.version = (TextParam)this.version.clone ();
        if (this.isSetContact ())
            curation.contact = (Contact)this.contact.clone ();
        if (this.isSetRights ())
            curation.rights = (TextParam)this.rights.clone ();
        if (this.isSetDate ())
            curation.date = (DateParam)this.date.clone ();
        if (this.isSetPublisherDID ())
            curation.publisherDID = (TextParam)this.publisherDID.clone ();


        return curation;
    }


    /**
     * Gets the value of the publisher property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getPublisher() {
        return publisher;
    }

    /**
     * Creates publisher property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createPublisher() {
        if (this.publisher == null)
           this.setPublisher (new TextParam ());
        return this.publisher;
    }


    /**
     * Sets the value of the publisher property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setPublisher(TextParam value) {
        this.publisher = value;
    }

    public boolean isSetPublisher() {
        return (this.publisher!= null);
    }

    /**
     * Gets the value of the publisherID property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getPublisherID() {
        return publisherID;
    }

    /**
     * Creates publisherID property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createPublisherID() {
        if (this.publisherID == null)
           this.setPublisherID (new TextParam ());
        return this.publisherID;
    }


    /**
     * Sets the value of the publisherID property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setPublisherID(TextParam value) {
        this.publisherID = value;
    }

    public boolean isSetPublisherID() {
        return (this.publisherID!= null);
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getReference() {
        return reference;
    }

    /**
     * Creates reference property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createReference() {
        if (this.reference == null)
           this.setReference (new TextParam ());
        return this.reference;
    }


    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setReference(TextParam value) {
        this.reference = value;
    }

    public boolean isSetReference() {
        return (this.reference!= null);
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getVersion() {
        return version;
    }

    /**
     * Creates version property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createVersion() {
        if (this.version == null)
           this.setVersion (new TextParam ());
        return this.version;
    }


    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setVersion(TextParam value) {
        this.version = value;
    }

    public boolean isSetVersion() {
        return (this.version!= null);
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     either null or
     *     {@link Contact }
     *     
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Creates contact property if one does not exist.
     *
     * @return
     *     {@link Contact }
     *
     */
    public Contact createContact() {
        if (this.contact== null)
           this.setContact (new Contact ());
        return this.contact;
    }


    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setContact(Contact value) {
        this.contact = value;
    }

    public boolean isSetContact() {
        return (this.contact!= null);
    }

    /**
     * Gets the value of the rights property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getRights() {
        return rights;
    }

    /**
     * Creates rights property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createRights() {
        if (this.rights == null)
           this.setRights (new TextParam ());
        return this.rights;
    }


    /**
     * Sets the value of the rights property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setRights(TextParam value) {
        this.rights = value;
    }

    public boolean isSetRights() {
        return (this.rights!= null);
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     either null or
     *     {@link DateParam }
     *     
     */
    public DateParam getDate() {
        return date;
    }

    /**
     * Creates date property if one does not exist.
     *
     * @return
     *     {@link DateParam }
     *
     */
    public DateParam createDate() {
        if (this.date == null)
           this.setDate (new DateParam ());
        return this.date;
    }


    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateParam }
     *     
     */
    public void setDate(DateParam value) {
        this.date = value;
    }

    public boolean isSetDate() {
        return (this.date!= null);
    }

    /**
     * Gets the value of the publisherDID property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getPublisherDID() {
        return publisherDID;
    }

    /**
     * Creates publisherDID property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createPublisherDID() {
        if (this.publisherDID == null)
           this.setPublisherDID (new TextParam ());
        return this.publisherDID;
    }


    /**
     * Sets the value of the publisherDID property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setPublisherDID(TextParam value) {
        this.publisherDID = value;
    }

    public boolean isSetPublisherDID() {
        return (this.publisherDID!= null);
    }

    /**
     * Validate the Curation. The method returns true or false depending
     * on whether the Curation validates. 
     *
     * @return boolean; whether or not the Curation is valid
     */
    public boolean validate ()
    {
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        return this.validate (errors);
    }

    /**
     * Validate the Curation. The method returns true or false depending
     * on whether the Curation validates. It also fills in the a list
     * of errors that occurred when validating
     *
     * @param errors
     *    List<ValidationError>
     *    {@link ValidationError}
     * @return boolean; whether or not the Sed is valid
     */
    public boolean validate (List<ValidationError> errors)
    {
        boolean valid = true;

        if (!this.isSetPublisher ())
        {
           valid = false;
           errors.add (new ValidationError (ValidationErrorEnum.MISSING_CURATION_PUB));
        }

        return valid;
    }

    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************
    @Override
    public Object getValueByUtype( int utypeNum, boolean create )
    {
	Object value = null;

	if ( Utypes.isContactUtype( utypeNum ) )
	{
	    value = this.createContact().getValueByUtype( utypeNum, create );
	}
	else if ( Utypes.isDateUtype( utypeNum ) )
	{
	    if (create)
		value = this.createDate();
	    else
		value = this.getDate();
	}
	else if ( Utypes.isPublisherUtype( utypeNum ) )
	{
	    if (create)
		value = this.createPublisher();
	    else
		value = this.getPublisher();
	}
	else if ( Utypes.isPublisherIDUtype( utypeNum ) )
	{
	    if (create)
		value = this.createPublisherID();
	    else
		value = this.getPublisherID();
	}
	else if ( Utypes.isPublisherDIDUtype( utypeNum ) )
	{
	    if (create)
		value = this.createPublisherDID();
	    else
		value = this.getPublisherDID();
	}
	else if ( Utypes.isReferenceUtype( utypeNum ) )
	{
	    if (create)
		value = this.createReference();
	    else
		value = this.getReference();
	}
	else if ( Utypes.isRightsUtype( utypeNum ) )
	{
	    if (create)
		value = this.createRights();
	    else
		value = this.getRights();
	}
	else if ( Utypes.isVersionUtype( utypeNum ) )
	{
	    if (create)
		value = this.createVersion();
	    else
		value = this.getVersion();
	}

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value )
    {

	if ( Utypes.isContactUtype( utypeNum ) )
	{
	    this.createContact().setValueByUtype( utypeNum, value );
	}
	else if ( Utypes.isDateUtype( utypeNum ) )
	{
	    this.setDate( (DateParam)value );
	}
	else if ( Utypes.isPublisherUtype( utypeNum ) )
	{
	    this.setPublisher( (TextParam)value );
	}
	else if ( Utypes.isPublisherIDUtype( utypeNum ) )
	{
	    this.setPublisherID( (TextParam)value );
	}
	else if ( Utypes.isPublisherDIDUtype( utypeNum ) )
	{
	    this.setPublisherDID( (TextParam)value );
	}
	else if ( Utypes.isReferenceUtype( utypeNum ) )
	{
	    this.setReference( (TextParam)value );
	}
	else if ( Utypes.isRightsUtype( utypeNum ) )
	{
	    this.setRights( (TextParam)value );
	}
	else if ( Utypes.isVersionUtype( utypeNum ) )
	{
	    this.setVersion( (TextParam)value );
	}

	return;
    }


}
