package com.gc.learning.scala.webservices.json4s.example
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
  /**
  * Created by ganeshchand on 9/11/16.
  */
object Json4sExampleBasic {
    def main(args: Array[String]): Unit = {

      // parse json string. It returns a JObject

      val json = parse("""
         { "name": "joe",
           "address": {
             "street": "Bulevard",
             "city": "Helsinki"
           },
           "children": [
             {
               "name": "Mary",
               "age": 5,
               "birthdate": "2004-09-04T18:06:22Z"
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         }
       """)
      println(json.getClass.getCanonicalName)
      println(pretty(json)) // pretty print

      // retrieve a field
      // XQuery style
//      val ages = (json \\ "age").children.map(jInt => compact(jInt))
//      ages.foreach(println)


      val ages = for {
        JInt(x) <- json \\ "age"
      } yield x

      println(ages.getClass.getCanonicalName)


      /*
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
*/
    }

}
