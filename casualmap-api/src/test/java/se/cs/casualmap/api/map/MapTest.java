package se.cs.casualmap.api.map;

import com.google.common.base.Optional;
import org.junit.Test;
import se.cs.casualmap.api.shared.Tile;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MapTest {

    @Test
    public void getAreaById() {
        Map map = newMap();

        Optional<Area> area = map.getArea(1);

        assertTrue(area.isPresent());
        assertEquals(1, area.get().getId());

        area = map.getArea(42);
        assertFalse(area.isPresent());
    }

    @Test
    public void getPassageById() {
        Map map = newMap();

        Optional<Passage> passage = map.getPassage(1);

        assertTrue(passage.isPresent());
        assertEquals(1, passage.get().getId());

        passage = map.getPassage(42);
        assertFalse(passage.isPresent());
    }

    @Test
    public void getAreaByTile() {
        Map map = newMap();

        Optional<Area> area = map.getArea(new Tile(0, 0));
        assertTrue(area.isPresent());
        assertEquals(0, area.get().getId());

        area = map.getArea(new Tile(42, 42));
        assertFalse(area.isPresent());
    }

    private Map newMap() {
        /**
         * Like so:
         * AABB
         * AABB
         *  CC
         *  CC
         */
        Set<Area> areas = new HashSet<>();

        areas.add(new Area(0, createRectangle(new Tile(0, 0), 2, 2)));
        areas.add(new Area(1, createRectangle(new Tile(2, 0), 2, 2)));
        areas.add(new Area(2, createRectangle(new Tile(1, 2), 2, 2)));

        Set<Passage> passages = new HashSet<>();
        passages.add(new Passage(0, new Tile(1, 0), new Tile(2, 0)));
        passages.add(new Passage(1, new Tile(2, 1), new Tile(2, 2)));

        return new Map(areas, passages, 4, 4);
    }

    private Set<Tile> createRectangle(Tile topLeft, int width, int height) {
        Set<Tile> tiles = new HashSet<Tile>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.add(new Tile(topLeft.getX() + x, topLeft.getY() + y));
            }
        }

        return tiles;
    }
}