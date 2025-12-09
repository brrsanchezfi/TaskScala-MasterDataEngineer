package data

 import com.typesafe.config.{Config, ConfigFactory}
 import scala.jdk.CollectionConverters._


object FlightsLoaderConfig {

  val config: Config = ConfigFactory.load().getConfig("flightsLoader")

  val filePath: String = config.getString("filePath")
  val hasHeaders: Boolean = config.getBoolean("hasHeaders")
  val headersLength: Int = config.getStringList("headers").size()
  val delimiter: String = config.getString("delimiter")
  val outputDir: String = config.getString("outputDir")
  val headers: List[String] = config.getStringList("headers").asScala.toList
  val columnIndexMap: Map[String, Int] = headers.map(x => (x, headers.indexOf(x))).toMap
  val filteredOrigin: List[String] = config.getStringList("filteredOrigin").asScala.toList
}
