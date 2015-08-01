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

            for (Tile tile : area.getTiles()) {
                this.areaByTile.put(tile, area);
            }
        }

        for (Passage passage : passages) {
            this.passages.put(passage.getId(), passage);
        }

        this.width = mapWidth;
        this.height = mapHeight;
    }

    public Collection<Area> getAreas() {
        return areaById.values();
    }

    public Optional<Area> getArea(int id) {
        return Optional.fromNullable(areaById.get(id));
    }

    public Optional<Area> getArea(Tile tile) {
        return Optional.fromNullable(areaByTile.get(tile));
    }

    public Collection<Passage> getPassages() {
        return passages.values();
    }

    public Optional<Passage> getPassage(int id) {
        return Optional.fromNullable(passages.get(id));
    }

    public Collection<Passage> getPassages(Area area) {
        Collection<Passage> result = new ArrayList<>();

        for (Passage passage : passages.values()) {
            Optional<Area> candidateA = getArea(passage.getTileAreaA());
            Optional<Area> candidateB = getArea(passage.getTileAreaB());

            if (candidateA.isPresent() && candidateA.get().equals(area)) {
                result.add(passage);
            }
            else if (candidateB.isPresent() && candidateB.get().equals(area)) {
                result.add(passage);
            }
        }

        return result;
    }

    public Collection<Passage> getPassages(Area area, Direction direction) {
        Collection<Passage> result = new ArrayList<>();

        for (Passage passage : getPassages(area)) {
            Tile thisAreaTile = getArea(passage.getTileAreaA()).get().equals(area)
                    ? passage.getTileAreaA()
                    : passage.getTileAreaB();

            Tile otherAreaTile = passage.getTileAreaA().equals(thisAreaTile)
                    ? passage.getTileAreaB()
                    : passage.getTileAreaA();

            if (thisAreaTile.relativeTo(direction).equals(otherAreaTile)) {
                result.add(passage);
            }
        }

        return result;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}