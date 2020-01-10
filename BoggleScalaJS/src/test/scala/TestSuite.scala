package Boggle

import Helper.isSquare
import org.scalatest._

class TestSuite extends FunSuite {
  test("isSquare") {
    List.range(0, 100).foreach{
      n => assert(isSquare(n * n))
    }
  }

  test("5 is not square") {
    assert(!isSquare(5))
  }
}
