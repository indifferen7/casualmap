package se.cs.casualmap.api.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.cs.casualmap.api.map.Map;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * If there is a MapReader there must be a MapWriter.
 */
public class MapWriter {

    private final Map map;

    private MapWriter(Map map) {
        this.map = checkNotNull(map);
    }

    /**
     * Creates a new instance of the map writer. Takes
     * the map that is to be written as reference.
     * @param map the map
     * @return a new fresh map writer
     */
    public static MapWriter forMap(Map map) {
        return new MapWriter(map);
    }

    /**
     * Writes the map to the provided output stream.
     * @param stream the stream the map will be written to
     * @throws MapWriterException if the writing operation fails
     */
    public void write(OutputStream stream) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(map);

        try {
            stream.write(json.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            throw new MapWriterException(e);
        }
    }

    public static class MapWriterException extends RuntimeException {
        public MapWriterException(Throwable cause) {
            super(cause);
        }
    }
}
