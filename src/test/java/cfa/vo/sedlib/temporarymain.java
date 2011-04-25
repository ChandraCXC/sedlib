/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfa.vo.sedlib;

import cfa.vo.sedlib.common.SedInconsistentException;
import cfa.vo.sedlib.common.SedNoDataException;
import cfa.vo.sedlib.common.SedParsingException;
import cfa.vo.sedlib.common.SedWritingException;
import cfa.vo.sedlib.io.SedFormat;
import cfa.vo.testtools.SedLibTestUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.SAXException;
import uk.ac.starlink.table.StarTable;
import uk.ac.starlink.table.StoragePolicy;
import uk.ac.starlink.util.DataSource;
import uk.ac.starlink.votable.VOElement;
import uk.ac.starlink.votable.VOElementFactory;
import uk.ac.starlink.votable.VOTableBuilder;

/**
 *
 * @author olaurino
 */
public class temporarymain extends SedTestBase {

    public temporarymain(String name) {
        super(name);
    }

    /**
     * @param args the command line arguments
     */
    public void testNoTest() throws SedParsingException, SedInconsistentException, IOException, SedWritingException, SedNoDataException, SAXException {
        String inputName = "BasicSpectrum+Photom.vot";
        String outputName = "BasicSpectrum+Photom.fits";
        SedFormat inputFormat = SedFormat.VOT;
        SedFormat outputFormat = SedFormat.FITS;

//        final String input = SedLibTestUtils.mkInFileName(inputName);
        final String input = "/pool14/brefsdal/sedlib/3c273.xml";
        String output = SedLibTestUtils.mkOutFileName(outputName);

        Sed sed = Sed.read(input, inputFormat);

//        System.out.println(sed);



//        System.out.println(input);

        VOTableBuilder b = new VOTableBuilder();
        DataSource ds = new DataSource() {

            @Override
            protected InputStream getRawInputStream() throws IOException {
                return new FileInputStream(new File(input));
            }
        };

        StarTable t = b.makeStarTable(ds, true, StoragePolicy.ADAPTIVE);

        System.out.println(t.getColumnInfo(7).getName());
        System.out.println(t.getCell(0, 7).getClass());

        VOElement voElement = new VOElementFactory().makeVOElement( new File(input) );




//
//        for(Object o : t.getParameters()) {
//            DescribedValue v = (DescribedValue) o;
//            System.out.println(v.getInfo().getUtype());
//        }

//        sed.write(output, outputFormat);
    }

}
