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
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String [] args) {

        launch(args);
    }
}