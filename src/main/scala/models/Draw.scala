package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.parser.InstructionsEnum

import java.awt.Color
import java.lang.reflect.Field
import scala.collection.mutable.ArrayBuffer

class Draw(_figures: ArrayBuffer[Instruction], _color: Color) extends Miscellaneous {
  var figures = _figures;
  var color = _color;

  def draw(gc: GraphicsContext): Unit ={
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
          case s if i.startsWith(InstructionsEnum.Circle.toString) => instructions.addOne(Circle.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) ))
          case s if i.startsWith(InstructionsEnum.Line.toString) => instructions.addOne(Line.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4) ))
          case s if i.startsWith(InstructionsEnum.Rectangle.toString) => instructions.addOne(Rectangle.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4) ))
          case s if i.startsWith(InstructionsEnum.TextAt.toString) => instructions.addOne(TextAt.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3)))
          case s if i.startsWith(InstructionsEnum.BoundingBox.toString) => instructions.addOne(BoundingBox.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4)))
          case _  =>      }
      })
      new Draw(instructions, color);

    } catch {
      case e: Exception =>
        new Error("Invalid Draw: " + input)
    }


  }
}