package main.scala.drawer

import javafx.embed.swing.SwingFXUtils
import javafx.scene.canvas.{Canvas, GraphicsContext}
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import main.scala.models.{BoundingBox, Instruction, Line, Point, TextAt}

import java.awt.image.BufferedImage
import java.awt.color
import java.awt.geom.AffineTransform
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Drawer {
  def getInitPixels(_boundingBox: BoundingBox): ArrayBuffer[Point] ={
    return CoordinateSystem.getLines(_boundingBox);;
  }

  def getDrawingPixels(_boundingBox: BoundingBox, _newInstructions: ArrayBuffer[Instruction], _oldInstructions: ArrayBuffer[Instruction]): ArrayBuffer[Point] = {
    var pixels: ArrayBuffer[Point] = new ArrayBuffer[Point]();

    _oldInstructions.foreach(i => {
      if(i.color == java.awt.Color.CYAN){
        i.color = java.awt.Color.BLACK;
      }
    });

    _newInstructions.foreach(i => {
      if(i.color == java.awt.Color.BLACK){
        i.color = java.awt.Color.CYAN;
      }
    });

    _oldInstructions.foreach(i => pixels = pixels.concat(i.draw(_boundingBox)));
    _newInstructions.foreach(i => pixels = pixels.concat(i.draw(_boundingBox)));

    return pixels;
  }


}
