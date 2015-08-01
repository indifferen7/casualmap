package se.cs.casualmap.api.map;

import com.google.common.base.Optional;
import org.junit.Test;
import se.cs.casualmap.api.shared.Direction;
import se.cs.casualmap.api.shared.Tile;

import java.util.Collection;
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

        Optional<Area> area = map.getArea(Tile.at(0, 0));
        assertTrue(area.isPresent());
        assertEquals(0, area.get().getId());

        area = map.getArea(Tile.at(42, 42));
        assertFalse(area.isPresent());
    }

    @Test
    public void getPassagesByArea() {
        Map map = newMap();

        Area area = new Area(1, createRectangle(Tile.at(2, 0), 2, 2));
        Collection<Passage> passages = map.getPassages(area);

        assertEquals(2, passages.size());
    }

    @Test
    public void getPassagesByAreaInDirection() {
        Map map = newMap();

        Area area = new Area(1, createRectangle(Tile.at(2, 0), 2, 2));
        Collection<Passage> passages = map.getPassages(area, Direction.DOWN);

        // there is one passage downwards..
        assertEquals(1, passages.size());

        passages = map.getPassages(area, Direction.LEFT);

        // ..and another one to the left
        assertEquals(1, passages.size());
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

        areas.add(new Area(0, createRectangle(Tile.at(0, 0), 2, 2)));
        areas.add(new Area(1, createRectangle(Tile.at(2, 0), 2, 2)));
        areas.add(new Area(2, createRectangle(Tile.at(1, 2), 2, 2)));

        Set<Passage> passages = new HashSet<>();
        passages.add(new Passage(0, Tile.at(1, 0), Tile.at(2, 0)));
        passages.add(new Passage(1, Tile.at(2, 1), Tile.at(2, 2)));

        return new Map(areas, passages, 4, 4);
    }

    private Set<Tile> createRectangle(Tile topLeft, int width, int height) {
        Set<Tile> tiles = new HashSet<Tile>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.add(Tile.at(topLeft.getX() + x, topLeft.getY() + y));
            }
        }

        return tiles;
    }
}