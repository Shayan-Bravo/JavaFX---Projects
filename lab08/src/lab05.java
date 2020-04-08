import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import static java.lang.Double.parseDouble;


public class lab05 extends Application {
    TableView<StudentRecord> dataTable;
    String row;
    ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
    String filename;
    double asgnW;
    double mdtW;
    double exW;

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(12,12,12,12));
        pane.setVgap(7);
        pane.setHgap(7);

        TableColumn<StudentRecord, String> SIDCol = new TableColumn("SID");
        SIDCol.setMinWidth(100);
        SIDCol.setCellValueFactory(new PropertyValueFactory<>("SID"));

        TableColumn<StudentRecord, Double> assignmentCol = new TableColumn("Assignment");
        assignmentCol.setMinWidth(100);
        assignmentCol.setCellValueFactory(new PropertyValueFactory<>("Assignment"));

        TableColumn<StudentRecord, Double> midtermCol = new TableColumn("Midterm");
        midtermCol.setMinWidth(100);
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("Midterm"));

        TableColumn<StudentRecord, Double> finalExamCol = new TableColumn("FinalExam");
        finalExamCol.setMinWidth(100);
        finalExamCol.setCellValueFactory(new PropertyValueFactory<>("FinalExam"));

        TableColumn<StudentRecord, Double> finalGradeCol = new TableColumn("FinalMark");
        finalGradeCol.setMinWidth(100);
        finalGradeCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Double>("FinalMark"));

        TableColumn<StudentRecord, String> letterGradeCol = new TableColumn("LetterGrade");
        letterGradeCol.setMinWidth(100);
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("LetterGrade"));

        dataTable = new TableView<>();
        Menu m = new Menu("File");

        // create menuitems
        MenuItem new1 = new MenuItem("New");
        MenuItem open1 = new MenuItem("Open");
        MenuItem save1 = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem exit1 = new MenuItem("Exit");

        // add menu items to menu
        m.getItems().add(new1);
        m.getItems().add(open1);
        m.getItems().add(save1);
        m.getItems().add(saveAs);
        m.getItems().add(exit1);

        // create a menubar
        MenuBar mb = new MenuBar();

        mb.getMenus().add(m);

        Button btAdd = new Button("Add");
        pane.add(btAdd,4,3);
        pane.add(new Label("SID:"), 0 ,0);
        TextField sidT = new TextField();
        pane.add(sidT,1,0);
        pane.add(new Label("Assignment:"), 3 ,0);
        TextField asgT = new TextField();
        pane.add(asgT,4,0);
        pane.add(new Label("Midterm:"), 0 ,1);
        TextField midtermT = new TextField();
        pane.add(midtermT,1,1);
        pane.add(new Label("Final Exam:"), 3 ,1);
        TextField fExamT = new TextField();
        pane.add(fExamT,4,1);
        pane.add(new Label("Assignment Weight:"), 0 ,2);
        TextField assignW = new TextField();
        pane.add(assignW,1,2);
        pane.add(new Label("Midterm Weight:"), 1 ,2);
        TextField midtermW = new TextField();
        pane.add(midtermW,2,2);
        pane.add(new Label("Exam Weight:"), 3 ,2);
        TextField examW = new TextField();
        pane.add(examW,4,2);


        dataTable.getColumns().addAll(SIDCol,assignmentCol,midtermCol,finalExamCol, finalGradeCol, letterGradeCol);

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        HBox hboxadd = new HBox();
        hBox.getChildren().addAll(mb);
        hboxadd.getChildren().add(pane);
        vBox.getChildren().addAll(hBox,dataTable,hboxadd);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Student Records");
        stage.show();

        EventHandler<ActionEvent> bt = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                dataTable.setItems(getAllMarks(sidT.getText(), parseDouble(asgT.getText()),
                        parseDouble(midtermT.getText()), parseDouble(fExamT.getText())));
                sidT.clear();
                asgT.clear();
                midtermT.clear();
                fExamT.clear();
            }
        };

        btAdd.setOnAction(bt);

        EventHandler<ActionEvent> newFile = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                dataTable.getItems().clear();
            }
        };

        new1.setOnAction(newFile);

        EventHandler<ActionEvent> exit = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                System.exit(0);
            }
        };

        exit1.setOnAction(exit);

        EventHandler<ActionEvent> saveAsF = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open CVS File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("CVS Files", "*.cvs"));
                File selectedFile = fileChooser.showSaveDialog(stage);
                filename = selectedFile.getAbsolutePath();
                save(filename);
            }
        };

        saveAs.setOnAction(saveAsF);

        EventHandler<ActionEvent> save = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                save(filename);
            }
        };

        save1.setOnAction(save);


        EventHandler<ActionEvent> open = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("CVS Files", "*.cvs"));
                File selectedFile = fileChooser.showOpenDialog(stage);
                filename = selectedFile.getAbsolutePath();
                BufferedReader csvReader = null;
                try {
                    csvReader = new BufferedReader(new FileReader(selectedFile));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        if (!((row = csvReader.readLine()) != null)) break;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    String[] data = row.split(",");
                    dataTable.setItems(getAllMarks(data[0], parseDouble(data[1]),
                            parseDouble(data[2]), parseDouble(data[3])));
                }
                try {
                    csvReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

        open1.setOnAction(open);

    }

    public ObservableList<StudentRecord> getAllMarks(String sid, double astg, double midtermt, double fexamt) {
        // Student ID, Assignments, Midterm, Final exam
        marks.add(new StudentRecord(sid, astg, midtermt, fexamt, .20, .30, .50));
        return marks;
    }
    public void save(String filename){
        Writer csvWriter = null;
        try {
            csvWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            for (StudentRecord student : marks) {

                String text = student.getSID() + "," + student.getAssignment() +
                        "," + student.getMidterm() + "," + student.getFinalExam() + "\n";
                csvWriter.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            try {
                csvWriter.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                csvWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}

