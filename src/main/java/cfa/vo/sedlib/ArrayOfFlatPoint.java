package cfa.vo.sedlib;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for arrayOfFlatPoint complex type.
 * 
 */
public class ArrayOfFlatPoint
    extends ArrayOfGenPoint
{

    protected List<FlatPoint> point;


    /**
     * Gets the point list.
     *
     * @return List<FlatPoint>
     *   either null or
     *   {@link FlatPoint}
     *
     */
    public List<FlatPoint> getPoint() {
        return this.point;
    }

    /**
     * Creates the point list if one does not exist.
     *
     * @return List<FlatPoint>
     *   {@link FlatPoint}
     *
     */
    public List<FlatPoint> createPoint() {
        if (point == null) {
            point = new ArrayList<FlatPoint>();
        }
        return this.point;
    }

    public boolean isSetPoint() {
        return (this.point!= null);
    }

    /**
     * Sets the point list to a new list
     *
     * @param point
     *     allowed object is List<FlatPoint>
     *     {@link FlatPoint }
     *
     */
    public void setPoint(List<FlatPoint> point) {
        this.point = point;
    }


    /**
     * Gets the length of the point list.
     *
     */
    public int getLength()
    {
        if (this.point == null)
            return 0;
        return this.point.size ();
    }


}
