package main.scala.drawer

import javafx.embed.swing.SwingFXUtils
import javafx.scene.canvas.{Canvas, GraphicsContext}
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import main.scala.models.{BoundingBox, Instruction, Line, Point}

import java.awt.image.BufferedImage
import java.awt.color
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Drawer(_gc: ImageView) {
  var gc = _gc;
  var instructions: ArrayBuffer[Instruction] = ArrayBuffer();
  def drawInstructions(_paneWidth: Int, _paneHeight: Int,_boundingBox: BoundingBox, _instructions: ArrayBuffer[Instruction]): Unit ={
    val additional_lines = 1;
    val x_lines = _boundingBox.upperRight.x - _boundingBox.bottomLeft.x + additional_lines + 1;
    val y_lines = _boundingBox.upperRight.y - _boundingBox.bottomLeft.y + additional_lines + 1;
    val coordsPixelMap = mutable.Map[Point, Point]();
    var x_spacing = (_paneWidth.toDouble)/x_lines.toDouble;
    var y_spacing = (_paneHeight.toDouble/y_lines.toDouble);
    var bufferedImage = new BufferedImage(_paneWidth, _paneHeight, BufferedImage.TYPE_INT_RGB);
    val g = bufferedImage.createGraphics()

    // Set background
    g.setBackground(java.awt.Color.lightGray);
    g.clearRect(0, 0, _paneWidth, _paneHeight)

    // Draw horizontal lines
    for(i <- 0 to _paneHeight-1){
      for(j <- 1 to x_lines - 1 ) {
          bufferedImage.setRGB((x_spacing * j).toInt, i , 0)
      };
    }

    // Draw vertical lines
    for(i <- 0 to _paneWidth-1){
      for(j <- 1 to y_lines - 1 ) {
        bufferedImage.setRGB(i, (y_spacing * j).toInt , 0)
      };
    }

    // Map coords to pixels
    for(x <- _boundingBox.bottomLeft.x to _boundingBox.upperRight.x){
      for(y <- _boundingBox.bottomLeft.y to _boundingBox.upperRight.y){
        coordsPixelMap += (new Point(x,y) -> new Point((x_spacing*(x+1)).toInt, (y_spacing*(y+1)).toInt));
      }
    }

    instructions = instructions.concat(_instructions);
    instructions.foreach(i => i.draw(bufferedImage, coordsPixelMap));

    gc.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    gc.setScaleY(-1);
  }


}
