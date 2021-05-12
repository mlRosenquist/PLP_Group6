
package test.scala

import main.scala.models.{Line, Point}
import main.scala.parser.Interpreter
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.mutable


class LineTest extends AnyFlatSpec with Matchers  {
  var uut = new Line(new Point(1, 1), new Point(3,3));

  "Inputting a valid line" should "parse to a line object" in {

    var input = "LINE 0 0 8 8";

    var line = Line.parse(input).asInstanceOf[Line];

    line.start.x should be (0);
    line.start.y should be (0);

    line.end.x should be (8);
    line.end.y should be (8);
  }

  "Inputting an invalid line" should "parse to an error object" in {

    var input = "LINE d 0 8 8";

    Line.parse(input) mustBe a[main.scala.models.Error]
  }
}