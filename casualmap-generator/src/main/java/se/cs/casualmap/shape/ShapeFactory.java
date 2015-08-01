package se.cs.casualmap.shape;

import se.cs.casualmap.api.shared.Tile;

/**
 * Implementors of this contract must be able to construct
 * {@link Shape}s based on the provided arguments.
 * @param <T> the shape type.
 */
public interface ShapeFactory<T extends Shape> {

    /**
     * Creates a {@link Shape} of the generic type.
     * @param topLeft The top left tile of the rectangular space in which the shape
     *                should be constructed.
     * @param width The width of the rectangular space in which to construct the shape.
     * @param height The height of the rectangular space in which to construct the shape.
     * @return the newly constructed shape.
     */
    T create(Tile topLeft, int width, int height);
}
