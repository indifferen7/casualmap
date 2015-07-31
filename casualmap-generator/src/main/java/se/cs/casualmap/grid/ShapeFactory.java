package se.cs.casualmap.grid;

import se.cs.casualmap.model.shared.Tile;

public interface ShapeFactory<T extends Shape> {
    T create(Tile topLeft, int width, int height);
}
