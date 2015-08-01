package se.cs.casualmap.generator;

import se.cs.casualmap.shape.Shape;
import se.cs.casualmap.api.shared.Tile;

import java.util.*;

/**
 * This class represents a grid, i.e. a rectangular space with coordinates in
 * which shapes can be placed. Shapes cannot be stacked on top of each other, and any
 * attempt to do so will render in an exception. Luckily the method fits() can be called
 * to assure that no exception will be thrown.
 *
 * The class api has been made public as it's quite cool and might be useful somehow. And
 * that seems like a sound reason to publish its api, right? ;)
 */
public class Grid {
    private final int width;
    private final int height;

    private final Random random = new Random();

    private Tile translation;
    private List<Shape> shapes = new ArrayList<>();
    private Set<Tile> occupied = new HashSet<>();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Translates the grid, i.e. "moves" the grid coordinate system to the
     * specified tile.
     * @param tile The translated top left tile.
     */
    public void translate(Tile tile) {
        this.translation = tile;
    }

    /**
     * Translates the grid from the current translation, i.e. "moves" the grid
     * coordinate system from the current translation - if any - to the specified
     * tile.
     * @param tile The translated top left tile.
     */
    public void translateFromCurrent(Tile tile) {
        this.translation = translation != null
                ? translation.add(tile)
                : tile;
    }

    /**
     * Resets the translation.
     */
    public void translateReset() {
        this.translation = null;
    }

    /**
     * Places the shape in the grid. Assumes that you have made your homework and
     * made certain that no overlapping will occur.
     * @param shape The shape to place.
     * @throws IllegalArgumentException If the shape overlaps an existing one.
     * @return The newly placed shape.
     */
    public Shape place(Shape shape) {
        if (!this.fits(shape)) {
            throw new IllegalArgumentException("Cannot place shape " + shape);
        }

        Shape shapeToAdd = shouldTranslate()
                ? shape.copy(translation)
                : shape;

        occupied.addAll(shapeToAdd.getTiles());
        shapes.add(shapeToAdd);

        return shapeToAdd;
    }

    /**
     * Checks if the shape provided as argument overlap an existing shape
     * in the grid
     * @param shape The shape to check.
     * @return true if okay, false if there will be an overlapping situation.
     */
    public boolean fits(Shape shape) {
        Set<Tile> tiles = shouldTranslate()
                ? shape.copy(translation).getTiles()
                : shape.getTiles();

        return isWithinGridBounds(tiles) && !overlap(tiles);
    }

    /**
     * @return all shapes in the grid.
     */
    public Collection<Shape> getShapes() {
        return Collections.unmodifiableCollection(shapes);
    }

    /**
     * @return the number of occupied tiles in the grid.
     */
    public int getNumberOfOccupiedTiles() {
        return occupied.size();
    }

    /**
     * @return any shape in the grid.
     */
    public Shape anyShape() {
        return shapes.get(shapes.size() == 1
                ? 0
                : random.nextInt(shapes.size() - 1));
    }

    private boolean shouldTranslate() {
        return translation != null;
    }

    private boolean overlap(Set<Tile> tiles) {
        for (Tile tile : tiles) {
            if (occupied.contains(tile)) {
                return true;
            }
        }

        return false;
    }

    private boolean isWithinGridBounds(Set<Tile> tiles) {
        for (Tile tile : tiles) {
            if (!isWithinGridBounds(tile))
                return false;
        }
        return true;
    }

    private boolean isWithinGridBounds(Tile tile) {
        return tile.getX() >= 0
                && tile.getX() < width
                && tile.getY() >= 0
                && tile.getY() < height;
    }
}
