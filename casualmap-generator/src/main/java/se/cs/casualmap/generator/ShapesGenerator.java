package se.cs.casualmap.generator;

import se.cs.casualmap.grid.*;
import se.cs.casualmap.model.shared.Tile;

import java.util.*;

public class ShapesGenerator {
    private final MapGeneratorArgs args;
    private final Random random = new Random();
    private final ShapeFactory<?> shapeFactory;

    private int maxAttempts = 2000;

    ShapesGenerator(MapGeneratorArgs args, ShapeFactory<? extends Shape> shapeFactory) {
        this.args = args;
        this.shapeFactory = shapeFactory;
    }

    Collection<Shape> generate() {
        int attempts = 0;

        Grid grid = new Grid(args.getWidth(), args.getHeight());
        grid.place(newShape());

        while (!hasFailedTooManyTimes(attempts)
                && !tileDensityThresholdReached(grid)) {

            int areaWidth = args.getMinAreaWidth() + random.nextInt(args.getMaxAreaWidth() - args.getMinAreaWidth()) + 1;
            int areaHeight = args.getMinAreaHeight() + random.nextInt(args.getMaxAreaHeight() - args.getMinAreaHeight()) + 1;

            Shape shape = shapeFactory.create(new Tile(0, 0), areaWidth, areaHeight);

            List<Shape> suggestions = LineUpSuggestor.newForShape(grid.anyShape()).suggest(shape);

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
        int areaWidth = args.getMinAreaWidth() + random.nextInt(args.getMaxAreaWidth() - args.getMinAreaWidth()) + 1;
        int areaHeight = args.getMinAreaHeight() + random.nextInt(args.getMaxAreaHeight() - args.getMinAreaHeight()) + 1;

        Tile topLeft = new Tile(
                random.nextInt(args.getWidth() - areaWidth),
                random.nextInt(args.getHeight() - areaHeight));

        return shapeFactory.create(topLeft, areaWidth, areaHeight);
    }
}
