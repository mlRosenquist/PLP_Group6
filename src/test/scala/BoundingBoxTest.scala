
package test.scala

import main.scala.models.{BoundingBox, Line, Point}
import main.scala.parser.Interpreter
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.mutable


class BoundingBoxTest extends AnyFlatSpec with Matchers  {
  var uut = new BoundingBox(new Point(0, 0), new Point(8,8), true);

  "Inputting a valid BoundingBox" should "parse to a BoundingBox object" in {

    var input = "BOUNDING-BOX 0 0 8 8";

    var bb = BoundingBox.parse(input).asInstanceOf[BoundingBox];

    bb.bottomLeft.x should be (0);
    bb.bottomLeft.y should be (0);

    bb.upperRight.x should be (8);
    bb.upperRight.y should be (8);
    bb.showGrid should be (true);
  }

  "Inputting an invalid BoundingBox" should "parse to an error object" in {

    var input = "BOUNDING-BOX 1 2 3 c";

    Line.parse(input) mustBe a[main.scala.models.Error]
  }

  "Inputting a BoundingBox with hidden grid" should "parse to a bounding box with a hidden grid" in {

    var input = "BOUNDING-BOX 0 0 8 8 F";

    var bb = BoundingBox.parse(input).asInstanceOf[BoundingBox];

    bb.showGrid should be (false);
  }
}