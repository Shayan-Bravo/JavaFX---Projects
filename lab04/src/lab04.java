import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class lab04 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12,12,12,12));
        pane.setVgap(7);
        pane.setHgap(7);

        pane.add(new Label("Username:"), 0 ,0);
        TextField tf1 = new TextField();
        pane.add(tf1,1,0);
        pane.add(new Label("Password:"),0,1);
        TextField tf2 = new TextField();
        pane.add(tf2,1,1);
        pane.add(new Label("Name:"),0,2);
        TextField tf3 = new TextField();
        pane.add(tf3,1,2);
        pane.add(new Label("E-mail:"),0,3);
        TextField tf4 = new TextField();
        pane.add(tf4,1,3);
        pane.add(new Label("Phone:"),0,4);
        TextField tf5 = new TextField();
        pane.add(tf5,1,4);
        pane.add(new Label("DoB:"),0,5);
        DatePicker dPicker = new DatePicker();
        pane.add(dPicker,1,5);

        Button btAdd = new Button("Register");
        pane.add(btAdd,2,6);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Sign-Up");
        stage.show();

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                System.out.println("Username: " + tf1.getText());
                System.out.println("Password: " + tf2.getText());
                System.out.println("Full Name: " + tf3.getText());
                System.out.println("E-Mail: " + tf4.getText());
                if (tf5.getLength() != 10 || !tf5.getText().chars().allMatch(Character::isDigit)) {
                    System.out.println("Phone #: ERROR! Incorrect Input!");
                } else {
                    System.out.println("Phone #: " + (tf5.getText()).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
                }
                System.out.println("Date of Birth: " + dPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        };

        // when button is pressed
        btAdd.setOnAction(event);
    }
}
