package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;


/**
 * <p>Java class for coordSys complex type.
 * 
 */
public class CoordSys extends Group {

    protected List<CoordFrame> coordFrame;
    protected String id;
    protected String ucd;
    protected String type;
    protected String href;

    @Override
    public Object clone ()
    {
        CoordSys coordSys = (CoordSys) super.clone();

        if (this.isSetCoordFrame ())
        {
            coordSys.coordFrame = new ArrayList<CoordFrame>();
            for (CoordFrame cf : this.coordFrame)
                coordSys.coordFrame.add ((CoordFrame)cf.clone ());
        }

        
        if (this.isSetIdref ())
        {
        	coordSys.idref = null;
        	Logger.getLogger(Sed.class.getName()).severe("IDRef from CoordFrame is not cloneable");
/*            try
            {
                coordSys.idref = this.idref.clone ();
            }
            catch (CloneNotSupportedException e)
            {
                Logger.getLogger(Sed.class.getName()).log(Level.SEVERE, null, e);
            }
            */
        }


        return coordSys;
    }


    /**
     * Gets the coordFrame list.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpaceFrame }
     * {@link CoordFrame }
     * {@link SpectralFrame }
     * {@link TimeFrame }
     * {@link RedshiftFrame }
     * 
     * @return List<CoordFrame>
     *   either null or List<CoordFrame>
     *   {@link CoordFrame}
     * 
     */
    public List<CoordFrame> getCoordFrame() {
        return coordFrame;
    }

    /**
     * Creates the CoordFrame list if one does not exist.
     *
     * @return List<CoordFrame>
     *   {@link CoordFrame}
     *
     */
    public List<CoordFrame> createCoordFrame() {
        if (coordFrame == null) {
            coordFrame = new ArrayList<CoordFrame>();
        }

        return coordFrame;
    }

    public boolean isSetCoordFrame() {
        return ((this.coordFrame!= null)&&(!this.coordFrame.isEmpty()));
    }


    /**
     * Sets the CoordFrame list to a new list
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpaceFrame }
     * {@link CoordFrame }
     * {@link SpectralFrame }
     * {@link TimeFrame }
     * {@link RedshiftFrame }
     *
     * @param coordFrame 
     *     allowed object is List<CoordFrame>
     *     {@link Point }
     *
     */
    public void setCoordFrame(List<CoordFrame> coordFrame) {
        this.coordFrame = coordFrame;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
    }



    /**
     * Gets the value of the ucd property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getUcd() {
        return ucd;
    }

    /**
     * Sets the value of the ucd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUcd(String value) {
        this.ucd = value;
    }

    public boolean isSetUcd() {
        return (this.ucd!= null);
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "simple";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    public boolean isSetType() {
        return (this.type!= null);
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    public boolean isSetHref() {
        return (this.href!= null);
    }

}
