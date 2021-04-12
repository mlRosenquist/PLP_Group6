package main.scala.models

import java.awt.Color

class Draw(_figures: Array[Figure], _color: Color) extends Miscellaneous {
  var figures = _figures;
  var color = _color;
}
object Draw {
  def parse(input: String): Draw ={
    // TODO: IMPLEMENT PARSING HERE
    var x = new Array[Figure](5);
    new Draw(x, Color.PINK);
  }
}