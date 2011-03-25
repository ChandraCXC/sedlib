package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for arrayOfParam complex type.
 * 
 * 
 */
public class ArrayOfParam {

    protected List<Param> param;

    /**
     * Gets the param list.
     *
     * @return List<param>
     *   either null or
     *   {@link Param}
     *
     */
    public List<Param> getParam() {
        return this.param;
    }

    /**
     * Creates the param list if one does not exist.
     *
     * @return List<param>
     *   {@link Param}
     *
     */
    public List<Param> createParam() {
        if (this.param == null) {
            this.param = new ArrayList<Param>();
        }
        return this.param;
    }


    public boolean isSetParam() {
        return ((this.param!= null)&&(!this.param.isEmpty()));
    }

    /**
     * Sets the param list to a new list
     *
     * @param param
     *     allowed object is List<Param>
     *     {@link Param }
     *
     */
    public void setParam(List<Param> param) {
        this.param = param;
    }

}
