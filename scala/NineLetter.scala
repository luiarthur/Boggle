import scala.util.Random.shuffle

// NOT DONE. TODO

object NineLetter {
  //private val dictionary = io.Source.fromFile("dictionary.txt").getLines.toList
  private val dictionary = io.Source.fromFile("big_dict.txt").getLines.toList

  def isValidWord(w:String) = dictionary.contains(w)
  def candidates(w:String) = {
    dictionary.filter(_.take(w.size) == w)
  }
  def isPotentialWord(w:String) = w == "" || candidates(w).size > 0
  def removeAt(x:String, i:Int) = {
    val (a,b) = x.splitAt(i)
    a + b.tail
  }


  def find(input:String) = {
    //val words = collection.mutable.Set[String]()
    //val visited = collection.mutable.Set[String]()

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

    search("", input, List()).distinct.sorted
  }


  def main(args:Array[String]) {
    val input = args(0)
    println("Here is the input: " + input)
    val words = find(input)
    println("Here is the result:")
    words.filter(_.size == input.size).foreach(println)
  }
}

// Test
//val letters = "unamusingly"
//val letters = "robbery"
//val letters = "nose"
//val input = shuffle(letters.toList).mkString
//val result = NineLetter.find(input)

//result.filter(_.size == letters.size)
