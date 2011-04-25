package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for characterization complex type.
 * 
 * 
 * 
 */
public class Characterization
    extends Group
{

    protected CharacterizationAxis spatialAxis;
    protected CharacterizationAxis timeAxis;
    protected SpectralCharacterizationAxis spectralAxis;
    protected CharacterizationAxis fluxAxis;
    protected List<CharacterizationAxis> characterizationAxis;

    @Override
    public Object clone ()
    {
        Characterization _char = null;

        _char = (Characterization) super.clone();

        if (this.isSetSpatialAxis ())
            _char.spatialAxis = (CharacterizationAxis)this.spatialAxis.clone ();
        if (this.isSetTimeAxis ())
            _char.timeAxis = (CharacterizationAxis)this.timeAxis.clone ();
        if (this.isSetSpectralAxis ())
            _char.spectralAxis = (SpectralCharacterizationAxis)this.spectralAxis.clone ();
        if (this.isSetFluxAxis ())
            _char.fluxAxis = (CharacterizationAxis)this.fluxAxis.clone ();
        if (this.isSetCharacterizationAxis ())
        {
            _char.characterizationAxis = new ArrayList<CharacterizationAxis>();
            for (CharacterizationAxis charAxis : this.characterizationAxis)
                _char.characterizationAxis.add ((CharacterizationAxis)charAxis.clone ());
        }
        return _char;
    }


    /**
     * Gets the value of the spatialAxis property.
     * 
     * @return
     *     either null or
     *     {@link CharacterizationAxis }
     *     
     */
    public CharacterizationAxis getSpatialAxis() {
        return spatialAxis;
    }

    /**
     * Create the spatialAxis property if one does not exist.
     *
     * @return
     *     {@link CharacterizationAxis }
     *
     */
    public CharacterizationAxis createSpatialAxis() {
        if (this.spatialAxis == null)
           this.setSpatialAxis (new CharacterizationAxis ());
        return this.spatialAxis;
    }


    /**
     * Sets the value of the spatialAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterizationAxis }
     *     
     */
    public void setSpatialAxis(CharacterizationAxis value) {
        this.spatialAxis = value;
    }

    public boolean isSetSpatialAxis() {
        return (this.spatialAxis!= null);
    }

    /**
     * Gets the value of the timeAxis property.
     * 
     * @return
     *     either null or
     *     {@link CharacterizationAxis }
     *     
     */
    public CharacterizationAxis getTimeAxis() {
        return timeAxis;
    }

    /**
     * Create the timeAxis property if one does not exist.
     *
     * @return
     *     {@link CharacterizationAxis }
     *
     */
    public CharacterizationAxis createTimeAxis() {
        if (this.timeAxis == null)
           this.setTimeAxis (new CharacterizationAxis ());
        return this.timeAxis;
    }


    /**
     * Sets the value of the timeAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterizationAxis }
     *     
     */
    public void setTimeAxis(CharacterizationAxis value) {
        this.timeAxis = value;
    }

    public boolean isSetTimeAxis() {
        return (this.timeAxis!= null);
    }

    /**
     * Gets the value of the spectralAxis property.
     * 
     * @return
     *     either null or
     *     {@link SpectralCharacterizationAxis }
     *     
     */
    public SpectralCharacterizationAxis getSpectralAxis() {
        return spectralAxis;
    }

    /**
     * Create the spectralAxis property if one does not exist.
     *
     * @return
     *     {@link SpectralCharacterizationAxis }
     *
     */
    public SpectralCharacterizationAxis createSpectralAxis() {
        if (this.spectralAxis == null)
           this.setSpectralAxis (new SpectralCharacterizationAxis ());
        return this.spectralAxis;
    }


    /**
     * Sets the value of the spectralAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpectralCharacterizationAxis }
     *     
     */
    public void setSpectralAxis(SpectralCharacterizationAxis value) {
        this.spectralAxis = value;
    }

    public boolean isSetSpectralAxis() {
        return (this.spectralAxis!= null);
    }

    /**
     * Gets the value of the fluxAxis property.
     * 
     * @return
     *     either null or
     *     {@link CharacterizationAxis }
     *     
     */
    public CharacterizationAxis getFluxAxis() {
        return fluxAxis;
    }

    /**
     * Create the fluxAxis property if one does not exist.
     *
     * @return
     *     {@link CharacterizationAxis }
     *
     */
    public CharacterizationAxis createFluxAxis() {
        if (this.fluxAxis == null)
           this.setFluxAxis (new CharacterizationAxis ());
        return this.fluxAxis;
    }


    /**
     * Sets the value of the fluxAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterizationAxis }
     *     
     */
    public void setFluxAxis(CharacterizationAxis value) {
        this.fluxAxis = value;
    }

    public boolean isSetFluxAxis() {
        return (this.fluxAxis!= null);
    }


    /**
     * Gets the CharacterizationAxis list.
     *
     * @return List<CharacterizationAxis>
     *   either null or List<CharacterizationAxis>
     *   {@link CharacterizationAxis}
     *
     */
    public List<CharacterizationAxis> getCharacterizationAxis() {
        return this.characterizationAxis;
    }

    /**
     * Creates the CharacterizationAxis list if one does not exist.
     *
     * @return List<CharacterizationAxis>
     *   {@link CharacterizationAxis}
     *
     */
    public List<CharacterizationAxis> createCharacterizationAxis() {
        if (characterizationAxis == null) {
            characterizationAxis = new ArrayList<CharacterizationAxis>();
        }
        return this.characterizationAxis;
    }


    public boolean isSetCharacterizationAxis() {
        return (this.characterizationAxis!= null);
    }

    /**
     * Sets the point list to a new list
     *
     * @param characterizationAxis
     *     allowed object is List<CharacterizationAxis>
     *     {@link CharacterizationAxis }
     *
     */
    public void setCharacterizationAxis(List <CharacterizationAxis> characterizationAxis) {
        this.characterizationAxis = characterizationAxis;
    }

}
