package se.cs.casualmap.shape;

import se.cs.casualmap.model.shared.Direction;
import se.cs.casualmap.model.shared.Tile;

import java.util.Set;

/**
 * This contract represents a filled shape made up by {@link Tile}s. The filled
 * part is important - all tiles must be connected to form a filled shape, e.g.
 * a filled circle, rectangle or diamond.
 */
public interface Shape {
    /**
     * Copies the current shape to the location described by the provided top left tile.
     * @param topLeft The top left tile of the copied shape.
     * @return The copied shape.
     */
    Shape copy(Tile topLeft);

    /**
     * @return all tiles in the shape.
     */
    Set<Tile> getTiles();

    /**
     * Returns all tiles facing the provided direction, i.e., all tiles that makes up
     * the edge of the shape in the provided direction.
     * @param direction The direction we're interested in.
     * @return the tiles facing the provided direction.
     */
    Set<Tile> getTiles(Direction direction);
}
