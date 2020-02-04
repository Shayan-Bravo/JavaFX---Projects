import javafx.application.Application;
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

public class lab04 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12,12,12,12));
        pane.setVgap(5);
        pane.setHgap(5);

        pane.add(new Label("Username:"), 0 ,0);
        pane.add(new TextField(),1,0);
        pane.add(new Label("Password:"),0,1);
        pane.add(new TextField(),1,1);
        pane.add(new Label("Name:"),0,2);
        pane.add(new TextField(),1,2);
        pane.add(new Label("E-mail:"),0,3);
        pane.add(new TextField(),1,3);
        pane.add(new Label("Phone:"),0,4);
        pane.add(new TextField(),1,4);
        pane.add(new Label("DoB:"),0,5);
        pane.add(new DatePicker(),1,5);

        Button btAdd = new Button("Register");
        pane.add(btAdd,2,6);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Sign-Up");
        stage.show();
    }

    public class btAdd{


    }
}
