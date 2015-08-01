package se.cs.casualmap.generator;

import se.cs.casualmap.shape.Shape;
import se.cs.casualmap.model.shared.Direction;
import se.cs.casualmap.model.shared.Tile;

import java.util.*;

/**
 * The purpose of this class is to generate {@link Connection}s between
 * the {@link Shape}s provided via the constructor so that all shapes
 * can be reached by traversing connections, i.e. one big region is formed.
 */
public class ConnectionGenerator {

    private final Collection<Shape> shapes;
    private final Map<Tile, Shape> shapeByTile = new HashMap<>();
    private final Random random = new Random();

    ConnectionGenerator(Collection<Shape> shapes) {
        this.shapes = shapes;

        for (Shape shape : shapes) {
            for (Tile tile : shape.getTiles()) {
                shapeByTile.put(tile, shape);
            }
        }
    }

    /**
     * Generates connections between the enclosed {@link Shape}s.
     */
    Set<Connection> generate() {
        Set<Connection> result = new HashSet<>();

        Set<Shape> withConnections = new HashSet<>();
        for (Shape shape : shapes) {
            if (withConnections.contains(shape)) {
                continue;
            }

            Connection connection = generateConnectionFrom(shape, withConnections);

            if (connection != null) {
                result.add(connection);
                withConnections.add(shape);
            }
        }

        while (true) {
            Set<Shape> region = getShapesConnectedWith(shapes.iterator().next(), result);

            if (region.size() == shapes.size())
                break;

            result.add(addConnectionFromRegion(region));
        }

        return result;
    }

    private Connection generateConnectionFrom(Shape shape, final Set<Shape> withConnections) {
        List<Shape> neighbours = new ArrayList<>();

        for (Shape s : neighbours(shape)) {
            if (!withConnections.contains(s)) {
                neighbours.add(s);
            }
        }

        if (neighbours.isEmpty()) {
            return null;
        }

        return createConnectionBetween(shape,
                neighbours.get(neighbours.size() > 1
                        ? random.nextInt(neighbours.size() - 1)
                        : 0));
    }

    private List<Shape> neighbours(Shape shapes) {
        List<Shape> result = new ArrayList<>();

        for (Direction direction : Direction.scramble()) {
            for (Tile borderTile : shapes.getTiles(direction)) {
                Shape neighbour = getShapes(borderTile.relativeTo(direction));

                if (neighbour != null && !result.contains(neighbour)) {
                    result.add(neighbour);
                }
            }
        }

        return result;
    }

    private Shape getShapes(Tile tile) {
        for (Shape shape : shapes) {
            if (shape.getTiles().contains(tile)) {
                return shape;
            }
        }

        return null;
    }

    private Connection createConnectionBetween(Shape one, Shape two) {

        List<Connection> connections = new ArrayList<>();

        for (Direction direction : Direction.scramble()) {
            for (Tile oneBorderTile : one.getTiles(direction)) {
                Tile twoTileCandidate = oneBorderTile.relativeTo(direction);

                if (two.getTiles().contains(twoTileCandidate)) {
                    Connection connection = new Connection(oneBorderTile, twoTileCandidate);

                    if (!connections.contains(connection)) {
                        connections.add(connection);
                    }
                }
            }
        }

        if (connections.isEmpty()) {
            throw new RuntimeException(String.format("Cannot copy passage between %s and %s", one, two));
        }

        return connections.get(connections.size() > 1
                ? random.nextInt(connections.size() - 1)
                : 0);
    }


    private Set<Shape> getShapesConnectedWith(Shape shape, Collection<Connection> connections) {
        Set<Shape> region = new HashSet<>();
        getConnectedShapesRecursively(shape, connections, region);
        return region;
    }

    private Set<Shape> getConnectedShapesRecursively(Shape shape, Collection<Connection> connections, Set<Shape> result) {

        for (Connection connection : connections) {
            Shape neighbour = null;
            if (shape.getTiles().contains(connection.getTile1())) {
                neighbour = shapeByTile.get(connection.getTile2());
            }
            else if (shape.getTiles().contains(connection.getTile2())) {
                neighbour = shapeByTile.get(connection.getTile1());
            }

            if (neighbour != null && !result.contains(neighbour)) {
                result.add(neighbour);
                result.addAll(getConnectedShapesRecursively(neighbour, connections, result));

            }
        }

        return result;
    }

    private Connection addConnectionFromRegion(Set<Shape> region) {
        for (Shape shape : region) {
            for (Direction direction : Direction.scramble()) {
                Set<Tile> border = shape.getTiles(direction);

                for (Tile tile : border) {
                    Shape other = shapeByTile.get(tile.relativeTo(direction));
                    if (other != null && !region.contains(other)) {
                        return new Connection(tile, tile.relativeTo(direction));
                    }
                }
            }
        }

        throw new RuntimeException("Could not copy a connection.");
    }
}
