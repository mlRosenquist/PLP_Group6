package main.scala.models

import javafx.scene.canvas.GraphicsContext

import java.awt.image.BufferedImage
import scala.collection.mutable

class Line(_start: Point, _end: Point) extends Figure {
  var start = _start
  var end = _end

  def draw(image: BufferedImage, coordsMapping: mutable.Map[Point, Point]): Unit ={

    var graphics = image.createGraphics();
    var x = coordsMapping.find((coords) => coords._1.x.equals(start.x) && coords._1.y.equals(start.y)).get
    var y = coordsMapping.find((coords) => coords._1.x.equals(end.x)  && coords._1.y.equals(end.y)).get
    graphics.drawLine(x._2.x, x._2.y, y._2.x, y._2.y);
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
