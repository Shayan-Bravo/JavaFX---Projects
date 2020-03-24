import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class lab10 extends Application {
    protected static TextField username;
    protected static TextField message;
    DataOutputStream osToServer;
    DataInputStream isFromServer;
    protected static Stage mainWindow;
    protected static Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setHgap(0);
        pane.setVgap(7);
        username = new TextField();
        message = new TextField();
        Button send = new Button("Send Message");
        Button exit = new Button("Exit");
        Label usernameLabel = new Label("Username:");
        Label messageLabel = new Label("Message:");
        pane.add(usernameLabel,0,0);
        pane.add(username,1,0);
        pane.add(messageLabel,0,1);
        pane.add(message,1,1);
        pane.add(send,0,2);
        pane.add(exit,0,3);

        mainScene = new Scene(pane);
        mainWindow.setScene(mainScene);
        mainWindow.setTitle("Client");
        mainWindow.show();

        //Board board = new Board();
        //serverThread = new Thread(board);
        //serverThread.start();

        EventHandler<ActionEvent> sendMsg = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                message.clear();
                Board.connectToClient();
            }
        };
        send.setOnAction(sendMsg);
        send.setOnAction(e -> connectToServer());

        EventHandler<ActionEvent> ext = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                //serverThread.stop();
                System.exit(0);
            }
        };
        exit.setOnAction(ext);


        try {
            // Create a socket to connect to the server
            Socket connectToServer = new Socket("localhost", 8000);

            // Create an input stream to receive data from the server

            // Create an output stream to send data to the server
            osToServer = new DataOutputStream(connectToServer.getOutputStream());
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void connectToServer() {
        try {
            // Get the weight from the text field

            // Send the weight to the server
            osToServer.writeUTF(username.getText()+": "+message.getText());
            osToServer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
