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

import cfa.vo.sedlib.io.IOConstants;

/**
 * A collection of VOTable specific utype information. This includes votable
 * element attributes enumerations.
 */

public class VOTableKeywords extends Utypes{

    //VOTable element and attribute name
    public final static String _ID        = "ID";
    public final static String _UTYPE     = "utype";
    public final static String _VALUE     = "value";
    public final static String _DATATYPE  = "datatype";
    public final static String _UCD       = "ucd";
    public final static String _ARRAYSIZE = "arraysize";
    public final static String _NAME      = "name";
    public final static String _UNIT      = "unit";
    public final static String _RESOURCE  = "RESOURCE";
    public final static String _TABLE     = "TABLE";
    public final static String _GROUP     = "GROUP";
    public final static String _DATA      = "DATA";
    public final static String _FIELD     = "FIELD";
    public final static String _VOTABLE   = "VOTABLE";
    public final static String _PARAM     = "PARAM";
    public final static String _TABLEDATA = "TABLEDATA";

    //To create FIELD IDs - a counter is added to distinguish IDs between TABLEs
    public final static String FLUXID = "Flux";
    public final static String FLUX_STATERRID = "FluxStatError";
    public final static String FLUX_STATERRLOWID = "FluxStatErrorLow";
    public final static String FLUX_STATERRHIGHID = "FluxStatErrorHigh";
    public final static String FLUX_SYSERRID = "FluxSysErr";
    public final static String FLUX_BINLOWID = "FluxBinLow";
    public final static String FLUX_BINHIGHID = "FluxBinHigh";
    public final static String FLUX_BINSIZEID = "FluxBinSize";
    public final static String FLUX_QUALITYID = "FluxQuality";

    public final static String SC_VALUEID       = "SCValue";
    public final static String SC_STATERRID     = "SCStatError";
    public final static String SC_STATERRLOWID  = "SCStatErrorLow";
    public final static String SC_STATERRHIGHID = "SCStatErrorHigh";
    public final static String SC_SYSERRID      = "SCSysError";
    public final static String SC_BINLOWID      = "SCBinLow";
    public final static String SC_BINHIGHID     = "SCBinHigh";
    public final static String SC_BINSIZEID     = "SCBinSize";
    public final static String SC_RESOLUTIONID  = "SCResolution";


    /**
     * Gets the string representation of a utype enumeration. The
     * namespace is removed from the utype if one exists.
     *
     */
    static public String getName (int utype, String namespace)
    {
    	if (namespace != null)
            namespace = namespace + ":";
        else
            namespace = "";

        return namespace+name[utype];

    }

    /**
     * Gets the namespace if associated with the utype. Returns
     * null if no namespace exists.
     *
     */
    static public String getNamespace (String utypeName)
    {
    	if (utypeName == null)
    		return "";
    	
        String[] utypeSplit = utypeName.split (":");
        if (utypeSplit.length == 1)
            return "";

        return utypeSplit[0];
    }

    /**
     * Gets the utype enumeration from string name. If the namespace
     * is not null; the namespace from the utype is extracted. If
     * namespaces are not the same an INVALID_UTYPE enumeration is
     * returned.
     *
     */
    static public int getUtypeFromString(String utypeName, String namespace)
    {

        if ((utypeName == null) || (utypeName.length () == 0))
            return INVALID_UTYPE;

        if (namespace != null)
        {
            String utypeNamespace = VOTableKeywords.getNamespace (utypeName);

            if ((utypeNamespace == null) || !(namespace.equals (utypeNamespace)))
                return INVALID_UTYPE;
        }

        return VOTableKeywords.getUtypeFromString (utypeName);
        
    }

    /**
     * Gets the utype enumeration from string name. The namespace
     * is ignored when performing the match.
     *
     */
    static public int getUtypeFromString(String utypeName)
    {
        Integer utypeEnum = INVALID_UTYPE;
        if ((utypeName == null) || (utypeName.length () == 0))
            return INVALID_UTYPE;

        utypeName = utypeName.replaceFirst( IOConstants.NAMESPACE_PATTERN, "" );
        utypeEnum = nameMap.get (utypeName.toLowerCase ());
        
        if (utypeEnum == null)
            utypeEnum = INVALID_UTYPE;


        return utypeEnum;
    }


    /**
     * Compares string version the utypes. Namespace is ignored and case is 
     * ignored.
     *
     */
    static public boolean compareUtypes (String utype1, String utype2)
    {

        // see they're equal
        if (utype1.equalsIgnoreCase (utype2))
            return true;

        utype1 = utype1.toLowerCase ();
        utype2 = utype2.toLowerCase ();

        // remove the namespace
        utype1 = utype1.replaceFirst( IOConstants.NAMESPACE_PATTERN, "" );
        utype2 = utype2.replaceFirst( IOConstants.NAMESPACE_PATTERN, "" );

        return utype1.equals (utype2);
    }

    /**
     *  Returns the last part of the utype. This includes whatever follows
     *  the last '.'.
     */
    static public String getLastPartOfUtype( String utype )
    {
        if ( utype == null )
        {
            return null;
        }

        // remove the namespace
        utype = utype.replaceFirst( IOConstants.NAMESPACE_PATTERN, "" );

        String[] parts = utype.split( "[.]" );

        return parts[ parts.length - 1 ];
    }

    /**
     *  Retrieve the utype without the namespace if one exists.
     */
    static public String removeNamespace( String utype )
    {
        if ( utype == null )
        {
            return null;
        }

        // remove the namespace
        utype = utype.replaceFirst( IOConstants.NAMESPACE_PATTERN, "" );

        return utype;
    }



    /**
     * Compare the string name of the utype with its enumerated counter part.
     * Namespace is ignored and case is ignored.
     *
     */
     static public boolean compare (String utypeName, int utype)
     {
         int utypeEnum = getUtypeFromString (utypeName);
         return utypeEnum == utype;
     }


}
