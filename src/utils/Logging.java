package utils;

/**
 * Created: 27-10-2012
 * @version: 0.1
 * Filename: Logging.java
 * Description:
 * @changes
 */

public class Logging
{
    public Logging()
    {
    }

    public static String handleException(Exception ex, int returnMessage)
    {
        //Add logging functionality here

        return messages(returnMessage);
    }

    private static String messages(int messageIndex)
    {
        switch (messageIndex)
        {
            case 0:
                return "Kunne ikke hente den nødvendige data. Prøv igen senere eller kontakt support";

            default:
                return "Der skete en fejl i udførselen af denne handling. Kontakt support";
        }
    }
}
