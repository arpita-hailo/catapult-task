package steps.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
    private static final String BOOK_CONTENT_FILE_LOCATION = "src/test/resources/book-content/";
    private static Logger logger = LogManager.getLogger(FileUtils.class);

    public static String getBookContentFromFile(String contentFile) {
        String content = null;

        if(contentFile != null){
            try {
                content = Files.readString(Path.of(BOOK_CONTENT_FILE_LOCATION + contentFile));
            } catch (IOException e) {
                logger.error("Cannot open content file : {}",contentFile);
                throw new RuntimeException(e);

            }
        }

        return content;
    }

}
