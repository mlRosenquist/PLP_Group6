package main.scala.models

import javafx.scene.canvas.GraphicsContext

class TextAt(_placement: Point, _text: String) extends Miscellaneous {
  var placement = _placement;
  var text = _text;

  def draw(gc: GraphicsContext): Unit ={
  }
}
object TextAt {
  def parse(input: String): Instruction ={
    var splitInput = input.split(" ");
    var placement = Point.TryParse(splitInput(1), splitInput(2));
    var text = splitInput(3);

    (placement, text) match {
      case (p1: Point, t: String) => new TextAt(p1, t);
      case (_, _) => new Error("Invalid TextAt: " + input);
    }
  }
}