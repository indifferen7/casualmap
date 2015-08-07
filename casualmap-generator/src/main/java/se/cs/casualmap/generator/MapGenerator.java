package se.cs.casualmap.generator;

import se.cs.casualmap.shape.*;
import se.cs.casualmap.api.map.Area;
import se.cs.casualmap.api.map.Map;
import se.cs.casualmap.api.map.Passage;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class generates {@link Map} instances based on the enclosed {@link Args}.
 */
public class MapGenerator {
    private final Args args;
    private final ShapeFactory<? extends Shape> shapeFactory;

    private MapGenerator(Args args,
                        ShapeFactory<? extends Shape> shapeFactory) {
        this.args = args;
        this.shapeFactory = shapeFactory;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private Map generate() {
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

    public static class Builder {
        private Collection<ShapeFactory<? extends Shape>> factories = new ArrayList<>();
        private Args args;

        private Builder() {}

        public Builder withShapeFactory(ShapeFactory<? extends Shape> factory) {
            checkNotNull(factory);
            checkArgument(!factories.contains(factory));

            factories.add(factory);

            return this;
        }

        public Builder withArgs(Args args) {
            this.args = checkNotNull(args);

            return this;
        }

        public Map generate() {
            List<ShapeFactory<? extends Shape>> theFactories = new ArrayList<>();

            if (this.factories.isEmpty()) {
                theFactories.add(new Rectangle.Factory());
                theFactories.add(new Circle.Factory());
            } else {
                theFactories.addAll(factories);
            }

            Args theArgs = args != null
                    ? args
                    : Args.newBuilder().build();

            return new MapGenerator(theArgs,
                    new RandomShapeFactory(theFactories)).generate();
        }
    }
}
