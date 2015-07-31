package se.cs.casualmap.generator;

import se.cs.casualmap.shape.Shape;
import se.cs.casualmap.model.shared.Tile;
import se.cs.casualmap.model.shared.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides suggestions of {@link Shape}s that can be
 * placed next to the subject, in any {@link Direction}.
 */
public class LineUpSuggestor {

    private final Shape subject;

    private LineUpSuggestor(Shape subject) {
        this.subject = subject;
    }

    public static LineUpSuggestor newForShape(Shape subject) {
        return new LineUpSuggestor(subject);
    }

    public List<Shape> suggest(Shape shape) {

        List<Shape> result = new ArrayList<Shape>();

        for (Direction direction : Direction.scramble()) {
            result.addAll(suggestInDirection(shape, direction));
        }

        return result;
    }

    private List<Shape> suggestInDirection(Shape shape, Direction direction) {
        List<Shape> result = new ArrayList<Shape>();

        for (Tile subjectBorderTile : subject.getTiles(direction)) {
            Tile neighbourTile = subjectBorderTile.relativeTo(direction);

            for (Tile tile : shape.copy(neighbourTile).getTiles(direction.inverse())) {
                Tile newTopLeft = neighbourTile
                        .relativeTo(Direction.LEFT, tile.getX() - neighbourTile.getX())
                        .relativeTo(Direction.UP, tile.getY() - neighbourTile.getY());

                Shape suggestion = shape.copy(newTopLeft);

                if (!result.contains(suggestion)) {
                    result.add(shape.copy(newTopLeft));
                }
            }
        }

        return result;
    }
}
