package data
import com.sun.media.sound.InvalidFormatException


/**
 * This class is used to represent a date of a flight
 *
 * @param day: Int
 * @param month: Int
 * @param year: Int
 */
case class FlightDate(day: Int,
                      month: Int,
                      year: Int) {

  lazy val formatted: String = f"$day%02d/$month%02d/$year%04d"

  override def toString: String = formatted

}

object FlightDate {
  /**
   * This function is used to convert a string to a FlightDate
   * @param date: String
   * @return FlightDate
   */
  def fromString(date: String): FlightDate = {

    val onlyDate = date.split(" ")(0)

    onlyDate.split("/") match {

      case Array(mm, dd, yyyy) =>
        val day   = dd.toIntOption.getOrElse(
          throw new InvalidFormatException(s"Invalid day: $dd")
        )
        val month = mm.toIntOption.getOrElse(
          throw new InvalidFormatException(s"Invalid month: $mm")
        )
        val year  = yyyy.toIntOption.getOrElse(
          throw new InvalidFormatException(s"Invalid year: $yyyy")
        )

        if (month < 1 || month > 12)
          throw new InvalidFormatException(s"Month invalid: $month")

        if (day < 1 || day > 31)
          throw new InvalidFormatException(s"Day invalid: $day")

        if (year < 1987)
          throw new InvalidFormatException(s"year invalid: $year")

        FlightDate(day, month, year)

      case _ =>
        throw new InvalidFormatException(s"$date has invalid format")
    }
  }


}
