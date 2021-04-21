package main.scala.models

import javafx.scene.canvas.GraphicsContext

class Rectangle(_bottomLeft: Point, _upperRight: Point) extends Figure {
  var bottomLeft = _bottomLeft
  var upperRight = _upperRight

  def draw(gc: GraphicsContext): Unit ={
    gc.strokeRect(bottomLeft.x, bottomLeft.y, upperRight.x, upperRight.y);
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