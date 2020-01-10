package Boggle

data class Coord(val x: Int, val y: Int) {
  operator fun plus(that: Coord): Coord {
    return Coord(this.x + that.x, this.y + that.y)
  }
}
