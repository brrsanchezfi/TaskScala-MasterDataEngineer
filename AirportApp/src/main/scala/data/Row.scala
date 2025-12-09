package data
import scala.util.Try

/**
 * This class is used to represent a row of the flights data. It contains the following fields:
 * @param flDate: String
 * @param originAirportId: Long
 * @param origin: String
 * @param originCityName: String
 * @param originStateAbr: String
 * @param destAirportId: Long
 * @param dest: String
 * @param destCityName: String
 * @param destStateAbr: String
 * @param depTime: String
 * @param depDelay: Double
 * @param arrTime: String
 * @param arrDelay: Double
 */
case class Row (
                 flDate: String,
                 originAirportId: Long,
                 origin: String,
                 originCityName: String,
                 originStateAbr: String,
                 destAirportId: Long,
                 dest: String,
                 destCityName: String,
                 destStateAbr: String,
                 depTime: String,
                 depDelay: Double,
                 arrTime: String,
                 arrDelay: Double
               )

object Row {
  /**
   * This method is used to create a Row object from a list of tokens. It returns a Try[Row] object.
   * If the list of tokens is not valid or any of the token is invalid, it returns a Failure object. Otherwise, it returns a Success object.
   *
   * @param tokens: Seq[String]
   * @return Try[Row]
   */
  def fromStringList(tokens: Seq[String]): Try[Row] = {

    if (tokens.length != 13) {
      return Try(throw new IllegalArgumentException(
        s"Expected 13 items but found ${tokens.length}"
      ))
    }
    Try {
      Row.apply(
        flDate          = tokens.apply(0).trim,
        originAirportId = tokens.apply(1).trim.toLong,
        origin          = tokens.apply(2).trim,
        originCityName  = tokens.apply(3).trim,
        originStateAbr  = tokens.apply(4).trim,
        destAirportId   = tokens.apply(5).trim.toLong,
        dest            = tokens.apply(6).trim,
        destCityName    = tokens.apply(7).trim,
        destStateAbr    = tokens.apply(8).trim,
        depTime         = tokens.apply(9).trim,
        depDelay        = tokens.apply(10).trim.toDouble,
        arrTime         = tokens.apply(11).trim,
        arrDelay        = tokens.apply(12).trim.toDouble
      )
    }
  }
}