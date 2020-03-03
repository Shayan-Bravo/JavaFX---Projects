import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Charts extends Application {

    private static double[] avgHousingPricesByYear = {247381.0,264171.4,287715.3,294736.1,308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear = {1121585.3,1219479.5,1246354.2,1295364.8,1335932.6,1472362.0,1583521.9,1613246.3};
    private static String[] ageGroups = {"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = {648, 1021, 2453, 3173, 1868, 2247};
    //private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings({ "unchecked", "static-access" })
    @Override
    public void start(Stage window) throws Exception {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 900, 500);

        root.getStylesheets().add(getClass().getResource("charts.css").toExternalForm());

        //legends
        Legend barLegend = new Legend("bar");
        barLegend.setAlignment(Pos.CENTER);
        Legend circleLegend = new Legend("circle");
        circleLegend.setAlignment(Pos.CENTER);

        //start of barchart
        CategoryAxis x = new CategoryAxis();
        x.setLabel("Year Number");
        NumberAxis y = new NumberAxis();
        y.setLabel("Average Price per Year");

        BarChart<String, Number> barChart = new BarChart<>(x, y);

        barChart.setLegendVisible(false);

        XYChart.Series<String, Number> housing = new XYChart.Series<>();
        int i = 1;
        for (double h : avgHousingPricesByYear) {
            XYChart.Data<String, Number> data = new XYChart.Data<>("Year " + i, h);
            housing.getData().add(data);
            i++;
        }
        XYChart.Series<String, Number> commercial = new XYChart.Series<>();
        i = 1;
        for (double c : avgCommercialPricesByYear) {
            XYChart.Data<String, Number> data = new XYChart.Data<>("Year " + i, c);
            commercial.getData().add(data);
            i++;
        }
        barChart.getData().addAll(housing, commercial);
        //end of barchart

        //start of circlechart
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (int j = 0 ; j < 6 ; j++) {
            data.add(new PieChart.Data(ageGroups[j], purchasesByAgeGroup[j]));
        }
        PieChart circleChart = new PieChart(data);
        data.get(0).getNode().setStyle("-fx-pie-color: -fx-aqua");
        data.get(1).getNode().setStyle("-fx-pie-color: -fx-gold");
        data.get(2).getNode().setStyle("-fx-pie-color: -fx-darkorange");
        data.get(3).getNode().setStyle("-fx-pie-color: -fx-darksalmon");
        data.get(4).getNode().setStyle("-fx-pie-color: -fx-lawngreen");
        data.get(5).getNode().setStyle("-fx-pie-color: -fx-plum");
        circleChart.setLegendVisible(false);
        circleChart.setClockwise(false);
        //end of circlechart

        root.setHgap(10);
        root.setVgap(10);

        root.setConstraints(barChart, 0, 0);
        root.setConstraints(circleChart, 1, 0);
        root.setConstraints(barLegend, 0, 1);
        root.setConstraints(circleLegend, 1, 1);

        root.getChildren().addAll(barChart,barLegend,circleChart,circleLegend);

        window.setScene(scene);
        window.setTitle("Charts for Lab06");
        window.show();
    }

    class Legend extends GridPane {

        public Legend(String type) {
            if (type.equals("bar")) {
                addRow(0, createSymbol("-fx-red"), new Label("Housing"));
                addRow(1, createSymbol("-fx-blue"), new Label("Commercial"));
            } else {
                addRow(0, createSymbol("-fx-aqua"), new Label(ageGroups[0]));
                addRow(1, createSymbol("-fx-gold"), new Label(ageGroups[1]));
                addRow(2, createSymbol("-fx-darkorange"), new Label(ageGroups[2]));
                addRow(3, createSymbol("-fx-darksalmon"), new Label(ageGroups[3]));
                addRow(4, createSymbol("-fx-lawngreen"), new Label(ageGroups[4]));
                addRow(5, createSymbol("-fx-plum"), new Label(ageGroups[5]));
            }
        }

        private Node createSymbol(String fillStyle) {
            Shape symbol = new Circle(5);
            symbol.setStyle("-fx-fill: " + fillStyle);
            symbol.setStroke(Color.BLACK);
            symbol.setStrokeWidth(2);
            return symbol;
        }
    }
}
