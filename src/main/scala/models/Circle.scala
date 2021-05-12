package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem
import main.scala.parser.InstructionsEnum

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.{Success, Try}

class Circle(_center: Point, _radius: Int) extends Figure {
  var center = _center;
  var radius = _radius;

  def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] ={
    var pixels = new ArrayBuffer[Point]();

    var centerPoint = CoordinateSystem.getPixelsFromCoordinate(_boundingBox, center);
    if(centerPoint == null) return pixels;

    var circleRadius = (radius * _boundingBox.xSpacing);
    return drawMidpointCircle(centerPoint.x, centerPoint.y, circleRadius)
  }
  // https://www.tutorialspoint.com/computer_graphics/circle_generation_algorithm.htm and https://www.thecrazyprogrammer.com/2016/12/bresenhams-midpoint-circle-algorithm-c-c.html
  def drawMidpointCircle(x0: Double, y0: Double, r: Double): ArrayBuffer[Point] ={
    var pixels = new ArrayBuffer[Point]();
    var x = r
    var y = 0.0
    var error = 0.0

    while (x >= y){

      pixels = pixels.concat(drawPoints(x0.toInt, y0.toInt, x.toInt, y.toInt));

      if(error <= 0){
        y += 1
        error += 2*y + 1
      }
      if (error > 0)
      {
        x -= 1;
        error -= 2*x + 1;
      }
    }
    return pixels;
  }

  def drawPoints(x0: Int, y0: Int, x: Int, y: Int): ArrayBuffer[Point] ={
    var pixels = new ArrayBuffer[Point]();
    pixels.addOne(new Point(x0 + x, y0 + y, this.color));
    pixels.addOne(new Point(x0 + y, y0 + x, this.color));
    pixels.addOne(new Point(x0 - y, y0 + x, this.color));
    pixels.addOne(new Point(x0 - x, y0 + y, this.color));
    pixels.addOne(new Point(x0 - x, y0 - y, this.color));
    pixels.addOne(new Point(x0 - y, y0 - x, this.color));
    pixels.addOne(new Point(x0 + y, y0 - x, this.color) );
    pixels.addOne(new Point(x0 + x, y0 - y, this.color));
    return pixels;
  }
}
object Circle {
  // (CIRCLE (12 12) 3)
  def parse(input: String): Instruction ={
    var splitInput = input.split(" ");

    var center = Point.TryParse(splitInput(1), splitInput(2));

    var radius = Try(splitInput(3).toInt);

    (center, Try(splitInput(3).toInt)) match {
      case (c: Point, Success(r)) => new Circle(c, r);
      case (_, _) => new Error("Invalid " + InstructionsEnum.Circle.toString + ": " + input);
    }
  }
}