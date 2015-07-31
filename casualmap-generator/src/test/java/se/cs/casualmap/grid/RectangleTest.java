package se.cs.casualmap.grid;

import org.junit.Test;
import se.cs.casualmap.model.shared.Direction;
import se.cs.casualmap.model.shared.Tile;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by indifferent on 7/29/15.
 */
public class RectangleTest {

    @Test
    public void getTilesInDirection() {
        Rectangle rect = new Rectangle(new Tile(0, 0), 2, 2);

        Set<Tile> result = rect.getTiles(Direction.LEFT);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Tile(0, 0)));
        assertTrue(result.contains(new Tile(0, 1)));

        result = rect.getTiles(Direction.DOWN);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Tile(0, 1)));
        assertTrue(result.contains(new Tile(1, 1)));

        result = rect.getTiles(Direction.RIGHT);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Tile(1, 0)));
        assertTrue(result.contains(new Tile(1, 1)));

        result = rect.getTiles(Direction.UP);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Tile(0, 0)));
        assertTrue(result.contains(new Tile(1, 0)));
    }
}