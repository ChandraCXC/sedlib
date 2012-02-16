/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfa.vo.sedlib;

import cfa.vo.sedlib.common.SedException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedNullException;

/**
 *
 * @author mcd
 */
public interface ISegment {

    /**
     * Creates new data custom data parameters to each point for a given field.
     * The field id is used to determine which parameter should be added/changed
     * If the point already has a data parameter with the given id, parameter
     * information will be replaced by the new information.
     *
     * @param field
     * {@link Field}
     * @param values
     * {@link Object}
     *
     * @throws SedException, SedNoDataException, SedNullException, SedInconsistentException
     *
     */
    void addCustomData(Field field, Object values) throws SedException, SedNoDataException, SedNullException, SedInconsistentException;

    /**
     * Creates _char property if one does not exist.
     *
     * @return
     * {@link Characterization }
     *
     */
    Characterization createChar();

    /**
     * Creates coordSys property if one does not exist.
     *
     * @return
     * {@link CoordSys }
     *
     */
    CoordSys createCoordSys();

    /**
     * Creates curation property if one does not exist.
     *
     * @return
     * {@link Curation }
     *
     */
    Curation createCuration();

    /**
     * Creates data property if one does not exist.
     * The data property will be an ArrayOfPoint.
     *
     * @return
     * {@link ArrayOfPoint }
     *
     */
    ArrayOfPoint createData();

    /**
     * Creates dataID property if one does not exist.
     *
     * @return
     * {@link DataID }
     *
     */
    DataID createDataID();

    /**
     * Creates derived property if one does not exist.
     *
     * @return
     * {@link DerivedData }
     *
     */
    DerivedData createDerived();

    /**
     * Creates fluxSI property if one does not exist.
     *
     * @return
     * {@link TextParam }
     *
     */
    TextParam createFluxSI();

    /**
     * Creates spectralSI property if one does not exist.
     *
     * @return
     * {@link TextParam }
     *
     */
    TextParam createSpectralSI();

    /**
     * Creates target property if one does not exist.
     *
     * @return
     * {@link Target }
     *
     */
    Target createTarget();

    /**
     * Creates timeSI property if one does not exist.
     *
     * @return
     * {@link TextParam }
     *
     */
    TextParam createTimeSI();

    /**
     * Creates type property if one does not exist.
     *
     * @return
     * {@link TextParam }
     *
     */
    TextParam createType();

    boolean equals(Object obj);

    /**
     * Gets the value of the char property.
     *
     * @return
     * either null or
     * {@link Characterization }
     *
     */
    Characterization getChar();

    /**
     * Gets the value of the coordSys property.
     *
     * @return
     * either null or
     * {@link CoordSys }
     *
     */
    CoordSys getCoordSys();

    /**
     * Gets the value of the curation property.
     *
     * @return
     * either null or
     * {@link Curation }
     *
     */
    Curation getCuration();

    /**
     * Gets the parameter info of the specified id.
     *
     * @param id The custom id to be fetched.
     *
     * @return
     * {@link Field} a Field object representing the data required.
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    Field getCustomDataInfo(String id) throws SedNoDataException, SedNullException;

    /**
     * Gets the values of the specified custom data id. If no data exists
     * a SedException will be thrown. For points where the axis is not set
     * a default value is used; for doubles its NaN for ints its -9999 and
     * empty strings for strings.
     * @param id
     * {@link String}
     *
     * @return
     * either null or double[] or int[] depending on the data. The
     * arrays will be cast to an Object.
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    Object getCustomDataValues(String id) throws SedNoDataException, SedNullException;

    /**
     * Gets the value of the data property.
     *
     * @return
     * {@link ArrayOfPoint }
     *
     */
    ArrayOfPoint getData();

    /**
     * Gets the value of the dataID property.
     *
     * @return
     * either null or
     * {@link DataID }
     *
     */
    DataID getDataID();

    /**
     * Gets the parameter info of the specified axis. If no data exists then
     * SedNoDataException is thrown.
     * @param utype
     * {@link String}
     *
     * @return
     * {@link Field}
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    Field getDataInfo(String utype) throws SedNoDataException, SedInconsistentException;

    /**
     * Gets the parameter info of the specified axis. If no data exists then
     * SedNoDataException is thrown.
     * @param utype
     * int
     *
     * @return
     * {@link Field}
     *
     * @throws SedInconsistentException, SedNoDataException
     *
     */
    Field getDataInfo(int utype) throws SedInconsistentException, SedNoDataException;

    /**
     * Gets the value of the dataModel property.
     *
     * @return
     * {@link String }
     *
     */
    String getDataModel();

    /**
     * Gets the value of the derived property.
     *
     * @return
     * either null or
     * {@link DerivedData }
     *
     */
    DerivedData getDerived();

    /**
     * Gets the units of the flux axis. If no data exists then
     * a SedNoDataException is thrown.
     *
     * @return
     * {@link String}
     *
     * @throws SedNoDataException
     *
     */
    String getFluxAxisUnits() throws SedNoDataException;

    /**
     * Gets the values of the flux axis. If no data exists then
     * SedNoDataException is thrown. For points where the flux axis is not set
     * a NaN value is used.
     *
     * @return
     * either double[]
     *
     * @throws SedNoDataException
     *
     */
    double[] getFluxAxisValues() throws SedNoDataException;

    /**
     * Gets the value of the fluxSI property.
     *
     * @return
     * either null or
     * {@link TextParam }
     *
     */
    TextParam getFluxSI();

    /**
     * Gets the length of the point list.
     *
     * @return int
     *
     */
    int getLength();

    /**
     * Gets the units of the spectral axis. If no data exists then
     * SedNoDataException is thrown
     *
     * @return
     * {@link String}
     *
     * @throws SedNoDataException
     */
    String getSpectralAxisUnits() throws SedNoDataException;

    /**
     * Gets the values of the spectral axis. If no data exists then
     * SedNoDataExeption is thrown. For points where the spectral axis is not set
     * a NaN value is used.
     *
     * @return
     * double[]
     *
     * @throws SedNoDataException
     *
     */
    double[] getSpectralAxisValues() throws SedNoDataException;

    /**
     * Gets the value of the spectralSI property.
     *
     * @return
     * either null or
     * {@link TextParam }
     *
     */
    TextParam getSpectralSI();

    /**
     * Gets the value of the target property.
     *
     * @return
     * either null or
     * {@link Target }
     *
     */
    Target getTarget();

    /**
     * Gets the value of the timeSI property.
     *
     * @return
     * either null or
     * {@link TextParam }
     *
     */
    TextParam getTimeSI();

    /**
     * Gets the value of the type property.
     *
     * @return
     * either null or
     * {@link TextParam }
     *
     */
    TextParam getType();

    boolean isSetChar();

    boolean isSetCoordSys();

    boolean isSetCuration();

    boolean isSetData();

    boolean isSetDataID();

    boolean isSetDerived();

    boolean isSetFluxSI();

    boolean isSetSpectralSI();

    boolean isSetTarget();

    boolean isSetTimeSI();

    boolean isSetType();

    /**
     * Sets the value of the char property.
     *
     * @param value
     * allowed object is
     * {@link Characterization }
     *
     */
    void setChar(Characterization value);

    /**
     * Sets the value of the coordSys property.
     *
     * @param value
     * allowed object is
     * {@link CoordSys }
     *
     */
    void setCoordSys(CoordSys value);

    /**
     * Sets the value of the curation property.
     *
     * @param value
     * allowed object is
     * {@link Curation }
     *
     */
    void setCuration(Curation value);

    /**
     * Sets the parameter info of the specified custom parameters. The field id
     * is used to determine which parameter should be changed. The specified field
     * will replace all information on each point except for the value. If no data
     * exists for the specified id on a particular point the point will be ignored.
     * Creates new data custom data parameters to each point for a given field id.
     * If the point already has a data parameter with the given id, parameter
     * information will be replaced by the new information.
     *
     * @param field
     * {@link Field}
     *
     * @throws SedNoDataException, SedNullException
     *
     */
    void setCustomDataInfo(Field field) throws SedNoDataException, SedNullException;

    /**
     * Sets the values of the custom data id. The first n values of the
     * are set in the spectral axis. If the array is larger then the
     * number of points, then the extra values are ignored.
     *
     * @param values
     * Valid values are double[], float[], int[], short[], long[] or String[]
     *
     * @throws SedNoDataException, SedInconsistentException, SedNullException
     *
     */
    void setCustomDataValues(String id, Object values) throws SedNoDataException, SedInconsistentException, SedNullException;

    /**
     * Sets the value of the data property.
     *
     * @param value
     * allowed object is
     * {@link ArrayOfPoint }
     *
     */
    void setData(ArrayOfPoint value);

    /**
     * Sets the value of the dataID property.
     *
     * @param value
     * allowed object is
     * {@link DataID }
     *
     */
    void setDataID(DataID value);

    /**
     * Sets the parameter info of the specified axis. The specfied field
     * will replace all information on each point except for the value. If no data
     * exists for the specified utype on a particular point the point will be ignored.
     *
     * If no data are found, a SedNoDataException is thrown.
     *
     * If utype is not supported, a SedInconsistentException is thrown.
     *
     * @param field
     * {@link Field}
     * @param utype
     * {@link String}
     *
     * @throws SedInconsistentException, SedNoDataException
     *
     */
    void setDataInfo(Field field, String utype) throws SedInconsistentException, SedNoDataException;

    /**
     * Sets the parameter info of the specified axis. The specfied field
     * will replace all information on each point except for the value. If no data
     * exists for the specified utype on a particular point the point will be ignored.
     *
     * If there are no points to set, a SedNoDataException is thrown.
     *
     * If the utype is not supported a SedInconsistentException is thrown.
     *
     * @param field
     * {@link Field}
     * @param utype
     * int
     *
     * @throws SedNoDataException, SedInconsistentException
     *
     */
    void setDataInfo(Field field, int utype) throws SedNoDataException, SedInconsistentException;

    /**
     * Sets the value of the derived property.
     *
     * @param value
     * allowed object is
     * {@link DerivedData }
     *
     */
    void setDerived(DerivedData value);

    /**
     * Sets the units of the flux axis.
     *
     * If there is no data on the flux axis a SedNoDataException is thrown
     *
     * @param units
     * {@link String}
     *
     * @throws SedNoDataException
     */
    void setFluxAxisUnits(String units) throws SedNoDataException;

    /**
     * Sets the values of the flux axis. The first n values of the array
     * are set in the flux axis. If the array is larger then the
     * number of points, the extra values are ignored.
     *
     * @param values
     * double[]
     *
     * @throws SedNoDataException
     *
     */
    void setFluxAxisValues(double[] values);

    /**
     * Sets the value of the fluxSI property.
     *
     * @param value
     * allowed object is
     * {@link TextParam }
     *
     */
    void setFluxSI(TextParam value);

    /**
     * Sets the units of the spectral axis. If there are no data
     * a SedNoDataException is thrown.
     *
     * @param units
     * {@link String}
     *
     * @throws SetNoDataException
     *
     */
    void setSpectralAxisUnits(String units) throws SedNoDataException;

    /**
     * Sets the values of the spectral axis. The first n values of the
     * are set in the spectral axis. If the array is larger then the
     * number of points, then the extra values are ignored.
     *
     * @param values
     * double[]
     *
     */
    void setSpectralAxisValues(double[] values);

    /**
     * Sets the value of the spectralSI property.
     *
     * @param value
     * allowed object is
     * {@link TextParam }
     *
     */
    void setSpectralSI(TextParam value);

    /**
     * Sets the value of the target property.
     *
     * @param value
     * allowed object is
     * {@link Target }
     *
     */
    void setTarget(Target value);

    /**
     * Sets the value of the timeSI property.
     *
     * @param value
     * allowed object is
     * {@link TextParam }
     *
     */
    void setTimeSI(TextParam value);

    /**
     * Sets the value of the type property.
     *
     * @param value
     * allowed object is
     * {@link TextParam }
     *
     */
    void setType(TextParam value);

}
