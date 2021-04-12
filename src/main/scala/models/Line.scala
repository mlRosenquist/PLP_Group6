package main.scala.models

class Line(_start: Point, _end: Point) extends Figure {
  var start = _start
  var end = _end
}
object Line {
  def parse(input: String): Line ={
    // TODO: IMPLEMENT PARSING HERE
    new Line(new Point(1,2), new Point(3,4))
  }

}
