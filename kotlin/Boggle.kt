class Die(val faces: List<String>) {
  fun roll(): String {
    return this.faces.shuffled()[0]
  }
}


// class BoggleBoard(val dice: Array<Array<Die>>) {
//   fun shuffled(): BoggleBoard {
//     // BoggleBoard()
//   }
// }
