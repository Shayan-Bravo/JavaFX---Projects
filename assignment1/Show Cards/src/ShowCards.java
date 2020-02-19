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

// Name: Shayan Khosravi
// Student Number: 100655396
// Assignment 1 | Show Cards Exercise
public class ShowCards extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Function creates a GUI which will show 3 random cards.
        //Function will choose 3 cards randomly with no duplicates on execution of program.

        // create an array <deck> to store 52 cards
        ArrayList<Integer> deck = new ArrayList<Integer>();
        // fill array with 52 values starting from 0
        for (int i=0; i<52; i++) {
            deck.add(i);
        }
        // shuffle array to have a randomized order of cards
        Collections.shuffle(deck);
        // print out array <deck> after it has been shuffled
        System.out.print(deck);
        // create pane using Hbox
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5,5,5,5));
        // creates 3 file input streams for each imageviewer (where the program will choose its random cards)
        // program will choose the first element from the shuffled deck array and delete the chosen card to avoid duplicates
        FileInputStream input = new FileInputStream("cards/"+deck.get(0)+".png");
        deck.remove(0);
        FileInputStream input2 = new FileInputStream("cards/"+deck.get(0)+".png");
        deck.remove(0);
        FileInputStream input3 = new FileInputStream("cards/"+deck.get(0)+".png");
        // create imageviewers and set their respective lengths and widths
        Image card1 = new Image(input);
        Image card2 = new Image(input2);
        Image card3 = new Image(input3);
        ImageView imageView = new ImageView(card1);
        imageView.setFitWidth(200);
        imageView.setFitHeight(400);
        ImageView imageView1 = new ImageView(card2);
        imageView1.setFitWidth(200);
        imageView1.setFitHeight(400);
        ImageView imageView2 = new ImageView(card3);
        imageView2.setFitWidth(200);
        imageView2.setFitHeight(400);
        // add the imageviewers to the pane
        pane.getChildren().add(imageView);
        pane.getChildren().add(imageView1);
        pane.getChildren().add(imageView2);
        // create the scene and set the pane on it
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Random Cards");
        stage.show();
    }

}
