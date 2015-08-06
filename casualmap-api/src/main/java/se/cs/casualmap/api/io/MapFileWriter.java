package se.cs.casualmap.api.io;

import se.cs.casualmap.api.map.Map;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A map writer that writes to a file.
 */
public class MapFileWriter {

    private final Map map;

    private MapFileWriter(Map map) {
        this.map = checkNotNull(map);
    }

    /**
     * Creates a new instance of the map writer. Takes
     * the map that is to be written as reference.
     * @param map the map
     * @return a new fresh map writer
     */
    public static MapFileWriter forMap(Map map) {
        return new MapFileWriter(map);
    }

    /**
     * Writes the map to the provided file.
     * @param file the file the map will be written to
     * @throws MapWriter.MapWriterException if the writing operation fails
     */
    public void write(File file)  {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            MapWriter.forMap(map).write(fos);
        } catch (IOException e) {
            throw new MapWriter.MapWriterException(e);
        }
    }
}









