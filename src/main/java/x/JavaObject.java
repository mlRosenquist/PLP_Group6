package main.java.x;

import main.scala.models.Instruction;
import main.scala.parser.Interpreter;
import main.scala.x.ScalaObject;

public class JavaObject {
    public void sayHello() {
        System.out.println("Hi from Java!");
    }
    // sbt -jvm-debug 5005
    public static void main(String [] args) {
        Interpreter interpreter = new Interpreter();

        Instruction[] x = interpreter.parse(
                "(LINE (7 8) (12 12))\n" +
                "(RECTANGLE (1 1) (2 2))\n" +
                "(CIRCLE (12 12) 3)\n" +
                "(TEXT-AT (1 1) Hello)\n" +
                "(BOUNDING-BOX (0 0) (16 12))\n" +
                "(BOUNDING-BOX (d 0) (16 12))\n" +
                "(DRAW green (LINE (7 8) (12 12)) (LINE (7 8) (12 12)) (LINE (7 8) (12 12)))\n" +
                "(FILL red (LINE (7 8) (12 12))) " );
    }
}