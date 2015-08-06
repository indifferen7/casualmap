package se.cs.casualmap.api.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.cs.casualmap.api.map.Map;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MapReader {

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

    public Map read(String fileName) {
        return read(Paths.get(fileName));
    }

    public static class MapReaderException extends RuntimeException {
        public MapReaderException(Throwable cause) {
            super(cause);
        }
    }
}
