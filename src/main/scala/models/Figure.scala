package main.scala.models

import javafx.scene.canvas.GraphicsContext

import java.awt.Color

abstract class Figure extends Instruction {
  var color = Color.BLACK;
  var fillColor = null;

}
