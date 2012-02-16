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

/***********************************************************************
*
* File: SedTestBase.java
*
* Author:  jcant		Created: Tue Dec  9 13:14:52 2008
*
*
* Update History:
*   2010-12-23:  MCD  Add writeSED,
*   2011-12-30:  MCD  Reduced to set used by current test suite.
*  
***********************************************************************/
package cfa.vo.sedlib;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import cfa.vo.testtools.SedLibTestUtils;

import cfa.vo.sedlib.io.SedFormat;
import cfa.vo.sedlib.io.SedIOFactory;
import cfa.vo.sedlib.io.ISedDeserializer;
import cfa.vo.sedlib.io.ISedSerializer;
import cfa.vo.sedlib.io.VOTableDeserializer;
import cfa.vo.sedlib.io.VOTableSerializer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.FileHandler;


public class SedTestBase extends TestCase
{
    static final Logger logger = Logger.getLogger( SedTestBase.class.getName() );
    static final Logger sedLogger = Logger.getLogger("cfa.vo.sedlib");
    
    static SedLibTestUtils  tu = SedLibTestUtils.getTestUtils();
    
    static public final int SUCCESS = 0;
    static public final int FAILURE = 1;
    static public final String  EXT_VOT  = ".vot";
    static public final String  EXT_XML  = ".xml";
    static public final String  EXT_FITS = ".fits";
    
    protected List<SedFormat> formats = new ArrayList();

    static public String getOutputDir() { return m_outputDir; }
    static String m_dataRoot = "";
    static String m_testRoot = "";
    static String m_outputDir = "";

    static
    {

	URL testDataUrl = SedTestBase.class.getResource("/test_data");

	m_testRoot = testDataUrl.getPath();
	m_dataRoot = m_testRoot+"/data/";
	m_outputDir = m_testRoot+"/out/";
	SedLibTestUtils.setOutputDirectories (m_outputDir);
	SedLibTestUtils.GOOD_DIR = m_testRoot+"/baseline/";
	SedLibTestUtils.DATA_DIR = m_dataRoot;
    }

    // Constructor
    public SedTestBase( String name )
    {
	super(name);

	formats.add(SedFormat.VOT);
	formats.add(SedFormat.FITS);
	    
	try
	{
	    FileHandler handler = new FileHandler(SedLibTestUtils.mkLogFileName (name));
	    sedLogger.addHandler(handler);
	    sedLogger.setUseParentHandlers (false);
	}
	catch (IOException e)
	{
	    sedLogger.setUseParentHandlers (true);
	}
    }

    /** 
     *  Read file of the specified format to populate SED object 
     */
    static public Sed readSED( SedFormat format, String fname )
    {
	Sed doc;

	try
	{
	    ISedDeserializer deserializer = SedIOFactory.createDeserializer( format );
	    doc = deserializer.deserialize( fname );
	}
	catch ( Exception exp )
	{
	    handleException( exp );
	    doc = null;
	}

	return doc;
    }

    /** 
     *  Write the SED object in the specified format.
     */
    static public int writeSED( SedFormat format, String fname, Sed doc )
    {
	int result = SUCCESS;
	
	try
	{
	    ISedSerializer serializer = SedIOFactory.createSerializer( format );
	    serializer.serialize( fname, doc );
	}
	catch ( Exception exp )
	{
	    handleException( exp );
	    result = FAILURE;
	}

	return result;
    }

    static void handleException( Throwable exp )
    {
	System.err.println( exp );

	StackTraceElement[] elems = exp.getStackTrace();
	for ( int ii = 0; ii < Math.min(10,elems.length); ii++ )
	{
	    String msg = elems[ii].toString();
	    System.err.println( "  @ " + msg.substring(0,Math.min(msg.length(), 90) ) );
	}
    }

}
