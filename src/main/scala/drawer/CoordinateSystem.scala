package main.scala.drawer

import main.scala.models.{BoundingBox, Point}

import java.awt.Color
import java.awt.image.BufferedImage

class CoordinateSystem(_width: Int, _height: Int, _boundingBox: BoundingBox) {
  val x_lines = (_boundingBox.upperRight.x - _boundingBox.bottomLeft.x).toInt - 1;
  val y_lines = (_boundingBox.upperRight.y - _boundingBox.bottomLeft.y).toInt - 1;

  var x_spacing = ((_width.toDouble)/x_lines.toDouble) - 1 ;
  var y_spacing = ((_height.toDouble/y_lines.toDouble)) - 1 ;

  var bufferedImage = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);

  val g = bufferedImage.createGraphics()

  // Set background
  g.setBackground(java.awt.Color.lightGray);
  g.clearRect(0, 0, _width, _height)

  // Draw horizontal lines
  for(i <- (0 to _height-1)){
    for(j <- 0 to x_lines) {
      drawPixel((x_spacing * j).toInt , i , Color.BLACK);
    };
  }

  // Draw vertical lines
  for(i <- (0 to _width-1).reverse){
    for(j <- 0 to y_lines) {
      drawPixel(i, _height - (y_spacing * j).toInt -1, Color.BLACK)
    };
  }

  def drawPixel(x: Int, y: Int, color: Color): Unit ={
    if((x < 0 || x >= _width) || (y < 0 || y >= _height)){}
    else {
      bufferedImage.setRGB(x, y, color.getRGB());
    }
  }

  def getPixelsFromCoordinate(p: Point): Point ={
    var x_pixel = p.x * x_spacing;
    var y_pixel = _height - (p.y * y_spacing).toInt - 1;

    if(x_pixel >= _width || x_pixel < 0 || y_pixel >= _height || y_pixel < 0)
      return null;

    return new Point(x_pixel, y_pixel);
  }
}
