package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem
import main.scala.parser.InstructionsEnum

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import java.lang.reflect.Field
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Draw(_figures: ArrayBuffer[Instruction], _color: Color) extends Miscellaneous {
  var figures = _figures;
  this.color = _color

  def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] ={
    var pixels = new ArrayBuffer[Point]();
    figures.foreach(f => f.color = color);
    figures.foreach(f => f match {
      case (t: TextAt) => ;
      case _ => pixels = pixels.concat(f.draw(_boundingBox))
    });
    return pixels;
  }
}
object Draw {
  // (DRAW green (LINE (7 8) (12 12)) (LINE (7 8) (12 12)) (LINE (7 8) (12 12)))
  def parse(input: String): Instruction ={

    try {
      var splitInput = input.split(" ");
      var field = Class.forName("java.awt.Color").getField(splitInput(1));
      var color = field.get(null).asInstanceOf[Color]
      var instructions = new ArrayBuffer[Instruction];

      splitInput.foreach(i => { i match {
          case s if i.startsWith(InstructionsEnum.Circle.toString) =>{
            instructions.addOne(Circle.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) ))
            splitInput(splitInput.indexOf(i)) = "USED";
          }
          case s if i.startsWith(InstructionsEnum.Line.toString) => {
            instructions.addOne(Line.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4) ))
            splitInput(splitInput.indexOf(i)) = "USED";
          }
          case s if i.startsWith(InstructionsEnum.Rectangle.toString) => {
            instructions.addOne(Rectangle.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4) ))
            splitInput(splitInput.indexOf(i)) = "USED";
          }
          case s if i.startsWith(InstructionsEnum.TextAt.toString) => {
            instructions.addOne(TextAt.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3)))
            splitInput(splitInput.indexOf(i)) = "USED";
          }
          case _  =>      }
      })
      new Draw(instructions, color);

    } catch {
      case e: Exception =>
        new Error("Invalid Draw: " + input)
    }
  }
}