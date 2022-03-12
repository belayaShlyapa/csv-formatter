package belaya.shlyapa.csvformatter.message;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MessageCleaner {

    private static final int NUMBER_OF_ROWS_TO_REMOVE = 10;

    public StringBuffer clean(FileReader file) {
        try {
            return cleanThrowingException(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuffer cleanThrowingException(FileReader file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(file);
        StringBuffer cleanMessage = new StringBuffer();
        String strLine;
        int rowCounter = 0;
        while ((strLine = bufferedReader.readLine()) != null) {
            // Remove useless introduction lines to perform csv handling.
            if (rowCounter < NUMBER_OF_ROWS_TO_REMOVE) {
                rowCounter++;
                continue;
            }

            if (strLine.length() > 0) {
                cleanMessage.append(formatAmountForCsvHandling(strLine)).append('\n');
            }
        }
        return cleanMessage;
    }

    /**
     * Remove NBSP character from provided string.
     * In this case, useful to transform, for example, '1 000,99' format amount to '1000,99' format.
     *
     * @param String with NBSP characters
     * @return String without NBSP character.
     */
    private String formatAmountForCsvHandling(String strLine) {
        if (Objects.isNull(strLine)) return "";
        String strLineWithoutNbspCharacter = strLine.replaceAll("\u00a0", "");
        return strLineWithoutNbspCharacter;
    }
}
