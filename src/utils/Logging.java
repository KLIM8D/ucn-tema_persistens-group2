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
                return "Kunne ikke hente den n\u00F8dvendige data. Pr\u00F8v igen senere eller kontakt support";
            case 1:
                return "Det var pt. ikke muligt at oprette et produkt. Pr\u00F8v igen senere eller kontakt support";
            case 2:
                return "Det var pt. ikke muligt at opdatere produktet. Pr\u00F8v igen senere eller kontakt support";
            case 3:
                return "Det var pt. ikke muligt at oprette en produkt kategori. Pr\u00F8v igen senere eller kontakt support";
            case 4:
                return "Det var pt. ikke muligt at opdatere produkt kategorien. Pr\u00F8v igen senere eller kontakt support";
            default:
                return "Der skete en fejl i udf\u00F8rselen af denne handling. Kontakt support";
        }
    }
}
