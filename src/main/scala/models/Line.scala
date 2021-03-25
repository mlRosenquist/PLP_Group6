package main.scala.models

class Line(_c1: Point, _c2: Point) extends Instruction {
  var c1 = _c1
  var c2 = _c2
}
object Line {
  def parse(input: String): Line ={
    // TODO: IMPLEMENT PARSING HERE
    new Line(new Point(1,2), new Point(3,4))
  }

}
