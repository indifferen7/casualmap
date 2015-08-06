package se.cs.casualmap.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import se.cs.casualmap.api.io.MapReader;
import se.cs.casualmap.api.map.Map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static se.cs.casualmap.TestObjectFactory.newMap;

public class MapFileWriterTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void write() throws IOException {
        File file = folder.newFile();

        Map map1 = newMap();
        Map map2 = null;

        MapFileWriter writer = MapFileWriter.forMap(map1);
        writer.write(file);

        try {
            map2 = new MapReader().read(file.toPath());
        } catch (MapReader.MapReaderException e) {
            e.printStackTrace();
        }

        assertEquals(map1, map2);
    }
}
