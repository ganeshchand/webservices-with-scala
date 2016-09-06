import org.specs2.Specification

/**
  * Created by ganeshchand on 9/5/16.
  */
class HelloWorldSpec extends Specification {

  def is =
    s2"""
      This is a specification to check the 'Hello World' string
      The 'Hello World' string should
        contain 11 characters   $e1
       |start with 'Hello'      $e2
       |end with 'World'        $e3""".stripMargin

  def e1 = "Hello World" must have size (11)

  def e2 = "Hello World" must startWith("Hello")

  def e3 = "Hello World" must endWith("World")

}
