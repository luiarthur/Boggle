import java.util.Random

class Die(vararg val faces: String) {
  val numFaces = faces.size

  fun roll(): String {
    val idx = Random().nextInt(numFaces)
    return this.faces[idx]
  }
}

/* Test
val d = Die("A", "B")
d.roll()
 */


class BoggleBoard(val dice: Array<Array<Die>>) {
  val rows = dice.size
  val cols = dice[0].size

  // TODO: Assert that number of columns is the same every row.

  fun shuffled(): Array<Array<String>> {
    val newDiceFlat = dice.flatten().shuffled()
    val newDice = Array(rows) {
      r -> Array(cols) {
        c -> newDiceFlat[r * c + c].roll()
      }
    }

    return newDice
  }
}

// Test
val BB = BoggleBoard(arrayOf(
    arrayOf(Die("A", "B"), Die("C", "D")),
    arrayOf(Die("X", "Y"), Die("W", "U"))))
