package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.image.BufferedImage
import scala.collection.mutable

class Error(_msg: String) extends Instruction {
  var msg = _msg;

  def draw(_coordinateSystem: CoordinateSystem): Unit ={
  }
}
