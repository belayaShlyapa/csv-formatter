package belaya.shlyapa.csvformatter;

import belaya.shlyapa.csvformatter.csv.CsvHandler;
import belaya.shlyapa.csvformatter.entity.Transaction;
import belaya.shlyapa.csvformatter.file.FileReader;
import belaya.shlyapa.csvformatter.message.MessageCleaner;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CsvFormatter {

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        Path filePathToHandle = Path.of(inputFile);
        FileReader fileReader = new FileReader();
        java.io.FileReader file = fileReader.read(filePathToHandle);

        MessageCleaner messageCleaner = new MessageCleaner();
        StringBuffer message = messageCleaner.clean(file);

        CsvHandler csvHandler = new CsvHandler();
        List<Transaction> transactionBeans = csvHandler.handle(message);
        Path filePathHandled = Path.of(outputFile);
        try {
            csvHandler.write(transactionBeans, filePathHandled);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }
}
