package se.cs.casualmap.persistence;

import org.junit.Test;
import se.cs.casualmap.api.map.Map;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static se.cs.casualmap.TestObjectFactory.newMap;

public class MapRepositoryFSTest {

    @Test
    public void persistMapToFS() {
        Map map1 = newMap();
        Map map2 = null;

        MapRepositoryFS repo = new MapRepositoryFS();
        repo.save(map1);
        try {
            map2 = repo.fetch();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(map1, map2);
        repo.cleanUp();
    }
}
