package main.scala.models
import main.scala.drawer.CoordinateSystem

import scala.collection.mutable.ArrayBuffer

class Line(_start: Point, _end: Point) extends Figure {
  val start: Point = _start
  val end: Point = _end

   def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] = {
     var pixels = new ArrayBuffer[Point]()

     val p0 = CoordinateSystem.getPixelsFromCoordinate(_boundingBox, start)
     val p1 = CoordinateSystem.getPixelsFromCoordinate(_boundingBox, end)

     val dx = math.abs(p1.x - p0.x) * _boundingBox.xSpacing
     val dy = math.abs(p1.y - p0.y) * _boundingBox.ySpacing

     val sx = if (p0.x < p1.x) 1 else -1
     val sy = if (p0.y < p1.y) 1 else -1

     def it: Iterator[(Double, Double)] = new Iterator[(Double, Double)] {
       var x: Double = p0.x
       var y: Double = p0.y
       var err: Double = (if (dx > dy) dx else -dy)/2

       def next: (Double, Double) = {
         val res = (x, y)
         val e2 = err
         if (e2 > -dx) {
           err -=dy
           x += sx
         }

         if (e2 < dy) {
           err += dx
           y += sy
         }
         res
       }

       def hasNext: Boolean = sx*x <= sx*p1.x && sy*y <= sy*p1.y
     }

     for ((x,y) <- it) {
       pixels = pixels.addOne(new Point(x, y, this.color))
     }

     pixels
  }
}

object Line {
  def parse(input: String): Instruction ={
    val splitInput = input.split(" ")
    val start = Point.TryParse(splitInput(1), splitInput(2))
    val end = Point.TryParse(splitInput(3), splitInput(4))

    (start, end) match {
      case (p1: Point, p2: Point) => new Line(p1, p2);
      case (_, _) => new Error("Invalid Line: " + input);
    }
  }
}
