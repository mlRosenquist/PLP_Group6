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

class Drawer(_imageView: ImageView, _paneWidth: Int, _paneHeight: Int,_boundingBox: BoundingBox) {
  var imageView = _imageView;
  var instructions: ArrayBuffer[Instruction] = ArrayBuffer();
  var coordinateSystem: CoordinateSystem = new CoordinateSystem(_paneWidth, _paneHeight, _boundingBox);

  imageView.setImage(SwingFXUtils.toFXImage(coordinateSystem.bufferedImage, null));

  def drawInstructions(_paneWidth: Int, _paneHeight: Int,_boundingBox: BoundingBox, _instructions: ArrayBuffer[Instruction]): Unit ={
    if(coordinateSystem.equals(null))
      coordinateSystem = new CoordinateSystem(_paneWidth, _paneHeight, _boundingBox);

    instructions = instructions.concat(_instructions);

    instructions.foreach(i => i.draw(coordinateSystem));

    imageView.setImage(SwingFXUtils.toFXImage(coordinateSystem.bufferedImage, null));
  }
}