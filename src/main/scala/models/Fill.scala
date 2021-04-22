package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem
import main.scala.parser.InstructionsEnum

import java.awt.Color
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Fill(_figure: Instruction, _color: Color) extends Miscellaneous {
  var figure = _figure;
  var color = _color;

  def draw(_coordinateSystem: CoordinateSystem): Unit ={
  }
}
object Fill {
  def parse(input: String): Instruction ={
    try {
      var splitInput = input.split(" ");
      var field = Class.forName("java.awt.Color").getField(splitInput(1));
      var color = field.get(null).asInstanceOf[Color]


      /*splitInput.foreach(i => { i match {
        case s if i.startsWith(InstructionsEnum.Circle.toString) => return new Fill(Circle.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) ), color);
        case s if i.startsWith(InstructionsEnum.Line.toString) => return new Fill(Line.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4)), color);
        case s if i.startsWith(InstructionsEnum.Rectangle.toString) => return new Fill(Rectangle.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4) ), color);
        case s if i.startsWith(InstructionsEnum.TextAt.toString) => return new Fill(TextAt.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3)), color)
        case s if i.startsWith(InstructionsEnum.BoundingBox.toString) => return new Fill(BoundingBox.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4)), color)
        case _  => }
      })*/
      return new Fill(null, color);
    } catch {
      case e: Exception =>
        new Error("Invalid Draw: " + input)
    }
  }
}