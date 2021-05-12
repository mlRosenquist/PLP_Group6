package main.scala.models
import scala.math.abs

import scala.collection.mutable.ArrayBuffer

class Line(_start: Point, _end: Point) extends Figure {
  val start: Point = _start
  val end: Point = _end

   def draw(_boundingBox: BoundingBox): ArrayBuffer[Point] = {
     var pixels = new ArrayBuffer[Point]();

     val dx = math.abs(end.x - start.x) * _boundingBox.xSpacing
     val dy = math.abs(end.y - start.y) * _boundingBox.ySpacing

     val sx = if (start.x < end.x) 1 else -1
     val sy = if (start.y < end.y) 1 else -1

     def it: Iterator[(Double, Double)] = new Iterator[(Double, Double)] {
       var x: Double = start.x
       var y: Double = start.y
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

       def hasNext: Boolean = (sx*x <= sx*end.x && sy*y <= sy*end.y)
     }

     for ((x,y) <- it) {
       //pixels.addOne(new Point(x, y, this.color))
       pixels = pixels.concat(drawPoints(start.x.toInt, start.y.toInt, x.toInt, y.toInt))
     }

     pixels
  }

  def drawPoints(x0: Int, y0: Int, x: Int, y: Int): ArrayBuffer[Point] ={
    val pixels = new ArrayBuffer[Point]()
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
