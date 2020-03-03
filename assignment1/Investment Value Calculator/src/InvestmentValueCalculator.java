import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.lang.Math;
import java.text.DecimalFormat;

public class InvestmentValueCalculator extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Function calculates future investment value given 3 different values

        // create pane with its respective verticle and horizontal gaps
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12,12,12,12));
        pane.setVgap(7);
        pane.setHgap(7);

        // add a label then a textfield right beside it.
        pane.add(new Label("Investment Amount"), 0 ,0);
        TextField investmentAmount = new TextField();
        pane.add(investmentAmount,1,0);
        pane.add(new Label("Year(s)"),0,1);
        TextField years = new TextField();
        pane.add(years,1,1);
        pane.add(new Label("Annual Interest Rate"),0,2);
        TextField interestRate = new TextField();
        pane.add(interestRate,1,2);
        pane.add(new Label("Future Value"),0,3);
        TextField futureValue = new TextField();
        // disable textfield from being editable by user
        futureValue.setEditable(false);
        pane.add(futureValue,1,3);

        //add calculate button then place it to the right
        Button btAdd = new Button("Calculate");
        pane.add(btAdd,2,6);
        GridPane.setHalignment(btAdd, HPos.RIGHT);
        // create scene with the pane on it
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Investment Value Calculator");
        stage.show();

        // create an event handler for the calculate button
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                // collect values from the textfields and convert them into a double to run calculations
                double invAmount = Double.parseDouble(investmentAmount.getText());
                double intRate = (Double.parseDouble(interestRate.getText())/100);
                double yrs = (Double.parseDouble(years.getText()));
                // set the significant digits to 2 decimal places to signify cents
                DecimalFormat df = new DecimalFormat("#.##");
                // calculate future value
                double ftrValue = (invAmount * (Math.pow(1 + intRate/12,yrs*12)));
                // format significant digits
                String ftr = df.format(ftrValue);
                // output future value
                futureValue.setText(ftr);
            }
        };
        // run event handler when button is pressed
        btAdd.setOnAction(event);
    }
}
