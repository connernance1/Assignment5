package sample;



import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

import javafx.scene.image.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
//import java.ImageIO.read;
import java.util.List;
import java.util.ArrayList;


public class Main extends Application {



    ObservableList<Node> children;
    void gallow() {
        Line Hbeam = new Line(25, 25, 200, 25);
        Hbeam.setStrokeWidth(3);
        children.add(Hbeam);

        Line gallow2 = new Line(25, 25, 25, 300);
        gallow2.setStrokeWidth(3);
        children.add(gallow2);

//        Line gallow3 = new Line(300, 300, 25, 300);
//        gallow3.setStrokeWidth(3);
//        children.add(gallow3);
        Arc arc = new Arc(25,300, 25, 25, 0, 180);
        arc.setFill(Color.WHITE);
        arc.setStroke(Color.BLACK);
        arc.setStrokeWidth(3);
        children.add(arc);

        Line rope = new Line(200, 25, 200, 75);
        rope.setStrokeWidth(3);
        children.add(rope);

    }


    void body() throws IOException {
        ArrayList body = new ArrayList<Shape>();
        //Will need to add image right here eventually
        Circle head = new Circle(200, 112,35,Color.WHITE);
//        Image image = new Image("Assingment5/boise.png");
//        image = ImageIO.read(getClass().getResource("/Assignment5/boise.png"));
//        head.setFill(new ImagePattern(image));
        head.setStrokeWidth(5);
        head.setVisible(false);
        children.add(head);
        body.add(head);

        Line spine = new Line(200, 200, 200, 150);
        spine.setStrokeWidth(5);
        spine.setVisible(false);
        children.add(spine);
        body.add(spine);

        Line leftArm = new Line(150,225,200,175);
        leftArm.setStrokeWidth(5);
        leftArm.setVisible(false);
        children.add(leftArm);
        body.add(head);

        Line rightArm = new Line(250,225,200, 175);
        rightArm.setStrokeWidth(5);
        rightArm.setVisible(false);
        children.add(rightArm);
        body.add(rightArm);

        Line leftLeg = new Line(200, 200, 175, 275);
        leftLeg.setStrokeWidth(5);
        leftLeg.setVisible(false);
        children.add(leftLeg);
        body.add(leftLeg);

        Line rightLeg = new Line(200, 200, 225, 275);
        rightLeg.setStrokeWidth(5);
        rightLeg.setVisible(false);
        children.add(rightLeg);
        body.add(rightLeg);
    }

    private 



    @Override
    public void start(Stage primaryStage) throws Exception{

        File file = new File("phrases.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        List<String> phraseList = new ArrayList<String>();
        while((line = br.readLine()) != null) {
            phraseList.add(line);
            stringBuffer.append(line);
            stringBuffer.append("\n");
        }
        fileReader.close();
        System.out.println(stringBuffer.toString());

        Pane pane = new Pane();
        children = pane.getChildren();




        //initialize empty game
        gallow();

        BorderPane bp = new BorderPane();

        bp.setCenter(pane);
        Scene scene = new Scene(bp, 600,600);



        boolean game = true;
            primaryStage.setTitle("Welcome to Hangman");
            primaryStage.setScene(scene);
            primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
