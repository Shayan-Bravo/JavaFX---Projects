import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class lab05 extends Application {
    TableView<StudentRecord> dataTable;
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

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
        dataTable.setItems(getAllMarks());
        dataTable.getColumns().addAll(SIDCol,assignmentCol,midtermCol,finalExamCol, finalGradeCol, letterGradeCol);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(dataTable);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Student Records");
        stage.show();
    }

    public ObservableList<StudentRecord> getAllMarks() {
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

        // Student ID, Assignments, Midterm, Final exam
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
        marks.add(new StudentRecord("100100101", 70.0f, 69.25f, 51.5f));
        marks.add(new StudentRecord("100100102", 100.0f, 97.0f, 92.5f));
        marks.add(new StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f, 58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f, 62.5f));
        marks.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        marks.add(new StudentRecord("100100107", 55.0f, 47.0f, 50.5f));
        marks.add(new StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));
        return marks;
    }
}

