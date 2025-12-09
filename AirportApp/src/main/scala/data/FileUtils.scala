package data

import java.io.{FileOutputStream, ObjectOutputStream}
import scala.io.Source
import scala.util.Try

object FileUtils {

  /**
   * This function is used to check if the line is valid or not
   * @param s: String
   * @return Boolean: true if the line is invalid, false otherwise
   */
  def isInvalidLine(s: String): Boolean = {

    if(s.trim.isEmpty) true
    else {
      val delimiter   = FlightsLoaderConfig.delimiter
      val sLen        = FlightsLoaderConfig.headers.length

      val tokens = s.split(delimiter)

      tokens.length != sLen
    }
  }

  /**
   * This function is used to read the file located in the path `filePath` and return a list of lines of the file
   *
   * @param filePath: String
   * @return List[String]
   */
  def getLinesFromFile(filePath: String): List[String] = {

    val file = Source.fromFile(filePath)

    try {
      file.getLines().toList
    } finally {
      file.close()
    }
  }

  /**
   * This function is used to load the rows from the file lines
   *
   * @param fileLines: Seq[String]
   * @return Seq[Try[Row]]
   */
  def loadFromFileLines(fileLines: Seq[String]): Seq[Try[Row]] = {
    val delimiter   = FlightsLoaderConfig.delimiter
    val hasHeader = FlightsLoaderConfig.hasHeaders

    // Si hay header, lo eliminamos
    val linesToProcess =
      if (hasHeader) fileLines.drop(1)
      else fileLines

    linesToProcess.map{ line =>
        val tokens = line.split(delimiter).toList
        Row.fromStringList(tokens)
    }
  }

  def writeFile(flights: Seq[Flight], outputFilePath: String): Unit = {
    val out = new ObjectOutputStream(new FileOutputStream(outputFilePath))
    out.writeObject(flights)
    out.close()
  }

}