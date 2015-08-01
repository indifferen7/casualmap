package se.cs.casualmap.generator;

import org.junit.Test;
import se.cs.casualmap.shape.Rectangle;
import se.cs.casualmap.shape.Shape;
import se.cs.casualmap.api.shared.Tile;
import se.cs.casualmap.shape.ShapeFactory;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by indifferent on 7/29/15.
 */
public class GridTest {

    private ShapeFactory<Rectangle> factory = new Rectangle.Factory();

    @Test
    public void place() {
        Grid grid = new Grid(400, 400);

        Shape result = grid.place(factory.create(Tile.at(4, 4), 2, 2));

        assertEquals(4, result.getTiles().size());
        assertTrue(result.getTiles().contains(Tile.at(4, 4)));
        assertTrue(result.getTiles().contains(Tile.at(4, 5)));
        assertTrue(result.getTiles().contains(Tile.at(5, 4)));
        assertTrue(result.getTiles().contains(Tile.at(5, 5)));
    }

    @Test
    public void placeWithTranslate() {
        Grid grid = new Grid(400, 400);

        grid.translate(Tile.at(4, 4));

        Shape result = grid.place(factory.create(Tile.at(0, 0), 2, 2));

        // grid had been translated to (4, 4)..
        assertEquals(4, result.getTiles().size());
        assertTrue(result.getTiles().contains(Tile.at(4, 4)));
        assertTrue(result.getTiles().contains(Tile.at(4, 5)));
        assertTrue(result.getTiles().contains(Tile.at(5, 4)));
        assertTrue(result.getTiles().contains(Tile.at(5, 5)));
    }

    @Test
    public void resetTranslation() {
        Grid grid = new Grid(400, 400);

        grid.translate(Tile.at(4, 4));
        grid.translateReset();

        Shape result = grid.place(factory.create(Tile.at(4, 4), 2, 2));

        // coordinates should begin on (4, 4) instead of (8, 8) as
        // we have reset the translation
        assertEquals(4, result.getTiles().size());
        assertTrue(result.getTiles().contains(Tile.at(4, 4)));
        assertTrue(result.getTiles().contains(Tile.at(4, 5)));
        assertTrue(result.getTiles().contains(Tile.at(5, 4)));
        assertTrue(result.getTiles().contains(Tile.at(5, 5)));
    }

    @Test
    public void placeWithTranslateFromCurrent() {
        Grid grid = new Grid(400, 400);

        // grid is translated to (4, 4)
        grid.translate(Tile.at(4, 4));

        // grid is translated to (8, 8)
        grid.translateFromCurrent(Tile.at(4, 4));

        Shape result = grid.place(factory.create(Tile.at(0, 0), 2, 2));

        assertEquals(4, result.getTiles().size());
        assertTrue(result.getTiles().contains(Tile.at(8, 8)));
        assertTrue(result.getTiles().contains(Tile.at(8, 9)));
        assertTrue(result.getTiles().contains(Tile.at(9, 8)));
        assertTrue(result.getTiles().contains(Tile.at(9, 9)));
    }

    @Test
    public void fitsShape() {
        Grid grid = new Grid(400, 400);

        grid.translate(Tile.at(4, 4));
        Shape shape = factory.create(Tile.at(0, 0), 2, 2);

        assertTrue(grid.fits(shape));
    }

    @Test
    public void doesNotFitShape() {
        Grid grid = new Grid(400, 400);

        grid.translate(Tile.at(4, 4));
        Shape shape = factory.create(Tile.at(0, 0), 2, 2);
        grid.place(shape);

        // the tiles have now been occupied so the grid should not fit the same shape again
        assertFalse(grid.fits(shape));
    }

    @Test
    public void outOfGridBounds() {
        Grid grid = new Grid(400, 400);

        grid.translate(Tile.at(400, 400));
        Shape shape = factory.create(Tile.at(0, 0), 2, 2);

        assertFalse(grid.fits(shape));
    }

    @Test
    public void getShapes() {
        Grid grid = new Grid(400, 400);

        Shape shape = grid.place(factory.create(Tile.at(4, 4), 2, 2));

        Collection<Shape> result = grid.getShapes();

        assertEquals(1, result.size());
        assertEquals(shape, result.iterator().next());
    }
}