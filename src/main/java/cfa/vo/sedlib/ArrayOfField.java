package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for arrayOfField complex type.
 * 
 */
public class ArrayOfField {

    protected List<Field> field;


     /**
     * Gets the field list.
     *
     * @return 
     *   either List<Field> or null
     *   {@link Field}
     *
     */
    public List<Field> getField() {
        return this.field;
    }

    /**
     * Creates the point list if one does not exist.
     *
     * @return List<Field>
     *   {@link Field}
     *
     */
    public List<Field> createField() {
        if (this.field == null) {
            this.field = new ArrayList<Field>();
        }
        return this.field;
    }

    public boolean isSetField() {
        return (this.field!= null);
    }

    /**
     * Sets the field list to a new list
     *
     * @param field
     *     allowed object is List<Field>
     *     {@link Field }
     *
     */
    public void setField(List<Field> field) {
        this.field = field;
    }

}
