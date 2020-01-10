object Helper {
  fun readLinesFromResourceDir(fpath: String): List<String> {
    return this::class.java
                      .getResourceAsStream(fpath)
                      .bufferedReader().readLines()
  }
}
