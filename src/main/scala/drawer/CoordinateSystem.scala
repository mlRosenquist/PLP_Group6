package main.scala.drawer

import main.scala.models.{BoundingBox, Point, TextAt}

import java.awt.Color
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer

object CoordinateSystem{
  def getLines(_boundingBox: BoundingBox) : ArrayBuffer[Point] = {
    var pixels: ArrayBuffer[Point] = new ArrayBuffer[Point]();
    if(_boundingBox.showGrid) {

      // Draw horizontal lines
      for (i <- (0 to _boundingBox.height - 1)) {
        for (j <- 0 to _boundingBox.xLines) {
          val pixel = new Point((_boundingBox.xSpacing * j).toInt, i);
          if(isPixelValid(_boundingBox.width, _boundingBox.height, pixel))
            pixels.addOne(pixel);
        };
      }

      // Draw vertical lines
      for (i <- (0 to _boundingBox.width - 1).reverse) {
        for (j <- 0 to _boundingBox.yLines) {
          val pixel = new Point(i, _boundingBox.height - (_boundingBox.ySpacing * j).toInt - 1);
          if(isPixelValid(_boundingBox.width, _boundingBox.height, pixel))
            pixels.addOne(pixel);
        };
      }
    }
    return pixels;
  }

  def isPixelValid(_width: Int, _height: Int, _pixel: Point): Boolean ={
    return !((_pixel.x < 0 || _pixel.x >= _width) || (_pixel.y < 0 || _pixel.y >= _height));
  }

  def  getPixelsFromCoordinate(_boundingBox: BoundingBox, p: Point): Point = {

    var x_pixel = (p.x - _boundingBox.bottomLeft.x) * _boundingBox.xSpacing;
    var y_pixel = _boundingBox.height - ((p.y-_boundingBox.bottomLeft.y) * _boundingBox.ySpacing).toInt - 1;

    if(x_pixel >= _boundingBox.width || x_pixel < 0 || y_pixel >= _boundingBox.height || y_pixel < 0)
      return null;

    return new Point(x_pixel, y_pixel);
  }
}
