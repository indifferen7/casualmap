package se.cs.casualmap.model.map;

import org.junit.Test;
import se.cs.casualmap.model.shared.Direction;
import se.cs.casualmap.model.shared.Tile;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AreaTest {

    @Test
    public void getTilesInDirection(){
        Set<Tile> tiles = new HashSet<>();

        tiles.add(new Tile(1, 1));
        tiles.add(new Tile(1, 2));
        tiles.add(new Tile(2, 1));
        tiles.add(new Tile(2, 2));

        Area area = new Area(0, tiles);

        Set<Tile> result = area.getTiles(Direction.LEFT);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Tile(1, 1)));
        assertTrue(result.contains(new Tile(1, 2)));

        result = area.getTiles(Direction.DOWN);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Tile(1, 2)));
        assertTrue(result.contains(new Tile(2, 2)));

        result = area.getTiles(Direction.RIGHT);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Tile(2, 1)));
        assertTrue(result.contains(new Tile(2, 2)));

        result = area.getTiles(Direction.UP);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Tile(1, 1)));
        assertTrue(result.contains(new Tile(2, 1)));
    }
}