/***********************************************************************
*
* File: src/test/cfa/vo/stc/ITestUtils.java
*
* Author:  jcant		Created: Thu Jul 17 10:38:05 2008
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
***********************************************************************/


package cfa.vo.testtools;

/**
Constants for testing
*/	
public abstract class ITestUtils
{
	static public String DATA_DIR = "data/";

	static public String OUT_DIR = "out/";
	static public final String OUT_EXT = ".out";

	static public final String ASCII_EXT = ".asc";

	static public String GOOD_DIR = "baseline";
	static public final String GOOD_EXT = ".good";

	static public String STDOUT_DIR = OUT_DIR;
	static public final String STDOUT_EXT = ".stdout";

	static public String STDERR_DIR = OUT_DIR;
	static public final String STDERR_EXT = ".stderr";
	
	static public String LOG_DIR = OUT_DIR;
	static public final String LOG_EXT = ".log";

	// These are to be used to catch the stdout and stderr of the
	// application code running in the JUnit test; they should be used conjunction with
	// mkTestOutName, mkTestErrName and setSystemOut() and
	// SetSystemErr().
	static public final String APPOUT_EXT = ".appout";
	static public final String APPERR_EXT = ".apperr";

	static public String DIFF_DIR = OUT_DIR;
	static public final String DIFF_EXT = ".dif";
	static public final String DIFFERR_EXT = ".diferr";

	static public final String SUFFIX_SEDLOG = ".sedlog";
	static public final String SUFFIX_STCLOG = ".stclog";

//	static public final String OTS = "/data/nvo/soft/cfa/ots/";
        static public final String OTS = System.getenv ("VAO_OTS")+"/lib";
	static public final String LOG4J_JAR = OTS + "/log4j.jar";

	/**
		Path to tools and other resources that should never (well,
		hardly ever) change.
	*/
	static public final String toolsPath =
			":" + OTS + "jaxb-api.jar"  +
			":" + OTS + "jaxb-impl.jar"  +
			":" + LOG4J_JAR  +
			"";

        /** 
                Sets the output variables to a common output
                directory
        */
        static public void setOutputDirectories (String dir) 
        {
            OUT_DIR = dir;
            STDOUT_DIR = dir;
            STDERR_DIR = dir;
            DIFF_DIR = dir;
        }

}
