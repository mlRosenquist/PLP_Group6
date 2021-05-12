package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem
import main.scala.parser.InstructionsEnum

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Fill(_figure: Instruction, _color: Color) extends Miscellaneous {
  var figure = _figure;
  this.color = _color;

  def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] ={
    var pixels = new ArrayBuffer[Point]();

    figure match {
      case (rec: Rectangle) => {
        var pix_botLeft = CoordinateSystem.getPixelsFromCoordinate(_boundingBox, rec.bottomLeft);
        var pix_upRight = CoordinateSystem.getPixelsFromCoordinate(_boundingBox, rec.upperRight);

        (pix_botLeft, pix_upRight) match {
          case (p1: Point, p2: Point) => {
            for(i <- (pix_botLeft.x.toInt + 1  to pix_upRight.x.toInt - 1)){
              for(j <- (pix_upRight.y.toInt +1 to pix_botLeft.y.toInt - 1)){
                pixels.addOne(new Point(i, j, this.color));
              }
            }
            return pixels;
          }
          case (null, p2: Point) => {
            for(i <- (1 to pix_upRight.x.toInt - 1)){
              for(j <- (pix_upRight.y.toInt +1 to _boundingBox.height-2)){
                pixels.addOne(new Point(i, j, this.color));
              }
            }
            return pixels;
          }
          case (p1: Point, null) => {
            for(i <- (pix_botLeft.x.toInt + 1 to _boundingBox.width)){
              for(j <- (0 to pix_botLeft.y.toInt -1)){
                pixels.addOne(new Point(i, j, this.color));
              }
            }
            return pixels;
          }
          case (_, _) => return pixels;
        }

      }
      case (cir: Circle) => {
        var pix_center = CoordinateSystem.getPixelsFromCoordinate(_boundingBox, cir.center);

        var pix_center_moved_radius = CoordinateSystem.getPixelsFromCoordinate(_boundingBox, new Point(cir.center.x+ cir.radius, cir.center.y));

        var pix_x_radius = (cir.radius * _boundingBox.xSpacing);
        var pix_y_radius = (cir.radius * _boundingBox.ySpacing);

        var pix_x_min = pix_center.x - pix_x_radius;
        var pix_x_max = pix_center.x + pix_x_radius;

        var pix_y_min = pix_center.y - pix_y_radius;
        var pix_y_max = pix_center.y + pix_y_radius;


        for( y <- (-pix_x_radius.toInt to pix_x_radius.toInt)){
          for(x <- (-pix_x_radius.toInt to pix_x_radius.toInt)){
            if(x*x+y*y <= pix_x_radius*pix_x_radius+pix_x_radius)
              pixels.addOne(new Point(x+pix_center.x.toInt, y+pix_center.y.toInt, this.color));
          }
        }
        return pixels;
      }
      case _ => return pixels;
    }

  }
}
object Fill {
  def parse(input: String): Instruction ={
    try {
      var splitInput = input.split(" ");
      var field = Class.forName("java.awt.Color").getField(splitInput(1));
      var color = field.get(null).asInstanceOf[Color]

      return splitInput(2) match {
        case s if s.startsWith(InstructionsEnum.Circle.toString) => new Fill(Circle.parse(s + " " + splitInput(3) + " " + splitInput(4) + " " + splitInput(5) ), color);
        case s if s.startsWith(InstructionsEnum.Rectangle.toString) => new Fill(Rectangle.parse(s + " " + splitInput(3) + " " + splitInput(4) + " " + splitInput(5) + " " + splitInput(6) ), color);
        case _ => new Error("Invalid Fill: " + input)
      }

    } catch {
      case e: Exception =>
        new Error("Invalid Draw: " + input)
    }
  }
}