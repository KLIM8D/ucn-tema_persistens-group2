package views;

public class GlobalUI
{
	private static String _systemTitle = "Tema Persistens";
	private static String _systemDescription = "UCN-DM79, Gruppe 2";
	private static String _systemBuild = "29102012";
	
	public static String systemInformation(int code)
	{
		switch(code)
		{
		case 01:
			return _systemTitle;
		case 02:
			return _systemDescription;
		case 03:
			return _systemBuild;
		}
		return null;
	}
}
