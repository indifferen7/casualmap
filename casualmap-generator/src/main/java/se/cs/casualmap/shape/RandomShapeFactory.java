package se.cs.casualmap.shape;

import se.cs.casualmap.api.shared.Tile;

import java.util.List;
import java.util.Random;

/**
 * Delegates the creation of shapes randomly to the enclosed {@link ShapeFactory}
 * instances.
 */
public class RandomShapeFactory implements ShapeFactory<Shape> {

    private List<ShapeFactory<? extends Shape>> factories;
    private Random random = new Random();

    public RandomShapeFactory(List<ShapeFactory<? extends Shape>> factories) {
        this.factories = factories;
    }

    @Override
    public Shape create(Tile topLeft, int width, int height) {
        ShapeFactory<? extends Shape> factory = factories.size() > 1
                ? factories.get(random.nextInt(factories.size()))
                : factories.get(0);

        return factory.create(topLeft, width,height);
    }
}
