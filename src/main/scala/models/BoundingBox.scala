package main.scala.models

import scala.util.{Failure, Success, Try}

class BoundingBox(_bottomLeft: Point, _upperRight: Point) extends Figure {
  var bottomLeft = _bottomLeft
  var upperRight = _upperRight

  def this(){
    this(new Point(0,0), new Point(0,0));
  }
}
object BoundingBox {
  // (BOUNDING-BOX (0 0) (16 12))
  def parse(input: String): Instruction ={
    // TODO: IMPLEMENT PARSING HERE
    var splitInput = input.split(" ");

    Validator.isInt()
    var bottomLeft = Try(splitInput(1).toInt) match {
      case Success(x) => {
        Try(splitInput(2).toInt) match {
          case Success(y) => new Point(x, y);
          case Failure(err) => new Error();
        }
      }
      case Failure(err) => new Error();
    }

    var upperRight = Try(splitInput(3).toInt) match {
      case Success(x) => {
        Try(splitInput(4).toInt) match {
          case Success(y) => new Point(x, y);
          case Failure(err) => new Error();
        }
      }
      case Failure(err) => new Error();
    }
    bottomLeft match {
      case (p1: Point) => upperRight match {
        case (p2: Point) => new BoundingBox(p1, p2);
        case (error: Error) => error;
      }
      case (error: Error) => error;
    }
  }



}