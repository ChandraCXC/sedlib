package cfa.vo.sedlib;

/**
 * <p>Java class for textParam complex type.
 * 
 * 
 */
public class TextParam
    extends Param
{
   public TextParam () {}

   public TextParam (String value, String name, String ucd)
   {
      super (value, name, ucd);
   }

   public TextParam (String value, String name, String ucd, String id)
   {
      super (value, name, ucd, id);
   }

   public TextParam (String value)
   {
      super (value);
   }

}
