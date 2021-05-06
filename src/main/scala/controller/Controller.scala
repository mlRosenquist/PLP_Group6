package main.scala.controller

import javafx.application.Platform
import javafx.embed.swing.SwingFXUtils
import javafx.scene.canvas.Canvas
import javafx.scene.control.TextArea
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import main.scala.drawer.Drawer
import main.scala.parser.Interpreter
import main.scala.models.{BoundingBox, Error, Instruction, Point}

import java.awt.Image
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer;

class Controller(_pane: Pane, _canvas: ImageView, _logTxtArea: TextArea) {
  var canvas = _canvas;
  var textArea = _logTxtArea;
  var interpreter = new Interpreter();
  var pane = _pane;
  var drawer: Drawer = null;

  Platform.runLater(() => drawer = new Drawer(_canvas, _pane.getWidth.toInt, _pane.getHeight.toInt, new BoundingBox(new Point(0,0), new Point(10,10), true)))

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

  def clear() = {
    drawer = new Drawer(_canvas, _pane.getWidth.toInt, _pane.getHeight.toInt, new BoundingBox(new Point(0,0), new Point(10,10), true));
  }
}
