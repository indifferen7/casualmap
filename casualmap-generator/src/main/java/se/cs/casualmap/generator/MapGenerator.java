package se.cs.casualmap.generator;

import se.cs.casualmap.grid.Shape;
import se.cs.casualmap.grid.ShapeFactory;
import se.cs.casualmap.model.map.Area;
import se.cs.casualmap.model.map.Map;
import se.cs.casualmap.model.map.Passage;
import se.cs.casualmap.model.shared.Tile;

import java.util.*;

/**
 * This class generates {@link Map} instances based on the enclosed {@link MapGeneratorArgs}.
 */
public class MapGenerator {
    private final MapGeneratorArgs args;
    private final ShapeFactory<? extends Shape> shapeFactory;

    public MapGenerator(MapGeneratorArgs args,
                        ShapeFactory<? extends Shape> shapeFactory) {
        this.args = args;
        this.shapeFactory = shapeFactory;
    }

    public Map generate() {
        Collection<Shape> shapes = new ShapesGenerator(args, shapeFactory).generate();
        Set<Connection> connections = new ConnectionGenerator(shapes).generate();

        return new Map(createAreas(new ArrayList<>(shapes)), createPassages(connections), args.getWidth(), args.getHeight());
    }

    private Set<Passage> createPassages(Set<Connection> connections) {
        Set<Passage> passages = new HashSet<>();

        int counter = 0;
        for (Connection connection : connections) {
            passages.add(new Passage(counter++, connection.getTile1(), connection.getTile2()));
        }

        return passages;
    }

    private Set<Area> createAreas(List<Shape> shapes) {
        Set<Area> result = new HashSet<>();

        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            result.add(new Area(i, shape.getTiles()));
        }

        return result;
    }
}
