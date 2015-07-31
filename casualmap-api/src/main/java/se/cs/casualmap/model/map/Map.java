package se.cs.casualmap.model.map;

import java.util.Set;

public class Map {
    private final Set<Area> areas;
    private final Set<Passage> passages;
    private final int width;
    private final int height;

    public Map(Set<Area> areas,
               Set<Passage> passages,
               int mapWidth,
               int mapHeight) {
        this.areas = areas;
        this.passages = passages;
        this.width = mapWidth;
        this.height = mapHeight;
    }

    public Set<Area> getAreas() {
        return areas;
    }

    public Set<Passage> getPassages() {
        return passages;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}