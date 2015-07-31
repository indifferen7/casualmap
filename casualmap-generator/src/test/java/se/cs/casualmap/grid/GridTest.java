package se.cs.casualmap.grid;

import org.junit.Test;
import se.cs.casualmap.model.shared.Tile;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by indifferent on 7/29/15.
 */
public class GridTest {

    @Test
    public void place() {
        Grid grid = new Grid(400, 400);

        Shape result = grid.place(new Rectangle(new Tile(4, 4), 2, 2));

        assertEquals(4, result.getTiles().size());
        assertTrue(result.getTiles().contains(new Tile(4, 4)));
        assertTrue(result.getTiles().contains(new Tile(4, 5)));
        assertTrue(result.getTiles().contains(new Tile(5, 4)));
        assertTrue(result.getTiles().contains(new Tile(5, 5)));
    }

    @Test
    public void placeWithTranslate() {
        Grid grid = new Grid(400, 400);

        grid.translate(new Tile(4, 4));

        Shape result = grid.place(new Rectangle(new Tile(0, 0), 2, 2));

        // grid had been translated to (4, 4)..
        assertEquals(4, result.getTiles().size());
        assertTrue(result.getTiles().contains(new Tile(4, 4)));
        assertTrue(result.getTiles().contains(new Tile(4, 5)));
        assertTrue(result.getTiles().contains(new Tile(5, 4)));
        assertTrue(result.getTiles().contains(new Tile(5, 5)));
    }

    @Test
    public void resetTranslation() {
        Grid grid = new Grid(400, 400);

        grid.translate(new Tile(4, 4));
        grid.translateReset();

        Shape result = grid.place(new Rectangle(new Tile(4, 4), 2, 2));

        // coordinates should begin on (4, 4) instead of (8, 8) as
        // we have reset the translation
        assertEquals(4, result.getTiles().size());
        assertTrue(result.getTiles().contains(new Tile(4, 4)));
        assertTrue(result.getTiles().contains(new Tile(4, 5)));
        assertTrue(result.getTiles().contains(new Tile(5, 4)));
        assertTrue(result.getTiles().contains(new Tile(5, 5)));
    }

    @Test
    public void placeWithTranslateFromCurrent() {
        Grid grid = new Grid(400, 400);

        // grid is translated to (4, 4)
        grid.translate(new Tile(4, 4));

        // grid is translated to (8, 8)
        grid.translateFromCurrent(new Tile(4, 4));

        Shape result = grid.place(new Rectangle(new Tile(0, 0), 2, 2));

        assertEquals(4, result.getTiles().size());
        assertTrue(result.getTiles().contains(new Tile(8, 8)));
        assertTrue(result.getTiles().contains(new Tile(8, 9)));
        assertTrue(result.getTiles().contains(new Tile(9, 8)));
        assertTrue(result.getTiles().contains(new Tile(9, 9)));
    }

    @Test
    public void fitsShape() {
        Grid grid = new Grid(400, 400);

        grid.translate(new Tile(4, 4));
        Shape shape = new Rectangle(new Tile(0, 0), 2, 2);

        assertTrue(grid.fits(shape));
    }

    @Test
    public void doesNotFitShape() {
        Grid grid = new Grid(400, 400);

        grid.translate(new Tile(4, 4));
        Shape shape = new Rectangle(new Tile(0, 0), 2, 2);
        grid.place(shape);

        // the tiles have now been occupied so the grid should not fit the same shape again
        assertFalse(grid.fits(shape));
    }

    @Test
    public void outOfGridBounds() {
        Grid grid = new Grid(400, 400);

        grid.translate(new Tile(400, 400));
        Shape shape = new Rectangle(new Tile(0, 0), 2, 2);

        assertFalse(grid.fits(shape));
    }

    @Test
    public void getShapes() {
        Grid grid = new Grid(400, 400);

        Shape shape = grid.place(new Rectangle(new Tile(4, 4), 2, 2));

        Collection<Shape> result = grid.getShapes();

        assertEquals(1, result.size());
        assertEquals(shape, result.iterator().next());
    }
}