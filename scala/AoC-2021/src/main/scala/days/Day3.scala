package days

import scala.annotation.tailrec
import scala.util.chaining.scalaUtilChainingOps

class Day3 {

  def solve(input: Seq[String]): (Long, Long) = {

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

    @tailrec
    def filterOxygen(inputToReduce: Seq[String], bitPosition: Int): String =
      inputToReduce match {
        case Seq(line) => line
        case _         =>
          val max: String = inputToReduce
            .map(_.toList)
            .transpose
            .map(_.groupBy(_.toString).maxBy(_._2.size)._1)(bitPosition)
          val nextInput = inputToReduce
            .filter(_.charAt(bitPosition).toString == max)
          filterOxygen(nextInput, bitPosition + 1)
      }

    @tailrec
    def filterCO2(inputToReduce: Seq[String], bitPosition: Int): String =
      inputToReduce match {
        case Seq(line) => line
        case _         =>
          val min: String = inputToReduce
            .map(_.toList)
            .transpose
            .map(_.groupBy(_.toString))
            .map(_
              .toSeq
              .sortBy(_._2.size)
              .reverse
              .minBy(_._2.size)
              ._1)(bitPosition)
          val nextInput = inputToReduce
            .filter(_.charAt(bitPosition).toString == min)
          filterCO2(nextInput, bitPosition + 1)
      }

    val oxygenGeneratorRating = filterOxygen(input, 0)
      .pipe { s =>
        Integer.parseInt(s, 2)
      }
    val co2ScrubberRating = filterCO2(input, 0)
      .pipe(s => Integer.parseInt(s, 2))


    val resultPart2 = oxygenGeneratorRating * co2ScrubberRating

    (resultPart1, resultPart2)
  }
}
