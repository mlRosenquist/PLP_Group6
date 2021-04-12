package main.scala.models

import main.scala.parser.InstructionsEnum

import java.awt.Color
import scala.collection.mutable.ArrayBuffer

class Fill(_figure: Instruction, _color: Color) extends Miscellaneous {
  var figure = _figure;
  var color = _color;
}
object Fill {
  def parse(input: String): Instruction ={
    try {
      var splitInput = input.split(" ");
      var field = Class.forName("java.awt.Color").getField(splitInput(1));
      var color = field.get(null).asInstanceOf[Color]
      var instruction = new Instruction();

      splitInput.foreach(i => { i match {
        case s if i.startsWith(InstructionsEnum.Circle.toString) => instruction = Circle.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) )
        case s if i.startsWith(InstructionsEnum.Line.toString) => instruction = Line.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4))
        case s if i.startsWith(InstructionsEnum.Rectangle.toString) => instruction = Rectangle.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4) )
        case s if i.startsWith(InstructionsEnum.TextAt.toString) => instruction = TextAt.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3))
        case s if i.startsWith(InstructionsEnum.BoundingBox.toString) => instruction = BoundingBox.parse(i + " " + splitInput(splitInput.indexOf(i) + 1) + " " + splitInput(splitInput.indexOf(i) + 2) + " " + splitInput(splitInput.indexOf(i) + 3) + " " + splitInput(splitInput.indexOf(i) + 4))
        case _  => }
      })
      new Fill(instruction, color);

    } catch {
      case e: Exception =>
        new Error("Invalid Draw: " + input)
    }
  }
}