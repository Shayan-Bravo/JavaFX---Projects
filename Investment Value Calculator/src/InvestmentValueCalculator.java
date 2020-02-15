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

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12,12,12,12));
        pane.setVgap(7);
        pane.setHgap(7);

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
        pane.add(futureValue,1,3);

        Button btAdd = new Button("Calculate");
        pane.add(btAdd,2,6);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Investment Value Calculator");
        stage.show();

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                double invAmount = Double.parseDouble(investmentAmount.getText());
                double intRate = Double.parseDouble(interestRate.getText());
                double yrs = Double.parseDouble(years.getText());
                double x = 1 + (intRate/100);
                DecimalFormat df = new DecimalFormat("##########.##");
                double ftrValue = (invAmount * (Math.pow(x,yrs)));
                df.format(ftrValue);

                futureValue.setText(Double.toString((ftrValue)));
            }
        };

        // when button is pressed
        btAdd.setOnAction(event);




    }
}
