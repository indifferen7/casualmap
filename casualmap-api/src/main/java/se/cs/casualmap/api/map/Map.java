package se.cs.casualmap.api.map;

import se.cs.casualmap.api.shared.Direction;
import se.cs.casualmap.api.shared.Tile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class functions as a container for all {@link Area}s and {@link Passage}s
 * in a tile map. It provides all kinds of handy functions you might need.
 */
public class Map {
    private final Set<Area> areas;
    private final Set<Passage> passages;

    private final int width;
    private final int height;
    private double tileDensity;

    public Map(Set<Area> areas,
               Set<Passage> passages,
               int mapWidth,
               int mapHeight) {

        this.areas = checkNotNull(areas);
        this.passages = checkNotNull(passages);
        checkArgument(mapWidth > 1, "Map width too low: %s", mapWidth);
        checkArgument(mapHeight > 1, "Map height too low: %s", mapHeight);

        this.width = mapWidth;
        this.height = mapHeight;

        setTileDensity();
    }

    private void setTileDensity() {
        double occupiedTiles = 0;
        for (Area area : areas) {
            occupiedTiles += area.allTiles().size();
        }

        tileDensity =
                new BigDecimal(occupiedTiles / (double) (width * height))
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
    }

    /**
     * @return all areas in the map
     */
    public Collection<Area> allAreas() {
        return Collections.unmodifiableCollection(areas);
    }

    /**
     * @param id the area id
     * @return an optional wrapper with the result of the query
     */
    public Optional<Area> areaWithId(int id) {
        for (Area area : areas) {
            if (area.getId() == id) {
                return Optional.of(area);
            }
        }

        return Optional.empty();
    }

    /**
     * @param tile a tile that should exist within the sought area
     * @return an optional wrapper with the result of the query
     */
    public Optional<Area> areaWithTile(Tile tile) {
        for (Area area : areas) {
            if (area.allTiles().contains(tile)) {
                return Optional.of(area);
            }
        }
        return Optional.empty();
    }

    /**
     * @return all passages in the map
     */
    public Collection<Passage> allPassages() {
        return Collections.unmodifiableCollection(passages);
    }

    /**
     * @param id the passage id
     * @return an optional wrapper with the result of the query
     */
    public Optional<Passage> passageWithId(int id) {
        for (Passage passage : passages) {
            if (passage.getId() == id) {
                return Optional.of(passage);
            }
        }

        return Optional.empty();
    }

    /**
     * @param area the area whose passages we seek
     * @return all passages in with the provided area
     */
    public Collection<Passage> passagesAt(Area area) {
        Collection<Passage> result = new ArrayList<>();

        for (Passage passage : passages) {
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

    /**
     * @param area the area whose passages we seek
     * @param direction the direction the passages should face
     * @return all passages in with the provided area facing the provided direction
     */
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

    /**
     * @return the map width (in tiles)
     */
    public int width() {
        return width;
    }

    /**
     * @return the map height (in tiles)
     */
    public int height() {
        return height;
    }

    /**
     * @return the tile density. The tile density is represented by
     * a number between 0 and 1. 0 means that there are not tiles at
     * all in areas, while 1 means that all tiles are in areas. Likely,
     * the number is somewhere between 0 and 1.
     */
    public double tileDensity() {
        return tileDensity;
    }

    @Override
    public String toString() {
        return "Map{" +
                "width=" + width +
                ", height=" + height +
                ", tileDensity=" + tileDensity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return Objects.equals(width, map.width) &&
                Objects.equals(height, map.height) &&
                Objects.equals(areas, map.areas) &&
                Objects.equals(passages, map.passages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areas, passages, width, height);
    }
}