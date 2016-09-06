import org.specs2.Specification

/**
  * Created by ganeshchand on 9/5/16.
  */
class UtilsTest extends Specification {

  def is =
    s2"""
         This is a specification to Check Utils to read a file from a resource directory

         The filePath must
            not be null  $isNotNull
            file path end with 'application.conf' $endsWith

    """.stripMargin

  def applicationConfFilePath = com.gc.learning.scala.restapi.dispatcher.example.Utils.getFilePathForResource("application.conf")
  def isNotNull = applicationConfFilePath.getFile.length must greaterThan(0)
  def endsWith = applicationConfFilePath.getFile must endWith("application.conf1")
}
