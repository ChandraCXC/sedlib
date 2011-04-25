package cfa.vo.sedlib;

/**
 * <p>Java class for target complex type.
 * 
 */
public class Target
    extends Group
{

    protected TextParam name;
    protected TextParam description;
    protected TextParam targetClass;
    protected TextParam spectralClass;
    protected DoubleParam redshift;
    protected PositionParam pos;
    protected DoubleParam varAmpl;

    @Override
    public Object clone ()
    {
        Target target = (Target) super.clone();
        
        if (this.isSetName ())
            target.name = (TextParam)this.name.clone ();
        if (this.isSetDescription ())
             target.description = (TextParam)this.description.clone ();
        if (this.isSetTargetClass ())
             target.targetClass = (TextParam)this.targetClass.clone ();
        if (this.isSetSpectralClass ())
             target.spectralClass = (TextParam)this.spectralClass.clone ();
        if (this.isSetRedshift ())
             target.redshift = (DoubleParam)this.redshift.clone ();
        if (this.isSetPos ())
             target.pos = (PositionParam)this.pos.clone ();
        if (this.isSetVarAmpl ())
             target.varAmpl = (DoubleParam)this.varAmpl.clone ();

        return target;
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
     * Creates name property if one does not exist.
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
     * Gets the value of the description property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getDescription() {
        return description;
    }

    /**
     * Creates description property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createDescription() {
        if (this.description == null)
           this.setDescription (new TextParam ());
        return this.description;
    }


    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setDescription(TextParam value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the targetClass property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getTargetClass() {
        return targetClass;
    }

    /**
     * Creates targetClass property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createTargetClass() {
        if (this.targetClass == null)
           this.setTargetClass (new TextParam ());
        return this.targetClass;
    }


    /**
     * Sets the value of the targetClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setTargetClass(TextParam value) {
        this.targetClass = value;
    }

    public boolean isSetTargetClass() {
        return (this.targetClass!= null);
    }

    /**
     * Gets the value of the spectralClass property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getSpectralClass() {
        return spectralClass;
    }

    /**
     * Creates spectralClass property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createSpectralClass() {
        if (this.spectralClass == null)
           this.setSpectralClass (new TextParam ());
        return this.spectralClass;
    }


    /**
     * Sets the value of the spectralClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setSpectralClass(TextParam value) {
        this.spectralClass = value;
    }

    public boolean isSetSpectralClass() {
        return (this.spectralClass!= null);
    }

    /**
     * Gets the value of the redshift property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getRedshift() {
        return redshift;
    }

    /**
     * Creates redshift property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createRedshift() {
        if (this.redshift == null)
           this.setRedshift (new DoubleParam ());
        return this.redshift;
    }


    /**
     * Sets the value of the redshift property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setRedshift(DoubleParam value) {
        this.redshift = value;
    }

    public boolean isSetRedshift() {
        return (this.redshift!= null);
    }

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     either null or
     *     {@link PositionParam }
     *     
     */
    public PositionParam getPos() {
        return pos;
    }

    /**
     * Creates pos property if one does not exist.
     *
     * @return
     *     {@link PositionParam }
     *
     */
    public PositionParam createPos() {
        if (this.pos == null)
           this.setPos (new PositionParam ());
        return this.pos;
    }


    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionParam }
     *     
     */
    public void setPos(PositionParam value) {
        this.pos = value;
    }

    public boolean isSetPos() {
        return (this.pos!= null);
    }

    /**
     * Gets the value of the varAmpl property.
     * 
     * @return
     *     either null or
     *     {@link DoubleParam }
     *     
     */
    public DoubleParam getVarAmpl() {
        return varAmpl;
    }

    /**
     * Creates varAmpl property if one does not exist.
     *
     * @return
     *     {@link DoubleParam }
     *
     */
    public DoubleParam createVarAmpl() {
        if (this.varAmpl == null)
           this.setVarAmpl (new DoubleParam ());
        return this.varAmpl;
    }


    /**
     * Sets the value of the varAmpl property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleParam }
     *     
     */
    public void setVarAmpl(DoubleParam value) {
        this.varAmpl = value;
    }

    public boolean isSetVarAmpl() {
        return (this.varAmpl!= null);
    }

}
