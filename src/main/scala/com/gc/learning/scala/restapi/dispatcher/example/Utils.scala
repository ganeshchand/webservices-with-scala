package com.gc.learning.scala.restapi.dispatcher.example

/**
  * Created by ganeshchand on 9/5/16.
  */
object Utils {
    def getFilePathForResource(resourceName: String) = getClass.getResource(s"/$resourceName")
}
