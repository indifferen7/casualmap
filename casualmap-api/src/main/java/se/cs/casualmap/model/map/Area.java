package se.cs.casualmap.model.map;

import se.cs.casualmap.model.shared.Direction;
import se.cs.casualmap.model.shared.Tile;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Area {
    private final int id;
    private final Set<Tile> tiles;

    public Area(int id, Set<Tile> tiles) {
        this.id = id;
        this.tiles = tiles;
    }

    public int getId() {
        return id;
    }

    public Set<Tile> getTiles() {
        return Collections.unmodifiableSet(tiles);
    }

    public Set<Tile> getTiles(Direction direction) {
        Set<Tile> result = new HashSet<Tile>();

        for (Tile tile : tiles) {
            if (!tiles.contains(tile.relativeTo(direction))) {
                result.add(tile);
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area room = (Area) o;

        return id == room.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", tiles=" + tiles +
                '}';
    }
}
