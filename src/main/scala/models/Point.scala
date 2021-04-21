package main.scala.models

import javafx.scene.canvas.GraphicsContext

import scala.util.{Failure, Success, Try}

class Point(_x: Int, _y: Int) extends Instruction {
  var x = _x
  var y = _y
  def this(){
    this(0,0);
  }

  def draw(gc: GraphicsContext): Unit ={
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
