import views.SystemUI;

final class Execute
{
    public static void main(String[] args)
    {
    	try {
    		SystemUI window = new views.SystemUI();
        	window.frmSystemWindow.setVisible(true);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Ding dong the wich is dead!");
    	}
    }
}