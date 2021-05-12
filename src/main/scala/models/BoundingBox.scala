package main.scala.models

import javafx.scene.canvas.GraphicsContext
import main.scala.drawer.CoordinateSystem

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success, Try}

class BoundingBox(_bottomLeft: Point, _upperRight: Point, _showGrid: Boolean) extends Figure {
  var bottomLeft = _bottomLeft;
  var upperRight = _upperRight;
  var showGrid = _showGrid;
  var height: Int = 0;
  var width: Int = 0;
  var xLines: Int = 0;
  var yLines: Int = 0;
  var xSpacing: Double = 0;
  var ySpacing: Double = 0;
  def this(){
    this(new Point(0,0), new Point(0,0), true);
  }

  def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] ={
    return null;
  }
}
object BoundingBox {
  def updateValues(_width: Int, _height: Int, _boundingBox: BoundingBox) : BoundingBox = {
    _boundingBox.width = _width;
    _boundingBox.height = _height;

    _boundingBox.xLines = (_boundingBox.upperRight.x - _boundingBox.bottomLeft.x).toInt;
    _boundingBox.yLines  = (_boundingBox.upperRight.y - _boundingBox.bottomLeft.y).toInt;

    _boundingBox.xSpacing  = ((_width.toDouble)/_boundingBox.xLines.toDouble) - 1 ;
    _boundingBox.ySpacing  = ((_height.toDouble/_boundingBox.yLines.toDouble)) - 1 ;

    return _boundingBox;
  }
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