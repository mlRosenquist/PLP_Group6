package main.scala.models

import javafx.scene.canvas.GraphicsContext

import java.awt.image.BufferedImage
import scala.collection.mutable

abstract class Instruction {

  def draw(image: BufferedImage, coordsMapping: mutable.Map[Point, Point]): Unit
}
