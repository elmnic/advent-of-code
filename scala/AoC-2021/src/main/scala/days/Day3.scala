package days

import scala.util.chaining.scalaUtilChainingOps

object Day3 {

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

    println(s"Day 3\n\tpart 1: $resultPart1\n\tpart 2: $resultPart2")
    (resultPart1, resultPart2)
  }
}
