package se.cs.casualmap.shape;

import se.cs.casualmap.model.shared.Tile;

public interface ShapeFactory<T extends Shape> {
    T create(Tile topLeft, int width, int height);
}
