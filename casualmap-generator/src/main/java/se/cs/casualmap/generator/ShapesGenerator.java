package se.cs.casualmap.generator;

import se.cs.casualmap.api.shared.Tile;
import se.cs.casualmap.shape.Shape;
import se.cs.casualmap.shape.ShapeFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class generates lined up {@link Shape}s based on the provided {@link Args}.
 * The creation of shapes are handled by the enclosed shapeFactory, making the shape form
 * unknown for this algorithm. The only thing this class cares about is that the shapes are
 * well formed, i.e. they form a filled region.
 */
public class ShapesGenerator {
    private final Args args;
    private final Random random = new Random();
    private final ShapeFactory<?> shapeFactory;

    private int maxAttempts = 3000;

    ShapesGenerator(Args args, ShapeFactory<? extends Shape> shapeFactory) {
        this.args = args;
        this.shapeFactory = shapeFactory;
    }

    /**
     * @return a collection of lined up shapes
     */
    Collection<Shape> generate() {
        int attempts = 0;

        Grid grid = new Grid(args.getWidth(), args.getHeight());
        while (!hasFailedTooManyTimes(attempts)) {
            Shape first = newShape();

            if (grid.fits(first)) {
                attempts = 0;
                grid.place(first);
                break;
            } else {
                attempts++;
            }
        }

        while (!hasFailedTooManyTimes(attempts)
                && !tileDensityThresholdReached(grid)) {

            Shape shape = shapeFactory.create(
                    Tile.at(0, 0),
                    randomAreaWidth(),
                    randomAreaHeight());

            List<Shape> suggestions =
                    LineUpSuggestor.newForShape(grid.anyShape()).suggest(shape);

            Collections.shuffle(suggestions);

            for (Shape suggestion : suggestions) {
                if (grid.fits(suggestion)) {
                    grid.place(suggestion);
                    attempts = 0;
                    break;
                } else {
                    attempts++;
                }
            }
        }

        // todo: think about whether or not to enforce this rule..
        /*
        checkArgument(!hasFailedTooManyTimes(attempts),
                "Could not generate map after %s attempts, please check your arguments.", attempts);
        */

        return grid.getShapes();
    }

    private boolean tileDensityThresholdReached(Grid grid) {
        double currentAreaDensity =
                (double) grid.getNumberOfOccupiedTiles() / (double) (args.getWidth() * args.getHeight());

        return  currentAreaDensity >= args.tileDensityThreshold();
    }

    private boolean hasFailedTooManyTimes(int attempts) {
        return attempts > maxAttempts;
    }

    private Shape newShape() {
        return shapeFactory.create(
                Tile.at(
                        random.nextInt(args.getWidth()),
                        random.nextInt(args.getHeight())),
                randomAreaWidth(),
                randomAreaHeight());
    }

    private int randomAreaWidth() {
        int widthModifier = 0;
        if (args.getMaxAreaWidth() - args.getMinAreaWidth() > 0) {
            widthModifier = random.nextInt(args.getMaxAreaWidth() - args.getMinAreaWidth()) + 1;
        }

        return args.getMinAreaWidth() + widthModifier;
    }

    private int randomAreaHeight() {
        int heightModifier = 0;
        if (args.getMaxAreaHeight() - args.getMinAreaHeight() > 0) {
            heightModifier = random.nextInt(args.getMaxAreaHeight() - args.getMinAreaHeight()) + 1;
        }

        return args.getMinAreaHeight() + heightModifier;
    }
}
