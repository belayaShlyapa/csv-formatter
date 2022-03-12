package belaya.shlyapa.csvformatter.csv;

import belaya.shlyapa.csvformatter.entity.Transaction;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

public class CsvHandler {

    private static final char characterSeparator = ';';

    public List<Transaction> handle(StringBuffer message) {
        List<Transaction> transactionBeans = read(message);
        clean(transactionBeans);
        return transactionBeans;
    }

    private List<Transaction> read(StringBuffer message) {
        return new CsvToBeanBuilder(new StringReader(message.toString()))
                .withType(Transaction.class).withSeparator(characterSeparator).build().parse();
    }

    /**
     * For each bean, remove break lines, carriage return, multiple spaces.
     *
     * @param List of beans to clean.
     * @return List of beans cleaned.
     */
    private void clean(List<Transaction> beans) {
        for (Transaction bean : beans) {
            bean.setWording(bean.getWording().replaceAll("\n", "").replaceAll("\r", "").replaceAll(" +", " ").trim());
        }
        // Todo : retourner la liste ?
    }

    public void write(List<Transaction> beans, Path filePath) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Writer writer = new FileWriter(filePath.toString());
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(beans);
        writer.close();
    }
}
