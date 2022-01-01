import scala.language.reflectiveCalls
import scala.reflect.Selectable.reflectiveSelectable
import scala.util.chaining.scalaUtilChainingOps

object AOC {
  private def runSolve(day: String): Unit =
    Class
      .forName(s"days.Day$day")
      .getDeclaredConstructor()
      .newInstance()
      .asInstanceOf[{ def solve(input: Seq[String]): (Long, Long) }]
      .solve(InputHelper.getInput(day))
      .pipe { case (p1, p2) => print(p1, p2, day) }

  private def print(part1: Long, part2: Long, day: String): Unit =
    println(s" ~~~ Day $day ~~~\npart 1: $part1\npart 2: $part2")

  private def runAllSolves(): Unit =
    for (dayNr <- 1 to 10) {
      runSolve(dayNr.toString)
    }

  @main def main(dayToRun: String*): Unit = {
    val startTime = System.currentTimeMillis()
    dayToRun.headOption.map(runSolve).getOrElse(runAllSolves())
    val endTime = System.currentTimeMillis()
    println(s"Elapsed time: ${endTime - startTime} ms")
  }
}
