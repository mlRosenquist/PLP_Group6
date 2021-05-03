package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem
import main.scala.parser.InstructionsEnum

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.util.{Success, Try}

class Circle(_center: Point, _radius: Int) extends Figure {
  var center = _center;
  var radius = _radius;

  def draw(_coordinateSystem: CoordinateSystem): Unit ={
  }
}
object Circle {
  // (CIRCLE (12 12) 3)
  def parse(input: String): Instruction ={
    var splitInput = input.split(" ");

    var center = Point.TryParse(splitInput(1), splitInput(2));

    var radius = Try(splitInput(3).toInt);

    (center, Try(splitInput(3).toInt)) match {
      case (c: Point, Success(r)) => new Circle(c, r);
      case (_, _) => new Error("Invalid " + InstructionsEnum.Circle.toString + ": " + input);
    }
  }
}