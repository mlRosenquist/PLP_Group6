package main.scala.models

import javafx.scene.canvas.GraphicsContext

import java.awt.image.BufferedImage
import scala.collection.mutable
import main.scala.drawer.CoordinateSystem
abstract class Instruction {

  def draw(_coordinateSystem: CoordinateSystem): Unit
}
