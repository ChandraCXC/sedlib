package cfa.vo.sedlib.common;

import java.util.HashMap;

/**
 * This class contains constant utypes enumerations and their string value 
   associations.  The class also contains methods to retrieve utypes and
   other associations.

   NOTE: This is class instead of an enumeration because as of
   Java 1.5 you cannot extend the Enum class. Using constants
   was a simple workaround.
 */

public class Utypes
{
    static protected final int max_enum = 362; // an easy way to let sub classes extend the enumerations


    //Enumerations
    static public final int   INVALID_UTYPE    =  -1;
    static public final int   SED              =   0;
    static public final int   DATAMODEL        =   2;
    static public final int   LENGTH           =   3;
    static public final int   TYPE             =   4;
    static public final int   TIMESI           =  11;
    static public final int   SPECTRALSI       =  12;
    static public final int   FLUXSI           =  13;
    static public final int   TARGET           =  14;
    static public final int   TARGET_NAME      =  15;
    static public final int   TARGET_DESCRIPTION       =  16;
    static public final int   TARGET_CLASS     =  17;
    static public final int   TARGET_SPECTRALCLASS     =  18;
    static public final int   TARGET_REDSHIFT  =  19;
    static public final int   TARGET_POS       =  20;
    static public final int   TARGET_VARAMPL   =  21;
    static public final int   SEG_CS           =  22;
    static public final int   SEG_CS_ID        =  23;
    static public final int   SEG_CS_HREF      =  24;
    static public final int   SEG_CS_UCD       =  25;
    static public final int   SEG_CS_TYPE      =  26;
    static public final int   SEG_CS_IDREF             =  27;
    static public final int   SEG_CS_SPACEFRAME        =  28;
    static public final int   SEG_CS_SPACEFRAME_ID     =  29;
    static public final int   SEG_CS_SPACEFRAME_NAME       =  30;
    static public final int   SEG_CS_SPACEFRAME_UCD    =  31;
    static public final int   SEG_CS_SPACEFRAME_REFPOS     =  32;
    static public final int   SEG_CS_SPACEFRAME_EQUINOX    =  33;
    static public final int   SEG_CS_TIMEFRAME     =  34;
    static public final int   SEG_CS_TIMEFRAME_ID  =  35;
    static public final int   SEG_CS_TIMEFRAME_NAME    =  36;
    static public final int   SEG_CS_TIMEFRAME_UCD     =  37;
    static public final int   SEG_CS_TIMEFRAME_ZERO    =  38;
    static public final int   SEG_CS_TIMEFRAME_REFPOS  =  39;
    static public final int   SEG_CS_SPECTRALFRAME     =  40;
    static public final int   SEG_CS_SPECTRALFRAME_ID      =  41;
    static public final int   SEG_CS_SPECTRALFRAME_NAME    =  42;
    static public final int   SEG_CS_SPECTRALFRAME_UCD     =  43;
    static public final int   SEG_CS_SPECTRALFRAME_REFPOS      =  44;
    static public final int   SEG_CS_SPECTRALFRAME_REDSHIFT    =  45;
    static public final int   SEG_CS_REDFRAME      =  46;
    static public final int   SEG_CS_REDFRAME_ID       =  47;
    static public final int   SEG_CS_REDFRAME_NAME     =  48;
    static public final int   SEG_CS_REDFRAME_UCD      =  49;
    static public final int   SEG_CS_REDFRAME_REFPOS   =  50;
    static public final int   SEG_CS_REDFRAME_DOPPLERDEF   =  51;
    static public final int   SEG_CS_GENFRAME      =  52;
    static public final int   SEG_CS_GENFRAME_ID       =  53;
    static public final int   SEG_CS_GENFRAME_NAME     =  54;
    static public final int   SEG_CS_GENFRAME_UCD      =  55;
    static public final int   SEG_CS_GENFRAME_REFPOS       =  56;
    static public final int   SEG_CURATION         =  57;
    static public final int   SEG_CURATION_PUBLISHER       =  58;
    static public final int   SEG_CURATION_REF     =  59;
    static public final int   SEG_CURATION_PUBID       =  60;
    static public final int   SEG_CURATION_PUBDID      =  61;
    static public final int   SEG_CURATION_VERSION     =  62;
    static public final int   SEG_CURATION_CONTACT     =  63;
    static public final int   SEG_CURATION_CONTACT_NAME    =  64;
    static public final int   SEG_CURATION_CONTACT_EMAIL   =  65;
    static public final int   SEG_CURATION_RIGHTS      =  66;
    static public final int   SEG_CURATION_DATE        =  67;
    static public final int   SEG_DATAID       =  68;
    static public final int   SEG_DATAID_TITLE     =  69;
    static public final int   SEG_DATAID_CREATOR       =  70;
    static public final int   SEG_DATAID_COLLECTION    =  71;
    static public final int   SEG_DATAID_DATASETID     =  72;
    static public final int   SEG_DATAID_CREATORDID    =  73;
    static public final int   SEG_DATAID_DATE      =  74;
    static public final int   SEG_DATAID_VERSION       =  75;
    static public final int   SEG_DATAID_INSTRUMENT    =  76;
    static public final int   SEG_DATAID_CREATIONTYPE      =  77;
    static public final int   SEG_DATAID_LOGO      =  78;
    static public final int   SEG_DATAID_CONTRIBUTOR       =  79;
    static public final int   SEG_DATAID_DATASOURCE    =  80;
    static public final int   SEG_DATAID_BANDPASS      =  81;
    static public final int   SEG_DD       =  82;
    static public final int   SEG_DD_SNR       =  83;
    static public final int   SEG_DD_REDSHIFT      =  84;
    static public final int   SEG_DD_REDSHIFT_VALUE    =  85;
    static public final int   SEG_DD_REDSHIFT_ACC      =  86;
    static public final int   SEG_DD_REDSHIFT_ACC_STATERR      =  87;
    static public final int   SEG_DD_REDSHIFT_ACC_CONFIDENCE   =  88;
    static public final int   SEG_DD_VARAMPL       =  89;
    static public final int   SEG_DD_REDSHIFT_ACC_BINLOW   =  90;
    static public final int   SEG_DD_REDSHIFT_ACC_BINHIGH      =  91;
    static public final int   SEG_DD_REDSHIFT_ACC_BINSIZE      =  92;
    static public final int   SEG_DD_REDSHIFT_ACC_STATERRLOW   =  93;
    static public final int   SEG_DD_REDSHIFT_ACC_STATERRHIGH  =  94;
    static public final int   SEG_DD_REDSHIFT_ACC_SYSERR   =  95;
    static public final int   SEG_DD_REDSHIFT_QUALITY      =  96;
    static public final int   SEG_DD_REDSHIFT_RESOLUTION   =  97;
    static public final int   SEG_CHAR         =  98;
    static public final int   SEG_CHAR_CHARAXIS        =  99;
    static public final int   SEG_CHAR_CHARAXIS_NAME       = 100;
    static public final int   SEG_CHAR_CHARAXIS_UNIT       = 101;
    static public final int   SEG_CHAR_CHARAXIS_UCD    = 102;
    static public final int   SEG_CHAR_CHARAXIS_COV    = 103;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC    = 104;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_VALUE  = 105;
    static public final int   SEG_CHAR_CHARAXIS_COV_BOUNDS     = 106;
    static public final int   SEG_CHAR_CHARAXIS_COV_BOUNDS_MIN     = 107;
    static public final int   SEG_CHAR_CHARAXIS_COV_BOUNDS_MAX     = 108;
    static public final int   SEG_CHAR_CHARAXIS_COV_BOUNDS_EXTENT  = 109;
    static public final int   SEG_CHAR_CHARAXIS_COV_BOUNDS_START   = 110;
    static public final int   SEG_CHAR_CHARAXIS_COV_BOUNDS_STOP    = 111;
    static public final int   SEG_CHAR_CHARAXIS_SAMPPREC_SAMPPRECREFVAL = 112;
    static public final int   SEG_CHAR_CHARAXIS_COV_SUPPORT    = 113;
    static public final int   SEG_CHAR_CHARAXIS_COV_SUPPORT_AREA   = 114;
    static public final int   SEG_CHAR_CHARAXIS_ACC    = 115;
    static public final int   SEG_CHAR_CHARAXIS_ACC_BINSIZE    = 116;
    static public final int   SEG_CHAR_CHARAXIS_ACC_STATERR    = 117;
    static public final int   SEG_CHAR_CHARAXIS_ACC_SYSERR     = 118;
    static public final int   SEG_CHAR_CHARAXIS_CAL    = 119;
    static public final int   SEG_CHAR_CHARAXIS_RESOLUTION     = 120;
    static public final int   SEG_CHAR_CHARAXIS_SAMPPREC   = 121;
    static public final int   SEG_CHAR_CHARAXIS_SAMPPREC_SAMPEXT   = 122;
    static public final int   SEG_CHAR_FLUXAXIS        = 123;
    static public final int   SEG_CHAR_FLUXAXIS_NAME       = 124;
    static public final int   SEG_CHAR_FLUXAXIS_UNIT       = 125;
    static public final int   SEG_CHAR_FLUXAXIS_UCD    = 126;
    static public final int   SEG_CHAR_FLUXAXIS_ACC    = 127;
    static public final int   SEG_CHAR_FLUXAXIS_ACC_BINLOW     = 128;
    static public final int   SEG_CHAR_FLUXAXIS_ACC_BINHIGH    = 129;
    static public final int   SEG_CHAR_FLUXAXIS_ACC_BINSIZE    = 130;
    static public final int   SEG_CHAR_FLUXAXIS_ACC_STATERRLOW     = 131;
    static public final int   SEG_CHAR_FLUXAXIS_ACC_STATERRHIGH    = 132;
    static public final int   SEG_CHAR_FLUXAXIS_ACC_STATERR    = 133;
    static public final int   SEG_CHAR_FLUXAXIS_ACC_SYSERR     = 134;
    static public final int   SEG_CHAR_FLUXAXIS_ACC_CONFIDENCE     = 135;
    static public final int   SEG_CHAR_FLUXAXIS_CAL    = 136;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_VALUE  = 137;
    static public final int   SEG_CHAR_FLUXAXIS_RESOLUTION     = 138;
    static public final int   SEG_CHAR_FLUXAXIS_SAMPPREC   = 139;
    static public final int   SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPEXT   = 140;
    static public final int   SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL = 141;
    static public final int   SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL_FILL = 142;
    static public final int   SEG_CHAR_FLUXAXIS_COV    = 143;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC    = 144;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_RESOLUTION = 145;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC    = 146;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINSIZE    = 147;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINLOW = 148;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINHIGH    = 149;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERR    = 150;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRLOW = 151;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRHIGH = 152;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC_CONFIDENCE = 153;
    static public final int   SEG_CHAR_FLUXAXIS_COV_LOC_ACC_SYSERR = 154;
    static public final int   SEG_CHAR_FLUXAXIS_COV_BOUNDS     = 155;
    static public final int   SEG_CHAR_FLUXAXIS_COV_BOUNDS_EXTENT  = 156;
    static public final int   SEG_CHAR_FLUXAXIS_COV_BOUNDS_MIN     = 157;
    static public final int   SEG_CHAR_FLUXAXIS_COV_BOUNDS_MAX     = 158;
    static public final int   SEG_CHAR_FLUXAXIS_COV_SUPPORT    = 159;
    static public final int   SEG_CHAR_FLUXAXIS_COV_SUPPORT_RANGE  = 160;
    static public final int   SEG_CHAR_FLUXAXIS_COV_SUPPORT_AREA   = 161;
    static public final int   SEG_CHAR_FLUXAXIS_COV_SUPPORT_EXTENT = 162;
    static public final int   SEG_CHAR_SPECTRALAXIS    = 163;
    static public final int   SEG_CHAR_SPECTRALAXIS_NAME   = 164;
    static public final int   SEG_CHAR_SPECTRALAXIS_UNIT   = 165;
    static public final int   SEG_CHAR_SPECTRALAXIS_UCD    = 166;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV    = 167;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC    = 168;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE  = 169;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC    = 170;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINLOW = 171;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINHIGH = 172;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINSIZE = 173;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERRLOW = 174;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERRHIGH = 175;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERR = 176;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_SYSERR = 177;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_CONFIDENCE = 178;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_LOC_RESOLUTION = 179;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_BOUNDS     = 180;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN = 181;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX = 182;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT  = 183;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_START   = 184;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP    = 185;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_SUPPORT    = 186;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_EXTENT = 187;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_RANGE  = 188;
    static public final int   SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_AREA   = 189;
    static public final int   SEG_CHAR_SPECTRALAXIS_SAMPPREC   = 190;
    static public final int   SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPEXT   = 191;
    static public final int   SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL = 192;
    static public final int   SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL = 193;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC    = 194;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC_BINLOW     = 195;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC_BINHIGH    = 196;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC_BINSIZE    = 197;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC_STATERRLOW = 198;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC_STATERRHIGH    = 199;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC_STATERR    = 200;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC_SYSERR     = 201;
    static public final int   SEG_CHAR_SPECTRALAXIS_ACC_CONFIDENCE = 202;
    static public final int   SEG_CHAR_SPECTRALAXIS_CAL    = 203;
    static public final int   SEG_CHAR_SPECTRALAXIS_RESOLUTION     = 204;
    static public final int   SEG_CHAR_SPECTRALAXIS_RESPOW     = 205;
    static public final int   SEG_CHAR_SPATIALAXIS     = 206;
    static public final int   SEG_CHAR_SPATIALAXIS_NAME    = 207;
    static public final int   SEG_CHAR_SPATIALAXIS_UNIT    = 208;
    static public final int   SEG_CHAR_SPATIALAXIS_UCD     = 209;
    static public final int   SEG_CHAR_SPATIALAXIS_CAL     = 210;
    static public final int   SEG_CHAR_SPATIALAXIS_RESOLUTION  = 211;
    static public final int   SEG_CHAR_SPATIALAXIS_COV     = 212;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC     = 213;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE   = 214;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_BOUNDS  = 215;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_BOUNDS_MIN  = 216;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_BOUNDS_MAX  = 217;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT   = 218;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_SUPPORT     = 219;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_SUPPORT_RANGE   = 220;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_SUPPORT_AREA    = 221;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_SUPPORT_EXTENT  = 222;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC     = 223;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC_STATERR     = 224;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC_SYSERR  = 225;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC_STATERRLOW  = 226;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC_STATERRHIGH = 227;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC_BINLOW  = 228;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC_BINHIGH     = 229;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC_BINSIZE     = 230;
    static public final int   SEG_CHAR_SPATIALAXIS_ACC_CONFIDENCE  = 231;
    static public final int   SEG_CHAR_SPATIALAXIS_SAMPPREC    = 232;
    static public final int   SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT    = 233;
    static public final int   SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL = 234;
    static public final int   SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL = 235;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_RESOLUTION  = 236;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_RA    = 237;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_DEC   = 238;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC     = 239;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINSIZE = 240;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINLOW  = 241;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINHIGH = 242;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERR = 243;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERRLOW = 244;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERRHIGH = 245;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_CONFIDENCE = 246;
    static public final int   SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_SYSERR  = 247;
    static public final int   SEG_CHAR_TIMEAXIS        = 248;
    static public final int   SEG_CHAR_TIMEAXIS_NAME       = 249;
    static public final int   SEG_CHAR_TIMEAXIS_UNIT       = 250;
    static public final int   SEG_CHAR_TIMEAXIS_UCD    = 251;
    static public final int   SEG_CHAR_TIMEAXIS_COV    = 252;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC    = 253;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_VALUE  = 254;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC    = 255;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINSIZE    = 256;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINLOW = 257;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINHIGH    = 258;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERR    = 259;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERRLOW = 260;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERRHIGH = 261;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC_CONFIDENCE = 262;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_ACC_SYSERR = 263;
    static public final int   SEG_CHAR_TIMEAXIS_COV_BOUNDS     = 264;
    static public final int   SEG_CHAR_TIMEAXIS_COV_BOUNDS_MIN     = 265;
    static public final int   SEG_CHAR_TIMEAXIS_COV_BOUNDS_MAX     = 266;
    static public final int   SEG_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT  = 267;
    static public final int   SEG_CHAR_TIMEAXIS_COV_BOUNDS_START   = 268;
    static public final int   SEG_CHAR_TIMEAXIS_COV_BOUNDS_STOP    = 269;
    static public final int   SEG_CHAR_TIMEAXIS_COV_SUPPORT    = 270;
    static public final int   SEG_CHAR_TIMEAXIS_COV_SUPPORT_AREA   = 271;
    static public final int   SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL_FILL = 272;
    static public final int   SEG_CHAR_TIMEAXIS_ACC    = 273;
    static public final int   SEG_CHAR_TIMEAXIS_ACC_BINLOW     = 274;
    static public final int   SEG_CHAR_TIMEAXIS_ACC_BINHIGH    = 275;
    static public final int   SEG_CHAR_TIMEAXIS_ACC_BINSIZE    = 276;
    static public final int   SEG_CHAR_TIMEAXIS_ACC_STATERR    = 277;
    static public final int   SEG_CHAR_TIMEAXIS_ACC_SYSERR     = 278;
    static public final int   SEG_CHAR_TIMEAXIS_ACC_STATERRLOW     = 279;
    static public final int   SEG_CHAR_TIMEAXIS_ACC_STATERRHIGH    = 280;
    static public final int   SEG_CHAR_TIMEAXIS_ACC_CONFIDENCE     = 281;
    static public final int   SEG_CHAR_TIMEAXIS_CAL    = 282;
    static public final int   SEG_CHAR_TIMEAXIS_RESOLUTION     = 283;
    static public final int   SEG_CHAR_TIMEAXIS_SAMPPREC   = 284;
    static public final int   SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPEXT   = 285;
    static public final int   SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL = 286;
    static public final int   SEG_CHAR_TIMEAXIS_COV_SUPPORT_EXTENT = 287;
    static public final int   SEG_CHAR_TIMEAXIS_COV_LOC_RESOLUTION = 288;
    static public final int   SEG_DATA_TIMEAXIS_ACC_CONFIDENCE     = 289;
    static public final int   SEG_CHAR_TIMEAXIS_COV_SUPPORT_RANGE  = 290;
    static public final int   SEG_DATA         = 291;
    static public final int   SEG_DATA_FLUXAXIS        = 292;
    static public final int   SEG_DATA_FLUXAXIS_VALUE      = 293;
    static public final int   SEG_DATA_FLUXAXIS_QUALITY    = 294;
    static public final int   SEG_DATA_FLUXAXIS_ACC    = 295;
    static public final int   SEG_DATA_FLUXAXIS_ACC_BINLOW     = 296;
    static public final int   SEG_DATA_FLUXAXIS_ACC_BINHIGH    = 297;
    static public final int   SEG_DATA_FLUXAXIS_ACC_BINSIZE    = 298;
    static public final int   SEG_DATA_FLUXAXIS_ACC_STATERRLOW     = 299;
    static public final int   SEG_DATA_FLUXAXIS_ACC_STATERRHIGH    = 300;
    static public final int   SEG_DATA_FLUXAXIS_ACC_STATERR    = 301;
    static public final int   SEG_DATA_FLUXAXIS_ACC_SYSERR     = 302;
    static public final int   SEG_DATA_FLUXAXIS_ACC_CONFIDENCE     = 303;
    static public final int   SEG_DATA_FLUXAXIS_RESOLUTION     = 304;
    static public final int   SEG_DATA_SPECTRALAXIS    = 305;
    static public final int   SEG_DATA_SPECTRALAXIS_VALUE      = 306;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC    = 307;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC_STATERR    = 308;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW = 309;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH    = 310;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC_BINLOW     = 311;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC_BINHIGH    = 312;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC_BINSIZE    = 313;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC_SYSERR     = 314;
    static public final int   SEG_DATA_SPECTRALAXIS_RESOLUTION     = 315;
    static public final int   SEG_DATA_SPECTRALAXIS_ACC_CONFIDENCE = 316;
    static public final int   SEG_DATA_TIMEAXIS        = 317;
    static public final int   SEG_DATA_TIMEAXIS_VALUE      = 318;
    static public final int   SEG_DATA_TIMEAXIS_ACC    = 319;
    static public final int   SEG_DATA_TIMEAXIS_ACC_STATERR    = 320;
    static public final int   SEG_DATA_TIMEAXIS_ACC_STATERRLOW     = 321;
    static public final int   SEG_DATA_TIMEAXIS_ACC_STATERRHIGH    = 322;
    static public final int   SEG_DATA_TIMEAXIS_ACC_SYSERR     = 323;
    static public final int   SEG_DATA_TIMEAXIS_ACC_BINLOW     = 324;
    static public final int   SEG_DATA_TIMEAXIS_ACC_BINHIGH    = 325;
    static public final int   SEG_DATA_TIMEAXIS_ACC_BINSIZE    = 326;
    static public final int   SEG_DATA_TIMEAXIS_RESOLUTION     = 327;
    static public final int   SEG_DATA_BGM         = 328;
    static public final int   SEG_DATA_BGM_VALUE       = 329;
    static public final int   SEG_DATA_BGM_QUALITY     = 330;
    static public final int   SEG_DATA_BGM_ACC     = 331;
    static public final int   SEG_DATA_BGM_ACC_STATERR     = 332;
    static public final int   SEG_DATA_BGM_ACC_STATERRLOW      = 333;
    static public final int   SEG_DATA_BGM_ACC_STATERRHIGH     = 334;
    static public final int   SEG_DATA_BGM_ACC_SYSERR      = 335;
    static public final int   SEG_DATA_BGM_RESOLUTION      = 336;
    static public final int   SEG_DATA_BGM_ACC_BINLOW      = 337;
    static public final int   SEG_DATA_BGM_ACC_BINHIGH     = 338;
    static public final int   SEG_DATA_BGM_ACC_BINSIZE     = 339;
    static public final int   SEG_DATA_BGM_ACC_CONFIDENCE      = 340;
    static public final int   CUSTOM       = 341;
    static public final int   SEG          = 342;
    static public final int   SEG_CHAR_CHARAXIS_ACC_BINLOW     = 343;
    static public final int   SEG_CHAR_CHARAXIS_ACC_BINHIGH    = 344;
    static public final int   SEG_CHAR_CHARAXIS_ACC_STATERRLOW     = 345;
    static public final int   SEG_CHAR_CHARAXIS_ACC_STATERRHIGH    = 346;
    static public final int   SEG_CHAR_CHARAXIS_ACC_CONFIDENCE     = 347;
    static public final int   SEG_CHAR_CHARAXIS_SAMPPREC_SAMPPRECREFVAL_FILL = 348;
    static public final int   SEG_CHAR_CHARAXIS_COV_SUPPORT_EXTENT = 349;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC_SYSERR = 350;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC_CONFIDENCE = 351;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINSIZE = 352;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINLOW = 353;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINHIGH = 354;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERR = 355;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRLOW = 356;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRHIGH = 357;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_RESOLUTION = 358;
    static public final int   SEG_CHAR_CHARAXIS_COV_LOC_ACC=359;
    static public final int   SEG_CHAR_CHARAXIS_COV_SUPPORT_RANGE=360;
    static public final int   SPEC=361;


    static final String[] name = new String[max_enum];
    static final String[] ucd = new String[max_enum];

    static protected final HashMap<String,Integer> nameMap = new HashMap<String,Integer> ();
    static
    {

        name[SED] = "Spectrum.Sed";
        name[DATAMODEL] = "Spectrum.DataModel";
        name[LENGTH] = "Spectrum.Length";
        name[TYPE] = "Spectrum.Type";
        name[TIMESI] = "Spectrum.TimeSI";
        name[SPECTRALSI] = "Spectrum.SpectralSI";
        name[FLUXSI] = "Spectrum.FluxSI";
        name[TARGET] = "Spectrum.Target";
        name[TARGET_NAME] = "Spectrum.Target.Name";
        name[TARGET_DESCRIPTION] = "Spectrum.Target.Description";
        name[TARGET_CLASS] = "Spectrum.Target.Class";
        name[TARGET_SPECTRALCLASS] = "Spectrum.Target.SpectralClass";
        name[TARGET_REDSHIFT] = "Spectrum.Target.Redshift";
        name[TARGET_POS] = "Spectrum.Target.Pos";
        name[TARGET_VARAMPL] = "Spectrum.Target.VarAmpl";
        name[SEG_CS] = "Spectrum.CoordSys";
        name[SEG_CS_ID] = "Spectrum.CoordSys.ID";
        name[SEG_CS_HREF] = "Spectrum.CoordSys.Href";
        name[SEG_CS_UCD] = "Spectrum.CoordSys.Ucd";
        name[SEG_CS_TYPE] = "Spectrum.CoordSys.Type";
        name[SEG_CS_IDREF] = "Spectrum.CoordSys.Idref";
        name[SEG_CS_SPACEFRAME] = "Spectrum.CoordSys.SpaceFrame";
        name[SEG_CS_SPACEFRAME_ID] = "Spectrum.CoordSys.SpaceFrame.ID";
        name[SEG_CS_SPACEFRAME_NAME] = "Spectrum.CoordSys.SpaceFrame.Name";
        name[SEG_CS_SPACEFRAME_UCD] = "Spectrum.CoordSys.SpaceFrame.UCD";
        name[SEG_CS_SPACEFRAME_REFPOS] = "Spectrum.CoordSys.SpaceFrame.RefPos";
        name[SEG_CS_SPACEFRAME_EQUINOX] = "Spectrum.CoordSys.SpaceFrame.Equinox";
        name[SEG_CS_TIMEFRAME] = "Spectrum.CoordSys.TimeFrame";
        name[SEG_CS_TIMEFRAME_ID] = "Spectrum.CoordSys.TimeFrame.ID";
        name[SEG_CS_TIMEFRAME_NAME] = "Spectrum.CoordSys.TimeFrame.Name";
        name[SEG_CS_TIMEFRAME_UCD] = "Spectrum.CoordSys.TimeFrame.UCD";
        name[SEG_CS_TIMEFRAME_ZERO] = "Spectrum.CoordSys.TimeFrame.Zero";
        name[SEG_CS_TIMEFRAME_REFPOS] = "Spectrum.CoordSys.TimeFrame.RefPos";
        name[SEG_CS_SPECTRALFRAME] = "Spectrum.CoordSys.SpectralFrame";
        name[SEG_CS_SPECTRALFRAME_ID] = "Spectrum.CoordSys.SpectralFrame.ID";
        name[SEG_CS_SPECTRALFRAME_NAME] = "Spectrum.CoordSys.SpectralFrame.Name";
        name[SEG_CS_SPECTRALFRAME_UCD] = "Spectrum.CoordSys.SpectralFrame.UCD";
        name[SEG_CS_SPECTRALFRAME_REFPOS] = "Spectrum.CoordSys.SpectralFrame.RefPos";
        name[SEG_CS_SPECTRALFRAME_REDSHIFT] = "Spectrum.CoordSys.SpectralFrame.Redshift";
        name[SEG_CS_REDFRAME] = "Spectrum.CoordSys.RedshiftFrame";
        name[SEG_CS_REDFRAME_ID] = "Spectrum.CoordSys.RedshiftFrame.ID";
        name[SEG_CS_REDFRAME_NAME] = "Spectrum.CoordSys.RedshiftFrame.Name";
        name[SEG_CS_REDFRAME_UCD] = "Spectrum.CoordSys.RedshiftFrame.UCD";
        name[SEG_CS_REDFRAME_REFPOS] = "Spectrum.CoordSys.RedshiftFrame.RefPos";
        name[SEG_CS_REDFRAME_DOPPLERDEF] = "Spectrum.CoordSys.RedshiftFrame.DopplerDefinition";
        name[SEG_CS_GENFRAME] = "Spectrum.CoordSys.GenericCoordFrame";
        name[SEG_CS_GENFRAME_ID] = "Spectrum.CoordSys.GenericCoordFrame.ID";
        name[SEG_CS_GENFRAME_NAME] = "Spectrum.CoordSys.GenericCoordFrame.Name";
        name[SEG_CS_GENFRAME_UCD] = "Spectrum.CoordSys.GenericCoordFrame.UCD";
        name[SEG_CS_GENFRAME_REFPOS] = "Spectrum.CoordSys.GenericCoordFrame.RefPos";
        name[SEG_CURATION] = "Spectrum.Curation";
        name[SEG_CURATION_PUBLISHER] = "Spectrum.Curation.Publisher";
        name[SEG_CURATION_REF] = "Spectrum.Curation.Reference";
        name[SEG_CURATION_PUBID] = "Spectrum.Curation.PublisherID";
        name[SEG_CURATION_PUBDID] = "Spectrum.Curation.PublisherDID";
        name[SEG_CURATION_VERSION] = "Spectrum.Curation.Version";
        name[SEG_CURATION_CONTACT] = "Spectrum.Curation.Contact";
        name[SEG_CURATION_CONTACT_NAME] = "Spectrum.Curation.Contact.Name";
        name[SEG_CURATION_CONTACT_EMAIL] = "Spectrum.Curation.Contact.Email";
        name[SEG_CURATION_RIGHTS] = "Spectrum.Curation.Rights";
        name[SEG_CURATION_DATE] = "Spectrum.Curation.Date";
        name[SEG_DATAID] = "Spectrum.DataID";
        name[SEG_DATAID_TITLE] = "Spectrum.DataID.Title";
        name[SEG_DATAID_CREATOR] = "Spectrum.DataID.Creator";
        name[SEG_DATAID_COLLECTION] = "Spectrum.DataID.Collection";
        name[SEG_DATAID_DATASETID] = "Spectrum.DataID.DatasetID";
        name[SEG_DATAID_CREATORDID] = "Spectrum.DataID.CreatorDID";
        name[SEG_DATAID_DATE] = "Spectrum.DataID.Date";
        name[SEG_DATAID_VERSION] = "Spectrum.DataID.Version";
        name[SEG_DATAID_INSTRUMENT] = "Spectrum.DataID.Instrument";
        name[SEG_DATAID_CREATIONTYPE] = "Spectrum.DataID.CreationType";
        name[SEG_DATAID_LOGO] = "Spectrum.DataID.Logo";
        name[SEG_DATAID_CONTRIBUTOR] = "Spectrum.DataID.Contributor";
        name[SEG_DATAID_DATASOURCE] = "Spectrum.DataID.DataSource";
        name[SEG_DATAID_BANDPASS] = "Spectrum.DataID.Bandpass";
        name[SEG_DD] = "Spectrum.Derived";
        name[SEG_DD_SNR] = "Spectrum.Derived.SNR";
        name[SEG_DD_REDSHIFT] = "Spectrum.Derived.Redshift";
        name[SEG_DD_REDSHIFT_VALUE] = "Spectrum.Derived.Redshift.Value";
        name[SEG_DD_REDSHIFT_ACC] = "Spectrum.Derived.Redshift.Accuracy";
        name[SEG_DD_REDSHIFT_ACC_STATERR] = "Spectrum.Derived.Redshift.Accuracy.StatError";
        name[SEG_DD_REDSHIFT_ACC_CONFIDENCE] = "Spectrum.Derived.Redshift.Accuracy.Confidence";
        name[SEG_DD_VARAMPL] = "Spectrum.Derived.VarAmpl";
        name[SEG_DD_REDSHIFT_ACC_BINLOW] = "Spectrum.Derived.Redshift.Accuracy.BinLow";
        name[SEG_DD_REDSHIFT_ACC_BINHIGH] = "Spectrum.Derived.Redshift.Accuracy.BinHigh";
        name[SEG_DD_REDSHIFT_ACC_BINSIZE] = "Spectrum.Derived.Redshift.Accuracy.BinSize";
        name[SEG_DD_REDSHIFT_ACC_STATERRLOW] = "Spectrum.Derived.Redshift.Accuracy.StatErrLow";
        name[SEG_DD_REDSHIFT_ACC_STATERRHIGH] = "Spectrum.Derived.Redshift.Accuracy.StatErrHigh";
        name[SEG_DD_REDSHIFT_ACC_SYSERR] = "Spectrum.Derived.Redshift.Accuracy.SysError";
        name[SEG_DD_REDSHIFT_QUALITY] = "Spectrum.Derived.Redshift.Quality";
        name[SEG_DD_REDSHIFT_RESOLUTION] = "Spectrum.Derived.Redshift.Resolution";
        name[SEG_CHAR] = "Spectrum.Char";
        name[SEG_CHAR_CHARAXIS] = "Spectrum.Char.CharAxis";
        name[SEG_CHAR_CHARAXIS_NAME] = "Spectrum.Char.CharAxis.Name";
        name[SEG_CHAR_CHARAXIS_UNIT] = "Spectrum.Char.CharAxis.Unit";
        name[SEG_CHAR_CHARAXIS_UCD] = "Spectrum.Char.CharAxis.UCD";
        name[SEG_CHAR_CHARAXIS_COV] = "Spectrum.Char.CharAxis.Coverage";
        name[SEG_CHAR_CHARAXIS_COV_LOC] = "Spectrum.Char.CharAxis.Coverage.Location";
        name[SEG_CHAR_CHARAXIS_COV_LOC_VALUE] = "Spectrum.Char.CharAxis.Coverage.Location.Value";
        name[SEG_CHAR_CHARAXIS_COV_BOUNDS] = "Spectrum.Char.CharAxis.Coverage.Bounds";
        name[SEG_CHAR_CHARAXIS_COV_BOUNDS_MIN] = "Spectrum.Char.CharAxis.Coverage.Bounds.Min";
        name[SEG_CHAR_CHARAXIS_COV_BOUNDS_MAX] = "Spectrum.Char.CharAxis.Coverage.Bounds.Max";
        name[SEG_CHAR_CHARAXIS_COV_BOUNDS_EXTENT] = "Spectrum.Char.CharAxis.Coverage.Bounds.Extent";
        name[SEG_CHAR_CHARAXIS_COV_BOUNDS_START] = "Spectrum.Char.CharAxis.Coverage.Bounds.Start";
        name[SEG_CHAR_CHARAXIS_COV_BOUNDS_STOP] = "Spectrum.Char.CharAxis.Coverage.Bounds.Stop";
        name[SEG_CHAR_CHARAXIS_SAMPPREC_SAMPPRECREFVAL] = "Spectrum.Char.CharAxis.SamplingPrecision.SamplingPrecisionRefVal";
        name[SEG_CHAR_CHARAXIS_COV_SUPPORT] = "Spectrum.Char.CharAxis.Coverage.Support";
        name[SEG_CHAR_CHARAXIS_COV_SUPPORT_AREA] = "Spectrum.Char.CharAxis.Coverage.Support.Area";
        name[SEG_CHAR_CHARAXIS_ACC] = "Spectrum.Char.CharAxis.Accuracy";
        name[SEG_CHAR_CHARAXIS_ACC_BINSIZE] = "Spectrum.Char.CharAxis.Accuracy.BinSize";
        name[SEG_CHAR_CHARAXIS_ACC_STATERR] = "Spectrum.Char.CharAxis.Accuracy.StatError";
        name[SEG_CHAR_CHARAXIS_ACC_SYSERR] = "Spectrum.Char.CharAxis.Accuracy.SysError";
        name[SEG_CHAR_CHARAXIS_CAL] = "Spectrum.Char.CharAxis.Calibration";
        name[SEG_CHAR_CHARAXIS_RESOLUTION] = "Spectrum.Char.CharAxis.Resolution";
        name[SEG_CHAR_CHARAXIS_SAMPPREC] = "Spectrum.Char.CharAxis.SamplingPrecision";
        name[SEG_CHAR_CHARAXIS_SAMPPREC_SAMPEXT] = "Spectrum.Char.CharAxis.SamplingPrecision.SamplingExtent";
        name[SEG_CHAR_FLUXAXIS] = "Spectrum.Char.FluxAxis";
        name[SEG_CHAR_FLUXAXIS_NAME] = "Spectrum.Char.FluxAxis.Name";
        name[SEG_CHAR_FLUXAXIS_UNIT] = "Spectrum.Char.FluxAxis.Unit";
        name[SEG_CHAR_FLUXAXIS_UCD] = "Spectrum.Char.FluxAxis.UCD";
        name[SEG_CHAR_FLUXAXIS_ACC] = "Spectrum.Char.FluxAxis.Accuracy";
        name[SEG_CHAR_FLUXAXIS_ACC_BINLOW] = "Spectrum.Char.FluxAxis.Accuracy.BinLow";
        name[SEG_CHAR_FLUXAXIS_ACC_BINHIGH] = "Spectrum.Char.FluxAxis.Accuracy.BinHigh";
        name[SEG_CHAR_FLUXAXIS_ACC_BINSIZE] = "Spectrum.Char.FluxAxis.Accuracy.BinSize";
        name[SEG_CHAR_FLUXAXIS_ACC_STATERRLOW] = "Spectrum.Char.FluxAxis.Accuracy.StatErrLow";
        name[SEG_CHAR_FLUXAXIS_ACC_STATERRHIGH] = "Spectrum.Char.FluxAxis.Accuracy.StatErrHigh";
        name[SEG_CHAR_FLUXAXIS_ACC_STATERR] = "Spectrum.Char.FluxAxis.Accuracy.StatError";
        name[SEG_CHAR_FLUXAXIS_ACC_SYSERR] = "Spectrum.Char.FluxAxis.Accuracy.SysError";
        name[SEG_CHAR_FLUXAXIS_ACC_CONFIDENCE] = "Spectrum.Char.FluxAxis.Accuracy.Confidence";
        name[SEG_CHAR_FLUXAXIS_CAL] = "Spectrum.Char.FluxAxis.Calibration";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_VALUE] = "Spectrum.Char.FluxAxis.Coverage.Location.Value";
        name[SEG_CHAR_FLUXAXIS_RESOLUTION] = "Spectrum.Char.FluxAxis.Resolution";
        name[SEG_CHAR_FLUXAXIS_SAMPPREC] = "Spectrum.Char.FluxAxis.SamplingPrecision";
        name[SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPEXT] = "Spectrum.Char.FluxAxis.SamplingPrecision.SamplingExtent";
        name[SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL] = "Spectrum.Char.FluxAxis.SamplingPrecision.SamplingPrecisionRefVal";
        name[SEG_CHAR_FLUXAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "Spectrum.Char.FluxAxis.SamplingPrecision.SamplingPrecisionRefVal.FillFactor";
        name[SEG_CHAR_FLUXAXIS_COV] = "Spectrum.Char.FluxAxis.Coverage";
        name[SEG_CHAR_FLUXAXIS_COV_LOC] = "Spectrum.Char.FluxAxis.Coverage.Location";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_RESOLUTION] = "Spectrum.Char.FluxAxis.Coverage.Location.Resolution";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINSIZE] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy.BinSize";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINLOW] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy.BinLow";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC_BINHIGH] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy.BinHigh";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERR] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy.StatError";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRLOW] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy.StatErrLow";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC_STATERRHIGH] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy.StatErrHigh";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC_CONFIDENCE] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy.Confidence";
        name[SEG_CHAR_FLUXAXIS_COV_LOC_ACC_SYSERR] = "Spectrum.Char.FluxAxis.Coverage.Location.Accuracy.SysError";
        name[SEG_CHAR_FLUXAXIS_COV_BOUNDS] = "Spectrum.Char.FluxAxis.Coverage.Bounds";
        name[SEG_CHAR_FLUXAXIS_COV_BOUNDS_EXTENT] = "Spectrum.Char.FluxAxis.Coverage.Bounds.Extent";
        name[SEG_CHAR_FLUXAXIS_COV_BOUNDS_MIN] = "Spectrum.Char.FluxAxis.Coverage.Bounds.Min";
        name[SEG_CHAR_FLUXAXIS_COV_BOUNDS_MAX] = "Spectrum.Char.FluxAxis.Coverage.Bounds.Max";
        name[SEG_CHAR_FLUXAXIS_COV_SUPPORT] = "Spectrum.Char.FluxAxis.Coverage.Support";
        name[SEG_CHAR_FLUXAXIS_COV_SUPPORT_RANGE] = "Spectrum.Char.FluxAxis.Coverage.Support.Range";
        name[SEG_CHAR_FLUXAXIS_COV_SUPPORT_AREA] = "Spectrum.Char.FluxAxis.Coverage.Support.Area";
        name[SEG_CHAR_FLUXAXIS_COV_SUPPORT_EXTENT] = "Spectrum.Char.FluxAxis.Coverage.Support.Extent";
        name[SEG_CHAR_SPECTRALAXIS] = "Spectrum.Char.SpectralAxis";
        name[SEG_CHAR_SPECTRALAXIS_NAME] = "Spectrum.Char.SpectralAxis.Name";
        name[SEG_CHAR_SPECTRALAXIS_UNIT] = "Spectrum.Char.SpectralAxis.Unit";
        name[SEG_CHAR_SPECTRALAXIS_UCD] = "Spectrum.Char.SpectralAxis.UCD";
        name[SEG_CHAR_SPECTRALAXIS_COV] = "Spectrum.Char.SpectralAxis.Coverage";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC] = "Spectrum.Char.SpectralAxis.Coverage.Location";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE] = "Spectrum.Char.SpectralAxis.Coverage.Location.Value";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINLOW] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy.BinLow";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINHIGH] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy.BinHigh";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_BINSIZE] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy.BinSize";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERRLOW] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy.StatErrLow";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERRHIGH] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy.StatErrHigh";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_STATERR] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy.StatError";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_SYSERR] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy.SysError";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_ACC_CONFIDENCE] = "Spectrum.Char.SpectralAxis.Coverage.Location.Accuracy.Confidence";
        name[SEG_CHAR_SPECTRALAXIS_COV_LOC_RESOLUTION] = "Spectrum.Char.SpectralAxis.Coverage.Location.Resolution";
        name[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS] = "Spectrum.Char.SpectralAxis.Coverage.Bounds";
        name[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN] = "Spectrum.Char.SpectralAxis.Coverage.Bounds.Min";
        name[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX] = "Spectrum.Char.SpectralAxis.Coverage.Bounds.Max";
        name[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT] = "Spectrum.Char.SpectralAxis.Coverage.Bounds.Extent";
        name[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_START] = "Spectrum.Char.SpectralAxis.Coverage.Bounds.Start";
        name[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP] = "Spectrum.Char.SpectralAxis.Coverage.Bounds.Stop";
        name[SEG_CHAR_SPECTRALAXIS_COV_SUPPORT] = "Spectrum.Char.SpectralAxis.Coverage.Support";
        name[SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_EXTENT] = "Spectrum.Char.SpectralAxis.Coverage.Support.Extent";
        name[SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_RANGE] = "Spectrum.Char.SpectralAxis.Coverage.Support.Range";
        name[SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_AREA] = "Spectrum.Char.SpectralAxis.Coverage.Support.Area";
        name[SEG_CHAR_SPECTRALAXIS_SAMPPREC] = "Spectrum.Char.SpectralAxis.SamplingPrecision";
        name[SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPEXT] = "Spectrum.Char.SpectralAxis.SamplingPrecision.SamplingExtent";
        name[SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL] = "Spectrum.Char.SpectralAxis.SamplingPrecision.SamplingPrecisionRefVal";
        name[SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "Spectrum.Char.SpectralAxis.SamplingPrecision.SamplingPrecisionRefVal.FillFactor";
        name[SEG_CHAR_SPECTRALAXIS_ACC] = "Spectrum.Char.SpectralAxis.Accuracy";
        name[SEG_CHAR_SPECTRALAXIS_ACC_BINLOW] = "Spectrum.Char.SpectralAxis.Accuracy.BinLow";
        name[SEG_CHAR_SPECTRALAXIS_ACC_BINHIGH] = "Spectrum.Char.SpectralAxis.Accuracy.BinHigh";
        name[SEG_CHAR_SPECTRALAXIS_ACC_BINSIZE] = "Spectrum.Char.SpectralAxis.Accuracy.BinSize";
        name[SEG_CHAR_SPECTRALAXIS_ACC_STATERRLOW] = "Spectrum.Char.SpectralAxis.Accuracy.StatErrLow";
        name[SEG_CHAR_SPECTRALAXIS_ACC_STATERRHIGH] = "Spectrum.Char.SpectralAxis.Accuracy.StatErrHigh";
        name[SEG_CHAR_SPECTRALAXIS_ACC_STATERR] = "Spectrum.Char.SpectralAxis.Accuracy.StatError";
        name[SEG_CHAR_SPECTRALAXIS_ACC_SYSERR] = "Spectrum.Char.SpectralAxis.Accuracy.SysError";
        name[SEG_CHAR_SPECTRALAXIS_ACC_CONFIDENCE] = "Spectrum.Char.SpectralAxis.Accuracy.Confidence";
        name[SEG_CHAR_SPECTRALAXIS_CAL] = "Spectrum.Char.SpectralAxis.Calibration";
        name[SEG_CHAR_SPECTRALAXIS_RESOLUTION] = "Spectrum.Char.SpectralAxis.Resolution";
        name[SEG_CHAR_SPECTRALAXIS_RESPOW] = "Spectrum.Char.SpectralAxis.ResPower";
        name[SEG_CHAR_SPATIALAXIS] = "Spectrum.Char.SpatialAxis";
        name[SEG_CHAR_SPATIALAXIS_NAME] = "Spectrum.Char.SpatialAxis.Name";
        name[SEG_CHAR_SPATIALAXIS_UNIT] = "Spectrum.Char.SpatialAxis.Unit";
        name[SEG_CHAR_SPATIALAXIS_UCD] = "Spectrum.Char.SpatialAxis.UCD";
        name[SEG_CHAR_SPATIALAXIS_CAL] = "Spectrum.Char.SpatialAxis.Calibration";
        name[SEG_CHAR_SPATIALAXIS_RESOLUTION] = "Spectrum.Char.SpatialAxis.Resolution";
        name[SEG_CHAR_SPATIALAXIS_COV] = "Spectrum.Char.SpatialAxis.Coverage";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC] = "Spectrum.Char.SpatialAxis.Coverage.Location";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE] = "Spectrum.Char.SpatialAxis.Coverage.Location.Value";
        name[SEG_CHAR_SPATIALAXIS_COV_BOUNDS] = "Spectrum.Char.SpatialAxis.Coverage.Bounds";
        name[SEG_CHAR_SPATIALAXIS_COV_BOUNDS_MIN] = "Spectrum.Char.SpatialAxis.Coverage.Bounds.Min";
        name[SEG_CHAR_SPATIALAXIS_COV_BOUNDS_MAX] = "Spectrum.Char.SpatialAxis.Coverage.Bounds.Max";
        name[SEG_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT] = "Spectrum.Char.SpatialAxis.Coverage.Bounds.Extent";
        name[SEG_CHAR_SPATIALAXIS_COV_SUPPORT] = "Spectrum.Char.SpatialAxis.Coverage.Support";
        name[SEG_CHAR_SPATIALAXIS_COV_SUPPORT_RANGE] = "Spectrum.Char.SpatialAxis.Coverage.Support.Range";
        name[SEG_CHAR_SPATIALAXIS_COV_SUPPORT_AREA] = "Spectrum.Char.SpatialAxis.Coverage.Support.Area";
        name[SEG_CHAR_SPATIALAXIS_COV_SUPPORT_EXTENT] = "Spectrum.Char.SpatialAxis.Coverage.Support.Extent";
        name[SEG_CHAR_SPATIALAXIS_ACC] = "Spectrum.Char.SpatialAxis.Accuracy";
        name[SEG_CHAR_SPATIALAXIS_ACC_STATERR] = "Spectrum.Char.SpatialAxis.Accuracy.StatError";
        name[SEG_CHAR_SPATIALAXIS_ACC_SYSERR] = "Spectrum.Char.SpatialAxis.Accuracy.SysError";
        name[SEG_CHAR_SPATIALAXIS_ACC_STATERRLOW] = "Spectrum.Char.SpatialAxis.Accuracy.StatErrLow";
        name[SEG_CHAR_SPATIALAXIS_ACC_STATERRHIGH] = "Spectrum.Char.SpatialAxis.Accuracy.StatErrHigh";
        name[SEG_CHAR_SPATIALAXIS_ACC_BINLOW] = "Spectrum.Char.SpatialAxis.Accuracy.BinLow";
        name[SEG_CHAR_SPATIALAXIS_ACC_BINHIGH] = "Spectrum.Char.SpatialAxis.Accuracy.BinHigh";
        name[SEG_CHAR_SPATIALAXIS_ACC_BINSIZE] = "Spectrum.Char.SpatialAxis.Accuracy.BinSize";
        name[SEG_CHAR_SPATIALAXIS_ACC_CONFIDENCE] = "Spectrum.Char.SpatialAxis.Accuracy.Confidence";
        name[SEG_CHAR_SPATIALAXIS_SAMPPREC] = "Spectrum.Char.SpatialAxis.SamplingPrecision";
        name[SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT] = "Spectrum.Char.SpatialAxis.SamplingPrecision.SamplingExtent";
        name[SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL] = "Spectrum.Char.SpatialAxis.SamplingPrecision.SamplingPrecisionRefVal";
        name[SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "Spectrum.Char.SpatialAxis.SamplingPrecision.SamplingPrecisionRefVal.FillFactor";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_RESOLUTION] = "Spectrum.Char.SpatialAxis.Coverage.Location.Resolution";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_RA] = "Spectrum.Char.SpatialAxis.Coverage.Location.Value.RA";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE_DEC] = "Spectrum.Char.SpatialAxis.Coverage.Location.Value.DEC";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINSIZE] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy.BinSize";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINLOW] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy.BinLow";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_BINHIGH] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy.BinHigh";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERR] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy.StatError";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERRLOW] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy.StatErrLow";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_STATERRHIGH] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy.StatErrHigh";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_CONFIDENCE] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy.Confidence";
        name[SEG_CHAR_SPATIALAXIS_COV_LOC_ACC_SYSERR] = "Spectrum.Char.SpatialAxis.Coverage.Location.Accuracy.SysError";
        name[SEG_CHAR_TIMEAXIS] = "Spectrum.Char.TimeAxis";
        name[SEG_CHAR_TIMEAXIS_NAME] = "Spectrum.Char.TimeAxis.Name";
        name[SEG_CHAR_TIMEAXIS_UNIT] = "Spectrum.Char.TimeAxis.Unit";
        name[SEG_CHAR_TIMEAXIS_UCD] = "Spectrum.Char.TimeAxis.UCD";
        name[SEG_CHAR_TIMEAXIS_COV] = "Spectrum.Char.TimeAxis.Coverage";
        name[SEG_CHAR_TIMEAXIS_COV_LOC] = "Spectrum.Char.TimeAxis.Coverage.Location";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_VALUE] = "Spectrum.Char.TimeAxis.Coverage.Location.Value";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINSIZE] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy.BinSize";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINLOW] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy.BinLow";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC_BINHIGH] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy.BinHigh";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERR] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy.StatError";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERRLOW] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy.StatErrLow";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC_STATERRHIGH] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy.StatErrHigh";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC_CONFIDENCE] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy.Confidence";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_ACC_SYSERR] = "Spectrum.Char.TimeAxis.Coverage.Location.Accuracy.SysError";
        name[SEG_CHAR_TIMEAXIS_COV_BOUNDS] = "Spectrum.Char.TimeAxis.Coverage.Bounds";
        name[SEG_CHAR_TIMEAXIS_COV_BOUNDS_MIN] = "Spectrum.Char.TimeAxis.Coverage.Bounds.Min";
        name[SEG_CHAR_TIMEAXIS_COV_BOUNDS_MAX] = "Spectrum.Char.TimeAxis.Coverage.Bounds.Max";
        name[SEG_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT] = "Spectrum.Char.TimeAxis.Coverage.Bounds.Extent";
        name[SEG_CHAR_TIMEAXIS_COV_BOUNDS_START] = "Spectrum.Char.TimeAxis.Coverage.Bounds.Start";
        name[SEG_CHAR_TIMEAXIS_COV_BOUNDS_STOP] = "Spectrum.Char.TimeAxis.Coverage.Bounds.Stop";
        name[SEG_CHAR_TIMEAXIS_COV_SUPPORT] = "Spectrum.Char.TimeAxis.Coverage.Support";
        name[SEG_CHAR_TIMEAXIS_COV_SUPPORT_AREA] = "Spectrum.Char.TimeAxis.Coverage.Support.Area";
        name[SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "Spectrum.Char.TimeAxis.SamplingPrecision.SamplingPrecisionRefVal.FillFactor";
        name[SEG_CHAR_TIMEAXIS_ACC] = "Spectrum.Char.TimeAxis.Accuracy";
        name[SEG_CHAR_TIMEAXIS_ACC_BINLOW] = "Spectrum.Char.TimeAxis.Accuracy.BinLow";
        name[SEG_CHAR_TIMEAXIS_ACC_BINHIGH] = "Spectrum.Char.TimeAxis.Accuracy.BinHigh";
        name[SEG_CHAR_TIMEAXIS_ACC_BINSIZE] = "Spectrum.Char.TimeAxis.Accuracy.BinSize";
        name[SEG_CHAR_TIMEAXIS_ACC_STATERR] = "Spectrum.Char.TimeAxis.Accuracy.StatError";
        name[SEG_CHAR_TIMEAXIS_ACC_SYSERR] = "Spectrum.Char.TimeAxis.Accuracy.SysError";
        name[SEG_CHAR_TIMEAXIS_ACC_STATERRLOW] = "Spectrum.Char.TimeAxis.Accuracy.StatErrLow";
        name[SEG_CHAR_TIMEAXIS_ACC_STATERRHIGH] = "Spectrum.Char.TimeAxis.Accuracy.StatErrHigh";
        name[SEG_CHAR_TIMEAXIS_ACC_CONFIDENCE] = "Spectrum.Char.TimeAxis.Accuracy.Confidence";
        name[SEG_CHAR_TIMEAXIS_CAL] = "Spectrum.Char.TimeAxis.Calibration";
        name[SEG_CHAR_TIMEAXIS_RESOLUTION] = "Spectrum.Char.TimeAxis.Resolution";
        name[SEG_CHAR_TIMEAXIS_SAMPPREC] = "Spectrum.Char.TimeAxis.SamplingPrecision";
        name[SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPEXT] = "Spectrum.Char.TimeAxis.SamplingPrecision.SamplingExtent";
        name[SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL] = "Spectrum.Char.TimeAxis.SamplingPrecision.SamplingPrecisionRefVal";
        name[SEG_CHAR_TIMEAXIS_COV_SUPPORT_EXTENT] = "Spectrum.Char.TimeAxis.Coverage.Support.Extent";
        name[SEG_CHAR_TIMEAXIS_COV_LOC_RESOLUTION] = "Spectrum.Char.TimeAxis.Coverage.Location.Resolution";
        name[SEG_CHAR_TIMEAXIS_COV_SUPPORT_RANGE] = "Spectrum.Char.TimeAxis.Coverage.Support.Range";
        name[SEG_DATA] = "Spectrum.Data";
        name[SEG_DATA_FLUXAXIS] = "Spectrum.Data.FluxAxis";
        name[SEG_DATA_FLUXAXIS_VALUE] = "Spectrum.Data.FluxAxis.Value";
        name[SEG_DATA_FLUXAXIS_QUALITY] = "Spectrum.Data.FluxAxis.Quality";
        name[SEG_DATA_FLUXAXIS_ACC] = "Spectrum.Data.FluxAxis.Accuracy";
        name[SEG_DATA_FLUXAXIS_ACC_BINLOW] = "Spectrum.Data.FluxAxis.Accuracy.BinLow";
        name[SEG_DATA_FLUXAXIS_ACC_BINHIGH] = "Spectrum.Data.FluxAxis.Accuracy.BinHigh";
        name[SEG_DATA_FLUXAXIS_ACC_BINSIZE] = "Spectrum.Data.FluxAxis.Accuracy.BinSize";
        name[SEG_DATA_FLUXAXIS_ACC_STATERRLOW] = "Spectrum.Data.FluxAxis.Accuracy.StatErrLow";
        name[SEG_DATA_FLUXAXIS_ACC_STATERRHIGH] = "Spectrum.Data.FluxAxis.Accuracy.StatErrHigh";
        name[SEG_DATA_FLUXAXIS_ACC_STATERR] = "Spectrum.Data.FluxAxis.Accuracy.StatError";
        name[SEG_DATA_FLUXAXIS_ACC_SYSERR] = "Spectrum.Data.FluxAxis.Accuracy.SysError";
        name[SEG_DATA_FLUXAXIS_ACC_CONFIDENCE] = "Spectrum.Data.FluxAxis.Accuracy.Confidence";
        name[SEG_DATA_FLUXAXIS_RESOLUTION] = "Spectrum.Data.FluxAxis.Resolution";
        name[SEG_DATA_SPECTRALAXIS] = "Spectrum.Data.SpectralAxis";
        name[SEG_DATA_SPECTRALAXIS_VALUE] = "Spectrum.Data.SpectralAxis.Value";
        name[SEG_DATA_SPECTRALAXIS_ACC] = "Spectrum.Data.SpectralAxis.Accuracy";
        name[SEG_DATA_SPECTRALAXIS_ACC_STATERR] = "Spectrum.Data.SpectralAxis.Accuracy.StatError";
        name[SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW] = "Spectrum.Data.SpectralAxis.Accuracy.StatErrLow";
        name[SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH] = "Spectrum.Data.SpectralAxis.Accuracy.StatErrHigh";
        name[SEG_DATA_SPECTRALAXIS_ACC_BINLOW] = "Spectrum.Data.SpectralAxis.Accuracy.BinLow";
        name[SEG_DATA_SPECTRALAXIS_ACC_BINHIGH] = "Spectrum.Data.SpectralAxis.Accuracy.BinHigh";
        name[SEG_DATA_SPECTRALAXIS_ACC_BINSIZE] = "Spectrum.Data.SpectralAxis.Accuracy.BinSize";
        name[SEG_DATA_SPECTRALAXIS_ACC_SYSERR] = "Spectrum.Data.SpectralAxis.Accuracy.SysError";
        name[SEG_DATA_SPECTRALAXIS_RESOLUTION] = "Spectrum.Data.SpectralAxis.Resolution";
        name[SEG_DATA_SPECTRALAXIS_ACC_CONFIDENCE] = "Spectrum.Data.SpectralAxis.Accuracy.Confidence";
        name[SEG_DATA_TIMEAXIS] = "Spectrum.Data.TimeAxis";
        name[SEG_DATA_TIMEAXIS_VALUE] = "Spectrum.Data.TimeAxis.Value";
        name[SEG_DATA_TIMEAXIS_ACC] = "Spectrum.Data.TimeAxis.Accuracy";
        name[SEG_DATA_TIMEAXIS_ACC_STATERR] = "Spectrum.Data.TimeAxis.Accuracy.StatError";
        name[SEG_DATA_TIMEAXIS_ACC_STATERRLOW] = "Spectrum.Data.TimeAxis.Accuracy.StatErrLow";
        name[SEG_DATA_TIMEAXIS_ACC_STATERRHIGH] = "Spectrum.Data.TimeAxis.Accuracy.StatErrHigh";
        name[SEG_DATA_TIMEAXIS_ACC_SYSERR] = "Spectrum.Data.TimeAxis.Accuracy.SysError";
        name[SEG_DATA_TIMEAXIS_ACC_BINLOW] = "Spectrum.Data.TimeAxis.Accuracy.BinLow";
        name[SEG_DATA_TIMEAXIS_ACC_BINHIGH] = "Spectrum.Data.TimeAxis.Accuracy.BinHigh";
        name[SEG_DATA_TIMEAXIS_ACC_BINSIZE] = "Spectrum.Data.TimeAxis.Accuracy.BinSize";
        name[SEG_DATA_TIMEAXIS_ACC_CONFIDENCE] = "Spectrum.Data.TimeAxis.Accuracy.Confidence";
        name[SEG_DATA_TIMEAXIS_RESOLUTION] = "Spectrum.Data.TimeAxis.Resolution";
        name[SEG_DATA_BGM] = "Spectrum.Data.BackgroundModel";
        name[SEG_DATA_BGM_VALUE] = "Spectrum.Data.BackgroundModel.Value";
        name[SEG_DATA_BGM_QUALITY] = "Spectrum.Data.BackgroundModel.Quality";
        name[SEG_DATA_BGM_ACC] = "Spectrum.Data.BackgroundModel.Accuracy";
        name[SEG_DATA_BGM_ACC_STATERR] = "Spectrum.Data.BackgroundModel.Accuracy.StatError";
        name[SEG_DATA_BGM_ACC_STATERRLOW] = "Spectrum.Data.BackgroundModel.Accuracy.StatErrLow";
        name[SEG_DATA_BGM_ACC_STATERRHIGH] = "Spectrum.Data.BackgroundModel.Accuracy.StatErrHigh";
        name[SEG_DATA_BGM_ACC_SYSERR] = "Spectrum.Data.BackgroundModel.Accuracy.SysError";
        name[SEG_DATA_BGM_RESOLUTION] = "Spectrum.Data.BackgroundModel.Resolution";
        name[SEG_DATA_BGM_ACC_BINLOW] = "Spectrum.Data.BackgroundModel.Accuracy.BinLow";
        name[SEG_DATA_BGM_ACC_BINHIGH] = "Spectrum.Data.BackgroundModel.Accuracy.BinHigh";
        name[SEG_DATA_BGM_ACC_BINSIZE] = "Spectrum.Data.BackgroundModel.Accuracy.BinSize";
        name[SEG_DATA_BGM_ACC_CONFIDENCE] = "Spectrum.Data.BackgroundModel.Accuracy.Confidence";
        name[CUSTOM] = "Spectrum.Custom";
        name[SEG] = "Segment";
        name[SPEC] = "Spectrum";
        name[SEG_CHAR_CHARAXIS_ACC_BINLOW] = "Spectrum.Char.CharAxis.Accuracy.BinLow";
        name[SEG_CHAR_CHARAXIS_ACC_BINHIGH] = "Spectrum.Char.CharAxis.Accuracy.BinHigh";
        name[SEG_CHAR_CHARAXIS_ACC_STATERRLOW] = "Spectrum.Char.CharAxis.Accuracy.StatErrLow";
        name[SEG_CHAR_CHARAXIS_ACC_STATERRHIGH] = "Spectrum.Char.CharAxis.Accuracy.StatErrHigh";
        name[SEG_CHAR_CHARAXIS_ACC_CONFIDENCE] = "Spectrum.Char.CharAxis.Accuracy.Confidence";
        name[SEG_CHAR_CHARAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "Spectrum.Char.CharAxis.SamplingPrecision.SamplingPrecisionRefVal.FillFactor";
        name[SEG_CHAR_CHARAXIS_COV_SUPPORT_EXTENT] = "Spectrum.Char.CharAxis.Coverage.Support.Extent";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC_SYSERR] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy.SysError";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC_CONFIDENCE] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy.Confidence";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINSIZE] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy.BinSize";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINLOW] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy.BinLow";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC_BINHIGH] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy.BinHigh";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERR] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy.StatError";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRLOW] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy.StatErrLow";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC_STATERRHIGH] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy.StatErrHigh";
        name[SEG_CHAR_CHARAXIS_COV_LOC_RESOLUTION] = "Spectrum.Char.CharAxis.Coverage.Location.Resolution";
        name[SEG_CHAR_CHARAXIS_COV_LOC_ACC] = "Spectrum.Char.CharAxis.Coverage.Location.Accuracy";
        name[SEG_CHAR_CHARAXIS_COV_SUPPORT_RANGE] = "Spectrum.Char.CharAxis.Coverage.Support.Range";

        ucd[LENGTH] = "meta.number";
        ucd[TIMESI] = "time;arith.zp";
        ucd[SEG_CS_SPACEFRAME_EQUINOX] = "time.equinox;pos.frame";
        ucd[SEG_CS_TIMEFRAME_NAME] = "time.scale";
        ucd[SEG_CS_TIMEFRAME_ZERO] = "time;arith.zp";
        ucd[SEG_CS_TIMEFRAME_REFPOS] = "time.scale";
        ucd[SEG_CURATION_PUBLISHER] = "meta.curation";
        ucd[SEG_CURATION_PUBID] = "meta.ref.url;meta.curation";
        ucd[SEG_CURATION_VERSION] = "meta.version;meta.curation";
        ucd[SEG_CURATION_REF] = "meta.bib.bibcode";
        ucd[SEG_CURATION_CONTACT_NAME] = "meta.bib.author;meta.curation";
        ucd[SEG_CURATION_CONTACT_EMAIL] = "meta.ref.url;meta.email";
        ucd[SEG_CURATION_PUBDID] = "meta.ref.url;meta.curation";
        ucd[SEG_DATAID_TITLE] = "meta.title;meta.dataset";
        ucd[SEG_DATAID_DATASETID] = "meta.id;meta.dataset";
        ucd[SEG_DATAID_CREATORDID] = "meta.id";
        ucd[SEG_DATAID_DATE] = "time;meta.dataset";
        ucd[SEG_DATAID_VERSION] = "meta.version;meta.dataset";
        ucd[SEG_DATAID_INSTRUMENT] = "meta.id;instr";
        ucd[SEG_DATAID_BANDPASS] = "intr.bandpass";
        ucd[SEG_DATAID_LOGO] = "meta.ref.url";
        ucd[SEG_DD_SNR] = "stat.snr";
        ucd[SEG_DD_VARAMPL] = "src.var.amplitude;arith.ratio";
/*        ucd[SEG_DD_REDSHIFT_STATERR] = "stat.error;src.redshift";
*/
        ucd[TARGET_NAME] = "meta.id;src";
        ucd[TARGET_DESCRIPTION] = "meta.note;src";
        ucd[TARGET_CLASS] = "src.class";
        ucd[TARGET_SPECTRALCLASS] = "src.spType";
        ucd[TARGET_REDSHIFT] = "src.redshift";
        ucd[TARGET_POS] = "pos.eq;src";
        ucd[TARGET_VARAMPL] = "src.var.amplitude";

        ucd[SEG_CHAR_SPATIALAXIS_NAME] = "meta.id";
        ucd[SEG_CHAR_SPATIALAXIS_UCD] = "meta.ucd";
        ucd[SEG_CHAR_SPATIALAXIS_UNIT] = "meta.unit";
//        ucd[SEG_CHAR_FLUXAXIS_CAL] = "?";
        ucd[SEG_CHAR_SPATIALAXIS_CAL] = "meta.code.qual";
        ucd[SEG_CHAR_TIMEAXIS_CAL] = "meta.code.qual";
        ucd[SEG_CHAR_SPECTRALAXIS_CAL] = "meta.code.qual";
        ucd[SEG_CHAR_SPATIALAXIS_COV_LOC_VALUE] = "pos.eq";
        ucd[SEG_CHAR_SPATIALAXIS_COV_BOUNDS_EXTENT] = "instr.fov";
//        ucd[SEG_CHAR_SPATIALAXIS_COV_SUPPORT_AREA] = "?";
        ucd[SEG_CHAR_SPATIALAXIS_COV_SUPPORT_EXTENT] = "instr.fov";
        ucd[SEG_CHAR_TIMEAXIS_COV_LOC_VALUE] = "time.epoch";
        ucd[SEG_CHAR_TIMEAXIS_COV_BOUNDS_EXTENT] = "time.duration";
        ucd[SEG_CHAR_TIMEAXIS_COV_BOUNDS_START] = "time.start;obs.exposure";
        ucd[SEG_CHAR_TIMEAXIS_COV_BOUNDS_STOP] = "time.stop;obs.exposure";
        ucd[SEG_CHAR_TIMEAXIS_COV_BOUNDS_MIN] = "time.start;obs.exposure";
        ucd[SEG_CHAR_TIMEAXIS_COV_BOUNDS_MAX] = "time.stop;obs.exposure";
        ucd[SEG_CHAR_TIMEAXIS_COV_SUPPORT_EXTENT] = "time.duration;obs.exposure";
        ucd[SEG_CHAR_SPECTRALAXIS_COV_LOC_VALUE] = "instr.bandpass";
        ucd[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_EXTENT] = "instr.bandwidth";
        ucd[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MIN] = "em.*;stat.min";
        ucd[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_MAX] = "em.*;stat.max";
        ucd[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_START] = "em.*;stat.min";
        ucd[SEG_CHAR_SPECTRALAXIS_COV_BOUNDS_STOP] = "em.*;stat.max";

        ucd[SEG_CHAR_SPECTRALAXIS_COV_SUPPORT_EXTENT] = "instr.bandwidth";
        ucd[SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPEXT] = "em.*;spect.binSize";
        ucd[SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPEXT] = "instr.pixel";
        ucd[SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPEXT] = "time.interval";
        ucd[SEG_CHAR_SPATIALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "stat.fill;pos.eq";
        ucd[SEG_CHAR_SPECTRALAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "stat.fill;em.*";
        ucd[SEG_CHAR_TIMEAXIS_SAMPPREC_SAMPPRECREFVAL_FILL] = "time;stat.fill;time";
        ucd[SEG_CHAR_FLUXAXIS_ACC_STATERR] = "stat.error;phot.flux.density;em.*";
        ucd[SEG_CHAR_FLUXAXIS_ACC_SYSERR] = "stat.error.sys;phot.flux.density;em.*";
        ucd[SEG_CHAR_SPECTRALAXIS_ACC_BINSIZE] = "em.*;spect.binSize";
        ucd[SEG_CHAR_SPECTRALAXIS_ACC_STATERR] = "stat.error;em.*";
        ucd[SEG_CHAR_SPECTRALAXIS_ACC_SYSERR] = "stat.error;em.*";
        ucd[SEG_CHAR_SPECTRALAXIS_RESOLUTION] = "spect.resolution;em.*";
        ucd[SEG_CHAR_SPECTRALAXIS_RESPOW] = "spect.resolution";
        ucd[SEG_CHAR_TIMEAXIS_ACC_BINSIZE] = "time.interval";
        ucd[SEG_CHAR_TIMEAXIS_ACC_STATERR] = "stat.error;time";
        ucd[SEG_CHAR_TIMEAXIS_ACC_SYSERR] = "stat.error.sys;time";
        ucd[SEG_CHAR_TIMEAXIS_RESOLUTION] = "time.resolution";
        ucd[SEG_CHAR_SPATIALAXIS_ACC_STATERR] = "stat.error;pos.eq";
        ucd[SEG_CHAR_SPATIALAXIS_ACC_SYSERR] = "stat.error.sys;pos.eq";
        ucd[SEG_CHAR_SPATIALAXIS_RESOLUTION] = "pos.angResolution";
        ucd[SEG_DATA_FLUXAXIS_ACC_STATERR] = "stat.error;phot.flux.density;em.*";
        ucd[SEG_DATA_FLUXAXIS_ACC_STATERRLOW] = "stat.error;phot.flux.density;em.*;stat.min";
        ucd[SEG_DATA_FLUXAXIS_ACC_STATERRHIGH] = "stat.error;phot.flux.density;em.*;stat.max";
        ucd[SEG_DATA_FLUXAXIS_ACC_SYSERR] = "stat.error.sys;phot.flux.density;em.*";
        ucd[SEG_DATA_FLUXAXIS_QUALITY] = "meta.code.qual;phot.flux.density;em.*";
        ucd[SEG_DATA_SPECTRALAXIS_ACC_BINSIZE] = "em.*;spect.binSize";
        ucd[SEG_DATA_SPECTRALAXIS_ACC_BINLOW] = "em.*;stat.min";
        ucd[SEG_DATA_SPECTRALAXIS_ACC_BINHIGH] = "em.*;stat.max";
        ucd[SEG_DATA_SPECTRALAXIS_ACC_STATERR] = "stat.error;em.*";
        ucd[SEG_DATA_SPECTRALAXIS_ACC_STATERRLOW] = "stat.error;em.*;stat.min";
        ucd[SEG_DATA_SPECTRALAXIS_ACC_STATERRHIGH] = "stat.error;em.*;stat.max";
        ucd[SEG_DATA_SPECTRALAXIS_ACC_SYSERR] = "stat.error.sys;em.*";
        ucd[SEG_DATA_SPECTRALAXIS_RESOLUTION] = "spect.resolution;em.*";
        ucd[SEG_DATA_TIMEAXIS_ACC_BINSIZE] = "time.interval";
        ucd[SEG_DATA_TIMEAXIS_ACC_BINLOW] = "time;stat.min";
        ucd[SEG_DATA_TIMEAXIS_ACC_BINHIGH] = "time;stat.max";
        ucd[SEG_DATA_TIMEAXIS_ACC_STATERR] = "stat.error;time";
        ucd[SEG_DATA_TIMEAXIS_ACC_STATERRLOW] = "stat.error;time;stat.min";
        ucd[SEG_DATA_TIMEAXIS_ACC_STATERRHIGH] = "stat.error;time;stat.max";
        ucd[SEG_DATA_TIMEAXIS_ACC_SYSERR] = "stat.error.sys;time";
        ucd[SEG_DATA_TIMEAXIS_RESOLUTION] = "time.resolution";
        ucd[SEG_DATA_BGM_ACC_STATERR] = "stat.error;phot.flux.density;em.*";
        ucd[SEG_DATA_BGM_ACC_STATERRLOW] = "stat.error;phot.flux.density;em.*;stat.min";
        ucd[SEG_DATA_BGM_ACC_STATERRHIGH] = "stat.error;phot.flux.density;em.*;stat.max";
        ucd[SEG_DATA_BGM_ACC_SYSERR] = "stat.error.sys;phot.flux.density;em.*";
        ucd[SEG_DATA_BGM_QUALITY] = "meta.code.qual;phot.flux.density;em.*";

        for (int ii=0; ii<name.length; ii++)
        {
            if (name[ii] != null)
                nameMap.put (name[ii].toLowerCase (), ii);
        }

    }

    /**
     * Gets the utype enumeration from string name.
     *
     */
    static public int getUtypeFromString (String name)
    {
    	Integer utypeEnum;

        if (name == null)
            return INVALID_UTYPE;

        utypeEnum = nameMap.get (name.toLowerCase ());
    	if (utypeEnum == null)
    	    return INVALID_UTYPE;
    	
    	return utypeEnum;
    	
    }
    
    /**
     * Gets the string representation of a utype enumeration.
     *
     */
    static public String getName (int utype) 
    {
        return name[utype];
    }


    /**
     * Override wild card sections of the ucd with a valid value. Currently only
     * "em.*" value can be overridden. For em valid override values include
     * wave, freq, and ener.
     * @param utype
     *   the enumerated utype
     * @param ucdBase
     *   the ucd section to override (em)
     * @param override
     *   the String to override the wildcard (wave, freq, and ener)
     *   
     *
     */
    static public String overrideUcd (int utype, String ucdBase, String override)
    {
        String ucdName;

        if (utype >= ucd.length)
            return null;

        ucdName =  ucd[utype];
        
        if (ucdName == null)
            return null;

        if (ucdBase.equalsIgnoreCase ("em"))
        {
            if (ucdName.matches ("^.*em\\.\\*.*$"))
            {
                if (override.equalsIgnoreCase ("wave"))
                    ucdName = ucdName.replaceFirst ("em\\.\\*", "em.wl");
                else if (override.equalsIgnoreCase ("freq"))
                    ucdName = ucdName.replaceFirst ("em\\.\\*", "em.freq");
                else if (override.equalsIgnoreCase ("ener"))
                    ucdName = ucdName.replaceFirst ("em\\.\\*", "em.energy");
            }
        }
        else
            ucdName = null;

        return ucdName;

    }

    /**
     * Gets the ucd associated with the specified utype enumeration.
     *
     */
    static public String getUcd (int utype)
    {
        return ucd[utype];
    }

    /**
     * Compares string version the utypes. Case is ignored.
     *
     */
    static public boolean compareUtypes (String utype1, String utype2)
    {
        return utype1.equalsIgnoreCase (utype2);
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

        String[] parts = utype.split( "[.]" );

        return parts[ parts.length - 1 ];
    }

    /**
     *  Retrieve an enumeration which is the combination of 
     *  of two inputs. The first argument is the base of the 
     *  new enumeration. The second argument is the desired
     *  end.
     */
    static public int mergeUtypes (int baseUtype, int suffixUtype) 
    {
        String baseUtypeName = name[baseUtype];
        String suffixUtypeName = name[suffixUtype];
        String newUtypeName = name[baseUtype];

        String[] baseParts = baseUtypeName.split( "[.]" );
        String[] suffixParts = suffixUtypeName.split ( "[.]" );

        for (int ii=baseParts.length; ii < suffixParts.length; ii++)
           newUtypeName = newUtypeName.concat ("."+suffixParts[ii]);

        return getUtypeFromString (newUtypeName);
    }

    /**
     * Gets the number of utypes.
     *
     */
    static public int getNumberOfUtypes ()
    {
        return max_enum;
    }

    /**
     * Compare the string name of the utype with its enumerated counter part.
     *
     */
     static public boolean compare (String utypeName, int utype)
     {
         int utypeEnum = getUtypeFromString (utypeName);
         return utypeEnum == utype;
     }

}


