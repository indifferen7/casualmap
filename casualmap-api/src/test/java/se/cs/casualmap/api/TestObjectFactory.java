package se.cs.casualmap.api;

import se.cs.casualmap.api.map.Area;
import se.cs.casualmap.api.map.Map;
import se.cs.casualmap.api.map.Passage;
import se.cs.casualmap.api.shared.Tile;

import java.util.HashSet;
import java.util.Set;

public class TestObjectFactory {

    public static Map newMap() {
        /**
         * Like so:
         * AABB
         * AABB
         *  CC
         *  CC
         */
        Set<Area> areas = new HashSet<>();

        areas.add(new Area(0, createRectangle(Tile.at(0, 0), 2, 2)));
        areas.add(new Area(1, createRectangle(Tile.at(2, 0), 2, 2)));
        areas.add(new Area(2, createRectangle(Tile.at(1, 2), 2, 2)));

        Set<Passage> passages = new HashSet<>();
        passages.add(new Passage(0, Tile.at(1, 0), Tile.at(2, 0)));
        passages.add(new Passage(1, Tile.at(2, 1), Tile.at(2, 2)));

        return new Map(areas, passages, 4, 4);
    }

    private static Set<Tile> createRectangle(Tile topLeft, int width, int height) {
        Set<Tile> tiles = new HashSet<Tile>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.add(Tile.at(topLeft.getX() + x, topLeft.getY() + y));
            }
        }

        return tiles;
    }
}
