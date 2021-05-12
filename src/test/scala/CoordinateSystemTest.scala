
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
  var boundingBox = new BoundingBox(new Point(0,0), new Point(8,8), true);

  "Getting a point inside the bounding" should "give a valid pixel point" in {

    BoundingBox.updateValues(1000, 1000, boundingBox);
    var pixels = CoordinateSystem.getPixelsFromCoordinate(boundingBox, new Point(4,4));

    pixels should not be null

    pixels.x should be (496)
    pixels.y should be (503)
  }

  "Getting a point outside the bounding box" should "return null" in {

    BoundingBox.updateValues(1000, 1000, boundingBox);
    var pixels = CoordinateSystem.getPixelsFromCoordinate(boundingBox, new Point(9,9));

    pixels should be (null)
  }
}