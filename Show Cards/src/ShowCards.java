import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileInputStream;

public class ShowCards extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ArrayList<Integer> cards = new ArrayList<Integer>();
        for (int i=0; i<55; i++) {
            cards.add(i);
        }
        Collections.shuffle(cards);

        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5,5,5,5));
        FileInputStream input = new FileInputStream("cards/"+cards.get(0)+".png");
        cards.remove(0);
        FileInputStream input2 = new FileInputStream("cards/"+cards.get(0)+".png");
        cards.remove(0);
        FileInputStream input3 = new FileInputStream("cards/"+cards.get(0)+".png");

        Image image = new Image(input);
        Image image2 = new Image(input2);
        Image image3 = new Image(input3);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(400);
        ImageView imageView1 = new ImageView(image2);
        imageView1.setFitWidth(200);
        imageView1.setFitHeight(400);
        ImageView imageView2 = new ImageView(image3);
        imageView2.setFitWidth(200);
        imageView2.setFitHeight(400);
        pane.getChildren().add(imageView);
        pane.getChildren().add(imageView1);
        pane.getChildren().add(imageView2);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Show Random Cards");
        stage.show();
    }

}
