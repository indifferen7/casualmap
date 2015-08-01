package se.cs.casualmap.api.map;

import se.cs.casualmap.api.shared.Tile;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Passages define at which tiles {@link Area}s can be traversed. The two tiles,
 * tileOne and tileTwo, captures the location in the map where the passage
 * exists. Although the variable names might suggest some kind of priority, the
 * traversal direction is not a concern of this class. The passage might be one
 * or bidirectional, blocked by a giant or whatever makes sense in whatever the
 * map is used for. Such details should be described in another construct. For
 * these types of scenarios, the passage id might come in handy. Similar to
 * {@link Area}s, passage identities are only unique within the context of its map.
 */
public class Passage {
    private final int id;
    private final Tile tileOne;
    private final Tile tileTwo;

    public Passage(int id, Tile tileOne, Tile tileTwo) {
        this.id = id;
        this.tileOne = checkNotNull(tileOne);
        this.tileTwo = checkNotNull(tileTwo);
    }

    public int getId() {
        return id;
    }

    public Tile tileOne() {
        return tileOne;
    }

    public Tile tileTwo() {
        return tileTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passage passage = (Passage) o;

        return id == passage.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "id=" + id +
                ", tileOne=" + tileOne +
                ", tileTwo=" + tileTwo +
                '}';
    }
}
