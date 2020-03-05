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
        // REPEATED CODE BLOCKS WITHIN THE FILE ARE ONLY COMMENTED ONCE


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
            // keep the point within the circle's circumference as its being moved
            // get the distance between cursor x pos and centre of circle
            double dx = e.getX() - circle.getCenterX();
            // get the distance between cursor y pos and centre of circle
            double dy = e.getY() - circle.getCenterY();
            // get the hypotneus
            double hyp = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
            // divide the distance of x and y between cursor and centre of circle by the hypotneus
            double x = dx / hyp;
            double y = dy / hyp;
            // lock the point on the circumference of the circle while its being moved
            point1.setCenterX(x * circle.getRadius() + circle.getCenterX());
            point1.setCenterY(y * circle.getRadius() + circle.getCenterY());

            // match the triangle sides coords to the point which is being moved
            side1.setStartX(point1.getCenterX());
            side1.setStartY(point1.getCenterY());
            side2.setStartX(point1.getCenterX());
            side2.setStartY(point1.getCenterY());

            // calculate the distances of all triangle sides for pythag theorem
            double [] distances = distance(side1.getStartX(),side1.getStartY(),
                    side1.getEndX(),side1.getEndY(),side2.getStartX(),side2.getStartY(),
                    side2.getEndX(),side2.getEndY(), side3.getStartX(),side3.getStartY(),
                    side3.getEndX(),side3.getEndY());

            double aLen = distances[0];
            double bLen = distances[1];
            double cLen = distances[2];

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

        point2.setOnMouseDragged(e ->{
            // keep the point within the circle's circumference as its being moved
            double dx = e.getX() - circle.getCenterX();
            double dy = e.getY() - circle.getCenterY();

            double hyp = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));

            double x = dx / hyp;
            double y = dy / hyp;

            point2.setCenterX(x * circle.getRadius() + circle.getCenterX());
            point2.setCenterY(y * circle.getRadius() + circle.getCenterY());

            // match the triangle sides coords to the point which is being moved
            side1.setEndX(point2.getCenterX());
            side1.setEndY(point2.getCenterY());
            side3.setStartX(point2.getCenterX());
            side3.setStartY(point2.getCenterY());

            // calculate the distances of all triangle sides for pythag theorem
            double [] distances = distance(side1.getStartX(),side1.getStartY(),
                    side1.getEndX(),side1.getEndY(),side2.getStartX(),side2.getStartY(),
                    side2.getEndX(),side2.getEndY(), side3.getStartX(),side3.getStartY(),
                    side3.getEndX(),side3.getEndY());

            double aLen = distances[0];
            double bLen = distances[1];
            double cLen = distances[2];

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
            System.out.println("x: " + point2.getCenterX());
            System.out.println("y: " + point2.getCenterY());
        });

        point3.setOnMouseDragged(e ->{
            // keep the point within the circle's circumference as its being moved
            double dx = e.getX() - circle.getCenterX();
            double dy = e.getY() - circle.getCenterY();

            double hyp = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));

            double x = dx / hyp;
            double y = dy / hyp;

            point3.setCenterX(x * circle.getRadius() + circle.getCenterX());
            point3.setCenterY(y * circle.getRadius() + circle.getCenterY());

            // match the triangle sides coords to the point which is being moved
            side2.setEndX(point3.getCenterX());
            side2.setEndY(point3.getCenterY());
            side3.setEndX(point3.getCenterX());
            side3.setEndY(point3.getCenterY());
            // calculate the distances of all triangle sides for pythag theorem
            double [] distances = distance(side1.getStartX(),side1.getStartY(),
                    side1.getEndX(),side1.getEndY(),side2.getStartX(),side2.getStartY(),
                    side2.getEndX(),side2.getEndY(), side3.getStartX(),side3.getStartY(),
                    side3.getEndX(),side3.getEndY());

            double aLen = distances[0];
            double bLen = distances[1];
            double cLen = distances[2];

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
            System.out.println("x: " + point3.getCenterX());
            System.out.println("y: " + point3.getCenterY());
        });


        // create pane and set the scene on it
        Pane pane = new Pane();
        pane.getChildren().addAll(circle,side1,side2,side3,point1,point2,point3,p1,p2,p3);
        Scene scene = new Scene(pane,400, 400);
        stage.setScene(scene);
        stage.setTitle("Circle");
        stage.show();
    }

    public double[] distance(double s1s, double s1sy, double s1e, double s1ey, double s2s, double s2sy,
                                  double s2e, double s2ey, double s3s, double s3sy, double s3e, double s3ey){

        double aLen = Math.sqrt(Math.pow(Math.abs(s1s-s1e),2)
                +Math.pow(Math.abs(s1sy-s1ey),2));

        double bLen = Math.sqrt(Math.pow(Math.abs(s2s-s2e),2)
                +Math.pow(Math.abs(s2sy-s2ey),2));

        double cLen = Math.sqrt(Math.pow(Math.abs(s3s-s3e),2)
                +Math.pow(Math.abs(s3sy-s3ey),2));

        double [] distance = new double[3];

        distance[0] = aLen;
        distance[1] = bLen;
        distance[2] = cLen;

        return distance;

    }
}
