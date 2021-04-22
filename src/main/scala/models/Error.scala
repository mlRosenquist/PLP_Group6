package main.scala.models

import javafx.scene.canvas.GraphicsContext

import java.awt.image.BufferedImage
import scala.collection.mutable

class Error(_msg: String) extends Instruction {
  var msg = _msg;

  def draw(image: BufferedImage, coordsMapping: mutable.Map[Point, Point]): Unit ={
  }
}
