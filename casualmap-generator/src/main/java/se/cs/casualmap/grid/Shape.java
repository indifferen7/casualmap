package se.cs.casualmap.grid;

import se.cs.casualmap.model.shared.Direction;
import se.cs.casualmap.model.shared.Tile;

import java.util.Set;

public interface Shape {
    Shape copy(Tile topLeft);
    Set<Tile> getTiles();
    Set<Tile> getTiles(Direction direction);
}
