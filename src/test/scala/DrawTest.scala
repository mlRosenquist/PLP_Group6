
package test.scala

import main.scala.models.{Draw, Line, Point}
import main.scala.parser.Interpreter
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.mutable


class DrawTest extends AnyFlatSpec with Matchers  {

  "Inputting a valid draw" should "parse to a draw object" in {

    var input = "DRAW green LINE 7 8 12 12 RECTANGLE 1 1 2 2 CIRCLE 12 12 3 TEXT-AT 1 1 Hello";

    var draw = Draw.parse(input).asInstanceOf[Draw];

    Draw.parse(input).asInstanceOf[Draw] mustBe a[main.scala.models.Draw]

    draw.figures(0) mustBe a[main.scala.models.Line]
    draw.figures(1) mustBe a[main.scala.models.Rectangle]
    draw.figures(2) mustBe a[main.scala.models.Circle]
    draw.figures(3) mustBe a[main.scala.models.TextAt]
  }

  "Inputting an invalid line" should "parse to an error object" in {

    var input = "DRAW green LINE d 8 12 12 RECTANGLE 1 1 2 2 CIRCLE 12 12 3 TEXT-AT 1 1 Hello";

    Draw.parse(input) mustBe a[main.scala.models.Error]
  }
}