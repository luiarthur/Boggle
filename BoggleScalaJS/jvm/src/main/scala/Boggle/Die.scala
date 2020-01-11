package Boggle

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js
import scala.util.Random

@JSExportTopLevel("Die")
case class Die(faces: js.Array[String]) {
  @JSExport
  val numFaces = faces.size

  @JSExport
  def roll(): String = {
    val idx = Random.nextInt(numFaces)
    return faces(idx)
  }
}
