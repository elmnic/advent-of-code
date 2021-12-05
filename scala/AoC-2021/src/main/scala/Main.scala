import scala.language.reflectiveCalls
import scala.util.chaining.scalaUtilChainingOps

object Main extends App {
  val fun = args.headOption.flatMap(_.toBooleanOption).getOrElse(false)

  private def runSolve(day: String): Unit =
    Class.forName(s"days.Day$day")
      .getDeclaredConstructor()
      .newInstance()
      .asInstanceOf[ {def solve(input: Seq[String]): (Int, Int)}]
      .solve(InputHelper.getInput(day))
      .pipe { case (p1, p2) => print(p1, p2, day) }

  private def print(part1: Int, part2: Int, day: String): Unit =
    println(s"Day $day\n\tpart 1: $part1\n\tpart 2: $part2")

  private def runBoringSolves(): Unit = {
    import days._
    new Day1().solve(InputHelper.getInput("1")).pipe { case (p1, p2) => print(p1, p2, "1") }
    new Day2().solve(InputHelper.getInput("2")).pipe { case (p1, p2) => print(p1, p2, "2") }
    new Day3().solve(InputHelper.getInput("3")).pipe { case (p1, p2) => print(p1, p2, "3") }
    new Day4().solve(InputHelper.getInput("4")).pipe { case (p1, p2) => print(p1, p2, "4") }
  }

  private def runFunSolves(): Unit = {
    for (dayNr <- 1 until 4) {
      runSolve(dayNr.toString)
    }
  }


  val startTime = System.currentTimeMillis()
  if (fun)
    runFunSolves()
  else
    runBoringSolves()
  val endTime = System.currentTimeMillis()
  println(s"Elapsed time: ${endTime - startTime} ms")

}
