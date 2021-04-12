package main.scala.models

class Rectangle(_bottomLeft: Point, _upperRight: Point) extends Figure {
  var bottomLeft = _bottomLeft
  var upperRight = _upperRight
}
object Rectangle {
  def parse(input: String): Rectangle ={
    // TODO: IMPLEMENT PARSING HERE
    new Rectangle(new Point(1,2), new Point(3,4));
  }
}