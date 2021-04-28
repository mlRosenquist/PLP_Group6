package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable

class Line(_start: Point, _end: Point) extends Figure {
  var start = _start
  var end = _end

  def draw(_coordinateSystem: CoordinateSystem): Unit ={

    var p1 = _coordinateSystem.getPixelsFromCoordinate(start);
    var p2 = _coordinateSystem.getPixelsFromCoordinate(end);
  }
}
object Line {
  def parse(input: String): Instruction ={
    var splitInput = input.split(" ");
    var start = Point.TryParse(splitInput(1), splitInput(2));
    var end = Point.TryParse(splitInput(3), splitInput(4));

    (start, end) match {
      case (p1: Point, p2: Point) => new Line(p1, p2);
      case (_, _) => new Error("Invalid Line: " + input);
    }
  }
}
