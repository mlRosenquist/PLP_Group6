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
  var oldInstructions: ArrayBuffer[Instruction] = ArrayBuffer();
  var coordinateSystem: CoordinateSystem = new CoordinateSystem(_paneWidth, _paneHeight, _boundingBox);

  imageView.setImage(SwingFXUtils.toFXImage(coordinateSystem.bufferedImage, null));

  def drawInstructions(_paneWidth: Int, _paneHeight: Int,_boundingBox: BoundingBox, _newInstructions: ArrayBuffer[Instruction]): Unit ={
    coordinateSystem = new CoordinateSystem(_paneWidth, _paneHeight, _boundingBox);

    oldInstructions.foreach(i => {
      if(i.color == java.awt.Color.CYAN){
        i.color = java.awt.Color.BLACK;
      }
    }
      );
    _newInstructions.foreach(i => {
      if(i.color == java.awt.Color.BLACK){
        i.color = java.awt.Color.CYAN;
      }
    });

    oldInstructions = oldInstructions.concat(_newInstructions);
    oldInstructions.foreach(i => i.draw(coordinateSystem));

    // Draw Text at origin
    var origin = new TextAt(_boundingBox.bottomLeft,"(" + _boundingBox.bottomLeft.x.toInt + "," + _boundingBox.bottomLeft.y.toInt + ")");
    origin.draw(coordinateSystem);
    imageView.setImage(SwingFXUtils.toFXImage(coordinateSystem.bufferedImage, null));
  }
}
