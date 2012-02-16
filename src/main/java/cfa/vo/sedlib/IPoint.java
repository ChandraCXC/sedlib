/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfa.vo.sedlib;

/**
 *
 * @author mcd
 */
public interface IPoint {

    /**
     * Creates backgroundModel property if one does not exist.
     *
     * @return
     * {@link SedQuantity }
     *
     */
    SedQuantity createBackgroundModel();

    /**
     * Creates fluxAxis property if one does not exist.
     *
     * @return
     * {@link SedQuantity }
     *
     */
    SedQuantity createFluxAxis();

    /**
     * Creates spectralAxis property if one does not exist.
     *
     * @return
     * {@link SedCoord }
     *
     */
    SedCoord createSpectralAxis();

    /**
     * Creates timeAxis property if one does not exist.
     *
     * @return
     * {@link SedCoord }
     *
     */
    SedCoord createTimeAxis();

    /**
     * Gets the value of the backgroundModel property.
     *
     * @return
     * either null or
     * {@link SedQuantity }
     *
     */
    SedQuantity getBackgroundModel();

    /**
     * Gets the value of the fluxAxis property.
     *
     * @return
     * either null or
     * {@link SedQuantity }
     *
     */
    SedQuantity getFluxAxis();

    /**
     * Gets the value of the spectralAxis property.
     *
     * @return
     * either null or
     * {@link SedCoord }
     *
     */
    SedCoord getSpectralAxis();

    /**
     * Gets the value of the timeAxis property.
     *
     * @return
     * either null or
     * {@link SedCoord }
     *
     */
    SedCoord getTimeAxis();

    boolean isSetBackgroundModel();

    boolean isSetFluxAxis();

    boolean isSetSpectralAxis();

    boolean isSetTimeAxis();

    /**
     * Sets the value of the backgroundModel property.
     *
     * @param value
     * allowed object is
     * {@link SedQuantity }
     *
     */
    void setBackgroundModel(SedQuantity value);

    /**
     * Sets the value of the fluxAxis property.
     *
     * @param value
     * allowed object is
     * {@link SedQuantity }
     *
     */
    void setFluxAxis(SedQuantity value);

    /**
     * Sets the value of the spectralAxis property.
     *
     * @param value
     * allowed object is
     * {@link SedCoord }
     *
     */
    void setSpectralAxis(SedCoord value);

    /**
     * Sets the value of the timeAxis property.
     *
     * @param value
     * allowed object is
     * {@link SedCoord }
     *
     */
    void setTimeAxis(SedCoord value);

}
