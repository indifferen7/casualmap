package se.cs.casualmap.ui.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.cs.casualmap.generator.MapGenerator;
import se.cs.casualmap.generator.MapGeneratorArgs;
import se.cs.casualmap.grid.*;
import se.cs.casualmap.model.map.Passage;
import se.cs.casualmap.model.shared.Tile;
import se.cs.casualmap.model.map.Map;
import se.cs.casualmap.model.map.Area;
import se.cs.casualmap.model.shared.Direction;

import java.util.ArrayList;
import java.util.List;

public class Sample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sample map generator");

        Text title = new Text();
        title.setText("Sample map generator");

        Button btn = new Button();
        btn.setText("Generate");
        btn.setLayoutX(10);
        btn.setLayoutY(10);

        final Text info = new Text();
        title.setText("Sample map generator");

        final Canvas canvas = new Canvas(400, 400);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                List<ShapeFactory<? extends Shape>> factories = new ArrayList<>();
                factories.add(new Rectangle.Factory());
                factories.add(new Circle.Factory());

                MapGenerator mapGenerator = new MapGenerator(new MapGeneratorArgs(), new RandomShapeFactory(factories));
                Map map = mapGenerator.generate();

                draw(map, canvas, info);
            }
        });

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: lightgray");

        root.getChildren().add(title);
        StackPane.setAlignment(title, Pos.TOP_LEFT);
        StackPane.setMargin(title, new Insets(10, 10, 10, 10));

        root.getChildren().add(btn);
        StackPane.setAlignment(btn, Pos.TOP_LEFT);
        StackPane.setMargin(btn, new Insets(30, 10, 10, 10));

        root.getChildren().add(info);
        StackPane.setAlignment(info, Pos.TOP_LEFT);
        StackPane.setMargin(info, new Insets(60, 10, 10, 10));

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    private void draw(Map map, Canvas canvas, final Text info) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        final double widthRatio = canvas.getWidth() / map.getWidth();
        final double heightRatio = canvas.getHeight() / map.getHeight();

        gc.setFill(Color.BLACK);
        canvas.addEventHandler(MouseEvent.MOUSE_MOVED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        int x = new Double(mouseEvent.getX() / widthRatio).intValue();
                        int y = new Double(mouseEvent.getY() / heightRatio).intValue();

                        info.setText("Hovering tile: " + x + ", " + y);
                    }
                });

        for (Area area : map.getAreas()) {
            gc.setFill(Color.LIGHTGREEN);
            gc.setStroke(Color.GREY);
            gc.setLineWidth(.5);

            for (Tile tile : area.getTiles()) {
                gc.fillRect(tile.getX() * widthRatio,
                        tile.getY() * heightRatio,
                        widthRatio,
                        heightRatio);

                gc.strokeRect(tile.getX() * widthRatio,
                        tile.getY() * heightRatio,
                        widthRatio,
                        heightRatio);
            }

            gc.setLineWidth(1);
            gc.setStroke(Color.DARKGREEN);
            for (Direction direction : Direction.scramble()) {
                for (Tile tile : area.getTiles(direction)) {
                    double fromX = direction.equals(Direction.RIGHT)
                            ? tile.getX() * widthRatio + widthRatio
                            : tile.getX() * widthRatio;

                    double fromY = direction.equals(Direction.DOWN)
                            ? tile.getY() * heightRatio + heightRatio
                            : tile.getY() * heightRatio;

                    double toX = direction.equals(Direction.UP) || direction.equals(Direction.DOWN)
                            ? fromX + widthRatio
                            : fromX;

                    double toY = direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)
                            ? fromY + heightRatio
                            : fromY;

                    gc.strokeLine(fromX, fromY, toX, toY);
                }
            }
        }

        gc.setFill(Color.BROWN);
        for (Passage passage : map.getPassages()) {
            gc.fillRect(passage.getTileAreaA().getX() * widthRatio,
                    passage.getTileAreaA().getY() * heightRatio,
                    widthRatio,
                    heightRatio);

            gc.fillRect(passage.getTileAreaB().getX() * widthRatio,
                    passage.getTileAreaB().getY() * heightRatio,
                    widthRatio,
                    heightRatio);
        }
    }
}
