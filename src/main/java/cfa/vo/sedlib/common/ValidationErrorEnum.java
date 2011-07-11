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

package cfa.vo.sedlib.common;


/**
 * This enumeration provides a list of possible validation errors. It contains
 * both the enumeration and a default error message.
 *
 *
 */
public enum ValidationErrorEnum
{

    MISSING_CURATION_PUB ("Missing required curation.publisher."),
    MISSING_TARGET_NAME ("Missing required target.name."),
    MISSING_CHAR_FLUXAXIS_UCD ("Missing required Char.FluxAxis.ucd"),
    MISSING_CHAR_FLUXAXIS_UNIT ("Missing required Char.FluxAxis.unit"),
    MISSING_CHAR_SPECTRALAXIS_UCD ("Missing required Char.FluxAxis.ucd"),
    MISSING_CHAR_SPECTRALAXIS_UNIT ("Missing required Char.FluxAxis.unit"),
    MISSING_CHAR_SPATIALAXIS_COV_LOCATION_VALUE ("Missing required Char.SpatialAxis.Coverage.Location.Value"),
    MISSING_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT ("Missing required Char.SpatialAxis.Coverage.Bounds.Extent"),
    MISSING_CHAR_TIMEAXIS_COV_LOCATION_VALUE ("Missing required Char.TimeAxis.Coverage.Location.Value"),
    MISSING_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT ("Missing required Char.TimeAxis.Coverage.Bounds.Extent"),
    MISSING_CHAR_SPECTRALAXIS_COV_LOCATION_VALUE ("Missing required Char.SpectralAxis.Coverage.Location.Value"),
    MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT ("Missing required Char.SpectralAxis.Coverage.Bounds.Extent"),
    MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_START ("Missing required Char.SpectralAxis.Coverage.Bounds.Start"),
    MISSING_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP ("Missing required Char.SpectralAxis.Coverage.Bounds.Stop"),
    MISSING_DATA_FLUXAXIS_VALUE ("Missing required Data.FluxAxis.Value"),
    MISSING_DATA_SPECTRALAXIS_VALUE ("Missing required Data.SpectralAxis.Value"),
    INVALID_SEGMENT_TYPE ("Invalid Segment type found. Expected the value to be Photometry, or Spectrum or TimeSeries"),
    INVALID_RESOURCE_UTYPE ("Invalid Resource utype found. Expected the value to be Sed."),
    INVALID_TABLE_UTYPE ("Invalid Table utype found. Expected the value to be Spectrum.");

    
    protected String errorMessage = "";

    ValidationErrorEnum (String message) {this.errorMessage = message;};

    public String getErrorMessage ()
    {
        return this.errorMessage;
    }

}

