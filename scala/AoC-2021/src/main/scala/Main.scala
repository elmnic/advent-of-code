import scala.language.reflectiveCalls
import scala.util.chaining.scalaUtilChainingOps

object Main extends App {
  val dayToRun = args.headOption.flatMap(_.toIntOption).map(_.toString)

  private def runSolve(day: String): Unit =
    Class.forName(s"days.Day$day")
      .getDeclaredConstructor()
      .newInstance()
      .asInstanceOf[ {def solve(input: Seq[String]): (Int, Int)}]
      .solve(InputHelper.getInput(day))
      .pipe { case (p1, p2) => print(p1, p2, day) }

  private def print(part1: Int, part2: Int, day: String): Unit =
    println(s" ~~~ Day $day ~~~\npart 1: $part1\npart 2: $part2")

  private def runAllSolves(): Unit = {
    for (dayNr <- 1 to 4) {
      runSolve(dayNr.toString)
    }
  }

  val startTime = System.currentTimeMillis()
  dayToRun.map(runSolve).getOrElse(runAllSolves())
  val endTime = System.currentTimeMillis()
  println(s"Elapsed time: ${endTime - startTime} ms")

}
