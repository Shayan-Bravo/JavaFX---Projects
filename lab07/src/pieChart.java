import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

public class pieChart extends Application {
    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};
    private static String[] types =  {"Flash Flood","Severe Thunderstorm","Special Marine","Tornado"};
    private static int[] amount = {0,0,0,0};

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 800, 500);
        final Canvas canvas = new Canvas(300,300);
        GraphicsContext gc2 = canvas.getGraphicsContext2D();
        Legend circleLegend = new Legend("Pie Chart");
        circleLegend.setAlignment(Pos.CENTER);
        root.getStylesheets().add(getClass().getResource("charts.css").toExternalForm());

        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader("test.csv"));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (data[5].equals("FLASH FLOOD")){
                amount[0]+=1;
            } else if (data[5].equals("SEVERE THUNDERSTORM")){
                amount[1]+=1;
            } else if (data[5].equals("TORNADO")){
                amount[2]+=1;
            } else if (data[5].equals("SPECIAL MARINE")){
                amount[3]+=1;
            }

        }
        csvReader.close();

        double checkPoint = 0.00;
        double  sum = 0;
        for (int j =0; j < amount.length; j++) {
            sum += amount[j];
        }
        for (int k =0; k < types.length; k++) {
            gc2.setFill(pieColours[k]);
            gc2.fillArc(0, 0, 200, 200, checkPoint, 1*(360/sum)*amount[k], ArcType.ROUND);
            checkPoint += 1*(360/sum)*amount[k];
        }

        root.setHgap(10);
        root.setVgap(10);


        root.setConstraints(canvas, 1, 0);
        root.setConstraints(circleLegend, 1, 1);

        root.getChildren().addAll(canvas,circleLegend);

        stage.setScene(scene);
        stage.setTitle("lab07");
        stage.show();
    }

    class Legend extends GridPane {

        public Legend(String type) {

            addRow(0, createSymbol("-fx-aqua"), new Label(types[0]));
            addRow(1, createSymbol("-fx-gold"), new Label(types[1]));
            addRow(2, createSymbol("-fx-darkorange"), new Label(types[2]));
            addRow(3, createSymbol("-fx-darksalmon"), new Label(types[3]));

        }

        private Node createSymbol(String fillStyle) {
            Shape symbol = new Circle(10);
            symbol.setStyle("-fx-fill: " + fillStyle);
            symbol.setStroke(Color.BLACK);
            symbol.setStrokeWidth(2);
            return symbol;
        }
    }


}
