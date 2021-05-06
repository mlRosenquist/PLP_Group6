package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem
import main.scala.parser.InstructionsEnum

import java.awt.Color
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.util.{Success, Try}

class Circle(_center: Point, _radius: Int) extends Figure {
  var center = _center;
  var radius = _radius;

  def draw(_coordinateSystem: CoordinateSystem): Unit ={
    var centerPoint = _coordinateSystem.getPixelsFromCoordinate(center);
    var circleRadius = (radius * _coordinateSystem.x_spacing)
    drawMidpointCircle(centerPoint.x, centerPoint.y, circleRadius, _coordinateSystem)
  }
  // https://www.tutorialspoint.com/computer_graphics/circle_generation_algorithm.htm and https://www.thecrazyprogrammer.com/2016/12/bresenhams-midpoint-circle-algorithm-c-c.html
  def drawMidpointCircle(x0: Double, y0: Double, r: Double, coordinateSystem: CoordinateSystem): Unit ={
    var x = r
    var y = 0.0
    var error = 0.0

    while (x >= y){

      drawPoints(x0.toInt, y0.toInt, x.toInt, y.toInt, coordinateSystem)

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
  }

  def drawPoints(x0: Int, y0: Int, x: Int, y: Int, coordinateSystem: CoordinateSystem): Unit ={
    coordinateSystem.drawPixel(x0 + x, y0 + y, Color.BLACK)
    coordinateSystem.drawPixel(x0 + y, y0 + x, Color.BLACK)
    coordinateSystem.drawPixel(x0 - y, y0 + x, Color.BLACK)
    coordinateSystem.drawPixel(x0 - x, y0 + y, Color.BLACK)
    coordinateSystem.drawPixel(x0 - x, y0 - y, Color.BLACK)
    coordinateSystem.drawPixel(x0 - y, y0 - x, Color.BLACK)
    coordinateSystem.drawPixel(x0 + y, y0 - x, Color.BLACK)
    coordinateSystem.drawPixel(x0 + x, y0 - y, Color.BLACK)

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