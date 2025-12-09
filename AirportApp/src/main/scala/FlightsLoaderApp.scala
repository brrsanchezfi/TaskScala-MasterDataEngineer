import data.{FileUtils, Flight, FlightsFileReport, Row, FlightsLoaderConfig}
import java.nio.file.{Files, Paths}
import scala.util.Try

object FlightsLoaderApp extends App {

  def sanitize(name: String): String =
    name.replaceAll("[^a-zA-Z0-9-_\\.]", "_")

  val filePath: String = FlightsLoaderConfig.filePath
  val outputDir: String = FlightsLoaderConfig.outputDir

  // Crear el directorio si no existe
  Files.createDirectories(Paths.get(outputDir))

  val fileLines: Seq[String] = FileUtils.getLinesFromFile(filePath)
  val rows: Seq[Try[Row]] = FileUtils.loadFromFileLines(fileLines)
  val flightReport: FlightsFileReport = FlightsFileReport.fromRows(rows)
  val flights: Seq[Flight] = flightReport.flights

  val origins: Seq[String] = flights.map(_.origin.toString).distinct

  for (origin <- origins) {

    // Filtrar vuelos del origen
    val filteredFlights =
      flights.filter(_.origin.toString == origin)

    // Filtrar retrasados y ordenarlos
    val delayedFlights =
      filteredFlights.filter(_.isDelayed).sorted

    // Filtrar NO retrasados y ordenarlos
    val notDelayedFlights =
      filteredFlights.filter(!_.isDelayed).sorted

    val sanitizeOrigin = sanitize(origin)

    // Paths de salida
    val delayedFlightsObj =
      s"$outputDir${sanitizeOrigin}_delayed.obj"

    val flightsObj =
      s"$outputDir${sanitizeOrigin}.obj"

    // Escribir ficheros .obj usando FileUtils
    FileUtils.writeFile(delayedFlights, delayedFlightsObj)
    FileUtils.writeFile(notDelayedFlights, flightsObj)
  }

  println(flightReport)
}
