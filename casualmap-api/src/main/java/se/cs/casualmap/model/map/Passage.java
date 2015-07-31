package se.cs.casualmap.model.map;

import se.cs.casualmap.model.shared.Tile;

public class Passage {
    private final int id;
    private final Tile tileAreaA;
    private final Tile tileAreaB;

    public Passage(int id, Tile tileAreaA, Tile tileAreaB) {
        this.id = id;
        this.tileAreaA = tileAreaA;
        this.tileAreaB = tileAreaB;
    }

    public int getId() {
        return id;
    }

    public Tile getTileAreaA() {
        return tileAreaA;
    }

    public Tile getTileAreaB() {
        return tileAreaB;
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
}
