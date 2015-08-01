package se.cs.casualmap.api.map;

import org.junit.Test;
import se.cs.casualmap.api.shared.Direction;
import se.cs.casualmap.api.shared.Tile;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AreaTest {

    @Test
    public void getTilesInDirection(){
        Set<Tile> tiles = new HashSet<>();

        tiles.add(Tile.at(1, 1));
        tiles.add(Tile.at(1, 2));
        tiles.add(Tile.at(2, 1));
        tiles.add(Tile.at(2, 2));

        Area area = new Area(0, tiles);

        Set<Tile> result = area.getTiles(Direction.LEFT);

        assertEquals(2, result.size());
        assertTrue(result.contains(Tile.at(1, 1)));
        assertTrue(result.contains(Tile.at(1, 2)));

        result = area.getTiles(Direction.DOWN);

        assertEquals(2, result.size());
        assertTrue(result.contains(Tile.at(1, 2)));
        assertTrue(result.contains(Tile.at(2, 2)));

        result = area.getTiles(Direction.RIGHT);

        assertEquals(2, result.size());
        assertTrue(result.contains(Tile.at(2, 1)));
        assertTrue(result.contains(Tile.at(2, 2)));

        result = area.getTiles(Direction.UP);

        assertEquals(2, result.size());
        assertTrue(result.contains(Tile.at(1, 1)));
        assertTrue(result.contains(Tile.at(2, 1)));
    }
}