package se.cs.casualmap.persistence;

import org.junit.Test;

import java.io.IOException;

import static se.cs.casualmap.TestObjectFactory.newMap;

public class MapSerializerTest {

    @Test
    public void output() throws IOException {
        MapSerializer.target(newMap()).output(System.out);
    }
}