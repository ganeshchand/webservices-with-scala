package com.gc.learning.scala.webservices.dispatcher.example

import dispatch._
import Defaults._
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._

/**
  * Created by ganeshchand on 9/1/16.
  */
case class Comment(postId: String, id: Int, name: String, email: String, body: String)

object HttpClientUsingDispatch {
  def main(args: Array[String]): Unit = {

    val targetURL = "http://jsonplaceholder.typicode.com/comments"
    val svc = url(targetURL)
    val comments = Http(svc OK as.String)
    val c = comments()
    //    println(c)

    implicit val formats = DefaultFormats
    val jsonComment = parse(c)
    val cmtList = jsonComment.children
    val cmts = for (comment <- cmtList) yield comment.extract[Comment]

    val cmts1 = cmtList.map { comment =>
      comment.extract[Comment]
    }

    println(cmts.head.email)
    println(cmts1.head.email)

    def host = :/("github.com")


  }

}
