package cfa.vo.sedlib;

/**
 * <p>Java class for point complex type.
 * 
 * 
 */
public class Point extends Group {

    protected SedCoord timeAxis;
    protected SedCoord spectralAxis;
    protected SedQuantity fluxAxis;
    protected SedQuantity backgroundModel;

    /**
     * Gets the value of the timeAxis property.
     * 
     * @return
     *     either null or
     *     {@link SedCoord }
     *     
     */
    public SedCoord getTimeAxis() {
        return timeAxis;
    }

    /**
     * Creates timeAxis property if one does not exist.
     *
     * @return
     *     {@link SedCoord }
     *
     */
    public SedCoord createTimeAxis() {
        if (this.timeAxis == null)
           this.setTimeAxis (new SedCoord ());
        return this.timeAxis;
    }


    /**
     * Sets the value of the timeAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedCoord }
     *     
     */
    public void setTimeAxis(SedCoord value) {
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
     *     {@link SedCoord }
     *     
     */
    public SedCoord getSpectralAxis() {
        return spectralAxis;
    }

    /**
     * Creates spectralAxis property if one does not exist.
     *
     * @return
     *     {@link SedCoord }
     *
     */
    public SedCoord createSpectralAxis() {
        if (this.spectralAxis == null)
           this.setSpectralAxis (new SedCoord ());
        return this.spectralAxis;
    }


    /**
     * Sets the value of the spectralAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedCoord }
     *     
     */
    public void setSpectralAxis(SedCoord value) {
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
     *     {@link SedQuantity }
     *     
     */
    public SedQuantity getFluxAxis() {
        return fluxAxis;
    }

    /**
     * Creates fluxAxis property if one does not exist.
     *
     * @return
     *     {@link SedQuantity }
     *
     */
    public SedQuantity createFluxAxis() {
        if (this.fluxAxis == null)
           this.setFluxAxis (new SedQuantity ());
        return this.fluxAxis;
    }


    /**
     * Sets the value of the fluxAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedQuantity }
     *     
     */
    public void setFluxAxis(SedQuantity value) {
        this.fluxAxis = value;
    }

    public boolean isSetFluxAxis() {
        return (this.fluxAxis!= null);
    }

    /**
     * Gets the value of the backgroundModel property.
     * 
     * @return
     *     either null or
     *     {@link SedQuantity }
     *     
     */
    public SedQuantity getBackgroundModel() {
        return backgroundModel;
    }

    /**
     * Creates backgroundModel property if one does not exist.
     *
     * @return
     *     {@link SedQuantity }
     *
     */
    public SedQuantity createBackgroundModel() {
        if (this.backgroundModel == null)
           this.setBackgroundModel (new SedQuantity ());
        return this.backgroundModel;
    }


    /**
     * Sets the value of the backgroundModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedQuantity }
     *     
     */
    public void setBackgroundModel(SedQuantity value) {
        this.backgroundModel = value;
    }

    public boolean isSetBackgroundModel() {
        return (this.backgroundModel!= null);
    }

/*
    public FlatPoint createFlatPoint ()
    {
        FlatPoint flatPoint = new FlatPoint ();
        Accuracy accuracy;

        if (this.isSetTimeAxis ())
        {
            if (this.timeAxis.isSetValue ())
                flatPoint.setT ((Double)this.timeAxis.getValue ().getCastValue ());
            if (this.timeAxis.isSetResolution ())
                flatPoint.setTRes ((Double)this.timeAxis.getResolution ().getCastValue ());
            if (this.timeAxis.isSetAccuracy ())
            {
                accuracy = this.timeAxis.getAccuracy ();
                if (accuracy.isSetBinLow ())
                   flatPoint.setTBinL ((Double)accuracy.getBinLow().getCastValue ());
                if (accuracy.isSetBinHigh ())
                   flatPoint.setTBinH ((Double)accuracy.getBinHigh().getCastValue ());
                if (accuracy.isSetBinSize ())
                   flatPoint.setTSize ((Double)accuracy.getBinSize().getCastValue ());
            }
        }
        if (this.isSetSpectralAxis ())
        {
            if (this.spectralAxis.isSetValue ())
                flatPoint.setSP ((Double)this.spectralAxis.getValue ().getCastValue ());
            if (this.spectralAxis.isSetResolution ())
                flatPoint.setSPRes ((Double)this.spectralAxis.getResolution ().getCastValue ());
            if (this.spectralAxis.isSetAccuracy ())
            {
                accuracy = this.spectralAxis.getAccuracy ();
                if (accuracy.isSetBinLow ())
                   flatPoint.setSPBinL ((Double)accuracy.getBinLow().getCastValue ());
                if (accuracy.isSetBinHigh ())
                   flatPoint.setSPBinH ((Double)accuracy.getBinHigh().getCastValue ());
                if (accuracy.isSetBinSize ())
                   flatPoint.setSPSize ((Double)accuracy.getBinSize().getCastValue ());
            }
        }
        if (this.isSetFluxAxis ())
        {
            if (this.fluxAxis.isSetQuality ())
                flatPoint.setFQual ((Integer)this.fluxAxis.getQuality ().getCastValue ());
            if (this.fluxAxis.isSetAccuracy ())
            {
                accuracy = this.fluxAxis.getAccuracy ();
                if (accuracy.isSetStatErrLow ())
                   flatPoint.setFErrL ((Double)accuracy.getStatErrLow().getCastValue ());
                if (accuracy.isSetStatErrHigh ())
                   flatPoint.setFErrH ((Double)accuracy.getStatErrHigh().getCastValue ());
                if (accuracy.isSetSysError ())
                   flatPoint.setFSys ((Double)accuracy.getSysError().getCastValue ());
            }
        }

        if (this.isSetBackgroundModel ())
        {
            if (this.backgroundModel.isSetQuality ())
                flatPoint.setBGQual ((Integer)this.backgroundModel.getQuality ().getCastValue ())
;
            if (this.backgroundModel.isSetAccuracy ())
            {
                accuracy = this.backgroundModel.getAccuracy ();
                if (accuracy.isSetStatErrLow ())
                   flatPoint.setBGErrL ((Double)accuracy.getStatErrLow().getCastValue ());
                if (accuracy.isSetStatErrHigh ())
                   flatPoint.setBGErrH ((Double)accuracy.getStatErrHigh().getCastValue ());
                if (accuracy.isSetSysError ())
                   flatPoint.setBGSys ((Double)accuracy.getSysError().getCastValue ());
            }
        }

        return flatPoint;
    }
*/


}
