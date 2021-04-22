package main.scala.controller

import javafx.embed.swing.SwingFXUtils
import javafx.scene.canvas.Canvas
import javafx.scene.control.TextArea
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import main.scala.drawer.Drawer
import main.scala.parser.Interpreter
import main.scala.models.{BoundingBox, Error, Instruction}

import java.awt.Image
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer;

class Controller(_pane: Pane, _canvas: ImageView, _logTxtArea: TextArea) {
  var canvas = _canvas;
  var textArea = _logTxtArea;
  var interpreter = new Interpreter();
  var drawer = new Drawer(_canvas);
  var pane = _pane;

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
    if(!drawings.isEmpty) {
      var boundingBox = drawings(0);
      drawings.remove(0);

      drawer.drawInstructions(pane.getWidth.toInt, pane.getHeight.toInt, boundingBox.asInstanceOf[BoundingBox], drawings)
    };
  }
}
