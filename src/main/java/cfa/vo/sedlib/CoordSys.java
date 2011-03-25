package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for coordSys complex type.
 * 
 */
public class CoordSys extends Group {

    protected List<CoordFrame> coordFrame;
    protected String id;
    protected Object idref;
    protected String ucd;
    protected String type;
    protected String href;

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
     * Gets the value of the idref property.
     * 
     * @return
     *     either null or
     *     {@link Object }
     *     
     */
    public Object getIdref() {
        return idref;
    }

    /**
     * Sets the value of the idref property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setIdref(Object value) {
        this.idref = value;
    }

    public boolean isSetIdref() {
        return (this.idref!= null);
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
