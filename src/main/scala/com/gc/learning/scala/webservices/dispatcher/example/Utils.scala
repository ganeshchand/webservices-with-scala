package com.gc.learning.scala.webservices.dispatcher.example

/**
  * Created by ganeshchand on 9/5/16.
  */
object Utils {
    def getFilePathForResource(resourceName: String) = getClass.getResource(s"/$resourceName")
}
