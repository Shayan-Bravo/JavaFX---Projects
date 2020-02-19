import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;

public class Histogram extends Application {
    // create axis category for x and y axis of graph
    public final CategoryAxis xAxis = new CategoryAxis();
    public final NumberAxis yAxis = new NumberAxis();
    // create bar graph composed of xaxis and yaxis
    public final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
    // create data chart to add to bar-graph
    public XYChart.Series series1 = new XYChart.Series();
    // create a char array with the alphabet in it to output on the xaxis of bar-graph
    public char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    // create an int array representing all ascii existing values
    public int count[] = new int[256];

    @Override
    public void start(Stage stage) throws Exception {
        // Function counts the amount of each letter from the alphabet given a text file
        // and outputs the amount on a bar-graph

        // create stack pane
        StackPane root = new StackPane();
        // create a process button and textfield for file directory
        Button evalBtn = new Button("View");
        evalBtn.setAlignment(Pos.CENTER);
        TextField dir = new TextField("Enter directory");
        dir.setAlignment(Pos.BOTTOM_CENTER);
        dir.setMaxWidth(200);
        // set respective labels for axises
        xAxis.setLabel("Letters");
        yAxis.setLabel("Commonality");
        series1.setName("Number of Letter(s)");
        // call generateGraph to set up the bar-graph before data is processed
        generateGraph();
        // event handler for button and enter key
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                // reset count array every time button or enter is pushed
                count = new int[256];
                // open a file stream variable
                FileInputStream file  = null;
                try {
                    // get file directory from textfield
                    file = new FileInputStream(dir.getText());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                // set up a reader function to read strings in text file
                BufferedReader reader = new BufferedReader(new InputStreamReader(file));
                String line = null;
                try {
                    // read lines
                    line = reader.readLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // variable to add each line
                String line1;
                // while line has not reached the end of the file, loop through each line
                while(line != null){
                    // get the length of line for for loops
                    int len = line.length();
                    // turn all letters to lower case for the same ascii value range
                    line1 = line.toLowerCase();
                    // Initialize count array index
                    // increment array element corresponding to its respective ascii value
                    for (int i = 0; i < len; i++)
                        count[line1.charAt(i)]++;
                    try {
                        line = reader.readLine();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                // process graph after count array has been processed
                generateGraph();
            }
        };
        // when button/enter is pressed
        evalBtn.setOnAction(event);
        dir.setOnAction(event);
        //add xyzchart to bar-graph
        barChart.getData().addAll(series1);
        //set up vbox and all children (button, textfield and bar-graph)to it
        VBox vBox = new VBox();
        vBox.getChildren().addAll(dir,evalBtn,barChart);
        // set vbox to pane
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 800, 450);
        stage.setTitle("Letter Histogram");
        stage.setScene(scene);
        stage.show();
    }
    public void generateGraph(){
        // function will loop through ascii array of size 256 starting from ascii value 97 and ending 26 increments later
        // set x as a counter for ascii array
        int x = 97;
        for (int i=0; i<alphabet.length; i++){
            // process ascii array element as numbers of letters within the text file
            series1.getData().add(new XYChart.Data(Character.toString(alphabet[i]), count[x]));
            x++;
        }
    }
}
