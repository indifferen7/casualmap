package se.cs.casualmap.console;

import org.apache.commons.cli.*;
import se.cs.casualmap.api.io.MapFileWriter;
import se.cs.casualmap.api.io.MapWriter;
import se.cs.casualmap.api.map.Map;
import se.cs.casualmap.generator.MapGenerator;
import se.cs.casualmap.generator.MapGeneratorArgs;
import se.cs.casualmap.shape.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by indifferent on 8/6/15.
 */
public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        Options options = new Options();
        options.addOption("f", true, "output map to file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        Map map = generate();
        if (cmd.hasOption("f") && cmd.getOptionValue("f") != null) {
            MapFileWriter.forMap(map).write(new File(cmd.getOptionValue("f")));
        } else {
            MapWriter.forMap(map).write(System.out);
        }
    }

    /**
     * The map generation should be a bit more flexible..
     * @return a generated map
     */
    private static Map generate() {
        List<ShapeFactory<? extends Shape>> factories = new ArrayList<>();
        factories.add(new Rectangle.Factory());
        factories.add(new Circle.Factory());

        MapGenerator mapGenerator = new MapGenerator(new MapGeneratorArgs.Builder().build(), new RandomShapeFactory(factories));

        return mapGenerator.generate();
    }
}
