package cfa.vo.sedlib.common;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
    A class to tailor the format of the output messages to for 
    the SED library.
*/
public class SedFormatter extends Formatter
{

   /**
    * Override the format of the output message of the logger.
    */
   public String format (LogRecord record)
   {
       return record.getLevel() + ": " + record.getMessage()+"\n";
   }

}
