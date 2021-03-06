package se.cs.casualmap.console;

import org.apache.commons.cli.*;
import se.cs.casualmap.api.io.MapFileWriter;
import se.cs.casualmap.api.io.MapWriter;
import se.cs.casualmap.api.map.Map;
import se.cs.casualmap.generator.Args;
import se.cs.casualmap.generator.MapGenerator;
import se.cs.casualmap.shape.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleApplication {

    public static void main(String[] args) throws ParseException, IOException {
        Options options = new Options();
        options.addOption("file", true, "output map to file");
        options.addOption("size", true, "size of map in format width[,height]. If the latter is omitted both will be set to the provided value.");
        options.addOption("areaWidth", true, "the width of areas in the map in format min[,max]. If the latter is omitted both will be set to the provided value.");
        options.addOption("areaHeight", true, "the height of areas in the map in format min[,max]. If the latter is omitted both will be set to the provided value.");
        options.addOption("shapes", true, "the shapes that the map should contain in format shape1[,shape2]. Supported shape values as of today are RECTANGLE and CIRCLE.");
        options.addOption("density", true, "the desired tile density. Expects a value between 0 and 1.");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            Map map = generate(cmd);

            if (cmd.hasOption("file") && cmd.getOptionValue("file") != null) {
                MapFileWriter.forMap(map).write(new File(cmd.getOptionValue("file")));
            } else {
                MapWriter.forMap(map).write(System.out);
            }
        } catch (Exception e) {
            System.out.println("Oops: " + e.getMessage());
        }
    }

    private static Map generate(CommandLine cmd) {

        Args.Builder argsBuilder = Args.newBuilder();

        Optional<String[]> size = getRangeValue(cmd, "size");
        if (size.isPresent()) {
            if (size.get().length > 1) {
                argsBuilder
                        .width(Integer.parseInt(size.get()[0]))
                        .height(Integer.parseInt(size.get()[1]));
            } else {
                argsBuilder
                        .width(Integer.parseInt(size.get()[0]))
                        .height(Integer.parseInt(size.get()[0]));
            }
        }

        Optional<String[]> areaWidth = getRangeValue(cmd, "areaWidth");
        if (areaWidth.isPresent()) {
            if (areaWidth.get().length > 1) {
                argsBuilder
                        .minAreaWidth(Integer.parseInt(areaWidth.get()[0]))
                        .maxAreaWidth(Integer.parseInt(areaWidth.get()[1]));
            } else {
                argsBuilder
                        .minAreaWidth(Integer.parseInt(areaWidth.get()[0]))
                        .maxAreaWidth(Integer.parseInt(areaWidth.get()[0]));
            }
        }

        Optional<String[]> areaHeight = getRangeValue(cmd, "areaHeight");
        if (areaHeight.isPresent()) {
            if (areaHeight.get().length > 1) {
                argsBuilder
                        .minAreaHeight(Integer.parseInt(areaHeight.get()[0]))
                        .maxAreaHeight(Integer.parseInt(areaHeight.get()[1]));
            } else {
                argsBuilder
                        .minAreaHeight(Integer.parseInt(areaHeight.get()[0]))
                        .maxAreaHeight(Integer.parseInt(areaHeight.get()[0]));
            }
        }

        if (cmd.hasOption("density") && cmd.getOptionValue("density") != null) {
            argsBuilder.desiredTileDensity(Double.parseDouble(cmd.getOptionValue("density")));
        }

        MapGenerator.Builder mapBuilder =
                MapGenerator.newBuilder()
                        .withArgs(argsBuilder.build());

        Optional<String[]> shapes = getRangeValue(cmd, "shapes");
        if (shapes.isPresent()) {
            List<ShapeFactory<? extends Shape>> factories = new ArrayList<>();

            // this part could definitely be improved, e.g. with an enum
            for (String shape : shapes.get()) {
                if (shape.toUpperCase().equals("RECTANGLE")) {
                    factories.add(new Rectangle.Factory());
                }
                else if (shape.toUpperCase().equals("CIRCLE")) {
                    factories.add(new Circle.Factory());
                }
            }

            if (!factories.isEmpty()) {
                mapBuilder.withShapeFactory(new RandomShapeFactory(factories));
            }
        }

        return mapBuilder.generate();
    }

    private static Optional<String[]> getRangeValue(CommandLine cmd, String option) {
        if (!cmd.hasOption(option)) {
            return Optional.empty();
        }

        if (cmd.getOptionValue(option) == null) {
            return Optional.empty();
        }

        String value = cmd.getOptionValue(option);

        return value.contains(",")
                ? Optional.of(value.split(","))
                : Optional.of(new String[] { value });
    }
}
