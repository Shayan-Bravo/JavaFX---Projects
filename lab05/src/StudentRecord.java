import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentRecord {
    private SimpleStringProperty SID;
    private SimpleDoubleProperty assignment;
    private SimpleDoubleProperty midterm;
    private SimpleDoubleProperty finalExam;
    private SimpleDoubleProperty finalMark;
    private SimpleStringProperty letterGrade;

    public StudentRecord(String SID, double assignment, double midterm, double finalExam){
        this.SID = new SimpleStringProperty(SID);
        this.assignment = new SimpleDoubleProperty(assignment);
        this.midterm = new SimpleDoubleProperty(midterm);
        this.finalExam = new SimpleDoubleProperty(finalExam);
        this.finalMark = new SimpleDoubleProperty((this.assignment.get() * .20) + (this.midterm.get() * .30) + (this.finalExam.get() * .50));
        if (this.finalMark.get() >= 80 && this.finalMark.get() <= 100){
            this.letterGrade = new SimpleStringProperty("A");
        } else if (this.finalMark.get() >= 70 && this.finalMark.get() <= 79){
            this.letterGrade = new SimpleStringProperty("B");
        } else if (this.finalMark.get() >= 60 && this.finalMark.get() <= 69) {
            this.letterGrade = new SimpleStringProperty("C");
        } else if (this.finalMark.get() >= 50 && this.finalMark.get() <= 59) {
            this.letterGrade = new SimpleStringProperty("D");
        }else if (this.finalMark.get() >= 0 && this.finalMark.get() <= 49) {
            this.letterGrade = new SimpleStringProperty("F");
        }

    }

    public String getSID(){
        return SID.get();
    }

    public double getAssignment(){
        return assignment.get();
    }

    public double getMidterm(){
        return midterm.get();
    }

    public double getFinalExam(){
        return finalExam.get();
    }

    public double getFinalMark(){
        return finalMark.get();
    }

    public String getLetterGrade(){
        return letterGrade.get();
    }

}
