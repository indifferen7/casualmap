package se.cs.casualmap.generator;

import org.junit.Test;
import se.cs.casualmap.shape.*;
import se.cs.casualmap.model.map.Map;

import java.util.ArrayList;
import java.util.List;

public class MapGeneratorTest {

    @Test
    public void testGenerate() {
        List<ShapeFactory<? extends Shape>> factories = new ArrayList<>();
        factories.add(new Rectangle.Factory());
        factories.add(new Circle.Factory());

        MapGenerator mapGenerator = new MapGenerator(new MapGeneratorArgs(), new RandomShapeFactory(factories));

        Map result = mapGenerator.generate();

        System.out.println(result);
    }

    private static MapGeneratorArgs sampleArgs() {
        return new MapGeneratorArgs();
    }
}