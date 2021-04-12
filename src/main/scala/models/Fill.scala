package main.scala.models

import java.awt.Color

class Fill(_figure: Figure, _color: Color) extends Miscellaneous {
  var figure = _figure;
  var color = _color;
}
object Fill {
  def parse(input: String): Fill ={
    // TODO: IMPLEMENT PARSING HERE
    new Fill(new Line(new Point(1,2), new Point(3,4)), Color.PINK);
  }
}