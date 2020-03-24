import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Board extends Application {
    protected static TextArea board;
    protected static Stage serverWindow;
    protected static Scene serverScene;
    @Override
    public void start(Stage stage) throws Exception {
        serverWindow = stage;
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5,5,5,5));
        pane.setVgap(4);
        pane.setHgap(0);
        board = new TextArea();
        board.setWrapText(true);
        board.setEditable(false);
        Button exit = new Button("Exit");
        pane.add(board,0,0);
        pane.add(exit,0,1);

        serverScene = new Scene(pane,300,300);
        serverWindow.setScene(serverScene);
        serverWindow.setTitle("Server Board");
        serverWindow.show();
        
        new Thread (() -> connectToClient()).start();

        EventHandler<ActionEvent> ext = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                Stage mainWindow = lab10.mainWindow;
                mainWindow.setScene(lab10.mainScene);
                mainWindow.setTitle("Client");
                mainWindow.show();
            }
        };
        exit.setOnAction(ext);
    }

    public static void connectToClient() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            Platform.runLater(() -> board.appendText("Server executed at " + new Date() + '\n'));
            Socket connectToClient = serverSocket.accept();
            //Display the client number
            Platform.runLater(() -> board.appendText("Connected to a client at " + new Date() + '\n'));
            //Create data input and output streams
            DataInputStream isFromClient = new DataInputStream(connectToClient.getInputStream());
            //Continuously server the client

            while (true){
                String message = isFromClient.toString();
                Platform.runLater( () -> {
                    board.appendText(message);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
