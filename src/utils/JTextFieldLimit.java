package utils;

/**
 * @author: Morten Klim Sørensen - mail@kl1m.dk
 * Created: 08-09-2012
 * @version: 0.1
 * Filename: JTextFieldLimit.java
 * Description:
 * @changes
 */

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial") //removes annyong serial warning in eclipse....
public class JTextFieldLimit extends PlainDocument
{
    private int limit;

    public JTextFieldLimit(int limit)
    {
        super();
        this.limit = limit;
    }

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException
    {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit)
            super.insertString(offset, str, attr);
    }
}
