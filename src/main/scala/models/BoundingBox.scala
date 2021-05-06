package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class BoundingBox(_bottomLeft: Point, _upperRight: Point, _showGrid: Boolean) extends Figure {
  var bottomLeft = _bottomLeft;
  var upperRight = _upperRight;
  var showGrid = _showGrid;
  def this(){
    this(new Point(0,0), new Point(0,0), true);
  }

  def draw(_coordinateSystem: CoordinateSystem): Unit ={
  }
}
object BoundingBox {
  // (BOUNDING-BOX (0 0) (16 12))
  def parse(input: String): Instruction ={

    var splitInput = input.split(" ");

    var bottomLeft = Point.TryParse(splitInput(1), splitInput(2));

    var upperRight = Point.TryParse(splitInput(3), splitInput(4));

    var showGrid: Boolean = true;
    if(splitInput.length >= 6){
      splitInput(5) match {
        case "F" => showGrid = false;
        case _ => showGrid = true;
      }
    }
    (bottomLeft, upperRight) match {
      case (p1: Point, p2: Point) => new BoundingBox(p1, p2, showGrid);
      case (_, _) => new Error("Invalid Bounding Box: " + input);
    }
  }



}