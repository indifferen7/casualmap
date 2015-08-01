package se.cs.casualmap.shape;

import se.cs.casualmap.api.shared.Direction;
import se.cs.casualmap.api.shared.Tile;

import java.util.HashSet;
import java.util.Set;

/**
 * A rectangular shape.
 */
public class Rectangle implements Shape {

    private Tile topLeft;
    private int width;
    private int height;

    Rectangle(Tile topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    public Shape copy(Tile topLeft) {
        return new Rectangle(topLeft, width, height);
    }

    public Set<Tile> getTiles() {
        Set<Tile> tiles = new HashSet<Tile>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.add(new Tile(topLeft.getX() + x, topLeft.getY() + y));
            }
        }

        return tiles;
    }

    public Set<Tile> getTiles(Direction direction) {
        Set<Tile> tiles = new HashSet<Tile>();

        if (direction.equals(Direction.LEFT)) {
            for (int y = 0; y < height; y++) {
                tiles.add(new Tile(topLeft.getX(), topLeft.getY() + y));
            }
        }
        else if (direction.equals(Direction.DOWN)) {
            for (int x = 0; x < width; x++) {
                tiles.add(new Tile(topLeft.getX() + x, topLeft.getY() + height - 1));
            }
        }
        else if (direction.equals(Direction.RIGHT)) {
            for (int y = 0; y < height; y++) {
                tiles.add(new Tile(topLeft.getX() + width - 1, topLeft.getY() + y));
            }
        }
        else {
            for (int x = 0; x < width; x++) {
                tiles.add(new Tile(topLeft.getX() + x, topLeft.getY()));
            }
        }

        return tiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (width != rectangle.width) return false;
        if (height != rectangle.height) return false;
        return !(topLeft != null ? !topLeft.equals(rectangle.topLeft) : rectangle.topLeft != null);

    }

    @Override
    public int hashCode() {
        int result = topLeft != null ? topLeft.hashCode() : 0;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "topLeft=" + topLeft +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public static class Factory implements ShapeFactory<Rectangle> {

        @Override
        public Rectangle create(Tile topLeft, int width, int height) {
            return new Rectangle(topLeft, width, height);
        }
    }
}
