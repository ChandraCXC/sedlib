package cfa.vo.sedlib;

import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNullException;

import java.util.List;
import java.util.ArrayList;


/**
 * <p>Java class for Group complex type.
 * 
 */
public class Group implements Cloneable {

    protected String groupId;
    protected Object idref;
    protected List<Param> customParams = new ArrayList <Param> ();
    protected List<Group> customGroups = new ArrayList <Group> ();
    protected int linkRef;

    public Group ()
    {
    	this.linkRef = -1;
    }
    
    /**
     * Contructor for create a group. The additional linkRef
     * is used to provide hints to the system that two or more groups
     * are related to each other. -1 is reserved value that
     * means there are no associations.
     */
    public Group (int linkRef)
    {
        this.linkRef = linkRef;
    }

    @Override
    public Object clone ()
    {
        Group group = null;
        try
        {
            group = (Group) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // this should never happen
            throw new InternalError(e.toString());
        }

        group.customParams = new ArrayList <Param> ();
        group.customGroups = new ArrayList <Group> ();
        for (Param param : this.customParams)
            group.customParams.add (param);
        for (Group grp : this.customGroups)
            group.customGroups.add (grp);
        return group;
    }


    /**
     * Gets the value of the id property.
     * 
     * @return
     *     either null or
     *     {@link String }
     *     
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupId(String value) {
        this.groupId = value;
    }

    public boolean isSetGroupId() {
        return (this.groupId!= null);
    }

    /**
     * Gets the value of the linkRef property. This method is
     * is meant for internal use only.
     *
     * @return int
     *
     */
    public int getLinkRef() {
        return this.linkRef;
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
     * Gets the custom param list.
     *
     * @return List<Param>
     *   List<Param>
     *   {@link Param}
     *
     */
    public List<? extends Param> getCustomParams() {
        return (new ArrayList <Param> (this.customParams));
    }

   
    /**
     * Sets the custom param list to a new list
     *
     * @param customParams
     *     allowed object is List<Param>
     *     {@link Param }
     *
     */
    public void setCustomParams(List<? extends Param> customParams) {
        if (customParams == null)
            this.customParams = new ArrayList <Param> ();
        else
            this.customParams = new ArrayList <Param> (customParams);
    }

    /**
     * Find a custom param in the list based on the id
     *
     * @param id 
     *     {@link String }
     *
     * @return Param
     *     {@link Param}
     *
     * @throws SedNoDataException, SedNullException
     */
    public Param findCustomParam(String paramId) throws SedNoDataException, SedNullException
    {

        Param returnParam = null;

        if (paramId == null)
            throw new SedNullException ("Parameters with a null id cannot be retrieved");

        for (Param param : this.customParams)
        {
            if (param.header.isSetId ())
            {
                if (paramId.equals (param.header.getId ()))
                    returnParam = param;
            }
        }

        if (returnParam == null)
            throw new SedNoDataException ("No param with id '"+paramId+"' was found.");

        return returnParam;
    }


    /**
     * Add a param to the custom param list
     *
     * @param param
     *     {@link Param }
     *
     * @thows SedNullException, SedInconsistentException
     *
     */
    public void addCustomParam(Param param) throws SedNullException, SedInconsistentException
    {
        String paramId;
        boolean paramFound = true;
        if (param == null)
            throw new SedNullException ("Cannot add a null custom parameter.");

        paramId = param.header.getId ();

        if (paramId != null)
        {
            try
            {
                findCustomParam(paramId);
            }
            catch (SedNoDataException exp)
            {
                paramFound = false;
            } 

            if (paramFound)
                throw new SedInconsistentException ("A custom parameter with the id,"+paramId+", has already been added. The custom parameter id must be unique.");
        }

        this.customParams.add (param);
    }

    /**
     * Remove a param from the custom param list based on the parameter id.
     *
     * @String id
     *     {@link String }
     *
     * @thows SedNoDataException, SedNullException
     *
     */
    public void removeCustomParam(String paramId) throws SedNoDataException, SedNullException
    {

        Param param = this.findCustomParam (paramId);

        this.customParams.remove (param);
    }

    /**
     * Gets the custom group list.
     *
     * @return List<Group>
     *   {@link Group}
     *
     */
    public List<? extends Group> getCustomGroups() {
        return (new ArrayList <Group> (this.customGroups));
    }


    /**
     * Sets the custom group list to a new list
     *
     * @group customGroup
     *     allowed object is List<Group>
     *     {@link Group }
     *
     */
    public void setCustomGroups(List<? extends Group> customGroups) {
        if (customGroups == null)
            this.customGroups = new ArrayList <Group> ();
        else
            this.customGroups = new ArrayList <Group> (customGroups);
    }

    /**
     * Find a custom group in the list based on the id
     *
     * @group id
     *     {@link String }
     *
     * @return Group
     *     {@link Group}
     *
     * @throws SedNoDataException, SedNullException
     */
    public Group findCustomGroup(String groupId) throws SedNoDataException, SedNullException
    {
        Group returnGroup = null;
        if (groupId == null)
            throw new SedNullException ("Groups with a null id cannot be retrieved");

        for (Group group : this.customGroups)
            if (groupId.equals (group.getGroupId ()))
                returnGroup = group;

        if (returnGroup == null)
            throw new SedNoDataException ("No group with id '"+groupId+"' was found.");

        return returnGroup;
    }


    /**
     * Add a group to the custom group list
     *
     * @group group
     *     {@link Group }
     *
     * @thows SedNullException, SedNoDataException
     *
     */
    public void addCustomGroup(Group group) throws SedNullException, SedInconsistentException
    {
        String grpId;
        boolean groupFound = true;
        if (group == null)
            throw new SedNullException ("Cannot add a null custom group.");

        grpId = group.getGroupId ();
        if (grpId != null)
        {
            try
            {
                findCustomGroup(grpId);
            }
            catch (SedNoDataException exp)
            {
                // this should fail it means that the group does not exist
                groupFound = false; 
            }

            if (groupFound)
                throw new SedInconsistentException ("A custom group with the id,"+grpId+", has already been added. The custom group id must be unique.");
        }

        this.customGroups.add (group);
    }

    /**
     * Remove a group from the custom group list based on the group id.
     *
     * @String id
     *     {@link String }
     *
     * @thows SedNoDataException, SedNullException
     *
     */
    public void removeCustomGroup(String groupId) throws SedNoDataException, SedNullException
    {

        Group group = this.findCustomGroup (groupId);

        this.customGroups.remove (group);
    }

}

