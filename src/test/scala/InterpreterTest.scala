
package test.scala

import main.scala.models.Line
import main.scala.parser.Interpreter
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.mutable


class InterpreterTest extends AnyFlatSpec with Matchers  {
  var uut = new Interpreter();

  "Not starting with bounding box" should "create an error" in {

    var input = "(LINE (0 0) (8 8))";
    var instructions = uut.parse(input);

    instructions should have size 1
    instructions(0) mustBe a[main.scala.models.Error]
  }

  "Inputting a bounding box and a line" should "create return a bounding box and a line" in {

    var input = "(BOUNDING-BOX (0 0) (8 8))\n" +
      "(LINE (0 0) (8 8))";
    var instructions = uut.parse(input);

    instructions should have size 2
    instructions(0) mustBe a[main.scala.models.BoundingBox]
    instructions(1) mustBe a[main.scala.models.Line]

    var line = instructions(1).asInstanceOf[Line];

    line.start.x should be (0)
    line.start.y should be (0)

    line.end.x should be (8)
    line.end.y should be (8)
  }
}