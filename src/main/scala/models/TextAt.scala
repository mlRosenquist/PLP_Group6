package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class TextAt(_placement: Point, _text: String) extends Figure {
  var placement = _placement;
  var text = _text;

  def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] ={
    return null;
  }

  def drawString(graphics2D: Graphics2D, boundingBox: BoundingBox) ={
    val p = CoordinateSystem.getPixelsFromCoordinate(boundingBox, placement);
    if(p != null) {
      graphics2D.setColor(this.color);
      graphics2D.drawString(this.text, p.x.toInt, p.y.toInt);
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