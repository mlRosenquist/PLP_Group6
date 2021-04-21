package main.scala.models

import javafx.scene.canvas.GraphicsContext

class Error(_msg: String) extends Instruction {
  var msg = _msg;

  def draw(gc: GraphicsContext): Unit ={
  }
}
