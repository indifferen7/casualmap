package se.cs.casualmap.api.map;

import com.google.common.base.Optional;
import se.cs.casualmap.api.shared.Direction;
import se.cs.casualmap.api.shared.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Map {
    private final java.util.Map<Integer, Area> areaById = new HashMap<>();
    private final java.util.Map<Tile, Area> areaByTile = new HashMap<>();
    private final java.util.Map<Integer, Passage> passages = new HashMap<>();

    private final int width;
    private final int height;

    public Map(Set<Area> areaByTile,
               Set<Passage> passages,
               int mapWidth,
               int mapHeight) {

        for (Area area : areaByTile) {
            this.areaById.put(area.getId(), area);

            for (Tile tile : area.allTiles()) {
                this.areaByTile.put(tile, area);
            }
        }

        for (Passage passage : passages) {
            this.passages.put(passage.getId(), passage);
        }

        this.width = mapWidth;
        this.height = mapHeight;
    }

    public Collection<Area> allAreas() {
        return areaById.values();
    }

    public Optional<Area> areaWithId(int id) {
        return Optional.fromNullable(areaById.get(id));
    }

    public Optional<Area> areaWithTile(Tile tile) {
        return Optional.fromNullable(areaByTile.get(tile));
    }

    public Collection<Passage> allPassages() {
        return passages.values();
    }

    public Optional<Passage> passageWithId(int id) {
        return Optional.fromNullable(passages.get(id));
    }

    public Collection<Passage> passagesAt(Area area) {
        Collection<Passage> result = new ArrayList<>();

        for (Passage passage : passages.values()) {
            Optional<Area> candidateA = areaWithTile(passage.tileOne());
            Optional<Area> candidateB = areaWithTile(passage.tileTwo());

            if (candidateA.isPresent() && candidateA.get().equals(area)) {
                result.add(passage);
            }
            else if (candidateB.isPresent() && candidateB.get().equals(area)) {
                result.add(passage);
            }
        }

        return result;
    }

    public Collection<Passage> passagesAt(Area area, Direction direction) {
        Collection<Passage> result = new ArrayList<>();

        for (Passage passage : passagesAt(area)) {
            Tile thisAreaTile = areaWithTile(passage.tileOne()).get().equals(area)
                    ? passage.tileOne()
                    : passage.tileTwo();

            Tile otherAreaTile = passage.tileOne().equals(thisAreaTile)
                    ? passage.tileTwo()
                    : passage.tileOne();

            if (thisAreaTile.relativeTo(direction).equals(otherAreaTile)) {
                result.add(passage);
            }
        }

        return result;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    @Override
    public String toString() {
        return "Map{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}