package Boggle

import scala.util.Random.shuffle
import scala.io.Source

import scala.scalajs.js
import js.JSConverters._

object Helper {
  def isSquare(n: Int): Boolean = math.sqrt(n).isWhole

  def readLinesFromResourceDir(fpath: String): List[String] = {
    println(Source.fromResource(fpath))
    return Source.fromResource(fpath).getLines().toList
  }

  val defaultDice: js.Array[Die] = {
    // val lines = Helper.readLinesFromResourceDir("/dice.txt")
    // val dice = lines.map(line => Die(line.toUpperCase.split(",").toJSArray))
    // dice.toJSArray
    Array.fill(16)(Die(js.Array("A", "B", "N"))).toJSArray
  }

  val defaultDict: js.Array[String] = {
    //Helper.readLinesFromResourceDir("/dict.txt").toJSArray
    js.Array("BAN", "NAB", "ANN", "CAN", "BANANA")

  }
}

