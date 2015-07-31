package se.cs.casualmap.generator;

import se.cs.casualmap.shape.Shape;
import se.cs.casualmap.model.shared.Tile;

import java.util.*;

public class Grid {
    private final int width;
    private final int height;

    private final Random random = new Random();

    private Tile translation;
    private List<Shape> shapes = new ArrayList<Shape>();
    private Set<Tile> occupied = new HashSet<Tile>();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void translate(Tile tile) {
        this.translation = tile;
    }

    public void translateFromCurrent(Tile tile) {
        this.translation = translation != null
                ? translation.add(tile)
                : tile;
    }

    public void translateReset() {
        this.translation = null;
    }

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

    public boolean fits(Shape shape) {
        Set<Tile> tiles = shouldTranslate()
                ? shape.copy(translation).getTiles()
                : shape.getTiles();

        return isWithinGridBounds(tiles) && !overlap(tiles);
    }

    public Collection<Shape> getShapes() {
        return Collections.unmodifiableCollection(shapes);
    }

    public int getNumberOfOccupiedTiles() {
        return occupied.size();
    }

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
