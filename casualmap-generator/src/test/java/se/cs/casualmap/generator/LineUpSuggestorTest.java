package se.cs.casualmap.generator;

import org.junit.Test;
import se.cs.casualmap.grid.Rectangle;
import se.cs.casualmap.grid.Shape;
import se.cs.casualmap.grid.ShapeFactory;
import se.cs.casualmap.model.shared.Tile;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LineUpSuggestorTest {

    @Test
    public void suggest() {
        ShapeFactory<Rectangle> factory = new Rectangle.Factory();

        // let's make things simple..
        Shape subject = factory.create(new Tile(2, 2), 2, 2);

        Shape shape = factory.create(new Tile(0, 0), 2, 2);

        List<Shape> result = LineUpSuggestor.newForShape(subject).suggest(shape);

        assertEquals(12, result.size());
        assertTrue(result.contains(factory.create(new Tile(0, 1), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(0, 2), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(0, 3), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(1, 0), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(1, 4), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(2, 0), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(2, 4), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(3, 0), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(3, 4), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(4, 1), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(4, 2), 2, 2)));
        assertTrue(result.contains(factory.create(new Tile(4, 3), 2, 2)));
    }
}