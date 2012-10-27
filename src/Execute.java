import views.SystemUI;

final class Execute
{
    public static void main(String[] args)
    {
    	try
        {
    		SystemUI window = new views.SystemUI();
        	window.frmSystemWindow.setVisible(true);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}