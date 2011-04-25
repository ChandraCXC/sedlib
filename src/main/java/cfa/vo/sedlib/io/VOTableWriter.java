package cfa.vo.sedlib.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import uk.ac.starlink.table.StarTable;
import uk.ac.starlink.votable.DataFormat;
import uk.ac.starlink.votable.VOElement;
import uk.ac.starlink.votable.VOSerializer;
import cfa.vo.sedlib.common.SedWritingException;
import cfa.vo.sedlib.common.VOTableKeywords;


/**
    Outputs a VOTable document to file or stream.
*/    
class VOTableWriter extends AbstractWriter<VOTableObject>
{
    static final String INDENT_UNIT = "    ";

    VOTableObject tableData = null;
    int currentStarTable = 0;


    /**
     * Writes VOTable data to a file.
     * @param outStream
     *   {@link java.io.OutputStream}
     * @param wrapper
     *   {@link VOTableObject}
     */
    public void write(OutputStream outStream, VOTableObject data) throws SedWritingException
    {
        this.tableData = (VOTableObject) data;

        Transformer trans = null;
        VOSerializer voSer = null;
        VOElement top = tableData.root;

        // Write the xml spec line.
        PrintWriter pw = new PrintWriter( outStream );
        pw.println(  IOConstants.XML_VERSION_ENCODING );
        pw.flush();


        try
        {
            // Create an "identity" transformer - copies input to output
            trans = TransformerFactory.newInstance().newTransformer();

            trans.setOutputProperty(OutputKeys.METHOD, "xml");
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes" );
            trans.setOutputProperty(OutputKeys.INDENT, "no" );
        }
        catch(TransformerConfigurationException tce) {
            throw new SedWritingException ("Caught TransformerConfigurationException: " +
                       tce.getMessage(), tce);
        }

        try
        {
            BufferedWriter out =
                new BufferedWriter( new PrettifyingOutputStreamWriter( outStream ) );

            VOElement[] vArray = {top};

            this.recursivePrint( vArray, trans, voSer, out );

        }
        catch (IOException ioe)
        {
            throw new SedWritingException ("IOException occurred while printing VOTable to output stream", ioe);
        }
        catch (TransformerException te)
        {
            throw new SedWritingException ("TransformerException occurred while transforming VOTable to file", te);

        }
    }


    /**
        Writes an array of VOElements using the specified writer.  This turns out to be
        somewhat complicated as several styles of writing are used;
        sometimes the writer is written to directly, sometimes it is
        passed to a XML Transformer, sometimes passed to a
        VOSerializer.  All of these seem to write a little
        differently which makes manipulationg the appearance of the
        output difficult.  See PrettifyingOutputStreamWriter for the
        solution to this.
    */
    private void recursivePrint(VOElement[] voElems,
                 Transformer xTrans,
                 VOSerializer voSer,
                 BufferedWriter out)
                throws IOException, TransformerException, SedWritingException
    {
        for(int step=0; step < voElems.length;step++)
        {
            VOElement node = voElems[step];

            if( node.getNodeType() == Node.ELEMENT_NODE)
            {
                NamedNodeMap nMap = node.getAttributes();
                if(node.getNodeName().equals(VOTableKeywords._VOTABLE))
                {
                    out.write("<VOTABLE");
                    if(nMap.getLength() > 0)
                        this.printAttributes(nMap, out, true );

                    out.write(">");
                    out.newLine();
                    out.flush();

                    this.recursivePrint(node.getChildren(), xTrans,
                                voSer, out);

                    out.write("</VOTABLE>");
                    out.newLine();
                    out.flush();
                }
                else if (node.getNodeName().equals(VOTableKeywords._RESOURCE))
                {
                    out.write("<RESOURCE");
                    if(nMap.getLength() > 0)
                        printAttributes(nMap, out);
                    out.write(">");
                    out.newLine();
                    out.flush();

                    this.recursivePrint(node.getChildren(), xTrans,
                                voSer, out);

                    out.write("</RESOURCE>");
                    out.newLine();
                    out.flush();
                }
                else if(node.getNodeName().equals(VOTableKeywords._TABLE))
                {
                    out.write("<TABLE");
                    if(nMap.getLength() > 0)
                        printAttributes(nMap, out);
                    out.write(">");
                    out.newLine();
                    out.flush();
                    //If there is a DATA child, then use VOSerializer to
                    // output.  Otherwise it's a metadata table, and
                    // everything is output above.
/*                    if(node.getChildByName(VOTableKeywords._DATA) != null)
                    {
//                        StarTable starTable = (SedStarTable)
//                            ((VOTableWrapper)_wrapper).nextTable();
                        StarTable starTable = (SedStarTable) this.tableData.starTableList.get(this.currentStarTable++);

                        voSer = VOSerializer.makeSerializer
                                ( DataFormat.TABLEDATA, starTable );
                    }
*/
                    this.recursivePrint(node.getChildren(), xTrans,
                                voSer, out);

                    // add data if it exists
                    StarTable starTable = (SedStarTable) this.tableData.starTableList.get(this.currentStarTable++);
                    if (starTable != null)
                    {
                        voSer = VOSerializer.makeSerializer
                                ( DataFormat.TABLEDATA, starTable );
                        voSer.writeFields( out );

                        voSer.writeInlineDataElement(out);
                    }

                    out.write("</TABLE>");
                    out.newLine();
                    out.flush();
                }
/*                else if (node.getNodeName().equals(VOTableKeywords._DATA))
                {
                    // This writes the fields in the segment
                    voSer.writeFields( out );

                    voSer.writeInlineDataElement(out);
                }//DATA
*/
                else if (!node.getNodeName().equals(VOTableKeywords._FIELD))
                {
                    if(node.getAttribute(VOTableKeywords._UTYPE).equals(VOTableKeywords.getName(VOTableKeywords.SEG)))
                    {
                        out.write("<GR_____________OUP");
                        if(nMap.getLength() > 0)
                            printAttributes(nMap, out);
                        out.write(">");
                        out.newLine();
                        out.flush();

                        //If there is a DATA child, then use VOSerializer
                        // to output.  Otherwise it's a metadata table,
                        // and everything is output above.

                        if(node.getChildByName(VOTableKeywords._DATA) != null)
                        {
//                            StarTable st = (SedStarTable)
//                                    ((VOTableWrapper)_wrapper).nextTable();
                            StarTable st = (SedStarTable) this.tableData.starTableList.get(this.currentStarTable++);
                            voSer = VOSerializer.makeSerializer
                                        ( DataFormat.TABLEDATA, st );
                        }
                        this.recursivePrint(node.getChildren(), xTrans,
                                    voSer, out);
                    }
                    else
                    {
                        // This writes out the PARAM fields
                        xTrans.transform(new DOMSource((Node)node),
                                new StreamResult( out ));
                    }
                }
            }
            else
            {
                throw new SedWritingException ("recursivePrint: Unknown node type. Expected ELEMENT_NODE, recieved " +
                		Integer.toString(node.getNodeType())); 
            }
        }
    }

    private void printAttributes(NamedNodeMap nMap, BufferedWriter out)
    throws IOException
    {
        printAttributes( nMap, out, false );
    }

    private void printAttributes(NamedNodeMap nMap, BufferedWriter out,
                boolean bAppendNewline ) throws IOException
    {
        for(int mIdx=0; mIdx < nMap.getLength(); mIdx++)
        {
            String attString = nMap.item(mIdx).getNodeName() + "=\"" +
                    nMap.item(mIdx).getNodeValue() + "\"";

            out.write(" " + attString);

            if ( bAppendNewline )
            {
                out.write( "\n");
            }
        }

    }
}
