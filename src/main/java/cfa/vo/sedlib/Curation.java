package cfa.vo.sedlib;

/**
 * <p>Java class for curation complex type.
 * 
 * 
 */
public class Curation
    extends Group
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

}
