package main.scala.models

class Circle(_center: Point, _radius: Int) extends Figure {
  var center = _center;
  var radius = _radius;
}
object Circle {
  def parse(input: String): Circle ={
    // TODO: IMPLEMENT PARSING HERE
    new Circle(new Point(1,2), 5)
  }

}