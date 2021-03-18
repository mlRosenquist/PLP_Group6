package main.java.x;

import main.java.y.ScalaObject;

public class JavaObject {
    public void sayHello() {
        System.out.println("Hi from Java!");
    }
    // sbt -jvm-debug 5005
    public static void main(String [] args) {
        JavaObject javaObject = new JavaObject();
        ScalaObject scalaObject = new ScalaObject();
        javaObject.sayHello();
        scalaObject.sayHello();
    }
}