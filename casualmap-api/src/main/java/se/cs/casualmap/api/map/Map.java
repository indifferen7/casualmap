package se.cs.casualmap.api.map;

import com.google.common.base.Optional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Map {
    private final java.util.Map<Integer, Area> areas = new HashMap<>();
    private final java.util.Map<Integer, Passage> passages = new HashMap<>();
    private final int width;
    private final int height;

    public Map(Set<Area> areas,
               Set<Passage> passages,
               int mapWidth,
               int mapHeight) {

        for (Area area : areas) {
            this.areas.put(area.getId(), area);
        }

        for (Passage passage : passages) {
            this.passages.put(passage.getId(), passage);
        }

        this.width = mapWidth;
        this.height = mapHeight;
    }

    public Collection<Area> getAreas() {
        return areas.values();
    }

    public Optional<Area> getArea(int id) {
        return Optional.fromNullable(areas.get(id));
    }

    public Collection<Passage> getPassages() {
        return passages.values();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}