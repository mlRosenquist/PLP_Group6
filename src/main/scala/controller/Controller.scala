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
import main.scala.models.{BoundingBox, Draw, Error, Instruction, Point, TextAt}

import java.awt.Image
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer;

class Controller(_pane: Pane, _canvas: ImageView, _logTxtArea: TextArea) {
  var oldInstructions: ArrayBuffer[Instruction] = new ArrayBuffer[Instruction]();
  var oldTextAt: ArrayBuffer[TextAt] = new ArrayBuffer[TextAt]();

  def getNewImage(_width: Int, _height: Int, _input: String): (BufferedImage, String) = {
    var instructions = Interpreter.parse(_input);

    var drawings = ArrayBuffer[Instruction]();
    var errors = ArrayBuffer[Error]();
    var text = ArrayBuffer[TextAt]();

    instructions.foreach(i => i match {
      case (e: Error) => errors.addOne(e);
      case (t: TextAt) => text.addOne(t);
      case (d: Draw) => {
        drawings.addOne(d);
        d.figures.foreach(f => f match {
          case (t: TextAt) => {
            t.color = d.color;
            text.addOne(t);
          }
          case _ =>
        })
      }
      case i => drawings.addOne(i);
    })

    var errorString = "";

    errors.foreach(e => errorString += e.msg + "\n");

    // Draw the figures

    if (!drawings.isEmpty) {
      var bufferedImage = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);

      val g = bufferedImage.createGraphics();

      // Set background
      g.setBackground(java.awt.Color.lightGray);
      g.clearRect(0, 0, _width, _height);

      var boundingBox = drawings(0).asInstanceOf[BoundingBox];
      boundingBox = BoundingBox.updateValues(_width, _height, boundingBox);
      drawings.remove(0);

      var initPixels = Drawer.getInitPixels(boundingBox);
      initPixels.foreach(p => {
        drawPixel(p, bufferedImage);
      })

      var pixels = Drawer.getDrawingPixels(boundingBox, drawings, oldInstructions);
      pixels.foreach(p => {
        drawPixel(p, bufferedImage);
      })

      oldTextAt.foreach(t => {
        if(t.color == java.awt.Color.CYAN)
          t.color = java.awt.Color.BLACK;
        t.drawString(g, boundingBox)
      })
      text.foreach(t => {
        if(t.color == java.awt.Color.BLACK)
          t.color = java.awt.Color.CYAN;
        t.drawString(g, boundingBox)
      });
      oldTextAt = oldTextAt.concat(text);

      new TextAt(boundingBox.bottomLeft,"(" + boundingBox.bottomLeft.x.toInt + "," + boundingBox.bottomLeft.y.toInt + ")").drawString(g, boundingBox);


      oldInstructions = oldInstructions.concat(drawings);
      return (bufferedImage, errorString);
    }
    else {
      return (null, errorString);
    }

  }

  def drawPixel(point: Point, bufferedImage: BufferedImage): Unit ={
    try {
      bufferedImage.setRGB(point.x.toInt, point.y.toInt, point.color.getRGB());
    }
    catch {
      case _ =>
    }
  }

  def getInitImage(_width: Int, _height: Int): (BufferedImage, String) = {
    oldInstructions.clear();
    oldTextAt.clear();

    var bufferedImage = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);

    var boundingBox = new BoundingBox(new Point(0,0), new Point(10,10), _showGrid = true);
    boundingBox = BoundingBox.updateValues(_width, _height, boundingBox);

    var initPixels = Drawer.getInitPixels(boundingBox);

    val g = bufferedImage.createGraphics();

    // Set background
    g.setBackground(java.awt.Color.lightGray);
    g.clearRect(0, 0, _width, _height);

    initPixels.foreach(p => {
      bufferedImage.setRGB(p.x.toInt, p.y.toInt, java.awt.Color.BLACK.getRGB());
    })

    new TextAt(boundingBox.bottomLeft,"(" + boundingBox.bottomLeft.x.toInt + "," + boundingBox.bottomLeft.y.toInt + ")").drawString(g, boundingBox);
    return (bufferedImage, "");
  }


}
