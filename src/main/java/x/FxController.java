package main.java.x;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;


public class FxController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private TextArea logTextArea;

    @FXML
    private TextArea codeTextArea;

    @FXML
    private Button runButton;

    @FXML
    private Button clearButton;

    public FxController() {

    }

    @FXML
    void initialize() {

    }

    @FXML
    private void console() {
        System.out.println("Hello from FX!");
    }

}