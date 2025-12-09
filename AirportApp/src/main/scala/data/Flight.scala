package data

/**
 * This class is used to represent a flight with its information like the date, origin, destination, scheduled departure time,
 * scheduled arrival time, departure delay and arrival delay.
 *
 * @param flDate: String
 * @param origin: Airport
 * @param dest: Airport
 * @param scheduledDepTime: Time
 * @param scheduledArrTime: Time
 * @param depDelay: Double
 * @param arrDelay: Double
 */
case class Flight(flDate: String,
                  origin: Airport,
                  dest: Airport,
                  scheduledDepTime: Time,
                  scheduledArrTime: Time,
                  depDelay: Double,
                  arrDelay: Double)
extends Ordered[Flight]
{
  lazy val flightDate: FlightDate = FlightDate.fromString(flDate)

  lazy val actualDepTime: Time = Time.fromMinutes((scheduledDepTime.asMinutes + depDelay.toInt).abs)

  lazy val actualArrTime: Time = Time.fromMinutes((scheduledArrTime.asMinutes + arrDelay.toInt).abs)

  val isDelayed: Boolean = if (depDelay != 0 || arrDelay != 0) true else false

  override def compare(that: Flight): Int =
    this.actualArrTime.compare(that.actualArrTime)
}

object Flight {
  /**
   * This function is used to create a Flight object from a string with the information of the flight separated by a
   * delimiter defined in the configuration. The function returns a Flight object with the information of the flight.
   * The input string should have the following format:
   * "FL_DATE,ORIGIN_AIRPORT_ID,ORIGIN,ORIGIN_CITY_NAME,ORIGIN_STATE_ABR,DEST_AIRPORT_ID,DEST,DEST_CITY_NAME,DEST_STATE_ABR,DEP_TIME,ARR_TIME,DEP_DELAY,ARR_DELAY"
   *
   * @param flightInfo: String
   * @return Flight
   */
  def fromString(flightInfo: String): Flight = {
    val columns : Array[String] = flightInfo.split(FlightsLoaderConfig.delimiter)  // importado de FlightsLoaderConfig

    /**
     * This function is used to get the value of a column from the array of String generated from the row of the csv
     * and stored in the variable `columns`.
     * @param colName: String name of the column
     * @return String value of the column
     */
    def getColValue(colName: String): String = {
      val index = FlightsLoaderConfig.columnIndexMap(colName)
      columns(index)
    }

    val oriAirport = Airport(
      airportId = getColValue("ORIGIN_AIRPORT_ID").toLong,
      code = getColValue("ORIGIN"),
      cityName = getColValue("ORIGIN_CITY_NAME"),
      stateAbr = getColValue("ORIGIN_STATE_ABR"))

    val destAirport = Airport(
      airportId = getColValue("DEST_AIRPORT_ID").toLong,
      code = getColValue("DEST"),
      cityName = getColValue("DEST_CITY_NAME"),
      stateAbr = getColValue("DEST_STATE_ABR"))

    Flight(
      flDate = getColValue("FL_DATE"),
      origin = oriAirport,
      dest = destAirport,
      scheduledDepTime = Time.fromString(getColValue("DEP_TIME")),
      scheduledArrTime = Time.fromString(getColValue("ARR_TIME")),
      depDelay = getColValue("DEP_DELAY").toDouble,
      arrDelay = getColValue("ARR_DELAY").toDouble
    )
  }

  /**
   * This function is used to create a Flight object from a Row object. The function returns a Flight object with the
   * information of the flight.
   *
   * @param row: Row
   * @return Flight
   */
  def fromRow(row: Row): Flight = {

    val oriAirport = Airport(
      airportId = row.originAirportId.toLong,
      code = row.origin.toString,
      cityName = row.originCityName.toString,
      stateAbr = row.originStateAbr.toString)

    val destAirport = Airport(
      airportId = row.destAirportId.toLong,
      code = row.dest.toString,
      cityName = row.destCityName.toString,
      stateAbr = row.destStateAbr.toString)

    Flight(
      flDate = row.flDate.toString,
      origin = oriAirport,
      dest = destAirport,
      scheduledDepTime = Time.fromString(row.depTime.toString),
      scheduledArrTime = Time.fromString(row.arrTime.toString),
      depDelay = row.depDelay.toDouble,
      arrDelay = row.arrDelay.toDouble
    )
  }
}
