package se.cs.casualmap.api.map;

import se.cs.casualmap.api.shared.Direction;
import se.cs.casualmap.api.shared.Tile;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An area is a uniquely identified and filled space in a tile map,
 * e.g. a rectangle, circle or perhaps a diamond. Its identity is
 * unique only within the context of a map.
 */
public class Area {
    private final int id;
    private final Set<Tile> tiles;

    public Area(int id, Set<Tile> tiles) {
        checkArgument(!tiles.isEmpty(), "Areas without tiles does not make sense.");

        this.id = id;
        this.tiles = checkNotNull(tiles);
    }

    /**
     * @return the area id
     */
    public int getId() {
        return id;
    }

    /**
     * @return all tiles in the present area
     */
    public Set<Tile> allTiles() {
        return Collections.unmodifiableSet(tiles);
    }

    /**
     * Returns all tiles that makes up a border in the specified
     * direction. For a 2*2 square positioned in the top left corner
     * of the map (i.e., coordinate (0,0)), a call to this method
     * with direction set to RIGHT would result in the tiles
     * (1,0) and (1,1).
     *
     * @param direction the border direction
     * @return all border tiles in the specified direction
     */
    public Set<Tile> tilesIn(Direction direction) {
        Set<Tile> result = new HashSet<>();

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
