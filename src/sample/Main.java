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
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.Node;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
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
