package main.scala.drawer

import javafx.scene.canvas.{Canvas, GraphicsContext}
import javafx.scene.paint.Color
import main.scala.models.{Instruction, Line}

import scala.collection.mutable.ArrayBuffer

class Drawer(_gc: GraphicsContext) {
  var gc = _gc;

  def drawInstructions(_instructions: ArrayBuffer[Instruction]): Unit ={

    _instructions.foreach(i => {
      i.draw(gc);
    })
  }
}
