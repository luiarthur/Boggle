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
    println(value)
    value.forEach {
      // row -> println(row.joinToString(","))
      row -> row.forEach {
        v -> print("%2s  ".format(v))
      }
      println()
    }
  }

  // TODO
  fun solve(dict: List<String>, solution: List<String> = listOf(), 
            path: List<Pair<Int, Int>> = listOf(Pair(0, 0)),
            minLetters: Int = 3): List<String> {
    if (path.last() == Pair(rows, cols)) return solution else {
      return listOf("Bob")
      /* TODO
      For each starting position, make every possible chain.
      Cut off the chain if it's not a real word.
       */
    }
  }


  fun visited(path: List<Pair<Int, Int>>, pos: Pair<Int, Int>): Boolean {
    return path.contains(pos)
  }

  fun onBoard(pos: Pair<Int, Int>): Boolean {
    val (r, c) = pos
    return r >= 0 && c >= 0 && r < rows && c < cols
  }

  fun isValidMove(path: List<Pair<Int, Int>>, move: Pair<Int, Int>): Boolean {
    val (r, c) = path[0]
    val (a, b) = move
    val pos = Pair(r + a, c + b)

    return (!visited(path, pos)) && onBoard(pos)
  }
}



fun makeBoradFromFile(fname: String): BoggleBoard {
  val lines = File(fname).readLines()
  val dice = lines.map{
    line -> Die(*line.toUpperCase().split(',').toTypedArray())
  }
  return BoggleBoard(dice.toTypedArray())
}


// Test
val board = makeBoradFromFile("misc/dice.txt")
board.shuffle()
board.display()


val dict = File("../scala/big_dict.txt").readLines()
val d = dict.filter{word -> word.length <= 16 && word.length >= 2}

val solution = board.solve(d)
solution.forEach{word -> println(word)}
