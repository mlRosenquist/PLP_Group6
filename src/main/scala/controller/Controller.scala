package main.scala.controller

import javafx.scene.canvas.Canvas
import javafx.scene.control.TextArea
import javafx.scene.paint.Color
import main.scala.parser.Interpreter
import main.scala.models.Error;

class Controller(_canvas: Canvas, _logTxtArea: TextArea) {
  var canvas = _canvas;
  var textArea = _logTxtArea;
  var gc = canvas.getGraphicsContext2D();
  var interpreter = new Interpreter();

  def updateUI(_input: String) = {
    var instructions = interpreter.parse(_input);

    // Get errors
    var errors = "";
    instructions.foreach(i =>  i match {
      case (e: Error) => errors += e.msg + "\n"
      case _ =>
    });
    _logTxtArea.setText(errors);

    gc.setFill(Color.BLUE);
    gc.fillRect(75, 75, 100, 100)
  }
}
