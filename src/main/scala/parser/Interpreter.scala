package main.scala.parser

import main.scala.models._


object InstructionsEnum extends Enumeration{
  type InstructionsEnum = Value

  val Line = Value("LINE")
  val Rectangle = Value("RECTANGLE")
  val Circle = Value("CIRCLE")
  val TextAt = Value("TEXT-AT")
  val BoundingBox = Value("BOUNDING-BOX")
  val Draw = Value("DRAW")
  val Fill = Value("FILL")
}
class Interpreter {

  def parse(instructions: String) : Array[Instruction] = {

    var instructions_split = instructions.split("\n")

    return instructions_split
        // Remove Brackets
        .mapInPlace(i => i.replace("(", "").replace(")", ""))
        // Map each instruction to an instruction
        .map(i =>
            i match {
              case s if s.startsWith(InstructionsEnum.BoundingBox.toString) => {
                BoundingBox.parse(s)
                }
              case s if s.startsWith(InstructionsEnum.Line.toString) => {
                Line.parse(s)
              }
              case s if s.startsWith(InstructionsEnum.Circle.toString) => {
                Circle.parse(s)
              }
              case s if s.startsWith(InstructionsEnum.Rectangle.toString) => {
                Rectangle.parse(s)
              }
              case s if s.startsWith(InstructionsEnum.TextAt.toString) => {
                TextAt.parse(s)
              }
              case s if s.startsWith(InstructionsEnum.Draw.toString) => {
                Draw.parse(s)
              }
              case s if s.startsWith(InstructionsEnum.Fill.toString) => {
                Fill.parse(s)
              }
              case default => {
                new Error()
              }
            })
  }
}
