package main.scala.controller

import javafx.scene.canvas.Canvas
import javafx.scene.control.TextArea
import javafx.scene.paint.Color
import main.scala.drawer.Drawer
import main.scala.parser.Interpreter
import main.scala.models.{Error, Instruction}

import scala.collection.mutable.ArrayBuffer;

class Controller(_canvas: Canvas, _logTxtArea: TextArea) {
  var canvas = _canvas;
  var textArea = _logTxtArea;
  var gc = canvas.getGraphicsContext2D();
  var interpreter = new Interpreter();
  var drawer = new Drawer(gc);

  def updateUI(_input: String) = {
    var instructions = interpreter.parse(_input);

    var drawings = ArrayBuffer[Instruction]();
    var errors = ArrayBuffer[Error]();

    instructions.foreach(i => i match {
      case (e: Error) => errors.addOne(e);
      case i => drawings.addOne(i);
    })

    var errorString = "";

    errors.foreach(e =>  errorString += e.msg + "\n");
    _logTxtArea.setText(errorString);

    // Draw the figures
    if(!drawings.isEmpty)
      drawer.drawInstructions(drawings);
  }
}
