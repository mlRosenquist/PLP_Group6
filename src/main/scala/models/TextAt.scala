package main.scala.models

class TextAt(_placement: Point, _text: String) extends Miscellaneous {
  var placement = _placement;
  var text = _text;
}
object TextAt {
  def parse(input: String): TextAt ={
    // TODO: IMPLEMENT PARSING HERE
    new TextAt(new Point(1,2), "Hello");
  }
}