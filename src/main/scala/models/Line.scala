package main.scala.models

class Line(_start: Point, _end: Point) extends Figure {
  var start = _start
  var end = _end
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
