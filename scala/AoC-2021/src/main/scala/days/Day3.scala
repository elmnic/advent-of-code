package days

import scala.util.chaining.scalaUtilChainingOps

object Day3 {

  def solve(input: Seq[String]): Unit = {

    val testInput =
      """00100
        |11110
        |10110
        |10111
        |10101
        |01111
        |00111
        |11100
        |10000
        |11001
        |00010
        |01010""".stripMargin.split("\n").toList

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

    println(s"Day 3 - part 1: ${resultPart1}, part 2: $resultPart2")
  }
}
