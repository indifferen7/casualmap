package se.cs.casualmap.generator;

import se.cs.casualmap.api.shared.Tile;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class represents a connection between two {@link se.cs.casualmap.shape.Shape}s
 * in a {@link Grid} via the two contained {@link Tile}s. Its constructor is package
 * private as it is not relevant outside the map generation context.
 */
public class Connection {
    private final Tile tile1;
    private final Tile tile2;

    Connection(Tile tile1, Tile tile2) {
        this.tile1 = checkNotNull(tile1);
        this.tile2 = checkNotNull(tile2);
        checkArgument(!tile1.equals(tile2), "Reflexive connections make no sense.");
    }

    Tile getTile1() {
        return tile1;
    }

    Tile getTile2() {
        return tile2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Connection connection = (Connection) o;

        if (tile1.equals(connection.getTile1()) && tile2.equals(connection.getTile2())) {
            return true;
        }
        else if (tile1.equals(connection.getTile2()) && tile2.equals(connection.getTile1())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = tile1 != null ? tile1.hashCode() : 0;
        result = 31 * result + (tile2 != null ? tile2.hashCode() : 0);
        return result;
    }
}
