package main.scala.models

import javafx.scene.canvas.GraphicsContext

import scala.util.{Failure, Success, Try}

class BoundingBox(_bottomLeft: Point, _upperRight: Point) extends Figure {
  var bottomLeft = _bottomLeft
  var upperRight = _upperRight

  def this(){
    this(new Point(0,0), new Point(0,0));
  }

  def draw(gc: GraphicsContext): Unit ={
  }
}
object BoundingBox {
  // (BOUNDING-BOX (0 0) (16 12))
  def parse(input: String): Instruction ={

    var splitInput = input.split(" ");

    var bottomLeft = Point.TryParse(splitInput(1), splitInput(2));

    var upperRight = Point.TryParse(splitInput(3), splitInput(4));

    (bottomLeft, upperRight) match {
      case (p1: Point, p2: Point) => new BoundingBox(p1, p2);
      case (_, _) => new Error("Invalid Bounding Box: " + input);
    }
  }



}