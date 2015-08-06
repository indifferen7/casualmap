package se.cs.casualmap.api.io;

import se.cs.casualmap.api.map.Map;

import java.io.File;
import java.io.FileOutputStream;

import static com.google.common.base.Preconditions.checkNotNull;

public class MapFileWriter {

    private final Map map;

    private MapFileWriter(Map map) {
        this.map = checkNotNull(map);
    }

    public static MapFileWriter forMap(Map map) {
        return new MapFileWriter(map);
    }

    public void write(File file)  {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            MapWriter.forMap(map).write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









