package se.cs.casualmap.shape;

import se.cs.casualmap.api.shared.Direction;
import se.cs.casualmap.api.shared.Tile;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A circular shape.
 */
public class Circle implements Shape {

    private Tile topLeft;
    private int width;
    private int height;
    private Set<Tile> tiles;

    private Circle(Tile topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;

        generateTiles();
    }

    @Override
    public Shape copy(Tile topLeft) {
        return new Circle(topLeft, width, height);
    }

    @Override
    public Set<Tile> getTiles() {
        return Collections.unmodifiableSet(tiles);
    }

    @Override
    public Set<Tile> getTiles(Direction direction) {
        Set<Tile> result = new HashSet<>();

        for (Tile tile : tiles) {
            if (!tiles.contains(tile.relativeTo(direction))) {
                result.add(tile);
            }
        }

        return result;
    }

    /**
     * Super inspired by http://rosettacode.org/wiki/Bitmap/Midpoint_circle_algorithm#Java
     */
    private void generateTiles() {
        final int centerX = topLeft.getX() + (width / 2);
        final int centerY = topLeft.getY() + (height / 2);
        final int radius = width / 2;

        int d = (5 - radius * 4)/4;
        int x = 0;
        int y = radius;

        Set<Tile> tiles = new HashSet<>();
        do {
            tiles.addAll(generateTileRow(Tile.at(centerX - y, centerY + x), Tile.at(centerX + y, centerY + x)));
            tiles.addAll(generateTileRow(Tile.at(centerX - x, centerY + y), Tile.at(centerX + x, centerY + y)));
            tiles.addAll(generateTileRow(Tile.at(centerX - x, centerY - y), Tile.at(centerX + x, centerY - y)));
            tiles.addAll(generateTileRow(Tile.at(centerX - y, centerY - x), Tile.at(centerX + y, centerY - x)));

            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } while (x <= y);

        this.tiles = tiles;
    }

    private Set<Tile> generateTileRow(Tile from, Tile to) {
        Set<Tile> result = new HashSet<>();
        for (int x = from.getX(); x <= to.getX(); x++) {
            result.add(Tile.at(x, from.getY()));
        }

        return result;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "topLeft=" + topLeft +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public static class Factory implements ShapeFactory<Circle> {

        @Override
        public Circle create(Tile topLeft, int width, int height) {
            return new Circle(topLeft, width, height);
        }
    }
}
