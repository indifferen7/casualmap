package se.cs.casualmap.api.io;

import com.google.gson.Gson;
import se.cs.casualmap.api.map.Map;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class reads maps, just like a savvy pirate.
 */
public class MapReader {

    /**
     * Attempts to parse a json formatted representation of
     * a map in the file at the specified path into POJOs.
     * @param path the file path
     * @return the map
     */
    public Map read(Path path) {
        try {
            StringBuilder builder = new StringBuilder();

            for (String line : Files.readAllLines(path, Charset.forName("UTF-8"))) {
                builder.append(line);
            }

            return new Gson().fromJson(builder.toString(), Map.class);
        } catch (IOException e) {
            throw new MapReaderException(e);
        }
    }

    /**
     * Attempts to parse a json formatted representation of
     * a map in the file with the provided file name into POJOs.
     * @param path the file path
     * @return the map
     */
    public Map read(String fileName) {
        return read(Paths.get(fileName));
    }

    public static class MapReaderException extends RuntimeException {
        public MapReaderException(Throwable cause) {
            super(cause);
        }
    }
}
