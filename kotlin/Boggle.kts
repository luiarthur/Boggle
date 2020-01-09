import java.util.Random
import java.io.File

fun rootIsInt(x: Int, eps: Double=1e-6): Boolean {
  val sqrt_x = Math.sqrt(x.toDouble())
  return Math.abs(sqrt_x - Math.round(sqrt_x)) < eps
}

class Die(vararg val faces: String) {
  val numFaces = faces.size

  fun roll(): String {
    val idx = Random().nextInt(numFaces)
    return this.faces[idx]
  }
}


class BoggleBoard(val dice: Array<Die>) {
  val numDice = dice.size
  // require(numDice > 0 && rootIsInt(numDice))

  val rows = Math.sqrt(numDice.toDouble()).toInt()
  val cols = rows
  var value = shuffled()
  val maxLetters = dice.size

  val allMoves = listOf(
      Pair(0, 1),
      Pair(0, -1),
      Pair(1, 0),
      Pair(-1, 0),
      Pair(1, 1),
      Pair(-1, -1),
      Pair(1, -1),
      Pair(-1, 1)
  )

  private fun shuffled(): Array<Array<String>> {
    val reorderedDice = dice.toList().shuffled().toTypedArray()
    val newDice = Array(rows) {
      r -> Array(cols) {
        c -> reorderedDice[r * cols + c].roll()
      }
    }

    return newDice
  }

  fun shuffle() {
    this.value = shuffled()
  }

  fun display() {
    value.forEach {
      // row -> println(row.joinToString(","))
      row -> row.forEach {
        v -> print("%2s  ".format(v))
      }
      println()
    }
  }

  // TODO
  fun solve(dict: List<String>,
            path: List<Pair<Int, Int>>,
            solution: List<String> = listOf(), 
            minLetters: Int = 3,
            _dict: List<String> = listOf()): List<String> {
    if (dict.size == 0) {
      return solution
    } else {
      // All valid moves
      val validMoves = allMoves.filter{move -> isValidMove(path, move)}

      // Create each possible chain
      val sols = validMoves.map { move ->
        val newPath = path + listOf(makeMove(path, move))
        val head = chain(newPath)
        val newDict = dict.filter{word -> word.startsWith(head)}
        val newSolution = if (newDict.contains(head)) {
          solution + listOf(head)
        } else {
          solution
        }
        solve(newDict, newPath, newSolution, minLetters, _dict)
      } 

      return sols.flatten().distinct()
    }
  }

  fun chain(path: List<Pair<Int, Int>>): String {
    return path.map{(r, c) -> value[r][c]}.joinToString("")
  }

  fun visited(path: List<Pair<Int, Int>>, pos: Pair<Int, Int>): Boolean {
    return path.contains(pos)
  }

  fun onBoard(pos: Pair<Int, Int>): Boolean {
    val (r, c) = pos
    return r >= 0 && c >= 0 && r < rows && c < cols
  }

  fun makeMove(path: List<Pair<Int, Int>>, move: Pair<Int, Int>): Pair<Int, Int> {
    val (r, c) = path.last()
    val (a, b) = move
    return Pair(r + a, c + b)
  }

  fun isValidMove(path: List<Pair<Int, Int>>, move: Pair<Int, Int>): Boolean {
    val pos = makeMove(path, move)
    return (!visited(path, pos)) && onBoard(pos)
  }

  fun solveAll(dict: List<String>): List<String> {
    val allSolutions = List(rows) {
      r -> List(cols) {
        c -> solve(dict, listOf(Pair(r, c)))
      }.flatten()
    }
    return allSolutions.flatten().distinct()
  }
}



fun makeBoradFromFile(fname: String): BoggleBoard {
  val lines = File(fname).readLines()
  val dice = lines.map{
    line -> Die(*line.toUpperCase().split(',').toTypedArray())
  }
  return BoggleBoard(dice.toTypedArray())
}


// MAIN
val board = makeBoradFromFile("misc/dice.txt")
// board.shuffle()

// val dict = File("../scala/big_dict.txt").readLines().map{w -> w.toUpperCase()}
val dict = File("misc/scrabble_dict.txt").readLines()
val d = dict.filter{word -> word.length <= 16 && word.length >= 3}

board.display()
println("Hit Enter to see solution ..."); readLine()
println(board.solveAll(d).sorted())
