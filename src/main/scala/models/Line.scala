package main.scala.models
import main.scala.RgbBitmap.RgbBitmap
import main.scala.drawer.CoordinateSystem

import scala.collection.mutable.ArrayBuffer

class Line(_start: Point, _end: Point) extends Figure {
  val start: Point = _start
  val end: Point = _end

   def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] = {
     var pixels = new ArrayBuffer[Point]();

     return pixels;
  }
}

object Line {
  def parse(input: String): Instruction ={
    val splitInput = input.split(" ")
    val start = Point.TryParse(splitInput(1), splitInput(2))
    val end = Point.TryParse(splitInput(3), splitInput(4))

    (start, end) match {
        //TODO: Reference the bounding box size for RgbBitmap
      case (p1: Point, p2: Point) => new Line(p1, p2);
      case (_, _) => new Error("Invalid Line: " + input);
    }
  }
}
