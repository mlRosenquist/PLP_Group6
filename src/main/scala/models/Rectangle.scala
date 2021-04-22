package main.scala.models

import javafx.scene.canvas.GraphicsContext

import java.awt.image.BufferedImage
import scala.collection.mutable

class Rectangle(_bottomLeft: Point, _upperRight: Point) extends Figure {
  var bottomLeft = _bottomLeft
  var upperRight = _upperRight

  def draw(image: BufferedImage, coordsMapping: mutable.Map[Point, Point]): Unit ={
    var l1 = new Line(new Point(bottomLeft.x, bottomLeft.y), new Point(bottomLeft.x, upperRight.y));
    var l2 = new Line(new Point(bottomLeft.x, upperRight.y), new Point(upperRight.x, upperRight.y));
    var l3 = new Line(new Point(upperRight.x, upperRight.y), new Point(upperRight.x, bottomLeft.y));
    var l4 = new Line(new Point(upperRight.x, bottomLeft.y), new Point(bottomLeft.x, bottomLeft.y));

    l1.draw(image, coordsMapping);
    l2.draw(image, coordsMapping);
    l3.draw(image, coordsMapping);
    l4.draw(image, coordsMapping);
  }
}
object Rectangle {
  def parse(input: String): Instruction ={
    var splitInput = input.split(" ");

    var bottomLeft = Point.TryParse(splitInput(1), splitInput(2));

    var upperRight = Point.TryParse(splitInput(3), splitInput(4));

    (bottomLeft, upperRight) match {
      case (p1: Point, p2: Point) => new Rectangle(p1, p2);
      case (_, _) => new Error("Invalid Rectangle: " + input);
    }
  }
}