package days

import scala.util.chaining.scalaUtilChainingOps

class Day3 {

  def solve(input: Seq[String]): (Int, Int) = {

    val gammaRate =
      input
        .map(_.toList)
        .transpose
        .map(_.groupBy(_.toString).maxBy(_._2.size)._1)
        .mkString
        .pipe(s => Integer.parseInt(s, 2))
    val epsilonRate =
      input
        .map(_.toList)
        .transpose
        .map(_.groupBy(_.toString).minBy(_._2.size)._1)
        .mkString
        .pipe(s => Integer.parseInt(s, 2))

    val resultPart1 = gammaRate * epsilonRate
    val resultPart2 = 0

    (resultPart1, resultPart2)
  }
}
