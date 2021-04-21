package main.scala.models

import javafx.scene.canvas.GraphicsContext

abstract class Instruction {

  def draw(gc: GraphicsContext)
}
