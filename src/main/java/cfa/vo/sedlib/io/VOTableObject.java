package cfa.vo.sedlib.io;

import java.util.ArrayList;
import java.util.List;

import uk.ac.starlink.votable.VOElement;


/**
 *  Stores VOTable data including a VOElement and a list of star tables. The
 *  class contains the root of the document and then a startable for each
 *  table inside the document.
 */
public class VOTableObject
{

    VOElement root;
    List<SedStarTable> starTableList;

    public VOTableObject ()
    {
        this.root = null;
        this.starTableList = new ArrayList<SedStarTable>();
    }

}
