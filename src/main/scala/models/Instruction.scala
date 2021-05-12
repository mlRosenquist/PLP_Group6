package main.scala.models

import javafx.scene.canvas.GraphicsContext

import java.awt.image.BufferedImage
import scala.collection.mutable
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import scala.collection.mutable.ArrayBuffer
abstract class Instruction {
  var color : Color = Color.BLACK;
  def draw(_boundingBox: BoundingBox): ArrayBuffer[Point]
}
