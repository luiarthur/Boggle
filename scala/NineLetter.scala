import scala.util.Random.shuffle

// NOT DONE. TODO

object NineLetter {
  //private val dictionary = io.Source.fromFile("dictionary.txt").getLines.toList
  private val dictionary = io.Source.fromFile("big_dict.txt").getLines.toList

  def isValidWord(w:String, dict:List[String]) = dict.contains(w)
  def candidates(w:String, dict:List[String]) = {
    dict.filter(_.take(w.size) == w)
  }
  def isPotentialWord(w:String, dict:List[String]) = w == "" || candidates(w,dict).size > 0
  def removeAt(x:String, i:Int) = {
    val (a,b) = x.splitAt(i)
    a + b.tail
  }
  def smallDictionary(word:String) = {
    val wordSet = word.toSet
    dictionary.filter(w => wordSet.subsetOf(w.toSet))
  }


  def find(input:String) = {
    //val words = collection.mutable.Set[String]()
    //val visited = collection.mutable.Set[String]()

    def search(curr:String, remains:String, words:List[String], dict:List[String]):List[String] = {
      if (remains == "" || remains > ""  && !isPotentialWord(curr,dict)) {
        if (isValidWord(curr,dict)) curr :: words else words
      } else  {
        val idxLeft = List.range(0, remains.size)
        idxLeft.par.flatMap{i => 
          val newDict = candidates(curr,dict)
          val newWords = if (isValidWord(curr,newDict)) curr :: words else words
          search(curr + remains(i), removeAt(remains, i), newWords, newDict)
        }.distinct.toList
      }
    }

    search("", input, List(), smallDictionary(input)).distinct.sorted
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
