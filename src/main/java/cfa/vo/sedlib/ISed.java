/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfa.vo.sedlib;

import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedWritingException;
import cfa.vo.sedlib.io.SedFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author mcd
 */
public interface ISed {

    /**
     * Appends a segment to the Sed. The routine also
     * verifies that the incoming segment flux axis qualities match
     * the flux axis qualities from existing segments. Null qualities
     * are always assumed to be valid and matching.
     * @param segment
     * Segment
     *
     * @throws SedInconsistentException
     */
    void addSegment(Segment segment) throws SedInconsistentException, SedNoDataException;

    /**
     * Add a segment to the Sed at particular offset. The routine also
     * verifies that the incoming segment flux axis qualities match
     * the flux axis qualities from existing segments. Null qualities
     * are always assumed to be valid and matching.
     * @param segment
     * Segment
     * @param offset
     * int
     *
     * @throws SedInconsistentException
     */
    void addSegment(Segment segment, int offset) throws SedInconsistentException, SedNoDataException;

    /**
     * Appends a list of segments to the Sed. The routine also
     * verifies that the incoming segment flux axis qualities match
     * the flux axis qualities from existing segments. Null qualities
     * are always assumed to be valid and matching.
     * @param segments
     * List<{@link Segment}>
     *
     * @throws SedInconsistentException
     */
    void addSegment(List<Segment> segments) throws SedInconsistentException, SedNoDataException;

    /**
     * Add a list of segments to the Sed starting at particular offset. The routine
     * also verifies that the incoming segment flux axis qualities match
     * the flux axis qualities from existing segments. Null qualities
     * are always assumed to be valid and matching.
     * @param segments
     * Segment
     * @param offset
     * int
     *
     * @throws SedInconsistentException
     */
    void addSegment(List<Segment> segments, int offset) throws SedInconsistentException, SedNoDataException;

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
     * boolean True if the Seds are the same in the sense explained above, False otherwise
     *
     */
    boolean equals(Object obj);

    /**
     * Filter the SED based on the spectral axis start and stop range.
     * The unit must match the unit of the spectral axis values
     * which are going to be filtered. The returned Sed will be a copy.
     * of the original containing only segments with the points in the
     * specified range. The range is inclusive.
     *
     * @param start
     * double
     * @param end
     * double
     * @param unit
     * {@link String}
     *
     * @return {@link Sed}
     *
     * @throws SedInconsistentException
     */
    Sed filterSed(double start, double end, String unit) throws SedInconsistentException;

    /**
     * Filter the SED based on the spectral axis range.
     * The unit must match the unit of the spectral axis values
     * which are going to be filtered. The returned Sed will be a copy.
     * of the original containing only segments with the points in the
     * specified range. The range is inclusive.
     *
     * @param rangeParam
     * {@link RangeParam}
     *
     * @return {@link Sed}
     *
     * @throws SedInconsistentException
     */
    Sed filterSed(RangeParam rangeParam) throws SedInconsistentException;

    /**
     * Filter the SED based on a list of spectral axis ranges.
     * The ranges in the list may have different units. Filtering will
     * not convert between units. The returned Sed will be a copy.
     * of the original containing only segments with the points in the
     * specified ranges. The ranges are inclusive.
     *
     * @param rangeParamList
     * list<{@link RangeParam}>
     *
     * @return {@link Sed}
     *
     * @throws SedInconsistentException
     */
    Sed filterSed(List<RangeParam> rangeParamList) throws SedInconsistentException;

    /**
     * Gets the namespace associated with this Sed
     *
     * @return
     * {@link String }
     *
     */
    String getNamespace();

    /**
     * Get the number of segments in the Sed.
     * @return int
     *
     */
    int getNumberOfSegments();

    /**
     * Get the specified of segment from the Sed.
     * @param segment
     * int
     * @return
     * {@link Segment}
     *
     */
    Segment getSegment(int segment);

    int hashCode();

    boolean isSetNamespace();

    /**
     * Remove a segment from the Sed.
     * @param segment
     * int
     *
     */
    void removeSegment(int segment);

    /**
     * Sets the namespace associated with this Sed
     *
     * @param namespace
     * allowed object is
     * {@link String }
     *
     */
    void setNamespace(String namespace);

    /**
     * Write the SED object in the specified format to an Output Stream.
     *
     * Different Exceptions are thrown for different kinds of problems:
     *
     * <ul>
     * <li>an IOException is thrown if the problems are encountered in accessing the file
     * (e.g. wrong permissions, disk full);</li>
     * <li>the SedWritingException wraps low level libraries Exceptions (e.g. fits/startab
     * le thrown exception when writing to the files);</li>
     * <li>a SedInconsistentException is thrown if the Sed object is found to be inconsist
     * ent and can't be serialized.</li>
     * </ul>
     *
     * @param os An Output Stream.
     * @param format The format of the file to be read. Currently, SedFormat.VOT and SedFo
     * rmat.FITS are supported.
     *
     * @throws SedWritingException, SedInconsistentException, IOException
     *
     *
     */
    void write(OutputStream os, SedFormat format) throws SedInconsistentException, SedWritingException, IOException;

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
    void write(String filename, SedFormat format) throws SedInconsistentException, SedWritingException, IOException;

}
