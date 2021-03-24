package main.scala.models

class Line(_c1: Coordinate, _c2: Coordinate) extends Instruction {
  var c1 = _c1
  var c2 = _c2
}
object Line {
  def parse(input: String): Line ={
    // TODO: IMPLEMENT PARSING HERE
    new Line(new Coordinate(1,2), new Coordinate(3,4))
  }

}
