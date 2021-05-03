package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable

class TextAt(_placement: Point, _text: String) extends Figure {
  var placement = _placement;
  var text = _text;

  def draw(_coordinateSystem: CoordinateSystem): Unit ={
    val g = _coordinateSystem.bufferedImage.createGraphics();
    g.setColor(this.color);
    val p = _coordinateSystem.getPixelsFromCoordinate(placement);
    if(p != null) {
      g.drawString(text, p.x.toInt, p.y.toInt)
    };
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