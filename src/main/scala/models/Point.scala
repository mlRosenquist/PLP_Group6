package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success, Try}

class Point(_x: Double, _y: Double) extends Instruction {
  var x = _x
  var y = _y
  def this(){
    this(0,0);
  }
  def this(_x: Double, _y: Double, _color: Color){
    this(_x, _y);
    this.color = _color;
  }

  def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] ={
    return null;
  }

  def mapCoordPointToPixel(x_pixels: Int, y_pixels: Int, x_spacing: Double, y_spacing: Double) : Point  = {
    var x_pixel = x * x_spacing;
    var y_pixel = y * y_spacing;
    new Point(x_pixel, y_pixel);
  }
}

object Point{
  def TryParse (_x: String, _y: String): Instruction = {
    Try(_x.toInt) match {
      case Success(x) => {
        Try(_y.toInt) match {
          case Success(y) => new Point(x, y);
          case Failure(err) => new Error("Invalid Point: (" + _x + "," + _y+")");
        }
      }
      case Failure(err) => new Error("Invalid Point: (" + _x + "," + _y+")");
    }
  }
}
