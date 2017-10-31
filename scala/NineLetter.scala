import scala.util.Random.shuffle

// NOT DONE. TODO

object NineLetter {
  private val dictionary = io.Source.fromFile("dictionary.txt").getLines.toList

  def isValidWord(w:String) = dictionary.contains(w)
  def candidates(w:String) = {
    dictionary.filter(_.take(w.size) == w)
  }
  def isPotentialWord(w:String) = w == "" || candidates(w).size > 0
  def removeAt(x:String, i:Int) = {
    val (a,b) = x.splitAt(i)
    a + b.tail
  }

  def search(curr:String, remains:String, words:List[String]):List[String] = {
    if (remains == "" || remains > ""  && !isPotentialWord(curr)) {
      if (isValidWord(curr)) curr :: words else words
    } else  {
      val idxLeft = List.range(0, remains.size)
      idxLeft.par.flatMap{i => 
        val newWords = if (isValidWord(curr)) curr :: words else words
        search(curr + remains(i), removeAt(remains, i), newWords)
      }.distinct.toList
    }
  }


  def main(args:Array[String]) {
    val word = args(0)
    val words = search("", word, List()).toSet
    words.foreach(println)
  }
}

// Test
//val letters = "theaters"
val letters = "robbery"
val input = shuffle(letters.toList).mkString
val result = NineLetter.search("", input, List()).distinct.sorted

result.filter(_.size == 6)
