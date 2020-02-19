import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.lang.Math;

public class Circle extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Function creates a circle with 3 points which are connected to each other through lines.
        // Function shows 3 dynamic angles which change on cursor movement
        // ONLY ONE point is able to move through cursor movement

        // create main circle and set it in the centre of the pane
        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle();
        circle.setCenterX(200);
        circle.setCenterY(200);
        circle.setStroke(Color.BLUE);
        circle.setFill(Color.WHITE);
        circle.setRadius(100);
        circle.setStrokeWidth(10);
        // create the first point and set it on a fixed location
        javafx.scene.shape.Circle point1 = new javafx.scene.shape.Circle();
        point1.setCenterX(100);
        point1.setCenterY(200);
        point1.setStroke(Color.BLACK);
        point1.setFill(Color.RED);
        point1.setRadius(5);
        // create the second point and set it on a fixed location
        javafx.scene.shape.Circle point2 = new javafx.scene.shape.Circle();
        point2.setCenterX(200);
        point2.setCenterY(300);
        point2.setStroke(Color.BLACK);
        point2.setFill(Color.RED);
        point2.setRadius(5);
        // create the third point and set it on a fixed location
        javafx.scene.shape.Circle point3 = new javafx.scene.shape.Circle();
        point3.setCenterX(300);
        point3.setCenterY(200);
        point3.setStroke(Color.BLACK);
        point3.setFill(Color.RED);
        point3.setRadius(5);
        // create the first triangle side and connect point1 to point2
        Line side1 = new Line();
        side1.setStartX(point1.getCenterX());
        side1.setStartY(point1.getCenterY());
        side1.setEndX(point2.getCenterX());
        side1.setEndY(point2.getCenterY());
        side1.setFill(Color.BLACK);
        // create the second triangle side and connect point1 to point3
        Line side2 = new Line();
        side2.setStartX(point1.getCenterX());
        side2.setStartY(point1.getCenterY());
        side2.setEndX(point3.getCenterX());
        side2.setEndY(point3.getCenterY());
        side2.setFill(Color.BLACK);
        // create the third triangle side and connect point2 to point3
        Line side3 = new Line();
        side3.setStartX(point2.getCenterX());
        side3.setStartY(point2.getCenterY());
        side3.setEndX(point3.getCenterX());
        side3.setEndY(point3.getCenterY());
        side3.setFill(Color.BLACK);
        // create label to show angle for the moving point
        Label p1 = new Label();
        p1.setTextFill(Color.RED);
        p1.setLayoutX(point1.getCenterX()+20);
        p1.setLayoutY(point1.getCenterY());
        p1.setText("Move");
        Label p2 = new Label();
        p2.setTextFill(Color.RED);
        Label p3 = new Label();
        p3.setTextFill(Color.RED);

        // create an event handle for mouse drag on point1
        point1.setOnMouseDragged(e ->{
            // match cursor coordinates to point1 and the 2 triangle sides coords
            point1.setCenterX(e.getX());
            point1.setCenterY(e.getY());
            side1.setStartX(e.getX());
            side1.setStartY(e.getY());
            side2.setStartX(e.getX());
            side2.setStartY(e.getY());
            // calculate the distances of all triangle sides for pythag theorem
            double aLen = Math.sqrt(Math.pow(Math.abs(side1.getStartX()-side1.getEndX()),2)
                    +Math.pow(Math.abs(side1.getStartY()-side1.getEndY()),2));
            double bLen = Math.sqrt(Math.pow(Math.abs(side2.getStartX()-side2.getEndX()),2)
                    +Math.pow(Math.abs(side2.getStartY()-side2.getEndY()),2));
            double cLen = Math.sqrt(Math.pow(Math.abs(side3.getStartX()-side3.getEndX()),2)
                    +Math.pow(Math.abs(side3.getStartY()-side3.getEndY()),2));
            // position the labels near respective points, which show the angles
            p1.setLayoutX(point3.getCenterX()-30);
            p1.setLayoutY(point3.getCenterY()-10);
            p2.setLayoutX(point2.getCenterX()-5);
            p2.setLayoutY(point2.getCenterY()+15);
            p3.setLayoutX(point1.getCenterX()+10);
            p3.setLayoutY(point1.getCenterY()+10);
            // calculate respective angles and output to respective label
            p1.setText(Integer.toString((int)Math.round(Math.toDegrees(Math.acos((aLen * aLen - bLen * bLen - cLen * cLen) / (-2 * bLen * cLen))))));
            p2.setText(Integer.toString((int)Math.round(Math.toDegrees(Math.acos((bLen * bLen - aLen * aLen - cLen * cLen) / (-2 * aLen * cLen))))));
            p3.setText(Integer.toString((int)Math.round(Math.toDegrees(Math.acos((cLen * cLen - bLen * bLen - aLen * aLen) / (-2 * aLen * bLen))))));
            // output mouse/point1 coords x and y
            System.out.println("x: " + point1.getCenterX());
            System.out.println("y: " + point1.getCenterY());
        });
        // create pane and set the scene on it
        Pane pane = new Pane();
        pane.getChildren().addAll(circle,side1,side2,side3,point1,point2,point3,p1,p2,p3);
        Scene scene = new Scene(pane,400, 400);
        stage.setScene(scene);
        stage.setTitle("Circle");
        stage.show();
    }
}
