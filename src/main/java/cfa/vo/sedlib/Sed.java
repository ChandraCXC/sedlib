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

import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedParsingException;
import cfa.vo.sedlib.common.SedWritingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Collections;
import java.util.HashMap;

import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.io.SedFormat;
import cfa.vo.sedlib.io.SedIOFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import cfa.vo.sedlib.common.ValidationError;



/**
 * This class describes a collection of segments. It provides methods
 * to access segments and augment the segments in the collection.
 * 
 */

public class Sed implements Cloneable
{

    protected List<Segment> segmentList = new ArrayList<Segment>();
    protected String namespace;

    static final Logger logger = Logger.getLogger ("cfa.vo.sedlib");

    @Override
    public Object clone ()
    {
        Sed sed = null;
        try
        {
            sed = (Sed) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // this should never happen
            throw new InternalError(e.toString());
        }

        sed.segmentList = new ArrayList <Segment> (segmentList.size ());
        for (Segment segment : this.segmentList)
            sed.segmentList.add ((Segment)segment.clone ());

        return sed;
    }


    /**
     * Appends a segment to the Sed. The routine also
     * verifies that the incoming segment flux axis qualities match
     * the flux axis qualities from existing segments. Null qualities
     * are always assumed to be valid and matching.
     * @param segment
     *   Segment
     *
     * @throws SedInconsistentException
     */
    public void addSegment (Segment segment) throws SedInconsistentException, SedNoDataException
    {
        this.addSegment (segment, this.getNumberOfSegments());
    }


    /**
     * Add a segment to the Sed at particular offset. The routine also
     * verifies that the incoming segment flux axis qualities match
     * the flux axis qualities from existing segments. Null qualities
     * are always assumed to be valid and matching.
     * @param segment
     *   Segment
     * @param offset
     *   int
     *
     * @throws SedInconsistentException
     */
    public void addSegment (Segment segment, int offset) throws SedInconsistentException, SedNoDataException
    {

        if(!segmentList.isEmpty()) {
            if(segment.isCompatibleWith(segmentList.get(0)))
                segmentList.add (offset, segment);
            else
                throw new SedInconsistentException ("Incompatible quantities");
        } else {
            segmentList.add (offset, segment);
        }

//        String ucd = null;
//        String unit = null;
//        List<Point> points;
//
//        // loop through the segments to find a ucd or unit
//        for (Segment currSegment : this.segmentList)
//        {
//            points = currSegment.getPointsFromData ();
//            if (points == null)
//                continue;
//
//            // loop through all the points in the data and try to find
//            // a ucd and unit value
//            for (Point point : points)
//            {
//                if (point.isSetFluxAxis () && point.getFluxAxis ().isSetValue ())
//                {
//                    if (point.getFluxAxis ().getValue ().isSetUcd ())
//                        ucd = point.getFluxAxis ().getValue ().getUcd ();
//                    if (point.getFluxAxis ().getValue ().isSetUcd ())
//                        unit = point.getFluxAxis ().getValue ().getUnit ();
//                }
//
//                // these should all be the same so all we need it for one point
//                if ((ucd != null) && (unit != null))
//                    break;
//            }
//
//            // these should all be the same so all we need it for one point
//            if ((ucd != null) && (unit != null))
//                break;
//        }
//
//        // go through the points for the input segment and verify that the ucd
//        // and units are the same and match the existing qualities
//        points = segment.getPointsFromData ();
//        if (points != null)
//        {
//            String currUcd;
//            String currUnit;
//            for (Point point : points)
//            {
//                if (point.isSetFluxAxis () && point.getFluxAxis ().isSetValue ())
//                {
//                    currUcd = point.getFluxAxis ().getValue ().getUcd ();
//                    currUnit = point.getFluxAxis ().getValue ().getUnit ();
//
//                    // if the default ucd and unit are null -- we still
//                    // should verify all the pionts have the same ucd and unit
//                    if (ucd == null)
//                        ucd = currUcd;
//
//                    if (unit == null)
//                        unit = currUnit;
//
//                    if ((ucd != null) && (currUcd != null) && !ucd.equalsIgnoreCase(currUcd))
//                        throw new SedInconsistentException ("The current flux axis ucd, "+ucd+", does not match the incoming ucd, "+currUcd);
//                }
//            }
//        }
        
    }


    /**
     * Appends a list of segments to the Sed. The routine also
     * verifies that the incoming segment flux axis qualities match
     * the flux axis qualities from existing segments. Null qualities
     * are always assumed to be valid and matching.
     * @param segments
     *   List<{@link Segment}>
     *
     * @throws SedInconsistentException
     */
    public void addSegment (List<Segment> segments) throws SedInconsistentException, SedNoDataException
    {
        this.addSegment (segments, this.getNumberOfSegments());
    }

    /**
     * Add a list of segments to the Sed starting at particular offset. The routine
     * also verifies that the incoming segment flux axis qualities match
     * the flux axis qualities from existing segments. Null qualities
     * are always assumed to be valid and matching.
     * @param segments
     *   Segment
     * @param offset
     *   int
     *
     * @throws SedInconsistentException
     */
    public void addSegment (List<Segment> segments, int offset) throws SedInconsistentException, SedNoDataException
    {
        for (int ii=0; ii<segments.size (); ii++)
            this.addSegment (segments.get(ii), ii+offset);
    }


    /**
     * Remove a segment from the Sed.
     * @param segment
     *   int
     *   
     */
    public void removeSegment (int segment)
    {
        if ((this.segmentList == null) || (this.segmentList.isEmpty ())) {
            logger.warning ("There are no segments in this Sed");
            return;
        }

        if ((segment < 0) || (segment > segmentList.size ())) {
            logger.warning ("The specified segment is outside the range of existing segments.");
            return;
        }

        segmentList.remove (segment);
    }

    /**
     * Get the number of segments in the Sed.
     * @return int
     *
     */
    public int getNumberOfSegments ()
    {
        return this.segmentList.size ();
    }

    /**
     * Get the specified of segment from the Sed. 
     * @param segment
     *   int
     * @return 
     *    {@link Segment}
     *
     */
    public Segment getSegment (int segment)
    {
        return this.segmentList.get (segment);
    }


    /**
     * Sets the namespace associated with this Sed
     *
     * @param namespace
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNamespace (String namespace)
    {
        this.namespace = namespace;
    }

    /**
     * Gets the namespace associated with this Sed
     *
     * @return 
     *     {@link String }
     *
     */
    public String getNamespace ()
    {
        return this.namespace;
    }

    public boolean isSetNamespace()
    {
        return (this.namespace!= null);
    }

    /**
     * Filter the SED based on the spectral axis start and stop range. 
     * The unit must match the unit of the spectral axis values 
     * which are going to be filtered. The returned Sed will be a copy.
     * of the original containing only segments with the points in the 
     * specified range. The range is inclusive.
     * 
     * @param start
     *     double
     * @param end
     *     double
     * @param unit
     *     {@link String}
     *
     * @return {@link Sed}
     * 
     * @throws SedInconsistentException
     */
    public Sed filterSed (double start, double end, String unit) throws SedInconsistentException

    {
        return this.filterSed (new RangeParam (start, end, null, null, unit));
    }

    /**
     * Filter the SED based on the spectral axis range. 
     * The unit must match the unit of the spectral axis values 
     * which are going to be filtered. The returned Sed will be a copy.
     * of the original containing only segments with the points in the 
     * specified range. The range is inclusive.
     * 
     * @param rangeParam
     *     {@link RangeParam}
     *
     * @return {@link Sed}
     * 
     * @throws SedInconsistentException
     */
    public Sed filterSed (RangeParam rangeParam) throws SedInconsistentException
    {
        List<RangeParam> rangeParamList = new ArrayList<RangeParam> (1);
        rangeParamList.add (rangeParam);
        return this.filterSed (rangeParamList);
    }

    /**
     * Filter the SED based on a list of spectral axis ranges. 
     * The ranges in the list may have different units. Filtering will
     * not convert between units. The returned Sed will be a copy.
     * of the original containing only segments with the points in the 
     * specified ranges. The ranges are inclusive.
     * 
     * @param rangeParamList
     *    list<{@link RangeParam}>
     *
     * @return {@link Sed}
     * 
     * @throws SedInconsistentException
     */
    public Sed filterSed (List<RangeParam> rangeParamList) throws SedInconsistentException
    {
        ListIterator<Segment> segIter;
        List<RangeParam> completeRangeParams = new ArrayList<RangeParam> ();
        HashMap <String,List<RangeParam>> rangeParamTable= new HashMap <String,List<RangeParam>> ();
        RangeParam r1 = null;
        RangeParam r2 = null;
        RangeMinComparator comparator = new RangeMinComparator ();

        Sed sedCopy = (Sed)this.clone ();
        
        // collect all the range param lists by their units
        for (RangeParam rangeParam : rangeParamList)
        {
        	
        	if (!rangeParamTable.containsKey(rangeParam.getUnit ()))
        		rangeParamTable.put (rangeParam.getUnit (), new LinkedList<RangeParam> ());
        	rangeParamTable.get(rangeParam.getUnit ()).add(rangeParam);
        }
        
        for (String unit : rangeParamTable.keySet ())
        {
        	
            List<RangeParam> rangeParams = rangeParamTable.get(unit);
            ListIterator<RangeParam> iter1 = rangeParams.listIterator ();


            // sort the range parameters
            Collections.sort (rangeParams, comparator);

            if (iter1.hasNext ())
            {
                r1 = iter1.next ();
            }

            // check for overlaps in the range list and reduce the list to a minimal list
            while (iter1.hasNext ())
            {
                Double r1_min = r1.getMin ();
                Double r1_max = r1.getMax ();
                Double r2_min, r2_max;

                r2 = iter1.next ();

                r2_min = r2.getMin ();
                r2_max = r2.getMax ();
             


                // throw an error if the min or max is not set
                if (!r1.isSetMax () || !r1.isSetMin ())
                    throw new SedInconsistentException ("Both the min and max values need to be set when filtering with the range param.");
                else if (r2.isSetMax () && r2.isSetMin ()) 
                {
                    // check for overlaps in the intervals and combine them
                    // into a single interval
                    if (r1_max >= r2_min)
                    {
                        r2.setMin (r1_min);
                        if (r1_max > r2_max)
                            r2.setMax (r1_max);

                        iter1.previous ();
                        iter1.previous ();
                        iter1.remove ();
                        iter1.next ();
                    }
                }
                
                r1 = r2;
            }
        

            // check to see that the last element in the list has both
            // max and min set
            if ((r1 != null) && (!r1.isSetMax () || !r1.isSetMin ()))
                throw new SedInconsistentException ("Both the min and max values need to be set when filtering with the range param.");
 
            completeRangeParams.addAll(rangeParams);
        }
        
        segIter = sedCopy.segmentList.listIterator();
        while (segIter.hasNext())
        {
    
            List<Point> points;
            Segment segment = segIter.next ();
            segment.filter (completeRangeParams);

            points = segment.getPointsFromData ();

            // if there are no points then they all got filtered
            // out; remove the segment from the list
            if (points.isEmpty ())
                segIter.remove ();
        }

        return sedCopy;
    }



    /**
     * Read file of the specified format to populate SED object.
     *
     * Different Exceptions are thrown for different kinds of problems:
     *
     * <ul>
     * <li>an IOException is thrown if there problems are encountered in loading the file (e.g. wrong permissions, disk full);</li>
     * <li>a SedParsingException is thrown if problems are encountered in parsing the file (e.g. wrong format, invalid document);</li>
     * <li>a SedInconsistentException is thrown if there are more than one segment in the file and the axis types do not match.</li>
     * </ul>
     *
     * @param filename A String representing the absolute or relative path of the file toread.
     * @param format The format of the file to be read. Currently, SedFormat.VOT and SedFormat.FITS are supported.
     *
     * @return Sed a Sed object representation of the file.
     *
     *
     * @throws SedParsingException, SedInconsistentException, IOException
     */
    public static Sed read( String filename, SedFormat format ) throws SedParsingException, SedInconsistentException, IOException, SedNoDataException
    {

        FileInputStream fis = new FileInputStream(filename);
        return read(fis, format);

    }

    /**
     * Read file of the specified format to populate SED object.
     *
     * Different Exceptions are thrown for different kinds of problems:
     *
     * <ul>
     * <li>an IOException is thrown if there problems are encountered in loading the file (e.g. wrong permissions, disk full);</li>
     * <li>a SedParsingException is thrown if problems are encountered in parsing the file (e.g. wrong format, invalid document);</li>
     * <li>a SedInconsistentException is thrown if there are more than one segment in the file and the axis types do not match.</li>
     * </ul>
     *
     * Notice that the library may wrap opaque exceptions from third party libraries it uses to read and parse the file.
     * Please refer to the exception message for further information.
     *
     * @param is An Input Stream.
     * @param format The format of the file to be read. Currently, SedFormat.VOT and SedFormat.FITS are supported.
     *
     * @return Sed a Sed object representation of the file.
     *
     * @throws SedParsingException, SedInconsistentException, IOException
     */
    public static Sed read( InputStream is , SedFormat format ) throws SedParsingException, SedInconsistentException, IOException, SedNoDataException
    {

        return SedIOFactory.createDeserializer( format ).deserialize( is );

    }

    /**
     * Write the SED object in the specified format to an Output Stream.
     *
     * Different Exceptions are thrown for different kinds of problems:
     *
     * <ul>
     * <li>an IOException is thrown if the problems are encountered in accessing the file
(e.g. wrong permissions, disk full);</li>
     * <li>the SedWritingException wraps low level libraries Exceptions (e.g. fits/startab
le thrown exception when writing to the files);</li>
     * <li>a SedInconsistentException is thrown if the Sed object is found to be inconsist
ent and can't be serialized.</li>
     * </ul>
     *
     * @param os An Output Stream.
     * @param format The format of the file to be read. Currently, SedFormat.VOT and SedFo
rmat.FITS are supported.
     *
     * @throws SedWritingException, SedInconsistentException, IOException
     *
     *
     */
    public void write( OutputStream os, SedFormat format ) throws SedInconsistentException, SedWritingException, IOException
    {

        SedIOFactory.createSerializer( format ).serialize( os, this );

    }

    /**
     * Write the SED object in the specified format to the specified location.
     *
     * Different Exceptions are thrown for different kinds of problems:
     *
     * <ul>
     * <li>an IOException is thrown if the problems are encountered in accessing the file (e.g. wrong permissions, disk full);</li>
     * <li>the SedWritingException wraps low level libraries Exceptions (e.g. fits/startable thrown exception when writing to the files);</li>
     * <li>a SedInconsistentException is thrown if the Sed object is found to be inconsistent and can't be serialized.</li>
     * </ul>
     *
     * @param filename The filename.
     * @param format The format of the file to be read. Currently, SedFormat.VOT and SedFormat.FITS are supported.
     *
     * @throws SedWritingException, SedInconsistentException, IOException
     *
     *
     */
    public void write( String filename, SedFormat format ) throws SedInconsistentException, SedWritingException, IOException
    {

        FileOutputStream fos = new FileOutputStream(filename);
        try {
            write(fos, format);
        } finally {
            fos.close();
        }

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.segmentList != null ? this.segmentList.hashCode() : 0);
        return hash;
    }

    /**
     * Verify if <code>obj</code> is the same as the current object.
     *
     * In this implementation, two Seds are said to be the same if:
     * <ul>
     * <li>they have the same number of segments;</li>
     * <li>they have equal value arrays on both axes for each segment;</li>
     * <li>no exception is thrown while accessing axis values.</li>
     * </ul>
     *
     * This implementation assumes the segments are in the same order. At this point there is no safe way of
     * asserting whether a segment is present in the segmentList, and so there is no way of getting the right segment from
     * a different position.
     *
     * If this was possible, however, a simple check on the segmentList hash values would be sufficient to assert whether two Seds
     * represent the same object.
     *
     * @return
     *     boolean True if the Seds are the same in the sense explained above, False otherwise
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sed other = (Sed) obj;

        /*
         * if the number of segments if different, the seds are different
         */
        if(other.getNumberOfSegments() != this.getNumberOfSegments())
            return false;

        for(int i=0; i<getNumberOfSegments(); i++) {

            Segment mySegment = getSegment(i);
            Segment otherSegment = other.getSegment(i);

            if(!mySegment.equals(otherSegment))
                return false;

        }

        return true;
    }

    /**
     * Validate the Sed. The method returns true or false depending
     * on whether the Sed validates. 
     *
     * @return boolean; whether or not the Sed is valid
     */
    public boolean validate ()
    {
        List<ValidationError> errors = new ArrayList<ValidationError> ();
        return this.validate (errors);
    }

    /**
     * Validate the Sed. The method returns true or false depending
     * on whether the Sed validates. It also fills in the a list
     * of errors that occurred when validating
     *
     * @param errors
     *    List<ValidationError>
     *    {@link ValidationError}
     * @return boolean; whether or not the Sed is valid
     */
    public boolean validate (List<ValidationError> errors)
    {
        boolean valid = true;
        for (Segment segment : this.segmentList)
            valid &= segment.validate (errors);

        return valid;
    }


}
