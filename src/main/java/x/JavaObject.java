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

        Instruction[] x = interpreter.parse(  "(BOUNDING-BOX (0 0) (16 12))\n" +
                "(CIRCLE (12 12) 3)\n" +
                "(CIRCLE (7 8) 6)\n" +
                "(LINE (7 8) (12 12))");
    }
}