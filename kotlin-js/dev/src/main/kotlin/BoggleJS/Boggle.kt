package BoggleJS


fun nextInt(n: Int): Int {
  return Math.floor(Math.random() * n).toInt()
}


class Die(vararg val faces: String) {
  val numFaces = faces.size

  fun roll(): String {
    val idx = nextInt(numFaces)
    return faces[idx]
  }
}
