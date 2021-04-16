package main.java.x;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import main.scala.models.Instruction;
import main.scala.parser.Interpreter;

import java.util.Objects;

public class JavaObject extends Application {
    public void sayHello() {
        System.out.println("Hi from Java!");
    }
    // sbt -jvm-debug 5005

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FigureDrawerMain.fxml")));
        primaryStage.setTitle("DrawingApp");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();
    }

    public static void main(String [] args) {
        Interpreter interpreter = new Interpreter();

        Instruction[] x = interpreter.parse(
                "(LINE (7 8) (12 12))\n" +
                        "(RECTANGLE (1 1) (2 2))\n" +
                        "(CIRCLE (12 12) 3)\n" +
                        "(TEXT-AT (1 1) Hello)\n" +
                        "(BOUNDING-BOX (0 0) (16 12))\n" +
                        "(DRAW green (LINE (7 8) (12 12)) (RECTANGLE (1 1) (2 2)) (CIRCLE (12 12) 3) (TEXT-AT (1 1) Hello) (BOUNDING-BOX (0 0) (16 12))\n" +
                        "(FILL red (LINE (7 8) (12 12)))" );

        launch(args);
    }
}