package Boggle

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js
import scala.util.Random
import Helper.isSquare

@JSExportTopLevel("Board")
case class Board(dice: js.Array[Die],
                 dictionary: js.Array[String],
                 minLetters: Int=3) {
  type Matrix = Array[Array[String]]

  val numDice = dice.size
  require(isSquare(numDice))

  val rows = math.sqrt(numDice).toInt
  val cols = rows
  var letters = Array.ofDim[String](rows, cols)

  private def shuffled(): Matrix = {
    ???
  }

}
