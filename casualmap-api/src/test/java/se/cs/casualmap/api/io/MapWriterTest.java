package se.cs.casualmap.api.io;

import org.junit.Test;
import se.cs.casualmap.api.TestObjectFactory;

import java.io.IOException;

public class MapWriterTest {

    @Test
    public void write() throws IOException {
        MapWriter.forMap(TestObjectFactory.newMap()).write(System.out);
    }
}