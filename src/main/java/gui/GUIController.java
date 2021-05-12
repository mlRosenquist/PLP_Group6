package main.java.gui;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.scala.controller.Controller;


public class GUIController {
    Controller controller;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Pane drawingPane;

    @FXML
    private ImageView drawingCanvas;

    @FXML
    private TextArea logTxtArea;

    @FXML
    private TextArea codeTxtArea;

    @FXML
    private Button runButton;

    @FXML
    private Button clearButton;

    public GUIController() {
    }

    @FXML
    void initialize() {
    controller = new Controller(drawingPane, drawingCanvas, logTxtArea);
    // Prefilled with example
    codeTxtArea.setText(
                    "(BOUNDING-BOX (0 0) (8 8))\n" +
                    "(LINE (0 0) (8 8))\n" +
                    "(RECTANGLE (1 1) (2 2))\n" +
                    "(CIRCLE (12 12) 3)\n" +
                    "(TEXT-AT (1 1) Hello)\n" +
                    "(DRAW green (LINE (7 8) (12 12)) (RECTANGLE (1 1) (2 2)) (CIRCLE (12 12) 3) (TEXT-AT (1 1) Hello) (BOUNDING-BOX (0 0) (16 12))\n" +
                    "(FILL red (CIRCLE (4 4) 3)))" );
    }

    @FXML
    private void runCode() {
        controller.updateUI(codeTxtArea.getText());
    }

    @FXML
    private void clearDrawing() {
        controller.clear();
    }

}