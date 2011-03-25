/***********************************************************************
*
* File: src/test/cfa/vo/stc/Utils.java
*
* Author:  jcant		Created: Wed Jul 16 15:32:02 2008
*
* National Virtual Observatory; contributed by Center for Astrophysics
*
* Change History
*   2010-Dec   MCD    added cleanupFiles
***********************************************************************/


package cfa.vo.testtools;


import java.io.*;
import java.io.File;
import java.net.URL;


/*
 *  Utilities for helping with testing; catches most exceptions and rethrows
 *  them as RuntimeExceptions. This allows the testing code to be simpler;
 *  junit tests will catch the exceptions and cause a test to fail.
*/	
public class SedLibTestUtils extends ITestUtils
{
    static SedLibTestUtils m_theOneAndOnly = null;

    // private so we can be a singleton
    private SedLibTestUtils()
    {
    }

    static public SedLibTestUtils getTestUtils()
    {
	if ( m_theOneAndOnly == null )
	{
	    m_theOneAndOnly = new SedLibTestUtils();
	}
	return m_theOneAndOnly;
    }

    // Builds full Path from input filename root
    //   adds <path> and <file extension>
    static public String mkInFileName( String name )
    { return DATA_DIR + File.separator + name; }

    static public String mkOutFileName( String name )
    { return OUT_DIR + File.separator + name + OUT_EXT; }

    static public String mkAsciiFileName( String name )
    { return OUT_DIR + File.separator + name + ASCII_EXT; }

    static public String mkGoodFileName( String name )
    { return GOOD_DIR + File.separator + name + GOOD_EXT; }

    static public String mkStdoutFileName( String name )
    { return OUT_DIR + File.separator + name + STDOUT_EXT; }

    static public String mkStderrFileName( String name )
    { return OUT_DIR + File.separator + name + STDERR_EXT; }

    static public String mkAppStdoutFileName( String name )
    { return OUT_DIR + File.separator + name + APPOUT_EXT; }

    static public String mkAppStderrFileName( String name )
    { return OUT_DIR + File.separator + name + APPERR_EXT; }

    static public String mkDiffFileName( String name )
    { return DIFF_DIR + File.separator + name + DIFF_EXT; }

    static public String mkDiffErrorFileName( String name )
    { return DIFF_DIR + File.separator + name + DIFFERR_EXT; }


    // Delete test files based on filename root
    static public void cleanupFiles( String name )
    {
	String fullname = null;
	File fptr = null;

	fullname = mkOutFileName( name );
	fptr = new File( fullname );
	if ( fptr.exists() ){ fptr.delete(); }
	    
	fullname = mkAsciiFileName( name );
	fptr = new File( fullname );
	if ( fptr.exists() ){ fptr.delete(); }

	fullname = mkDiffFileName( name );
	fptr = new File( fullname );
	if ( fptr.exists() ){ fptr.delete(); }

	fullname = mkStderrFileName( name );
	fptr = new File( fullname );
	if ( fptr.exists() ){ fptr.delete(); }
    }


    /*
     *  Builds the out, asc, good, diff files names then calls the
     *  appropriate diffFits().
     *  @see diffFits( String, String, String, String,String )
     */
    static public int diffFits( String testName )
    {
	return diffFits( mkOutFileName( testName ) ,
			 mkAsciiFileName( testName ),
			 mkGoodFileName( testName ),
			 mkDiffFileName( testName ),
			 mkStderrFileName( testName) );
    }

    /*
     *  Diff's fits output against a 'good' file.  Makes an ascii
     *  representation of the fits binary output file using 'dmlist'
     *  and diffs that against the good files.
     *	@return the return value of the diff
     */
    static public int diffFits( String fitsFile, String asciiFile,
				String goodFile, String diffFile, String stderrFile )
    {
	Runtime runtime = Runtime.getRuntime();
	int exitValue = -1;

	// First, convert the FITS file to ASCII
	try
	{
	    // testtools are not installed so we need to 
	    // use the absolute path to call fits2ascii.pl 
	    // this may not be the best place to keep this file
//	    String testToolDir = System.getProperties().getProperty( "TESTTOOLS_DIR" );
            URL testToolUrl = SedLibTestUtils.class.getResource("/test_tools");
            String testToolDir = testToolUrl.getPath();

	    String convertCommand = String.format(
						  "perl %s/perl/fits2ascii.pl -o %s %s",
						  testToolDir, asciiFile, fitsFile );

	    Process proc = runtime.exec( convertCommand );
	    try
	    {
		java.io.InputStream  stdoutStream = proc.getInputStream();
		java.io.InputStream  stderrStream = proc.getErrorStream();

		proc.waitFor();
		exitValue = proc.exitValue();

		// Save the outputs of the exec'd process.
		// Don't need to catch stdout; it goes to the ascii file.
		streamToFile( stderrStream, stderrFile );
		if ( exitValue != 0 )
		{
		    return exitValue;
		}
	    }
	    catch( InterruptedException exp )
	    {
		System.out.println( exp.getMessage());
		exp.printStackTrace( System.out );
	    }
	}
	catch( IOException exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}
	catch( Exception exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}

	// Now diff the ASCII file against the good file.
	return SORTEDDIFFIT( asciiFile, goodFile, diffFile, stderrFile );
    }

    /*
     *  Build the out, good, diff filenames from input root 
     *  then calls the DIFFIT() which does the work.
     */
    static public int DIFFIT( String testName )
    {
	return DIFFIT( mkOutFileName( testName ),
		       mkGoodFileName( testName ),
		       mkDiffFileName( testName ),
		       mkStderrFileName( testName) );
    }

    /**
       Does a diff between two files by exec'ing a new process to run the
       system 'diff' utility.  Saves the result in a third file
       @return the return value of the diff
    */
    static public int DIFFIT( String testFile, String goodFile, String diffFile,
                              String stderrFile )
    {
        Runtime runtime = Runtime.getRuntime();
        int exitValue = -1;

        try
        {
            Process proc = runtime.exec( "diff " + goodFile +" "+ testFile );

            try
            {
                java.io.InputStream  stdoutStream = proc.getInputStream();
                java.io.InputStream  stderrStream = proc.getErrorStream();

                proc.waitFor();
                exitValue = proc.exitValue();

                // Save the outputs of the exec'd process.
                streamToFile( stdoutStream, diffFile );
                streamToFile( stderrStream, stderrFile );
            }
            catch( InterruptedException exp )
            {
                System.out.println( exp.getMessage());
                exp.printStackTrace( System.out );
            }
        }
        catch( IOException exp )
        {
            System.out.println( exp.getMessage());
            exp.printStackTrace( System.out );
        }
        catch( Exception exp )
        {
            System.out.println( exp.getMessage());
            exp.printStackTrace( System.out );
        }
        return exitValue;
    }




    /**
       Does a diff between two sorted files by exec'ing a new process to run the
       system 'sort' and 'diff' utility.  Saves the result in a third file
       @return the return value of the diff
    */
    static public int SORTEDDIFFIT( String testFile, String goodFile, String diffFile,
			      String stderrFile )
    {
	Runtime runtime = Runtime.getRuntime();
	int exitValue = -1;

	try
	{
            // testtools are not installed so we need to
            // use the absolute path to call fits2ascii.pl
            // this may not be the best place to keep this file
//            String testToolDir = System.getProperties()
//                                        .getProperty( "TESTTOOLS_DIR" );
            URL testToolUrl = SedLibTestUtils.class.getResource("/test_tools");
            String testToolDir = testToolUrl.getPath();


            String diffCommand = String.format(
                                                "ksh %s/ksh/sortedDiff.ksh %s %s",
                                                testToolDir, goodFile, testFile );
            // create the process
            Process proc = runtime.exec (diffCommand); //runtime.exec( "diff " + goodFile +" "+ testFile );

	    try
	    {
		java.io.InputStream  stdoutStream = proc.getInputStream();
		java.io.InputStream  stderrStream = proc.getErrorStream();

		proc.waitFor();
		exitValue = proc.exitValue();

		// Save the outputs of the exec'd process.
		streamToFile( stdoutStream, diffFile );
		streamToFile( stderrStream, stderrFile );
	    }
	    catch( InterruptedException exp )
	    {
		System.out.println( exp.getMessage());
		exp.printStackTrace( System.out );
	    }
	}
	catch( IOException exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}
	catch( Exception exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}
	return exitValue;
    }

    /*
     * Used by several of these utilities,  this method directs
     * the provided stream to a file.
     */
    static void streamToFile( InputStream inStream, String filename )
    {
	try
	{
	    FileOutputStream fos = new FileOutputStream( new File( filename) );

	    while( true )
	    {
		final int numBytes = 1000;
		byte[] bytes = new byte[numBytes ];
		int numRead = inStream.read( bytes, 0, numBytes  );
			
		if ( numRead == -1 ){ break; }

		fos.write( bytes, 0, numRead );
	    };
	    fos.close();
	}
	catch( FileNotFoundException exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}
	catch( IOException exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}
    }

    // ********************************************************************************
    // MCD CUT LINE
    // ********************************************************************************
    // Methods below are used by test routines not yet reviewed for VAO


    /*
     *  Builds the out, good, diff files names then calls the
     *  appropriate validateVOT().
     */
    static public long validateVOT( String testName )
    {
	return validateVOT( mkOutFileName( testName ) ,
			    mkGoodFileName( testName ),
			    mkDiffFileName( testName ),
			    mkStderrFileName( testName) );
    }

    /**
       Validates a VOTable by exec'ing a new process to run the validation.
       Saves the result in a 'diff' file.
       Note: the validation does not set a status value.
       @return 0 if the size of the output 'diff' file is zero, greater-than-zero otherwise ;
       -1 indicates an exception was thrown.
    */
    static public long validateVOT( String testFile, String goodFile, String diffFile,
				    String stderrFile )
    {
	Runtime runtime = Runtime.getRuntime();
	int exitValue = -1;

	try
	{
	    String validateCommand = "java -jar /data/nvo/soft/cfa//devtools/validation.jar -q /data/nvo/staff/jcant//workspace//schemas/VOTable-1.1.xsd";

	    Process proc = runtime.exec( validateCommand  +"  "+ testFile );
	    try
	    {
		java.io.InputStream  stdoutStream = proc.getInputStream();
		java.io.InputStream  stderrStream = proc.getErrorStream();

		proc.waitFor();
		exitValue = proc.exitValue();

		// Save the outputs of the exec'd process.
		streamToFile( stdoutStream, diffFile );
		streamToFile( stderrStream, stderrFile );

		File validationOutputFile = new File( diffFile );
		return validationOutputFile.length();
	    }
	    catch( InterruptedException exp )
	    {
		System.out.println( exp.getMessage());
		exp.printStackTrace( System.out );
	    }
	}
	catch( IOException exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}
	catch( Exception exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}

	// This shows it didn't work.
	return -1;
    }

    /*
     *  Builds the out, good, diff files names then calls grepIt()
     *  @see #grepIt(String,String, String, String )
     */
    static public int grepIt( String grepFor, String testName )
    {
	return grepIt( grepFor, mkOutFileName( testName ) ,
		       mkStdoutFileName( testName),
		       mkStderrFileName( testName)
		       );
    }

    /**
       grep <code>fileName</code> for the string <code>grepFor</code>
       Execs a new process to run the system 'grep' utility.
       @return the return value of the grep
    */
    static public int grepIt( String grepFor, String testFile, String stdoutFile,
			      String stderrFile )
    {
	Runtime runtime = Runtime.getRuntime();
	int exitValue = -1;

	try
	{
	    // We pass an array of args to exec rather than the complete command
	    // line. When we do the latter, it gets confused if there are spaces
	    // in the search pattern; searches for the 1st word and treats the
	    // remaining words as file names.  If we quote the search pattern
	    // then, for some reason, it includes the quotes in the search.
	    String[] args = { "grep", "replace this", "replace this too" };
	    args[ 1 ] = grepFor;
	    args[ 2 ] = testFile;

	    Process proc = runtime.exec( args );
	    try
	    {
		java.io.InputStream  stdoutStream = proc.getInputStream();
		java.io.InputStream  stderrStream = proc.getErrorStream();

		proc.waitFor();
		exitValue = proc.exitValue();

		// Save the outputs of the exec'd process.
		streamToFile( stdoutStream, stdoutFile );
		streamToFile( stderrStream, stderrFile );
	    }
	    catch( InterruptedException exp )
	    {
		System.out.println( exp.getMessage());
		exp.printStackTrace( System.out );
	    }

	}
	catch( IOException exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}
	catch( Exception exp )
	{
	    System.out.println( exp.getMessage());
	    exp.printStackTrace( System.out );
	}
	return exitValue;
    }


    static PrintStream m_savedSystemErr = null;
    static PrintStream m_savedSystemOut = null;
    static PrintStream m_redirectedErr = null;

    /**
     *  Redirects system error output to the a file whose name is
     *  constructed form testName.
     *  If 'testName' is null, restores system error to the
     *  printstream that was in place before the last call with
     *  on-null testName.  The intent is that this be called in
     *  pairs, first with a testName, then with null.
     *  Note: If there's a chance that an exception will be thrown
     *  in the code between a pair of calls to this method, put
     *  the first in a try block and the 2nd in a finally block.
     *  If the stream does not get reset, the next test will inherit
     *  the stream that write to a file.
     *
     * */
    static public void setSystemErr( String testName )
    {
	if ( testName == null )
	{
	    System.err.flush();
	    m_redirectedErr.close();
	    System.setErr( m_savedSystemErr );
	    m_savedSystemErr = null;
	}
	else
	{
	    m_savedSystemErr = System.err;
	    try
	    {
		String fileName = mkAppStderrFileName( testName );
		if ( m_redirectedErr != null )
		{
		    m_redirectedErr.close();
		    m_redirectedErr = null;
		}
		m_redirectedErr = new PrintStream( new File( fileName ) );
		System.err.flush();
		System.setErr( m_redirectedErr );
	    }
	    catch( FileNotFoundException exp )
	    {
		throw new RuntimeException( "TestUtils.setSystemError: " + exp );
	    }
	}
    }

    /**
     *  @see #setSystemErr
     */
    static public void setSystemOut( String testName )
    {
	if ( testName == null )
	{
	    System.out.flush();
	    System.setOut( m_savedSystemOut );
	    m_savedSystemOut = null;
	}
	else
	{
	    m_savedSystemOut = System.out;
	    try
	    {
		String fileName = mkAppStdoutFileName( testName );
		PrintStream printStream = new PrintStream( new File( fileName ) );
		System.out.flush();
		System.setOut( printStream );
	    }
	    catch( FileNotFoundException exp )
	    {
		throw new RuntimeException( "TestUtils.setSystemOut: " + exp );
	    }
	}
    }

// ********************************************************************************
// MCD NOTE: Used in STC testing
// ********************************************************************************
//    /**
//     *  A convience function to hide the exception handling; we rethrow
//     *  the error which will make a test fail.
//     */
//    static public PrintStream getPrintStream( String fileName )
//    {
//	try
//	 {
//	     return new PrintStream( new File( fileName ) );
//	 }
//	catch( Exception exp )
//	{
//	    throw new RuntimeException( "Could not create Printstream from '"
//					+ fileName + "'" + exp.getMessage() );
//	}
//    }
//
//    /**
//     *  Copies the <em>inFile</em>  to <em> toFile</em>
//     */
//    static public boolean copyFile( String inFile, String toFile )
//    {
//	Runtime runtime = Runtime.getRuntime();
//	String cmd = "cp -p " + inFile + "  " + toFile;
//
//	try
//	{
//	    runtime.exec( cmd );
//	}
//	catch( IOException exp )
//	{
//	    return false;
//	}
//
//	// wait for the file to be copied; I think it's possible to
//	// return and to try to used the file before it's 'settled'.
//
//	try
//	{
//	    Thread.sleep( 2000 );
//	}
//	catch( InterruptedException exp )
//	{
//	    System.err.println( "copySedLog: caught iterupped exception" );
//	    return false;
//	}
//	return true;
//    }
//
//    /**
//       Runs a program using Runtime.exec().
//       @param progName the program to run
//       @param progArgs list of command line arguements to be passed to progName.
//       @param stdinFile file to be piped to progName as STDIN
//       @param stdoutFile progName STDOUT is piped to this file.
//       @return returns the exit code of progName
//    */
//    static public int RUNIT( String progName, String[] progArgs,
//			     String stdinFile, String stdoutFile, String stderrFile )
//    {
//	Runtime runtime = Runtime.getRuntime();
//	int exitValue = -1;
//
//	try
//	{
//	    // Build a command line from the args we are given
//	    String commandLine = progName + "";
//
//	    if (progArgs != null )
//	    {
//		for ( int i = 0; i < progArgs.length; i++ )
//		{
//		    commandLine += " " + progArgs[ i ] + "";
//		}
//	    }
//	    //  Run it!
//	    Process proc = runtime.exec( commandLine, null );
//	    
//	    // Capture the standard output of the program in a file
//	    try
//	    {
//		java.io.InputStream  stdoutStream = proc.getInputStream();
//		java.io.InputStream  stderrStream = proc.getErrorStream();
//
//		proc.waitFor();
//		exitValue = proc.exitValue();
//
//		// Save the outputs of the exec'd process.
//		streamToFile( stdoutStream, stdoutFile );
//		streamToFile( stderrStream, stderrFile );
//	    }
//	    catch( InterruptedException exp )
//	    {
//		System.out.println( exp.getMessage());
//		exp.printStackTrace( System.out );
//	    }
//
//	}
//	catch( IOException exp )
//	{
//	    System.out.println( exp.getMessage());
//	    exp.printStackTrace( System.out );
//	}
//
//	return exitValue;
//    }





}
