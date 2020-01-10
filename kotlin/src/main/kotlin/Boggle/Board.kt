package Boggle

import java.util.Random
import java.io.File

/**
 * Check if square root of an integer is an integer.
 */
fun rootIsInt(x: Int, eps: Double=1e-6): Boolean {
  val sqrt_x = Math.sqrt(x.toDouble())
  return Math.abs(sqrt_x - Math.round(sqrt_x)) < eps
}

fun readDice(pathToDice: String = ""): Array<Die> {
  val lines = if (pathToDice == "") {
    Helper.readLinesFromResourceDir("/dice.txt")
  } else {
    File(pathToDice).readLines()
  }

  val dice = lines.map{
    line -> Die(*line.toUpperCase().split(',').toTypedArray())
  }.toTypedArray()

  return dice
}

fun readDict(pathToDict: String = ""): List<String> {
  if (pathToDict == "") {
    return Helper.readLinesFromResourceDir("/scrabble_dict.txt")
  } else {
    return File(pathToDict).readLines()
  }
}


class Board(val dice: Array<Die> = readDice(),
            val dictionary: List<String> = readDict(),
            val minLetters: Int=3) {

  val numDice: Int = dice.size
  val rows: Int
  val cols: Int
  var letters:Array<Array<String>> = shuffled()
  val dictionaryLimited = dictionary.filter{w -> 
    w.length >= minLetters && w.length <= numDice
  }

  init {
    require(numDice > 0 && rootIsInt(numDice))
    rows = Math.sqrt(numDice.toDouble()).toInt()
    cols = rows
    shuffle()
  }

  constructor(pathToDice: String, pathToDict: String) : this(
    dice=readDice(pathToDice),
    dictionary=readDict(pathToDict),
    minLetters=3
  )

  private fun shuffled(): Array<Array<String>> {
    val reorderedDice = dice.toList().shuffled().toTypedArray()
    val newLetters = Array(rows) {
      r -> Array(cols) {
        c -> reorderedDice[r * cols + c].roll()
      }
    }

    return newLetters
  }

  fun shuffle(): Unit {
    this.letters = shuffled()
  }

  // All possible moves from current position
  private val allMoves = listOf(
    Coord(0, 1),
    Coord(0, -1),
    Coord(1, 0),
    Coord(-1, 0),
    Coord(1, 1),
    Coord(-1, -1),
    Coord(1, -1),
    Coord(-1, 1)
  )

  fun display() {
    letters.forEach {
      row -> row.forEach {
        v -> print("%2s  ".format(v))
      }
      println()
    }
  }

  fun chain(path: Array<Coord>): String {
    return path.map{coord -> letters[coord.x][coord.y]}.joinToString("")
  }

  fun visited(path: Array<Coord>, pos: Coord): Boolean {
    return path.contains(pos)
  }

  fun onBoard(pos: Coord): Boolean {
    val (r, c) = Pair(pos.x, pos.y)
    return r >= 0 && c >= 0 && r < rows && c < cols
  }

  fun makeMove(path: Array<Coord>, move: Coord): Coord {
    return path.last() + move
  }

  fun isValidMove(path: Array<Coord>, move: Coord): Boolean {
    val pos = makeMove(path, move)
    return (!visited(path, pos)) && onBoard(pos)
  }

  /** Solve entier Boggle board. */
  fun solve(): List<String> {
    val allSolutions = List(rows) {
      r -> List(cols) {
        c -> _solve(dictionaryLimited, arrayOf(Coord(r, c))).toList()
      }.flatten()
    }
    return allSolutions.flatten().distinct()
  }

  private fun _solve(
    dict: List<String>,
    path: Array<Coord>,
    solution: Array<String> = arrayOf()): Array<String> {

    if (dict.size == 0) return solution else {
      // All valid moves
      val validMoves = allMoves.filter{move -> isValidMove(path, move)}

      // Create each possible chain, given current path.
      val sols = validMoves.map{move -> 
        val newPath = path + arrayOf(makeMove(path, move))
        val head = chain(newPath)
        val newDict = dict.filter{word -> word.startsWith(head)}
        val newSolution = if (newDict.contains(head)) {
          solution + arrayOf(head)
        } else {
          solution
        }
        _solve(newDict, newPath, newSolution)
      }

      return sols.toTypedArray().flatten().distinct().toTypedArray()
    }
  }
}

