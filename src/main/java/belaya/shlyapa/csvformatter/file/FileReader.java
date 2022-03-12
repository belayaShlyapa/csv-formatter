package belaya.shlyapa.csvformatter.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class FileReader {
    public java.io.FileReader read(Path filePath) {
        File file = new File(filePath.toString());
        encodeFileToUtf8(file);
        try {
            return new java.io.FileReader(filePath.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void encodeFileToUtf8(File file) {
        try {
            String content = FileUtils.readFileToString(file, "ISO8859_1");
            FileUtils.write(file, content, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
