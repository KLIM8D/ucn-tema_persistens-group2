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
            case 1:
                return "Det var pt. ikke muligt at oprette et produkt. Prøv igen senere eller kontakt support";
            case 2:
                return "Det var pt. ikke muligt at opdatere produktet. Prøv igen senere eller kontakt support";
            case 3:
                return "Det var pt. ikke muligt at oprette en produkt kategori. Prøv igen senere eller kontakt support";
            case 4:
                return "Det var pt. ikke muligt at opdatere produkt kategorien. Prøv igen senere eller kontakt support";
            default:
                return "Der skete en fejl i udførselen af denne handling. Kontakt support";
        }
    }
}
