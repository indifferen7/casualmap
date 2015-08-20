package se.cs.casualmap.generator;

import org.junit.Test;
import se.cs.casualmap.api.shared.Tile;

import static org.junit.Assert.assertEquals;

public class ConnectionTest {

    @Test(expected = NullPointerException.class)
    public void tile1IsNull() {
        new Connection(null, Tile.at(0, 0));
    }

    @Test(expected = NullPointerException.class)
    public void tile2IsNull() {
        new Connection(Tile.at(0, 0), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void reflexiveConnection() {
        new Connection(Tile.at(0, 0), Tile.at(0, 0));
    }

    @Test
    public void equalsTest() {
        Connection connectionA = new Connection(Tile.at(0, 0), Tile.at(0, 1));
        Connection connectionB = new Connection(Tile.at(0, 1), Tile.at(0, 0));
        Connection connectionC = new Connection(Tile.at(0, 1), Tile.at(0, 0));

        assertEquals(connectionA, connectionB);
        assertEquals(connectionB, connectionC);
    }
}