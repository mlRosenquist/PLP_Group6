package main.java.x;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.scala.controller.Controller;
import main.scala.parser.Interpreter;


public class FxController {
    Controller controller;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Pane drawingPane;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private TextArea logTxtArea;

    @FXML
    private TextArea codeTxtArea;

    @FXML
    private Button runButton;

    @FXML
    private Button clearButton;

    public FxController() {
    }

    @FXML
    void initialize() {
    controller = new Controller(drawingCanvas, logTxtArea);

    // Prefilled with example
    codeTxtArea.setText(
                    "(LINE (7 8) (12 12))\n" +
                    "(RECTANGLE (1 1) (2 2))\n" +
                    "(CIRCLE (12 12) 3)\n" +
                    "(TEXT-AT (1 1) Hello)\n" +
                    "(BOUNDING-BOX (0 0) (16 12))\n" +
                    "(DRAW green (LINE (7 8) (12 12)) (RECTANGLE (1 1) (2 2)) (CIRCLE (12 12) 3) (TEXT-AT (1 1) Hello) (BOUNDING-BOX (0 0) (16 12))\n" +
                    "(FILL red (LINE (7 8) (12 12)))" );
    }

    @FXML
    private void console() {
        controller.updateUI(codeTxtArea.getText());
    }

}