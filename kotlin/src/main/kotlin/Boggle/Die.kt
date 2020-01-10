package Boggle

import java.util.Random

class Die(vararg val faces: String) {
  val numFaces = faces.size

  fun roll(): String {
    val idx = Random().nextInt(numFaces)
    return faces[idx]
  }
}


