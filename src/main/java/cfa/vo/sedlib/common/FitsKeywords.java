
package cfa.vo.sedlib.common;

import java.util.HashMap;

/**
 * A collection of FITS specific utype information. This includes fits keyword
 * mappings, column names, and associated methods.
 */

public class FitsKeywords extends Utypes{

    static public final int   TARGET_RA = max_enum;
    static public final int   TARGET_DECL = max_enum + 1;

    static protected String[] fitsname = {
        "Spectrum.Target.Pos",
        "Spectrum.Target.Pos",
    };

    static String[] keywords = new String[max_enum+2];

    static HashMap <Integer,String>columnNames = new HashMap<Integer, String> ();


    static HashMap<String,Integer[]> fitsMap;
    
    static
    {
    	fitsMap = new HashMap<String,Integer[]> ();
    	
        // populate keywords
        // ignore keywords for data they should all be TTYPE
        for (int ii=0; ii<max_enum+2; ii++)
            keywords[ii] = "_todo"+ii;

        keywords[SED] = "SED";
        keywords[DATAMODEL] = "VOCLASS";
        keywords[LENGTH] = "DATALEN";
        keywords[TYPE] = "VOSEGT";

        keywords[TIMESI] = "TIMESDIM";
        keywords[SPECTRALSI] = "SPECSDIM";
        keywords[FLUXSI] = "FLUXSDIM";
        keywords[TARGET_NAME] = "OBJECT";
        keywords[TARGET_DESCRIPTION] = "OBJDESC";
        keywords[TARGET_CLASS] = "SRCCLASS";
        keywords[TARGET_SPECTRALCLASS] = "SPECTYPE";
        keywords[TARGET_REDSHIFT] = "REDSHIFT";
        keywords[TARGET_VARAMPL] = "TARGVAR";
        keywords[SEG_CS_ID] = "VOCSID";
        keywords[SEG_CS_SPACEFRAME_NAME] = "RADECSYS";
        keywords[SEG_CS_SPACEFRAME_UCD] = "SKY_UCD";
        keywords[SEG_CS_SPACEFRAME_REFPOS] = "SKY_REF";
        keywords[SEG_CS_SPACEFRAME_EQUINOX] = "EQUINOX";
        keywords[SEG_CS_TIMEFRAME_NAME] = "TIMESYS";
        keywords[SEG_CS_TIMEFRAME_ZERO] = "MJDREF";
        keywords[SEG_CS_SPECTRALFRAME_REFPOS] = "SPECSYS";
        keywords[SEG_CS_SPECTRALFRAME_REDSHIFT] = "REST_Z";
        keywords[SEG_CS_SPECTRALFRAME_UCD] = "TUCDn";
        keywords[SEG_CS_SPECTRALFRAME_NAME] = "SPECNAME";
 /*       keywords[SEG_CS_FLUXFRAME_NAME] = "PHBAND";
        keywords[SEG_CS_FLUXFRAME_UCD] = "PHUCD";
        keywords[SEG_CS_FLUXFRAME_ID] = "PHID";
 */
        keywords[SEG_CS_REDFRAME_NAME] = "ZNAME";
        keywords[SEG_CS_REDFRAME_DOPPLERDEF] = "TCTYPnZ";
        keywords[SEG_CS_REDFRAME_REFPOS] = "SPECSYSZ";

        keywords[SEG_CURATION_PUBLISHER] = "VOPUB";
        keywords[SEG_CURATION_REF] = "VOREF";
        keywords[SEG_CURATION_PUBID] = "VOPUBID";
        keywords[SEG_CURATION_PUBDID] = "DS_IDPUB";
        keywords[SEG_CURATION_VERSION] = "VOVER";
        keywords[SEG_CURATION_CONTACT_NAME] = "CONTACT";
        keywords[SEG_CURATION_CONTACT_EMAIL] = "EMAIL";
        keywords[SEG_CURATION_RIGHTS] = "VORIGHTS";
        keywords[SEG_CURATION_DATE] = "VODATE";
        keywords[SEG_DATAID_TITLE] = "TITLE";
        keywords[SEG_DATAID_CREATOR] = "AUTHOR";
        keywords[SEG_DATAID_COLLECTION] = "COLLECTn";
        keywords[SEG_DATAID_DATASETID] = "DS_IDENT";
        keywords[SEG_DATAID_CREATORDID] = "CR_IDENT";
        keywords[SEG_DATAID_DATE] = "DATE";
        keywords[SEG_DATAID_VERSION] = "VERSION";
        keywords[SEG_DATAID_INSTRUMENT] = "INSTRUME";
        keywords[SEG_DATAID_CREATIONTYPE] = "CRETYPE";
        keywords[SEG_DATAID_LOGO] = "VOLOGO";
        keywords[SEG_DATAID_CONTRIBUTOR] = "CONTRIBn";
        keywords[SEG_DATAID_DATASOURCE] = "DSSOURCE";
        keywords[SEG_DATAID_BANDPASS] = "SPECBAND";
        keywords[SEG_DD_SNR] = "DER_SNR";
        keywords[SEG_DD_REDSHIFT_VALUE] = "DER_Z";
        keywords[SEG_DD_REDSHIFT_ACC_STATERR] = "DER_ZERR";
        keywords[SEG_DD_REDSHIFT_ACC_CONFIDENCE] = "DER_ZCNF";
        keywords[SEG_DD_VARAMPL] = "DER_VAR";
        keywords[SEG_CHAR_FLUXAXIS_NAME] = "FLUXNAME";
        keywords[SEG_CHAR_FLUXAXIS_UNIT] = "FLUXUNIT";
        keywords[SEG_CHAR_FLUXAXIS_UCD] = "FLUX_UCD";
        keywords[SEG_CHAR_FLUXAXIS_ACC_STATERR] = "STAT_ERR";
        keywords[SEG_CHAR_FLUXAXIS_ACC_SYSERR] = "SYS_ERR";
        keywords[SEG_CHAR_FLUXAXIS_CAL] = "FLUX_CAL";
        keywords[SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE] = "SPEC_VAL";
        keywords[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT] = "SPEC_BW";
        keywords[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN] = "TDMINn";
        keywords[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX] = "TDMAXn";
        keywords[SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "SPEC_FIL";
        keywords[SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPEXT] = "SPEC_BIN"; // duplicate intended
        keywords[SEG_CHAR_SPECTRALAXIS_ACC_BINSIZE] = "SPEC_BIN";
        keywords[SEG_CHAR_SPECTRALAXIS_ACC_STATERR] = "SPEC_ERR";
        keywords[SEG_CHAR_SPECTRALAXIS_ACC_SYSERR] = "SPEC_SYE";
        keywords[SEG_CHAR_SPECTRALAXIS_CAL] = "SPEC_CAL";
        keywords[SEG_CHAR_SPECTRALAXIS_RESOLUTION] = "SPEC_RES";
        keywords[SEG_CHAR_SPECTRALAXIS_RESPOW] = "SPEC_RP";
        keywords[SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_EXTENT] = "SPECWID";
        keywords[SEG_CHAR_SPECTRALAXIS_UNIT] = "SPEC_UNI";
        keywords[SEG_CHAR_SPECTRALAXIS_UCD] = "SPEC_UCD";
        keywords[SEG_CHAR_SPATIALAXIS_UCD] = "SKY_UCD";
        keywords[SEG_CHAR_SPATIALAXIS_CAL] = "SKY_CAL";
        keywords[SEG_CHAR_SPATIALAXIS_RESOLUTION] = "SKY_RES";
        keywords[SEG_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT] = "APERTURE";
        keywords[SEG_CHAR_SPATIALAXIS_COV_SUPPORT_AREA] = "REGION";
        keywords[SEG_CHAR_SPATIALAXIS_COV_SUPPORT_EXTENT] = "AREA";
        keywords[SEG_CHAR_SPATIALAXIS_ACC_STATERR] = "SKY_ERR";
        keywords[SEG_CHAR_SPATIALAXIS_ACC_SYSERR] = "SKY_SYE";
        keywords[SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "SKY_FILL";
        keywords[SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT] = "TCDLT";
        keywords[SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_RA] = "RA";
        keywords[SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_DEC] = "DEC";
        keywords[SEG_CHAR_TIMEAXIS_UNIT] = "TIMEUNIT";
        keywords[SEG_CHAR_TIMEAXIS_COV_LOC_VALUE] = "TMID";
        keywords[SEG_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT] = "TELAPSE";
        keywords[SEG_CHAR_TIMEAXIS_COV_BOUNDS_MIN] = "TSTART";
        keywords[SEG_CHAR_TIMEAXIS_COV_BOUNDS_MAX] = "TSTOP";
        keywords[SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "DTCOR";
        keywords[SEG_CHAR_TIMEAXIS_ACC_BINSIZE] = "TIMEDEL";
        keywords[SEG_CHAR_TIMEAXIS_ACC_STATERR] = "TIME_ERR";
        keywords[SEG_CHAR_TIMEAXIS_ACC_SYSERR] = "TIME_SYE";
        keywords[SEG_CHAR_TIMEAXIS_CAL] = "TIME_CAL";
        keywords[SEG_CHAR_TIMEAXIS_RESOLUTION] = "TIME_RES";
        keywords[SEG_CHAR_TIMEAXIS_COV_SUPPORT_EXTENT] = "EXPOSURE";
        keywords[SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPEXT] = "TIMEDEL"; // duplicate intended
        keywords[TARGET_RA] = "RA_TARG";
        keywords[TARGET_DECL] = "DEC_TARG";

        
        // populate default column names
        columnNames.put (SEG_DATA_FLUXAXIS_VALUE, "FLUX");
        columnNames.put (SEG_DATA_FLUXAXIS_ACC_STATERR, "ERR");
        columnNames.put (SEG_DATA_FLUXAXIS_ACC_STATERRLOW, "ERR_LO");
        columnNames.put (SEG_DATA_FLUXAXIS_ACC_STATERRHIGH, "ERR_HI");
        columnNames.put (SEG_DATA_FLUXAXIS_ACC_SYSERR, "SYS_ERR");
        columnNames.put (SEG_DATA_FLUXAXIS_QUALITY, "QUALITY");
//        columnNames.put (?, "QUALn");
        columnNames.put (SEG_DATA_SPECTRALAXIS_VALUE, "SPEC");
        // the spectral axis meta data is the spectral axis value 
        // plus the extension
        columnNames.put (SEG_DATA_SPECTRALAXIS_ACC_BINSIZE, "SPEC_BIN");
        columnNames.put (SEG_DATA_SPECTRALAXIS_ACC_BINLOW, "SPEC_LO");
        columnNames.put (SEG_DATA_SPECTRALAXIS_ACC_BINHIGH, "SPEC_HI");
        columnNames.put (SEG_DATA_SPECTRALAXIS_ACC_STATERR, "SPEC_ERR");
        columnNames.put (SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW, "SPEC_ELO");
        columnNames.put (SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH, "SPEC_EHI");
        columnNames.put (SEG_DATA_SPECTRALAXIS_ACC_SYSERR, "SPEC_SYE");
        columnNames.put (SEG_DATA_SPECTRALAXIS_RESOLUTION, "SPEC_RES");
        columnNames.put (SEG_DATA_TIMEAXIS_VALUE, "TIME");
        columnNames.put (SEG_DATA_TIMEAXIS_ACC_BINLOW, "TIME_LO");
        columnNames.put (SEG_DATA_TIMEAXIS_ACC_BINHIGH, "TIME_HI");
        // this was not specified in the spec and has been artifically added
        columnNames.put (SEG_DATA_TIMEAXIS_ACC_BINSIZE, "TIMEDEL");

        columnNames.put (SEG_DATA_TIMEAXIS_RESOLUTION, "TIME_RES");
        columnNames.put (SEG_DATA_TIMEAXIS_ACC_STATERR, "TIME_ERR");
        columnNames.put (SEG_DATA_TIMEAXIS_ACC_STATERRLOW, "TIME_ELO");
        columnNames.put (SEG_DATA_TIMEAXIS_ACC_STATERRHIGH, "TIME_EHI");
        columnNames.put (SEG_DATA_TIMEAXIS_ACC_SYSERR, "TIME_SYE");
        columnNames.put (SEG_DATA_BGM_VALUE, "BGFLUX");
        columnNames.put (SEG_DATA_BGM_ACC_STATERR, "SPEC_BIN");
        columnNames.put (SEG_DATA_BGM_ACC_STATERRLOW, "SPEC_LO");
        columnNames.put (SEG_DATA_BGM_ACC_STATERRHIGH, "SPEC_HI");
        columnNames.put (SEG_DATA_BGM_ACC_SYSERR, "SPEC_ERR");
        columnNames.put (SEG_DATA_BGM_QUALITY, "SPEC_ELO");


        // populate maps

        for (int ii=0; ii < keywords.length; ii++) 
        {
            if (fitsMap.containsKey (keywords[ii]))
            {
                Integer values[] = fitsMap.get(keywords[ii]);
                Integer newValues[] = new Integer [values.length+1];
                System.arraycopy (values, 0, values, 0, values.length);
                newValues[values.length] = ii;
            }
            else
            {
                Integer values[] = new Integer[1];
                values[0] = ii;
                fitsMap.put (keywords[ii], values);
            }
        }
    }


    /**
     * Gets the keyword associated with the specified utype enumeration.
     *
     */
    static public String getKeyword (int utype)
    {
        return keywords[utype];
    }


    /**
     * Gets the enumerated utype associated with the specified keyword. Its
     * possible the multiple keywords are associated with a single
     * utype. Therefore an array of utypes are returned.
     *
     */
    static public Integer[] getUtypeFromKeyword(String compName)
    {
        Integer utypeEnum[] = {INVALID_UTYPE};
        if ((compName == null) || (compName.length () == 0))
            return utypeEnum;

        utypeEnum = fitsMap.get (compName);

        if (utypeEnum == null)
        {
            // several keywords have variable numbers in them which 
            // can be remapped as the letter n
            if ((compName.matches ("^COLLECT[1-9][0-9]*$")) ||
                (compName.matches ("^CONTRIB[1-9][0-9]*$")) ||
                (compName.matches ("^TDMIN[1-9][0-9]*$")) ||
                (compName.matches ("^TDMAX[1-9][0-9]*$")) ||
                (compName.matches ("^TCDLT[1-9][0-9]*$")) ||
                (compName.matches ("^TCTYP[1-9][0-9]*Z$")))
            {
                compName = compName.replaceFirst ("[1-9][0-9]*", "n");
                utypeEnum = fitsMap.get (compName);
            }
            else
            {
                utypeEnum = new Integer[1];
                utypeEnum[0] = INVALID_UTYPE;
            }
        }
            
        return utypeEnum;
    }

    /**
     * Gets the string representation of a utype enumeration.
     *
     */
    static public String getName (int utype)
    {
        if (utype < max_enum)
            return name[utype];
        return fitsname[utype-max_enum];
    }

    /**
     * Gets the default column associated with the specified utype enumeration.
     *
     */
    static public String getDefaultColumnName (int utype)
    {
        return columnNames.get(utype);
    }

    /**
     * Gets the number of utypes.
     *
     */
    static public int getNumberOfUtypes ()
    {
        return max_enum+2;
    }

    /**
     * Compare the string name of the utype with its enumerated counter part.
     *
     */
     static public boolean compare (String utypeName, int utype)
     {
         boolean compareVal = false;
         int utypeEnum = getUtypeFromString (utypeName);

         // first try the fits specific values
         for (int ii=0; ii<fitsname.length; ii++)
         {
             if (utypeName.equals (fitsname[ii]) &&
                 ((ii+max_enum) == utype))
                 compareVal = true;
         
         }
         
         if (!compareVal)
         {
        	 utypeEnum = getUtypeFromString (utypeName);
             compareVal = utypeEnum == utype;
         }
 
         return compareVal;
     }


}
