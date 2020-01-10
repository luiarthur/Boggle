package Boggle

import kotlin.test.Test
import kotlin.test.assertTrue

class LibraryTest {
    @Test fun testDieRoll() {
        val die = Die("A", "B")
        val roll = die.roll()

        assertTrue(die.numFaces == 2, "Number of faces should be 2")
        assertTrue(roll == "A" || roll == "B")
    }

    @Test fun testBoggleSolve() {
      val board = Board()
      val solution = board.solve()

      println("Board:")
      board.display()
      println()

      println("Solution:")
      println(solution.sortedWith(compareBy({it.length})).reversed())
    }
}
