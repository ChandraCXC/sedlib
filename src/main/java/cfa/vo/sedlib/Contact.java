package cfa.vo.sedlib;

/**
 * <p>Java class for contact complex type.
 * 
 * 
 */
public class Contact
    extends Group
{

    protected TextParam name;
    protected TextParam email;

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

}
