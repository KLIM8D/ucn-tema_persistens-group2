package utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Helper 
{
	
	public static void checkIfInt(JTextField data) 
	{
		try 
		{
			Integer.parseInt(data.getText());
		}
		catch (NumberFormatException err) 
		{
			JOptionPane.showMessageDialog(null, "Dette felt kan kun indeholde tal", "Information", JOptionPane.WARNING_MESSAGE, new ImageIcon(Helper.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
			data.setText(null);
		}
	}
	
	public static void checkIfLong(JTextField data) 
	{
		try 
		{
			Long.parseLong(data.getText());
		}
		catch (NumberFormatException err) 
		{
			JOptionPane.showMessageDialog(null, "Dette felt kan kun indeholde tal", "Information", JOptionPane.WARNING_MESSAGE, new ImageIcon(Helper.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
			data.setText(null);
		}
	}

    public static void checkIfDouble(JTextField data)
    {
        try
        {
            Double.parseDouble(data.getText());
        }
        catch (NumberFormatException err)
        {
            JOptionPane.showMessageDialog(null, "Dette felt kan kun indeholde decimal tal", "Information", JOptionPane.WARNING_MESSAGE, new ImageIcon(Helper.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
            data.setText(null);
        }
    }

    public static int showConfirmDialog(String objectName)
    {
        int option = JOptionPane.showConfirmDialog (null, String.format("Er du sikker på du vil slette dette %s?", objectName));
        if (option == JOptionPane.YES_OPTION )
            return 1;

        return 0;
    }
	
	//Added because of stupid a bug with setLocationRelativeTo(null) for some JFrames or JDialogs
	public static void centerOnScreen(final Component c)
	{
	    final int width = c.getWidth();
	    final int height = c.getHeight();
	    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screenSize.width / 2) - (width / 2);
	    int y = (screenSize.height / 2) - (height / 2);
	    c.setLocation(x, y);
	}

    public static <E> ArrayList<E> makeCollection(Iterable<E> iter)
    {
        ArrayList<E> list = new ArrayList<E>();
        for (E item : iter)
            list.add(item);
        return list;
    }
}
