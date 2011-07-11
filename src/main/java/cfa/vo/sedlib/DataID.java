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


/**
 * <p>Java class for dataID complex type.
 * 
 */
public class DataID
    extends Group
{

    protected TextParam title;
    protected TextParam creator;
    protected List<TextParam> collection;
    protected TextParam datasetID;
    protected DateParam date;
    protected TextParam version;
    protected TextParam instrument;
    protected TextParam creationType;
    protected TextParam bandpass;
    protected TextParam creatorDID;
    protected List<TextParam> contributor;
    protected TextParam logo;
    protected TextParam dataSource;

    @Override
    public Object clone ()
    {
        DataID dataID = (DataID) super.clone();

        if (this.isSetTitle ())
            dataID.title = (TextParam)this.title.clone ();
        if (this.isSetCreator ())
            dataID.creator = (TextParam)this.creator.clone ();
        if (this.isSetCollection ())
        {
            dataID.collection = new ArrayList<TextParam>();
            for (TextParam cc : this.collection)
                dataID.collection.add ((TextParam)cc.clone ());
        }
        if (this.isSetDatasetID ())
            dataID.datasetID = (TextParam)this.datasetID.clone ();
        if (this.isSetDate ())
            dataID.date = (DateParam)this.date.clone ();
        if (this.isSetVersion ())
            dataID.version = (TextParam)this.version.clone ();
        if (this.isSetInstrument ())
            dataID.instrument = (TextParam)this.instrument.clone ();
        if (this.isSetCreationType ())
            dataID.creationType = (TextParam)this.creationType.clone ();
        if (this.isSetBandpass ())
            dataID.bandpass = (TextParam)this.bandpass.clone ();
        if (this.isSetCreatorDID ())
            dataID.creatorDID = (TextParam)this.creatorDID.clone ();
        if (this.isSetContributor ())
        {
            dataID.contributor = new ArrayList<TextParam>();
            for (TextParam cc : this.contributor)
                dataID.contributor.add ((TextParam)cc.clone ());
        }

        if (this.isSetLogo ())
            dataID.logo = (TextParam)this.logo.clone ();
        if (this.isSetDataSource ())
            dataID.dataSource = (TextParam)this.dataSource.clone ();

        return dataID;
    }


    /**
     * Gets the value of the title property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getTitle() {
        return title;
    }

    /**
     * Creates title property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createTitle() {
        if (this.title == null)
           this.setTitle (new TextParam ());
        return this.title;
    }


    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setTitle(TextParam value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title!= null);
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getCreator() {
        return creator;
    }

    /**
     * Creates creator property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createCreator() {
        if (this.creator == null)
           this.setCreator (new TextParam ());
        return this.creator;
    }


    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setCreator(TextParam value) {
        this.creator = value;
    }

    public boolean isSetCreator() {
        return (this.creator!= null);
    }

    /**
     * Gets the collection.
     *
     * @return List<TextParam>
     *   {@link TextParam}
     *
     */
    public List<TextParam> getCollection() {
        return this.collection;
    }

    /**
     * Creates the collection if one does not exist.
     *
     * @return List<TextParam>
     *   {@link TextParam}
     *
     */
    public List<TextParam> createCollection() {
        if (this.collection == null) {
            this.collection = new ArrayList<TextParam>();
        }
        return this.collection;
    }


    public boolean isSetCollection() {
        return (this.collection!= null);
    }

    public void setCollection(List<TextParam> collection) {
        this.collection = collection;
    }

    /**
     * Gets the value of the datasetID property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getDatasetID() {
        return datasetID;
    }

    /**
     * Creates datasetID property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createDatasetID() {
        if (this.datasetID == null)
           this.setDatasetID (new TextParam ());
        return this.datasetID;
    }


    /**
     * Sets the value of the datasetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setDatasetID(TextParam value) {
        this.datasetID = value;
    }

    public boolean isSetDatasetID() {
        return (this.datasetID!= null);
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     either null or
     *     {@link DateParam }
     *     
     */
    public DateParam getDate() {
        return date;
    }

    /**
     * Creates date property if one does not exist.
     *
     * @return
     *     {@link DateParam }
     *
     */
    public DateParam createDate() {
        if (this.date == null)
           this.setDate (new DateParam ());
        return this.date;
    }


    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateParam }
     *     
     */
    public void setDate(DateParam value) {
        this.date = value;
    }

    public boolean isSetDate() {
        return (this.date!= null);
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getVersion() {
        return version;
    }

    /**
     * Creates version property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createVersion() {
        if (this.version == null)
           this.setVersion (new TextParam ());
        return this.version;
    }


    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setVersion(TextParam value) {
        this.version = value;
    }

    public boolean isSetVersion() {
        return (this.version!= null);
    }

    /**
     * Gets the value of the instrument property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getInstrument() {
        return instrument;
    }

    /**
     * Creates instrument property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createInstrument() {
        if (this.instrument == null)
           this.setInstrument (new TextParam ());
        return this.instrument;
    }


    /**
     * Sets the value of the instrument property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setInstrument(TextParam value) {
        this.instrument = value;
    }

    public boolean isSetInstrument() {
        return (this.instrument!= null);
    }

    /**
     * Gets the value of the creationType property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getCreationType() {
        return creationType;
    }

    /**
     * Creates creationType property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createCreationType() {
        if (this.creationType == null)
           this.setCreationType (new TextParam ());
        return this.creationType;
    }


    /**
     * Sets the value of the creationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setCreationType(TextParam value) {
        this.creationType = value;
    }

    public boolean isSetCreationType() {
        return (this.creationType!= null);
    }

    /**
     * Gets the value of the bandpass property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getBandpass() {
        return bandpass;
    }

    /**
     * Creates bandpass property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createBandpass() {
        if (this.bandpass == null)
           this.setBandpass (new TextParam ());
        return this.bandpass;
    }


    /**
     * Sets the value of the bandpass property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setBandpass(TextParam value) {
        this.bandpass = value;
    }

    public boolean isSetBandpass() {
        return (this.bandpass!= null);
    }

    /**
     * Gets the value of the creatorDID property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getCreatorDID() {
        return creatorDID;
    }

    /**
     * Creates creatorDID property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createCreatorDID() {
        if (this.creatorDID == null)
           this.setCreatorDID (new TextParam ());
        return this.creatorDID;
    }


    /**
     * Sets the value of the creatorDID property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setCreatorDID(TextParam value) {
        this.creatorDID = value;
    }

    public boolean isSetCreatorDID() {
        return (this.creatorDID!= null);
    }

    /**
     * Gets the contributor list.
     *
     * @return List<TextParam>
     *   {@link TextParam}
     *
     */
    public List<TextParam> getContributor() {
        return this.contributor;
    }

    /**
     * Creates the contributor list if one does not exist.
     *
     * @return List<TextParam>
     *   {@link TextParam}
     *
     */
    public List<TextParam> createContributor() {
        if (this.contributor == null) {
            this.contributor = new ArrayList<TextParam>();
        }
        return this.contributor;
    }


    public boolean isSetContributor() {
        return (this.contributor!= null);
    }

    public void setContributor(List<TextParam> contributor) {
        this.contributor = contributor;
    }

    /**
     * Gets the value of the logo property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getLogo() {
        return logo;
    }

    /**
     * Creates logo property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createLogo() {
        if (this.logo == null)
           this.setLogo (new TextParam ());
        return this.logo;
    }


    /**
     * Sets the value of the logo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setLogo(TextParam value) {
        this.logo = value;
    }

    public boolean isSetLogo() {
        return (this.logo!= null);
    }

    /**
     * Gets the value of the dataSource property.
     * 
     * @return
     *     either null or
     *     {@link TextParam }
     *     
     */
    public TextParam getDataSource() {
        return dataSource;
    }

    /**
     * Creates dataSource property if one does not exist.
     *
     * @return
     *     {@link TextParam }
     *
     */
    public TextParam createDataSource() {
        if (this.dataSource == null)
           this.setDataSource (new TextParam ());
        return this.dataSource;
    }


    /**
     * Sets the value of the dataSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextParam }
     *     
     */
    public void setDataSource(TextParam value) {
        this.dataSource = value;
    }

    public boolean isSetDataSource() {
        return (this.dataSource!= null);
    }

}
