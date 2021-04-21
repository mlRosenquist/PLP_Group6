package main.scala.models
import main.scala.RgbBitmap.RgbBitmap

class Line(_start: Point, _end: Point, bm:RgbBitmap) extends Figure {
  val start: Point = _start
  val end: Point = _end

  def draw(start: Point, end: Point): Iterator[(Int, Int)] = {
    import scala.math.abs

    val dx = abs(end.x - start.x)
    val dy = abs(end.y - start.y)

    val sx = if (start.x < end.x) 1 else -1
    val sy = if (start.y < end.y) 1 else -1

    new Iterator[(Int, Int)] {
      var (x, y) = (start.x, start.y)
      var err: Int = dx - dy

      def next: (Int, Int) = {
        val omitted = (x, y)
        val e2 = 2 * err
        if (e2 > -dy) {
          err -= dy
          x += sx
        }
        omitted
      }

      def hasNext: Boolean = sx*x <= sx*end.x && sy*y <= sy*end.y
    }
  }


}
object Line {
  def parse(input: String): Instruction ={
    val splitInput = input.split(" ")
    val start = Point.TryParse(splitInput(1), splitInput(2))
    val end = Point.TryParse(splitInput(3), splitInput(4))

    (start, end) match {
        //TODO: Reference the bounding box size for RgbBitmap
      case (p1: Point, p2: Point) => new Line(p1, p2, new RgbBitmap(100,100));
      case (_, _) => new Error("Invalid Line: " + input);
    }
  }

}
