
package test.scala

import main.scala.drawer.CoordinateSystem
import main.scala.models.{BoundingBox, Line, Point}
import main.scala.parser.Interpreter
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.mutable


class CoordinateSystemTest extends AnyFlatSpec with Matchers  {
  var uut = new CoordinateSystem(1000, 1000, new BoundingBox(new Point(0,0), new Point(8,8)));

  "Getting a point inside the bounding" should "give a valid pixel point" in {

    var pixels = uut.getPixelsFromCoordinate(new Point(4,4));

    pixels should not be null

    pixels.x should be (496)
    pixels.y should be (503)
  }

  "Getting a point outside the bounding box" should "return null" in {

    var pixels = uut.getPixelsFromCoordinate(new Point(9,9));

    pixels should be (null)
  }
}