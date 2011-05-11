package cfa.vo.sedlib.common;


/**
 * This class provides provides a method to expand the validation error 
 * enumeration by adding notes. 
 *
 *
 */
public class ValidationError
{

    protected ValidationErrorEnum error;
    protected String note = "";

    public ValidationError (ValidationErrorEnum error) 
    {
        this.error = error;
    };

    public ValidationError (ValidationErrorEnum error, String note)
    {
        this.error = error;
        this.note = note;
    };

    public void addNote (String note)
    {
        this.note = note;
    }

    public ValidationErrorEnum getError ()
    {
        return this.error;
    }

    public String getErrorMessage ()
    {
        String message = error.getErrorMessage ();

        if (this.note.length () > 0)
            message = message +" ("+note+")";

        return message;
    }

}

