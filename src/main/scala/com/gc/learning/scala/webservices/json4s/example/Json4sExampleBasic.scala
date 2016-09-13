package com.gc.learning.scala.webservices.json4s.example
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
  /**
  * Created by ganeshchand on 9/11/16.
  */
object Json4sExampleBasic {
    def main(args: Array[String]): Unit = {

      // basic Json String parsing

      val sampleJsonString =
        """
          |{
          |"name": "John",
          |"id": "123",
          |"age": 26,
          |"weight": 65.5
          |}
        """.stripMargin
      println(sampleJsonString)

      val sampleJson = parse(sampleJsonString)
      println(sampleJson)
      println(pretty(sampleJson))


      // retrieve a field


      val age = compact(sampleJson \\ "age")
      println(s"Age is: $age")

      // remove a field

      val filteredSampleJson = sampleJson removeField {
        case JField("weight", _) => true
        case _ => false
      }

      // alternatively you can also use filterField method

      /*
      val filteredSampleJson = sampleJson filterField {
        case JField("weight", _) => false
        case _ => true  // everything else is not a filtered field
      }

       */

      println(pretty(filteredSampleJson))

      // add a field

      var json = ("name", "john") ~ ("id", "123")
      json = json  ~ ("ht", 172)

      //update a field

//      json = json transformField {
//        case ("ht", x) => ("height", x)
//      }

    }
}
