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

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

import cfa.vo.sedlib.common.Utypes;
import cfa.vo.sedlib.common.SedInconsistentException;

/**
 * <p>Java class for coordSys complex type.
 * 
 */
public class CoordSys extends Group  implements IAccessByUtype
{

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


    // ********************************************************************************
    //   Utype interface.
    // ********************************************************************************

    @Override
    public Object getValueByUtype( int utypeNum, boolean create ) throws SedInconsistentException
    {
	CoordFrame frame = null;
	Object value = null;

	if ( Utypes.isSpaceFrameUtype( utypeNum ) )
	{
	    if ( create )
		this.createCoordFrame();

	    if ( this.coordFrame != null )
	    {
		frame = this.findFrame( "SpaceFrame" );
		if ( create && (frame == null) )
		{
		    frame =  new SpaceFrame();
		    this.coordFrame.add( frame );
		}
	    }
	}
	else if ( Utypes.isSpectralFrameUtype( utypeNum ) )
	{
	    if ( create )
		this.createCoordFrame();

	    if ( this.coordFrame != null )
	    {
		frame = this.findFrame( "SpectralFrame" );
		if ( create && (frame == null) )
		{
		    frame =  new SpectralFrame();
		    this.coordFrame.add( frame );
		}
	    }
	}
	else if ( Utypes.isRedshiftFrameUtype( utypeNum ) )
	{
	    if ( create )
		this.createCoordFrame();

	    if ( this.coordFrame != null )
	    {
		frame = this.findFrame( "RedshiftFrame" );
		if ( create && (frame == null) )
		{
		    frame =  new RedshiftFrame();
		    this.coordFrame.add( frame );
		}
	    }
	}
	else if ( Utypes.isTimeFrameUtype( utypeNum ) )
	{
	    if ( create )
		this.createCoordFrame();

	    if ( this.coordFrame != null )
	    {
		frame = this.findFrame( "TimeFrame" );
		if ( create && (frame == null) )
		{
		    frame =  new TimeFrame();
		    this.coordFrame.add( frame );
		}
	    }
	}
	else if ( Utypes.isGenericFrameUtype( utypeNum ) )
	{
	    if ( create )
		this.createCoordFrame();

	    if ( this.coordFrame != null )
	    {
		frame = this.findFrame( "CoordFrame" );
		if ( create && (frame == null) )
		{
		    frame =  new TimeFrame();
		    this.coordFrame.add( frame );
		}
	    }
	}
	else if ( Utypes.isIdUtype( utypeNum ) )
	{
	    if ( this.isSetId() )
		value = this.getId();
	    else
		value = "";
	}
	else if ( Utypes.isUCDUtype( utypeNum ) )
	{
	    if ( this.isSetUcd() )
		value = this.getUcd();
	    else
		value = "";
	}
	else if ( Utypes.isTypeUtype( utypeNum ) )
	{
	    if ( this.isSetType() )
		value = this.getType();
	    else
		value = "";
	}
	else if ( Utypes.isHrefUtype( utypeNum ) )
	{
	    if ( this.isSetHref() )
		value = this.getHref();
	    else
		value = "";
	}

	if ( frame != null )
	    value = frame.getValueByUtype( utypeNum, create );

	return value;
    }

    @Override
    public void setValueByUtype( int utypeNum, Object value ) throws SedInconsistentException
    {
	CoordFrame frame = null;

	if ( Utypes.isSpaceFrameUtype( utypeNum ) )
	{
	    this.createCoordFrame();

	    frame = this.findFrame( "SpaceFrame" );
	    if ( frame == null )
	    {
		frame =  new SpaceFrame();
		this.coordFrame.add( frame );
	    }
	}
	else if ( Utypes.isSpectralFrameUtype( utypeNum ) )
	{
	    this.createCoordFrame();

	    frame = this.findFrame( "SpectralFrame" );
	    if ( frame == null )
	    {
		frame =  new SpectralFrame();
		this.coordFrame.add( frame );
	    }
	}
	else if ( Utypes.isTimeFrameUtype( utypeNum ) )
	{
	    this.createCoordFrame();

	    frame = this.findFrame( "TimeFrame" );
	    if ( frame == null )
	    {
		frame =  new TimeFrame();
		this.coordFrame.add( frame );
	    }
	}
	else if ( Utypes.isRedshiftFrameUtype( utypeNum ) )
	{
	    this.createCoordFrame();

	    frame = this.findFrame( "RedshiftFrame" );
	    if ( frame == null )
	    {
		frame =  new RedshiftFrame();
		this.coordFrame.add( frame );
	    }
	}
	else if ( Utypes.isGenericFrameUtype( utypeNum ) )
	{
	    this.createCoordFrame();

	    frame = this.findFrame( "CoordFrame" );
	    if ( frame == null )
	    {
		frame =  new CoordFrame();
		this.coordFrame.add( frame );
	    }
	}
	else if ( Utypes.isIdUtype( utypeNum ) )
	{
	    this.setId( (String)value );
	}
	else if ( Utypes.isUCDUtype( utypeNum ) )
	{
	    this.setUcd( (String)value );
	}
	else if ( Utypes.isTypeUtype( utypeNum ) )
	{
	    this.setType( (String)value );
	}
	else if ( Utypes.isHrefUtype( utypeNum ) )
	{
	    this.setHref( (String)value );
	}

	if ( frame != null )
	    frame.setValueByUtype( utypeNum, value );

    }

    private CoordFrame findFrame( String type ) throws SedInconsistentException
    {
	CoordFrame frame = null;

	if ( this.coordFrame == null )
	    return frame;

	for ( CoordFrame item : this.coordFrame )
	{
	    if ( item.getClass().getSimpleName().equals( type ) )
	    {
		if ( frame != null )
		    throw new SedInconsistentException ("Multiple copies of " + type + " found. It's ambiguous as to which param should be retrieved.");
		else
		    frame = item;
	    }
	}

	return frame;
    }


}
