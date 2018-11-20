package sample;




import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert.ButtonType;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Alert.AlertType.Information;
import javafx.scene.Node;
import javafx.scene.text.Text;

import javafx.scene.image.*;
import java.io.*;
import java.util.ArrayList;


public class Main extends Application {


    private TextField GuessLetter = new TextField();
    private TextField GuessedLetters = new TextField();
    StringBuilder history = new StringBuilder();
    boolean valid = false;
    boolean WonGame = false;
    Text[] phrase = new Text[1];
    private int movesTaken = 5;
    ArrayList<Shape> body = new ArrayList<>();

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
        body.add(leftArm);

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

    private void addLine(String phrase) {
        Line[] notFound = new Line[phrase.length()];
        int xStart = 28;
        int lineLength = 7;
        int lineSpacing = 15;
        for (int i = 0; i < notFound.length; i++) {
            int xPos = xStart + (lineSpacing * i);

            notFound[i] = new Line(xPos, 505, xPos - lineLength, 505);
            notFound[i]. setStrokeWidth(3);
            children.add(notFound[i]);
        }
    }


    private Text[] Phrase(String phrase) {
        Text[] text = new Text[phrase.length()];
        int xStart = 10;
        int spaceing = 10;
        int count = 0;
        for(int i = 0; i < text.length; i++){
            if(phrase.substring(i) == "" && count > 10 ){
                int xPos = xStart + (spaceing * i);
                text[i] = new Text(phrase.substring(i, i + 1));
                text[i].setX(xPos);
                text[i].setY(800);
                text[i].setVisible(false);
                children.add(text[i]);

                count = 0;
            }
            int xPos = xStart + (spaceing * i);
            text[i] = new Text(phrase.substring(i, i + 1));
            text[i].setX(xPos);
            text[i].setY(500);
            children.add(text[i]);
            if(phrase.substring(i) == ""){
                text[i].setVisible(true);
            }
            else{
                text[i].setVisible(false);
            }

            count++;
        }
        return text;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        File file = new File("phrases.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        ArrayList<String> phraseList = new ArrayList<String>();
        while((line = br.readLine()) != null) {
            phraseList.add(line);
            stringBuffer.append(line);
            stringBuffer.append("\n");
        }
        fileReader.close();
//        System.out.println(stringBuffer.toString());

        Pane pane = new Pane();
        children = pane.getChildren();




        //initialize empty game
        gallow();
        body();

        addLine(phraseList.get(0));
        System.out.println(phraseList.get(0));


        phrase = Phrase(phraseList.get(0));
        System.out.println(phraseList.get(0));
        BorderPane bp = new BorderPane();



        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        TextField textField = new TextField();
//        Label label1 = new Label("Please Enter A Letter");

        gridPane.add(new Label("Please Enter A Letter"), 0, 0);
//        HorizontalBox horizontalBox = new HorizontalBox();
//        gridPane.getChildren().add(textField);
        gridPane.add(GuessLetter, 1, 0);

        gridPane.add(new Label("Incorrect Guesses"), 0, 1);
        gridPane.add(GuessedLetters, 1, 1);




        bp.setCenter(pane);
        bp.setRight(gridPane);
        Scene scene = new Scene(bp, 600,600);

        GuessLetter.setOnAction(event -> HangMan());

        boolean game = true;
            primaryStage.setTitle("Welcome to Hangman");
            primaryStage.setScene(scene);
            primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }







    private void HangMan() {
        String guess = GuessLetter.getText().toString();
        if(guess.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter more than a valid Character.");
            alert.showAndWait();
            return;
        }
        else if(guess.length() >= 1) {
            guess = guess.substring(0, 1);
        }
        GuessLetter.setText("");

        if(history.length() > 0) {

            if(history.indexOf(guess) > -1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Guess Error");
                alert.setHeaderText(null);
                alert.setContentText("This Character Has Already been Guessed");
                alert.showAndWait();
                return;
            } else {
                history.append(guess);
            }
        } else {
            history.append(guess);
        }
//        GuessedLetters.setText(history.toString());
        Boolean temp;
        temp = Character.isUpperCase(guess.charAt(0));
        for(int i = 0; i < phrase.length; i++) {
            String letter;
            letter = phrase[i].getText();
            if(guess.equals(letter)) {
                phrase[i].setVisible(true);
                valid = true;
            }
            else if (temp == true && guess.toLowerCase().equals(letter)){
                phrase[i].setVisible(true);
                valid = true;
            }
            else if(temp == false && guess.toUpperCase().equals(letter)){
                phrase[i].setVisible(true);
                valid = true;
            }

        }

        if(valid == false) {
//            """Set TO VISIBLE --- FIGURE OUT HOW TO DELIMIT VISIBLE ELEMENT"""

            body.get(5 - movesTaken).setVisible(true);
            GuessedLetters.setText(history.toString());

            movesTaken--;
        }

        if(movesTaken == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME Lost");
            alert.setHeaderText(null);
            alert.setContentText("YOU LOST THE GAME... This Wasn't even that hard... \n Try Doing this again and not sucking.");
            ButtonType yesbutton = new ButtonType("Play Again");
            alert.getButtonTypes().add(yesbutton);
            ButtonType nobutton = new ButtonType("Nah Im good Fam");
            alert.getButtonTypes().add(nobutton);
            return;
        }

        for(int i = 0; i < phrase.length; i++) {
            if(!phrase[i].isVisible()){
                WonGame = false;
                break;
            }
        }

        if(WonGame) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME WON");
            alert.setHeaderText(null);
            alert.setContentText("CONGRATS! YOU WON THE GAME!!");
            ButtonType yesbutton = new ButtonType("Play Again");
            alert.getButtonTypes().add(yesbutton);
            ButtonType nobutton = new ButtonType("Nah Im good Fam");
            alert.getButtonTypes().add(nobutton);
//            Optional<ButtonType> result = alert.showAndWait();
//            if(result.get() == yesbutton){
//                movesTaken = 6;
//                WonGame = false;
//
//            }
//            alert.showAndWait();
            return;
        }



    }

}






