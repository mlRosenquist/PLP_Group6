package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable

class Rectangle(_bottomLeft: Point, _upperRight: Point) extends Figure {
  var bottomLeft = _bottomLeft
  var upperRight = _upperRight

  def draw(_coordinateSystem: CoordinateSystem): Unit ={
    var l1 = new Line(new Point(bottomLeft.x, bottomLeft.y), new Point(bottomLeft.x, upperRight.y));
    var l2 = new Line(new Point(bottomLeft.x, upperRight.y), new Point(upperRight.x, upperRight.y));
    var l3 = new Line(new Point(upperRight.x, upperRight.y), new Point(upperRight.x, bottomLeft.y));
    var l4 = new Line(new Point(upperRight.x, bottomLeft.y), new Point(bottomLeft.x, bottomLeft.y));

    l1.draw(_coordinateSystem);
    l2.draw(_coordinateSystem);
    l3.draw(_coordinateSystem);
    l4.draw(_coordinateSystem);
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