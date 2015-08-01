package se.cs.casualmap.generator;

import se.cs.casualmap.model.shared.Tile;

/**
 * This class represents a connection between two {@link se.cs.casualmap.shape.Shape}s
 * in a {@link Grid} via the two contained {@link Tile}s. Its constructor is package
 * private as it is not relevant outside the map generation context.
 */
public class Connection {
    private final Tile tile1;
    private final Tile tile2;

    Connection(Tile tile1, Tile tile2) {
        this.tile1 = tile1;
        this.tile2 = tile2;
    }

    public Tile getTile1() {
        return tile1;
    }

    public Tile getTile2() {
        return tile2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Connection connection = (Connection) o;

        if (tile1 != null ? !tile1.equals(connection.tile1) : connection.tile1 != null) return false;
        return !(tile2 != null ? !tile2.equals(connection.tile2) : connection.tile2 != null);

    }

    @Override
    public int hashCode() {
        int result = tile1 != null ? tile1.hashCode() : 0;
        result = 31 * result + (tile2 != null ? tile2.hashCode() : 0);
        return result;
    }
}
