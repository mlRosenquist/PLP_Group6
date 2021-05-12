package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Error(_msg: String) extends Instruction {
  var msg = _msg;

  def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] ={
    return null;
  }
}
