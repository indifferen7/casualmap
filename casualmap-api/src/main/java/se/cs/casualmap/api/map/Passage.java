package se.cs.casualmap.api.map;

import se.cs.casualmap.api.shared.Tile;

public class Passage {
    private final int id;
    private final Tile tileOne;
    private final Tile tileTwo;

    public Passage(int id, Tile tileOne, Tile tileTwo) {
        this.id = id;
        this.tileOne = tileOne;
        this.tileTwo = tileTwo;
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
