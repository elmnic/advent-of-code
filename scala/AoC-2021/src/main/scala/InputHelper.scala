import scalaj.http.Http

import java.nio.file.{Files, Path, Paths}
import scala.util.chaining.scalaUtilChainingOps

object InputHelper {

  private val directory = "src/main/resources"

  def getInput(day: String): Seq[String] =
    if (Files.isRegularFile(Paths.get(s"$directory/day$day.in"))) {
      // File exists, getting cached input
      getCached(day)
    } else {
      // File not cached, sending request and caching input
      Http(s"https://adventofcode.com/2021/day/$day/input")
        .cookie("session", System.getenv("AOC_TOKEN"))
        .asString
        .body
        .split("\n")
        .toSeq
        .tap(input => cacheInput(day, input))
    }

  private def getCached(day: String): Seq[String] =
    Files.readString(Path.of(s"$directory/day$day.in")).split("\n").toSeq

  private def cacheInput(day: String, input: Seq[String]): Unit = {
    val path = Path.of(s"$directory/day$day.in")
    // Writing input to file in resources
    Files.write(path, input.mkString("\n").getBytes)
  }
}
