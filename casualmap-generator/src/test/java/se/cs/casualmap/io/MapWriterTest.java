package se.cs.casualmap.io;

import org.junit.Test;

import java.io.IOException;

import static se.cs.casualmap.TestObjectFactory.newMap;

public class MapWriterTest {

    @Test
    public void write() throws IOException {
        MapWriter.forMap(newMap()).write(System.out);
    }
}