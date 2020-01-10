package Boggle

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js
import scala.util.Random

case class Board(dice: js.Array[Die],
                 dictionary: js.Array[String],
                 minLetters: Int=3)
