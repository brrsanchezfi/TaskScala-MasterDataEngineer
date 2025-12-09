package data

import scala.util.Try

/**
 * This class is used to represent a report of the flights file with the valid rows, invalid rows and the flights
 * extracted from the valid rows.
 * @param validRows: Seq[Row]
 * @param invalidRows: Seq[String]
 * @param flights: Seq[Flight]
 */
case class FlightsFileReport(validRows: Seq[Row],
                         invalidRows: Seq[String],
                         flights: Seq[Flight]
                        ) {

  override val toString: String = {

    val errorSummary: Map[String, Int] =
      invalidRows.groupBy(identity).map{case(msg, list) => s"<$msg>" -> list.size}

    val formattedErrors = errorSummary.map { case (error, count) => s"$error:$count"}.mkString("\n")


    s"""FlightsReport:
       |
       | - ${validRows.size} valid rows.
       | - ${invalidRows.size} invalid rows.
       |Error summary:
       |$formattedErrors
       |""".stripMargin
  }
}

object FlightsFileReport {
  /**
   * This function is used to create a FlightsFileReport from a list of Try[Row] objects where each Try[Row] represents a row
   * loaded from the file. If the row is valid, it is added to the validRows list, otherwise the error message is added to
   * the invalidRows list. Finally, the valid rows are converted to Flight objects and added to the flights list.
   *
   * @param rows: Seq[Try[Row]]
   * @return FlightsFileReport
   */
  def fromRows(rows: Seq[Try[Row]]): FlightsFileReport = {

    val validRows: Seq[Row] = rows.filter(_.isSuccess).map(_.get)

    val invalidRows: Seq[String] = rows.filter(_.isFailure).map(_.failed.get.toString)

    val flight: Seq[Flight] = validRows.map(Flight.fromRow)

    FlightsFileReport(validRows, invalidRows, flight)
  }
}
