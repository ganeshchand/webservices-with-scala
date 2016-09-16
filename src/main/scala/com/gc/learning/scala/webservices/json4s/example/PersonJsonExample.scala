package com.gc.learning.scala.webservices.json4s.example
import org.json4s._
import org.json4s.jackson.{JsonMethods, Serialization}
import org.json4s.jackson.JsonMethods._
/**
  * Created by ganeshchand on 9/13/16.
  */

case class Person(firstName: String,
                  lastName: String,
                  age: Int,
                  address: Address,
                  phoneNumber: List[PhoneNumber],
                  children: Option[List[Children]] // optional
                 )
case class Address(streetAddress: String, city: String, state: String, postalCode: String)
case class PhoneNumber(`type`: String, number: String)
case class Children(name: String, age: Int, birthdate: Option[java.util.Date])


object PersonJsonExample {
  def main(args: Array[String]): Unit = {
    implicit val formats = DefaultFormats
    val jsonFilePath = this.getClass.getResource("/json/sample/person.json").getPath
    println(s"Reading file from $jsonFilePath")
    val content = scala.io.Source.fromFile(jsonFilePath).mkString

    val parsedJson = JsonMethods.parse(content)


    println(parsedJson.getClass.getCanonicalName)
//    println(pretty(per)) // pretty print

    // mapping to case class

    val person = parsedJson(0).extract[Person]
     println(person.firstName)
  }
}
